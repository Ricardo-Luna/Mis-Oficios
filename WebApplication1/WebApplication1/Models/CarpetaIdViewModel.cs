using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using System.Text;
using System.Threading.Tasks;
using WebApplication1.Content.Infrastructure;
using WebApplication1.Models;

namespace WebApplication1.Models
{
    public class CarpetaIdViewModel
    {
        
        public Guid IdCarpeta { get; set; }
    }

    public class CarpetasIdEncabezadoViewModel
    {
        public ICollection<CarpetaIdViewModel> Carpetas { get; set; }
    }
}
