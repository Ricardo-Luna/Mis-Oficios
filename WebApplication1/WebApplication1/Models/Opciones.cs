namespace WebApplication1.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    public partial class Opciones
    {
        [Key]
        [Column(Order = 0)]
        public Guid IdOpcion { get; set; }

        [Key]
        [Column(Order = 1)]
        public Guid IdUsuario { get; set; }

        [Key]
        [Column(Order = 2)]
        public byte Codigo { get; set; }

        [StringLength(100)]
        public string Valor { get; set; }

        [Key]
        [Column(Order = 3)]
        public Guid IdUsuarioActualizacion { get; set; }

        [Key]
        [Column(Order = 4, TypeName = "smalldatetime")]
        public DateTime FechaActualizacion { get; set; }
    }
}
