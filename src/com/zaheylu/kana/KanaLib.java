package com.zaheylu.kana;

import java.util.ArrayList;
@Deprecated
public class KanaLib {
	private static final String space = "　";
	private static final String dot = "。";
	private static final String[][] CaseConversion = new String[][] {
			{
					"ﾔ", "ヤ" }, {
					"ﾕ", "ユ" }, {
					"ﾖ", "ヨ" }, {

					"ｱ", "ア" }, {
					"ｲ", "イ" }, {
					"ｳ", "ウ" }, {
					"ｴ", "エ" }, {
					"ｵ", "オ" }, {

					"ゃ", "や" }, {
					"ゅ", "ゆ" }, {
					"ょ", "よ" } };


	private static final String[] Alphabet = new String[] {
			"a", "i", "u", "e", "o", "ka", "ki", "ku", "ke", "ko", "sa", "si", "su", "se", "so", "ta", "ti", "tu", "te", "to", "na", "ni", "nu", "ne", "no",
			"ha", "hi", "hu", "he", "ho", "ma", "mi", "mu", "me", "mo", "ya", "yu", "yo", "ra", "ri", "ru", "re", "ro", "wa", "wo", "n", "ga", "gi", "gu",
			"ge", "go", "za", "zi", "zu", "ze", "zo", "da", "di", "du", "de", "do", "ba", "bi", "bu", "be", "bo", "pa", "pi", "pu", "pe", "po" };
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
					"nn", "ん", "ン" }, {
					".", dot, dot }, {
					" ", space, space }, {
					",", "、", "、" }, {
					"-", null, "ー" } };


	public static String convert(String arg, int from, int to) {
		for (String[] s : DATA) {
			if (equalsIgnoreCase(arg, s[from]) && s[to] != null) {
				return s[to];
			}
		}
		return null;
	}

	public static String convertPlus(String arg, int from, int to) {
		String result = "";
		int index = 0;
		int read = 4;
		if (arg.isEmpty()) return null;
		boolean broke = false;
		do {
			if (index + read > arg.length()) read = arg.length() - index;
			while (convert(arg.substring(index, index + read), from, to) == null) {
				read--;
				if (read == 0) {
					read++;
					broke = true;
					break;
				}
			}
			if (broke) {
				result += arg.substring(index, index + read);
				broke = false;
			} else {
				result += convert(arg.substring(index, index + read), from, to);
				if (from == 0 && to == 2 && index + read < arg.length()) {
					if (equalsIgnoreCase(arg.substring(index + read - 1, index + read), arg.substring(index + read, index + read + 1))) {
						result += convert("-", 0, 2);
						index++;
					}
				}
			}
			index += read;
			read = 4;

		} while (index < arg.length());
		return result;
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
		if (arg.isEmpty()) return -1;
		else {
			for (String[] data : DATA) {
				if (equalsIgnoreCase(data[1], arg)) {
					return 1;
				}
				if (equalsIgnoreCase(data[2], arg)) {
					return 2;
				}
			}
		}
		return 0;
	}

	public static String getSpace() {
		return space;
	}

	public static String getDot() {
		return dot;
	}

	public static String[][] getData() {
		return DATA;
	}

	public static String[] getAlphabet() {
		return Alphabet;
	}

	public static boolean equalsIgnoreCase(String s1, String s2) {
		if (s1 == null || s2 == null) return false;
		char[] c1 = s1.toCharArray();
		char[] c2 = s2.toCharArray();
		for (char char1 : c1) {
			for (String[] caseC : CaseConversion) {
				if (String.valueOf(char1).equalsIgnoreCase(caseC[0])) {
					char1 = caseC[1].charAt(0);
				}
			}
		}
		for (char char2 : c2) {
			for (String[] caseC : CaseConversion) {
				if (String.valueOf(char2).equalsIgnoreCase(caseC[0])) {
					char2 = caseC[1].charAt(0);
				}
			}
		}
		return String.valueOf(c1).equalsIgnoreCase(String.valueOf(c2));
	}

}
