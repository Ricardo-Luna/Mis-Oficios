package com.example.ricky.misoficios.Modelos

class Oficios (var asunto: String,
               var remitente: String,
               var  destinatario: String,
               var fecha: String,
               var folio: String,
               var codigo: String,
               var importancia: Byte
               )



class Grupos (var nombre : String,
              var destinatarios: String,
              var propietario: String,
                  var fecha: String
             )


class Empleados (var nombre: String,
                 var puesto: String
                )


class EmpleadosGrupo(var nombre: String,
                     var puesto: String
                    )



data class Permiso (val numeroPermiso: Int
                    )



class Mensaje (var NombreArea: String,
               var Comentario: String
              )



class LoginReq (var NickName: String,
                var Password : String
                )



public class LoginRes (var IdUsuario: String,
                var IdPersona: String,
                var varIdArea: String,
                var NickName: String,
                var Password: String,
                var NombreCompleto: String,
                var Correo : String,
                var Puesto: String,
                var Status: String,
                var Rol: String,
                var Permisos: List<Permiso>
                )
