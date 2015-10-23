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
    public partial class MainWindow : Window, INavigate
    {
        private static INavigate navigation;
        public static INavigate Navigation
        {
            get
            {
                return navigation;
            }
            protected set
            {
                navigation = value;
            }
        }

        private KanaPage page;


        public MainWindow()
        {
            InitializeComponent();
            Navigation = this;
            _mainFrame.Navigate(new PageMain());
        }

        private void _mainFrame_Navigated(object sender, NavigationEventArgs e)
        {
            KanaPage last = page;
            page = ((KanaPage)(_mainFrame.NavigationService.Content));
            //Console.Out.WriteLine("Navigate: " + page);
            page.HandleOnNavigate(last);
        }

        protected override void OnPreviewKeyDown(KeyEventArgs e)
        {
            base.OnKeyDown(e);
            //Console.Out.WriteLine("KeyDown: " + page + " Key: " + e.Key);
            page.HandleKeyDown(e);
        }

        public void Navigate(KanaPage page)
        {
            _mainFrame.Navigate(page);
        }

        public void NavigateBack()
        {
            _mainFrame.NavigationService.GoBack();
        }
    }
}
