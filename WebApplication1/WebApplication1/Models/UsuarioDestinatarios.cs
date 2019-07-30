namespace WebApplication1.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    public partial class UsuarioDestinatarios
    {
        [Key]
        [Column(Order = 0)]
        public Guid IdUsuarioPropietario { get; set; }

        [Key]
        [Column(Order = 1)]
        public Guid IdUsuarioDestinatario { get; set; }

        public byte EnvioTipo { get; set; }

        public Guid IdUsuarioActualizacion { get; set; }

        [Column(TypeName = "smalldatetime")]
        public DateTime FechaActualizacion { get; set; }
    }
}
