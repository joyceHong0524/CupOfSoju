﻿namespace Client
{
    partial class StoreList
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
            this.listView1 = new System.Windows.Forms.ListView();
            this.Lv_Id = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.Lv_Pwd = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.Lv_Name = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.Lv_Locate = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.Lv_Bill = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.Lv_Phone = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.Lv_Image = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.Lv_Menu = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.Lv_Size = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.button2 = new System.Windows.Forms.Button();
            this.label1 = new System.Windows.Forms.Label();
            this.textBox1 = new System.Windows.Forms.TextBox();
            this.pictureBox1 = new System.Windows.Forms.PictureBox();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).BeginInit();
            this.SuspendLayout();
            // 
            // listView1
            // 
            this.listView1.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.Lv_Id,
            this.Lv_Pwd,
            this.Lv_Name,
            this.Lv_Locate,
            this.Lv_Bill,
            this.Lv_Phone,
            this.Lv_Image,
            this.Lv_Menu,
            this.Lv_Size});
            this.listView1.Location = new System.Drawing.Point(16, 61);
            this.listView1.Name = "listView1";
            this.listView1.Size = new System.Drawing.Size(447, 228);
            this.listView1.TabIndex = 0;
            this.listView1.UseCompatibleStateImageBehavior = false;
            this.listView1.View = System.Windows.Forms.View.Details;
            this.listView1.SelectedIndexChanged += new System.EventHandler(this.listView1_SelectedIndexChanged);
            this.listView1.DoubleClick += new System.EventHandler(this.listView1_DoubleClick);
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
            // button2
            // 
            this.button2.Location = new System.Drawing.Point(486, 241);
            this.button2.Name = "button2";
            this.button2.Size = new System.Drawing.Size(100, 23);
            this.button2.TabIndex = 2;
            this.button2.Text = "가맹점 삭제";
            this.button2.UseVisualStyleBackColor = true;
            this.button2.Click += new System.EventHandler(this.button2_Click);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(204, 19);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(97, 15);
            this.label1.TabIndex = 3;
            this.label1.Text = "가맹점리스트";
            // 
            // textBox1
            // 
            this.textBox1.Location = new System.Drawing.Point(486, 210);
            this.textBox1.Name = "textBox1";
            this.textBox1.Size = new System.Drawing.Size(100, 25);
            this.textBox1.TabIndex = 4;
            // 
            // pictureBox1
            // 
            this.pictureBox1.Location = new System.Drawing.Point(485, 61);
            this.pictureBox1.Name = "pictureBox1";
            this.pictureBox1.Size = new System.Drawing.Size(100, 133);
            this.pictureBox1.TabIndex = 5;
            this.pictureBox1.TabStop = false;
            // 
            // StoreList
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.pictureBox1);
            this.Controls.Add(this.textBox1);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.button2);
            this.Controls.Add(this.listView1);
            this.Name = "StoreList";
            this.Size = new System.Drawing.Size(631, 333);
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ListView listView1;
        private System.Windows.Forms.Button button2;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.TextBox textBox1;
        private System.Windows.Forms.ColumnHeader Lv_Id;
        private System.Windows.Forms.ColumnHeader Lv_Pwd;
        private System.Windows.Forms.ColumnHeader Lv_Name;
        private System.Windows.Forms.ColumnHeader Lv_Locate;
        private System.Windows.Forms.ColumnHeader Lv_Bill;
        private System.Windows.Forms.ColumnHeader Lv_Phone;
        private System.Windows.Forms.ColumnHeader Lv_Image;
        private System.Windows.Forms.ColumnHeader Lv_Menu;
        private System.Windows.Forms.ColumnHeader Lv_Size;
        private System.Windows.Forms.PictureBox pictureBox1;
    }
}
