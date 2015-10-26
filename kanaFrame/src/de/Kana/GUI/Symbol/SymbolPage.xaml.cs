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
using System.Threading;

namespace KanaFrame
{
    /// <summary>
    /// Interaction logic for PageSymbol.xaml
    /// </summary>
    public partial class SymbolPage : ContentPage
    {
        public new SymbolSettings Options { get { return settings; } }
        private SymbolSettings settings;
        private UserProgress userProgress;
        private SymbolSettingsPage _pageSymbolSettings;

        public SymbolPage()
        {
            InitializeComponent();
            _pageSymbolSettings = new SymbolSettingsPage(this);
            userProgress = new UserProgress(Hiragana.Alphabet);
            symbolProgress.OnSymbolClick += Progress_OnSymbolClick;
            foreach (var item in userProgress.Progress)
                item.OnChange += Progress_OnChange;
            string _init = "お";
            foreach (var item in Hiragana.StdSet)
            {
                userProgress[item.Characters].Enabled = true;
                if (String.Compare(item.Characters, _init) == 0) break;

            }
            symbolMatch.SetUserProgress(userProgress);
        }

        private void Progress_OnChange(object sender, EventArgs e)
        {
            var item = (Progress)sender;
            ISymbolIcon border = symbolProgress.Borders[item.Symbol.Characters];
            border.Color = item.ToColor();
        }

        private void Progress_OnSymbolClick(object sender, EventArgs e)
        {
            bool stat = true;
            foreach (var item in Hiragana.StdSet)
                if (String.Compare(item.Characters, SymbolProgress.EMPTY) != 0)
                {
                    userProgress[item.Characters].Enabled = stat;
                    if (String.Compare((string)sender, item.Characters) == 0) stat = false;
                }
            symbolMatch.UpdatePool();
        }

        public override void ApplySettings(Settings settings)
        {
            this.settings = (SymbolSettings)settings;
            if (settings.Equals(Settings.KEY_MODE, SymbolSettings.MODE_MATCH))
            {
                symbolMatch.Visibility = Visibility.Visible;
                symbolFlow.Visibility = Visibility.Hidden;
                symbolMatch.Next();
            }
            else
            {

            }
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
            if (settings == null)
                if (last == _pageSymbolSettings)
                    ApplySettings(settings = new SymbolSettings());
                else
                    MainWindow.Navigation.Navigate(_pageSymbolSettings);
            else
                box.Focus();
        }

        private void box_Translation(object sender, RoutedEventArgs e)
        {
            Console.Out.WriteLine("Box: " + box.Text + " Match: " + symbolMatch.Text + " Hira=" + Hiragana.StartsWithStdHira(box.Text));
            if (settings.Equals(Settings.KEY_MODE, SymbolSettings.MODE_MATCH))
            {
                if (Hiragana.StartsWithStdHira(box.Text))
                {
                    if (String.Compare(symbolMatch.Text, box.Text) == 0)
                        userProgress[symbolMatch.Text].Correct();
                    else
                    {
                        userProgress[symbolMatch.Text].Wrong();
                        userProgress[box.Text].Wrong();
                    }
                    symbolMatch.Next();
                    box.Text = "";
                }
            }
            else
            {

            }
        }//END box_Translation
    }//END class PageSymbol
}//END namespace
