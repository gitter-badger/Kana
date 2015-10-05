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
    /// Interaction logic for PageSymbol.xaml
    /// </summary>
    public partial class PageSymbol : Page
    {
        private MainWindow _main;
        private PageSymbolSettings _pageSymbolSettings;

        public PageSymbol(MainWindow _main)
        {
            InitializeComponent();
            this._main = _main;
            _pageSymbolSettings = new PageSymbolSettings(_main);
        }

        private void button1_Click(object sender, RoutedEventArgs e)
        {
            _main._mainFrame.NavigationService.GoBack();
        }

        private void button2_Click(object sender, RoutedEventArgs e)
        {
            _main._mainFrame.Navigate(_pageSymbolSettings);
        }
    }
}
