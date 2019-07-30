using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Description;
using WebApplication1.Models;

namespace WebApplication1.Controllers
{
    public class Documentos1Controller : ApiController
    {
        private ModelosOficios db = new ModelosOficios();

        // GET: api/Documentos1
        public IQueryable<Documentos> GetDocumentos()
        {
            return db.Documentos;
        }

        // GET: api/Documentos1/5
        [ResponseType(typeof(Documentos))]
        public IHttpActionResult GetDocumentos(Guid id)
        {
            Documentos documentos = db.Documentos.Find(id);
            if (documentos == null)
            {
                return NotFound();
            }

            return Ok(documentos);
        }

        // PUT: api/Documentos1/5
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

        // POST: api/Documentos1
        [ResponseType(typeof(Documentos))]
        public IHttpActionResult PostDocumentos(Documentos documentos)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.Documentos.Add(documentos);

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateException)
            {
                if (DocumentosExists(documentos.IdDocumento))
                {
                    return Conflict();
                }
                else
                {
                    throw;
                }
            }

            return CreatedAtRoute("DefaultApi", new { id = documentos.IdDocumento }, documentos);
        }

        // DELETE: api/Documentos1/5
        [ResponseType(typeof(Documentos))]
        public IHttpActionResult DeleteDocumentos(Guid id)
        {
            Documentos documentos = db.Documentos.Find(id);
            if (documentos == null)
            {
                return NotFound();
            }

            db.Documentos.Remove(documentos);
            db.SaveChanges();

            return Ok(documentos);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool DocumentosExists(Guid id)
        {
            return db.Documentos.Count(e => e.IdDocumento == id) > 0;
        }
    }
}