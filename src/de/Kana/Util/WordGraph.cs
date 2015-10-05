using System.Collections.Generic;
using System.Collections.Specialized;
using System.Linq;

namespace Kana.src.de.Kana.Util {
	public class WordGraph : IKanaDictionary {

		public ListDictionary Root { get; private set; }

		private HiraganaComparer Comparer { get; }

		public WordGraph () {
			Comparer = new HiraganaComparer ();
			Root = new ListDictionary (Comparer);
		}

        public void Add(Vocable voc) {
			ListDictionary currentSyllableSet = Root;
			Element elem = default (Element);
			foreach (Syllable syl in voc) {
                if (!currentSyllableSet.Contains(syl))
                    elem = new Element(syl);
                else
                    elem = currentSyllableSet.Keys.Cast<Element>().FirstOrDefault(x => x.Syllable.Characters.Equals(syl.Characters));
				if (!currentSyllableSet.Contains (elem.Syllable))
					currentSyllableSet [elem] = new ListDictionary (Comparer);
				currentSyllableSet = (ListDictionary)currentSyllableSet [elem.Syllable];
			}
            elem.Vocable = voc;
            elem.Eow = true;
		}

        public LinkedList<Vocable> getContent () {
            LinkedList<Vocable> vocabels = new LinkedList<Vocable>();
            traverse(vocabels, Root);
            return vocabels;
        }

        public Vocable getVocable(string searchString) {
            ListDictionary curLevel = Root;
            foreach (char elem in searchString) {
                if (elem.Equals(searchString.Last()))
                    break;
                curLevel = (ListDictionary)curLevel[new Syllable(char.ToString(elem), Alphabet.HIRAGANA)];
            }
            Element[] tmp = new Element[curLevel.Keys.Count];
            curLevel.Keys.CopyTo(tmp, 0);
            return (from key in tmp where key.Syllable.Characters.Equals(char.ToString(searchString.Last())) select key).ToList().First().Vocable;
        }
        
        private void traverse (LinkedList<Vocable> vocabels, ListDictionary syllableSet) {
            if (syllableSet == null)
                return;

            foreach (Element elem in syllableSet.Keys) {
                if (elem.Eow && ((ListDictionary)syllableSet[elem.Syllable]).Keys.Count != 0)
                    vocabels.AddLast(elem.Vocable);

                traverse(vocabels, syllableSet[elem.Syllable] as ListDictionary);

                if (elem.Eow && ((ListDictionary)syllableSet[elem.Syllable]).Keys.Count == 0)
                    vocabels.AddLast(elem.Vocable);
            }
        }
	}
}

