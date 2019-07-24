package com.example.ricky.misoficios.Modelos

class Oficios (var asunto: String, var remitente: String, var  destinatario: String, var fecha: String, var folio: String, var codigo: String, var importancia: Byte )

class Grupos (var nombre : String, var destinatarios: String, var propietario: String, var fecha: String)

class Empleados (var nombre: String, var puesto: String)

class EmpleadosGrupo(var nombre: String, var puesto: String)

data class Permiso (var IdPermiso: String, val Numero: Int)

class Mensaje (var NombreArea: String, var Comentario: String)

class LoginReq (var NickName: String, var Password : String)

class LoginRes (val NickName: String,val Password: String, val Permisos: List<Permiso> )
