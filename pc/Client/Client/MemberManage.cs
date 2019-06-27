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
    public partial class MemberManage : UserControl
    {
        List<User> users = new List<User>();
        public MemberManage()
        {
            InitializeComponent();
            
           
        }
        #region total 테이블 정보 ListView에 띄어주기(수정완료)

        public void ShowTotal()
        {
            //   string bar = getDataFromPHP(url + "Building.php");
            listView1.Items.Clear();                               // View가 중복조회 되는것을 방지한다.
            //state가 0인 경우 리스트 뷰 추가 
            #region testcode
            foreach(var a in users)
            {
                ListViewItem listitem = new ListViewItem(a.email);
                listitem.SubItems.Add(a.password);
                listitem.SubItems.Add(a.name);

                listitem.SubItems.Add(a.location);
                listitem.SubItems.Add(a.phone);

                listitem.SubItems.Add(a.bill.ToString());
                listitem.SubItems.Add(a.type.ToString());
                listitem.SubItems.Add(a.monthLeft.ToString());
                listitem.SubItems.Add(a.todayLeft.ToString());
                listView1.Items.Add(listitem);
            }
            #endregion

        }
        #endregion
        public async void UserParse()
        {
            users.Clear();
            FirestoreDb db = FirestoreDb.Create("cupofsoju-a47b2");
            CollectionReference usersRef = db.Collection("User");
            QuerySnapshot snapshot = await usersRef.GetSnapshotAsync();
            foreach (DocumentSnapshot document in snapshot.Documents)
            {
                User push = new User();
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
                push.name = documentDictionary["name"].ToString();
                push.password = documentDictionary["password"].ToString();
                push.phone = documentDictionary["phone"].ToString();
                push.todayLeft = int.Parse(documentDictionary["todayLeft"].ToString().ToString());
                push.type = int.Parse(documentDictionary["type"].ToString());
                push.monthLeft = int.Parse(documentDictionary["monthLeft"].ToString());
                users.Add(push);
            }
            ShowTotal();
        }

        #region 데이터 수정
        private async void UpdateDoc()
        {
            FirestoreDb db = FirestoreDb.Create("cupofsoju-a47b2");
            string updateid = null;
            // [START fs_delete_doc]
            CollectionReference usersRef = db.Collection("User");
            QuerySnapshot snapshot = await usersRef.GetSnapshotAsync();
            foreach (DocumentSnapshot document in snapshot.Documents)
            {
                DocumentReference cityRef = db.Collection("User").Document(document.Id);

                // Update age and favorite color
                Dictionary<string, object> updates = new Dictionary<string, object>
                    {
                        { "todayLeft", 0},
                    };

                // Asynchronously update the document
                await cityRef.UpdateAsync(updates);
            }

            MessageBox.Show("수정 완료");
            UserParse();
        }
        #endregion

        private void button1_Click(object sender, EventArgs e)
        {
           UpdateDoc();
        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void button2_Click(object sender, EventArgs e)
        {
            UserParse();
        }

        private void MemberManage_Load(object sender, EventArgs e)
        {
            UserParse();
        }
    }
}
