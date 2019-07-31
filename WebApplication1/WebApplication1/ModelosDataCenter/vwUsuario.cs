namespace WebApplication1.ModelosDataCenter
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;
    using WebApplication1.ModelsDataCenter;

    public partial class vwUsuario
    {
        [Key]
        public Guid IdUsuario { get; set; }

    //   public Guid IdPersona { get; set; }
        public Guid IdGrupo { get; set; }

        public Guid? IdArea { get; set; }

        [StringLength(25)]
        public string NickName { get; set; }

        [StringLength(32)]
        public string Password { get; set; }
       
        public string GrupoUsuarios { get; set; }

       // public int Status { get; set; }
       
        [NotMapped]
        //public string NombreCompleto { get; set; }
        //[NotMapped]
        public string NombreArea { get; set; }

        [NotMapped]
       // public List<Derecho> Derechos;

        public List<Permiso> Permisos { get; set; }

        public vwUsuario()
        {

            Permisos = new List<Permiso>();
        }
    }
}
