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

        private Frame _mainFrame;
        private IContentPage _parent;
        private Dictionary<String, String> currentSettings;


        public PageSymbolSettings(Frame _mainFrame, IContentPage _parent)
        {
            InitializeComponent();
            this._mainFrame = _mainFrame;
            this._parent = _parent;
            currentSettings = new Dictionary<string, string>();
            currentSettings[Settings.MODE_KEY] = MODE_MATCH;
        }

        private void btnCancel_Click(object sender, RoutedEventArgs e)
        {
            revertContent();
            _mainFrame.NavigationService.GoBack();
        }

        private void btnOk_Click(object sender, RoutedEventArgs e)
        {
            setSettings();
            _parent.applySettings(currentSettings);
            _mainFrame.NavigationService.GoBack();
        }

        public void setSettings()
        {
            if ((bool)rbtnMatch.IsChecked) currentSettings[Settings.MODE_KEY] = MODE_MATCH;
            else if ((bool)rbtnFlow.IsChecked) currentSettings[Settings.MODE_KEY] = MODE_FLOW;
        }

        public void revertContent()
        {
            switch (currentSettings[Settings.MODE_KEY])
            {
                case MODE_MATCH: rbtnMatch.IsChecked = true; break;
                case MODE_FLOW: rbtnFlow.IsChecked = true; break;
            }
        }

        public override void HandleKeyDown(KeyEventArgs e)
        {
            base.HandleKeyDown(e);
            if (e.Key == Key.Escape)
                btnCancel_Click(null, null);
        }
    }
}
