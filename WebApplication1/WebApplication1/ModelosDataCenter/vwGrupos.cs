namespace WebApplication1.ModelosDataCenter
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    public partial class vwGrupos
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

        [StringLength(25)]
        public string NicknameActualizacion { get; set; }
    }
}
