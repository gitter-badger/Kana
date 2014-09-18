package com.zaheylu.kana;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



public class ReadXMLEntryNames {
	private String path;

	public ReadXMLEntryNames(String path) {
		this.path = path;
	}

	public ArrayList<String> load() throws ParserConfigurationException, SAXException, IOException {
		System.out.println("Loading names from " + path);
		File fXmlFile = new File(path);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);

		doc.getDocumentElement().normalize();


		NodeList nList = doc.getElementsByTagName("entry");
		ArrayList<String> list = new ArrayList<String>();


		for (int temp = 0; temp < nList.getLength(); temp++) {

			Node nNode = nList.item(temp);

			// 

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) nNode;


				for (int n = 0; n < eElement.getElementsByTagName("name").getLength(); n++) {
					String s = eElement.getElementsByTagName("name").item(n).getTextContent();
					list.add(s);
					System.out.println(s);
				}
			}
		}
		System.out.println("Completed Loading names from " + path);
		return list;
	}
}