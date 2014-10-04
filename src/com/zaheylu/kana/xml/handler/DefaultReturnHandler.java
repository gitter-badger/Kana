package com.zaheylu.kana.xml.handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public abstract class DefaultReturnHandler extends DefaultHandler {

	protected SAXParserFactory factory;
	protected SAXParser saxParser;

	public abstract void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException;

	public abstract void endElement(String uri, String localName, String qName) throws SAXException;

	public abstract void characters(char ch[], int start, int length) throws SAXException;

	public abstract void startDocument() throws SAXException;

	public abstract void endDocument() throws SAXException;

	public abstract Object getReturn();

	public void load(File f) throws UnsupportedEncodingException, FileNotFoundException, ParserConfigurationException, SAXException  {
		loadXML(new FileInputStream(f));
	}
	
	public void load(String path) throws UnsupportedEncodingException, FileNotFoundException, ParserConfigurationException, SAXException {
		loadXML(new FileInputStream(path));
	}

	public void load(URL path) throws UnsupportedEncodingException, ParserConfigurationException, SAXException, IOException {
		loadXML(path.openStream());
	}

	protected void loadXML(InputStream inputStream) throws ParserConfigurationException, SAXException, UnsupportedEncodingException {
		factory = SAXParserFactory.newInstance();
		saxParser = factory.newSAXParser();
		Reader reader = new InputStreamReader(inputStream, "UTF-8");
		InputSource is = new InputSource(reader);
		is.setEncoding("UTF-8");
		try {
			saxParser.parse(is, this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
