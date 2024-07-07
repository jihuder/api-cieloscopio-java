# Api del Clima CieloScopio

## OpenWeather API Client
Este proyecto es un cliente Java para consultar la API de clima utilizando GSON. El programa se conecta a la API de OpenWeather para obtener información meteorológica y presenta los datos de una manera estructurada y fácil de entender.

## Características
Consulta datos meteorológicos actuales de cualquier ciudad.
Utiliza la librería GSON para parsear respuestas JSON de la API.
Muestra información relevante como la temperatura, humedad, y descripción del clima.


### Requisitos
Java 8 o superior
IntelliJ IDEA (opcional, pero recomendado)
Una clave API de OpenWeather

# Diseño del aplicativo por UML


![Imagen UML del CieloScopio](https://github.com/jihuder/cieloscopio-api-java/blob/main/imagenes/UML-CieloScopio-api.jpg?raw=true)

Se comienza con la clase Principal en donde se ejecuta el aplicativo. Pasamos a la capa CONTROLLER en donde hay una clase Búsqueda, la cual  es el controlador que llama los métodos de las otras dos clases de Clima y Ubicación, los cuales se llaman en  determinada línea de tiempo. Se hace una capa MODELOS en donde ubicamos las clases Clima y Ubicación, cuyo objetivo es asociar los datos que necesitaremos de la API para darle una estructura. Esto es debido a que la API nos envía muchos datos, de los cuales utilizaremos algunos. También haremos la manipulación de esos datos, pidiendo y recibiendo la comunicación de la API, cumpliendo la lógica de negocio del requerimiento puntual dentro de esta capa, aparte, se crea una interfaz debido a que estas dos clases que la firman tiene el mismo método cuyo propósito es el mismo y devuelven el mismo tipo de dato. No se HACE una capa DTO porque no vamos a enviar información entre capas, ya que no tenemos Frontend y tampoco vamos a enviar la información por HTTP. Tampoco se hace una capa de ENTIDAD porque no vamos a utilizar una base de datos en nuestro sistema o registrarlo dentro.

Tenemos que aclarar que la capa de MODELOS no  nos sirve para la capa de ENTIDAD, porque tienen propósitos diferentes. La capa MODELOS nos sirve para agrupar los datos y hacer la lógica de negocio de una entidad, mientras que la capa de ENTIDAD nos sirve para registrar esa entidad en base de datos. Aunque las dos pueden tener la misma estructura, sus propósitos son totalmente diferentes y van a tener cambios a medida que se vaya desarrollando el aplicativo.


## Relaciones UML

Asociación: Se utiliza para indicar que una clase conoce a otra y puede interactuar con ella. No implica una relación fuerte y se representa con una línea sólida entre las clases. Puede tener un verbo o etiqueta que describa la naturaleza de la interacción, como "utiliza", "contiene", "colabora con", etc.

Agregación: Indica una relación de "todo a parte", donde una clase (todo) contiene a otras clases (partes). Se representa con un rombo hueco en la clase "todo" y se puede usar verbos como "incluye", "tiene", "comprende", etc.

Composición: Es una forma más fuerte de agregación, donde las partes solo pueden pertenecer a un todo específico y no pueden existir independientemente del todo. Se representa con un rombo lleno en la clase "todo" y se pueden usar verbos como "constituye", "forma parte de", "es parte de", etc.

Dependencia: Indica que una clase depende de otra para realizar alguna operación o función, pero no implica una asociación directa. Se representa con una línea punteada y una flecha que va desde la clase que depende hacia la clase de la que depende. No suele tener un verbo específico, pero se puede acompañar con un comentario que describa la dependencia.

Generalización (Herencia): Indica una relación "es un/a" entre una clase general (superclase o clase base) y una clase más específica (subclase o clase derivada). Se representa con una línea sólida con un triángulo hueco en el extremo de la superclase hacia la subclase. No suele tener un verbo específico más allá de "es un/a".

Realización (Implementación): Indica que una clase implementa las operaciones definidas en una interfaz. Es específico de los lenguajes que admiten interfaces y se representa con una línea punteada con un triángulo hueco en el extremo de la clase que implementa hacia la interfaz. No suele tener un verbo específico más allá de "implementa".

Estos verbos o etiquetas ayudan a clarificar la naturaleza y el propósito de cada relación entre clases en el diagrama UML, facilitando la comprensión del diseño de software.


# Documentación de la API

El propósito esta sección es documentar el proceso de funcionamiento de la API, en OpenWeather utilizaremo dos APIS para el mismo proposito, por lo cual una depende de la otra, es decir, a partir de la primera petición de la primera API podemos obtener los datos de geolocalización, los cuales, los utilizaremos en la segunda consulta de la segunda API para obtener los datos del clima.

## Primera Consulta:

### La primera consulta la hacemos a la API Geocoding API: 

La API de geocodificación es una herramienta sencilla de usar, basa su búsqueda con nombres y coordenadas geográficas. 

#### ¿Pero qué es la Geocodificación?

La geocodificación es el proceso de transformación de cualquier nombre semántico de ubicación en coordenadas geográficas, y viceversa (geocodificación inversa). La API de geocodificación de OpenWeather admite tanto el método directo como el inverso, y funciona a nivel de nombres de ciudades, áreas y distritos, países y estados.

Esto significa que, en el concepto de OpenWeather como aplicación, se precisa de dos pasos para localizar el clima de una ciudad. OpenWeather ha desarrollado una forma precisa para especificar cualquier ubicación, reduciendo los fallos de errores geográficos,convirtiendo esta forma en una API e integrándola en todas las APIs de OpenWeather. Es decir que, OpenWeather se conforma por un ecosistema que se divide en diferentes APIs que se consultan entre sí para obtener información.

## Geocoding API

Es el primer paso para obtener los datos del clima. Aunque es obvio, a esta API primero necesitamos proporcionarle el lugar para hacer nuestra consulta. A este lugar lo llamaremos un string semántico, esto se debe a que le podemos dar la ubicación en diferentes formas: ciudad, capital, país, etc., y ser tan específicos como deseamos.

Esta API posee dos métodos, uno de geocodificación inversa y otro de coordenadas geográficas. Es decir, a partir de un string semántico obtenemos la ubicación en coordenadas, o a partir de las coordenadas obtenemos un string semántico, en otras palabras, el sitio que estamos buscando.


---------------------------------------------

* La geocodificación directa convierte el nombre especificado de una ubicación o código postal en coordenadas geográficas exactas.
* La geocodificación inversa convierte las coordenadas geográficas en los nombres de las ubicaciones cercanas.

Para este proyecto vamos a utilizar la codificación directa.


### Geocodificación directa

La geocodificación directa permite obtener coordenadas geográficas (latitud, longitud) utilizando el nombre de la ubicación (nombre de la ciudad o nombre del área). Si utilizamos el parámetro limit en la llamada API, podemos limitar la cantidad de ubicaciones con el mismo nombre que se verán en la respuesta API (por ejemplo, Londres en el Reino Unido y Londres en los EE. UU.).

Esto quiere decir que, tan solo dando el nombre como parámetro en la petición, vamos a obtener las coordenadas exactas del lugar requerido para utilizarlas después en la otra request de la API, cuya respuesta es la obtención de datos del clima de dicha ubicación.

OpenWeather nos proporciona una URL de manera de ejemplo. En este ejemplo, podemos  enviar más datos semánticos para que sea más exacta, pero vamos a utilizar solo un parámetro en nuestras consultas como se requiere la lógica de negocio:

http://api.openweathermap.org/geo/1.0/direct?q={city name},{state code},{country code}&limit={limit}&appid={API key}


### Pero utilizaremos esta forma: 

http://api.openweathermap.org/geo/1.0/direct?q={city name}&limit={limit}&appid={API key}


🤷‍♂️![Como se utilizan los parametros GeoCoding](https://github.com/jihuder/api-cieloscopio-java/blob/main/imagenes/parametros-geocoding.png?raw=true)

#### NOTA: La respuesta la manipularemos en Postman.

( ͡°👅 ͡°)



## Segunda Consulta:

### La Segunda consulta la hacemos a la API Current weather data:

Una vez que hemos recopilado los datos de la primera consulta a la API de Geocodificación, podemos utilizar estos datos como parámetros en la API Current Weather Data. Esta última API utiliza los parámetros que le proporcionamos para obtener información sobre el clima del lugar en cuestión y devolvernos una respuesta. Es importante tener en cuenta que, a diferencia de la API de Geocoding, la API de Datos Meteorológicos Actuales solo cuenta con un método de consulta.

## Current Weather Data:


Current Weather Data nos ofrece información meteorológica actual de cualquier ubicación en el mundo. Utilizan diversas fuentes como modelos meteorológicos globales y locales, datos satelitales, radares y una extensa red de estaciones meteorológicas para recopilar y analizar la información.  Los datos nonos lo proporcionan en formato JSON, XML o HTML.

## La utilizaremos de esta forma:

https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={API key}

Tenemos que considerar que la longitud y latitud serán obtenidas a través de GeoCoding para luego pasarlas como parámetros a Current Weather Data.

![Parametros de Current Weather Data](https://github.com/jihuder/api-cieloscopio-java/blob/main/imagenes/Current%20Weather%20Data.png?raw=true)

#### NOTA: La respuesta la manipularemos en Postman.😶


