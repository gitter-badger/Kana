using System;
using System.Collections;
using System.Collections.Generic;

namespace Kana {
	public struct Vocable : IEnumerable {
		public class VocableEnumerator : IEnumerator<Syllable> {
			private Vocable vocable;
			private int curIndex;
			private Syllable curSyllable;

			public Syllable Current {
				get {
					return curSyllable;
				}
			}

			object IEnumerator.Current {
				get {
					return Current;
				}
			}

			public VocableEnumerator (Vocable vocable) {
				this.vocable = vocable;
				curIndex = -1;
				curSyllable = default (Syllable);
			}

			public bool MoveNext () {
				if (++curIndex >= vocable.Word.Count)
					return false;
				else
					curSyllable = vocable.Word [curIndex];
				return true;
			}

			public void Reset () {
				curIndex = -1;
			}

			void IDisposable.Dispose () {
			}
		}

        public List<Syllable> Word { get; set; }

        public string Romaji { get; set; }

        public LinkedList<Vocable> Alternatives { get; set; }

        public LinkedList<string> Kanji { get; set; }

        public LinkedList<string> EnWords { get; set; }

        public LinkedList<string> DeWords { get; set; }

        public HashSet<Enum> Flags { get; set; }
        
        public Vocable (List<Syllable> syllables) {
            Word = syllables ?? new List<Syllable>();
            Romaji = "";
            Alternatives = new LinkedList<Vocable>();
            Kanji = new LinkedList<string>();
            EnWords = new LinkedList<string>();
            DeWords = new LinkedList<string>();
            Flags = new HashSet<Enum>();
		}

		IEnumerator IEnumerable.GetEnumerator () {
			return (IEnumerator)GetEnumerator ();
		}

		public VocableEnumerator GetEnumerator () {
			return new VocableEnumerator (this);
		}

        public override string ToString() {
            string voc = "";
            foreach (Syllable syl in Word)
                voc += syl;
            return voc;
        }
    }
}

