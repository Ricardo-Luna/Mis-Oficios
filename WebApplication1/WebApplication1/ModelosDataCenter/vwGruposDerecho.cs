namespace WebApplication1.ModelosDataCenter
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("vwGruposDerechos")]
    public partial class vwGruposDerecho
    {
        [Key]
        [Column(Order = 0)]
        public Guid IdGrupo { get; set; }

        [Key]
        [Column(Order = 1)]
        [DatabaseGenerated(DatabaseGeneratedOption.None)]
        public int IdDerecho { get; set; }

        [StringLength(100)]
        public string Nombre { get; set; }

        [StringLength(500)]
        public string Descripcion { get; set; }

        public byte? Acceso { get; set; }
    }
}
