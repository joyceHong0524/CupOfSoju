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
            this.label1 = new System.Windows.Forms.Label();
            this.Lv_Id = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.Lv_Pwd = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.Lv_Name = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.Lv_Locate = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.Lv_Phone = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.Lv_Bill = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.Lv_Left = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.Lv_Today = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.Lv_Type = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.SuspendLayout();
            // 
            // listView1
            // 
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
            this.listView1.Location = new System.Drawing.Point(38, 72);
            this.listView1.Name = "listView1";
            this.listView1.Size = new System.Drawing.Size(566, 246);
            this.listView1.TabIndex = 0;
            this.listView1.UseCompatibleStateImageBehavior = false;
            this.listView1.View = System.Windows.Forms.View.Details;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(251, 33);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(67, 15);
            this.label1.TabIndex = 2;
            this.label1.Text = "회원관리";
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
            // Lv_Type
            // 
            this.Lv_Type.DisplayIndex = 8;
            this.Lv_Type.Text = "Type";
            // 
            // MemberManage
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.label1);
            this.Controls.Add(this.listView1);
            this.Name = "MemberManage";
            this.Size = new System.Drawing.Size(687, 372);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ListView listView1;
        private System.Windows.Forms.ColumnHeader Lv_Id;
        private System.Windows.Forms.ColumnHeader Lv_Pwd;
        private System.Windows.Forms.ColumnHeader Lv_Name;
        private System.Windows.Forms.ColumnHeader Lv_Locate;
        private System.Windows.Forms.ColumnHeader Lv_Phone;
        private System.Windows.Forms.ColumnHeader Lv_Bill;
        private System.Windows.Forms.ColumnHeader Lv_Left;
        private System.Windows.Forms.ColumnHeader Lv_Today;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.ColumnHeader Lv_Type;
    }
}
