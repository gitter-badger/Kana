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

            graph.Add(v1);
            graph.Add(v2);
            graph.Add(v3);
            graph.Add(v4);
            graph.Add(v5);

            foreach (Vocable v in graph.getContent())
                System.Console.WriteLine(v);
        }
	}
}

