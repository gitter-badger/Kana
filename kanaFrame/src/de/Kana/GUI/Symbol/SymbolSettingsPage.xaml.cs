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
    public partial class SymbolSettingsPage : SettingsPage
    {
        private SymbolSettings settings;
        private SymbolPage _parent;

        public SymbolSettingsPage(SymbolPage _parent)
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
                : new SymbolSettings();
            SetGUI();
        }

        protected override void ReadGUI()
        {
            settings[Settings.KEY_MODE] = (rbtnFlow.IsChecked == true) ? SymbolSettings.MODE_FLOW : SymbolSettings.MODE_MATCH;
            settings.Bool(SymbolSettings.KEY_LEARNING, chboxLeaning.IsChecked == true);
            settings.Bool(SymbolSettings.KEY_MARKS, chboxMarks.IsChecked == true);
        }

        protected override void SetGUI()
        {
            if (settings.Equals(Settings.KEY_MODE, SymbolSettings.MODE_MATCH)) rbtnMatch.IsChecked = true; else rbtnFlow.IsChecked = true;
            chboxLeaning.IsChecked = settings.Bool(SymbolSettings.KEY_LEARNING);
            chboxMarks.IsChecked = settings.Bool(SymbolSettings.KEY_MARKS);
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
