using System;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.Linq;

namespace Kana.src.de.Kana.Util {
	public class WordGraph : IKanaDictionary {
        private int junctionLevel = 0;

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

        public void Add(Vocable voc) {
			ListDictionary currentSyllableSet = root;
			Element elem = default (Element);
			foreach (Syllable syl in voc) {
                if (!currentSyllableSet.Contains(new Element(syl)))
                    elem = new Element(syl);
                else
                    elem = currentSyllableSet.Keys.Cast<Element>().FirstOrDefault(x => x.Syllable.Characters.Equals(syl.Characters));
				if (!currentSyllableSet.Contains (elem))
					currentSyllableSet [elem] = new ListDictionary (Comparer);
				currentSyllableSet = (ListDictionary)currentSyllableSet [elem];
			}
            elem.Vocable = voc;
            elem.Eow = true;
		}

        public List<Vocable> getContent () {
            List<Vocable> vocabels = new List<Vocable>();

            traverse(vocabels, root);

            return vocabels;
        }
        
        private void traverse (List<Vocable> vocabels, ListDictionary syllableSet) {
            if (syllableSet == null)
                return;

            foreach (Element elem in syllableSet.Keys) {
                if (elem.Eow && ((ListDictionary)syllableSet[elem]).Keys.Count != 0)
                    vocabels.Add(elem.Vocable);

                traverse(vocabels, syllableSet[elem] as ListDictionary);

                if (elem.Eow && ((ListDictionary)syllableSet[elem]).Keys.Count == 0)
                    vocabels.Add(elem.Vocable);
            }
        }
	}
}

