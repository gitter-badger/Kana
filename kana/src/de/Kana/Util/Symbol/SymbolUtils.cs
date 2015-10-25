using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Kana.Symbol
{

    public class UserProgress
    {
        private HashSet<Symbol> symbols;
        public Dictionary<string, Progress> Progress { get; private set; }
        public UserProgress(HashSet<Symbol> symbols)
        {
            Progress = new Dictionary<string, Progress>();
            this.symbols = symbols;
            foreach (var symbol in symbols)
            {
                Progress.Add(symbol.ToString(), new Progress(symbol));
            }
        }

        public Progress this[string str]
        {
            get { return Progress[str]; }
            protected set { Progress[str] = value; }
        }

        public Progress get(string str) { return Progress[str]; }
    }



    public class SymbolUtils
    {
        public static HashSet<Symbol> Hiragana { get; }

        static SymbolUtils()
        {
            Hiragana = new HashSet<Symbol>();
            Hiragana.Add(new Symbol("あ", Alphabet.HIRAGANA, SymbolFlags.None));
            Hiragana.Add(new Symbol("い", Alphabet.HIRAGANA, SymbolFlags.None));
            Hiragana.Add(new Symbol("う", Alphabet.HIRAGANA, SymbolFlags.None));
            Hiragana.Add(new Symbol("え", Alphabet.HIRAGANA, SymbolFlags.None));
            Hiragana.Add(new Symbol("お", Alphabet.HIRAGANA, SymbolFlags.None));
            Hiragana.Add(new Symbol("か", Alphabet.HIRAGANA, SymbolFlags.None));
            Hiragana.Add(new Symbol("き", Alphabet.HIRAGANA, SymbolFlags.None));
            Hiragana.Add(new Symbol("く", Alphabet.HIRAGANA, SymbolFlags.None));
            Hiragana.Add(new Symbol("け", Alphabet.HIRAGANA, SymbolFlags.None));
            Hiragana.Add(new Symbol("こ", Alphabet.HIRAGANA, SymbolFlags.None));
            Hiragana.Add(new Symbol("さ", Alphabet.HIRAGANA, SymbolFlags.None));
            Hiragana.Add(new Symbol("し", Alphabet.HIRAGANA, SymbolFlags.None));
            Hiragana.Add(new Symbol("す", Alphabet.HIRAGANA, SymbolFlags.None));
            Hiragana.Add(new Symbol("せ", Alphabet.HIRAGANA, SymbolFlags.None));
            Hiragana.Add(new Symbol("そ", Alphabet.HIRAGANA, SymbolFlags.None));
            Hiragana.Add(new Symbol("た", Alphabet.HIRAGANA, SymbolFlags.None));
            Hiragana.Add(new Symbol("ち", Alphabet.HIRAGANA, SymbolFlags.None));
            Hiragana.Add(new Symbol("つ", Alphabet.HIRAGANA, SymbolFlags.None));
            Hiragana.Add(new Symbol("て", Alphabet.HIRAGANA, SymbolFlags.None));
            Hiragana.Add(new Symbol("と", Alphabet.HIRAGANA, SymbolFlags.None));
            Hiragana.Add(new Symbol("な", Alphabet.HIRAGANA, SymbolFlags.None));
            Hiragana.Add(new Symbol("に", Alphabet.HIRAGANA, SymbolFlags.None));
            Hiragana.Add(new Symbol("ぬ", Alphabet.HIRAGANA, SymbolFlags.None));
            Hiragana.Add(new Symbol("ね", Alphabet.HIRAGANA, SymbolFlags.None));
            Hiragana.Add(new Symbol("の", Alphabet.HIRAGANA, SymbolFlags.None));
            Hiragana.Add(new Symbol("は", Alphabet.HIRAGANA, SymbolFlags.None));
            Hiragana.Add(new Symbol("ひ", Alphabet.HIRAGANA, SymbolFlags.None));
            Hiragana.Add(new Symbol("ふ", Alphabet.HIRAGANA, SymbolFlags.None));
            Hiragana.Add(new Symbol("へ", Alphabet.HIRAGANA, SymbolFlags.None));
            Hiragana.Add(new Symbol("ほ", Alphabet.HIRAGANA, SymbolFlags.None));
            Hiragana.Add(new Symbol("ま", Alphabet.HIRAGANA, SymbolFlags.None));
            Hiragana.Add(new Symbol("み", Alphabet.HIRAGANA, SymbolFlags.None));
            Hiragana.Add(new Symbol("む", Alphabet.HIRAGANA, SymbolFlags.None));
            Hiragana.Add(new Symbol("め", Alphabet.HIRAGANA, SymbolFlags.None));
            Hiragana.Add(new Symbol("も", Alphabet.HIRAGANA, SymbolFlags.None));
            Hiragana.Add(new Symbol("や", Alphabet.HIRAGANA, SymbolFlags.None));
            Hiragana.Add(new Symbol("ゆ", Alphabet.HIRAGANA, SymbolFlags.None));
            Hiragana.Add(new Symbol("よ", Alphabet.HIRAGANA, SymbolFlags.None));
            Hiragana.Add(new Symbol("ら", Alphabet.HIRAGANA, SymbolFlags.None));
            Hiragana.Add(new Symbol("り", Alphabet.HIRAGANA, SymbolFlags.None));
            Hiragana.Add(new Symbol("る", Alphabet.HIRAGANA, SymbolFlags.None));
            Hiragana.Add(new Symbol("れ", Alphabet.HIRAGANA, SymbolFlags.None));
            Hiragana.Add(new Symbol("ろ", Alphabet.HIRAGANA, SymbolFlags.None));
            Hiragana.Add(new Symbol("わ", Alphabet.HIRAGANA, SymbolFlags.None));
            Hiragana.Add(new Symbol("ん", Alphabet.HIRAGANA, SymbolFlags.None));
            Hiragana.Add(new Symbol("を", Alphabet.HIRAGANA, SymbolFlags.None));
        }
    }

    public class Progress
    {
        public event EventHandler OnChange;
        private bool enabled;
        public bool Enabled { get { return enabled; } set { enabled = value; Changed(); } }
        public Symbol Symbol { get; protected set; }
        public int Streak { get; protected set; }
        public Progress(Symbol symbol)
        {
            this.Symbol = symbol;
            Streak = 0;
        }
        public void Reset() { Streak = 0; Changed(); }
        public void Correct() { Streak = Math.Max(1, ++Streak); Changed(); }
        public void Wrong() { Streak = Math.Min(-1, --Streak); Changed(); }
        private void Changed() { if (OnChange != null) OnChange(this, new EventArgs()); }
    }

    public class Symbol : Syllable
    {
        SymbolFlags flags;
        public Symbol(string syllable, Alphabet alphabet, SymbolFlags flags) : base(syllable, alphabet)
        {
            this.flags = flags;
        }
    }

    [Flags]
    public enum SymbolFlags : short
    {
        None = 0,
        Mark = 1,
        Combined = 2,
    };
}
