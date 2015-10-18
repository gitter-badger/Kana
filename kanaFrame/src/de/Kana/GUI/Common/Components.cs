using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Controls;

namespace KanaFrame
{
    public class TranlationBox : TextBox
    {
        private int prevL = 0;
        public TranlationBox()
        {

        }

        protected override void OnTextChanged(TextChangedEventArgs e)
        {
            base.OnTextChanged(e);
            //Console.Out.WriteLine("Text: " + Text + " Length: " + Text.Length);
            int maxL = 4;
            int len = Text.Length;
            if (len == prevL + 1)
            {
                int sel = SelectionStart;
                int n1 = Math.Max(sel - maxL, 0);
                int n2 = Math.Min(sel - n1, maxL);
                var sb = new StringBuilder(Text);
                sb.Remove(n1, n2);
                sb.Insert(n1, Kana.Transwriting.Trie.ReplaceToKana(Text.Substring(n1, n2)));
                Text = sb.ToString();
                SelectionStart = Text.Length;
            }
            prevL = Text.Length;
        }
    }
}