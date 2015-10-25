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
        public const string EMPTY = "";
        public static readonly string[] HIRAGANA = new string[] { "あ", "い", "う", "え", "お", "か", "き", "く", "け", "こ", "さ", "し", "す", "せ", "そ", "た", "ち", "つ", "て", "と", "な", "に", "ぬ", "ね", "の", "は", "ひ", "ふ", "へ", "ほ", "ま", "み", "む", "め", "も", "や", EMPTY, "ゆ", EMPTY, "よ", "ら", "り", "る", "れ", "ろ", "わ", EMPTY, "ん", EMPTY, "を" };
        public event EventHandler OnSymbolClick;
        public Dictionary<string, ColorBorder> Borders { get; private set; }

        public SymbolProgress()
        {
            Borders = new Dictionary<string, ColorBorder>();
            InitializeComponent();
        }

        private void UniformGrid_Initialized(object sender, EventArgs e)
        {
            foreach (string str in HIRAGANA)
            {
                grid.Children.Add(NewBorderedButtonThing(str));
            }
        }

        private Border NewBorderedButtonThing(string str)
        {
            var border = new ColorBorder();
            var box = new Viewbox();
            var lbl = new TextBlock();
            border.Style = FindResource("StdBorder") as Style;
            border.Margin = new Thickness(0);
            border.Padding = new Thickness(0);
            if (String.Compare(EMPTY, str) != 0)
            {
                border.PreviewMouseDown += Border_PreviewMouseDown;
                Borders[str] = border;
            }
            box.Margin = new Thickness(0);
            lbl.Margin = new Thickness(0);

            lbl.Text = str;
            box.Child = lbl;
            border.Child = box;
            return border;
        }

        private void Border_PreviewMouseDown(object sender, MouseButtonEventArgs e)
        {
            string str = ((TextBlock)((Viewbox)(((ColorBorder)sender).Child)).Child).Text;
            if (OnSymbolClick != null) OnSymbolClick(str, new EventArgs());
        }
    }






    public class ColorBorder : Border
    {
        private Color color;
        public Color Color
        {
            get { return color; }
            set { setColor(value); }
        }

        public void setColor(Color color)
        {
            this.color = color;
            Background = new SolidColorBrush(Color);
        }
        public ColorBorder() : base()
        {
            Color = Color.FromArgb(0, 0, 0, 0);
            Background = new SolidColorBrush(color);
        }
        protected override void OnMouseEnter(MouseEventArgs e)
        {
            base.OnMouseEnter(e);
            Background = new SolidColorBrush(Color.FromRgb(255, 255, 255));
        }
        protected override void OnMouseLeave(MouseEventArgs e)
        {
            base.OnMouseLeave(e);
            Background = new SolidColorBrush(Color);
        }
        protected override void OnPreviewMouseLeftButtonUp(MouseButtonEventArgs e)
        {
            base.OnPreviewMouseLeftButtonUp(e);
        }
    }
}
