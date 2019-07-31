namespace WebApplication1.ModelosDataCenter
{
    using System;
    using System.Data.Entity;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Linq;
    using System.Collections.Generic;

    public partial class Model1 : DbContext
    {
        internal IEnumerable<object> vwGruposDerecho;

        public Model1()
            : base("name=DataCenter")
        {
        }

        public virtual DbSet<UsuarioDerecho> UsuarioDerechos { get; set; }
        public virtual DbSet<vwArea> vwAreas { get; set; }
        public virtual DbSet<vwDerecho> vwDerechos { get; set; }
        public virtual DbSet<vwGrupos> vwGrupos { get; set; }
        public virtual DbSet<vwGruposDerecho> vwGruposDerechos { get; set; }
        public virtual DbSet<vwGrupoUsuario> vwGrupoUsuarios { get; set; }
        public virtual DbSet<vwUsuario> vwUsuarios { get; set; }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            modelBuilder.Entity<vwArea>()
                .Property(e => e.Clave)
                .IsUnicode(false);

            modelBuilder.Entity<vwArea>()
                .Property(e => e.Abreviacion)
                .IsUnicode(false);

            modelBuilder.Entity<vwArea>()
                .Property(e => e.Nombre)
                .IsUnicode(false);

            modelBuilder.Entity<vwArea>()
                .Property(e => e.Descripcion)
                .IsUnicode(false);

            modelBuilder.Entity<vwArea>()
                .Property(e => e.NombreAreaPadre)
                .IsUnicode(false);

            modelBuilder.Entity<vwArea>()
                .Property(e => e.DescripcionAreaPadre)
                .IsUnicode(false);

            modelBuilder.Entity<vwArea>()
                .Property(e => e.NombreAreaOrigen)
                .IsUnicode(false);

            modelBuilder.Entity<vwArea>()
                .Property(e => e.NombreNomina)
                .IsUnicode(false);

            modelBuilder.Entity<vwArea>()
                .Property(e => e.Cargo)
                .IsUnicode(false);

            modelBuilder.Entity<vwArea>()
                .Property(e => e.Responsable)
                .IsUnicode(false);

            modelBuilder.Entity<vwArea>()
                .Property(e => e.DescripcionAreaOrigen)
                .IsUnicode(false);

            modelBuilder.Entity<vwArea>()
                .Property(e => e.NombreActivo)
                .IsUnicode(false);

            modelBuilder.Entity<vwArea>()
                .Property(e => e.NombreTipo)
                .IsUnicode(false);

            modelBuilder.Entity<vwArea>()
                .Property(e => e.NombreTipoPadre)
                .IsUnicode(false);

            modelBuilder.Entity<vwArea>()
                .Property(e => e.NombreActivoPadre)
                .IsUnicode(false);

            modelBuilder.Entity<vwArea>()
                .Property(e => e.NombreTipoOrigen)
                .IsUnicode(false);

            modelBuilder.Entity<vwArea>()
                .Property(e => e.NombreActivoOrigen)
                .IsUnicode(false);

            modelBuilder.Entity<vwArea>()
                .Property(e => e.NicknameUsuarioActualizacion)
                .IsUnicode(false);

            modelBuilder.Entity<vwArea>()
                .Property(e => e.Ubica)
                .IsUnicode(false);

            modelBuilder.Entity<vwArea>()
                .Property(e => e.ClaveAreaPadreOperativa)
                .IsUnicode(false);

            modelBuilder.Entity<vwArea>()
                .Property(e => e.NombreAreaPadreOperativa)
                .IsUnicode(false);

            modelBuilder.Entity<vwDerecho>()
                .Property(e => e.Nombre)
                .IsUnicode(false);

            modelBuilder.Entity<vwDerecho>()
                .Property(e => e.Descripcion)
                .IsUnicode(false);

            modelBuilder.Entity<vwDerecho>()
                .Property(e => e.NicknameActualizacion)
                .IsUnicode(false);

            modelBuilder.Entity<vwGrupos>()
                .Property(e => e.Nombre)
                .IsUnicode(false);

            modelBuilder.Entity<vwGrupos>()
                .Property(e => e.Descripcion)
                .IsUnicode(false);

            modelBuilder.Entity<vwGrupos>()
                .Property(e => e.NicknameActualizacion)
                .IsUnicode(false);

            modelBuilder.Entity<vwGruposDerecho>()
                .Property(e => e.Nombre)
                .IsUnicode(false);

            modelBuilder.Entity<vwGruposDerecho>()
                .Property(e => e.Descripcion)
                .IsUnicode(false);

            modelBuilder.Entity<vwGrupoUsuario>()
                .Property(e => e.NickName)
                .IsUnicode(false);

            modelBuilder.Entity<vwGrupoUsuario>()
                .Property(e => e.Password)
                .IsUnicode(false);

            modelBuilder.Entity<vwGrupoUsuario>()
                .Property(e => e.Correo)
                .IsUnicode(false);

            modelBuilder.Entity<vwGrupoUsuario>()
                .Property(e => e.NombreCompleto)
                .IsUnicode(false);

            modelBuilder.Entity<vwGrupoUsuario>()
                .Property(e => e.NicknameActualizacion)
                .IsUnicode(false);

            modelBuilder.Entity<vwGrupoUsuario>()
                .Property(e => e.GrupoUsuariosNicknameActualizacion)
                .IsUnicode(false);

            modelBuilder.Entity<vwGrupoUsuario>()
                .Property(e => e.Nombre)
                .IsUnicode(false);

            modelBuilder.Entity<vwGrupoUsuario>()
                .Property(e => e.AreaNombre)
                .IsUnicode(false);

            modelBuilder.Entity<vwGrupoUsuario>()
                .Property(e => e.ArchivoCartaResponsabilidad)
                .IsUnicode(false);

            modelBuilder.Entity<vwGrupoUsuario>()
                .Property(e => e.NicknameJefe)
                .IsUnicode(false);

            modelBuilder.Entity<vwGrupoUsuario>()
                .Property(e => e.Puesto)
                .IsUnicode(false);

            modelBuilder.Entity<vwUsuario>()
                .Property(e => e.NickName)
                .IsUnicode(false);

            modelBuilder.Entity<vwUsuario>()
                .Property(e => e.Password)
                .IsUnicode(false);

            //modelBuilder.Entity<vwUsuario>()
            //    .Property(e => e.Correo)
            //    .IsUnicode(false);

            //modelBuilder.Entity<vwUsuario>()
            //    .Property(e => e.NombreCompleto)
            //    .IsUnicode(false);

            //modelBuilder.Entity<vwUsuario>()
            //    .Property(e => e.NicknameActualizacion)
            //    .IsUnicode(false);

            //modelBuilder.Entity<vwUsuario>()
            //    .Property(e => e.AreaNombre)
            //    .IsUnicode(false);

            //modelBuilder.Entity<vwUsuario>()
            //    .Property(e => e.ArchivoCartaResponsabilidad)
            //    .IsUnicode(false);

            //modelBuilder.Entity<vwUsuario>()
            //    .Property(e => e.NicknameJefe)
            //    .IsUnicode(false);

            //modelBuilder.Entity<vwUsuario>()
            //    .Property(e => e.Puesto)
            //    .IsUnicode(false);
        }

        public System.Data.Entity.DbSet<WebApplication1.ModelsDataCenter.Usuario> Usuarios { get; set; }
    }
}
