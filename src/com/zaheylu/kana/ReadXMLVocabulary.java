package com.zaheylu.kana;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import static com.zaheylu.CodeLibary.*;


public class ReadXMLVocabulary {

	private SAXParserFactory factory;
	private SAXParser saxParser;


	private final String emptyEntry = String.valueOf(new char[] {
			(char) 10, (char) 9, (char) 9 });

	public ReadXMLVocabulary() throws ParserConfigurationException, SAXException {
		factory = SAXParserFactory.newInstance();
		saxParser = factory.newSAXParser();

	}

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
		private TWord tmpWord;

		public VocHandler() {
			list = new TVocabulary();
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
				/*
				 * if (attributes.getValue("type") != null) {
				 * if (attributes.getValue("type").equalsIgnoreCase("VERB")) tmpWord.setVerb(true);
				 * }
				 */
				entry = true;
			} else

			if (qName.equalsIgnoreCase("ENGL")) {
				engl = true;
			} else

			if (qName.equalsIgnoreCase("KANA")) {
				kana = true;
			} else

			if (qName.equalsIgnoreCase("ROMAJI")) {
				romaji = true;
			} else

			if (qName.equalsIgnoreCase("COMMENT")) {
				comment = true;
			} else

			if (qName.equalsIgnoreCase("KANJI")) {
				kanji = true;
			} else

			if (qName.equalsIgnoreCase("GROUP")) {
				group = true;
			} else

			if (qName.equalsIgnoreCase("PRESENT")) {
				present = true;
			}
		}

		public void endElement(String uri, String localName, String qName) throws SAXException {
			if (qName.equalsIgnoreCase("ENTRY")) {
				if (tmpWord.getKana().compareTo(emptyEntry) != 0) {
					list.add(tmpWord);
					System.out.println("Loaded Word: " + tmpWord.getEngl());
				} else System.out.print("[]");
			}
		}

		public void characters(char ch[], int start, int length) throws SAXException {
			if (entry) {
				entry = false;
			} else

			if (kana) {
				tmpWord.setKana(new String(ch, start, length));
				kana = false;
			} else

			if (engl) {
				tmpWord.addEngl(new String(ch, start, length));
				engl = false;
			} else

			if (romaji) {
				tmpWord.setRomaji(new String(ch, start, length));
				romaji = false;
			} else

			if (comment) {
				tmpWord.setComment(new String(ch, start, length));
				comment = false;
			} else

			if (kanji) {
				tmpWord.setKanji(new String(ch, start, length));
				kanji = false;
			}

			if (group) {
				tmpWord.setGroup(new String(ch, start, length));
				group = false;
			} else

			if (present) {
				tmpWord.setPresent(new String(ch, start, length));
				present = false;
			}
		}
		
		public void startDocument() throws SAXException {
	        System.out.println("start document   : ");
	    }

	    public void endDocument() throws SAXException {
	        System.out.println("end document     : ");
	    }
	}// end class voc handler



	public TVocabulary loadVocabulary(String path) throws SAXException, IOException, ParserConfigurationException {
		File file = new File(path);
		InputStream inputStream = new FileInputStream(file);
		Reader reader = new InputStreamReader(inputStream, "UTF-8");

		InputSource is = new InputSource(reader);
		is.setEncoding("UTF-8");
		VocHandler handler = new VocHandler();
		try {
			saxParser.parse(is, handler);
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*for (int n = list.size() - 1; n > -1; n--) {
			if (list.get(n).getJp().length() == 3) {
				if (list.get(n).getJp().equalsIgnoreCase(String.valueOf(new char[] {
						(char) 10, (char) 9, (char) 9 }))) {
					list.remove(n);

				}
			}

		}*/
		return handler.list;
	}
}
