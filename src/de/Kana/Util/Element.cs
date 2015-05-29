using System;
using System.Collections.Generic;

namespace Kana {
	public class Element {
		private Syllable syllable;
        private List<Element> alternatives;
        private List<string> kanji;
        private List<string> enWords;
        private List<string> deWords;
        private HashSet<Enum> flags;
        private bool eow;

		public Syllable Syllable {
			get {
				return syllable;
			}
			set {
				syllable = value;
			}
		}

        public List<Element> Alternatives {
            get {
                return alternatives;
            }

            set {
                alternatives = value;
            }
        }

        public List<string> Kanji {
            get {
                return kanji;
            }

            set {
                kanji = value;
            }
        }

        public List<string> EnWords {
            get {
                return enWords;
            }

            set {
                enWords = value;
            }
        }

        public List<string> DeWords {
            get {
                return deWords;
            }

            set {
                deWords = value;
            }
        }

        public HashSet<Enum> Flags {
            get {
                return flags;
            }

            set {
                flags = value;
            }
        }

        public bool Eow {
            get {
                return eow;
            }

            set {
                eow = value;
            }
        }

        public Element (Syllable syllable) {
			this.syllable = syllable;
		}
	}
}

