package com.zaheylu.snippets;

import java.net.URL;

import com.zaheylu.log.LogEntry;

public class ToString {



	public static String logEntryToString(LogEntry entry) {
		return objectToString(entry.getLogObj());
	}

	public static String objectToString(Object obj) {
		String result = null;
		if (obj instanceof String) result = ((String) obj);
		else if (obj instanceof URL) result = ((URL) obj).getPath();
		else if (obj instanceof Integer) result = String.valueOf((Integer) obj);
		return result;
	}
}
