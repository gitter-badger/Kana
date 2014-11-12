package com.zaheylu.kana.words;

import static com.zaheylu.kana.KanaLibV2.*;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.zaheylu.kana.mp3.Mp3Play;
import com.zaheylu.kana.users.Profile;
import com.zaheylu.log.Log;
import com.zaheylu.snippets.CodeLibary;

public class TVocabulary {

	public HashMap<Integer, TWord> words;

	public TVocabulary() {
		words = new HashMap<Integer, TWord>();
	}

	public void add(TWord word) {
		word.setIndex(words.size());
		words.put(words.size(), word);
	}

	public void loadAllSounds() {
		AudioLoadThread run = new AudioLoadThread(words.values(), false, true);
		run.start();
	}

	public void loadSound(TWord word, boolean instantPlay, boolean overwrite) {
		AudioLoadThread run = new AudioLoadThread(word, instantPlay, overwrite);
		run.start();
	}

	public ArrayList<TWord> getFiltered(ArrayList<Integer> filter) {
		ArrayList<TWord> result = new ArrayList<TWord>();
		for (TWord word : words.values()) {
			int iGroup = word.getGroup();
			for (Integer filt : filter) {
				if (iGroup == filt) result.add(word);
			}
		}
		return result;
	}

	public ArrayList<TWord> getFiltered(int filter) {
		ArrayList<TWord> result = new ArrayList<TWord>();
		for (TWord word : words.values()) {
			if (word.getGroup() == filter) result.add(word);
		}
		return result;
	}

	public TWord get(int i) {
		return words.get(i);
	}

	public int size() {
		return words.size();
	}

	public int size(int group) {
		int result = 0;
		for (TWord word : words.values()) {
			if (word.getGroup() == group) result++;
		}
		return result;
	}

	public ArrayList<String> getPossibleKana(String arg) {
		Log.event("getPossibleKana");
		ArrayList<String> result = new ArrayList<String>();

		for (TWord word : words.values())
			for (String engl : word.getEngl()) {
				if (equalsIgnoreCase(engl, arg)) {
					result.add(word.getKana());
					if (word.hasRomaji()) result.add(word.getRomaji());
					if (word.hasPresent()) result.add(word.getPresent());
				}
			}
		return result;
	}

	public ArrayList<String> getPossibleEngl(TWord word) {
		Log.event("getPossibleEngl");
		ArrayList<String> result = new ArrayList<String>();
		for (TWord nWord : words.values()) {
			if (equalsIgnoreCase(nWord.getKana(), word.getKana()) || equalsIgnoreCase(nWord.getKana(), word.getKanji())) {
				for (String engl : nWord.getEngl()) {
					result.add(engl);
				}
			}

		}

		return result;
	}

	public HashMap<Integer, TWord> getWords() {
		return words;
	}

	private class AudioLoadThread extends Thread {

		private ArrayList<TWord> words;
		private boolean instantPlay;
		private boolean overwrite;

		public AudioLoadThread(TWord word, boolean instantPlay, boolean overwrite) {
			words = new ArrayList<TWord>();
			words.add(word);
			this.instantPlay = instantPlay;
			this.overwrite = overwrite;
		}



		public AudioLoadThread(Collection<TWord> words, boolean instantPlay, boolean overwrite) {
			words = new ArrayList<TWord>();
			this.words.addAll(words);
			this.instantPlay = instantPlay;
			this.overwrite = overwrite;
		}

		public void run() {
			boolean err = false;
			ArrayList<Integer> errs = new ArrayList<Integer>();
			for (TWord word : words) {
				try {
					File dest = new File(Log.getString("path.user") + "sounds" + FileSystems.getDefault().getSeparator() + "Voc"
							+ String.valueOf(word.getIndex()) + ".mp3");
					if ((!dest.exists()) || (overwrite)) {
						Log.event("AudioLoadThread: " + dest.getPath());
						String txt = word.getKana();
						txt = java.net.URLEncoder.encode(txt, "UTF-8");
						URL url = new URL("http://translate.google.com/translate_tts?ie=UTF-8&tl=ja&q=" + txt);
						HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
						urlConn.addRequestProperty("User-Agent", "Mozilla/4.76");
						InputStream audioSrc = urlConn.getInputStream();
						DataInputStream read = new DataInputStream(audioSrc);
						OutputStream outstream = new FileOutputStream(dest);
						byte[] buffer = new byte[1024];
						int len;
						while ((len = read.read(buffer)) > 0) {
							outstream.write(buffer, 0, len);
						}
						outstream.close();
					}
					if (word.hasPresent()) {
						dest = new File(Log.getString("Path.User") + "sounds" + FileSystems.getDefault().getSeparator() + "Voc"
								+ String.valueOf(word.getIndex()) + "p.mp3");
						if ((!dest.exists()) || (overwrite)) {
							String txt = word.getPresent();
							txt = java.net.URLEncoder.encode(txt, "UTF-8");
							URL url = new URL("http://translate.google.com/translate_tts?ie=UTF-8&tl=ja&q=" + txt);
							HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
							urlConn.addRequestProperty("User-Agent", "Mozilla/4.76");
							InputStream audioSrc = urlConn.getInputStream();
							DataInputStream read = new DataInputStream(audioSrc);
							OutputStream outstream = new FileOutputStream(dest);
							byte[] buffer = new byte[1024];
							int len;
							while ((len = read.read(buffer)) > 0) {
								outstream.write(buffer, 0, len);
							}
							outstream.close();
						}
					}

					if (instantPlay && words.size() == 1) {
						Mp3Play.play(words.get(0));
					}

				} catch (IOException e) {
					err = true;
					Log.event("Err.AudioLoadThread.Run");
					e.printStackTrace();
				}
			}
			if (words.size() > 1 && (!instantPlay)) if (err) CodeLibary.showmessage("There was an error loading the sound files:" + errs);
			else CodeLibary.showmessage("Loading complete.");
		}
	}

	public String toString() {
		return words.size() + " words";
	}

	public void linkProfile(Profile p) {
		for (int n = 0; n < p.size(); n++) {
			words.get(n).setSuccess(p.get(n));
		}
	}

	public void unlinkProfile() {
		for (TWord word : words.values()) {
			word.setSuccess(null);
		}
	}

	public void saveProfile() throws UnsupportedEncodingException, FileNotFoundException, XMLStreamException {
		// TODO: This should be called only if the User wants to.
		if (size() > 0) {
			Log.event("vocabulary.profile.save");
			PrintWriter writerXml = new PrintWriter(new OutputStreamWriter(new FileOutputStream(Log.getString("path.user") + "profiles"
					+ FileSystems.getDefault().getSeparator() + "default.xml"), "utf-8"));
			XMLOutputFactory xof = XMLOutputFactory.newInstance();
			XMLStreamWriter xmlsw = xof.createXMLStreamWriter(writerXml);

			// write declaration
			xmlsw.writeStartDocument("UTF-8", "1.0");
			xmlsw.writeCharacters("\r\n");
			xmlsw.writeStartElement("profile");
			xmlsw.writeStartElement("success");
			xmlsw.writeCharacters("\r\n");
			for (TWord word: words.values()) {
				xmlsw.writeStartElement("entry");

				xmlsw.writeStartElement("value");
				xmlsw.writeCharacters(String.valueOf(word.getSuccess().getSuccess()));
				xmlsw.writeEndElement();

				xmlsw.writeStartElement("number");
				xmlsw.writeCharacters(String.valueOf(word.getSuccess().getNumber()));
				xmlsw.writeEndElement();

				xmlsw.writeEndElement();
				xmlsw.writeCharacters("\r\n");
			}
			xmlsw.writeEndElement(); // end success
			xmlsw.writeEndElement(); // end profile
			xmlsw.writeEndDocument();
			xmlsw.flush();
			xmlsw.close();

		}
	}
	
	public void update(int n, boolean result) {
		if (result) words.get(n).update(result);
	}
}