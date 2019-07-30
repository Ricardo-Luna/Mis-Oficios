
namespace WebApplication1.ModelsDataCenter
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    public partial class GrupoUsuario
    {
        [Key]
        [Column(Order = 0)]
        public Guid IdGrupo { get; set; }

        [Key]
        [Column(Order = 1)]
        public Guid IdUsuario { get; set; }

        [Column(TypeName = "smalldatetime")]
        public DateTime? FechaActualizacion { get; set; }

        public Guid? IdUsuarioActualizacion { get; set; }

    }
}
