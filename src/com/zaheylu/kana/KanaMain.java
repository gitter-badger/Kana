package com.zaheylu.kana;

import java.awt.EventQueue;

import com.zaheylu.kana.gui.KanaWindow;
import com.zaheylu.kana.version.Version;
import com.zaheylu.snippets.CodeLibary;

public class KanaMain {
	public static void main(String[] arg) {
		final String[] args = arg;
		EventQueue.invokeLater(new Runnable() {
            public void run() {
            	CodeLibary.init();
            	Version.loadVersion();
        		new KanaWindow(args);
            }
        });
	}
}