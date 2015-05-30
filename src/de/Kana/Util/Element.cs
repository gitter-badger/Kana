using System;
using System.Collections.Generic;

namespace Kana {
	public class Element {
		private Syllable syllable;
        private Vocable vocable;
        private bool eow;

		public Syllable Syllable {
			get {
				return syllable;
			}
			set {
				syllable = value;
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

        public Vocable Vocable {
            get {
                return vocable;
            }

            set {
                vocable = value;
            }
        }

        public Element (Syllable syllable) {
			this.syllable = syllable;
		}
	}
}

