namespace WebApplication1.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    public partial class Documentos
    {
       
        public Documentos()
        {
        /*    Archivos = new HashSet<Archivos>();
            DocumentoDestinatarios = new HashSet<DocumentoDestinatarios>();*/
        }

        [Key]
        public Guid IdDocumento { get; set; }

        public Guid IdUsuarioPropietario { get; set; }

        public Guid IdDocumentoOrigen { get; set; }

        public Guid IdDocumentoRemitente { get; set; }

        public Guid? IdCarpeta { get; set; }

        public Guid? IdArea { get; set; }

        [Required]
        [StringLength(10)]
        public string Codigo { get; set; }

        [Required]
        [StringLength(500)]
        public string Asunto { get; set; }

        [Required]
        public string Contenido { get; set; }

        [Column(TypeName = "smalldatetime")]
        public DateTime FechaCreacion { get; set; }

        [Column(TypeName = "smalldatetime")]
        public DateTime? FechaEnvio { get; set; }

        public byte Importancia { get; set; }

        public byte Tipo { get; set; }

        public byte Estatus { get; set; }

        [StringLength(1000)]
        public string Nota { get; set; }

       // public string CadenaOriginal { get; set; }

        [StringLength(32)]
        public string SelloDigital { get; set; }

        public Guid IdUsuarioActualizacion { get; set; }

        [Column(TypeName = "smalldatetime")]
        public DateTime FechaActualizacion { get; set; }

      
     //   public  ICollection<Archivos> Archivos { get; set; }

     //   public virtual Carpetas Carpetas { get; set; }

      
      //  public  ICollection<DocumentoDestinatarios> DocumentoDestinatarios { get; set; }
    }
}
