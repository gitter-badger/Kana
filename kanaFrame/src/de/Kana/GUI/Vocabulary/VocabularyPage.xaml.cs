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
    /// Interaction logic for VocabularyPage.xaml
    /// </summary>
    public partial class VocabularyPage : ContentPage
    {
        public new VocabularySettings Options { get { return settings; } }
        private VocabularySettings settings;
        private VocabularySettingsPage _pageSettings;

        public VocabularyPage()
        {
            InitializeComponent();
            _pageSettings = new VocabularySettingsPage(this);
        }


        public override void ApplySettings(Settings settings)
        {
            this.settings = (VocabularySettings)settings;
        }

        private void btnBack_Click(object sender, RoutedEventArgs e)
        {
            MainWindow.Navigation.NavigateBack();
        }

        private void btnSettings_Click(object sender, RoutedEventArgs e)
        {
            MainWindow.Navigation.Navigate(_pageSettings);
        }

        public override void HandleOnNavigate(KanaPage last)
        {
            if (settings == null)
                if (last == _pageSettings)
                    ApplySettings(settings = new VocabularySettings());
                else
                    MainWindow.Navigation.Navigate(_pageSettings);
        }
    }//END class VocabularySymbol
}//END namespace
