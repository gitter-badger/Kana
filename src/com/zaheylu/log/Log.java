package com.zaheylu.log;

import java.util.ArrayList;

import com.zaheylu.ToString;

public class Log {
	private static ArrayList<LogEntry> log = new ArrayList<LogEntry>();
	private static boolean output = false;

	public static void enableLogOutput() {
		output = true;
		write("Log output enabled");
	}

	public static void disableLogOutput() {
		write("Log output disabled");
		output = false;
	}

	public static boolean hasEntry(String name) {
		for (int n = 0; n < log.size(); n++)
			if (log.get(n).getName().equalsIgnoreCase(name)) return true;
		return false;
	}

	public static String getLog(String name) {
		LogEntry tmpEntry;
		for (int n = 0; n < log.size(); n++) {
			tmpEntry = log.get(n);
			if (tmpEntry.getName().equalsIgnoreCase(name)) return logString(tmpEntry);
		}
		return null;
	}

	public static boolean setLog(String name, Object logObj) {
		LogEntry tmpEntry = getEntry(name);
		if (tmpEntry == null) {
			return addLog(name, logObj);

		} else {
			tmpEntry.setLogObj(logObj);
			write("changed", tmpEntry);
			return true;
		}
	}

	public static LogEntry getEntry(String name) {
		LogEntry tmpEntry;
		for (int n = 0; n < log.size(); n++) {
			tmpEntry = log.get(n);
			if (tmpEntry.getName().equalsIgnoreCase(name)) return tmpEntry;
		}
		return null;
	}

	private static boolean addLog(String name, Object logObj) {
		LogEntry tmpEntry = new LogEntry(name, logObj);
		log.add(tmpEntry);
		write("added  ", tmpEntry);
		return true;
	}

	private static String logString(LogEntry entry) {
		String result = ToString.logEntryToString(entry);
		if (result == null) result = "(" + entry.getType().getSimpleName() + ")";
		return result;
	}

	private static void write(String msg, LogEntry entry) {
		if (output) write(msg + ": " + entry.getName() + ": " + logString(entry));
	}

	@SuppressWarnings("unused")
	private static void write(LogEntry entry) {
		if (output) write(entry.getName() + ": " + logString(entry));
	}

	private static void write(String msg) {
		if (output) System.out.println(msg);
	}

	public static void event(String eventMsg) {
		// TODO Add EventLogging
		write("event  : " + eventMsg);		
	}
}
