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
    /// Interaction logic for ContentControl.xaml
    /// </summary>
    public partial class ContentControl : UserControl
    {
        private UserControl _root;
        public ContentControl(UserControl _root)
        {
            this._root = _root;
            InitializeComponent();
        }
        public ContentControl()
        {
            InitializeComponent();
        }

        private void Label_Click(object sender, RoutedEventArgs e)
        {

        }
    }
}
