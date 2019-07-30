namespace WebApplication1.ModelosDataCenter
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    public partial class vwArea
    {
        [Key]
        [Column(Order = 0)]
        public Guid IdArea { get; set; }

        [StringLength(25)]
        public string Clave { get; set; }

        [StringLength(10)]
        public string Abreviacion { get; set; }

        [StringLength(100)]
        public string Nombre { get; set; }

        [StringLength(100)]
        public string Descripcion { get; set; }

        public Guid? IdAreaPadre { get; set; }

        [StringLength(100)]
        public string NombreAreaPadre { get; set; }

        [StringLength(100)]
        public string DescripcionAreaPadre { get; set; }

        public Guid? IdAreaOrigen { get; set; }

        [StringLength(100)]
        public string NombreAreaOrigen { get; set; }

        [StringLength(100)]
        public string NombreNomina { get; set; }

        [StringLength(100)]
        public string Cargo { get; set; }

        [StringLength(100)]
        public string Responsable { get; set; }

        [StringLength(100)]
        public string DescripcionAreaOrigen { get; set; }

        [Key]
        [Column(Order = 1)]
        public Guid IdUsuarioActualizacion { get; set; }

        [Key]
        [Column(Order = 2, TypeName = "smalldatetime")]
        public DateTime FechaActualizacion { get; set; }

        [Key]
        [Column(Order = 3)]
        [DatabaseGenerated(DatabaseGeneratedOption.None)]
        public int Tipo { get; set; }

        [Key]
        [Column(Order = 4)]
        [DatabaseGenerated(DatabaseGeneratedOption.None)]
        public int Activo { get; set; }

        [StringLength(25)]
        public string NombreActivo { get; set; }

        [StringLength(25)]
        public string NombreTipo { get; set; }

        public int? TipoPadre { get; set; }

        [StringLength(25)]
        public string NombreTipoPadre { get; set; }

        public int? ActivoPadre { get; set; }

        [StringLength(25)]
        public string NombreActivoPadre { get; set; }

        public int? TipoOrigen { get; set; }

        [StringLength(25)]
        public string NombreTipoOrigen { get; set; }

        public int? ActivoOrigen { get; set; }

        [StringLength(25)]
        public string NombreActivoOrigen { get; set; }

        [StringLength(25)]
        public string NicknameUsuarioActualizacion { get; set; }

        [StringLength(100)]
        public string Ubica { get; set; }

        public int? Estatus { get; set; }

        [Column(TypeName = "smalldatetime")]
        public DateTime? FechaAlta { get; set; }

        public Guid? IdAreaPadreOperativa { get; set; }

        [StringLength(25)]
        public string ClaveAreaPadreOperativa { get; set; }

        [StringLength(100)]
        public string NombreAreaPadreOperativa { get; set; }
    }
}
