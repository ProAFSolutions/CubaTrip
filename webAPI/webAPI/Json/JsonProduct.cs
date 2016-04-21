using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace webAPI.Json
{
    public class JsonProduct
    {
        public int productId { get; set; }

        public string name { get; set; }

        public string description { get; set; }

        public string contact { get; set; }

        public string address { get; set; }

        public string phone { get; set; }

        public string email { get; set; }

        public string url { get; set; }

        public JsonProduct() { }
    }
}