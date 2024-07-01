# Reglas de negocio

En este documento se plantean todos los requimientos tomandolos como reglas de negocio.

## 1 Menú de Selección de Ciudades:

. Mostrar un menú con opciones para seleccionar una ciudad base (Ciudad de México, Buenos Aires, Bogotá, Lima, Santiago) o la opción de consultar otra ciudad.
. Incluir una opción para salir del menú.


## 2 Consulta de Datos Meteorológicos:

. Permitir al usuario consultar el clima de las ciudades base.
. Permitir al usuario consultar el clima de cualquier otra ciudad especificada.
  

## 3 Datos Meteorológicos a Consultar:

. Proporcionar los siguientes datos meteorológicos para la ciudad seleccionada: 
  . Ciudad
  . Fecha
  . Horario
  . Temperatura actual
  . Condición climática
  . Temperatura mínima
  . Temperatura máxima
  . Precipitación

## 4 Interacción con el Menú:

. Volver a mostrar el menú después de cada consulta hasta que el usuario elija la opción de salir.
. Validar la entrada del usuario y mostrar un mensaje de error si la opción seleccionada no es válida.
. Mostrar un mensaje de despedida cuando el usuario decida salir del programa.


# Reglas de Negocio

### Menú de Selección de Ciudades:

  . Regla de negocio 1.1: 
    El sistema debe presentar un menú inicial con opciones numeradas para seleccionar una ciudad base, consultar otra ciudad, o salir.
  . Regla de negocio 1.2: 
    Si el usuario selecciona una opción no válida, el sistema debe mostrar un mensaje de error y volver a presentar el menú.