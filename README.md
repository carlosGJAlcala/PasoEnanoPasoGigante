# Paso Enano Paso Gigante

Este repositorio implementa el algoritmo Paso Enano Paso Gigante (Baby-step Giant-step), un método utilizado para resolver el problema del logaritmo discreto en grupos finitos. Es un algoritmo crucial en criptografía y teoría de números.

# Descripción

El algoritmo Paso Enano Paso Gigante es una técnica de búsqueda basada en la estrategia de dicotomía para resolver ecuaciones de la forma:



donde:

 es una base generadora de un grupo cíclico,

 es un valor conocido,

 es un número primo,

 es el logaritmo discreto que se quiere encontrar.

El método divide el espacio de búsqueda en dos partes:

Pasos gigantes: Se almacenan potencias grandes de  en una tabla hash.

Pasos enanos: Se busca en la tabla hash un valor coincidente con pequeñas potencias de  multiplicadas por .
