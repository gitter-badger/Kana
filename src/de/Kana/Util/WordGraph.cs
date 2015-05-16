using System;
using System.Collections.Specialized;

namespace Kana {
	public class WordGraph {
		private ListDictionary root;
		private ElementComparer comparer;

		public ListDictionary Root {
			get {
				return root;
			}
			private set {
				root = value;
			}
		}

		public ElementComparer Comparer {
			get {
				return comparer;
			}
			private set {
				comparer = value;
			}
		}

		public WordGraph () {
			comparer = new ElementComparer ();
			root = new ListDictionary (Comparer);
		}

		public void Add (Vocable word) {
			ListDictionary currentSyllableSet = root;
			Element elem = default (Element);
			foreach (Syllable syl in word) {
				elem = new Element (syl);
				if (!currentSyllableSet.Contains (elem))
					currentSyllableSet [elem] = new ListDictionary (Comparer);
				currentSyllableSet = (ListDictionary)currentSyllableSet [elem];
			}
			elem.Eow = true;
		}
	}
}

