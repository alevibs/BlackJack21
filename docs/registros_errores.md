# Registros de errores y correcciones

## Error 1: Veinte y medio le ganaba a un 21

**Qué fallaba.** El jugador tenía K + 4 (Veinte y medio) y se plantaba. El computador llegaba a 21 con 7 + 5 + 9. Según la HU 8.4, el 21 le gana al Veinte y medio, así que esperábamos que ganara el computador, pero el programa le daba la victoria al jugador.

**Causa.** En `BlackJack.determinarResultado()`, la condición `else if (jugadorEspecial)` declaraba ganador al jugador con Veinte y medio sin revisar el puntaje del computador.

**Cambio realizado.** Se agregó la condición del puntaje del computador:

```java
} else if (jugadorEspecial && puntajeComputador < 21) {
```

Si el computador tiene 21, esa condición no se cumple y el resultado se decide por puntaje (21 contra 14).

**Comprobación.** Repetimos la prueba con K + 4 contra 7 + 5 + 9 y ahora gana el computador. También comprobamos que el Veinte y medio sigue ganándole a 18 (K + 4 contra 10 + 8). Corregido en el commit 7ac774f.

## Error 2: el juego se cerraba al repartir el As de corazones

**Qué fallaba.** Al salir el As de corazones, el juego mostraba un error en la consola y dejaba de funcionar, en lugar de mostrar la imagen de la carta.

**Causa.** El controlador arma el nombre de la imagen como `A_hearts.png`, pero el archivo se llamaba `A__hearts.png`, con dos guiones bajos. Al no encontrar la imagen, `crearVistaCarta()` lanzaba una excepción.

**Cambio realizado.** Se renombró el archivo a `A_hearts.png` en `resources/org/example/blackjack21/imagenes/cartas/`.

**Comprobación.** Revisamos que las 52 imágenes tuvieran el formato `rango_palo.png` y que no quedara ningún nombre con doble guion bajo. Corregido en el commit 7ac774f.
