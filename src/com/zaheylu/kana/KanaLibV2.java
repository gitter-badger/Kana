package com.zaheylu.kana;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class KanaLibV2 {
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

	private static final String[][] HIRADATA = new String[][] {
			{
					"ssha", "っしゃ" }, {
					"ssya", "っしゃ" }, {
					"sshu", "っしゅ" }, {
					"ssyu", "っしゅ" }, {
					"ssho", "っしょ" }, {
					"ssyo", "っしょ" }, {

					"ccha", "っちゃ" }, {
					"ttya", "っちゃ" }, {
					"cchu", "っちゅ" }, {
					"ttyu", "っちゅ" }, {
					"ccho", "っちょ" }, {
					"ttyo", "っちょ" }, {

					"ppya", "っぴゃ" }, {
					"ppyu", "っぴゅ" }, {
					"ppyo", "っぴょ" }, {

					"kkya", "っきゃ" }, {
					"kkyi", "っきぃ" }, {
					"kkyu", "っきゅ" }, {
					"kkye", "っきぇ" }, {
					"kkyo", "っきょ" }, {

					"tta", "った" }, {
					"cchi", "っち" }, {
					"tti", "っち" }, {
					"ttsu", "っつ" }, {
					"ttu", "っつ" }, {
					"tte", "って" }, {
					"tto", "っと" }, {

					"ppa", "っぱ" }, {
					"ppi", "っぴ" }, {
					"ppu", "っぷ" }, {
					"ppe", "っぺ" }, {
					"ppo", "っぽ" }, {

					"kka", "っか" }, {
					"kki", "っき" }, {
					"kku", "っく" }, {
					"kke", "っけ" }, {
					"kko", "っこ" }, {

					"ssa", "っさ" }, {
					"sshi", "っし" }, {
					"ssi", "っし" }, {
					"ssu", "っす" }, {
					"sse", "っせ" }, {
					"sso", "っそ" }, {

					"nna", "んな" }, {
					"nni", "んに" }, {
					"nnu", "んぬ" }, {
					"nne", "んね" }, {
					"nno", "んの" }, {

					"kya", "きゃ" }, {
					"kyu", "きゅ" }, {
					"kyo", "きょ" }, {

					"gya", "ぎゃ" }, {
					"gyu", "ぎゅ" }, {
					"gyo", "ぎょ" }, {

					"sha", "しゃ" }, {
					"sya", "しゃ" }, {
					"shu", "しゅ" }, {
					"syu", "しゅ" }, {
					"sho", "しょ" }, {
					"syo", "しょ" }, {
					"ja", "じゃ" }, {
					"zya", "じゃ" }, {
					"ju", "じゅ" }, {
					"zyu", "じゅ" }, {
					"jo", "じょ" }, {
					"zyo", "じょ" }, {

					"cha", "ちゃ" }, {
					"tya", "ちゃ" }, {
					"chu", "ちゅ" }, {
					"tyu", "ちゅ" }, {
					"cho", "ちょ" }, {
					"tyo", "ちょ" }, {

					"nya", "にゃ" }, {
					"nyu", "にゅ" }, {
					"nyo", "にょ" }, {

					"hya", "ひゃ" }, {
					"hyu", "ひゅ" }, {
					"hyo", "ひょ" }, {

					"bya", "びゃ" }, {
					"byu", "びゅ" }, {
					"byo", "びょ" }, {

					"pya", "びゃ" }, {
					"pyu", "びゅ" }, {
					"pyo", "びょ" }, {

					"mya", "みゃ" }, {
					"myu", "みゅ" }, {
					"myo", "みょ" }, {

					"rya", "りゃ" }, {
					"ryu", "りゅ" }, {
					"ryo", "りょ" }, {

					"ga", "が" }, {
					"gi", "ぎ" }, {
					"gu", "ぐ" }, {
					"ge", "げ" }, {
					"go", "ご" }, {

					"za", "ざ" }, {
					"ji", "じ" }, {
					"zi", "じ" }, {
					"zu", "ず" }, {
					"ze", "ぜ" }, {
					"zo", "ぞ" }, {

					"da", "だ" }, {
					"ji", "ぢ" }, {
					"di", "ぢ" }, {
					"zu", "づ" }, {
					"du", "づ" }, {
					"de", "で" }, {
					"do", "ど" }, {

					"ba", "ば" }, {
					"bi", "び" }, {
					"bu", "ぶ" }, {
					"be", "べ" }, {
					"bo", "ぼ" }, {

					"pa", "ぱ" }, {
					"pi", "ぴ" }, {
					"pu", "ぷ" }, {
					"pe", "ぺ" }, {
					"po", "ぽ" }, {

					"ka", "か" }, {
					"ki", "き" }, {
					"ku", "く" }, {
					"ke", "け" }, {
					"ko", "こ" }, {

					"sa", "さ" }, {
					"shi", "し" }, {
					"si", "し" }, {
					"su", "す" }, {
					"se", "せ" }, {
					"so", "そ" }, {
					"ta", "た" }, {
					"chi", "ち" }, {
					"ti", "ち" }, {
					"tsu", "つ" }, {
					"tu", "つ" }, {
					"te", "て" }, {
					"to", "と" }, {

					"na", "な" }, {
					"ni", "に" }, {
					"nu", "ぬ" }, {
					"ne", "ね" }, {
					"no", "の" }, {

					"ha", "は" }, {
					"hi", "ひ" }, {
					"fu", "ふ" }, {
					"hu", "ふ" }, {
					"he", "へ" }, {
					"ho", "ほ" }, {

					"ma", "ま" }, {
					"mi", "み" }, {
					"mu", "む" }, {
					"me", "め" }, {
					"mo", "も" }, {

					"ya", "や" }, {
					"yu", "ゆ" }, {
					"yo", "よ" }, {

					"ra", "ら" }, {
					"ri", "り" }, {
					"ru", "る" }, {
					"re", "れ" }, {
					"ro", "ろ" }, {

					"wa", "わ" }, {
					"wo", "を" }, {

					"a", "あ" }, {
					"i", "い" }, {
					"u", "う" }, {
					"e", "え" }, {
					"o", "お" }, {

					"n", "ん" }, {
					"nn", "ん" } };
	private static final String[][] KATADATA = new String[][] {
			{
					"wi", "ウィ" }, {
					"we", "ウェ" }, {
					"wo", "ウォ" }, {
					"she", "シェ" }, {
					"je", "ジェ" }, {
					"che", "チェ" }, {
					"fa", "ファ" }, {
					"fi", "フィ" }, {
					"fe", "フェ" }, {
					"fo", "フォ" }, {
					"ti", "ティ" }, {
					"di", "ディ" }, {
					"dyu", "ヂュ" }, {

					"kya", "キャ" }, {
					"kyu", "キュ" }, {
					"kyo", "キョ" }, {

					"gya", "ギャ" }, {
					"gyu", "ギュ" }, {
					"gyo", "ギョ" }, {

					"sha", "シャ" }, {
					"sya", "シャ" }, {
					"shu", "シュ" }, {
					"syu", "シュ" }, {
					"sho", "ショ" }, {
					"syo", "ショ" }, {
					"ja", "ジャ" }, {
					"zya", "ジャ" }, {
					"ju", "ジュ" }, {
					"zyu", "ジュ" }, {
					"jo", "ジョ" }, {
					"zyo", "ジョ" }, {

					"cha", "チャ" }, {
					"tya", "チャ" }, {
					"chu", "チュ" }, {
					"tyu", "チュ" }, {
					"cho", "チョ" }, {
					"tyo", "チョ" }, {

					"nya", "ニャ" }, {
					"nyu", "ニュ" }, {
					"nyo", "ニョ" }, {

					"hya", "ヒャ" }, {
					"hyu", "ヒュ" }, {
					"hyo", "ヒィ" }, {

					"bya", "ビャ" }, {
					"byu", "ビュ" }, {
					"byo", "ビョ" }, {

					"pya", "ピャ" }, {
					"pyu", "ピュ" }, {
					"pyo", "ピョ" }, {

					"mya", "ミャ" }, {
					"myu", "ミュ" }, {
					"myo", "ミョ" }, {

					"rya", "リャ" }, {
					"ryu", "リュ" }, {
					"ryo", "リョ" }, {

					"ga", "ガ" }, {
					"gi", "ギ" }, {
					"gu", "グ" }, {
					"ge", "ゲ" }, {
					"go", "ゴ" }, {

					"za", "ザ" }, {
					"ji", "ジ" }, {
					"zi", "ジ" }, {
					"zu", "ズ" }, {
					"ze", "ゼ" }, {
					"zo", "ゾ" }, {

					"da", "ダ" }, {
					"ji", "ヂ" }, {
					"di", "ヂ" }, {
					"zu", "ヅ" }, {
					"du", "ヅ" }, {
					"de", "デ" }, {
					"do", "ド" }, {

					"ba", "バ" }, {
					"bi", "ビ" }, {
					"bu", "ブ" }, {
					"be", "ベ" }, {
					"bo", "ボ" }, {

					"pa", "パ" }, {
					"pi", "ピ" }, {
					"pu", "プ" }, {
					"pe", "ペ" }, {
					"po", "ポ" }, {

					"ka", "カ" }, {
					"ki", "キ" }, {
					"ku", "ク" }, {
					"ke", "ケ" }, {
					"ko", "コ" }, {

					"sa", "サ" }, {
					"shi", "シ" }, {
					"si", "シ" }, {
					"su", "ス" }, {
					"se", "セ" }, {
					"so", "ソ" }, {
					"ta", "タ" }, {
					"chi", "チ" }, {
					"ti", "チ" }, {
					"tsu", "ツ" }, {
					"tu", "ツ" }, {
					"te", "テ" }, {
					"to", "ト" }, {

					"na", "ナ" }, {
					"ni", "ニ" }, {
					"nu", "ヌ" }, {
					"ne", "ネ" }, {
					"no", "ノ" }, {

					"ha", "ハ" }, {
					"hi", "ヒ" }, {
					"fu", "フ" }, {
					"hu", "フ" }, {
					"he", "ヘ" }, {
					"ho", "ホ" }, {

					"ma", "マ" }, {
					"mi", "ミ" }, {
					"mu", "ム" }, {
					"me", "メ" }, {
					"mo", "モ" }, {

					"ya", "ヤ" }, {
					"yu", "ユ" }, {
					"yo", "ヨ" }, {

					"ra", "ラ" }, {
					"ri", "リ" }, {
					"ru", "ル" }, {
					"re", "レ" }, {
					"ro", "ロ" }, {

					"wa", "ワ" }, {
					"wo", "ヲ" }, {


					"a", "ア" }, {
					"i", "イ" }, {
					"u", "ウ" }, {
					"e", "エ" }, {
					"o", "オ" }, {

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

	public static String convertPlus(String arg, int from, int to) { 	// TODO: Seperate Threading TODO: use char[] instead of Strings; performance check
		String result = "";												// TODO: Seperated Length Arrays
		int index = 0;													// TODO: Everything more seperated
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
