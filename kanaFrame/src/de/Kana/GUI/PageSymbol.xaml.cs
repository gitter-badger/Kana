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
    public partial class PageSymbol : Page, IMainPageBase
    {
        private Frame _mainFrame;
        private PageSymbolSettings _pageSymbolSettings;
        private Dictionary<String, String> currentSettings;



        public PageSymbol(Frame _mainFrame)
        {
            InitializeComponent();
            this._mainFrame = _mainFrame;
            _pageSymbolSettings = new PageSymbolSettings(_mainFrame, this);
        }


        public void applySettings(Dictionary<string, string> settings)
        {
            currentSettings = settings;
            switch (currentSettings[Settings.MODE_KEY])
            {
                case PageSymbolSettings.MODE_FLOW:; break;
                case PageSymbolSettings.MODE_MATCH:; break;
                default: break;
            }
        }

        private void btnBack_Click(object sender, RoutedEventArgs e)
        {
            _mainFrame.NavigationService.GoBack();
        }

        private void btnSettings_Click(object sender, RoutedEventArgs e)
        {
            _mainFrame.Navigate(_pageSymbolSettings);
        }

        public void onNavigate(IPage page)
        {
            if (currentSettings == null)
                _mainFrame.Navigate(_pageSymbolSettings);
        }
    }
}
