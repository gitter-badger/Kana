package com.zaheylu.kana.xml;

import static com.zaheylu.snippets.CodeLibary.*;

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

import com.zaheylu.kana.version.Version;
import com.zaheylu.kana.words.TVocabulary;
import com.zaheylu.kana.words.TWord;
import com.zaheylu.log.Log;

public class ReadXMLVocabulary {

	private SAXParserFactory factory;
	private SAXParser saxParser;

	// private final String emptyEntry = String.valueOf(new char[] {
	// (char) 10, (char) 9, (char) 9 });
	private final String c9 = String.valueOf((char) 9);
	private final String c10 = String.valueOf((char) 10);


	private class VocHandler extends DefaultHandler {
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

		public VocHandler() {

			init();
		}

		public void init() {
			list = new TVocabulary();
			nErr = 0;
		}

		public int getErrorCount() {
			return nErr;
		}

		public boolean isValid(String s) {
			if (s.contains(c9) || s.contains(c10)) return false;
			return true;
		}

		public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
			if (qName.equalsIgnoreCase("DICTIONARY")) {
				if (attributes.getLength() == 0) {
					showmessage("Your Vocabulary File is outdated. It may not be working. (It's more than likely not to work)");
				} else {
					if (!(attributes.getValue(0).equalsIgnoreCase(String.valueOf(Version.getVocVersion())))) {
						showmessage("This version only supports vocabulary-version " + Version.getVocVersion() + "." + "\nBut all you got is version " + attributes.getValue(0) + "."
								+ "\nSorry for that, but you gotta do something about that.");
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
					//Log.event("Vocabulary.Loading.Err: " + tmpWord.getEngl());
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
		}
	}// end class voc handler


	private TVocabulary loadVocabulary(InputStream inputStream) throws ParserConfigurationException, SAXException, UnsupportedEncodingException {
		factory = SAXParserFactory.newInstance();
		saxParser = factory.newSAXParser();
		Reader reader = new InputStreamReader(inputStream, "UTF-8");
		InputSource is = new InputSource(reader);
		is.setEncoding("UTF-8");
		VocHandler handler = new VocHandler();
		handler.init();
		try {
			saxParser.parse(is, handler);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (handler.getErrorCount() > 0) Log.setLog("Vocabulary.Loading.Err.Count", handler.getErrorCount());
		return handler.list;
	}


	public TVocabulary loadVocabulary(URL path) throws IOException, ParserConfigurationException, SAXException {
		return loadVocabulary(path.openStream());
	}

	public TVocabulary loadVocabulary(String path) throws UnsupportedEncodingException, FileNotFoundException, ParserConfigurationException, SAXException {
		return loadVocabulary(new FileInputStream(path));
	}
}
