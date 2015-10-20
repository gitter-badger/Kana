﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;

namespace KanaFrame
{
    public class TranslationBox : TextBox
    {
        private int prevL = 0;

        public TranslationBox()
        {
            TranslationEnabled = true;
        }


        public bool TranslationEnabled
        {
            get { return (bool)GetValue(TranslationEnabledProperty); }
            set { SetValue(TranslationEnabledProperty, value); }
        }

        // Using a DependencyProperty as the backing store for TranslationEnabled.  This enables animation, styling, binding, etc...
        public static readonly DependencyProperty TranslationEnabledProperty =
            DependencyProperty.Register("TranslationEnabled", typeof(bool), typeof(TranslationBox));



        protected override void OnTextChanged(TextChangedEventArgs e)
        {
            base.OnTextChanged(e);
            if (!TranslationEnabled) return;
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
        protected override void OnPropertyChanged(DependencyPropertyChangedEventArgs e)
        {
            base.OnPropertyChanged(e);
            if (String.Compare(e.Property.Name, "Height") == 0)
                FontSize = Double.Parse(e.NewValue.ToString()) - 10;
        }
    }
}