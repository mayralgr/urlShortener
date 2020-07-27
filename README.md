# Url Shortener for Java Challenge

# Funcionalidad

El acortador funciona recibiendo a través de 
* POST un url en el formato:

{"url":"https://nearsoft.com"}


Del cual genera un alias
* GET un url con el alias buscado

http:localhost/BdfGT


Del cual regresa el url original

### Los alias se generan de acuerdo al contenido de la URL bajo los criterios:
* Si contiene la palabra google

    El alias contendrá 5 caracteres alfabéticos
* Si contiene la palabra yahoo

    El alias contiene 7 caracteres alfanuméricos
* Cualquier otro

    El alias estará basado en la URL original removiendo caracteres carácteres, vocales y números 

# Creación de Alias
#### Request
```
Método: POST
URL: http://localhost:8080/
Cuerpo: content-type = json
{
   "url": "https://nearsoft.com"
}
```

#### Respuesta
```
200 OK
  alias:aliasGenerado
400 Bad Request
  Invalid Url
```

# Obtención de URL original a través del Alias
```
Método: GET
URL: http://localhost:8080/{alias}
```
#### Respuestas
```
200 OK
  urlOriginal
400 Bad Request 
  The alias does not match any register
```

# Ejemplos de Uso
* https://nearsoft.com -> alias:httpsnrsftcm
* www.google.com -> alias:lavlG
* www.yahoo.com -> alias:5Xp1QsM
* www.something.mx/435#25 -> alias:wwwsmthngmx
* www.algo.mx/428%go%here -> alias:wwwlgmxghr
* https://www.google.com.mx/maps/@22.1440199,-100.9451008,15z -> alias:hsPwH
* www.udfhf@.mx/53jfs -> Invalid Url

# Enfoque
## División del proyecto
### Modelo
El cuál contiene los atributos
```
- url
- alias
```
junto con sus respectivos métodos de acceso e impresión
### Controlador
El cuál responde a las solicitudes GET y POST
### Servicio
Contiene los métodos de manipulación de modelo, usados por el controlador
* generateAlias : Llama al método generateRandomAlias enviando las restricciones de tamaño y formato.
* findExistingUrl : Verifica la existencia del url en la lista de url generados.
* getUrlByAlias : Encuentra el url correspondiente al alias.
* validateUrl : Verifica si el el url entrante es valido.
* isAGoogleUrl : Verifica si el url contiene la palabra google.
* isAYahooUrl : Verifica si el url contiene la palabra yahoo.
* generateRandomAlias: Genera el alias de acuerdo con el tamaño y formato requeridos. 
