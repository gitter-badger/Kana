package com.zaheylu.kana.words;

import static com.zaheylu.kana.KanaLibV2.*;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;

import com.zaheylu.kana.exceptions.IndexException;
import com.zaheylu.kana.mp3.Mp3Play;
import com.zaheylu.kana.users.Profile;
import com.zaheylu.kana.users.SuccessEntry;
import com.zaheylu.log.Log;
import com.zaheylu.snippets.CodeLibary;

public class TVocabulary {

	public LinkedHashMap<Integer, TWord> words;
	private Profile profile;

	public void unassignProfile() {
		Log.event("Vocabulary.unassignProfile");
		for (TWord word : words.values()) {
			word.setNullSuccess();
		}
		profile = null;
	}

	public void assignProfile(Profile p) {
		Log.event("Vocabulary.assignProfile");
		try {
			if (p.size() > words.size()) {
				CodeLibary.showmessage("Cannot load Profile because there was an error loading the vocabulary: " + CodeLibary.newLine()
						+ p.toString() + "; " + toString() + CodeLibary.newLine() + "The program will not work properly.");
				throw new IndexException(p, this);
			}
			profile = p;
			for (SuccessEntry se : profile.success().values()) {
				words.get(se.getIndex()).setSuccess(se);
			}

			for (TWord word : words.values()) {
				if (word.getSuccess() == null) {
					SuccessEntry se = new SuccessEntry(word.getIndex());

					profile.add(se, word.getIndex());
					word.setSuccess(se);
				}
			}
		} catch (IndexException e) {
			e.printStackTrace();
		}
	}

	@Deprecated
	public void appendProfile(Profile p) {
		for (SuccessEntry se : p.getSuccess().values()) {
			if (words.get(se.getIndex()) != null) {
				TWord word = words.get(se.getIndex());
				word.getSuccess().update(se.getNumber(), se.getSuccess());
			} else words.get(se.getIndex()).setSuccess(se);
		}
	}

	public TVocabulary() {
		words = new LinkedHashMap<Integer, TWord>();
	}

	public void add(TWord word) {
		try {
			if (words.get(words.size()) != null) throw new IndexException(words.size(), word);
		} catch (IndexException e) {
			e.printStackTrace();
		}
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

	public LinkedHashMap<Integer, TWord> getWords() {
		return words;
	}

	private class AudioLoadThread extends Thread {

		private Collection<TWord> words;
		private boolean instantPlay;
		private boolean overwrite;

		public AudioLoadThread(TWord word, boolean instantPlay, boolean overwrite) {
			words = new ArrayList<TWord>();
			words.add(word);
			this.instantPlay = instantPlay;
			this.overwrite = overwrite;
		}



		public AudioLoadThread(Collection<TWord> words, boolean instantPlay, boolean overwrite) {
			this.words = words;
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
						Mp3Play.play(words.iterator().next());
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
		return "[Voc:" + words.size() + " words]";
	}

}