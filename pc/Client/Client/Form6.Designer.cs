namespace Client
{
    partial class Form6
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.memberManage1 = new Client.MemberManage();
            this.SuspendLayout();
            // 
            // memberManage1
            // 
            this.memberManage1.Location = new System.Drawing.Point(52, 46);
            this.memberManage1.Name = "memberManage1";
            this.memberManage1.Size = new System.Drawing.Size(687, 372);
            this.memberManage1.TabIndex = 0;
            // 
            // Form6
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.memberManage1);
            this.Name = "Form6";
            this.Text = "Form6";
            this.ResumeLayout(false);

        }

        #endregion

        private MemberManage memberManage1;
    }
}