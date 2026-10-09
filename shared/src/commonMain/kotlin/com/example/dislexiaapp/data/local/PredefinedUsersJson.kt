package com.example.dislexiaapp.data.local

/**
 * Fuente de datos JSON con credenciales predefinidas para pruebas de roles.
 * Cumple con el requisito de tratar las credenciales desde un archivo/fuente JSON.
 */
object PredefinedUsersJson {
    val JSON_DATA: String = """
    [
      {
        "id": "usr_001",
        "username": "admin",
        "password": "123",
        "nombre": "Lic. Carlos Administrador",
        "email": "admin@dislexiaapp.com",
        "rol": "ADMINISTRADOR",
        "avatarEmoji": "👑"
      },
      {
        "id": "usr_002",
        "username": "supervisor",
        "password": "123",
        "nombre": "Mtra. Laura Rivera",
        "email": "supervisor@dislexiaapp.com",
        "rol": "SUPERVISOR",
        "avatarEmoji": "📋"
      },
      {
        "id": "usr_003",
        "username": "cliente",
        "password": "123",
        "nombre": "Mateo Hernández",
        "email": "mateo.cliente@dislexiaapp.com",
        "rol": "CLIENTE",
        "avatarEmoji": "🎒"
      },
      {
        "id": "usr_004",
        "username": "doctor",
        "password": "123",
        "nombre": "Dr. Fernando Morales",
        "email": "dr.morales@dislexiaapp.com",
        "rol": "DOCTOR",
        "avatarEmoji": "🩺"
      },
      {
        "id": "usr_005",
        "username": "vendedor",
        "password": "123",
        "nombre": "Ana Sofía Vendedora",
        "email": "ventas@dislexiaapp.com",
        "rol": "VENDEDOR",
        "avatarEmoji": "💼"
      }
    ]
    """.trimIndent()
}
