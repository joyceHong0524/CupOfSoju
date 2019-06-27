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
using Google.Apis.Auth.OAuth2;
using Google.Cloud.Storage.V1;
using Google.Cloud.Firestore.V1;

namespace Client
{
    public partial class StoreList : UserControl
    {
        public static string d_name;
         List<Store> stores = new List<Store>();
      
        public StoreList()
        {
            InitializeComponent();
            
        }

        #region detail 테이블 행 선택시 오른쪽에 자료표현
        private void listView1_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (listView1.SelectedItems.Count == 1)
            {
                ListView.SelectedListViewItemCollection items = listView1.SelectedItems;
                ListViewItem lvItem = items[0];
                textBox1.Text = lvItem.SubItems[2].Text; //ID
                d_name = textBox1.Text;
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
            foreach (var a in stores)
            {
                if (a.permission_state == 0)
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
                listView1.Items.Add(listitem);
            }
            #endregion



        }
        #endregion

        #endregion
        #region 데이터 삭제
        private async void DeleteDoc()
        {
            FirestoreDb db = FirestoreDb.Create("cupofsoju-a47b2");
            string deleteid=null;
            // [START fs_delete_doc]
            CollectionReference usersRef = db.Collection("Store");
            QuerySnapshot snapshot = await usersRef.GetSnapshotAsync();
            foreach (DocumentSnapshot document in snapshot.Documents)
            {
                Dictionary<string, object> documentDictionary = document.ToDictionary();
                if (documentDictionary["name"].ToString() == d_name)
                {
                    deleteid = document.Id;
                }
            }
            if (deleteid != null)
            {
                DocumentReference cityRef = db.Collection("Store").Document(deleteid);
                await cityRef.DeleteAsync();
            }
            MessageBox.Show("삭제 완료");
            //StoreParse();
        }
        #endregion

        #region 데이터 수정
        private static async Task UpdateDoc()
        {
            FirestoreDb db = FirestoreDb.Create("cupofsoju-a47b2");
            // [START fs_delete_field]
            DocumentReference cityRef = db.Collection("Store").Document("HCsnqyAD8yfAArCcRHSI");
            Dictionary<string, object> updates = new Dictionary<string, object>
            {
                { "Capital", FieldValue.Delete }
            };
            await cityRef.UpdateAsync(updates);
            // [END fs_delete_field]
            Console.WriteLine("Deleted the Capital field from the BJ document in the cities collection.");
        }
        #endregion


        private void listView1_DoubleClick(object sender, EventArgs e)
        {
           
        }

        private void button2_Click(object sender, EventArgs e)
        {
            //DB 삭제 알고리즘
            DeleteDoc();
        }
  

      
        private void button1_Click(object sender, EventArgs e)
        {
            //AuthExplicit("cupofsoju-a47b2");
            // AuthImplicit("cupofsoju-a47b2");

           // StoreParse();

        }

        private void button1_Click_1(object sender, EventArgs e)
        {
            StoreParse();
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
                push.Id = document.Id;
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

        private void StoreList_Load(object sender, EventArgs e)
        {
            StoreParse();
        }
    }
}
