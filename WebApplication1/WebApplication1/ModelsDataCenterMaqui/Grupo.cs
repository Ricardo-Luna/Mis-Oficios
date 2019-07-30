
namespace WebApplication1.ModelsDataCenter
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    public partial class Grupo
    {

        [Key]
        public Guid IdGrupo { get; set; }

        [StringLength(50)]
        public string Nombre { get; set; }

        [StringLength(250)]
        public string Descripcion { get; set; }

        [Column(TypeName = "smalldatetime")]
        public DateTime? FechaActualizacion { get; set; }

        public Guid? IdUsuarioActualizacion { get; set; }

    }
}
