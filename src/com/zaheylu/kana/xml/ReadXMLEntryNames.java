package com.zaheylu.kana.xml;

import java.io.File;
import java.io.IOException;
import java.net.URL;
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

	public ReadXMLEntryNames() {

	}



	public ArrayList<String> load(String path) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		if (!new File(path).exists()) return null;
		return load(dBuilder.parse(path));
	}

	public ArrayList<String> load(URL path) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		return load(dBuilder.parse(path.openStream()));
	}

	private ArrayList<String> load(Document doc) throws ParserConfigurationException, SAXException, IOException {

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
				}
			}
		}
		return list;
	}
}