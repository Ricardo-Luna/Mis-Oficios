namespace WebApplication1.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    public  partial class Carpetas
    {
        //[System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public Carpetas()
        {
           // Documentos = new HashSet<Documentos>();
        }

        [Key]
        public Guid IdCarpeta { get; set; }

        public Guid IdUsuarioPropietario { get; set; }

        [Required]
        [StringLength(150)]
        public string Nombre { get; set; }

        public bool Recibidos { get; set; }

        public bool Enviados { get; set; }

        public bool Borradores { get; set; }

        public Guid IdUsuarioActualizacion { get; set; }

        [Column(TypeName = "smalldatetime")]
        public DateTime FechaActualizacion { get; set; }

       
        public virtual ICollection<Documentos> Documentos { get; set; }

    }
}
