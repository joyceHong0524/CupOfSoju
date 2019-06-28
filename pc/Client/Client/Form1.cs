using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
<<<<<<< HEAD
=======
using Google.Cloud.Firestore;
>>>>>>> 0115a4c97f8e563c26cf3cdb6a8602e9dca0ca61

namespace Client
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
            storeList1.BringToFront();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            storeList1.BringToFront();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            permission1.BringToFront();
        }

        private void button3_Click(object sender, EventArgs e)
        {
            memberManage1.BringToFront();
        }

        private void button4_Click(object sender, EventArgs e)
        {
<<<<<<< HEAD
            billingMange1.BringToFront();
=======
            billing1.BringToFront();
>>>>>>> 0115a4c97f8e563c26cf3cdb6a8602e9dca0ca61
        }
    }
}
