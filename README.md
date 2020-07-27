# Url Shortener for Java Challenge

# Funcionalidad

El acortador funciona recibiendo a través de 
* POST un url en el formato:
{"url":"https://nearsoft.com"}
Del cual genera un #####alias

* GET un url con el alias buscado
http:localhost/BdfGT
Del cual regresa el url original

###Los alias se general de acuerdo al contenido de la url bajo los criterios:
* Contiene la palabra google
El alias contiene 5 caracteres alfabéticos
* Contiene la palabra yahoo
El alias contiene 7 caracteres alfanuméricos
*Cualquier otro
Basado en la url original removiendo caracteres especiales, vocales y números 

# Ejemplos de Uso
https://nearsoft.com -> alias:httpsnrsftcm
www.google.com -> alias:lavlG
www.yahoo.com -> alias:5Xp1QsM
www.something.mx/435#25 -> alias:wwwsmthngmx
www.algo.mx/428%go%here -> alias:wwwlgmxghr
https://www.google.com.mx/maps/@22.1440199,-100.9451008,15z -> alias:hsPwH
www.udfhf@.mx/53jfs -> Invalid Url

# Enfoque
División del proyecto:
### Modelo
El cuál contiene los atributos
* url
* alias
junto con sus respectivos métodos de acceso e impresión
### Controlador
El cuál responde a las solicitudes GET y POST
### Servicio
Contiene los métodos de manipulación de modelo, usados en el controlador
* generateAlias : Genera el alias de la url
* findExistingUrl :Verifica la existencia del url en la lista de url generados
* getUrlByAlias : Encuentra el url correspondiente al alias
* validateUrl : Revisa si el el url entrante es valido.
