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
    public partial class PageSymbolSettings : KanaPage, ISettingsPage
    {

        public const string MODE_MATCH = "MATCH";
        public const string MODE_FLOW = "FLOW";

        private INavigate _window;
        private IContentPage _parent;
        private Dictionary<String, String> currentSettings;


        public PageSymbolSettings(INavigate _window, IContentPage _parent)
        {
            InitializeComponent();
            this._window = _window;
            this._parent = _parent;
            DefaultSettings(false);
        }

        private void btnCancel_Click(object sender, RoutedEventArgs e)
        {
            RevertContent();
            _window.NavigateBack();
        }

        private void btnOk_Click(object sender, RoutedEventArgs e)
        {
            SetSettings();
            _parent.ApplySettings(currentSettings);
            _window.NavigateBack();
        }

        public void SetSettings()
        {
            if ((bool)rbtnMatch.IsChecked) currentSettings[Settings.MODE_KEY] = MODE_MATCH;
            else if ((bool)rbtnFlow.IsChecked) currentSettings[Settings.MODE_KEY] = MODE_FLOW;
        }

        public void RevertContent()
        {
            switch (currentSettings[Settings.MODE_KEY])
            {
                case MODE_MATCH: rbtnMatch.IsChecked = true; break;
                case MODE_FLOW: rbtnFlow.IsChecked = true; break;
            }
        }

        public void DefaultSettings(bool apply)
        {
            currentSettings = new Dictionary<String, String>();
            currentSettings[Settings.MODE_KEY] = MODE_MATCH;
            RevertContent();
            if (apply) _parent.ApplySettings(currentSettings);
        }

        public override void HandleKeyDown(KeyEventArgs e)
        {
            base.HandleKeyDown(e);
            if (e.Key == Key.Escape)
                btnCancel_Click(null, null);
        }
    }
}
