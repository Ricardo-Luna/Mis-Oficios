using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Net;
using System.Web.Http;
using System.Web.Http.Description;
using WebApplication1.Models;


namespace WebApplication1.Controllers
{
    public class DocumentosController : ApiController
    {

        private ModelosOficios db = new ModelosOficios();
        DocumentosListViewModel doc;
        OficiosViewModel of = new OficiosViewModel();
        // GET: api/Documentos
        [HttpGet]
        [Route("api/Documentos/{IdUsuarioPropietario}/{inicio}/{final}")]
        public IHttpActionResult GetDocumentos(Guid IdUsuarioPropietario, int inicio, int final)
        {

            var documentos = db.Documentos.Where(g => g.IdUsuarioPropietario == IdUsuarioPropietario && g.Estatus != 5).ToList();
            var docs = new List<DocumentosListViewModel>();


            for (; inicio <= final; inicio++)
            {
                doc = new DocumentosListViewModel
                {
                    IdDocumento = documentos.ElementAt(inicio).IdDocumento,                      //---
                    Titulo = documentos.ElementAt(inicio).Asunto,                                //---
                    FechaEnvio = documentos.ElementAt(inicio).FechaEnvio,                        //---
                    IdPropietario = documentos.ElementAt(inicio).IdUsuarioPropietario,           //---
                    idDocumentoRemitente = documentos.ElementAt(inicio).IdDocumentoRemitente,    //---
                    IdCarpeta = documentos.ElementAt(inicio).IdCarpeta,                          //NULL
                    Codigo = documentos.ElementAt(inicio).Codigo,                                //---
                    Importancia = documentos.ElementAt(inicio).Importancia,                      
                    estatus = documentos.ElementAt(inicio).Estatus,
                };

                docs.Add(doc);
            }
            return Ok(docs);
        }


        [HttpGet]
        [Route("api/Oficios/{IdDocumento}")]
        public IHttpActionResult getOficio(Guid IdDocumento)
        {
            var documentos = db.Documentos.Where(g => g.IdDocumento == IdDocumento).ToList();
            var docs = new List<OficiosViewModel>();

            foreach (var documento in documentos)
            {
                of.IdDocumento = documento.IdDocumento;
                of.IdUsuarioPropietario = documento.IdUsuarioPropietario;
                of.IdDocumentoOrigen = documento.IdDocumentoOrigen;
                of.IdDocumentoRemitente = documento.IdDocumentoRemitente;
                of.IdCarpeta = documento.IdCarpeta;
                of.IdArea = documento.IdArea;
                of.Codigo = documento.Codigo;
                of.Asunto = documento.Asunto;
                of.Contenido = documento.Contenido;
                of.FechaCreacion = documento.FechaCreacion;
                of.FechaEnvio = documento.FechaEnvio;
                of.Importancia = documento.Importancia;
                of.Tipo = documento.Tipo;
                of.Estatus = documento.Estatus;
                of.Nota = documento.Nota;
                of.SelloDigital = documento.SelloDigital;
                of.IdUsuarioActualizacion = documento.IdUsuarioActualizacion;
                of.FechaActualizacion = documento.FechaActualizacion;
                docs.Add(of);
            }
            return Ok(docs);

        }

        // GET: Buscar documento
        [HttpGet]
        [Route("api/DocumentoBusqueda/{IdUsuarioPropietario}/{Codigo}")]
        public IHttpActionResult GetDocumentoBusqueda(Guid IdUsuarioPropietario,string Codigo)
        {

            var documentos = db.Documentos.Where(g => g.IdUsuarioPropietario == IdUsuarioPropietario && g.Estatus != 5 && Codigo == g.Codigo).ToList();
            var docs = new List<DocumentosListViewModel>();

            foreach(var documento in documentos)
            {
                doc = new DocumentosListViewModel
                {
                    IdDocumento = documento.IdDocumento,
                    Titulo = documento.Asunto,
                    FechaEnvio = documento.FechaEnvio,
                    IdPropietario = documento.IdUsuarioPropietario,
                    idDocumentoRemitente = documento.IdDocumentoRemitente,
                    IdCarpeta = documento.IdCarpeta,
                    Codigo = documento.Codigo,
                    Importancia = documento.Importancia,
                    estatus = documento.Estatus,
                };
            }
           
                docs.Add(doc);
            
            return Ok(docs);
        }

        // PUT: api/Documentos/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutDocumentos(Guid id, Documentos documentos)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != documentos.IdDocumento)
            {
                return BadRequest();
            }

            db.Entry(documentos).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
                return Ok(documentos);
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!DocumentosExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return StatusCode(HttpStatusCode.NoContent);
        }
        private bool DocumentosExists(Guid id)
        {
            return db.Documentos.Count(e => e.IdDocumento == id) > 0;
        }

        // POST: api/Documentos1
        [ResponseType(typeof(Documentos))]
        public IHttpActionResult PostDocumentos(Documentos documentos)
        {
            documentos.IdDocumento = Guid.NewGuid();
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.Documentos.Add(documentos);

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateException e)
            {
                return BadRequest(e.Message);
            }

            return Ok(documentos);
        }

    }
}