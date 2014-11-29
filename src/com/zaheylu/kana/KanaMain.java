package com.zaheylu.kana;

import java.awt.EventQueue;

import com.zaheylu.kana.gui.KanaWindow;
import com.zaheylu.log.Log;

public class KanaMain {
	public static void main(String[] arg) {
		Log.put("path.user", System.getProperty("user.home") + "\\Documents\\kana\\");
		final String[] args = arg;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new KanaWindow(args);
			}
		});
	}
}