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
    public class GrupoDestinatariosController : ApiController
    {
        private ModelosOficios db = new ModelosOficios();

        // GET: api/GrupoDestinatarios
        public IQueryable<GrupoDestinatarios> GetGrupoDestinatarios()
        {
            return db.GrupoDestinatarios;
        }

        // GET: api/GrupoDestinatarios/5
        [ResponseType(typeof(GrupoDestinatarios))]
        public IHttpActionResult GetGrupoDestinatarios(Guid id)
        {
            GrupoDestinatarios grupoDestinatarios = db.GrupoDestinatarios.Find(id);
            if (grupoDestinatarios == null)
            {
                return NotFound();
            }

            return Ok(grupoDestinatarios);
        }

        // PUT: api/GrupoDestinatarios/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutGrupoDestinatarios(Guid id, GrupoDestinatarios grupoDestinatarios)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != grupoDestinatarios.IdGrupo)
            {
                return BadRequest();
            }

            db.Entry(grupoDestinatarios).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!GrupoDestinatariosExists(id))
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

        // POST: api/GrupoDestinatarios
        [ResponseType(typeof(GrupoDestinatarios))]
        public IHttpActionResult PostGrupoDestinatarios(GrupoDestinatarios grupoDestinatarios)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.GrupoDestinatarios.Add(grupoDestinatarios);

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateException)
            {
                if (GrupoDestinatariosExists(grupoDestinatarios.IdGrupo))
                {
                    return Conflict();
                }
                else
                {
                    throw;
                }
            }

            return CreatedAtRoute("DefaultApi", new { id = grupoDestinatarios.IdGrupo }, grupoDestinatarios);
        }

        // DELETE: api/GrupoDestinatarios/5
        [ResponseType(typeof(GrupoDestinatarios))]
        public IHttpActionResult DeleteGrupoDestinatarios(Guid id)
        {
            GrupoDestinatarios grupoDestinatarios = db.GrupoDestinatarios.Find(id);
            if (grupoDestinatarios == null)
            {
                return NotFound();
            }

            db.GrupoDestinatarios.Remove(grupoDestinatarios);
            db.SaveChanges();

            return Ok(grupoDestinatarios);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool GrupoDestinatariosExists(Guid id)
        {
            return db.GrupoDestinatarios.Count(e => e.IdGrupo == id) > 0;
        }
    }
}