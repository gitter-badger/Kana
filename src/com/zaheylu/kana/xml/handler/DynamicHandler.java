package com.zaheylu.kana.xml.handler;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class DynamicHandler extends DefaultReturnHandler {

	private ArrayList<ArrayList<String>> s2;
	private int length;
	private boolean[] lookB;
	private String[] lookS;

	public DynamicHandler(String[] args) {
		length = args.length;
		lookB = new boolean[length];
		lookS = args;
		s2 = new ArrayList<ArrayList<String>>();
		for (int n = 0; n < length; n++) {
			s2.add(new ArrayList<String>());
		}
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		for (int n = 0; n < length; n++) {
			if (qName.compareTo(lookS[n]) == 0) {
				lookB[n] = true;
				break;
			}
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		String s = new String(ch, start, length);
		for (int n = 0; n < length; n++) {
			if (lookB[n]) {
				s2.get(n).add(s);
				break;
			}
		}
	}

	@Override
	public void startDocument() throws SAXException {

	}

	@Override
	public void endDocument() throws SAXException {

	}

	@Override
	public ArrayList<ArrayList<String>> getReturn() {
		return s2;
	}

}
