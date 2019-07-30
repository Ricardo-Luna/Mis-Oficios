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
    public class CarpetasViewModels
    {
        
        public Guid IdCarpeta { get; set; }
        public Guid IdUsuarioPropietario { get; set; }
        public string Nombre { get; set; }
        public bool Recibidos { get; set; }
        public bool Enviados { get; set; }
        public bool Borradores { get; set; }
        public Guid IdUsuarioActualizacion { get; set; }
        public DateTime FechaActualizacion { get; set; }
    }

    public class CarpetasEncabezadosViewModel
    {
        public ICollection<CarpetasViewModels> Carpetas { get; set; }
    }
    
 }
