package com.zaheylu.kana;

import com.zaheylu.kana.gui.KanaWindow;
import com.zaheylu.kana.version.Version;
import com.zaheylu.log.Log;

public class KanaMain {
	public static void main(String[] args) {
		Version.loadVersion();
		if (args != null) {
			for (int n = 0; n < args.length; n++) {
				if (args[0].equalsIgnoreCase("debug")) Log.enableLogOutput();
			}
		}
		new KanaWindow();
	}
}