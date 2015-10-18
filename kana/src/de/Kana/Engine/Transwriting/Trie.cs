using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Kana.Transwriting
{
    public class Trie : TrieNode, ITrie
    {

        private static Trie TTrie { get; }

        static Trie()
        {
            TTrie = new Trie();
            TTrie.Add(".", "。");
            TTrie.Add(",", "、");
            TTrie.Add(" ", "　");
            TTrie.Add("-", "ー");
            TTrie.Add("ssha", "っしゃ");
            TTrie.Add("ssya", "っしゃ");
            TTrie.Add("sshu", "っしゅ");
            TTrie.Add("ssyu", "っしゅ");
            TTrie.Add("ssho", "っしょ");
            TTrie.Add("ssyo", "っしょ");
            TTrie.Add("ccha", "っちゃ");
            TTrie.Add("ttya", "っちゃ");
            TTrie.Add("cchu", "っちゅ");
            TTrie.Add("ttyu", "っちゅ");
            TTrie.Add("ccho", "っちょ");
            TTrie.Add("ttyo", "っちょ");
            TTrie.Add("ppya", "っぴゃ");
            TTrie.Add("ppyu", "っぴゅ");
            TTrie.Add("ppyo", "っぴょ");
            TTrie.Add("kkya", "っきゃ");
            TTrie.Add("kkyi", "っきぃ");
            TTrie.Add("kkyu", "っきゅ");
            TTrie.Add("kkye", "っきぇ");
            TTrie.Add("kkyo", "っきょ");
            TTrie.Add("tta", "った");
            TTrie.Add("cchi", "っち");
            TTrie.Add("tti", "っち");
            TTrie.Add("ttsu", "っつ");
            TTrie.Add("ttu", "っつ");
            TTrie.Add("tte", "って");
            TTrie.Add("tto", "っと");
            TTrie.Add("ppa", "っぱ");
            TTrie.Add("ppi", "っぴ");
            TTrie.Add("ppu", "っぷ");
            TTrie.Add("ppe", "っぺ");
            TTrie.Add("ppo", "っぽ");
            TTrie.Add("kka", "っか");
            TTrie.Add("kki", "っき");
            TTrie.Add("kku", "っく");
            TTrie.Add("kke", "っけ");
            TTrie.Add("kko", "っこ");
            TTrie.Add("ssa", "っさ");
            TTrie.Add("sshi", "っし");
            TTrie.Add("ssi", "っし");
            TTrie.Add("ssu", "っす");
            TTrie.Add("sse", "っせ");
            TTrie.Add("sso", "っそ");
            TTrie.Add("kya", "きゃ");
            TTrie.Add("kyu", "きゅ");
            TTrie.Add("kyo", "きょ");
            TTrie.Add("gya", "ぎゃ");
            TTrie.Add("gyu", "ぎゅ");
            TTrie.Add("gyo", "ぎょ");
            TTrie.Add("sha", "しゃ");
            TTrie.Add("sya", "しゃ");
            TTrie.Add("shu", "しゅ");
            TTrie.Add("syu", "しゅ");
            TTrie.Add("sho", "しょ");
            TTrie.Add("syo", "しょ");
            TTrie.Add("ja", "じゃ");
            TTrie.Add("zya", "じゃ");
            TTrie.Add("ju", "じゅ");
            TTrie.Add("zyu", "じゅ");
            TTrie.Add("jo", "じょ");
            TTrie.Add("zyo", "じょ");
            TTrie.Add("cha", "ちゃ");
            TTrie.Add("tya", "ちゃ");
            TTrie.Add("chu", "ちゅ");
            TTrie.Add("tyu", "ちゅ");
            TTrie.Add("cho", "ちょ");
            TTrie.Add("tyo", "ちょ");
            TTrie.Add("nya", "にゃ");
            TTrie.Add("nyu", "にゅ");
            TTrie.Add("nyo", "にょ");
            TTrie.Add("hya", "ひゃ");
            TTrie.Add("hyu", "ひゅ");
            TTrie.Add("hyo", "ひょ");
            TTrie.Add("bya", "びゃ");
            TTrie.Add("byu", "びゅ");
            TTrie.Add("byo", "びょ");
            TTrie.Add("pya", "ぴゃ");
            TTrie.Add("pyu", "ぴゅ");
            TTrie.Add("pyo", "ぴょ");
            TTrie.Add("mya", "みゃ");
            TTrie.Add("myu", "みゅ");
            TTrie.Add("myo", "みょ");
            TTrie.Add("rya", "りゃ");
            TTrie.Add("ryu", "りゅ");
            TTrie.Add("ryo", "りょ");
            TTrie.Add("ga", "が");
            TTrie.Add("gi", "ぎ");
            TTrie.Add("gu", "ぐ");
            TTrie.Add("ge", "げ");
            TTrie.Add("go", "ご");
            TTrie.Add("za", "ざ");
            TTrie.Add("ji", "じ");
            TTrie.Add("zi", "じ");
            TTrie.Add("zu", "ず");
            TTrie.Add("ze", "ぜ");
            TTrie.Add("zo", "ぞ");
            TTrie.Add("da", "だ");
            TTrie.Add("ji", "ぢ");
            TTrie.Add("di", "ぢ");
            TTrie.Add("zu", "づ");
            TTrie.Add("du", "づ");
            TTrie.Add("de", "で");
            TTrie.Add("do", "ど");
            TTrie.Add("ba", "ば");
            TTrie.Add("bi", "び");
            TTrie.Add("bu", "ぶ");
            TTrie.Add("be", "べ");
            TTrie.Add("bo", "ぼ");
            TTrie.Add("pa", "ぱ");
            TTrie.Add("pi", "ぴ");
            TTrie.Add("pu", "ぷ");
            TTrie.Add("pe", "ぺ");
            TTrie.Add("po", "ぽ");
            TTrie.Add("ka", "か");
            TTrie.Add("ki", "き");
            TTrie.Add("ku", "く");
            TTrie.Add("ke", "け");
            TTrie.Add("ko", "こ");
            TTrie.Add("sa", "さ");
            TTrie.Add("shi", "し");
            TTrie.Add("si", "し");
            TTrie.Add("sh", "し");
            TTrie.Add("su", "す");
            TTrie.Add("se", "せ");
            TTrie.Add("so", "そ");
            TTrie.Add("ta", "た");
            TTrie.Add("chi", "ち");
            TTrie.Add("ti", "ち");
            TTrie.Add("tsu", "つ");
            TTrie.Add("tu", "つ");
            TTrie.Add("te", "て");
            TTrie.Add("to", "と");
            TTrie.Add("na", "な");
            TTrie.Add("ni", "に");
            TTrie.Add("nu", "ぬ");
            TTrie.Add("ne", "ね");
            TTrie.Add("no", "の");
            TTrie.Add("ha", "は");
            TTrie.Add("hi", "ひ");
            TTrie.Add("fu", "ふ");
            TTrie.Add("hu", "ふ");
            TTrie.Add("he", "へ");
            TTrie.Add("ho", "ほ");
            TTrie.Add("ma", "ま");
            TTrie.Add("mi", "み");
            TTrie.Add("mu", "む");
            TTrie.Add("me", "め");
            TTrie.Add("mo", "も");
            TTrie.Add("ya", "や");
            TTrie.Add("yu", "ゆ");
            TTrie.Add("yo", "よ");
            TTrie.Add("ra", "ら");
            TTrie.Add("ri", "り");
            TTrie.Add("ru", "る");
            TTrie.Add("re", "れ");
            TTrie.Add("ro", "ろ");
            TTrie.Add("wa", "わ");
            TTrie.Add("wo", "を");
            TTrie.Add("a", "あ");
            TTrie.Add("i", "い");
            TTrie.Add("u", "う");
            TTrie.Add("e", "え");
            TTrie.Add("o", "お");
            TTrie.Add("nn", "ん");
            TTrie.Add("n", "ん");
        }

        public void Add(string key, string value) { Add(key, 0, value); }

        public IEnumerable<string> Retrieve(string query) { return Retrieve(query, 0); }

        public static string ReplaceToKana(string query) { return TTrie.Replace(query); }
        public static string ReplaceToKana(string query, bool end) { return TTrie.Replace(query, end); }
        public string Replace(string query) { return Replace(query, false); }

        public string Replace(string query, bool end)
        {
            string result = null;
            int index = 0, length = 0;
            TrieNode node = null, lastNode = null;

            while (!EndOfString(index + length, query))
            {
                lastNode = node;
                node = (isset(node))
                    ? node.GetChildOrNull(query, index + length++)
                    : GetChildOrNull(query, index + length++);

                if (isset(node))
                {
                    if (EndOfString(index + length, query))
                        result += (node.Values.Count > 0 && (node.Children.Count == 0 || end))
                             ? node.Values.Peek()
                             : query.Substring(index, length);
                }
                else
                {
                    result += (isset(lastNode))
                        ? ((lastNode.Values.Count > 0)
                            ? lastNode.Values.Peek()
                            : query.Substring(index, 1))
                        : query.Substring(index, length);
                    index += (isset(lastNode))
                        ? ((lastNode.Values.Count > 0)
                            ? length - 1
                            : 1)
                        : length;
                    length = 0;
                }
            }
            return result;
        }
    }

    public class TrieNode
    {
        public Dictionary<char, TrieNode> Children;
        public Queue<string> Values { get; }

        protected TrieNode()
        {
            Children = new Dictionary<char, TrieNode>();
            Values = new Queue<string>();
        }

        protected TrieNode GetOrCreateChild(char key)
        {
            TrieNode result;
            if (!Children.TryGetValue(key, out result))
            {
                result = new TrieNode();
                Children.Add(key, result);
            }
            return result;
        }

        public TrieNode GetChildOrNull(string query, int position)
        {
            if (query == null) throw new ArgumentNullException("query");
            TrieNode childNode;
            return
                Children.TryGetValue(query[position], out childNode) ? childNode : null;
        }

        public void Add(string key, int position, string value)
        {
            if (key == null) throw new ArgumentNullException("key");
            if (EndOfString(position, key))
            {
                AddValue(value);
                return;
            }

            TrieNode child = GetOrCreateChild(key[position]);
            child.Add(key, position + 1, value);
        }

        private void AddValue(string value) { Values.Enqueue(value); }

        public static bool EndOfString(int position, string text) { return position >= text.Length; }

        public static bool isset(object o) { return o != null; }

        private IEnumerable<string> ValuesDeep()
        {
            return Subtree().SelectMany(node => node.Values);
        }

        private IEnumerable<TrieNode> Subtree()
        {
            return Enumerable.Repeat(this, 1)
                .Concat(Children.Values.SelectMany(child => child.Subtree()));
        }

        public IEnumerable<string> Retrieve(string query, int position)
        {
            return
                EndOfString(position, query)
                    ? ValuesDeep()
                    : SearchDeep(query, position);
        }

        private IEnumerable<string> SearchDeep(string query, int position)
        {
            TrieNode nextNode = GetChildOrNull(query, position);
            return isset(nextNode)
                       ? nextNode.Retrieve(query, position + 1)
                       : Enumerable.Empty<string>();
        }
    }
}
