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
    var NombreCompleto: String
)

class Carpetas(
    var IdCarpeta: String?,
    var IdUsuarioPropietario: String?,
    var Nombre: String?,
    var Recibidos: Boolean?,
    var Enviados: Boolean?,
    var Borradores: Boolean?,
    var IdUsuarioActualizacion: String?,
    var FechaActualizacion: String
)

//106.98
//- 4
//
class folder(
    var IdCarpeta: String?,
    var IdUsuarioPropietario: String?,
    var Nombre: String?,
    var Recibidos: Boolean?,
    var Enviados: Boolean?,
    var Borradores: Boolean?,
    var IdUsuarioActualizacion: String?,
    var FechaActualizacion: String
)

class valor(
    var cantidad: Int?
)

class UsuariosGrupo(
    var UsuarioNombreCompleto: String
)

class recycler(
    var expanded: Boolean
) {
    fun isExpanded(): Boolean {
        return expanded
    }
}

class Oficio(
    var ContenidoHTML: String?
)

class Visto(
    var FechaLectura: String,
    var IdDocumento: String?
)

class VistoPost(
    var FechaLectura: String,
    var IdDocumento: String?
)
class hash(
    var cadenaOriginal: String,
    var IdPropietario: String
)


class Oficios(
    var IdDocumento: String?,
    var Titulo: String?,
    var FechaEnvio: String?,
    var FechaCreacion: String,
    var IdPropietario: String?,
    var idDocumentoRemitente: String?,
    var IdCarpeta: String?,
    var Codigo: String?,
    var Importancia: String?,
    var estatus: String?,
    var Destinatarios: String?,
    var PropietarioNombreCompleto: String,
    var cadenaOriginal: String?
)

class Documentos(
    var IdDocumento: String?,
    var Titulo: String?,
    var FechaEnvio: String?,
    var FechaCreacion: String,
    var IdPropietario: String?,
    var idDocumentoRemitente: String?,
    var IdCarpeta: String?,
    var Codigo: String?,
    var Importancia: String?,
    var estatus: String?,
    var Destinatarios: String?,
    var PropietarioNombreCompleto: String,
    var cadenaOriginal: String?
)

class Remitente(
    var UsuarioNombreCompleto: String,
    var FechaLectura: String,
    var IdDestinatario: String
)

class Remitentes(
    var UsuarioNombreCompleto: String,
    var FechaLectura: String?,
    var IdDestinatario: String?
)

class Gruposrv(
    var IdGrupo: String,
    var Nombre: String,
    var IdUsuarioActualizacion: String?,
    var FechaActualizacion: String,
    var PropietarioNombreCompleto: String?
)

class Grupos(
    var IdGrupo: String,
    var Nombre: String,
    var IdUsuarioActualizacion: String?,
    var FechaActualizacion: String,
    var PropietarioNombreCompleto: String,
    var idUsuarioPropietario: String
)

class Empleados(
    var nombre: String,
    var puesto: String
)

class EmpleadosGrupo(
    var nombre: String,
    var puesto: String
)

class usuariosGruposR(
    var UsuarioNombreCompleto: String
)

class Integrantes(
    var UsuarioNombreCompleto: String
)

data class Permiso(
    val numeroPermiso: Int
)

class LoginReq(
    var NickName: String,
    var Password: String
)

class LoginRes(
    var IdUsuario: String?,
    var carpetaSeleccionada: String?,
    var CarpetaInicial: String?,
    var NickName: String?,
    var Password: String?,
    var NombreCompleto: String?,
    var Permisos: List<Permiso>?,
    var Recordar: Boolean
)
