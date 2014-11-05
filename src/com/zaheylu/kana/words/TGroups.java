package com.zaheylu.kana.words;

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

	public String[] toStringArray(TVocabulary vocabulary) {
		String[] result = new String[groups.size()];
		for (int n = 0; n < groups.size(); n++) {
			result[n] = groups.get(n) + " (" + vocabulary.size(n) + " words)";
		}
		return result;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		boolean first = true;
		for (String str : groups)
			if (first) {
				sb.append(str);
				first = false;
			} else sb.append(", " + str);
		sb.append(']');
		return sb.toString();
	}
}
