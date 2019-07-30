using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WebApplication1.Models
{
    public class GruposListViewModels
    {
        public Guid IdGrupo { get; set; }
        public Guid idUsuarioPropietario { get; set; }
        public string Nombre { get; set; }
        public Guid IdUsuarioActualizacion { get; set; }
        public DateTime FechaActualizacion { get; set; }
    }
    public class GruposEncabezadosViewModel
    {
        public ICollection<GruposListViewModels> Grupos { get; set; }
    }
}