package com.zaheylu.kana.users;

import java.util.HashMap;

public class Profile {

	private HashMap<Integer, SuccessEntry> success;

	public Profile() {
		success = new HashMap<Integer, SuccessEntry>();
	}

	public Profile(HashMap<Integer, SuccessEntry> ar) {
		this.success = ar;
	}

	public void add(SuccessEntry se) {
		success.put(success.size(), se);
	}

	public SuccessEntry get(int n) {
		if (n < size()) return success.get(n);
		else return null;
	}

	public int size() {
		return success.size();
	}

	public HashMap<Integer, SuccessEntry> getSuccess() {
		return success;
	}
}
