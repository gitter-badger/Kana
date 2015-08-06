using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using Kana.Transwriting;

namespace Kana.src.de.Kana.GUI
{
    public partial class DebugForm : Form
    {
        private Trie<string> TransTrie { get; set; }
        private int prevL = 0;
        public DebugForm()
        {
            InitializeComponent();
            init();
        }

        private void init()
        {
            TransTrie = new Trie<string>();
            TransTrie.Add("a", "あ");
            TransTrie.Add("i", "い");
            TransTrie.Add("o", "お");

            TransTrie.Add("sa", "さ");
            TransTrie.Add("si", "し");
            TransTrie.Add("so", "そ");

            TransTrie.Add("ssa", "っさ");
            TransTrie.Add("ssi", "っし");
            TransTrie.Add("sso", "っそ");

            TransTrie.Add("sha", "しゃ");
            TransTrie.Add("shi", "し");
            TransTrie.Add("sho", "しょ");

            TransTrie.Add("ssha", "っしゃ");
            TransTrie.Add("sshi", "っし");
            TransTrie.Add("ssho", "っしょ");
        }

        private void textBox1_TextChanged(object sender, EventArgs e)
        {
            if (textBox1.Text.Length == prevL + 1 && textBox1.SelectionStart == textBox1.Text.Length) //Only 1 input
            {
                string news = textBox1.Text.Substring(Math.Max(prevL - 3, 0));
                string[] result = TransTrie.Retrieve(news).ToArray();
                int count = result.Length;
                if (count == 1)
                    textBox1.
                    
            }
            prevL = textBox1.Text.Length;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            button1.Text = TransTrie.Retrieve("").Count().ToString();
        }
    }
}
