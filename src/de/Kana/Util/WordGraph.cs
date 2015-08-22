using System.Collections.Generic;
using System.Collections.Specialized;
using System.Linq;

namespace Kana.src.de.Kana.Util {
	public class WordGraph : IKanaDictionary {

		public ListDictionary Root { get; private set; }

		private ElementComparer Comparer { get; }

		public WordGraph () {
			Comparer = new ElementComparer ();
			Root = new ListDictionary (Comparer);
		}

        public void Add(Vocable voc) {
			ListDictionary currentSyllableSet = Root;
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

        public LinkedList<Vocable> getContent () {
            LinkedList<Vocable> vocabels = new LinkedList<Vocable>();
            traverse(vocabels, Root);
            return vocabels;
        }
        
        private void traverse (LinkedList<Vocable> vocabels, ListDictionary syllableSet) {
            if (syllableSet == null)
                return;

            foreach (Element elem in syllableSet.Keys) {
                if (elem.Eow && ((ListDictionary)syllableSet[elem]).Keys.Count != 0)
                    vocabels.AddLast(elem.Vocable);

                traverse(vocabels, syllableSet[elem] as ListDictionary);

                if (elem.Eow && ((ListDictionary)syllableSet[elem]).Keys.Count == 0)
                    vocabels.AddLast(elem.Vocable);
            }
        }
	}
}

