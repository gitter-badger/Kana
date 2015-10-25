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
    /// Interaction logic for PageSymbolSettings.xaml
    /// </summary>
    public partial class PageSymbolSettings : SettingsPage
    {
        public const string MODE_MATCH = "MATCH";
        public const string MODE_FLOW = "FLOW";
        public const string OPTION_KEY_MARKS = "MARKS";
        public const string OPTION_KEY_LEARNING = "LEARNING";

        private ContentPage _parent;

        public PageSymbolSettings(ContentPage _parent)
        {
            InitializeComponent();
            this._parent = _parent;
            currentSettings = GetDefaultSettings();
            SetGUI();
        }

        protected override void ReadGUI()
        {
            if ((bool)rbtnMatch.IsChecked) currentSettings[Settings.MODE_KEY] = MODE_MATCH;
            else if ((bool)rbtnFlow.IsChecked) currentSettings[Settings.MODE_KEY] = MODE_FLOW;
            currentSettings[OPTION_KEY_LEARNING] = (chboxLeaning.IsChecked == true) ? Settings.OPTION_TRUE : Settings.OPTION_FALSE;
            currentSettings[OPTION_KEY_MARKS] = (chboxMarks.IsChecked == true) ? Settings.OPTION_TRUE : Settings.OPTION_FALSE;
        }

        protected override void SetGUI()
        {
            switch (currentSettings[Settings.MODE_KEY])
            {
                case MODE_MATCH: rbtnMatch.IsChecked = true; break;
                case MODE_FLOW: rbtnFlow.IsChecked = true; break;
            }
            chboxLeaning.IsChecked = Settings.ToBool(currentSettings[OPTION_KEY_LEARNING]);
            chboxMarks.IsChecked = Settings.ToBool(currentSettings[OPTION_KEY_MARKS]);
        }

        public override void DefaultSettings(bool apply)
        {
            currentSettings = GetDefaultSettings();
            SetGUI();
            if (apply) _parent.ApplySettings(currentSettings);
        }

        public override Dictionary<string, string> GetDefaultSettings()
        {
            Dictionary<string, string> result = new Dictionary<string, string>();
            result[Settings.MODE_KEY] = MODE_MATCH;
            result[OPTION_KEY_LEARNING] = Settings.OPTION_TRUE;
            result[OPTION_KEY_MARKS] = Settings.OPTION_FALSE;
            return result;
        }




        public override void HandleKeyDown(KeyEventArgs e)
        {
            base.HandleKeyDown(e);
            if (e.Key == Key.Escape)
                btnCancel_Click(null, null);
        }




        private void btnCancel_Click(object sender, RoutedEventArgs e)
        {
            SetGUI();
            MainWindow.Navigation.NavigateBack();
        }

        private void btnOk_Click(object sender, RoutedEventArgs e)
        {
            ReadGUI();
            _parent.ApplySettings(currentSettings);
            MainWindow.Navigation.NavigateBack();
        }
    }
}
