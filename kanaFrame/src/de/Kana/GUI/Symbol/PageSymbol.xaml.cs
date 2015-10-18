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
    public partial class PageSymbol : KanaPage, IContentPage
    {
        private KanaWindow _window;
        private PageSymbolSettings _pageSymbolSettings;
        private Dictionary<String, String> currentSettings;



        public PageSymbol(KanaWindow _window)
        {
            InitializeComponent();
            this._window = _window;
            _pageSymbolSettings = new PageSymbolSettings(_window, this);
        }


        public void ApplySettings(Dictionary<string, string> settings)
        {
            if (Settings.Mode_Changed(settings, currentSettings))
                switch (settings[Settings.MODE_KEY])
                {
                    case PageSymbolSettings.MODE_FLOW:
                        ;
                        break;
                    case PageSymbolSettings.MODE_MATCH:
                        ;
                        break;
                    default: break;
                }
            currentSettings = settings;
        }

        private void btnBack_Click(object sender, RoutedEventArgs e)
        {
            _window.NavigateBack();
        }

        private void btnSettings_Click(object sender, RoutedEventArgs e)
        {
            _window.Navigate(_pageSymbolSettings);
        }

        public override void HandleOnNavigate()
        {
            if (currentSettings == null)
            {
                _window.Navigate(_pageSymbolSettings);
                if (currentSettings == null)
                    _pageSymbolSettings.DefaultSettings(true);
            }

        }
    }
}
