# Seguridad Alpha

# Software

Eclipse

Spring boot

Postgresql

# application.yml

Para correr el ejecutable se tiene que usar un comando muy especifico para que pueda usar el archivo de configuración yml que esta ubbicado en la carpeta "configuracion" en el servidor, que contiene lo siguiente:

```
server:
  port: 8090
jwt:
  expiration: 36000
  secret: secret
spring:
  jpa:
    hibernate:
      ddl-auto: update

  data:
    jpa.repositories.enabled: true
  datasource:
    initialization-mode: always
    driverClassName: org.postgresql.Driver
    url: jdbc:postgresql://localhost:5432/app
    username: postgres
    password: 1234
    continueOnError: true
my:
    property:
        s: encriptadoclubalphaprueba
```

Comando paara cargar el archivo application.yml externo:

```
java -jar crud-0.0.1-SNAPSHOT.jar --spring.config.name=application,jdbc --spring.config.location=file:///home/administrador/configuracion/application.yml
```


Puerto en el que se ejecuta la aplicación, en este caso es el 8090.

"expiration" es el tiempo en el que se expirará el token, en este caso son 36 segundos, pero internamente se multiplica por 1000 para dar un total de 10 horas. 
Esto se configuró de esta manera debido a que es más sencillo pasar de un minuto a una hora o más facilmente.

"secret" es la palabra con la que se encripta el token con el algoritmo HS512.

# Uso de la aplicación

# Ingresar un usuario

Existe un endpoint para agregar usuarios tanto normal como administrador. La unica diferencia es que el administrador puede acceder a funciones extras. 

# Ejemplo

Estos ejemlos estan disponibles en la colección de postman **ServiciosAppCopy**

Por un metodo Post en postman en el siguiente endpoint: **192.168.20.57:8090/auth/nuevo**. En el cual se enviará un Json con lo siguiente:

**Aunque dice que se usa un nombre y nombreUsuario estos serán llenados con la Membresia e ID de cliente respectivamente, no afecta en nada su funcionamiento.**

```
{
    "nombre": "admin",
    "nombreUsuario": "admin",
    "email": "admin@gmail.com",
    "password": "admin",
    "roles": ["admin","user"],
    "estatus": "Cliente Vigente",
    "cliente": "EDUARDO ALVARADO ALONSO",
    "clienteTipo": "ESTANDAR",
    "estatusCobranza": "Activo - Al Corriente",
    "nivel1": "Club Alpha 3"
}
```

el cual devolverá una Json de notificación para confirmar si el usuario esta agregado o si ya existe, campos mal puestos. 

```
{
    "mensaje": "Usuario Agregado"
}
```

```
{
    "mensaje": "ese nombre ya existe"
}

```

```
{
    "mensaje": "Campos mal puestos"
}

```

# Inicio de Sesión

Por un metodo Post en postman en el siguiente endpoint: **192.168.20.57:8090/auth/login**. En el cual se enviará un Json con lo siguiente:

```
{
    "nombreUsuario": "admin",
    "password": "F0G6sUwEK38="
}
```

Si la contraseña o el usuario son incorrectos este devolverá un Json con la siguiente estructura: 

```
{
    "mensaje": "Campos mal puestos"
}

```

En caso contrario, devolverá un Json como este: 

```
{
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiAkMmEkMTAkbTR4a3RDbGxiTWh6Zjl3NEc1MEFhLktsMEJ3bEFVZlhOZHBETGFrMU5YcGsvNEJvczFmVWkiLCJpYXQiOjE2MjczMTU2MTEsImV4cCI6MTYyNzM1MTYxMX0.LtCqYE_-h73tJ3U9E-ppILjSAmvlTCuiBv3YYjzIc8B2hXx8c6svfIMR66h3XGvdkafte2aPJx0Zgw0wy6v60w",
    "bearer": "Bearer",
    "nombreUsuario": "admin",
    "authorities": [
        {
            "authority": "ROLE_USER"
        },
        {
            "authority": "ROLE_ADMIN"
        }
    ]
}
```
El cual contiene el token de autorización que se usará para todas las acciones o enventos dentro de la aplicación, ademas de los roles que tiene el usuario, en este caso es de administrador,
un usuario sin rol de adminitrador no tendrá acceso a otras acciones o enventos que el adminitrador sí. 

# **Ejemplo de Json de un usuario simple:**

Por postman se envia el siguiente Json en el body:

IdCliente

password con formato (ese es el formato de la contraseña, sin embargdo debe de ir cifrada) IdCliente + . + fechaDeNacimiento (aaaa/mm/dd), sin / como en el siguiente ejemplo: 
```
{
    "nombreUsuario": "123456",
    "password": "123456.19980111"
}
```
Donde devolverá lo siguiente en postman si el login es correcto: 
```
{
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiAkMmEkMTAkbTR4a3RDbGxiTWh6Zjl3NEc1MEFhLktsMEJ3bEFVZlhOZHBETGFrMU5YcGsvNEJvczFmVWkiLCJpYXQiOjE2MjczMTU2MTEsImV4cCI6MTYyNzM1MTYxMX0.LtCqYE_-h73tJ3U9E-ppILjSAmvlTCuiBv3YYjzIc8B2hXx8c6svfIMR66h3XGvdkafte2aPJx0Zgw0wy6v60w",
    "bearer": "Bearer",
    "nombreUsuario": "123456",
    "authorities": [
        {
            "authority": "ROLE_USER"
        }
    ]
}
```

El Token se usará con el encabezado con Authorization como lo siguiente: 

```
Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiAkMmEkMTAkbTR4a3RDbGxiTWh6Zjl3NEc1MEFhLktsMEJ3bEFVZlhOZHBETGFrMU5YcGsvNEJvczFmVWkiLCJpYXQiOjE2MjczMTU2MTEsImV4cCI6MTYyNzM1MTYxMX0.LtCqYE_-h73tJ3U9E-ppILjSAmvlTCuiBv3YYjzIc8B2hXx8c6svfIMR66h3XGvdkafte2aPJx0Zgw0wy6v60w
```

Una vez puesto ese encabezado se puede tener acceso a cualquiera de los WebServices que estan en la coleccion de postman **ServiciosDemoTest**


# cifrado en typescript

En el frontend se usa la bliblioteca **crypto-js** y el algoritmo DES para cifrar la contraseña antes de enviarla, pero previamente se define una llave de cifrado que comparten el backend y el frontend (más adelante se cambiará con un algoritmo más seguro como RSA). 
