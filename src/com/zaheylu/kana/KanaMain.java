package com.zaheylu.kana;

import com.zaheylu.kana.gui.KanaWindow;
import com.zaheylu.kana.version.Version;

public class KanaMain {
	public static void main(String[] args) {
		Version.loadVersion();
		new KanaWindow(args);
	}
}