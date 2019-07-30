
namespace WebApplication1.ModelsDataCenter
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    public partial class Persona
    {

        [Key]
        public Guid IdPersona { get; set; }

        public Guid? IdUbicacion { get; set; }

        public Guid? IdPersonaPadre { get; set; }

        public Guid? IdPersonaMadre { get; set; }

        public Guid? IdCiudadLugarNacimiento { get; set; }

        public Guid? IdPaisNacionalidad { get; set; }

        [StringLength(10)]
        public string Prefijo { get; set; }

        [Required]
        [StringLength(50)]
        public string Nombres { get; set; }

        [StringLength(50)]
        public string ApellidoPaterno { get; set; }

        [StringLength(50)]
        public string ApellidoMaterno { get; set; }

        [Column(TypeName = "smalldatetime")]
        public DateTime? FechaNacimiento { get; set; }

        [StringLength(100)]
        public string CiudadNacimiento { get; set; }

        public int Sexo { get; set; }

        public int? Edad { get; set; }

        public int? Estatura { get; set; }

        public int? Peso { get; set; }

        public int EstadoCivil { get; set; }

        [StringLength(18)]
        public string CURP { get; set; }

        [StringLength(15)]
        public string RFC { get; set; }

        public byte? Escolaridad { get; set; }

        [StringLength(25)]
        public string UltimoGradoEstudios { get; set; }

        [StringLength(25)]
        public string SeguroSocial { get; set; }

        [StringLength(25)]
        public string CartillaMilitar { get; set; }

        [StringLength(25)]
        public string Pasaporte { get; set; }

        [StringLength(25)]
        public string LicenciaConducir { get; set; }

        public int LicenciaConducirTipo { get; set; }

        [StringLength(500)]
        public string PersonasDependen { get; set; }

        public int EstadoVida { get; set; }

        public int SangreTipo { get; set; }

        public bool Homonimo { get; set; }

        [StringLength(150)]
        public string NombreMadre { get; set; }

        public int? EstadoVidaMadre { get; set; }

        [StringLength(150)]
        public string NombrePadre { get; set; }

        public int? EstadoVidaPadre { get; set; }

        [StringLength(150)]
        public string NombreTutor { get; set; }

        public byte? SexoTutor { get; set; }

        [Column(TypeName = "smalldatetime")]
        public DateTime FechaAlta { get; set; }

        [Column(TypeName = "smalldatetime")]
        public DateTime FechaActualizacion { get; set; }

        public Guid IdUsuarioActualizacion { get; set; }

        public byte ParaVerificar { get; set; }

        public Guid? IdPersona2 { get; set; }

        [DatabaseGenerated(DatabaseGeneratedOption.Computed)]
        [StringLength(152)]
        public string NombreCompleto { get; set; }
        /*
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<Persona> Personas1 { get; set; }

        public virtual Persona Persona1 { get; set; }*/

        //public virtual Usuario Usuario { get; set; }
    }
}
