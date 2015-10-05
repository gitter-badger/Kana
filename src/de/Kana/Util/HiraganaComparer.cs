using System;
using System.Collections;

namespace Kana {
    public class HiraganaComparer : IComparer {
        public int Compare(Object elem1, Object elem2) {
            return ((Element)elem1).Syllable.Characters.CompareTo((elem2 as Syllable)?.Characters ?? ((Element)elem2).Syllable.Characters);
        }
    }
}

