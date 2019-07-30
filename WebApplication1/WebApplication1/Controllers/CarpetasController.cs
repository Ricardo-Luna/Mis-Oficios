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
    public class CarpetasController : ApiController
    {
        private ModelosOficios db = new ModelosOficios();
        CarpetasViewModels crp;
        CarpetaIdViewModel icrp;
        DocumentosListViewModel doc;






        // GET: api/Carpetas
        [HttpGet]
        [Route("api/Carpetas/{IdUsuarioPropietario}")]
        //[Route("api/Carpetas/")]
        public IHttpActionResult GetCarpetas(Guid IdUsuarioPropietario)
        {

            var carpetas = db.Carpetas.Where(g => g.IdUsuarioPropietario == IdUsuarioPropietario).ToList();
            var cp = new List<CarpetasViewModels>();

            foreach (var carpeta in carpetas)
            {
                crp = new CarpetasViewModels
                {
                    IdCarpeta = carpeta.IdCarpeta,
                    IdUsuarioPropietario = carpeta.IdUsuarioPropietario,
                    Nombre = carpeta.Nombre,
                    Recibidos = carpeta.Recibidos,
                    Enviados = carpeta.Enviados,
                    Borradores = carpeta.Borradores,
                    IdUsuarioActualizacion = carpeta.IdUsuarioActualizacion,
                    FechaActualizacion = carpeta.FechaActualizacion

                };

                cp.Add(crp);
            }


            return Ok(cp);
        }



        // GET: api/Carpetas
        [HttpGet]
        [Route("api/Carpetas/{IdUsuarioPropietario}/{IdCarpeta}/{inicio}/{final}")]
        //[Route("api/Carpetas/")]
        public IHttpActionResult GetDocsCarpetas(Guid IdUsuarioPropietario, Guid IdCarpeta, int inicio, int final)
        {
           var documentos = db.Documentos.Where(g => g.IdUsuarioPropietario == IdUsuarioPropietario && g.Estatus != 5 && g.IdCarpeta == IdCarpeta).ToList();
           var DocumentosOrdenados = documentos.OrderByDescending(g => g.FechaEnvio);
           var docs = new List<DocumentosListViewModel>();
           for (; inicio <= final; inicio++)
             {
               doc = new DocumentosListViewModel
                 {
                          IdDocumento = DocumentosOrdenados.ElementAt(inicio).IdDocumento,
                            Titulo = DocumentosOrdenados.ElementAt(inicio).Asunto,
                            FechaEnvio = DocumentosOrdenados.ElementAt(inicio).FechaEnvio,
                            IdPropietario = DocumentosOrdenados.ElementAt(inicio).IdUsuarioPropietario,
                            idDocumentoRemitente = DocumentosOrdenados.ElementAt(inicio).IdDocumentoRemitente,
                            IdCarpeta = DocumentosOrdenados.ElementAt(inicio).IdCarpeta,
                            Codigo = DocumentosOrdenados.ElementAt(inicio).Codigo,
                            Importancia = DocumentosOrdenados.ElementAt(inicio).Importancia,
                            estatus = DocumentosOrdenados.ElementAt(inicio).Estatus,
                        };

                        docs.Add(doc);
                    }
                    return Ok(docs);

        }

        // POST: api/Carpetas
        [ResponseType(typeof(Carpetas))]
        public IHttpActionResult PostCarpetas(Carpetas carpetas)
        {
            carpetas.IdCarpeta = Guid.NewGuid();
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.Carpetas.Add(carpetas);

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateException e)
            {
                return BadRequest(e.Message);
            }

            return Ok(carpetas);
        }

        // PUT: api/Carpetas1/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutCarpetas(Guid id, Carpetas carpetas)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != carpetas.IdCarpeta)
            {
                return BadRequest();
            }

            db.Entry(carpetas).State = EntityState.Modified;

            try
            {
                db.SaveChanges();

            }
            catch (DbUpdateConcurrencyException)
            {
                if (!CarpetasExists(id))
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

        private bool CarpetasExists(Guid id)
        {
            return db.Carpetas.Count(e => e.IdCarpeta == id) > 0;
        }


    }
}




































//// GET: api/Carpetas
//[HttpGet]
//[Route("api/Carpetas/{IdUsuarioPropietario}")]
////[Route("api/Carpetas/")]
//public IHttpActionResult GetCarpetas()
//{

//    var carpetas = db.Carpetas.ToList();
//    var cp = new List<CarpetasEncabezadosViewModel>();
//    foreach (var carpeta in carpetas)
//    {
//        crp = new CarpetasViewModels();

//        crp.IdCarpeta = carpeta.IdCarpeta;
//        crp.IdUsuarioPropietario = carpeta.IdUsuarioPropietario;
//        crp.Nombre = carpeta.Nombre;
//        crp.Recibidos = carpeta.Recibidos;
//        crp.Enviados = carpeta.Enviados;
//        crp.Borradores = carpeta.Borradores;
//        crp.IdUsuarioActualizacion = carpeta.IdUsuarioActualizacion;
//        crp.FechaActualizacion = carpeta.FechaActualizacion;

//        cp.Add(crp);


//    }


//    return Ok(crp);
//}







//// GET: api/Carpetas
//[HttpGet]
//[Route("api/Carpetas/{IdUsuarioPropietario}/{Car}/{inicio}/{final}")]
////[Route("api/Carpetas/")]
//public IHttpActionResult GetDocsCarpetas(Guid IdUsuarioPropietario, int Car, int inicio, int final)
//{
//    if (Car == 1)
//    {
//        var carpetas = db.Carpetas.Where(g => g.IdUsuarioPropietario == IdUsuarioPropietario && g.Recibidos == true);
//        var cp = new List<CarpetaIdViewModel>();
//        Guid idC;
//        foreach (var carpeta in carpetas)
//        {
//            icrp = new CarpetaIdViewModel();
//            icrp.IdCarpeta = carpeta.IdCarpeta;
//            idC = icrp.IdCarpeta;
//            var documentos = db.Documentos.Where(g => g.IdUsuarioPropietario == IdUsuarioPropietario && g.Estatus != 5 && g.IdCarpeta == idC).ToList();
//            var DocumentosOrdenados = documentos.OrderByDescending(g => g.FechaActualizacion);
//            var docs = new List<DocumentosListViewModel>();
//            int sizeOfDocs = DocumentosOrdenados.Count();
//            if (sizeOfDocs <= 20)
//            {
//                foreach (var documento in DocumentosOrdenados)
//                {
//                    doc = new DocumentosListViewModel
//                    {
//                        IdDocumento = DocumentosOrdenados.ElementAt(inicio).IdDocumento,
//                        Titulo = DocumentosOrdenados.ElementAt(inicio).Asunto,
//                        FechaEnvio = DocumentosOrdenados.ElementAt(inicio).FechaEnvio,
//                        IdPropietario = DocumentosOrdenados.ElementAt(inicio).IdUsuarioPropietario,
//                        idDocumentoRemitente = DocumentosOrdenados.ElementAt(inicio).IdDocumentoRemitente,
//                        IdCarpeta = DocumentosOrdenados.ElementAt(inicio).IdCarpeta,
//                        Codigo = DocumentosOrdenados.ElementAt(inicio).Codigo,
//                        Importancia = DocumentosOrdenados.ElementAt(inicio).Importancia,
//                        estatus = DocumentosOrdenados.ElementAt(inicio).Estatus,
//                    };

//                    docs.Add(doc);


//                    return Ok(docs);
//                }
//            }
//            else
//            {
//                for (; inicio <= final; inicio++)
//                {
//                    doc = new DocumentosListViewModel
//                    {
//                        IdDocumento = DocumentosOrdenados.ElementAt(inicio).IdDocumento,
//                        Titulo = DocumentosOrdenados.ElementAt(inicio).Asunto,
//                        FechaEnvio = DocumentosOrdenados.ElementAt(inicio).FechaEnvio,
//                        IdPropietario = DocumentosOrdenados.ElementAt(inicio).IdUsuarioPropietario,
//                        idDocumentoRemitente = DocumentosOrdenados.ElementAt(inicio).IdDocumentoRemitente,
//                        IdCarpeta = DocumentosOrdenados.ElementAt(inicio).IdCarpeta,
//                        Codigo = DocumentosOrdenados.ElementAt(inicio).Codigo,
//                        Importancia = DocumentosOrdenados.ElementAt(inicio).Importancia,
//                        estatus = DocumentosOrdenados.ElementAt(inicio).Estatus,
//                    };

//                    docs.Add(doc);
//                }
//                return Ok(docs);


//            }

//        }
//    }
//    if (Car == 2)
//    {
//        var carpetas = db.Carpetas.Where(g => g.IdUsuarioPropietario == IdUsuarioPropietario && g.Enviados == true);
//        var cp = new List<CarpetaIdViewModel>();
//        Guid idC;
//        foreach (var carpeta in carpetas)
//        {
//            icrp = new CarpetaIdViewModel();
//            icrp.IdCarpeta = carpeta.IdCarpeta;
//            idC = icrp.IdCarpeta;
//            var documentos = db.Documentos.Where(g => g.IdUsuarioPropietario == IdUsuarioPropietario && g.Estatus != 5 && g.IdCarpeta == idC).ToList();
//            var DocumentosOrdenados = documentos.OrderByDescending(g => g.FechaActualizacion);
//            var docs = new List<DocumentosListViewModel>();
//            int sizeOfDocs = DocumentosOrdenados.Count();
//            if (sizeOfDocs <= 20)
//            {
//                foreach (var documento in DocumentosOrdenados)
//                {
//                    doc = new DocumentosListViewModel
//                    {
//                        IdDocumento = DocumentosOrdenados.ElementAt(inicio).IdDocumento,
//                        Titulo = DocumentosOrdenados.ElementAt(inicio).Asunto,
//                        FechaEnvio = DocumentosOrdenados.ElementAt(inicio).FechaEnvio,
//                        IdPropietario = DocumentosOrdenados.ElementAt(inicio).IdUsuarioPropietario,
//                        idDocumentoRemitente = DocumentosOrdenados.ElementAt(inicio).IdDocumentoRemitente,
//                        IdCarpeta = DocumentosOrdenados.ElementAt(inicio).IdCarpeta,
//                        Codigo = DocumentosOrdenados.ElementAt(inicio).Codigo,
//                        Importancia = DocumentosOrdenados.ElementAt(inicio).Importancia,
//                        estatus = DocumentosOrdenados.ElementAt(inicio).Estatus,
//                    };

//                    docs.Add(doc);


//                    return Ok(docs);
//                }
//            }
//            else
//            {
//                for (; inicio <= final; inicio++)
//                {
//                    doc = new DocumentosListViewModel
//                    {
//                        IdDocumento = DocumentosOrdenados.ElementAt(inicio).IdDocumento,
//                        Titulo = DocumentosOrdenados.ElementAt(inicio).Asunto,
//                        FechaEnvio = DocumentosOrdenados.ElementAt(inicio).FechaEnvio,
//                        IdPropietario = DocumentosOrdenados.ElementAt(inicio).IdUsuarioPropietario,
//                        idDocumentoRemitente = DocumentosOrdenados.ElementAt(inicio).IdDocumentoRemitente,
//                        IdCarpeta = DocumentosOrdenados.ElementAt(inicio).IdCarpeta,
//                        Codigo = DocumentosOrdenados.ElementAt(inicio).Codigo,
//                        Importancia = DocumentosOrdenados.ElementAt(inicio).Importancia,
//                        estatus = DocumentosOrdenados.ElementAt(inicio).Estatus,
//                    };

//                    docs.Add(doc);
//                }
//                return Ok(docs);


//            }

//        }
//    }
//    if (Car == 3)
//    {
//        var carpetas = db.Carpetas.Where(g => g.IdUsuarioPropietario == IdUsuarioPropietario && g.Borradores == true);
//        var cp = new List<CarpetaIdViewModel>();
//        Guid idC;
//        foreach (var carpeta in carpetas)
//        {
//            icrp = new CarpetaIdViewModel();
//            icrp.IdCarpeta = carpeta.IdCarpeta;
//            idC = icrp.IdCarpeta;
//            var documentos = db.Documentos.Where(g => g.IdUsuarioPropietario == IdUsuarioPropietario && g.Estatus != 5 && g.IdCarpeta == idC).ToList();
//            var DocumentosOrdenados = documentos.OrderByDescending(g => g.FechaActualizacion);
//            var docs = new List<DocumentosListViewModel>();
//            int sizeOfDocs = DocumentosOrdenados.Count();
//            if (sizeOfDocs <= 20)
//            {
//                foreach (var documento in DocumentosOrdenados)
//                {
//                    doc = new DocumentosListViewModel
//                    {
//                        IdDocumento = DocumentosOrdenados.ElementAt(inicio).IdDocumento,
//                        Titulo = DocumentosOrdenados.ElementAt(inicio).Asunto,
//                        FechaEnvio = DocumentosOrdenados.ElementAt(inicio).FechaEnvio,
//                        IdPropietario = DocumentosOrdenados.ElementAt(inicio).IdUsuarioPropietario,
//                        idDocumentoRemitente = DocumentosOrdenados.ElementAt(inicio).IdDocumentoRemitente,
//                        IdCarpeta = DocumentosOrdenados.ElementAt(inicio).IdCarpeta,
//                        Codigo = DocumentosOrdenados.ElementAt(inicio).Codigo,
//                        Importancia = DocumentosOrdenados.ElementAt(inicio).Importancia,
//                        estatus = DocumentosOrdenados.ElementAt(inicio).Estatus,
//                    };

//                    docs.Add(doc);


//                    return Ok(docs);
//                }
//            }
//            else
//            {
//                for (; inicio <= final; inicio++)
//                {
//                    doc = new DocumentosListViewModel
//                    {
//                        IdDocumento = DocumentosOrdenados.ElementAt(inicio).IdDocumento,
//                        Titulo = DocumentosOrdenados.ElementAt(inicio).Asunto,
//                        FechaEnvio = DocumentosOrdenados.ElementAt(inicio).FechaEnvio,
//                        IdPropietario = DocumentosOrdenados.ElementAt(inicio).IdUsuarioPropietario,
//                        idDocumentoRemitente = DocumentosOrdenados.ElementAt(inicio).IdDocumentoRemitente,
//                        IdCarpeta = DocumentosOrdenados.ElementAt(inicio).IdCarpeta,
//                        Codigo = DocumentosOrdenados.ElementAt(inicio).Codigo,
//                        Importancia = DocumentosOrdenados.ElementAt(inicio).Importancia,
//                        estatus = DocumentosOrdenados.ElementAt(inicio).Estatus,
//                    };

//                    docs.Add(doc);
//                }
//                return Ok(docs);


//            }

//        }
//    }
//    return Ok();
//}