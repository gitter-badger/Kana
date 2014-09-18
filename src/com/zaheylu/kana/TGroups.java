package com.zaheylu.kana;

import java.util.ArrayList;

public class TGroups {

	private ArrayList<String> groups;

	public TGroups() {
		setGroups(new ArrayList<String>());
	}

	public TGroups(ArrayList<String> groups) {
		this.setGroups(groups);
	}

	public ArrayList<String> getGroups() {
		return groups;
	}

	public void setGroups(ArrayList<String> groups) {
		this.groups = groups;
	}

	public void add(String group) {
		groups.add(group);
	}

	public String get(int i) {
		return groups.get(i);
	}

	public String[] toStringArray() {
		String[] result = new String[groups.size()];
		for (int n = 0; n < groups.size(); n++) {
			result[n] = groups.get(n);
		}
		return result;
	}

}
