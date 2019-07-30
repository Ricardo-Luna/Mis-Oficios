namespace WebApplication1.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    public partial class Grupos
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public Grupos()
        {
            GrupoDestinatarios = new HashSet<GrupoDestinatarios>();
        }

        [Key]
        public Guid IdGrupo { get; set; }

        public Guid IdUsuarioPropietario { get; set; }

        [Required]
        [StringLength(150)]
        public string Nombre { get; set; }

        public Guid IdUsuarioActualizacion { get; set; }

        [Column(TypeName = "smalldatetime")]
        public DateTime FechaActualizacion { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<GrupoDestinatarios> GrupoDestinatarios { get; set; }
    }
}
