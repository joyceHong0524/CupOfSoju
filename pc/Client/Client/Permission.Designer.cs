namespace Client
{
    partial class Permission
    {
        /// <summary> 
        /// 필수 디자이너 변수입니다.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary> 
        /// 사용 중인 모든 리소스를 정리합니다.
        /// </summary>
        /// <param name="disposing">관리되는 리소스를 삭제해야 하면 true이고, 그렇지 않으면 false입니다.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region 구성 요소 디자이너에서 생성한 코드

        /// <summary> 
        /// 디자이너 지원에 필요한 메서드입니다. 
        /// 이 메서드의 내용을 코드 편집기로 수정하지 마세요.
        /// </summary>
        private void InitializeComponent()
        {
            this.label1 = new System.Windows.Forms.Label();
            this.pictureBox1 = new System.Windows.Forms.PictureBox();
            this.textBox1 = new System.Windows.Forms.TextBox();
            this.button1 = new System.Windows.Forms.Button();
            this.listView2 = new System.Windows.Forms.ListView();
            this.Lv_Id = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.Lv_Pwd = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.Lv_Name = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.Lv_Locate = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.Lv_Bill = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.Lv_Phone = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.Lv_Image = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.Lv_Menu = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.Lv_Size = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).BeginInit();
            this.SuspendLayout();
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(236, 17);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(37, 15);
            this.label1.TabIndex = 1;
            this.label1.Text = "허가";
            // 
            // pictureBox1
            // 
            this.pictureBox1.Location = new System.Drawing.Point(541, 56);
            this.pictureBox1.Name = "pictureBox1";
            this.pictureBox1.Size = new System.Drawing.Size(118, 158);
            this.pictureBox1.TabIndex = 2;
            this.pictureBox1.TabStop = false;
            // 
            // textBox1
            // 
            this.textBox1.Location = new System.Drawing.Point(541, 220);
            this.textBox1.Name = "textBox1";
            this.textBox1.Size = new System.Drawing.Size(114, 25);
            this.textBox1.TabIndex = 3;
            // 
            // button1
            // 
            this.button1.Location = new System.Drawing.Point(542, 260);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(116, 27);
            this.button1.TabIndex = 4;
            this.button1.Text = "가맹 허가";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // listView2
            // 
            this.listView2.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.Lv_Id,
            this.Lv_Pwd,
            this.Lv_Name,
            this.Lv_Locate,
            this.Lv_Bill,
            this.Lv_Phone,
            this.Lv_Image,
            this.Lv_Menu,
            this.Lv_Size});
            this.listView2.Location = new System.Drawing.Point(39, 56);
            this.listView2.Name = "listView2";
            this.listView2.Size = new System.Drawing.Size(447, 228);
            this.listView2.TabIndex = 5;
            this.listView2.UseCompatibleStateImageBehavior = false;
            this.listView2.View = System.Windows.Forms.View.Details;
            this.listView2.SelectedIndexChanged += new System.EventHandler(this.listView2_SelectedIndexChanged);
            // 
            // Lv_Id
            // 
            this.Lv_Id.Text = "ID";
            // 
            // Lv_Pwd
            // 
            this.Lv_Pwd.Text = "PWD";
            // 
            // Lv_Name
            // 
            this.Lv_Name.Text = "Name";
            // 
            // Lv_Locate
            // 
            this.Lv_Locate.Text = "Locate";
            // 
            // Lv_Bill
            // 
            this.Lv_Bill.Text = "Bill";
            // 
            // Lv_Phone
            // 
            this.Lv_Phone.Text = "Phone";
            // 
            // Lv_Image
            // 
            this.Lv_Image.Text = "Image";
            // 
            // Lv_Menu
            // 
            this.Lv_Menu.Text = "Menu";
            // 
            // Lv_Size
            // 
            this.Lv_Size.Text = "Size";
            // 
            // Permission
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.listView2);
            this.Controls.Add(this.button1);
            this.Controls.Add(this.textBox1);
            this.Controls.Add(this.pictureBox1);
            this.Controls.Add(this.label1);
            this.Name = "Permission";
            this.Size = new System.Drawing.Size(697, 357);
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.PictureBox pictureBox1;
        private System.Windows.Forms.TextBox textBox1;
        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.ListView listView2;
        private System.Windows.Forms.ColumnHeader Lv_Id;
        private System.Windows.Forms.ColumnHeader Lv_Pwd;
        private System.Windows.Forms.ColumnHeader Lv_Name;
        private System.Windows.Forms.ColumnHeader Lv_Locate;
        private System.Windows.Forms.ColumnHeader Lv_Bill;
        private System.Windows.Forms.ColumnHeader Lv_Phone;
        private System.Windows.Forms.ColumnHeader Lv_Image;
        private System.Windows.Forms.ColumnHeader Lv_Menu;
        private System.Windows.Forms.ColumnHeader Lv_Size;
    }
}
