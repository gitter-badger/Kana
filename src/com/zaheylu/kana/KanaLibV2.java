package com.zaheylu.kana;

import java.util.ArrayList;

public class KanaLibV2 {

	private KanaLibV2() {}


	private static final char[][] specialChar = new char[][] {
			{
					'.', '。' }, {
					' ', '　' } };
	private static final char[][] CaseConversion = new char[][] {
			{
					'ﾔ', 'ヤ' }, {
					'ﾕ', 'ユ' }, {
					'ﾖ', 'ヨ' }, {

					'ｱ', 'ア' }, {
					'ｲ', 'イ' }, {
					'ｳ', 'ウ' }, {
					'ｴ', 'エ' }, {
					'ｵ', 'オ' }, {

					'ゃ', 'や' }, {
					'ゅ', 'ゆ' }, {
					'ょ', 'よ' } };


	private static final String[] Alphabet = new String[] {
			"a", "i", "u", "e", "o", "ka", "ki", "ku", "ke", "ko", "sa", "si", "su", "se", "so", "ta", "ti", "tu", "te", "to", "na", "ni", "nu",
			"ne", "no", "ha", "hi", "hu", "he", "ho", "ma", "mi", "mu", "me", "mo", "ya", "yu", "yo", "ra", "ri", "ru", "re", "ro", "wa", "wo", "n",
			"ga", "gi", "gu", "ge", "go", "za", "zi", "zu", "ze", "zo", "da", "di", "du", "de", "do", "ba", "bi", "bu", "be", "bo", "pa", "pi", "pu",
			"pe", "po" };
	private static final String[][] DATA = new String[][] {
			{
					"wi", null, "ウィ" }, {
					"we", null, "ウェ" }, {
					"wo", null, "ウォ" }, {
					"she", null, "シェ" }, {
					"je", null, "ジェ" }, {
					"che", null, "チェ" }, {
					"fa", null, "ファ" }, {
					"fi", null, "フィ" }, {
					"fe", null, "フェ" }, {
					"fo", null, "フォ" }, {
					"ti", null, "ティ" }, {
					"di", null, "ディ" }, {
					"dyu", null, "ヂュ" }, {
					"ssha", "っしゃ", null }, {
					"ssya", "っしゃ", null }, {
					"sshu", "っしゅ", null }, {
					"ssyu", "っしゅ", null }, {
					"ssho", "っしょ", null }, {
					"ssyo", "っしょ", null }, {

					"ccha", "っちゃ", null }, {
					"ttya", "っちゃ", null }, {
					"cchu", "っちゅ", null }, {
					"ttyu", "っちゅ", null }, {
					"ccho", "っちょ", null }, {
					"ttyo", "っちょ", null }, {

					"ppya", "っぴゃ", null }, {
					"ppyu", "っぴゅ", null }, {
					"ppyo", "っぴょ", null }, {

					"kkya", "っきゃ", null }, {
					"kkyi", "っきぃ", null }, {
					"kkyu", "っきゅ", null }, {
					"kkye", "っきぇ", null }, {
					"kkyo", "っきょ", null }, {

					"tta", "った", null }, {
					"cchi", "っち", null }, {
					"tti", "っち", null }, {
					"ttsu", "っつ", null }, {
					"ttu", "っつ", null }, {
					"tte", "って", null }, {
					"tto", "っと", null }, {

					"ppa", "っぱ", null }, {
					"ppi", "っぴ", null }, {
					"ppu", "っぷ", null }, {
					"ppe", "っぺ", null }, {
					"ppo", "っぽ", null }, {

					"kka", "っか", null }, {
					"kki", "っき", null }, {
					"kku", "っく", null }, {
					"kke", "っけ", null }, {
					"kko", "っこ", null }, {

					"ssa", "っさ", null }, {
					"sshi", "っし", null }, {
					"ssi", "っし", null }, {
					"ssu", "っす", null }, {
					"sse", "っせ", null }, {
					"sso", "っそ", null }, {

					"nna", "んな", null }, {
					"nni", "んに", null }, {
					"nnu", "んぬ", null }, {
					"nne", "んね", null }, {
					"nno", "んの", null }, {

					"kya", "きゃ", "キャ" }, {
					"kyu", "きゅ", "キュ" }, {
					"kyo", "きょ", "キョ" }, {

					"gya", "ぎゃ", "ギャ" }, {
					"gyu", "ぎゅ", "ギュ" }, {
					"gyo", "ぎょ", "ギョ" }, {

					"sha", "しゃ", "シャ" }, {
					"sya", "しゃ", "シャ" }, {
					"shu", "しゅ", "シュ" }, {
					"syu", "しゅ", "シュ" }, {
					"sho", "しょ", "ショ" }, {
					"syo", "しょ", "ショ" }, {
					"ja", "じゃ", "ジャ" }, {
					"zya", "じゃ", "ジャ" }, {
					"ju", "じゅ", "ジュ" }, {
					"zyu", "じゅ", "ジュ" }, {
					"jo", "じょ", "ジョ" }, {
					"zyo", "じょ", "ジョ" }, {

					"cha", "ちゃ", "チャ" }, {
					"tya", "ちゃ", "チャ" }, {
					"chu", "ちゅ", "チュ" }, {
					"tyu", "ちゅ", "チュ" }, {
					"cho", "ちょ", "チョ" }, {
					"tyo", "ちょ", "チョ" }, {

					"nya", "にゃ", "ニャ" }, {
					"nyu", "にゅ", "ニュ" }, {
					"nyo", "にょ", "ニョ" }, {

					"hya", "ひゃ", "ヒャ" }, {
					"hyu", "ひゅ", "ヒュ" }, {
					"hyo", "ひょ", "ヒィ" }, {

					"bya", "びゃ", "ビャ" }, {
					"byu", "びゅ", "ビュ" }, {
					"byo", "びょ", "ビョ" }, {

					"pya", "びゃ", "ピャ" }, {
					"pyu", "びゅ", "ピュ" }, {
					"pyo", "びょ", "ピョ" }, {

					"mya", "みゃ", "ミャ" }, {
					"myu", "みゅ", "ミュ" }, {
					"myo", "みょ", "ミョ" }, {

					"rya", "りゃ", "リャ" }, {
					"ryu", "りゅ", "リュ" }, {
					"ryo", "りょ", "リョ" }, {

					"ga", "が", "ガ" }, {
					"gi", "ぎ", "ギ" }, {
					"gu", "ぐ", "グ" }, {
					"ge", "げ", "ゲ" }, {
					"go", "ご", "ゴ" }, {

					"za", "ざ", "ザ" }, {
					"ji", "じ", "ジ" }, {
					"zi", "じ", "ジ" }, {
					"zu", "ず", "ズ" }, {
					"ze", "ぜ", "ゼ" }, {
					"zo", "ぞ", "ゾ" }, {

					"da", "だ", "ダ" }, {
					"ji", "ぢ", "ヂ" }, {
					"di", "ぢ", "ヂ" }, {
					"zu", "づ", "ヅ" }, {
					"du", "づ", "ヅ" }, {
					"de", "で", "デ" }, {
					"do", "ど", "ド" }, {

					"ba", "ば", "バ" }, {
					"bi", "び", "ビ" }, {
					"bu", "ぶ", "ブ" }, {
					"be", "べ", "ベ" }, {
					"bo", "ぼ", "ボ" }, {

					"pa", "ぱ", "パ" }, {
					"pi", "ぴ", "ピ" }, {
					"pu", "ぷ", "プ" }, {
					"pe", "ぺ", "ペ" }, {
					"po", "ぽ", "ポ" }, {

					"ka", "か", "カ" }, {
					"ki", "き", "キ" }, {
					"ku", "く", "ク" }, {
					"ke", "け", "ケ" }, {
					"ko", "こ", "コ" }, {

					"sa", "さ", "サ" }, {
					"shi", "し", "シ" }, {
					"si", "し", "シ" }, {
					"su", "す", "ス" }, {
					"se", "せ", "セ" }, {
					"so", "そ", "ソ" }, {
					"ta", "た", "タ" }, {
					"chi", "ち", "チ" }, {
					"ti", "ち", "チ" }, {
					"tsu", "つ", "ツ" }, {
					"tu", "つ", "ツ" }, {
					"te", "て", "テ" }, {
					"to", "と", "ト" }, {

					"na", "な", "ナ" }, {
					"ni", "に", "ニ" }, {
					"nu", "ぬ", "ヌ" }, {
					"ne", "ね", "ネ" }, {
					"no", "の", "ノ" }, {

					"ha", "は", "ハ" }, {
					"hi", "ひ", "ヒ" }, {
					"fu", "ふ", "フ" }, {
					"hu", "ふ", "フ" }, {
					"he", "へ", "ヘ" }, {
					"ho", "ほ", "ホ" }, {

					"ma", "ま", "マ" }, {
					"mi", "み", "ミ" }, {
					"mu", "む", "ム" }, {
					"me", "め", "メ" }, {
					"mo", "も", "モ" }, {

					"ya", "や", "ヤ" }, {
					"yu", "ゆ", "ユ" }, {
					"yo", "よ", "ヨ" }, {

					"ra", "ら", "ラ" }, {
					"ri", "り", "リ" }, {
					"ru", "る", "ル" }, {
					"re", "れ", "レ" }, {
					"ro", "ろ", "ロ" }, {

					"wa", "わ", "ワ" }, {
					"wo", "を", "ヲ" }, {


					"a", "あ", "ア" }, {
					"i", "い", "イ" }, {
					"u", "う", "ウ" }, {
					"e", "え", "エ" }, {
					"o", "お", "オ" }, {

					"n", "ん", "ン" }, {
					"nn", "ん", "ン" } };

	// variables to initiate
	public static ArrayList<String[][]> romaHira;
	public static ArrayList<String[][]> romaKata;

	// field numbers
	public static final int ROMA = 0;
	public static final int HIRA = 1;
	public static final int KATA = 2;


	public static void init() {
		int hiraC[] = new int[] {
				0, 0, 0, 0 };
		int kataC[] = new int[] {
				0, 0, 0, 0 };
		romaHira = new ArrayList<String[][]>();
		romaKata = new ArrayList<String[][]>();

		for (String[] str : DATA) {
			if (str[HIRA] != null) hiraC[str[ROMA].length() - 1]++;
			if (str[KATA] != null) kataC[str[ROMA].length() - 1]++;
		}

		for (int n = 0; n < hiraC.length; n++) {
			romaHira.add(new String[hiraC[n]][2]);
			hiraC[n] = 0;
			romaKata.add(new String[kataC[n]][2]);
			kataC[n] = 0;
		}
		for (String[] str : DATA) {
			int group = str[ROMA].length() - 1;
			if (str[HIRA] != null) {
				romaHira.get(group)[hiraC[group]][0] = str[ROMA];
				romaHira.get(group)[hiraC[group]][1] = str[HIRA];
				hiraC[group]++;
			}
			if (str[KATA] != null) {
				romaKata.get(group)[kataC[group]][0] = str[ROMA];
				romaKata.get(group)[kataC[group]][1] = str[KATA];
				kataC[group]++;
			}
		}
	}

	public static String convertChr(char arg, int from, int to) {
		return convertChr(String.valueOf(arg), from, to);
	}

	public static String convertChr(String arg, int from, int to) {
		ArrayList<String[][]> target;
		if (from == ROMA) {
			if (to == HIRA) target = romaHira;
			else target = romaKata;
			for (String[] str : target.get(arg.length() - 1)) {
				if (str[0].equalsIgnoreCase(arg)) return str[1];
			}
		} else if (to == ROMA) {
			for (String[] str : DATA) {
				if (str[from] != null && str[from].equalsIgnoreCase(arg)) return str[ROMA];
			}
		} else {
			if (from == HIRA) {
				for (String[] str : DATA) {
					if (str[HIRA] != null && str[HIRA].equalsIgnoreCase(arg)) return convertChr(str[ROMA], ROMA, KATA);
				}
			} else {
				for (String[] str : DATA) {
					if (str[KATA] != null && str[KATA].equalsIgnoreCase(arg)) return convertChr(str[ROMA], ROMA, HIRA);
				}
			}
		}
		return null;
	}

	public static String convertStr(String arg, int from, int to) {
		if (arg == null || arg.isEmpty()) return null;
		char[] c = arg.toCharArray();
		String tmpS = null;
		int length = c.length;
		int index = 0;
		int readLength = 4;
		StringBuilder sb = new StringBuilder("");
		do {
			readLength = 4;
			if (index + readLength > length) readLength = length - index;
			do {
				tmpS = KanaLibV2.convertChr(new String(c, index, readLength), from, to);
				if (tmpS != null) {
					if (readLength == 1 && index > 0 && from == ROMA && to == KATA && c[index - 1] == c[index]
							&& (c[index] == 'a' || c[index] == 'e' || c[index] == 'i' || c[index] == 'o' || c[index] == 'u')) {
						sb.append("ー");
						index += 1;
						readLength = -1;
					} else {
						sb.append(tmpS);
						index += readLength;
						readLength = -1;
					}
				} else {
					boolean special = false;
					if (readLength == 1) {
						if (to == KATA || to == HIRA) {
							for (char[] cc : specialChar)
								if (c[index] == cc[0]) {
									sb.append(cc[1]);
									special = true;
								}
						} else if (to == ROMA) {
							for (char[] cc : specialChar)
								if (c[index] == cc[1]) {
									sb.append(cc[0]);
									special = true;
								}
						}
						if (!special) {
							if (from == KATA && to == ROMA) {
								if (c[index] == 'ー' && index > 0) {
									String s = convertChr(c[index - 1], from, to);
									sb.append(s.charAt(s.length() - 1));
									special = true;
								}
							}
						}
					}
					if (special) {
						index += 1;
						readLength = -1;
					} else readLength--;
				}
				if (readLength == 0) {
					sb.append(c[index]);
					index++;
				}
			} while (readLength > 0);
		} while (index < length);
		return sb.toString();
	}

	public static boolean matches(String str1, int typ1, String str2, int typ2) {
		// TODO: Rethink this.
		ArrayList<Integer> ar = new ArrayList<Integer>();
		for (int n = 0; n < DATA.length; n++)
			if (equalsIgnoreCase(DATA[n][typ1], str1)) {
				ar.add(n);
			}
		boolean result = false;
		for (Integer val : ar) {
			if (equalsIgnoreCase(DATA[val][typ2], str2)) {
				result = true;
			}
		}
		return result;
	}

	public static int findType(String arg) {
		if (arg.isEmpty()) return -1;
		else {
			char[] c = arg.toCharArray();
			int max = 3;
			if (c.length < 4) max = c.length - 1;
			for (int n = max; n >= 0; n--) {
				String arg2 = new String(c, 0, n + 1);
				for (String[] str : romaHira.get(n)) {
					if (equalsIgnoreCase(arg2, str[0])) return 0;
					if (equalsIgnoreCase(arg2, str[1])) return 1;
				}
				for (String[] str : romaKata.get(n)) {
					if (equalsIgnoreCase(arg2, str[1])) return 2;
				}
				for (String[] str : romaKata.get(n)) {
					if (equalsIgnoreCase(arg2, str[0])) return 0;
				}

			}
		}
		return 3;
	}

	public static String[][] getData() {
		// TODO: Rethink Getter and setter
		return DATA;
	}

	public static String[] getAlphabet() {
		return Alphabet;
	}

	public static boolean equalsIgnoreCase(String s1, String s2) {
		if (s1 == null || s2 == null) return false;
		return uppercase(s1).equalsIgnoreCase(uppercase(s2));
	}

	public static String uppercase(String s) {
		char[] c = s.toCharArray();
		for (int n = 0; n < c.length; n++) {
			for (char[] caseC : CaseConversion) {
				if (c[n] == caseC[0]) {
					c[n] = caseC[1];
				}
			}
		}
		return new String(c);
	}

}
