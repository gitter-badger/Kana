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
        private List<Vocable> items;

        public DebugForm() {
            InitializeComponent();
            init();
        }

        private void init() {
            items = new List<Vocable>();
        }

        public void ShowItems(List<Vocable> items) {
            this.items = items;
            foreach (object obj in items) {
                hiraKataBox.Items.Add(obj);
            }
            hiraKataBox.Refresh();
        }

        private void hiraKataBox_SelectedIndexChanged(object sender, EventArgs e) {
            foreach (object obj in ((Vocable)((ListBox)sender).SelectedItem).Kanji) {
                kanjiBox.Items.Add(obj);
            }
            kanjiBox.Refresh();
        }
    }
}
