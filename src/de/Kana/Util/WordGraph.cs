using System;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.Linq;

namespace Kana.src.de.Kana.Util {
	public class WordGraph : IKanaDictionary {
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

		private ElementComparer Comparer {
			get {
				return comparer;
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

        public LinkedList<Vocable> getContent () {
            List<Syllable> traverseTmp = new List<Syllable>();
            LinkedList<Vocable> vocabels = new LinkedList<Vocable>();

            traverse(traverseTmp, vocabels, root, 0, 0);

            return vocabels;
        }

        private void traverse (List<Syllable> traverseTmp, LinkedList<Vocable> vocabels, ListDictionary syllableSet, int level, int junctionLevel) {
            if (syllableSet == null)
                return;

            foreach (Element elem in syllableSet.Keys) { 
                traverseTmp.Add(elem.Syllable);

                traverse(traverseTmp, vocabels, syllableSet[elem] as ListDictionary, ++level, (syllableSet.Keys.Count > 1)? junctionLevel = level - 1 : junctionLevel);

                if (elem.Eow) {
                    vocabels.AddLast(new Vocable(traverseTmp.ToList()));
                    traverseTmp.RemoveRange(junctionLevel, (vocabels.Last != null) ? vocabels.Last.Value.Word.Count - junctionLevel : 0);
                }

            }
        }
	}
}

