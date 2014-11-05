package com.zaheylu.kana.mp3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import com.zaheylu.kana.words.TWord;
import com.zaheylu.log.Log;


public class Mp3Play {

	public Mp3Play() {

	}

	/*public static void play(String path) {
		AudioPlayThread thread = new AudioPlayThread(new File(path));
		thread.start();
	}*/

	public static void play(TWord word) {
		AudioPlayThread thread = new AudioPlayThread(word);
		thread.start();
	}
}

class AudioPlayThread extends Thread {

	private TWord word;


	public AudioPlayThread(TWord word) {
		this.word = word;
	}

	public void run() {
		File f = new File(Log.getString("path.user") + "sounds\\Voc" + String.valueOf(word.getIndex()) + ".mp3");
		if (f.exists()) {
			Log.event("audioPlayThread: " + f.getPath());
			Player player;
			try {
				player = new Player(new FileInputStream(f));
				player.play();
				if (word.hasPresent()) {
					f = new File(Log.getString("path.user") + "sounds\\Voc" + String.valueOf(word.getIndex()) + "p.mp3");
					if (f.exists()) {
						Thread.sleep(400);
						player = new Player(new FileInputStream(f));
						player.play();
					}
				}
			} catch (FileNotFoundException | JavaLayerException | InterruptedException e) {
				e.printStackTrace();
			}


		}
	}

}
