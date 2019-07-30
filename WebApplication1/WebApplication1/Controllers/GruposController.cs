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
    public class GruposController : ApiController
    {
        private ModelosOficios db = new ModelosOficios();
        GruposEncabezadosViewModel gruposVM = new GruposEncabezadosViewModel();
        GruposListViewModels gru;
        [HttpGet]
        [Route("api/Grupos/{IdUsuarioPropietario}")]
        public IHttpActionResult GetGrupos(Guid IdUsuarioPropietario)
        {
            var grupos = db.Grupos.Where(g => g.IdUsuarioPropietario == IdUsuarioPropietario).ToList();
            var grus = new List<GruposListViewModels>();
            foreach (var grupo in grupos)
            {
                gru = new GruposListViewModels();
                gru.IdGrupo = grupo.IdGrupo;
                gru.idUsuarioPropietario= grupo.IdUsuarioPropietario;
                gru.Nombre = grupo.Nombre;
                gru.IdUsuarioActualizacion = grupo.IdUsuarioActualizacion;
                gru.FechaActualizacion = grupo.FechaActualizacion;
                grus.Add(gru);
            }
            

            return Ok(grus);
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
            grupos.IdGrupo = Guid.NewGuid();
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.Grupos.Add(grupos);

            try
            {
                db.SaveChanges();
                return Ok(grupos);
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
        private bool GruposExists(Guid id)
        {
            return db.Grupos.Count(e => e.IdGrupo == id) > 0;
        }


    }
}