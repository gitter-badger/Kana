package com.zaheylu.kana;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Version {

	private static final String TITLE = "Kana";
	private static final String AUTHOR = "Zaheylu";

	private static byte MAJOR_VERSION;
	private static byte MINOR_VERSION;
	private static byte RELEASE_VERSION;
	private static int BUILD_VERSION;
	private static final String ANNEX = "";
	private static final String ANNEX2 = "";
	private static final int VOCVERSION = 3;
	private static final int LOGVERSION = 1;

	private static final boolean DEBUG_BUILD = true;
	private static final boolean SPECIAL_BUILD = false;
	private static final boolean PRIVATE_BUILD = true;
	private static final boolean PRE_RELEASE = false;
	private static final boolean DLL = false;


	private static final String LOCALE_ID = "$0407";

	public Version() {
		try {
			MAJOR_VERSION = Byte.valueOf(new BufferedReader(new InputStreamReader(Version.class.getResourceAsStream("/version/version1"))).readLine());
			MINOR_VERSION = Byte.valueOf(new BufferedReader(new InputStreamReader(Version.class.getResourceAsStream("/version/version2"))).readLine());
			RELEASE_VERSION = Byte.valueOf(new BufferedReader(new InputStreamReader(Version.class.getResourceAsStream("/version/version3"))).readLine());
			BUILD_VERSION = Byte.valueOf(new BufferedReader(new InputStreamReader(Version.class.getResourceAsStream("/version/version4"))).readLine());
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}

	public static String getVersion() {
		return String.format("%d.%d.%d.%d%s %s", MAJOR_VERSION, MINOR_VERSION, RELEASE_VERSION, BUILD_VERSION, ANNEX, ANNEX2);
	}

	public static String getShortVersion() {
		return String.format("%d.%d.%d.%d", MAJOR_VERSION, MINOR_VERSION, RELEASE_VERSION, BUILD_VERSION);
	}


	public static String getTitle() {
		return String.format("%s V%d.%d.%d.%d%s %s", TITLE, MAJOR_VERSION, MINOR_VERSION, RELEASE_VERSION, BUILD_VERSION, ANNEX, ANNEX2);
	}

	public static String getExtendedTitle() {
		return String.format("%s by %s, Version %d.%d.%d.%d%s %s", TITLE, AUTHOR, MAJOR_VERSION, MINOR_VERSION, RELEASE_VERSION, BUILD_VERSION, ANNEX, ANNEX2);
	}

	public static String getAuthor() {
		return AUTHOR;
	}

	public static byte getMajorVersion() {
		return MAJOR_VERSION;
	}

	public static byte getMinorVersion() {
		return MINOR_VERSION;
	}

	public static byte getReleaseVersion() {
		return RELEASE_VERSION;
	}

	public static int getBuildVersion() {
		return BUILD_VERSION;
	}

	public static String getAnnex() {
		return ANNEX;
	}

	public static String getAnnex2() {
		return ANNEX2;
	}

	public static boolean isDebugBuild() {
		return DEBUG_BUILD;
	}

	public static boolean isSpecialBuild() {
		return SPECIAL_BUILD;
	}

	public static boolean isPrivateBuild() {
		return PRIVATE_BUILD;
	}

	public static boolean isPreRelease() {
		return PRE_RELEASE;
	}

	public static boolean isDll() {
		return DLL;
	}

	public static String getLocaleId() {
		return LOCALE_ID;
	}

	public static int getVocVersion() {
		return VOCVERSION;
	}

	public static int getLogVersion() {
		return LOGVERSION;
	}


}
