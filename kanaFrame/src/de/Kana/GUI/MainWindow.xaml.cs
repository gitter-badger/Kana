using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.InteropServices;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Interop;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace KanaFrame
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        private IPage lastPage;
        public MainWindow()
        {

            InitializeComponent();
            _mainFrame.Navigate(new PageMain(_mainFrame));
            //pageTransitionControl.ShowPage(new PageMain(new Frame()));
        }

        private void _mainFrame_Navigated(object sender, NavigationEventArgs e)
        {
            IPage page = ((IPage)(_mainFrame.NavigationService.Content));
            page.onNavigate(lastPage);
            lastPage = page;
        }
    }
}
