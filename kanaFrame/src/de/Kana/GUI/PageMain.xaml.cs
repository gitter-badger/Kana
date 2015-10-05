﻿using System;
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
    /// Interaction logic for PageMain.xaml
    /// </summary>
    public partial class PageMain : Page
    {
        private MainWindow _main;
        private PageSymbol _pageSymbol;

        public PageMain(MainWindow _main)
        {
            InitializeComponent();
            this._main = _main;
            _pageSymbol = new PageSymbol(_main);
        }

        private void btnSymbol_Click(object sender, RoutedEventArgs e)
        {
            _main._mainFrame.Navigate(_pageSymbol);

        }
    }
}