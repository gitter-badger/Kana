using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace Kana.src.de.Kana.GUI {
    public partial class DebugForm : Form {
        public DebugForm() {
            InitializeComponent();
            
        }

        private void listBox1_SelectedIndexChanged(object sender, EventArgs e) {

        }

        public void showItems(LinkedList<Vocable> items) {
            foreach (object obj in items) {
                debugBox.Items.Add(obj);
            }
            debugBox.Refresh();
        }
    }
}
