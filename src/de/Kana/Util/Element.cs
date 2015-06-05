namespace Kana {
	public class Element {

		public Syllable Syllable { get; set; }

        public bool Eow { get; set; }

        public Vocable Vocable { get; set; }

        public Element (Syllable syllable) {
			Syllable = syllable;
		}
	}
}

