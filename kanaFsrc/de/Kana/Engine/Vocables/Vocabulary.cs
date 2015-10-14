using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Kana.Vocables
{
    public class Voc
    {

    }

    public class Node
    {
        Dictionary<char, Node> children;
        Queue<Voc> Values { get; }

        protected Node()
        {
            children = new Dictionary<char, Node>();
            Values = new Queue<Voc>();
        }

        Node GetOrCreate(char key)
        {
            Node result;
            if (!children.TryGetValue(key, out result))
            {
                result = new Node();
                children.Add(key, result);
            }
            return result;
        }

        Node GetOrNull(string query, int position)
        {
            Node result;
            return children.TryGetValue(query[position], out result)
                ? result
                : null;
        }

        protected void Add(string query, int position, Voc value)
        {
            if (EndOfWord(position, query))
            {
                AddValue(value);
                return;
            }
            Node child = GetOrCreate(query[position]);
        }

        void AddValue(Voc value)
        {
            Values.Enqueue(value);
        }

        static bool EndOfWord(int position, string text)
        {
            return position >= text.Length;
        }

        static bool isset(object o)
        {
            return o != null;
        }

    }

    public class WordTree : Node
    {
        public void Add(String query, Voc value)
        {
            Add(query, 0, value);
        }



























        public static WordTree Words { get; }

        static WordTree()
        {
            Words.Add()
        }

    }
}
