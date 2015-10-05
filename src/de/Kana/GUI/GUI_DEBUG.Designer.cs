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
            this.textBox1 = new System.Windows.Forms.TextBox();
            this.vocableL = new System.Windows.Forms.Label();
            this.nextB = new System.Windows.Forms.Button();
            this.streamOutputLabel = new System.Windows.Forms.Label();
            this.checkButton = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // textBox1
            // 
            this.textBox1.Font = new System.Drawing.Font("MS Gothic", 48F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.textBox1.Location = new System.Drawing.Point(12, 146);
            this.textBox1.Name = "textBox1";
            this.textBox1.Size = new System.Drawing.Size(736, 71);
            this.textBox1.TabIndex = 0;
            this.textBox1.TextChanged += new System.EventHandler(this.textBox1_TextChanged);
            // 
            // vocableL
            // 
            this.vocableL.AutoSize = true;
            this.vocableL.Location = new System.Drawing.Point(12, 9);
            this.vocableL.Name = "vocableL";
            this.vocableL.Size = new System.Drawing.Size(0, 13);
            this.vocableL.TabIndex = 1;
            // 
            // nextB
            // 
            this.nextB.Location = new System.Drawing.Point(673, 223);
            this.nextB.Name = "nextB";
            this.nextB.Size = new System.Drawing.Size(75, 23);
            this.nextB.TabIndex = 2;
            this.nextB.Text = "next";
            this.nextB.UseVisualStyleBackColor = true;
            this.nextB.Click += new System.EventHandler(this.nextB_Click);
            // 
            // streamOutputLabel
            // 
            this.streamOutputLabel.AutoSize = true;
            this.streamOutputLabel.Location = new System.Drawing.Point(18, 9);
            this.streamOutputLabel.Name = "streamOutputLabel";
            this.streamOutputLabel.Size = new System.Drawing.Size(35, 13);
            this.streamOutputLabel.TabIndex = 3;
            this.streamOutputLabel.Text = "label1";
            // 
            // checkButton
            // 
            this.checkButton.Location = new System.Drawing.Point(592, 223);
            this.checkButton.Name = "checkButton";
            this.checkButton.Size = new System.Drawing.Size(75, 23);
            this.checkButton.TabIndex = 4;
            this.checkButton.Text = "check";
            this.checkButton.UseVisualStyleBackColor = true;
            this.checkButton.Click += new System.EventHandler(this.checkButton_Click);
            // 
            // DebugForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(762, 257);
            this.Controls.Add(this.checkButton);
            this.Controls.Add(this.streamOutputLabel);
            this.Controls.Add(this.nextB);
            this.Controls.Add(this.vocableL);
            this.Controls.Add(this.textBox1);
            this.Margin = new System.Windows.Forms.Padding(2);
            this.Name = "DebugForm";
            this.Text = "Debug";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.TextBox textBox1;
        private System.Windows.Forms.Label vocableL;
        private System.Windows.Forms.Button nextB;
        private System.Windows.Forms.Label streamOutputLabel;
        private System.Windows.Forms.Button checkButton;
    }
}