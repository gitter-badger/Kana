package com.zaheylu.log;


public class LogEntry {
	private String name;
	private Object logObj;
	private Class<?> type;

	public LogEntry(String name, Object logObj) {
		this.name = name;
		this.logObj = logObj;
		type = this.logObj.getClass();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getLogObj() {
		return logObj;
	}

	public void setLogObj(Object logObj) {
		this.logObj = logObj;
		type = this.logObj.getClass();
	}

	public Class<?> getType() {
		return type;
	}

	public void setType(Class<?> type) {
		this.type = type;
	}




}
