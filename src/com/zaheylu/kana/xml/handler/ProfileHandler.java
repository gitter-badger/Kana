package com.zaheylu.kana.xml.handler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.zaheylu.kana.users.Profile;
import com.zaheylu.kana.users.SuccessEntry;


public class ProfileHandler extends DefaultReturnHandler {

	private SuccessEntry tmpEntry;
	private Profile profile;
	private boolean value = false;
	private boolean number = false;
	private boolean timestamp = false;

	private ProfileHandler() {}

	private void init() {
		profile = new Profile();
	}

	public static ProfileHandler newProfileHandler() {
		ProfileHandler result = new ProfileHandler();
		result.init();
		return result;
	}

	public Profile getReturn() {
		return profile;
	}

	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName.equalsIgnoreCase("ENTRY")) tmpEntry = new SuccessEntry();
		else if (qName.equalsIgnoreCase("VALUE")) value = true;
		else if (qName.equalsIgnoreCase("NUMBER")) number = true;
		else if (qName.equalsIgnoreCase("TIMESTAMP")) timestamp = true;
	}

	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equalsIgnoreCase("ENTRY")) profile.add(tmpEntry);
	}

	public void characters(char ch[], int start, int length) throws SAXException {
		if (value) {
			String s = new String(ch, start, length);
			tmpEntry.setSuccess(Integer.valueOf(s));
			value = false;
		} else if (number) {
			String s = new String(ch, start, length);
			tmpEntry.setNumber(Integer.valueOf(s));
			number = false;
		} else if (timestamp) {
			String s = new String(ch, start, length);
			tmpEntry.setTimestamp(Long.valueOf(s));
			timestamp = false;
		}
	}

	public void startDocument() throws SAXException {

	}

	public void endDocument() throws SAXException {

	}



}
