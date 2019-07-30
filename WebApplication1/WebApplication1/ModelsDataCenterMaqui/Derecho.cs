namespace WebApplication1.ModelsDataCenter
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("Derechos")]
    public partial class Derecho
    {
        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.None)]
        public int IdDerecho { get; set; }

        [StringLength(100)]
        public string Nombre { get; set; }

        [StringLength(500)]
        public string Descripcion { get; set; }

        public int Acceso { get; set; }

        [Column(TypeName = "smalldatetime")]
        public DateTime? FechaActualizacion { get; set; }

        public Guid? IdUsuarioActualizacion { get; set; }
    }
}