## Introducción 
Aplication que se levanta en el puerto 5000 para recibir peticiones
de la aplicacion tests para enviarsela a esta aplicación:

Internamente dicha aplicación enviará peticiones a la aplicacion mock por el
puerto 3001.

A nivel técnico:
 - Esta aplicación dispone de un swagger por si queremos ver el contrato que tiene: http://localhost:5000/swagger-ui/index.html

 - Se ha optado por usar un cliente web reactivo que respecto a resttemplate
evitamos bloquear los hilos en la llamada a la otra api, de esta manera ganamos en una mayor eficiencia
 - 
NOTA: Sería interesante para las pruebas de aceptación (considerando prueba de aceptación las que prueba todo el flujo desde que nos llega una petición http) usar alguna libreria para mockear WebClient y no nuestro servicio interno,

https://www.baeldung.com/spring-mocking-webclient

## Arranque de la aplicacion
mvn spring-boot:run




