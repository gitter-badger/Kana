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

		private List<Syllable> word;
        // Todo: Define Word-Modul

		public List<Syllable> Word {
			get {
				return word;
			}
			private set {
				word = value;
			}
		}

		public Vocable (List<Syllable> syllables) {
			word = syllables;
		}

		IEnumerator IEnumerable.GetEnumerator () {
			return (IEnumerator)GetEnumerator ();
		}

		public VocableEnumerator GetEnumerator () {
			return new VocableEnumerator (this);
		}

        public override string ToString() {
            string voc = "";
            foreach (Syllable syl in word)
                voc += syl;
            return voc;
        }
    }
}

