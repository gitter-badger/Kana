using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace KanaFrame
{
    /// <summary>
    /// Interaction logic for SymbolMatch.xaml
    /// </summary>
    public partial class SymbolMatch : UserControl, SymbolMatchPublic
    {
        public SymbolMatchPublic p { get; }
        public HiraSyllable Symbol { get; protected set; }
        public string Text { get { return text.Text; } set { text.Text = value; } }
        private HiraSyllable[] Symbols { get; set; }
        private Random random;
        private UserProgress userProgress;
        public SymbolMatch()
        {
            InitializeComponent();
            random = new Random();
            p = this;
        }

        public void Next()
        {
            if (userProgress == null) return;
            HiraSyllable last = Symbol;
            do
                Symbol = Symbols[random.Next(Symbols.Length)];
            while (last == Symbol && Symbols.Length > 1);
            Text = Symbol.Characters;
        }

        public void SetUserProgress(UserProgress value)
        {
            userProgress = value;
            UpdatePool();
        }

        public void UpdatePool()
        {
            Symbols = (from sylls in userProgress.Progress where sylls.Enabled select sylls.Symbol).ToArray();
            Next();
        }

    }//END class SymbolMatch

    public interface SymbolMatchPublic
    {
        void Next();
        void SetUserProgress(UserProgress value);
        void UpdatePool();
    }
}//END namespace