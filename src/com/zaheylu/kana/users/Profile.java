package com.zaheylu.kana.users;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.FileSystems;
import java.util.LinkedHashMap;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.zaheylu.kana.exceptions.IndexException;
import com.zaheylu.log.Log;

public class Profile {

	private LinkedHashMap<Integer, SuccessEntry> success;

	public Profile() {
		success = new LinkedHashMap<Integer, SuccessEntry>();
	}

	public Profile(LinkedHashMap<Integer, SuccessEntry> success) {
		this.success = success;
	}



	public void add(SuccessEntry se) {
		int l = size();
		boolean exception = false;
		if (success.get(l) != null) exception = true;
		se.setIndex(l);
		success.put(l, se);
		try {
			if (exception) throw new IndexException(l, se);
		} catch (IndexException e) {
			e.printStackTrace();
		}
	}

	public void add(SuccessEntry se, int index) {
		boolean exception = false;
		if (success.get(index) != null) exception = true;
		se.setIndex(index);
		success.put(index, se);
		try {
			if (exception) throw new IndexException(index, se);
		} catch (IndexException e) {
			e.printStackTrace();
		}
	}

	@Deprecated
	public SuccessEntry get(int n) {
		return success.get(n);
	}

	public int size() {
		return success.size();
	}

	public void put(Integer i, SuccessEntry se) {
		try {
			if (i != se.getIndex()) throw new IndexException(i, se);
		} catch (IndexException e) {
			e.printStackTrace();
		}
		success.put(i, se);
	}

	// TODO: This should be called only if the User wants to.
	public void save() throws UnsupportedEncodingException, FileNotFoundException, XMLStreamException {
		if (null != success && size() > 0) {
			Log.event("profile.save");
			PrintWriter writerXml = new PrintWriter(new OutputStreamWriter(new FileOutputStream(Log.getString("path.user") + "profiles"
					+ FileSystems.getDefault().getSeparator() + "default.xml"), "utf-8"));
			XMLOutputFactory xof = XMLOutputFactory.newInstance();
			XMLStreamWriter xmlsw = xof.createXMLStreamWriter(writerXml);

			// write declaration
			xmlsw.writeStartDocument("UTF-8", "1.0");
			xmlsw.writeCharacters("\r\n");
			xmlsw.writeStartElement("profile");
			xmlsw.writeStartElement("success");
			xmlsw.writeCharacters("\r\n");
			for (SuccessEntry item : success.values()) {
				xmlsw.writeStartElement("entry");

				xmlsw.writeStartElement("value");
				xmlsw.writeCharacters(String.valueOf(item.getSuccess()));
				xmlsw.writeEndElement();

				xmlsw.writeStartElement("number");
				xmlsw.writeCharacters(String.valueOf(item.getNumber()));
				xmlsw.writeEndElement();

				xmlsw.writeStartElement("timestamp");
				xmlsw.writeCharacters(String.valueOf(item.getTimestamp()));
				xmlsw.writeEndElement();

				xmlsw.writeEndElement();
				xmlsw.writeCharacters("\r\n");
			}
			xmlsw.writeEndElement(); // end success
			xmlsw.writeEndElement(); // end profile
			xmlsw.writeEndDocument();
			xmlsw.flush();
			xmlsw.close();

		}
	}

	/**
	 * The same as GetSuccess(), but shorter
	 * 
	 * @return success
	 */
	public LinkedHashMap<Integer, SuccessEntry> success() {
		return success;
	}

	public LinkedHashMap<Integer, SuccessEntry> getSuccess() {
		return success;
	}
}
