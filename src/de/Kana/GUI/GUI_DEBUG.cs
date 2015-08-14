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
        private Trie TTrie { get; set; }
        private int prevL = 0;
        public DebugForm()
        {
            InitializeComponent();
            init();
        }

        private void init()
        {
            TTrie = new Trie();
            TTrie.Add("a", "あ");
            TTrie.Add("i", "い");
            TTrie.Add("o", "お");

            TTrie.Add("sa", "さ");
            TTrie.Add("si", "し");
            TTrie.Add("so", "そ");

            TTrie.Add("ssa", "っさ");
            TTrie.Add("ssi", "っし");
            TTrie.Add("sso", "っそ");

            TTrie.Add("sha", "しゃ");
            TTrie.Add("shi", "し");
            TTrie.Add("sho", "しょ");

            TTrie.Add("ssha", "っしゃ");
            TTrie.Add("sshi", "っし");
            TTrie.Add("ssho", "っしょ");
        }

        private void textBox1_TextChanged(object sender, EventArgs e)
        {
            int maxL = 4;
            int len;
            if (((len = textBox1.Text.Length) == prevL + 1))
            {
                int sel = textBox1.SelectionStart;
                int n1 = Math.Max(sel - maxL, 0);
                int n2 = Math.Min(sel - n1, maxL);
                string news = textBox1.Text.Substring(n1, n2);
                int res = TTrie.Retrieve(news).Count();
                button1.Text = "start: [" + n1 + "] length : [" + n2 + "] text: [" + news + "] res: [" + res + "]";
            }
            prevL = textBox1.Text.Length;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            string str = TTrie.halfword(textBox1.Text);
            button1.Text = (str == null) ? "NULL" : str;
        }
    }
}