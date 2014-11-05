package com.zaheylu.snippets;

import java.awt.Canvas;
import java.awt.Desktop;
import java.awt.Font;
import java.net.URI;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class CodeLibary {

	private static Random rand = new Random();
	private static Canvas can = new Canvas();

	public static void init() {
		randInt(10);
		for (int n = 0; n < randInt(10); n++) {
			randInt(10);
		}
	}

	public static int getStringWidth(Font font, String txt) {

		return can.getFontMetrics(font).stringWidth(txt);
	}

	public static void showmessage(String msg) {
		JOptionPane.showMessageDialog(null, msg, null, JOptionPane.PLAIN_MESSAGE);
	}

	public static void showmessage(Object msg) {
		JOptionPane.showMessageDialog(null, msg, null, JOptionPane.PLAIN_MESSAGE);
	}

	public static int randInt(int min, int max) {
		return rand.nextInt((max - min) + 1) + min;
	}

	public static int randInt(int max) {
		if (max >= 0) return rand.nextInt((max) + 1);
		else return -1;
	}

	public static int[] randomOrder(int number) {
		int[] result = new int[number];
		int[] helper = new int[number];
		for (int n = 0; n < number; n++) {
			helper[n] = n;
		}
		for (int n = 0; n < number; n++) {
			int random = randInt(number - n - 1);
			result[n] = helper[random];
			int exchange = helper[random];
			helper[random] = helper[number - n - 1];
			helper[number - n - 1] = exchange;
		}
		return result;
	}

	public static void strechFont(Object obj) {
		if (obj instanceof JLabel) {
			Font font = ((JLabel) obj).getFont();
			String text = ((JLabel) obj).getText();
			if (text == null || text.isEmpty()) return;
			int stringWidth = ((JLabel) obj).getFontMetrics(font).stringWidth(text);
			int componentWidth = ((JLabel) obj).getWidth() - (((JLabel) obj).getWidth() / 50);
			int componentHeight = ((JLabel) obj).getHeight();
			((JLabel) obj).setFont(new Font(font.getName(), font.getStyle(), calcTextRatio(stringWidth, componentWidth, componentHeight, font)));
		}
	}

	private static int calcTextRatio(int stringWidth, int componentWidth, int componentHeight, Font font) {
		double widthRatio = (double) componentWidth / (double) stringWidth;
		int newFontSize = (int) ((double) font.getSize() * widthRatio);
		return Math.min(newFontSize, componentHeight);
	}

	public static int boolToInt(boolean bool) {
		if (bool) return 1;
		else return 0;
	}

	public static void openHTTP(String url) {
		if (Desktop.isDesktopSupported()) {
			try {
				Desktop.getDesktop().browse(new URI(url));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else showmessage(new JTextField(url));
	}


}
