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
    /// Interaction logic for SymbolProgress.xaml
    /// </summary>
    public partial class SymbolProgress : UserControl
    {
        public static readonly string EMPTY = "";
        public event EventHandler OnSymbolClick;

        public Dictionary<string, ISymbolIcon> Borders { get; private set; }

        public SymbolProgress()
        {
            InitializeComponent();
            List<string> list = new List<string>();
            list.AddRange(new String[] { "や", "ゆ", "わ", "ん" }); //to add some spaces in the grid after these symbols
            Borders = new Dictionary<string, ISymbolIcon>();
            foreach (HiraSyllable syll in (from sylls
                                           in Hiragana.Alphabet
                                           where sylls.Flags.HasFlag(SymbolFlags.None)
                                           select sylls))
            {
                string str = syll.Characters;
                BlinkingBorder border = new BlinkingBorder(syll.Characters);
                border.PreviewMouseDown += Border_PreviewMouseDown;
                Borders[str] = border;
                grid.Children.Add(border);
                if (list.Contains(str)) grid.Children.Add(new BlinkingBorder(""));
            }
        }

        private void Border_PreviewMouseDown(object sender, MouseButtonEventArgs e)
        {
            string str = ((BlinkingBorder)sender).GetText();
            if (OnSymbolClick != null) OnSymbolClick(str, new EventArgs());
        }
    }//END class SymbolProgress






    public class BlinkingBorder : Border, ISymbolIcon
    {
        private Color color;
        public Color Color
        {
            get { return color; }
            set { color = value; Background = new SolidColorBrush(Color); }
        }

        public BlinkingBorder(String str) : base()
        {
            Color = Color.FromArgb(0, 0, 0, 0);
            Background = new SolidColorBrush(color);
            Style = FindResource("StdBorder") as Style;
            Margin = new Thickness(0);
            Padding = new Thickness(0);

            var box = new Viewbox();
            var lbl = new TextBlock();
            box.Margin = new Thickness(0);
            lbl.Margin = new Thickness(0);

            lbl.Text = str;
            box.Child = lbl;
            Child = box;
        }

        protected override void OnMouseEnter(MouseEventArgs e)
        {
            base.OnMouseEnter(e);
            if (GetText() != SymbolProgress.EMPTY)
                Background = new SolidColorBrush(Color.FromRgb(255, 255, 255));
        }

        protected override void OnMouseLeave(MouseEventArgs e)
        {
            base.OnMouseLeave(e);
            if (GetText() != SymbolProgress.EMPTY)
                Background = new SolidColorBrush(Color);
        }

        public string GetText()
        {
            return ((TextBlock)((Viewbox)Child).Child).Text;
        }
    }//END class ColorBorder



    public interface ISymbolIcon
    {
        Color Color { get; set; }
        string GetText();
    }//END interface ISymbolIcon
}//END namespace
