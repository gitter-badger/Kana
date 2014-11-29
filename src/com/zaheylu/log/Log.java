package com.zaheylu.log;

import java.util.HashMap;

public class Log {
	private static HashMap<String, LogEntry> log = new HashMap<String, LogEntry>();
	private static HashMap<Integer, String> events = new HashMap<Integer, String>();
	private static int eventIndex = 0;
	private static boolean output = false;

	private Log() {

	}

	public static void enableLogOutput() {
		output = true;
		write("Log output enabled");
	}

	public static void disableLogOutput() {
		write("Log output disabled");
		output = false;
	}

	public static boolean containsKey(String name) {
		return log.containsKey(name.toUpperCase());
	}

	public static LogEntry get(String name) {
		return log.get(name.toUpperCase());
	}

	public static String getString(String name) {
		LogEntry obj = get(name);
		if (obj == null) return null;
		return obj.toString();
	}

	public static void put(String name, LogEntry obj) {
		if (containsKey(name)) write("put    ", obj);
		else write("add    ", obj);
		log.put(name.toUpperCase(), obj);
	}

	public static void put(String name, Object obj) {
		LogEntry entry = new LogEntry(name, obj);
		put(name, entry);
	}

	private static void write(String msg, LogEntry entry) {
		if (output) write(msg + ": " + entry.getKey() + " : " + entry.toString());
	}

	private static void write(String msg) {
		if (output) System.out.println(msg);
	}

	public static void event(String eventMsg) {
		events.put(eventIndex, eventMsg);
		write(String.format("ev(%3d): %s", eventIndex, eventMsg));
		eventIndex++;
	}

	public static boolean getBool(String name) {
		LogEntry entry = get(name);
		if (entry == null) return false;
		if (entry.getValue() instanceof Boolean) return (Boolean) entry.getValue();
		return false;
	}
}
