namespace Kana.src.de.Kana.GUI {
    partial class DebugForm {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing) {
            if (disposing && (components != null)) {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent() {
            this.hiraKataBox = new System.Windows.Forms.ListBox();
            this.kanjiBox = new System.Windows.Forms.ListBox();
            this.englishBox = new System.Windows.Forms.ListBox();
            this.germanBox = new System.Windows.Forms.ListBox();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.vocTypeLabel = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // hiraKataBox
            // 
            this.hiraKataBox.FormattingEnabled = true;
            this.hiraKataBox.ItemHeight = 16;
            this.hiraKataBox.Location = new System.Drawing.Point(3, 8);
            this.hiraKataBox.Name = "hiraKataBox";
            this.hiraKataBox.Size = new System.Drawing.Size(267, 244);
            this.hiraKataBox.TabIndex = 0;
            this.hiraKataBox.SelectedIndexChanged += new System.EventHandler(this.hiraKataBox_SelectedIndexChanged);
            // 
            // kanjiBox
            // 
            this.kanjiBox.FormattingEnabled = true;
            this.kanjiBox.ItemHeight = 16;
            this.kanjiBox.Location = new System.Drawing.Point(276, 40);
            this.kanjiBox.Name = "kanjiBox";
            this.kanjiBox.Size = new System.Drawing.Size(120, 212);
            this.kanjiBox.TabIndex = 1;
            // 
            // englishBox
            // 
            this.englishBox.FormattingEnabled = true;
            this.englishBox.ItemHeight = 16;
            this.englishBox.Location = new System.Drawing.Point(404, 40);
            this.englishBox.Name = "englishBox";
            this.englishBox.Size = new System.Drawing.Size(120, 212);
            this.englishBox.TabIndex = 2;
            // 
            // germanBox
            // 
            this.germanBox.FormattingEnabled = true;
            this.germanBox.ItemHeight = 16;
            this.germanBox.Location = new System.Drawing.Point(530, 40);
            this.germanBox.Name = "germanBox";
            this.germanBox.Size = new System.Drawing.Size(120, 212);
            this.germanBox.TabIndex = 3;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(277, 17);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(39, 17);
            this.label1.TabIndex = 4;
            this.label1.Text = "Kanji";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(404, 17);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(61, 17);
            this.label2.TabIndex = 5;
            this.label2.Text = "Englisch";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(531, 17);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(60, 17);
            this.label3.TabIndex = 6;
            this.label3.Text = "Deutsch";
            // 
            // vocTypeLabel
            // 
            this.vocTypeLabel.AutoSize = true;
            this.vocTypeLabel.Location = new System.Drawing.Point(13, 259);
            this.vocTypeLabel.Name = "vocTypeLabel";
            this.vocTypeLabel.Size = new System.Drawing.Size(0, 17);
            this.vocTypeLabel.TabIndex = 7;
            // 
            // DebugForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(655, 632);
            this.Controls.Add(this.vocTypeLabel);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.germanBox);
            this.Controls.Add(this.englishBox);
            this.Controls.Add(this.kanjiBox);
            this.Controls.Add(this.hiraKataBox);
            this.Name = "DebugForm";
            this.Text = "Debug";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ListBox hiraKataBox;
        private System.Windows.Forms.ListBox kanjiBox;
        private System.Windows.Forms.ListBox englishBox;
        private System.Windows.Forms.ListBox germanBox;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label vocTypeLabel;
    }
}