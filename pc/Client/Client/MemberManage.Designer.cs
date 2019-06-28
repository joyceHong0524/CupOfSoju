namespace Client
{
    partial class MemberManage
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
<<<<<<< HEAD
            this.button1 = new System.Windows.Forms.Button();
=======
            this.Lv_Id = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.Lv_Pwd = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.Lv_Name = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.Lv_Locate = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.Lv_Phone = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.Lv_Bill = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.Lv_Type = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.Lv_Left = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.Lv_Today = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.button1 = new System.Windows.Forms.Button();
            this.button2 = new System.Windows.Forms.Button();
>>>>>>> 0115a4c97f8e563c26cf3cdb6a8602e9dca0ca61
            this.SuspendLayout();
            // 
            // listView1
            // 
<<<<<<< HEAD
            this.listView1.Location = new System.Drawing.Point(103, 79);
            this.listView1.Name = "listView1";
            this.listView1.Size = new System.Drawing.Size(485, 246);
            this.listView1.TabIndex = 0;
            this.listView1.UseCompatibleStateImageBehavior = false;
            // 
            // button1
            // 
            this.button1.Location = new System.Drawing.Point(304, 28);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(75, 23);
            this.button1.TabIndex = 1;
            this.button1.Text = "회원관리";
            this.button1.UseVisualStyleBackColor = true;
=======
            this.listView1.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.Lv_Id,
            this.Lv_Pwd,
            this.Lv_Name,
            this.Lv_Locate,
            this.Lv_Phone,
            this.Lv_Bill,
            this.Lv_Type,
            this.Lv_Left,
            this.Lv_Today});
            this.listView1.Dock = System.Windows.Forms.DockStyle.Right;
            this.listView1.Location = new System.Drawing.Point(121, 0);
            this.listView1.Name = "listView1";
            this.listView1.Size = new System.Drawing.Size(566, 372);
            this.listView1.TabIndex = 0;
            this.listView1.UseCompatibleStateImageBehavior = false;
            this.listView1.View = System.Windows.Forms.View.Details;
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
            // Lv_Phone
            // 
            this.Lv_Phone.Text = "Phone";
            // 
            // Lv_Bill
            // 
            this.Lv_Bill.Text = "Bill";
            // 
            // Lv_Type
            // 
            this.Lv_Type.DisplayIndex = 8;
            this.Lv_Type.Text = "Type";
            // 
            // Lv_Left
            // 
            this.Lv_Left.DisplayIndex = 6;
            this.Lv_Left.Text = "Left";
            // 
            // Lv_Today
            // 
            this.Lv_Today.DisplayIndex = 7;
            this.Lv_Today.Text = "Today";
            // 
            // button1
            // 
            this.button1.Location = new System.Drawing.Point(13, 3);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(92, 23);
            this.button1.TabIndex = 3;
            this.button1.Text = "일일초기화";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // button2
            // 
            this.button2.Location = new System.Drawing.Point(13, 41);
            this.button2.Name = "button2";
            this.button2.Size = new System.Drawing.Size(92, 23);
            this.button2.TabIndex = 4;
            this.button2.Text = "새로고침";
            this.button2.UseVisualStyleBackColor = true;
            this.button2.Click += new System.EventHandler(this.button2_Click);
>>>>>>> 0115a4c97f8e563c26cf3cdb6a8602e9dca0ca61
            // 
            // MemberManage
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
<<<<<<< HEAD
=======
            this.Controls.Add(this.button2);
>>>>>>> 0115a4c97f8e563c26cf3cdb6a8602e9dca0ca61
            this.Controls.Add(this.button1);
            this.Controls.Add(this.listView1);
            this.Name = "MemberManage";
            this.Size = new System.Drawing.Size(687, 372);
<<<<<<< HEAD
=======
            this.Load += new System.EventHandler(this.MemberManage_Load);
>>>>>>> 0115a4c97f8e563c26cf3cdb6a8602e9dca0ca61
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.ListView listView1;
<<<<<<< HEAD
        private System.Windows.Forms.Button button1;
=======
        private System.Windows.Forms.ColumnHeader Lv_Id;
        private System.Windows.Forms.ColumnHeader Lv_Pwd;
        private System.Windows.Forms.ColumnHeader Lv_Name;
        private System.Windows.Forms.ColumnHeader Lv_Locate;
        private System.Windows.Forms.ColumnHeader Lv_Phone;
        private System.Windows.Forms.ColumnHeader Lv_Bill;
        private System.Windows.Forms.ColumnHeader Lv_Left;
        private System.Windows.Forms.ColumnHeader Lv_Today;
        private System.Windows.Forms.ColumnHeader Lv_Type;
        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.Button button2;
>>>>>>> 0115a4c97f8e563c26cf3cdb6a8602e9dca0ca61
    }
}
