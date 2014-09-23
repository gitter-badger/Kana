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

	public static void play(String path) {
		AudioPlayThread thread = new AudioPlayThread(new File(path));
		thread.start();
	}

	public static void play(TWord word) {
		AudioPlayThread thread = new AudioPlayThread(new File(Log.getLog("Path.User") + "Voc" + String.valueOf(word.getIndex()) + ".mp3"));
		thread.start();
	}
}

class AudioPlayThread extends Thread {

	private File file;

	public AudioPlayThread(File file) {
		this.file = file;
	}

	public void run() {
		if (file.exists()) {
			Log.event("AudioPlayThread: " + file.getPath());
			/*
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();*/
			/*Media hit = new Media(file.toURI().toString());
			MediaPlayer mediaPlayer = new MediaPlayer(hit);
			mediaPlayer.play();*/
			Player player;
			try {
				player = new Player(new FileInputStream(file));
				player.play();
			} catch (FileNotFoundException | JavaLayerException e) {
				e.printStackTrace();
			}
			
            
		}
	}

}
