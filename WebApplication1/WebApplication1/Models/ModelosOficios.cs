namespace WebApplication1.Models
{
    using System;
    using System.Data.Entity;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Linq;

    public partial class ModelosOficios : DbContext
    {
        public ModelosOficios()
            : base("name=ModelosOficios")
        {
        }

        public virtual DbSet<Archivos> Archivos { get; set; }
        public virtual DbSet<Carpetas> Carpetas { get; set; }
        public virtual DbSet<DocumentoDestinatarios> DocumentoDestinatarios { get; set; }
        public virtual DbSet<Documentos> Documentos { get; set; }
        public virtual DbSet<GrupoDestinatarios> GrupoDestinatarios { get; set; }
        public virtual DbSet<Grupos> Grupos { get; set; }
        public virtual DbSet<UsuarioDestinatarios> UsuarioDestinatarios { get; set; }
        public virtual DbSet<Opciones> Opciones { get; set; }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Archivos>()
                .Property(e => e.Nombre)
                .IsUnicode(false);

            modelBuilder.Entity<Archivos>()
                .Property(e => e.SelloDigital)
                .IsUnicode(false);

            modelBuilder.Entity<Carpetas>()
                .Property(e => e.Nombre)
                .IsUnicode(false);

            modelBuilder.Entity<Documentos>()
                .Property(e => e.Codigo)
                .IsUnicode(false);

            modelBuilder.Entity<Documentos>()
                .Property(e => e.Asunto)
                .IsUnicode(false);

            modelBuilder.Entity<Documentos>()
                .Property(e => e.Contenido)
                .IsUnicode(false);

            modelBuilder.Entity<Documentos>()
                .Property(e => e.Nota)
                .IsUnicode(false);

            //modelBuilder.Entity<Documentos>()
            //    .Property(e => e.CadenaOriginal)
            //    .IsUnicode(false);

            modelBuilder.Entity<Documentos>()
                .Property(e => e.SelloDigital)
                .IsUnicode(false);

           

         /*   modelBuilder.Entity<Documentos>()
                .HasMany(e => e.Archivos)
                .WithRequired(e => e.Documentos)
                .HasForeignKey(e => e.IdPropietario)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<Documentos>()
                .HasMany(e => e.DocumentoDestinatarios)
                .WithRequired(e => e.Documentos)
                .WillCascadeOnDelete(false);
                */
            //modelBuilder.Entity<Grupos>()
            //    .Property(e => e.Nombre)
            //    .IsUnicode(false);

            //modelBuilder.Entity<Grupos>()
            //    .HasMany(e => e.GrupoDestinatarios)
            //    .WithRequired(e => e.Grupos)
            //    .WillCascadeOnDelete(false);

            modelBuilder.Entity<Opciones>()
                .Property(e => e.Valor)
                .IsUnicode(false);
        }

        public System.Data.Entity.DbSet<WebApplication1.ModelsDataCenter.Usuario> Usuarios { get; set; }
    }
}
