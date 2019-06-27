using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing;
using System.Data;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Client
{
    public partial class StoreList : UserControl
    {
        public StoreList()
        {
            InitializeComponent();
            ShowTotal();
        }

        #region detail 테이블 행 선택시 오른쪽에 자료표현
        private void listView1_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (listView1.SelectedItems.Count == 1)
            {
                ListView.SelectedListViewItemCollection items = listView1.SelectedItems;
                ListViewItem lvItem = items[0];
                textBox1.Text = lvItem.SubItems[0].Text; //ID

            }
            //pictureBox1.ImageLocation = imgsrc;
        }

        #endregion

        #region 리스트뷰 정보
        #region total 테이블 정보 ListView에 띄어주기(수정완료)

        public void ShowTotal()
        {
        //   string bar = getDataFromPHP(url + "Building.php");

           listView1.Items.Clear();                                // View가 중복조회 되는것을 방지한다.
            //state가 0인 경우 리스트 뷰 추가 x
            #region testcode
            ListViewItem listitem = new ListViewItem("ID");
            listitem.SubItems.Add("PWD");
            listitem.SubItems.Add("NAME");
            listitem.SubItems.Add("LOCATE");
            listitem.SubItems.Add("BILL");
            listitem.SubItems.Add("PHONE");
            listitem.SubItems.Add("IMAGE");
            listitem.SubItems.Add("MENU");
            listitem.SubItems.Add("SIZE");
            listView1.Items.Add(listitem);
            #endregion

           

        }
        #endregion

        #endregion

        private void listView1_DoubleClick(object sender, EventArgs e)
        {
           
        }

        private void button2_Click(object sender, EventArgs e)
        {
            //DB 삭제 알고리즘
        }
    }
}
