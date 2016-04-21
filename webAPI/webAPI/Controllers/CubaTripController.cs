using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using webAPI.Json;

namespace webAPI.Controllers
{
    [RoutePrefix("api/CubaTrip")]
    public class CubaTripController : ApiController
    {
        
        [Route("Products")]        
        public IEnumerable<JsonProduct> GetProducts()
        {
            return new JsonProduct[] {
               new JsonProduct
               {
                   productId = 1234,
                   name = "Restaurante1",
                   address = "La Vigia",
                   contact = "Jonito",
                   description = "None",
                   email = "jonito@gmail.com",
                   phone = "XXX-XXX-XXXX",
                   url = "http://cubarica.com"
               },

               new JsonProduct
               {
                   productId = 4567,
                   name = "Restaurante2",
                   address = "Vista Hermosa",
                   contact = "Alex",
                   description = "None",
                   email = "alex@gmail.com",
                   phone = "XXX-XXX-XXXX",
                   url = "http://cubarica2.com"
               }
            };
        }

       
        // POST: api/CubaTrip
        public void Post([FromBody]string value)
        {
        }

        // PUT: api/CubaTrip/5
        public void Put(int id, [FromBody]string value)
        {
        }

      
    }
}
