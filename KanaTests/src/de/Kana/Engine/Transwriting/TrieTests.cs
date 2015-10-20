using Microsoft.VisualStudio.TestTools.UnitTesting;
using Kana.Transwriting;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Kana.Transwriting.Tests
{
    [TestClass()]
    public class TrieTests
    {
        [TestMethod()]
        public void ReplaceToKanaTest()
        {
            Assert.AreEqual("あsっしょ", Trie.ReplaceToKana("asssho"));
            Assert.AreEqual("あ", Trie.ReplaceToKana("a"));
            Assert.AreEqual("ああ", Trie.ReplaceToKana("aa"));
            Assert.AreEqual("ああああ", Trie.ReplaceToKana("aaaa"));
            Assert.AreEqual("あえ", Trie.ReplaceToKana("ae"));
            Assert.AreEqual("ふ", Trie.ReplaceToKana("hu"));
            Assert.AreEqual("fふ", Trie.ReplaceToKana("fhu"));
            Assert.AreEqual("ふう", Trie.ReplaceToKana("fuu"));
            Assert.AreEqual("づ", Trie.ReplaceToKana("du"));
            Assert.AreEqual("っしょ", Trie.ReplaceToKana("ssho"));
            Assert.AreEqual("しょ", Trie.ReplaceToKana("sho"));
            Assert.AreEqual("し", Trie.ReplaceToKana("shi"));
            Assert.AreEqual("あし", Trie.ReplaceToKana("ashi"));
            Assert.AreEqual("ありがとう　ございます", Trie.ReplaceToKana("arigatou gozaimasu"));
            Assert.AreEqual("に", Trie.ReplaceToKana("ni"));
            Assert.AreEqual("んに", Trie.ReplaceToKana("nnni"));
            Assert.AreEqual("ん", Trie.ReplaceToKana("nn"));
            Assert.AreEqual("んい", Trie.ReplaceToKana("nni"));
            Assert.AreEqual("n", Trie.ReplaceToKana("n"));
            Assert.AreEqual("ん", Trie.ReplaceToKana("n", true));
            Assert.AreEqual("つっちょちょ", Trie.ReplaceToKana("tsucchocho", true));   
        }
    }
}