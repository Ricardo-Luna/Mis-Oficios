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
    public class Grupos1Controller : ApiController
    {
        private ModelosOficios db = new ModelosOficios();

        // GET: api/Grupos1
        public IQueryable<Grupos> GetGrupos()
        {
            return db.Grupos;
        }

        // GET: api/Grupos1/5
        [ResponseType(typeof(Grupos))]
        public IHttpActionResult GetGrupos(Guid id)
        {
            Grupos grupos = db.Grupos.Find(id);
            if (grupos == null)
            {
                return NotFound();
            }

            return Ok(grupos);
        }

        // PUT: api/Grupos1/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutGrupos(Guid id, Grupos grupos)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != grupos.IdGrupo)
            {
                return BadRequest();
            }

            db.Entry(grupos).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!GruposExists(id))
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

        // POST: api/Grupos1
        [ResponseType(typeof(Grupos))]
        public IHttpActionResult PostGrupos(Grupos grupos)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.Grupos.Add(grupos);

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateException)
            {
                if (GruposExists(grupos.IdGrupo))
                {
                    return Conflict();
                }
                else
                {
                    throw;
                }
            }

            return CreatedAtRoute("DefaultApi", new { id = grupos.IdGrupo }, grupos);
        }

        // DELETE: api/Grupos1/5
        [ResponseType(typeof(Grupos))]
        public IHttpActionResult DeleteGrupos(Guid id)
        {
            Grupos grupos = db.Grupos.Find(id);
            if (grupos == null)
            {
                return NotFound();
            }

            db.Grupos.Remove(grupos);
            db.SaveChanges();

            return Ok(grupos);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool GruposExists(Guid id)
        {
            return db.Grupos.Count(e => e.IdGrupo == id) > 0;
        }
    }
}