package com.zaheylu.kana.words;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import com.zaheylu.kana.KanaLib;
import com.zaheylu.kana.mp3.Mp3Play;
import com.zaheylu.log.Log;
import com.zaheylu.snippets.CodeLibary;

public class TVocabulary {

	public ArrayList<TWord> words;
	private int index;

	public TVocabulary() {
		words = new ArrayList<TWord>();
		index = 0;
	}

	public void add(TWord word) {
		word.setIndex(index);
		words.add(word);
		index++;
	}

	public void loadAllSounds() {
		AudioLoadThread run = new AudioLoadThread(words, false, true);
		run.start();
	}

	public void loadSound(TWord word, boolean instantPlay, boolean overwrite) {
		AudioLoadThread run = new AudioLoadThread(word, instantPlay, overwrite);
		run.start();
	}

	public ArrayList<TWord> getFiltered(ArrayList<Integer> filter) {
		ArrayList<TWord> result = new ArrayList<TWord>();
		for (int i = 0; i < words.size(); i++) {
			int iGroup = words.get(i).getGroup();
			for (int n = 0; n < filter.size(); n++) {
				if (iGroup == filter.get(n)) result.add(words.get(i));
			}
		}
		return result;
	}

	public ArrayList<TWord> getFiltered(int filter) {
		ArrayList<TWord> result = new ArrayList<TWord>();
		for (int i = 0; i < words.size(); i++) {
			int iGroup = words.get(i).getGroup();
			if (iGroup == filter) result.add(words.get(i));
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
		for (int i = 0; i < words.size(); i++) {
			int iGroup = words.get(i).getGroup();
			if (iGroup == group) result++;
		}
		return result;
	}

	public ArrayList<String> getPossibleAnswers(String arg) {
		Log.event("getPossibleAnswers");
		ArrayList<String> result = new ArrayList<String>();
		for (int n = 0; n < this.size(); n++)
			for (int m = 0; m < this.get(n).getEngl().size(); m++) {
				if (KanaLib.equalsIgnoreCase(this.get(n).getEngl().get(m), arg)) {
					result.add(this.get(n).getKana());
					if (this.get(n).hasRomaji()) result.add(this.get(n).getRomaji());
					if (this.get(n).hasPresent()) result.add(this.get(n).getPresent());
				}
			}
		return result;
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



		public AudioLoadThread(ArrayList<TWord> words, boolean instantPlay, boolean overwrite) {
			this.words = words;
			this.instantPlay = instantPlay;
			this.overwrite = overwrite;
		}

		public void run() {
			boolean err = false;
			ArrayList<Integer> errs = new ArrayList<Integer>();
			for (int n = 0; n < words.size(); n++) {
				try {
					File dest = new File(Log.getLog("Path.User") + "Voc" + String.valueOf(words.get(n).getIndex()) + ".mp3");
					if ((!dest.exists()) || (overwrite)) {
						Log.event("AudioLoadThread: " + dest.getPath());
						String txt = words.get(n).getKana();
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
					if (instantPlay && words.size() == 1) {
						Mp3Play.play(words.get(0));
					}

				} catch (IOException e) {
					err = true;
					errs.add(n);
					Log.event("Err.AudioLoadThread.run");
					e.printStackTrace();
				}
			}
			if (words.size() > 1 && (!instantPlay)) if (err) CodeLibary.showmessage("There was an error loading the sound files:" + errs);
			else CodeLibary.showmessage("Loading complete.");
		}

	}
}