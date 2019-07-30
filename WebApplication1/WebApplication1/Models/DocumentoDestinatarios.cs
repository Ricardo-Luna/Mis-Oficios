namespace WebApplication1.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    public partial class DocumentoDestinatarios
    {
        [Key]
        [Column(Order = 0)]
        public Guid IdDocumento { get; set; }

        [Key]
        [Column(Order = 1)]
        public Guid IdUsuarioDestinatario { get; set; }

        [Column(TypeName = "smalldatetime")]
        public DateTime? FechaLectura { get; set; }

        public byte Tipo { get; set; }

        public byte? Estatus { get; set; }

        [Column(TypeName = "smalldatetime")]
        public DateTime? FechaActualizacion { get; set; }

        public Guid? IdUsuarioActualizacion { get; set; }

        public Documentos Documentos { get; set; }
    }
}


