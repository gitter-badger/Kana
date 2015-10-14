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
        private int prevL = 0;
        VocableStream stream;
        public DebugForm()
        {
            InitializeComponent();
            init();
        }

        private void init()
        {
            stream = new VocableStream();
            stream.InitializeStream(VocableStream.Type.Kana);
        }

        private void textBox1_TextChanged(object sender, EventArgs e)
        {
            int maxL = 4;
            int len = textBox1.TextLength;
            if (len == prevL + 1)
            {
                int sel = textBox1.SelectionStart;
                int n1 = Math.Max(sel - maxL, 0);
                int n2 = Math.Min(sel - n1, maxL);
                var sb = new StringBuilder(textBox1.Text);
                sb.Remove(n1, n2);
                sb.Insert(n1, Trie.TTrie.Replace(textBox1.Text.Substring(n1, n2)));
                textBox1.Text = sb.ToString();
                textBox1.SelectionStart = textBox1.TextLength;
            }
            prevL = textBox1.TextLength;
        }

        private void nextB_Click(object sender, EventArgs e) {
            streamOutputLabel.Text = stream.Next();
            nextB.Enabled = false;
            textBox1.BackColor = Color.White;
        }

        private void checkButton_Click(object sender, EventArgs e) {
            if (Matcher.Matches(textBox1.Text, streamOutputLabel.Text)) {
                nextB.Enabled = true;
                textBox1.BackColor = Color.Aqua;
            } else {
                textBox1.BackColor = Color.Red;
            }
        }
    }
}