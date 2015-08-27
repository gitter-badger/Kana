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
        public void ReplaceTest()
        {
            Assert.AreEqual("あsっしょ", Trie.TTrie.Replace("asssho"));
            Assert.AreEqual("あ", Trie.TTrie.Replace("a"));
            Assert.AreEqual("ああ", Trie.TTrie.Replace("aa"));
            Assert.AreEqual("ああああ", Trie.TTrie.Replace("aaaa"));
            Assert.AreEqual("あえ", Trie.TTrie.Replace("ae"));
            Assert.AreEqual("ふ", Trie.TTrie.Replace("hu"));
            Assert.AreEqual("fふ", Trie.TTrie.Replace("fhu"));
            Assert.AreEqual("ふう", Trie.TTrie.Replace("fuu"));
            Assert.AreEqual("づ", Trie.TTrie.Replace("du"));
            Assert.AreEqual("っしょ", Trie.TTrie.Replace("ssho"));
            Assert.AreEqual("しょ", Trie.TTrie.Replace("sho"));
            Assert.AreEqual("し", Trie.TTrie.Replace("shi"));
            Assert.AreEqual("あし", Trie.TTrie.Replace("ashi"));
            Assert.AreEqual("ありがとう ございます", Trie.TTrie.Replace("arigatou gozaimasu"));
            Assert.AreEqual("に", Trie.TTrie.Replace("ni"));
            Assert.AreEqual("んに", Trie.TTrie.Replace("nnni"));
            Assert.AreEqual("ん", Trie.TTrie.Replace("nn"));
            Assert.AreEqual("んい", Trie.TTrie.Replace("nni"));
            Assert.AreEqual("n", Trie.TTrie.Replace("n"));
            Assert.AreEqual("ん", Trie.TTrie.Replace("n", true));
        }
    }
}