using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WebApplication1.Models
{
    public class DocumentosListViewModel
    {
        public Guid IdDocumento { get; set; }
        public string Titulo { get; set; }
        public Guid IdPropietario { get; set; }
        public Guid idDocumentoRemitente { get; set; }
        public Guid? IdCarpeta { get; set; }
        public string Codigo { get; set; }
        public byte Importancia { get; set; }
        public byte estatus { get; set; }
        public DateTime? FechaEnvio { get; set; }
        
    }
   

    public class DocumentosEncabezadosViewModel
    {
        public ICollection<DocumentosListViewModel> Documentos { get; set; }   
    }
}