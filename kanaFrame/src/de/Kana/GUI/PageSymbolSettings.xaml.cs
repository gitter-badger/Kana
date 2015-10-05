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
    /// Interaction logic for PageSymbolSettings.xaml
    /// </summary>
    public partial class PageSymbolSettings : Page
    {
        private MainWindow _main;

        public PageSymbolSettings(MainWindow _main)
        {
            InitializeComponent();
            this._main = _main;
        }

        private void button4_Click(object sender, RoutedEventArgs e)
        {
            _main._mainFrame.NavigationService.GoBack();
        }
    }
}
