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
    public partial class Billing : UserControl
    {
      
        public Billing()
        {
            InitializeComponent();
           
        }

        List<Store> stores = new List<Store>();
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
                setChart();
            }
            
        }

        public void setChart()
        {
            this.chart1.ChartAreas[0].AxisX.Minimum = 0;
            this.chart1.ChartAreas[0].AxisX.Maximum = 10;
            this.chart1.ChartAreas[0].AxisX.Interval = 1;
            chart1.Series.Clear();
            foreach(var a in stores)
            {
                try
                {
                    chart1.Series.Add(a.name);
                }
                catch
                {
                    continue;
                }
                chart1.Series[a.name].Points.Clear();
                chart1.Series[a.name].Points.Add(a.bill);  // X=1

            }
            
        }

        private void Billing_Load(object sender, EventArgs e)
        {
            StoreParse();
        }

        private void chart1_DoubleClick(object sender, EventArgs e)
        {
            StoreParse();
        }
    }
}
