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
    /// Interaction logic for VocabularySettingsPage.xaml
    /// </summary>
    public partial class VocabularySettingsPage : SettingsPage
    {
        private VocabularySettings settings;
        private VocabularyPage _parent;

        public VocabularySettingsPage(VocabularyPage _parent)
        {
            InitializeComponent();
            this._parent = _parent;
        }

        public override void HandleKeyDown(KeyEventArgs e)
        {
            base.HandleKeyDown(e);
            if (e.Key == Key.Escape)
                btnCancel_Click(null, null);
        }

        public override void HandleOnNavigate(KanaPage last)
        {
            settings = _parent.Options != null
                ? _parent.Options.Clone()
                : new VocabularySettings();
            SetGUI();
        }

        protected override void ReadGUI()
        {
            settings[VocabularySettings.KEY_1] = rbtn1.IsChecked == true
                ? VocabularySettings.OPTION_1_FIRST
                : VocabularySettings.OPTION_1_SECOND;
            settings.Bool(VocabularySettings.KEY_2, chbox1.IsChecked == true);
            settings[VocabularySettings.KEY_3] = box.Text;
        }

        protected override void SetGUI()
        {
            if (settings.Equals(VocabularySettings.KEY_1, VocabularySettings.OPTION_1_FIRST))
                rbtn1.IsChecked = true;
            else rbtn2.IsChecked = true;
            chbox1.IsChecked = settings.Bool(VocabularySettings.KEY_2);
            box.Text = settings[VocabularySettings.KEY_3];
        }

        private void btnCancel_Click(object sender, RoutedEventArgs e)
        {
            SetGUI();
            MainWindow.Navigation.NavigateBack();
        }

        private void btnOk_Click(object sender, RoutedEventArgs e)
        {
            ReadGUI();
            _parent.ApplySettings(settings);
            MainWindow.Navigation.NavigateBack();
        }
    }
}
