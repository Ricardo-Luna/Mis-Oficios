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
    public class OficiosViewModel
    {

        public Guid IdDocumento { get; set; }
        public Guid IdUsuarioPropietario { get; set; }
        public Guid IdDocumentoOrigen { get; set; }
        public Guid IdDocumentoRemitente { get; set; }
        public Guid? IdCarpeta { get; set; }
        public Guid? IdArea { get; set; }
        public string Codigo { get; set; }
        public string Asunto { get; set; }
        public string Contenido { get; set; }
        public DateTime FechaCreacion { get; set; }
        public DateTime? FechaEnvio { get; set; }
        public byte Importancia { get; set; }
        public byte Tipo { get; set; }
        public byte Estatus { get; set; }
        public string Nota { get; set; }
        public string SelloDigital { get; set; }
        public Guid IdUsuarioActualizacion { get; set; }
        public DateTime FechaActualizacion { get; set; }
    }

    public class OficiosEncabezadosViewModel
    {

        public ICollection<OficiosViewModel> Documentos { get; set; }

    }
}
