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
    public partial class PageSymbol : ContentPage
    {
        public new SymbolSettings Settings { get { return settings; } }
        private SymbolSettings settings;
        private UserProgress userProgress;
        private PageSymbolSettings _pageSymbolSettings;

        public PageSymbol()
        {
            InitializeComponent();
            _pageSymbolSettings = new PageSymbolSettings(this);
            userProgress = new UserProgress(Hiragana.Alphabet);
            symbolProgress.OnSymbolClick += Progress_OnSymbolClick;
            foreach (var item in userProgress.Progress)
                item.OnChange += Progress_OnChange;
            string _init = "お";
            foreach (var item in (from sylls
                                  in Hiragana.Alphabet
                                  where sylls.Flags.HasFlag(SymbolFlags.None)
                                  select sylls.Characters))
            {
                userProgress[item].Enabled = true;
                if (String.Compare(item, _init) == 0) break;

            }
            symbolMatch.p.SetUserProgress(userProgress);
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
            foreach (var item in (from sylls
                                  in Hiragana.Alphabet
                                  where sylls.Flags.HasFlag(SymbolFlags.None)
                                  select sylls.Characters))
                if (String.Compare(item, SymbolProgress.EMPTY) != 0)
                {
                    userProgress[item].Enabled = stat;
                    if (String.Compare((string)sender, item) == 0) stat = false;
                }
            symbolMatch.p.UpdatePool();
        }

        public override void ApplySettings(Settings settings)
        {
            this.settings = (SymbolSettings)settings;
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
    }//END class PageSymbol
}//END namespace
