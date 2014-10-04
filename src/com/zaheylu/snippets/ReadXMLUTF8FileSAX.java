package com.zaheylu.snippets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;



public class ReadXMLUTF8FileSAX {

	private String path;
	private SAXParserFactory factory;
	private SAXParser saxParser;
	private DefaultHandler handler;



	public ReadXMLUTF8FileSAX(String path) throws SAXException, IOException, ParserConfigurationException {
		this.path = path;
		factory = SAXParserFactory.newInstance();
		saxParser = factory.newSAXParser();


		handler = new DefaultHandler() {

			ArrayList<String> context = new ArrayList<String>();
			String currContext = "";;

			public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
				context.add(qName);
				System.out.println("<" + qName + ">");

			}

			public void endElement(String uri, String localName, String qName) throws SAXException {
				System.out.println("</" + qName + ">");
				context.remove(context.size() - 1);
			}

			public void characters(char ch[], int start, int length) throws SAXException {

				currContext = "";
				for (String cont : context) {
					currContext += cont + ".";
				}					
				System.out.println(String.format("%s.%s", currContext, new String(ch, start, length)));

			}

		};



	}

	public void load() throws SAXException, IOException {
		File file = new File(path);
		InputStream inputStream= new FileInputStream(file);
		Reader reader = new InputStreamReader(inputStream,"UTF-8");
		InputSource is = new InputSource(reader);
		is.setEncoding("UTF-8");
		saxParser.parse(is, handler);

	}
	
	public void find(String arg) throws SAXException, IOException {
		File file = new File(path);
		InputStream inputStream= new FileInputStream(file);
		Reader reader = new InputStreamReader(inputStream,"UTF-8");
		InputSource is = new InputSource(reader);
		is.setEncoding("UTF-8");
		saxParser.parse(is, handler);

	}

}
