namespace Client
{
    partial class Form1
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

        #region Windows Form 디자이너에서 생성한 코드

        /// <summary>
        /// 디자이너 지원에 필요한 메서드입니다. 
        /// 이 메서드의 내용을 코드 편집기로 수정하지 마세요.
        /// </summary>
        private void InitializeComponent()
        {
            this.button1 = new System.Windows.Forms.Button();
            this.button2 = new System.Windows.Forms.Button();
            this.button3 = new System.Windows.Forms.Button();
            this.button4 = new System.Windows.Forms.Button();
            this.storeList1 = new Client.StoreList();
            this.permission1 = new Client.Permission();
            this.memberManage1 = new Client.MemberManage();
            this.billingMange1 = new Client.BillingMange();
            this.SuspendLayout();
            // 
            // button1
            // 
            this.button1.Location = new System.Drawing.Point(12, 12);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(82, 70);
            this.button1.TabIndex = 0;
            this.button1.Text = "가맹점리스트";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // button2
            // 
            this.button2.Location = new System.Drawing.Point(12, 108);
            this.button2.Name = "button2";
            this.button2.Size = new System.Drawing.Size(82, 76);
            this.button2.TabIndex = 1;
            this.button2.Text = "허가";
            this.button2.UseVisualStyleBackColor = true;
            this.button2.Click += new System.EventHandler(this.button2_Click);
            // 
            // button3
            // 
            this.button3.Location = new System.Drawing.Point(12, 204);
            this.button3.Name = "button3";
            this.button3.Size = new System.Drawing.Size(82, 78);
            this.button3.TabIndex = 2;
            this.button3.Text = "회원 관리";
            this.button3.UseVisualStyleBackColor = true;
            this.button3.Click += new System.EventHandler(this.button3_Click);
            // 
            // button4
            // 
            this.button4.Location = new System.Drawing.Point(12, 310);
            this.button4.Name = "button4";
            this.button4.Size = new System.Drawing.Size(82, 78);
            this.button4.TabIndex = 2;
            this.button4.Text = "요금 관리";
            this.button4.UseVisualStyleBackColor = true;
            this.button4.Click += new System.EventHandler(this.button4_Click);
            // 
            // storeList1
            // 
            this.storeList1.Location = new System.Drawing.Point(118, 43);
            this.storeList1.Name = "storeList1";
            this.storeList1.Size = new System.Drawing.Size(631, 333);
            this.storeList1.TabIndex = 3;
            // 
            // permission1
            // 
            this.permission1.Location = new System.Drawing.Point(100, 57);
            this.permission1.Name = "permission1";
            this.permission1.Size = new System.Drawing.Size(697, 357);
            this.permission1.TabIndex = 4;
            // 
            // memberManage1
            // 
            this.memberManage1.Location = new System.Drawing.Point(110, 43);
            this.memberManage1.Name = "memberManage1";
            this.memberManage1.Size = new System.Drawing.Size(687, 372);
            this.memberManage1.TabIndex = 5;
            // 
            // billingMange1
            // 
            this.billingMange1.Location = new System.Drawing.Point(150, 51);
            this.billingMange1.Name = "billingMange1";
            this.billingMange1.Size = new System.Drawing.Size(599, 325);
            this.billingMange1.TabIndex = 6;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.billingMange1);
            this.Controls.Add(this.memberManage1);
            this.Controls.Add(this.permission1);
            this.Controls.Add(this.storeList1);
            this.Controls.Add(this.button4);
            this.Controls.Add(this.button3);
            this.Controls.Add(this.button2);
            this.Controls.Add(this.button1);
            this.Name = "Form1";
            this.Text = "Form1";
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.Button button2;
        private System.Windows.Forms.Button button3;
        private System.Windows.Forms.Button button4;
        private StoreList storeList1;
        private Permission permission1;
        private MemberManage memberManage1;
        private BillingMange billingMange1;
    }
}

