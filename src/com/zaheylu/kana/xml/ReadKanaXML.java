package com.zaheylu.kana.xml;

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

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.zaheylu.kana.xml.handler.DefaultReturnHandler;
@Deprecated
public class ReadKanaXML {

	private SAXParserFactory factory;
	private SAXParser saxParser;


	@Deprecated
	private Object loadXML(InputStream inputStream, DefaultReturnHandler handler) throws ParserConfigurationException, SAXException,
			UnsupportedEncodingException {
		factory = SAXParserFactory.newInstance();
		saxParser = factory.newSAXParser();
		Reader reader = new InputStreamReader(inputStream, "UTF-8");
		InputSource is = new InputSource(reader);
		is.setEncoding("UTF-8");
		try {
			saxParser.parse(is, handler);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return handler.getReturn();
	}

	@Deprecated
	public Object loadVocabulary(URL path, DefaultReturnHandler handler) throws IOException, ParserConfigurationException, SAXException {
		return loadXML(path.openStream(), handler);
	}
	@Deprecated
	public Object loadVocabulary(String path, DefaultReturnHandler handler) throws UnsupportedEncodingException, FileNotFoundException,
			ParserConfigurationException, SAXException {
		return loadXML(new FileInputStream(path), handler);
	}
}
