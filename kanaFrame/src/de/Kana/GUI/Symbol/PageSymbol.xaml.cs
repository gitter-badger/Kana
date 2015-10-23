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
    public partial class PageSymbol : ContentPage
    {
        private SettingsPage _pageSymbolSettings;

        public PageSymbol()
        {
            InitializeComponent();
            _pageSymbolSettings = new PageSymbolSettings(this);
        }


        public override void ApplySettings(Dictionary<string, string> settings)
        {
            switch (settings[Settings.MODE_KEY])
            {
                case PageSymbolSettings.MODE_FLOW:
                    Console.WriteLine("Mode: Flow");
                    symbolFlow.Visibility = Visibility.Visible;
                    symbolMatch.Visibility = Visibility.Hidden;
                    break;
                case PageSymbolSettings.MODE_MATCH:
                    Console.WriteLine("Mode: Match");
                    symbolFlow.Visibility = Visibility.Hidden;
                    symbolMatch.Visibility = Visibility.Visible;
                    break;
                default: break;
            }
            currentSettings = settings;
        }

        private void btnBack_Click(object sender, RoutedEventArgs e)
        {
            MainWindow.Navigation.NavigateBack();
        }

        private void btnSettings_Click(object sender, RoutedEventArgs e)
        {
            MainWindow.Navigation.Navigate(_pageSymbolSettings);
        }

        public override void HandleOnNavigate(KanaPage last)
        {
            if (currentSettings == null)
                if (last == _pageSymbolSettings)
                    _pageSymbolSettings.DefaultSettings(true);
                else
                    MainWindow.Navigation.Navigate(_pageSymbolSettings);
            else
                box.Focus();
        }
    }
}
