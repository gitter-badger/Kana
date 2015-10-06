using System.Collections.Generic;
using System.Linq;

namespace Kana.src.de.Kana.Util {
	public class WordGraph {

        public enum Type {
            Japanese,
            English,
            German
        }

        class Node {
            public Dictionary<char, Node> Childs { get; set; }
            public Vocable Vocable { get; set; }
            public bool EOW { get; set; }

            public Node() {
                Vocable = default(Vocable);
                Childs = new Dictionary<char, Node>();
                EOW = false;
            } 
        }

		private Node Root { get; set; }
        
		public WordGraph () {
			Root = new Node ();
		}

        public void Add(Vocable voc, string keyString) {
			Node currentNode = Root;

			foreach (char syl in keyString) {
                if (!currentNode.Childs.ContainsKey(syl))
                    currentNode.Childs[syl] = new Node();
                currentNode = currentNode.Childs[syl];
			}
            currentNode.Vocable = voc;
            currentNode.EOW = true;
		}

        public LinkedList<Vocable> getContent () {
            LinkedList<Vocable> vocabels = new LinkedList<Vocable>();
            traverse(vocabels, Root);
            return vocabels;
        }
        
        public Vocable getVocable(string searchString) {
            Node node = Root;
            foreach (char c in searchString) {
                if (!node.Childs.ContainsKey(c))
                    return null;
                node = node.Childs[c];
            }
            if (!node.EOW)
                return null;
            return node.Vocable;
        }
        
        private void traverse (LinkedList<Vocable> vocabels, Node node) {
            if (node == null)
                return;

            foreach (char syl in node.Childs.Keys) {
                if (node.Childs[syl].EOW && node.Childs.Count != 0)
                    vocabels.AddLast(node.Childs[syl].Vocable);

                traverse(vocabels, node.Childs[syl]);

                if (node.Childs[syl].EOW && node.Childs.Count == 0)
                    vocabels.AddLast(node.Childs[syl].Vocable);
            }
        }
	}
}

