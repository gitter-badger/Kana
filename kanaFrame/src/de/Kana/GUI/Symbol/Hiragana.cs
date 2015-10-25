using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace KanaFrame
{
    public class Hiragana
    {
        private static LinkedList<HiraSyllable> alphabet { get; }
        public static IEnumerable<HiraSyllable> Alphabet { get { return alphabet; } }
        static Hiragana()
        {
            alphabet = new LinkedList<HiraSyllable>();
            alphabet.AddLast(new HiraSyllable("あ", "a", SymbolFlags.None));
            alphabet.AddLast(new HiraSyllable("い", "i", SymbolFlags.None));
            alphabet.AddLast(new HiraSyllable("う", "u", SymbolFlags.None));
            alphabet.AddLast(new HiraSyllable("え", "e", SymbolFlags.None));
            alphabet.AddLast(new HiraSyllable("お", "o", SymbolFlags.None));
            alphabet.AddLast(new HiraSyllable("か", "ka", SymbolFlags.None));
            alphabet.AddLast(new HiraSyllable("き", "ki", SymbolFlags.None));
            alphabet.AddLast(new HiraSyllable("く", "ku", SymbolFlags.None));
            alphabet.AddLast(new HiraSyllable("け", "ke", SymbolFlags.None));
            alphabet.AddLast(new HiraSyllable("こ", "ko", SymbolFlags.None));
            alphabet.AddLast(new HiraSyllable("さ", "sa", SymbolFlags.None));
            alphabet.AddLast(new HiraSyllable("し", "shi", SymbolFlags.None));
            alphabet.AddLast(new HiraSyllable("す", "su", SymbolFlags.None));
            alphabet.AddLast(new HiraSyllable("せ", "se", SymbolFlags.None));
            alphabet.AddLast(new HiraSyllable("そ", "so", SymbolFlags.None));
            alphabet.AddLast(new HiraSyllable("た", "ta", SymbolFlags.None));
            alphabet.AddLast(new HiraSyllable("ち", "chi", SymbolFlags.None));
            alphabet.AddLast(new HiraSyllable("つ", "tsu", SymbolFlags.None));
            alphabet.AddLast(new HiraSyllable("て", "te", SymbolFlags.None));
            alphabet.AddLast(new HiraSyllable("と", "to", SymbolFlags.None));
            alphabet.AddLast(new HiraSyllable("な", "na", SymbolFlags.None));
            alphabet.AddLast(new HiraSyllable("に", "ni", SymbolFlags.None));
            alphabet.AddLast(new HiraSyllable("ぬ", "nu", SymbolFlags.None));
            alphabet.AddLast(new HiraSyllable("ね", "ne", SymbolFlags.None));
            alphabet.AddLast(new HiraSyllable("の", "no", SymbolFlags.None));
            alphabet.AddLast(new HiraSyllable("は", "ha", SymbolFlags.None));
            alphabet.AddLast(new HiraSyllable("ひ", "hi", SymbolFlags.None));
            alphabet.AddLast(new HiraSyllable("ふ", "hu", SymbolFlags.None));
            alphabet.AddLast(new HiraSyllable("へ", "he", SymbolFlags.None));
            alphabet.AddLast(new HiraSyllable("ほ", "ho", SymbolFlags.None));
            alphabet.AddLast(new HiraSyllable("ま", "ma", SymbolFlags.None));
            alphabet.AddLast(new HiraSyllable("み", "mi", SymbolFlags.None));
            alphabet.AddLast(new HiraSyllable("む", "mu", SymbolFlags.None));
            alphabet.AddLast(new HiraSyllable("め", "me", SymbolFlags.None));
            alphabet.AddLast(new HiraSyllable("も", "mo", SymbolFlags.None));
            alphabet.AddLast(new HiraSyllable("や", "ya", SymbolFlags.None));
            alphabet.AddLast(new HiraSyllable("ゆ", "yu", SymbolFlags.None));
            alphabet.AddLast(new HiraSyllable("よ", "yo", SymbolFlags.None));
            alphabet.AddLast(new HiraSyllable("ら", "ra", SymbolFlags.None));
            alphabet.AddLast(new HiraSyllable("り", "ri", SymbolFlags.None));
            alphabet.AddLast(new HiraSyllable("る", "ru", SymbolFlags.None));
            alphabet.AddLast(new HiraSyllable("れ", "re", SymbolFlags.None));
            alphabet.AddLast(new HiraSyllable("ろ", "ro", SymbolFlags.None));
            alphabet.AddLast(new HiraSyllable("わ", "wa", SymbolFlags.None));
            alphabet.AddLast(new HiraSyllable("ん", "n", SymbolFlags.None));
            alphabet.AddLast(new HiraSyllable("を", "wo", SymbolFlags.None));
        }
    }
}
