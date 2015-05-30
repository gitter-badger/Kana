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

        public void Add(Vocable word) {
			ListDictionary currentSyllableSet = root;
			Element elem = default (Element);
			foreach (Syllable syl in word) {
                if (!currentSyllableSet.Contains(new Element(syl)))
                    elem = new Element(syl);
                else
                    elem = currentSyllableSet.Keys.Cast<Element>().FirstOrDefault(x => x.Syllable.Characters.Equals(syl.Characters));
				if (!currentSyllableSet.Contains (elem))
					currentSyllableSet [elem] = new ListDictionary (Comparer);
				currentSyllableSet = (ListDictionary)currentSyllableSet [elem];
			}
            elem.Vocable = word;
            elem.Eow = true;
		}

        public LinkedList<Vocable> getContent () {
            List<Syllable> traverseTmp = new List<Syllable>();
            LinkedList<Vocable> vocabels = new LinkedList<Vocable>();

            traverse(traverseTmp, vocabels, root, 0);

            return vocabels;
        }
        
        private void traverse (List<Syllable> traverseTmp, LinkedList<Vocable> vocabels, ListDictionary syllableSet, int level) {
            if (syllableSet == null)
                return;


            foreach (Element elem in syllableSet.Keys) {
                traverseTmp.Add(elem.Syllable);

                if (elem.Eow && ((ListDictionary)syllableSet[elem]).Keys.Count != 0)
                    vocabels.AddLast(new Vocable(traverseTmp.ToList()));

                traverse(traverseTmp, vocabels, syllableSet[elem] as ListDictionary, ++level);

                if (elem.Eow && ((ListDictionary)syllableSet[elem]).Keys.Count == 0)
                    vocabels.AddLast(new Vocable(traverseTmp.ToList()));
                traverseTmp.Remove(traverseTmp.Last());
            }
        }
	}
}

