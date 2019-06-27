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
    public partial class MemberManage : UserControl
    {
        public MemberManage()
        {
            InitializeComponent();
            ShowTotal();
        }
        #region total 테이블 정보 ListView에 띄어주기(수정완료)

        public void ShowTotal()
        {
            //   string bar = getDataFromPHP(url + "Building.php");

            listView1.Items.Clear();                                // View가 중복조회 되는것을 방지한다.
            //state가 0인 경우 리스트 뷰 추가 
            #region testcode
            ListViewItem listitem = new ListViewItem("ID");
            listitem.SubItems.Add("PWD");
            listitem.SubItems.Add("NAME");
            listitem.SubItems.Add("LOCATE");
            listitem.SubItems.Add("PHONE");
            listitem.SubItems.Add("BILL");
            listitem.SubItems.Add("Type");
            listitem.SubItems.Add("Left");
            listitem.SubItems.Add("Today");
            listView1.Items.Add(listitem);
            #endregion

        }
        #endregion
    }
}
