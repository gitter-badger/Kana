using NUnit.Framework;
using System;
using System.Linq;
using System.Collections;
using System.Collections.Generic;
using Kana.src.de.Kana.Util;

namespace Kana {
	[TestFixture ()]
	public class TestWordGraph {
		private WordGraph wordGraph;
		private Vocable v1, v2, v3, v4;

		[SetUp]
		public void SetUp() {
			wordGraph = new WordGraph ();

			// Word 1
			List<Syllable> word = new List<Syllable>();
			word.Add (new Syllable ("ka", Alphabet.HIRAGANA));
			word.Add (new Syllable ("ta", Alphabet.HIRAGANA));
			word.Add (new Syllable ("ka", Alphabet.HIRAGANA));
			word.Add (new Syllable ("na", Alphabet.HIRAGANA));
			v1 = new Vocable (word);

			// Word 2
			word = new List<Syllable>();
			word.Add (new Syllable ("ka", Alphabet.HIRAGANA));
			word.Add (new Syllable ("ta", Alphabet.HIRAGANA));
			word.Add (new Syllable ("fu", Alphabet.HIRAGANA));
			word.Add (new Syllable ("gi", Alphabet.HIRAGANA));
			v2 = new Vocable (word);

			// Word 3
			word = new List<Syllable>();
			word.Add (new Syllable ("ka", Alphabet.HIRAGANA));
			word.Add (new Syllable ("la", Alphabet.HIRAGANA));
			word.Add (new Syllable ("ma", Alphabet.HIRAGANA));
			word.Add (new Syllable ("ri", Alphabet.HIRAGANA));
			v3 = new Vocable (word);

			// Word 4
			word = new List<Syllable>();
			word.Add (new Syllable ("na", Alphabet.HIRAGANA));
			word.Add (new Syllable ("ga", Alphabet.HIRAGANA));
			word.Add (new Syllable ("sa", Alphabet.HIRAGANA));
			word.Add (new Syllable ("ki", Alphabet.HIRAGANA));
			v4 = new Vocable (word);
		}

		[Test ()]
		public void TestTrie () {
			wordGraph.Add (v1);
			wordGraph.Add (v2);
			wordGraph.Add (v3);
			wordGraph.Add (v4);

            Syllable[] syllables = (from elem in wordGraph.Root.Keys.Cast<Element>() select elem.Syllable).ToArray();

            Assert.Contains(v1.Word[0], syllables, "Test 1");
            Assert.Contains(v4.Word[0], syllables, "Test 2");
            Assert.AreEqual(syllables.Length, 2, "Test 3");

            
        }
	}
}

