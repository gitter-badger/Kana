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
    /// Interaction logic for PageMain.xaml
    /// </summary>
    public partial class PageMain : MenuPage
    {
        private ContentPage _pageSymbol;

        public PageMain()
        {
            InitializeComponent();
            _pageSymbol = new SymbolPage();
        }

        private void btnSymbol_Click(object sender, RoutedEventArgs e)
        {
            MainWindow.Navigation.Navigate(_pageSymbol);
        }
    }
}