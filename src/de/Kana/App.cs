using System;
using System.Collections.Generic;

using Kana.src.de.Kana.Util;

namespace Kana
{
	public class App
	{
		public static void Main(string[] args) {
            WordGraph graph = new WordGraph();

            // Word 1
            List<Syllable> word = new List<Syllable>();
            word.Add(new Syllable("ka", Alphabet.HIRAGANA));
            word.Add(new Syllable("ta", Alphabet.HIRAGANA));
            word.Add(new Syllable("ka", Alphabet.HIRAGANA));
            word.Add(new Syllable("na", Alphabet.HIRAGANA));
            Vocable v1 = new Vocable(word);

            // Word 2
            word = new List<Syllable>();
            word.Add(new Syllable("ka", Alphabet.HIRAGANA));
            word.Add(new Syllable("ta", Alphabet.HIRAGANA));
            word.Add(new Syllable("fu", Alphabet.HIRAGANA));
            word.Add(new Syllable("gi", Alphabet.HIRAGANA));
            Vocable v2 = new Vocable(word);

            // Word 3
            word = new List<Syllable>();
            word.Add(new Syllable("ka", Alphabet.HIRAGANA));
            word.Add(new Syllable("la", Alphabet.HIRAGANA));
            word.Add(new Syllable("ma", Alphabet.HIRAGANA));
            word.Add(new Syllable("ri", Alphabet.HIRAGANA));
            Vocable v3 = new Vocable(word);

            // Word 4
            word = new List<Syllable>();
            word.Add(new Syllable("na", Alphabet.HIRAGANA));
            word.Add(new Syllable("ga", Alphabet.HIRAGANA));
            word.Add(new Syllable("sa", Alphabet.HIRAGANA));
            word.Add(new Syllable("ki", Alphabet.HIRAGANA));
            Vocable v4 = new Vocable(word);

            // Word 4
            word = new List<Syllable>();
            word.Add(new Syllable("ka", Alphabet.HIRAGANA));
            word.Add(new Syllable("ta", Alphabet.HIRAGANA));
            word.Add(new Syllable("ka", Alphabet.HIRAGANA));
            word.Add(new Syllable("ro", Alphabet.HIRAGANA));
            word.Add(new Syllable("go", Alphabet.HIRAGANA));
            Vocable v5 = new Vocable(word);

            // Word 5
            word = new List<Syllable>();
            word.Add(new Syllable("ka", Alphabet.HIRAGANA));
            word.Add(new Syllable("ta", Alphabet.HIRAGANA));
            Vocable v6 = new Vocable(word);

            graph.Add(v1, new List<string>(), new List<string>() { "Test", "Test1" }, new List<string>());
            graph.Add(v2, new List<string>(), new List<string>() { "Test", "Test2" }, new List<string>());
            graph.Add(v3, new List<string>(), new List<string>() { "Test", "Test3" }, new List<string>());
            graph.Add(v4, new List<string>(), new List<string>() { "Test", "Test4" }, new List<string>());
            graph.Add(v5, new List<string>(), new List<string>() { "Test", "Test5" }, new List<string>());
            graph.Add(v6, new List<string>(), new List<string>() { "Test", "Test6" }, new List<string>());

            foreach (Vocable v in graph.getContent())
                System.Console.WriteLine(v);
        }
	}
}

