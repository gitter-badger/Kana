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
        private Frame _mainFrame;
        private PageSymbolSettings _pageSymbolSettings;

        public PageSymbol(Frame _mainFrame)
        {
            InitializeComponent();
            this._mainFrame = _mainFrame;
            _pageSymbolSettings = new PageSymbolSettings(_mainFrame);
        }
    }
}
