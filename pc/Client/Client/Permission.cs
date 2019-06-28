using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing;
using System.Data;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using Google.Cloud.Firestore;

namespace Client
{
    public partial class Permission : UserControl
    {
        List<Store> stores = new List<Store>();
        public Permission()
        {
            InitializeComponent();
            
        }
        #region total 테이블 정보 ListView에 띄어주기(수정완료)

        string u_name;
        public void ShowTotal()
        {
            //   string bar = getDataFromPHP(url + "Building.php");

            listView2.Items.Clear();                                // View가 중복조회 되는것을 방지한다.
                                                                    //state가 0인 경우 리스트 뷰 추가 x
            #region testcode
            foreach (var a in stores)
            {
                if (a.permission_state == 1)
                    continue;
                ListViewItem listitem = new ListViewItem(a.email);
                listitem.SubItems.Add(a.password);
                listitem.SubItems.Add(a.name);
                listitem.SubItems.Add(a.location);
                listitem.SubItems.Add(a.bill.ToString());
                listitem.SubItems.Add(a.phone);
                listitem.SubItems.Add(a.image);
                listitem.SubItems.Add(a.menu);
                listitem.SubItems.Add(a.size.ToString());
                listView2.Items.Add(listitem);
            }
            #endregion



        }
        #endregion

        #region 테이블 행 선택시 오른쪽에 자료 표현
        private void listView2_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (listView2.SelectedItems.Count == 1)
            {
                ListView.SelectedListViewItemCollection items = listView2.SelectedItems;
                ListViewItem lvItem = items[0];
                textBox1.Text = lvItem.SubItems[2].Text; //ID
                u_name = textBox1.Text;

            }
        }
        #endregion
        #region 데이터 수정
        private async void UpdateDoc()
        {
            FirestoreDb db = FirestoreDb.Create("cupofsoju-a47b2");
            string updateid = null;
            // [START fs_delete_doc]
            CollectionReference usersRef = db.Collection("Store");
            QuerySnapshot snapshot = await usersRef.GetSnapshotAsync();
            foreach (DocumentSnapshot document in snapshot.Documents)
            {
                Dictionary<string, object> documentDictionary = document.ToDictionary();
                if (documentDictionary["name"].ToString() == u_name)
                {
                    updateid = document.Id;
                }
            }
            if (updateid != null)
            {
                DocumentReference cityRef = db.Collection("Store").Document(updateid);

                // Update age and favorite color
                Dictionary<string, object> updates = new Dictionary<string, object>
                    {
                        { "permission_state", 1},
                    };

                // Asynchronously update the document
                await cityRef.UpdateAsync(updates);

            }
            MessageBox.Show("수정 완료");
            StoreParse();
        }
        #endregion

        private void button1_Click(object sender, EventArgs e)
        {
            //가맹 허가 코드
            UpdateDoc();
        }
        public async void StoreParse()
        {
            stores.Clear();
            FirestoreDb db = FirestoreDb.Create("cupofsoju-a47b2");
            CollectionReference usersRef = db.Collection("Store");
            QuerySnapshot snapshot = await usersRef.GetSnapshotAsync();
            foreach (DocumentSnapshot document in snapshot.Documents)
            {
                Store push = new Store();
                Dictionary<string, object> documentDictionary = document.ToDictionary();
                push.bill = int.Parse(documentDictionary["bill"].ToString());
                push.email = documentDictionary["email"].ToString();
                try
                {
                    push.location = documentDictionary["location"].ToString();
                }
                catch
                {
                    push.location = "0";
                }
                try
                {
                    push.image = documentDictionary["image"].ToString();
                }
                catch
                {
                    push.image = "0";
                }
                push.menu = documentDictionary["menu"].ToString();
                push.name = documentDictionary["name"].ToString();
                push.password = documentDictionary["password"].ToString();
                push.phone = documentDictionary["phone"].ToString();
                push.permission_state = int.Parse(documentDictionary["permission_state"].ToString());
                try
                {
                    push.size = int.Parse(documentDictionary["size"].ToString());
                }
                catch
                {
                    push.size = 0;
                }
                push.storeId = documentDictionary["storeId"].ToString();
                stores.Add(push);
            }
            ShowTotal();
        }

        private void button2_Click(object sender, EventArgs e)
        {

        }

        private void button2_Click_1(object sender, EventArgs e)
        {
            StoreParse();
        }

        private void Permission_Load(object sender, EventArgs e)
        {
            StoreParse();
        }
    }
}
