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
        public SymbolProgress()
        {
            InitializeComponent();
        }

        private void UniformGrid_Initialized(object sender, EventArgs e)
        {
            foreach (string str in new string[] { "あ", "い", "う", "え", "お", "か", "き", "く", "け", "こ", "さ", "し", "す", "せ", "そ", "た", "ち", "つ", "て", "と", "な", "に", "ぬ", "ね", "の", "は", "ひ", "ふ", "へ", "ほ", "ま", "み", "む", "め", "も", "や", " ", "ゆ", " ", "よ", "ら", "り", "る", "れ", "ろ", "わ", "を", "ん", " ", "?" })
            {
                grid.Children.Add(NewBorderedButtonThing(str));
            }
        }

        private Border NewBorderedButtonThing(string str)
        {
            var border = new Border();
            var box = new Viewbox();
            var lbl = new TextBlock();
            border.Style = FindResource("StdBorder") as Style;
            border.Margin = new Thickness(0);
            border.Padding = new Thickness(0);
            if (String.Compare(" ", str) != 0)
            {
                border.MouseEnter += Lbl_MouseEnter;
                border.MouseLeave += Lbl_MouseLeave;
            }
            box.Margin = new Thickness(0);
            lbl.Margin = new Thickness(0);
            if (String.Compare(str, "?") == 0)
            {
                var border2 = new Border();
                border2.Style = FindResource("StdBorder") as Style;
                border2.Margin = new Thickness(1);
                border2.Padding = new Thickness(2);
                border2.MouseEnter += Lbl_MouseEnter;
                border2.MouseLeave += Lbl_MouseLeave;
                lbl.Text = str;
                box.Child = lbl;
                border2.Child = box;
                border.Child = border2;
                return border;
            }
            lbl.Text = str;
            box.Child = lbl;
            border.Child = box;
            return border;
        }

        private void Lbl_MouseLeave(object sender, MouseEventArgs e)
        {
            ((Border)sender).Background = new SolidColorBrush(Color.FromArgb(0, 0, 0, 0));
        }

        private void Lbl_MouseEnter(object sender, MouseEventArgs e)
        {
            ((Border)sender).Background = new SolidColorBrush(Color.FromRgb(255, 255, 255));
        }
    }
}
