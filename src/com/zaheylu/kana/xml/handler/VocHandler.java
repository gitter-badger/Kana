package com.zaheylu.kana.xml.handler;

import static com.zaheylu.snippets.CodeLibary.*;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.zaheylu.kana.version.Version;
import com.zaheylu.kana.words.TVocabulary;
import com.zaheylu.kana.words.TWord;
import com.zaheylu.log.Log;

public class VocHandler extends DefaultReturnHandler {
	public TVocabulary list;
	private boolean entry = false;
	private boolean kana = false;
	private boolean engl = false;
	private boolean romaji = false;
	private boolean comment = false;
	private boolean kanji = false;
	private boolean group = false;
	private boolean present = false;
	private boolean valid = false;
	private boolean validIdentifier = false;
	private TWord tmpWord;
	private int nErr;
	private final String c9 = String.valueOf((char) 9);
	private final String c10 = String.valueOf((char) 10);

	private VocHandler() {

	}

	private void init() {
		list = new TVocabulary();
		nErr = 0;
	}

	public static VocHandler newVocHandler() {
		VocHandler result = new VocHandler();
		result.init();
		return result;
	}

	private int getErrorCount() {
		return nErr;
	}

	private boolean isValid(String s) {
		if (s.contains(c9) || s.contains(c10)) return false;
		return true;
	}

	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName.equalsIgnoreCase("DICTIONARY")) {
			if (attributes.getLength() == 0) {
				showmessage("Your Vocabulary File is outdated. It may not be working. (It's more than likely not to work)");
			} else {
				if (!(attributes.getValue(0).equalsIgnoreCase(String.valueOf(Version.getVocVersion())))) {
					showmessage("This version only supports vocabulary-version " + Version.getVocVersion() + "." + "\nBut all you got is version "
							+ attributes.getValue(0) + "." + "\nSorry for that, but you gotta do something about that.");
				}
			}
		} else if (qName.equalsIgnoreCase("ENTRY")) {
			tmpWord = new TWord();
			valid = true;
			/*
			 * if (attributes.getValue("type") != null) {
			 * if (attributes.getValue("type").equalsIgnoreCase("VERB")) tmpWord.setVerb(true);
			 * }
			 */
			entry = true;
		} else

		if (qName.equalsIgnoreCase("ENGL")) {
			engl = true;
			validIdentifier = true;
		} else

		if (qName.equalsIgnoreCase("KANA")) {
			kana = true;
			validIdentifier = true;
		} else

		if (qName.equalsIgnoreCase("ROMAJI")) {
			romaji = true;
			validIdentifier = true;
		} else

		if (qName.equalsIgnoreCase("COMMENT")) {
			comment = true;
			validIdentifier = true;
		} else

		if (qName.equalsIgnoreCase("KANJI")) {
			kanji = true;
			validIdentifier = true;
		} else

		if (qName.equalsIgnoreCase("GROUP")) {
			group = true;
			validIdentifier = true;
		} else

		if (qName.equalsIgnoreCase("PRESENT")) {
			present = true;
			validIdentifier = true;
		}
	}

	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equalsIgnoreCase("ENTRY")) {
			if (valid && tmpWord.isValid()) {
				list.add(tmpWord);
			} else {
				nErr++;
				Log.event("vocabulary.loading.err: " + tmpWord.getEngl());
			}
		}
	}

	public void characters(char ch[], int start, int length) throws SAXException {
		if (entry) {
			entry = false;
		} else {
			String s = new String(ch, start, length);
			if (validIdentifier) if (!(isValid(s))) {
				valid = false;
			}
			if (valid) {
				if (kana) {
					tmpWord.setKana(s);
					kana = false;
					validIdentifier = false;
				} else

				if (engl) {
					tmpWord.addEngl(s);
					engl = false;
					validIdentifier = false;
				} else

				if (romaji) {
					tmpWord.setRomaji(s);
					romaji = false;
					validIdentifier = false;
				} else

				if (comment) {
					tmpWord.setComment(s);
					comment = false;
					validIdentifier = false;
				} else

				if (kanji) {
					tmpWord.setKanji(s);
					kanji = false;
					validIdentifier = false;
				}

				if (group) {
					tmpWord.setGroup(s);
					group = false;
					validIdentifier = false;
				} else

				if (present) {
					tmpWord.setPresent(s);
					present = false;
					validIdentifier = false;
				}
			}
		}
	}

	public void startDocument() throws SAXException {
	}

	public void endDocument() throws SAXException {
		if (getErrorCount() > 0) Log.put("vocabulary.loading.err.count", getErrorCount());
	}

	public TVocabulary getReturn() {
		return list;
	}

	
}
