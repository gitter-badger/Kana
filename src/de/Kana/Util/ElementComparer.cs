using System;
using System.Collections;

namespace Kana {
	public class ElementComparer : IComparer {
		public int Compare (Object elem1, Object elem2) { 
			return ((Element)elem1).Syllable.Characters.CompareTo (((Element)elem2).Syllable.Characters);
		}
	}
}

