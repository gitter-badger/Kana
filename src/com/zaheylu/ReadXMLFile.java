package com.zaheylu;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ReadXMLFile {
	private String path;

	public ReadXMLFile(String path) {
		this.path = path;
	}

	public void load() throws ParserConfigurationException, SAXException, IOException {

		File file = new File(path);
		DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = dBuilder.parse(file);
		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		if (doc.hasChildNodes()) {
			printNote(doc.getChildNodes());
		}

	}


	private static void printNote(NodeList nodeList) {

		for (int n = 0; n < nodeList.getLength(); n++) {
			Node tempNode = nodeList.item(n);
			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
				System.out.println("\nNode Name =" + tempNode.getNodeName() + " [OPEN]");
				System.out.println("Node Value =" + tempNode.getTextContent());
				if (tempNode.hasAttributes()) {
					NamedNodeMap nodeMap = tempNode.getAttributes();
					for (int i = 0; i < nodeMap.getLength(); i++) {
						Node node = nodeMap.item(i);
						System.out.println("attr name : " + node.getNodeName());
						System.out.println("attr value : " + node.getNodeValue());
					}
				}
				if (tempNode.hasChildNodes()) {
					printNote(tempNode.getChildNodes());
				}
				System.out.println("Node Name =" + tempNode.getNodeName() + " [CLOSE]");
			}
		}
	}


}