package com.zaheylu.kana;

import com.zaheylu.log.Log;

public class KanaMain {
	public static void main(String[] args) {
		if (args != null) {
			for (int n = 0; n < args.length; n++) {
				if (args[0].equalsIgnoreCase("debug")) Log.enableLogOutput();
			}
		}
		new KanaWindow();
	}
}