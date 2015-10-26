using System;
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
        private bool textLock = false;
        public bool TranslationEnabled
        {
            get { return (bool)GetValue(TranslationEnabledProperty); }
            set { SetValue(TranslationEnabledProperty, value); }
        }
        public static readonly DependencyProperty TranslationEnabledProperty =
            DependencyProperty.Register("TranslationEnabled", typeof(bool), typeof(TranslationBox));

        public static readonly RoutedEvent TranslationEvent =
            EventManager.RegisterRoutedEvent("Translation", RoutingStrategy.Bubble, typeof(RoutedEventHandler), typeof(TranslationBox));

        // Provide CLR accessors for the event
        public event RoutedEventHandler Translation
        {
            add { AddHandler(TranslationEvent, value); }
            remove { RemoveHandler(TranslationEvent, value); }
        }

        public TranslationBox()
        {
            TranslationEnabled = true;
        }

        protected override void OnTextChanged(TextChangedEventArgs e)
        {
            if (textLock) return;
            textLock = true;
            base.OnTextChanged(e);
            if (!TranslationEnabled) return;
            //Console.Out.WriteLine("Text: " + Text + " Length: " + Text.Length);
            int maxL = 4;
            int len = Text.Length;
            if (len == prevL + 1)
            {
                //Console.Out.WriteLine("Before: Text: " + Text + " Length: " + Text.Length);
                int sel = SelectionStart;
                int n1 = Math.Max(sel - maxL, 0);
                int n2 = Math.Min(sel - n1, maxL);
                var sb = new StringBuilder(Text);
                sb.Remove(n1, n2);
                sb.Insert(n1, Kana.Transwriting.Trie.ReplaceToKana(Text.Substring(n1, n2)));
                Text = sb.ToString();
                SelectionStart = sel + (Text.Length - prevL - 1);
                //Console.Out.WriteLine("After: Text: " + Text + " Length: " + Text.Length + " prevL: " + prevL + " len: " + len + " sel: " + sel);
            }
            prevL = Text.Length;
            textLock = false;
            RoutedEventArgs newEventArgs = new RoutedEventArgs(TranslationEvent);
            RaiseEvent(newEventArgs);
        }
        protected override void OnPropertyChanged(DependencyPropertyChangedEventArgs e)
        {
            base.OnPropertyChanged(e);
            if (String.Compare(e.Property.Name, "Height") == 0)
                FontSize = Double.Parse(e.NewValue.ToString()) - 10;
        }
    }
}