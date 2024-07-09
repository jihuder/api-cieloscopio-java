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


![Imagen UML del CieloScopio](https://github.com/jihuder/api-cieloscopio-java/blob/main/imagenes/ActualizacionUML-Call.jpg?raw=true)

Se comienza con la clase Principal en donde se ejecuta el aplicativo. Pasamos a la capa CONTROLLER en donde hay una clase Búsqueda, la cual es el controlador que llama los métodos de las otras dos clases de Geocoding y CurrentWeatherData, los cuales se llaman en  determinada línea de tiempo. Se hace una capa MODELOS en donde ubicamos las clases CurrentWeatherData y Geocoding, cuyo objetivo es asociar los datos que necesitaremos de la API para darle una estructura. Esto es debido a que la API nos envía muchos datos, de los cuales utilizaremos algunos. También haremos la manipulación de esos datos, pidiendo y recibiendo la comunicación de la API, cumpliendo la lógica de negocio del requerimiento puntual dentro de esta capa, aparte, se crea una interfaz Call debido a que estas dos clases que la firman tiene el mismo método cuyo propósito es el mismo y devuelven el mismo tipo de dato. No se HACE una capa DTO porque no vamos a enviar información entre capas, ya que no tenemos Frontend y tampoco vamos a enviar la información por HTTP. Tampoco se hace una capa de ENTIDAD porque no vamos a utilizar una base de datos en nuestro sistema o registrarlo dentro.

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

## Current Weather Data API:


Current Weather Data nos ofrece información meteorológica actual de cualquier ubicación en el mundo. Utilizan diversas fuentes como modelos meteorológicos globales y locales, datos satelitales, radares y una extensa red de estaciones meteorológicas para recopilar y analizar la información.  Los datos nonos lo proporcionan en formato JSON, XML o HTML.

## La utilizaremos de esta forma:

https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={API key}

Tenemos que considerar que la longitud y latitud serán obtenidas a través de GeoCoding para luego pasarlas como parámetros a Current Weather Data.

![Parametros de Current Weather Data](https://github.com/jihuder/api-cieloscopio-java/blob/main/imagenes/Current%20Weather%20Data.png?raw=true)

#### NOTA: La respuesta la manipularemos en Postman.😶



# ¿Que aprenderemos?

Este proyecto tiene como objetivo entender el funcionamiento del protocolo HTTP y su aplicación en el desarrollo de software. A través de este proyecto, aprenderemos a consultar una API utilizando una aplicación en Java y a serializar los datos obtenidos de la API. Esta experiencia nos permitirá comprender mejor la interacción entre aplicaciones y servicios web, así como la manipulación y procesamiento de datos en aplicaciones Java 🚑.

## ¿Pero que es una API?

Una API (Interfaz de Programación de Aplicaciones) es un conjunto de definiciones y protocolos que permite la comunicación entre diferentes sistemas de información a través del protocolo HTTP (Protocolo de Transferencia de Hipertexto). Una API puede ser una aplicación externa a nuestro sistema o una capa dentro de nuestro sistema que facilita la interacción y el intercambio de datos.

Específicamente, una API actúa como el punto de comunicación entre nuestra aplicación y otra aplicación, permitiendo enviar y recibir datos mediante solicitudes HTTP. Esto nos permite recolectar datos de otras fuentes y también integrar nuestros datos con otros sistemas, lo que mejora y enriquece nuestras aplicaciones. La utilización de APIs facilita la interoperabilidad entre diferentes aplicaciones y servicios, optimizando el desarrollo de software y ampliando las funcionalidades de nuestras aplicaciones.

En el protocolo HTTP existen diferentes tipos de operaciones, pero solo vamos a utilizar, en nuestro caso específico, la operación GET que es para recolectar o traer información a nuestro sistema. Recuerda que la idea es tener una base de cómo funciona puntualmente una API.

Por lo cual, necesitamos saber cómo funciona la ARQUITECTURA CLIENTE - SERVIDOR, en la cual fue montada.

### ¿Que es un Cliente?

Un cliente es un dispositivo o aplicación que solicita servicios o recursos de un servidor a través de una red. Es decir, un programa pide una acción en concreta a otro programa instalado en otro dispositivo, acciones/servicio como pedir una página web en específico, como consultar una base de datos, envío de un correo electrónico o un recurso como un video, una imagen, archivo hasta una información en específico, y todo lo hace por el protocolo HTTP.

Pero para entender bien, tenemos que saber y tener en cuenta la diferencia entre servicio, recurso y microservicio:

**Servicio:**

Un servicio es un proceso lógico (QUE REQUIERE DE VARIOS PASOS CONVERTIDOS EN FUNCIONES) que se encarga de cumplir con un requerimiento específico solicitado por una acción de un usuario en cierto momento de un aplicativo. Puede ser una funcionalidad como enviar un correo electrónico, procesar una transacción bancaria, o consultar datos de una base de datos, entre otros ejemplos. Esencialmente, un servicio encapsula la lógica necesaria para llevar a cabo una operación deseada dentro de un sistema informático.

**Recurso:**

Un recurso lo tenemos que ver como un elemento o dato específico que puede ser accedido o utilizado por un sistema. Por ejemplo: un archivo, una imagen, datos en una base de datos, etc. Los recursos son los componentes que los servicios manipulan o entregan según las solicitudes de los usuarios.

**Micro-Servicio:**

Un microServicio lo tenemos que ver como SERVICIO que divide su proceso lógico (PASOS SEPARADOS Y REUNIDOS EN FUNCIONES) en partes más pequeñas (TOMAR ESAS FUNCIONES Y SEPARARLAS DEL SISTEMA) y que sigan interactuando entre ellas para llevar a cabo el SERVICIO. La separación del servicio se hace por medio de una arquitectura de diferentes servidores en que cada funcionalidad va a ser independiente y autónoma del sistema, pero cada funcionalidad va a ser desarrollada, desplegada y gestionada en un SOLO SERVIDOR que interactúa con otros servidores/funcionalidades para llevar a cabo el SERVICIO. Por ejemplo, un servicio de comercio electrónico podría tener microservicios separados para la gestión de usuarios, procesamiento de pagos, gestión de inventario, etc., que juntos componen la funcionalidad completa del servicio de comercio electrónico.

En resumen, un servicio es la capacidad de realizar acciones o procesos, mientras que un recurso es el objeto sobre el cual actúan esas acciones o procesos y un microservicio divide la lógica de un servicio mediante una arquitectura de servidores que interactúan entre sí para llevar a cabo el servicio.

**Nota:** Aunque un servicio, como solicitar una página web a un servidor o consultar una base de datos, puede dividirse en microservicios, es importante evaluar si realmente vale la pena hacerlo. En una arquitectura de microservicios, debemos considerar todos los aspectos y tareas necesarios para transformar un servicio en un microservicio ya que lo coloqué de manera de ejemplo del porqué que un microServicio es un SERVICIO y cuál su relación con este.

Para entender mejor un microservicio lo miramos esencialmente como un tipo de servicio, y su relación radica en su capacidad para descomponer la lógica y las reglas de negocio en componentes más pequeños y manejables. Esto ayuda a evitar que la ejecución de una parte del aplicativo entorpezca el funcionamiento general del sistema.

En general, los microservicios se utilizan para servicios que están estrechamente relacionados con la lógica y las reglas de negocio, permitiendo así una división eficiente de su funcionamiento. Esta división asegura que cada componente pueda operar de manera independiente, mejorando la escalabilidad, la resiliencia y el mantenimiento del aplicativo durante su ejecución.



### ¿Qué es un Servidor?

Un servidor es un software o dispositivo físico que guarda y proporciona servicios (lógica), recursos y datos a través de una red, ya sea local o en internet.

# ¿Cual es el siguiente paso?

Ahora sabiendo el funcionamiento de la API OpenWeather y que es una API tenemos que saber como hacer una peticion y que hacer con la respuesta de la peticion.
Vamos a ver concepto como serializacion y desarializacion.

## ¿Como hacemos una peticion?


Dado que estamos realizando una consulta a una API 🎁, es fundamental adaptar nuestro aplicativo al funcionamiento básico de cualquier API. Esto implica crear una capa que maneje exclusivamente el protocolo HTTP, en donde podamos comunicarnos con una aplicativo externo utilizando el internet. Sin embargo, debido a la simplicidad de nuestro aplicativo de consulta, no implementaremos una capa separada y manejaremos las peticiones directamente según la lógica que conformemos.

Volviendo al contexto de HTTP (Hypertext Transfer Protocol), una petición o request es una comunicación que un cliente (como un navegador web) envía a un servidor para solicitar algún tipo de recurso o servicio. Aunque HTTP incluye varios tipos de peticiones, no profundizaremos en todos ellos. Para el funcionamiento de nuestro aplicativo, nos centraremos en el verbo GET y cómo utilizarlo en nuestra aplicación hecha en Java.

El primer paso vamos a ver cómo JAVA tiene un módulo en donde gestiona el protocolo HTTP, por lo cual, buscamos en JavaDocs versión 17, es importante la versión porque JAVA tiene diferentes documentaciones para cada una de sus versiones. En el buscador ponemos HTTP y vemos cómo la documentación nos va arrojando diferentes tipos de documentación dependiendo del funcionamiento de lo que busquemos de HTTP.

Buscamos httpRequest:

![JavaDocs httpRequest](https://github.com/jihuder/api-cieloscopio-java/blob/main/imagenes/Java-Http_request.png?raw=true)


Entonces, estamos viendo cómo Java maneja el protocolo HTTP comenzando desde la realización de una petición. No profundizaremos en los detalles de cómo se hace, sino que esto servirá como punto de referencia inicial para que podamos hacer investigación respectiva, partiendo de la comprensión de la documentación. Más adelante, desarrollaremos otro aplicativo cuya idea central será entender la documentación de los diferentes módulos y, a partir de ella, crear nuestra propia documentación por módulo mientras desarrollamos el aplicativo.

# Ahora vamos con el concepto propio de serialización y deserialización

Para entender el concepto de serialización y deserialización, primero debemos comprender qué es un JSON, ya que interviene en ambos procesos.

JSON es un formato de texto plano, pero ¿de dónde proviene y por qué se creó? Proviene de las estructuras de datos que tienen un sentido común en diferentes lenguajes de programación. Este sentido común son los objetos, presentes en lenguajes como Java, Python, JavaScript, entre otros. Los objetos comparten la misma estructura, aunque con diferentes nombres. Entonces JSON como primera medida utilizo esa estructura de objeto para standarizar la representacion de la información en los diferentes lenguajes.

¿Pero para qué quiero estandarizar la información?

Sencillo, para poder comunicarme con diferentes lenguajes de programación de una forma unificada.

Json es el puente de comunicacion entre los diferentes lenguajes y aplicativos, es la forma que utiliza el protoco HTTP para enviar información de un sistema a otro y que se entendible por todas las partes.

Entonces, como segunda medida, JSON nos sirve como un protocolo estandarizado para enviar información y que la otra parte la reciba correctamente.

Como tercera medida, JSON es un texto plano. ¿Qué significa esto? Significa que la información no está representada en memoria. En otras palabras, la información no está guardada ni asignada a ningún lugar de la memoria dinámica.

# ¿Qué es la Serialización?

La serialización es el proceso de convertir un objeto Java (una instancia de una clase) en un JSON (formato de texto plano) para transmitir datos por internet con el protocolo HTTP hacia otros lenguajes o aplicaciones. Pero vamos más allá, no es solo convertirlo en un formato de texto plano, sino que nuestro objeto antes tenía direcciones de memoria y estaba erradicado en un espacio de memoria de nuestro dispositivo. Al convertirlo en un texto plano, la información del objeto sigue siendo la misma, pero no tiene ninguna dirección asociada para la memoria, porque si tuviera una dirección asociada sería imposible que el otro dispositivo admitiera esta información cuando le llegue, ya que NO podría UBICAR las mismas direcciones de memoria. En caso de que se pudiera, se tendrían que hacer diferentes procesos al sistema operativo que solo recargarían la operación y demoraría todo el aplicativo, haciendo imposible su ejecución y muchas otras razones de contexto del porqué un JSON no tiene direcciones de memoria asociadas.
Es por esto que dicen que se puede hacer portable un programa y pasarlo a una USB a otro sistema cuando se le aplica la serialización, es decir, sin asignación de memoria interna.

**NOTA Interoperabilidad :** Al eliminar las direcciones de memoria, se facilita la interoperabilidad entre diferentes sistemas y lenguajes de programación. Por ejemplo, un JSON generado en Java puede ser leído y utilizado en JavaScript, Python, etc.


# ¿Qué es la deserialización?

La deserialización es el proceso inverso, es decir, convertir un JSON en un objeto Java. Es decir, cuando recibimos información de otro aplicativo por internet por medio del protocolo HTTP en nuestro caso.

Cuando recibimos el formato de texto, en este caso JSON, lo deserializamos, es decir, a cada dato del JSON le asignamos memoria para convertirlo en la estructura de datos deseada, en nuestro caso un objeto (la instancia de una clase).

**NOTA Asignación de memoria :** Durante la deserialización, se asigna memoria en el heap de Java para los datos que componen el objeto. Esto es necesario para reconstruir la estructura de datos en memoria.

# Gson ¿Como lo hacemos en java?

Gson es la librería en donde manipularemos los JSON, es decir, por medio de Gson podemos serializar y deserializar la información de JSON.

Antes de nada, instalamos Gson en nuestro proyecto por medio de Maven repository:
https://mvnrepository.com/

En el buscador escribimos Gson y escogemos la versión que nos ha hecho el Google:

imagen

Ahora vamos a las versiones y le damos clic en la versión que deseamos instalar.

imagen

**NOTA:** Es importante darle clic en el apartado de las versiones, ya que nos abre la interfaz o la vista en donde podemos descargar la versión que deseamos.

Ahora, para poder descargar, lo haremos en un archivo JAR. Le damos clic en JAR y nos da una descarga automática.

imagen

Ya descargado el archivo JAR, vamos a Intellij IDEA y hacemos estas acciones:


Vamos a OPCIONES DE CONFIGURACION:
   └──Project Structur:
        └──Nos sale una ventana:
                   └──Escogemos: "+"
                          └──Damos clic en:
                                  └── JARs or Directories:
                                            └── Nos bota el sistema de archivo de
                                                nuestro sistema operativo:
                                                        └──Buscamos el JAR que bajamos
                                                            de Marven Repositorio
                                                                └── Agregamos gson-2.10.1.jar

                                



                                  