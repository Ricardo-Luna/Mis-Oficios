namespace WebApplication1.ModelosDataCenter
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    public partial class vwGrupoUsuario
    {
        public Guid? IdUsuario { get; set; }

        public Guid? IdPersona { get; set; }

        [StringLength(25)]
        public string NickName { get; set; }

        [StringLength(32)]
        public string Password { get; set; }

        [StringLength(255)]
        public string Correo { get; set; }

        public int? Status { get; set; }

        [Column(TypeName = "smalldatetime")]
        public DateTime? FechaAlta { get; set; }

        [Column(TypeName = "smalldatetime")]
        public DateTime? FechaActualizacion { get; set; }

        public Guid? IdUsuarioActualizacion { get; set; }

        [StringLength(150)]
        public string NombreCompleto { get; set; }

        [StringLength(25)]
        public string NicknameActualizacion { get; set; }

        [Key]
        public Guid IdGrupo { get; set; }

        [Column(TypeName = "smalldatetime")]
        public DateTime? GrupoUsuariosFechaActualizacion { get; set; }

        public Guid? GrupoUsuariosIdUsuarioActualizacion { get; set; }

        [StringLength(25)]
        public string GrupoUsuariosNicknameActualizacion { get; set; }

        public Guid? IdArea { get; set; }

        public int? Rol { get; set; }

        public Guid? IdUsuarioJefe { get; set; }

        [StringLength(50)]
        public string Nombre { get; set; }

        public Guid? IdEmpleado { get; set; }

        [StringLength(100)]
        public string AreaNombre { get; set; }

        [StringLength(20)]
        public string EmpleadoCodigo { get; set; }

        [Column(TypeName = "smalldatetime")]
        public DateTime? FechaVigencia { get; set; }

        [StringLength(255)]
        public string ArchivoCartaResponsabilidad { get; set; }

        [StringLength(25)]
        public string NicknameJefe { get; set; }

        [StringLength(150)]
        public string Puesto { get; set; }
    }
}
