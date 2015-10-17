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
    public partial class PageMain : Page, IMenuPageBase
    {
        private Frame _mainFrame;
        private PageSymbol _pageSymbol;

        public PageMain(Frame _mainFrame)
        {
            InitializeComponent();
            this._mainFrame = _mainFrame;
            _pageSymbol = new PageSymbol(_mainFrame);
        }

        public void onNavigate(IPage page)
        {
            //throw new NotImplementedException();
        }

        private void btnSymbol_Click(object sender, RoutedEventArgs e)
        {
            _mainFrame.Navigate(_pageSymbol);
        }
    }
}