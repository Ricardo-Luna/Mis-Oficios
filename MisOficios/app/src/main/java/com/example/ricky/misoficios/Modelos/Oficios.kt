package com.example.ricky.misoficios.Modelos

//class Oficios(
//
//    var IdDocumento: String?,
//    var remitente: String?,
//    var IdDocumentoOrigen: String?,
//    var IdDocumentoRemitente: String?,
//    var IdCarpeta: String?,
//    var IdArea: String?,
//    var codigo: String?,
//    var asunto: String?,
//    var Contenido: String?,
//    var fecha: String?,
//    var FechaEnvio: String?,
//    var importancia: Byte?,
//    var Tipo: String?,
//    var Estatus: String?,
//    var Nota: String?,
//    var SelloDigital: String?,
//    var IdUsuarioActualizacion: String?,
//    var FechaActualizacion: String?
//)


class nickname(
    NickName: String
)

class Carpetas(
   var IdCarpeta: String?  ,
   var IdUsuarioPropietario: String?  ,
   var Nombre: String? ,
   var Recibidos: Byte? ,
   var Enviados: Byte?,
   var Borradores: Byte? ,
   var IdUsuarioActualizacion: String? ,
   var FechaActualizacion: String
)


class folder(
   var IdCarpeta: String?  ,
   var IdUsuarioPropietario: String?  ,
   var Nombre: String? ,
   var Recibidos: Byte? ,
   var Enviados: Byte?,
   var Borradores: Byte? ,
   var IdUsuarioActualizacion: String? ,
   var FechaActualizacion: String
)




class Oficios(
    var asunto: String?,
    var remitente: String?,
    var destinatario: String?,
    var fecha: String?,
    var folio: String?,
    var codigo: String?,
    var importancia: Byte?
)



class Oficios2(
    var IdDocumento: String?,
    var Titulo: String?,
    var FechaEnvio: String?,
    var IdPropietario: String?,
    var idDocumentoRemitente: String?,
    var IdCarpeta: String?,
    var Codigo: String?,
    var Importancia: String?,
    var estatus: String?,
    var PropietarioNombreCompleto: String

    )

class Documentos2(
    var IdDocumento: String?,
    var Titulo: String?,
    var IdPropietario: String?,
    var idDocumentoRemitente: String?,
    var IdCarpeta: String?,
    var Codigo: String?,
    var Importancia:    String?,
    var estatus: String?,
    var FechaEnvio: String?,
    var PropietarioNombreCompleto: String
)


class valor(
    var cantidad: Int?
)


class Documentos(
    var asunto: String?,
    var remitente: String?,
    var destinatario: String?,
    var fecha: String?,
    var folio: String?,
    var codigo: String?,
    var importancia: Byte?
)


class Gruposrv(
    var IdGrupo: String,
    var idUsuarioPropietario: String,
    var Nombre: String?,
    var IdUsuarioActualizacion: String?,
    var FechaActualizacion: String?
)


class Grupos(
    var IdGrupo: String,
    var idUsuarioPropietario: String,
    var Nombre: String?,
    var IdUsuarioActualizacion: String?,
    var FechaActualizacion: String?
)


class Empleados(
    var nombre: String,
    var puesto: String
)


class EmpleadosGrupo(
    var nombre: String,
    var puesto: String
)


data class Permiso(
    val numeroPermiso: Int
)


class LoginReq(
    var NickName: String,
    var Password: String
)


public class LoginRes(
    var IdUsuario: String,
    var IdPersona: String,
    var varIdArea: String,
    var NickName: String,
    var Password: String,
    var NombreCompleto: String,
    var Correo: String,
    var Puesto: String,
    var Status: String,
    var Rol: String,
    var Permisos: List<Permiso>
)
