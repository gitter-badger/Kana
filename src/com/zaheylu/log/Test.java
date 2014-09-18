package com.zaheylu.log;

public class Test {

	public static void main(String[] args) {
		new Test();
	}

	public Test() {
		Log.enableLogOutput();
		Log.setLog("1", "a");
		Log.setLog("1", "a");
		Log.setLog("2", new Integer(1));
	}

}
