using System;
using System.Collections.Generic;

namespace Kana {
	public struct Syllable {
		private string characters;
		private Alphabet alphabet;

		public string Characters {
			get {
				return characters;
			}
			private set {
				characters = value;
			}
		}

		public Alphabet Alphabet {
			get {
				return alphabet;
			}
			private set {
				alphabet = value;
			}
		}

		public Syllable (string syllable, Alphabet alphabet) {
			this.characters = syllable;
			this.alphabet = alphabet;
		}

        public override string ToString() {
            return Characters;
        }
    }
}

