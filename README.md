
# Scala Workshop - Tech Meetup 2014

## Ejercicio 1

#### Objetivo

En `src/test/resources` encontrarás un archivo de texto, `HistorySong.txt`. Este archivo contiene texto en un formato semi estructurado. Es decir, algunas líneas respetan una estructura y otras no.

El contenido es una transcripción (menos algunos datos) de un archivo originalmente generado para analizar la performance de un sistema real; un recommender para una radio online.

El objetivo es detectar si existen canciones duplicadas y si las hubiere emitir cuales son, cuantas son y cuantas veces aparece cada una. Alcanza con emitir los ids (columnas song id o seed id).

#### Implementación y Tests

En `src/test/scala/` encontrarás un archivo `DuplictesTest` con los tests vacíos para validar la implementación.
En `src/main/scala` el archivo `Duplicates` con funciones sin implementación para completar.

#### Técnicas que puedes aplicar

* String splitting y pattern matching contra arrays
* Pattern matching contra expresiones regulares.


