namespace WebApplication1.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    public partial class Archivos
    {
        [Key]
        public Guid IdArchivo { get; set; }

        public Guid IdPropietario { get; set; }

        [Required]
        [StringLength(255)]
        public string Nombre { get; set; }

        [StringLength(32)]
        public string SelloDigital { get; set; }

        public Guid IdUsuarioActualizacion { get; set; }

        [Column(TypeName = "smalldatetime")]
        public DateTime FechaActualizacion { get; set; }

        public Documentos Documentos { get; set; }
    }
}
