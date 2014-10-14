package com.zaheylu.kana.users;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.zaheylu.log.Log;

public class Profile {

	private ArrayList<SuccessEntry> success;

	public Profile() {
		success = new ArrayList<SuccessEntry>();
	}

	public Profile(ArrayList<SuccessEntry> ar) {
		this.success = ar;
	}

	public ArrayList<SuccessEntry> success() {
		return success;
	}

	public void add() {
		int i = size();
		success.add(new SuccessEntry(i));
	}

	public void add(SuccessEntry se) {
		se.setIndex(success.size());
		success.add(se);
	}

	public void update(int index, boolean result) {
		int to = index - size() + 1;
		for (int n = 0; n < to; n++) {
			add();
		}
		get(index).update(result);
	}

	public SuccessEntry get(int n) {
		if (n < size()) return success.get(n);
		else return null;
	}

	public int size() {
		return success.size();
	}

	public void save() throws UnsupportedEncodingException, FileNotFoundException, XMLStreamException {
		if (null != success && size() > 0) {
			Log.event("Profile.Save");
			PrintWriter writerXml = new PrintWriter(new OutputStreamWriter(new FileOutputStream(Log.getString("Path.user") + "profiles\\default.xml"), "utf-8"));
			XMLOutputFactory xof = XMLOutputFactory.newInstance();
			XMLStreamWriter xmlsw = xof.createXMLStreamWriter(writerXml);

			// write declaration
			xmlsw.writeStartDocument("UTF-8", "1.0");
			xmlsw.writeCharacters("\r\n");
			xmlsw.writeStartElement("profile");
			xmlsw.writeStartElement("success");
			xmlsw.writeCharacters("\r\n");
			for (SuccessEntry item : success) {
				xmlsw.writeStartElement("entry");

				xmlsw.writeStartElement("value");
				xmlsw.writeCharacters(String.valueOf(item.getSuccess()));
				xmlsw.writeEndElement();

				xmlsw.writeStartElement("number");
				xmlsw.writeCharacters(String.valueOf(item.getNumber()));
				xmlsw.writeEndElement();

				xmlsw.writeEndElement();
				xmlsw.writeCharacters("\r\n");
			}
			xmlsw.writeEndElement(); //end success
			xmlsw.writeEndElement(); //end profile
			xmlsw.writeEndDocument();
			xmlsw.flush();
			xmlsw.close();

		}
	}

	public ArrayList<SuccessEntry> getSuccess() {
		// TODO: This should be called only if the DAU wants to.
		return success;
	}

}
