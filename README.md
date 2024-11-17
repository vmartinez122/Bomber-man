## Bomber-man
### PR01-Ejercicio 1 M03-UF1: Programación Estructurada
### Víctor Martínez
** **
**Descripción del proyecto:**

Este ejercicio, consiste en la creación de un juego tipo Bomberman. Generaremos un tablero con valores aleatorios, donde el usuario puede colocar "bombas", que destruirán todas las casillas en horizontal y vertical desde la posición de la bomba, cambiando el valor de dichas casillas a 0 y sumando todos los valores. Mostrando por pantalla el valor que ha obtenido la explosión.

Para navegar por este programa, utilizamos un menú, donde el usuario puede escojer la acción a realizar:
```
[3] Ránking
[2] Poner bomba
[1] Mostrar matriz
[0] Salir
```
[3] Muestra los valores de las explosiones realizadas en esta partida

[2] Pide al usuario las coordenadas para poner una bomba

[1] Muestra el tablero con sus valores por pantalla

[0] Cierra el programa
** **
**Variables introducidas por el usuario:**
- ***width:*** Ancho del tablero de juego. Máximo 20
- ***height:*** Alto del tablero de juego. Máximo 15
- ***action:*** Variable utilizada para navegar por el menú [3-0]
- ***coordX:*** Coordenada x de la bomba
- ***coordY:*** Coordenada y de la bomba

**Ejemplo:**
```
Ancho del terreno de juego (máx.20):
10
Altura del terreno de juego (máx.15):
5

[3] Ránking
[2] Poner bomba
[1] Mostrar matriz
[0] Salir
2

Poner bomba:
Coordenada X: 
4
Coordenada Y:
3
Valor explosión(4,3): 75

[3] Ránking
[2] Poner bomba
[1] Mostrar matriz
[0] Salir
1

Mostrar matriz:
1 5 0 5 1 7 4 3 9 1 
9 1 0 6 3 6 9 5 1 2 
6 5 0 7 9 4 3 6 2 4 
0 0 0 0 0 0 0 0 0 0 
5 3 0 9 8 2 4 8 1 4 

[3] Ránking
[2] Poner bomba
[1] Mostrar matriz
[0] Salir
3

Ránking:
#1: 75

[3] Ránking
[2] Poner bomba
[1] Mostrar matriz
[0] Salir
0

Saliendo del programa...
```
