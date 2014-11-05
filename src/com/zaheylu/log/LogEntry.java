package com.zaheylu.log;

import java.util.Map.Entry;

public class LogEntry implements Entry<String, Object> {
	private String key;
	private Object value;
	private Class<?> type;

	public LogEntry(String key, Object value) {
		this.key = key;
		this.value = value;
		type = value.getClass();
	}

	public Class<?> getType() {
		return type;
	}

	public String toString() {
		return type.cast(value).toString();
	}

	@Override
	public String getKey() {
		return key;
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public Object setValue(Object arg0) {
		Object old = getValue();
		value = arg0;
		return old;
	}
}
