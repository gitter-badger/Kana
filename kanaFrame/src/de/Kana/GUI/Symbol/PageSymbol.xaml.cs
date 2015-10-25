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

using Kana.Symbol;

namespace KanaFrame
{
    /// <summary>
    /// Interaction logic for PageSymbol.xaml
    /// </summary>
    public partial class PageSymbol : ContentPage
    {
        private SettingsPage _pageSymbolSettings;
        private UserProgress userProgress;

        public PageSymbol()
        {
            InitializeComponent();
            userProgress = new UserProgress(SymbolUtils.Hiragana);
            _pageSymbolSettings = new PageSymbolSettings(this);
            progress.OnSymbolClick += Progress_OnSymbolClick;
            foreach (var item in userProgress.Progress)
            {
                userProgress[item.Key].OnChange += PageSymbol_OnChange;
            }
        }

        private void PageSymbol_OnChange(object sender, EventArgs e)
        {
            var item = (Progress)sender;
            ColorBorder border = progress.Borders[item.Symbol.Characters];
            border.setColor(item.Enabled ? Color.FromRgb(50, 100, 150) : Color.FromArgb(0, 0, 0, 0));
        }

        private void Progress_OnSymbolClick(object sender, EventArgs e)
        {
            bool stat = true;
            foreach (var item in SymbolProgress.HIRAGANA)
                if (String.Compare(item, SymbolProgress.EMPTY) != 0)
                {
                    userProgress[item].Enabled = stat;
                    if (String.Compare((string)sender, item) == 0) stat = false;
                }
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

        private void box_TextChanged(object sender, TextChangedEventArgs e)
        {

        }
    }
}
