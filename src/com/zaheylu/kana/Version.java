package com.zaheylu.kana;

public class Version {

	private static final String TITLE = "Kana";
	private static final String AUTHOR = "Zaheylu";

	private static final byte MAJOR_VERSION = 0;
	private static final byte MINOR_VERSION = 2;
	private static final byte RELEASE_VERSION = 1;
	private static final int BUILD_VERSION = 65;
	private static final String ANNEX = "";
	private static final String ANNEX2 = "";
	private static final int VOCVERSION = 3;
	
	private static final boolean DEBUG_BUILD = true;
	private static final boolean SPECIAL_BUILD = false;
	private static final boolean PRIVATE_BUILD = true;
	private static final boolean PRE_RELEASE = false;
	private static final boolean DLL = false;
	

	private static final String LOCALE_ID = "$0407";
		
	
	
	
	
	public static String getVersion() {
		return String.format("%d.%d.%d.%d%s %s", MAJOR_VERSION, MINOR_VERSION, RELEASE_VERSION, BUILD_VERSION, ANNEX, ANNEX2);
	}
	public static String getShortVersion() {
		return String.format("%d.%d.%d.%d", MAJOR_VERSION, MINOR_VERSION, RELEASE_VERSION, BUILD_VERSION);
	}
	
	
	public static String getTitle()  {
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
	
	
}
