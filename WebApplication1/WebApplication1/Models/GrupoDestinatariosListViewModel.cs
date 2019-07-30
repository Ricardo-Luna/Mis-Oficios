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
    public class GrupoDestinatariosListViewModel
    {
        
        public Guid IdGrupo { get; set; }
        public Guid IdUsuarioDestinatario { get; set; }
        public DateTime FechaActualizacion { get; set; }
        public Guid IdUsuarioActualizacion { get; set; }
    }
    public class GruposDestinatariosEncabezadosViewModel
    {
        public ICollection<GrupoDestinatariosListViewModel> GrupoDestinatarios{ get; set; }
    }
    
}
