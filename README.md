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




