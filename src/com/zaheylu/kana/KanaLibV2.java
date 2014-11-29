package com.zaheylu.kana;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class KanaLibV2 {

	private KanaLibV2() {}


	private static HashMap<Character, Character> specialMap = new HashMap<Character, Character>();
	private static HashMap<Character, Character> caseMap = new HashMap<Character, Character>();
	private static HashMap<String, String> HiraMap = new HashMap<String, String>();
	private static HashMap<String, String> KataMap = new HashMap<String, String>();


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

					"n", "ん", "ン" } };

	private static final String[] Alphabet = new String[] {
			"a", "i", "u", "e", "o", "ka", "ki", "ku", "ke", "ko", "sa", "si", "su", "se", "so", "ta", "ti", "tu", "te", "to", "na", "ni", "nu",
			"ne", "no", "ha", "hi", "hu", "he", "ho", "ma", "mi", "mu", "me", "mo", "ya", "yu", "yo", "ra", "ri", "ru", "re", "ro", "wa", "wo", "n",
			"ga", "gi", "gu", "ge", "go", "za", "zi", "zu", "ze", "zo", "da", "di", "du", "de", "do", "ba", "bi", "bu", "be", "bo", "pa", "pi", "pu",
			"pe", "po" };



	// variables to initiate
	public static ArrayList<String[][]> romaHira;
	public static ArrayList<String[][]> romaKata;

	// field numbers
	public static final int ROMA = 0;
	public static final int HIRA = 1;
	public static final int KATA = 2;

	static {
		specialMap.put('.', '。');
		specialMap.put(' ', '　');

		caseMap.put('ﾔ', 'ヤ');
		caseMap.put('ﾕ', 'ユ');
		caseMap.put('ﾖ', 'ヨ');
		caseMap.put('ｱ', 'ア');
		caseMap.put('ｲ', 'イ');
		caseMap.put('ｳ', 'ウ');
		caseMap.put('ｴ', 'エ');
		caseMap.put('ｵ', 'オ');
		caseMap.put('ゃ', 'や');
		caseMap.put('ゅ', 'ゆ');
		caseMap.put('ょ', 'よ');

		for (String[] str : DATA) {
			if (str[HIRA] != null) HiraMap.put(str[ROMA], str[HIRA]);
			if (str[KATA] != null) KataMap.put(str[ROMA], str[KATA]);
		}
	}

	public static String convertChr(char arg, int from, int to) {
		return convertChr(String.valueOf(arg), from, to);
	}

	public static String convertChr(String arg, int from, int to) {
		if (from == ROMA) {
			if (to == HIRA) {
				return HiraMap.get(arg);
			} else if (to == KATA) {
				return KataMap.get(arg);
			}
		} else if (to == ROMA) {
			if (from == HIRA) {
				if (HiraMap.containsValue(arg)) for (Entry<String, String> entry : HiraMap.entrySet()) {
					if (arg.equals(entry.getValue())) {
						return entry.getKey();
					}
				}
			} else if (from == KATA) {
				if (KataMap.containsValue(arg)) for (Entry<String, String> entry : KataMap.entrySet()) {
					if (arg.equals(entry.getValue())) {
						return entry.getKey();
					}
				}
			}
		}
		return null;
	}

	public static boolean containsChr(String arg, int from, int to) {
		if (from == ROMA) {
			if (to == HIRA) return HiraMap.containsKey(arg);
			else if (to == KATA) return KataMap.containsKey(arg);
		} else if (to == ROMA) {
			if (from == HIRA) return HiraMap.containsValue(arg);
			else if (from == KATA) return KataMap.containsValue(arg);
		}
		return false;
	}

	/**
	 * Converts a String from one writing system to another.<br>
	 * Allowed are <b>Romaji -> Hiragana / Katakana</b><br>
	 * and <b>Hiragana / Katakana -> Romaji.</b><br>
	 * <br>
	 * <b> 0 : Romaji<br>
	 * 1 : Hiragana<br>
	 * 2 : Katakana</b><br>
	 * <br>
	 * 
	 * @param arg
	 *            string to convert
	 * @param from
	 *            writing system of <b>arg</b>
	 * @param to
	 *            writing system you want <b>arg</b> to convert to
	 */
	public static String convertStr(String arg, int from, int to) {
		if (arg == null || arg.isEmpty()) return null;
		if (from < 0 || from > 2 || to < 0 || to > 2) return null;
		if ((from == KATA && to == HIRA) || (from == HIRA && to == KATA)) return null;
		char[] c = arg.toCharArray();
		String tmpS = null;
		int length = c.length;
		int index = 0;
		int readLength = 0;
		StringBuilder sb = new StringBuilder("");
		if (from == ROMA) {
			readLength = 1;
			while (index < length) {
				boolean eof = false;
				if (index + readLength >= arg.length()) {
					readLength = arg.length() - index;
					eof = true;
				}
				tmpS = new String(c, index, readLength);
				if (containsChr(tmpS, from, to)) {
					if (readLength == 1 && index > 0 && from == ROMA && to == KATA && c[index - 1] == c[index]
							&& (c[index] == 'a' || c[index] == 'e' || c[index] == 'i' || c[index] == 'o' || c[index] == 'u')) {
						sb.append("ー");
						index += 1;
						readLength = 1;
					} else {
						sb.append(convertChr(tmpS, from, to));
						index += readLength;
						readLength = 1;
					}
				} else {
					boolean special = false;
					if (readLength == 1) {
						if (to == KATA || to == HIRA) {
							if (specialMap.containsKey(c[index])) {
								sb.append(specialMap.get(c[index]));
								special = true;
							}
						}
					}
					if (special) {
						index += 1;
						readLength = 1;
					} else {
						readLength++;
						if (readLength >= 5) {
							sb.append(c[index]);
							index += 1;
							readLength = 1;
						}
						if (eof) {
							sb.append(c[index]);
							index += 1;
						}
					}
				}
			}
		} else if (to == ROMA) {
			readLength = 4;
			while (index < length) {
				if (index + readLength >= arg.length()) {
					readLength = arg.length() - index;
				}
				tmpS = new String(c, index, readLength);
				if (containsChr(tmpS, from, to)) {
					sb.append(convertChr(tmpS, from, to));
					index += readLength;
					readLength = 4;
				} else {
					boolean special = false;
					if (readLength == 1) {
						if (from == KATA && to == ROMA && index > 0 && c[index] == 'ー') {
							sb.append(sb.charAt(sb.length() - 1));
							special = true;
						} else if (!special && to == ROMA) {
							if (!special && specialMap.containsValue(c[index])) {
								for (Entry<Character, Character> entry : specialMap.entrySet()) {
									if (entry.getValue() == c[index]) {
										sb.append(entry.getKey());
										special = true;
										break;
									}
								}
							}
						}
					}
					if (special) {
						index += 1;
						readLength = 4;
					} else {
						readLength--;
						if (readLength == 0) {
							sb.append(c[index]);
							index += 1;
							readLength = 4;
						}
					}
				}
			}
		}
		return sb.toString();
	}

	public static boolean matches(String str1, int typ1, String str2, int typ2) {
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
		if (arg == null || arg.isEmpty()) return -1;
		int max = 4;
		if (arg.length() < max) max = arg.length();
		for (int n = 0; n <= max; n++) {
			if (HiraMap.containsValue(arg.substring(0, n))) return HIRA;
			if (KataMap.containsValue(arg.substring(0, n))) return KATA;
		}
		return ROMA;
	}

	public static String[] getAlphabet() {
		return Alphabet;
	}

	public static boolean equalsIgnoreCase(String s1, String s2) {
		if (s1 == null || s2 == null) return false;
		if ((s1 == null || s1.isEmpty()) && (s2 == null || s2.isEmpty())) return true;
		return uppercase(s1).equalsIgnoreCase(uppercase(s2));
	}

	public static String uppercase(String s) {
		char[] c = s.toCharArray();
		for (int n = 0; n < c.length; n++) {
			if (caseMap.containsKey(c[n])) c[n] = caseMap.get(c[n]);
		}
		return new String(c);
	}

}
