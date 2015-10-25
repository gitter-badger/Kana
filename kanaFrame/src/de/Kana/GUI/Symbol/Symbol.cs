using Kana;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace KanaFrame
{
    public class HiraSyllable : Syllable
    {
        public SymbolFlags Flags { get; private set; }
        public HiraSyllable SymbolBase { get; private set; }
        public string Roman { get; private set; }

        public HiraSyllable(string syllable, HiraSyllable symbolBase, string roman, SymbolFlags flags) : base(syllable, Alphabet.HIRAGANA)
        {
            Flags = flags;
            SymbolBase = symbolBase;
            Roman = roman;
        }

        public HiraSyllable(string syllable, string roman, SymbolFlags flags) : this(syllable, null, roman, flags) { }

        public override string ToString()
        {
            return (SymbolBase != null) ? SymbolBase.Characters : Characters;
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
