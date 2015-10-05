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
    public partial class PageSettings : Page
    {
        private MainWindow _main;

        public PageSettings(MainWindow _main)
        {
            InitializeComponent();
            this._main = _main;
        }

        private void btnCancelClick(object sender, RoutedEventArgs e)
        {

        }

        private void btnOkClick(object sender, RoutedEventArgs e)
        {

        }
    }
}
