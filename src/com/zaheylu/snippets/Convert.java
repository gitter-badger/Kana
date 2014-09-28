package com.zaheylu.snippets;

import java.net.URL;

public class Convert {



	public static String logEntryToString(Entry entry) {
		if (entry == null) return null;
		return objectToString(entry.getLogObj());
	}

	public static String objectToString(Object obj) {
		if (obj == null) return null;
		String result = null;
		if (obj instanceof String) result = ((String) obj);
		else if (obj instanceof URL) result = ((URL) obj).getPath();
		else if (obj instanceof Integer) result = String.valueOf((Integer) obj);
		else if (obj instanceof Boolean) result = String.valueOf((Boolean) obj);
		return result;
	}

}
