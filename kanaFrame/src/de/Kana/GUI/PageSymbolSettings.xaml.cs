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
    public partial class PageSymbolSettings : Page, ISettingsPageBase
    {
        private Frame _mainFrame;
        private IMainPageBase _parent;
        private Dictionary<String, String> currentSettings;


        public PageSymbolSettings(Frame _mainFrame, IMainPageBase _parent)
        {
            InitializeComponent();
            this._mainFrame = _mainFrame;
            this._parent = _parent;

        }

        private void btnCancel_Click(object sender, RoutedEventArgs e)
        {
            _mainFrame.NavigationService.GoBack();
            revertContent();
        }

        private void btnOk_Click(object sender, RoutedEventArgs e)
        {
            _mainFrame.NavigationService.GoBack();
            setSettings();
            _parent.applySettings(currentSettings);
        }

        public void setSettings()
        {
            throw new NotImplementedException();
        }

        public void revertContent()
        {
            throw new NotImplementedException();
        }
    }
}
