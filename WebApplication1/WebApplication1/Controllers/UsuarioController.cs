using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Security.Cryptography;
using System.Text;
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
            login.Password = GetMd5Hash(login.Password);

            if (login.NickName == null)
            {
                return BadRequest("El nombre de usuario o contraseña invalidos");
            }

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
                consul.Read();

                us = new vwUsuario();
                try
                {
                    us.IdUsuario = new Guid(consul["IdUsuario"].ToString());
                    us.NickName = consul["NickName"].ToString();
                    us.Password = consul["Password"].ToString();
                    us.IdArea = new Guid(consul["IdArea"].ToString());
                    us.NombreArea = consul["AreaNombre"].ToString();
                    us.IdGrupo = new Guid(consul["IdGrupo"].ToString());
                    us.GrupoUsuarios = consul["Nombre"].ToString();
                    while (consul.Read())
                    {
                        Permiso permiso = new Permiso();
                        permiso.IdPermiso = consul["IdDerecho"].ToString();
                        permiso.Numero = consul["NombreDerecho"].ToString();
                        us.Permisos.Add(permiso);
                    }
                    usuario.Add(us);
                }
                catch (Exception e) { return BadRequest("El nombre de usuario o contraseña invalidos"); }
                if (login.NickName == us.NickName && login.Password == us.Password)
                {
                    return Ok(usuario);
                }
                else { return BadRequest("Datos inválidos: " + login.Password); }




                conn.Close();

            }
        }
        private string GetMd5Hash(string Valor)
        {
            MD5 md5 = MD5.Create();
            byte[] hash = md5.ComputeHash(Encoding.ASCII.GetBytes(Valor));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < hash.Length; i++)
            {
                sb.Append(hash[i].ToString("x2"));
            }
            return sb.ToString();
        }
    }
}