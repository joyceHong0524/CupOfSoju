using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Client
{
    class User
    {
        public string Id { get; set; }
        public int bill { get; set; }
        public  string email { get; set; }
        public string location { get; set; }
        public int monthLeft { get; set; }
        public string name { get; set; }
        public string password { get; set; }
        public string phone { get; set; }
        public int todayLeft { get; set; }
        public int type { get; set; }
    }

    class Store
    {
        public string Id { get; set; }
        public int bill { get; set; }
        public string email { get; set; }
        public string image { get; set; }
        public string location { get; set; }
        public string menu { get; set; }
        public string name { get; set; }
        public string password { get; set; }
        public int permission_state { get; set; }
        public string phone { get; set; }
        public int size { get; set; }
        public string storeId { get; set; }
    }
}
