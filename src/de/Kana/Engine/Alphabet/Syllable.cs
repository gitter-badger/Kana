namespace Kana {
	public class Syllable {

		public string Characters { get; private set; }

		public Alphabet Alphabet { get; private set; }

		public Syllable (string syllable, Alphabet alphabet) {
			Characters = syllable;
			Alphabet = alphabet;
		}

        public override string ToString() {
            return Characters;
        }
    }
}

