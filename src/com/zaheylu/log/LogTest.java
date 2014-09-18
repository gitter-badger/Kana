package com.zaheylu.log;

public class LogTest {

	public static void main(String[] args) {
		new LogTest();
	}

	public LogTest() {
		Log.enableLogOutput();
		Log.setLog("1", "a");
		Log.setLog("1", "a");
		Log.setLog("2", new Integer(1));
	}

}
