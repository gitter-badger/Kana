using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Kana.Transwriting
{
    public class Trie : TrieNode, ITrie
    {
        public void Add(string key, string value)
        {
            Add(key, 0, value);
        }

        public IEnumerable<string> Retrieve(string query)
        {
            return Retrieve(query, 0);
        }

        public string Replace(string query)
        {
            string result = "";
            int index = 0;
            TrieNode node = null, lastNode = null;

            while (index <= query.Length)
            {
                if (EndOfString(index, query)) {
                    if (node != null && node.Values.Count > 0)
                        result += node.Values.First();
                }
                else
                {g
                    lastNode = node;
                    if (node == null)
                        node = GetChildOrNull(query, index++);
                    else
                        node = node.GetChildOrNull(query, index++);
                    if (node == null)
                        if (lastNode != null && lastNode.Values.Count > 0)
                            result += lastNode.Values.First();
                        else
                            result += query[index - 1];
                }
            }
            //TODO halfword-wise translating
            return result;
        }
        //TODO string translating
        //TODO hira-to-kana
    }

    public class TrieNode
    {
        private Dictionary<char, TrieNode> Children;
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

        private void AddValue(string value)
        {
            Values.Enqueue(value);
        }

        public static bool EndOfString(int position, string text)
        {
            return position >= text.Length;
        }

        private IEnumerable<string> ValuesDeep()
        {
            return Subtree().SelectMany(node => node.Values);
        }

        private IEnumerable<TrieNode> Subtree()
        {
            return Enumerable.Repeat(this, 1).Concat(Children.Values.SelectMany(child => child.Subtree()));
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
            return nextNode != null
                       ? nextNode.Retrieve(query, position + 1)
                       : Enumerable.Empty<string>();
        }
    }
}
