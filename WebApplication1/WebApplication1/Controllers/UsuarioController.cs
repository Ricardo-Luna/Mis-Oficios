using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Web.Http;
using System.Web.Http.Description;
using WebApplication1.ModelosDataCenter;
using WebApplication1.ModelsDataCenter;


namespace WebApplication1.Controllers
{
    public class UsuariosController : ApiController
    {
        private Model1 db = new Model1();
        vwUsuario us;
        private string connectionString = "data source=Desarrollo;initial catalog=DataCenter;user id=sa;password= Lamismadesiempre= ;multipleactiveresultsets=True;application name=EntityFramework";
        private System.Data.SqlClient.SqlDataReader consul;
        [HttpPost]
        [Route("api/Usuarios/Login")]
        [ResponseType(typeof(Usuario))]
        public IHttpActionResult PostGrupos([FromBody] Login login)
        {
            using (SqlConnection conn = new SqlConnection(connectionString))
            using (SqlCommand cmd = new SqlCommand("uspUsuariosGrupoDerechosObtener", conn))
            {
                var usuario = new List<vwUsuario>();
                
                cmd.CommandType = CommandType.StoredProcedure;
                SqlParameter nn = cmd.Parameters.Add("@NickName", SqlDbType.VarChar, 25);
                nn.Direction = ParameterDirection.Input;
                nn.Value = login.NickName;
                conn.Open();
                consul = cmd.ExecuteReader();
                while (consul.Read())
                {
                    us = new vwUsuario();
                    Permiso permiso = new Permiso();
                    us.IdUsuario = new Guid(consul["IdUsuario"].ToString());
                    us.NickName = consul["NickName"].ToString();
                    us.Password = consul["Password"].ToString();
                    us.IdArea = new Guid(consul["IdArea"].ToString());
                    permiso.IdPermiso = System.Convert.ToInt32(consul["IdDerecho"].ToString());

                    usuario.Add(us);
                }
                conn.Close();
                return Ok(usuario);
            }
        }
    }
}