
namespace WebApplication1.ModelsDataCenter
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    public partial class Usuario
    {
        [Key]
        public Guid IdUsuario { get; set; }

        public Guid IdPersona { get; set; }

        public Guid? IdArea { get; set; }
        /*
        public Guid? IdEmpleado { get; set; }

        public Guid? IdUsuarioJefe { get; set; }*/

        [StringLength(25)]
        public string NickName { get; set; }

        [StringLength(32)]
        public string Password { get; set; }
        /*
        [StringLength(255)]
        public string Correo { get; set; }

        [StringLength(150)]
        public string Puesto { get; set; }*/

        public int Status { get; set; }
        /*
        public int Rol { get; set; }

        [Column(TypeName = "smalldatetime")]
        public DateTime FechaAlta { get; set; }

        [Column(TypeName = "smalldatetime")]
        public DateTime? FechaVigencia { get; set; }

        [StringLength(255)]
        public string ArchivoCartaResponsabilidad { get; set; }

        [Column(TypeName = "smalldatetime")]
        public DateTime FechaActualizacion { get; set; }

        public Guid IdUsuarioActualizacion { get; set; }*/
        [NotMapped]
        public string NombreCompleto { get; set; }
        [NotMapped]
        public string NombreArea { get; set; }
        [NotMapped]
        public List<Permiso> Permisos { get; set; }

        public Usuario() {

            Permisos = new List<Permiso>();
        }
    }
}
