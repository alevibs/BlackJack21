# Matriz de pruebas

Las pruebas del modelo se ejecutaron con datos controlados (manos armadas a propósito), como sugiere la guía de historias de usuario. Las pruebas de la interfaz se hacen jugando desde la ventana.

## Pruebas del modelo

| Prueba | Datos y acción | Resultado esperado | Resultado obtenido | Estado |
|---|---|---|---|---|
| Apuestas inválidas | Intentar iniciar con: vacío, "abc", 15, 0, −10, 10,5 | Rechazo con mensaje; no se reparte ni se descuenta | Las seis se rechazaron con mensaje | Cumple |
| Saldo insuficiente | Saldo 1000, apuesta 1010 | Rechazo por superar el saldo | Rechazada | Cumple |
| Apuesta igual al saldo | Saldo 1000, apuesta 1000 | Se acepta; saldo durante la ronda = 0 | Aceptada; saldo 0 | Cumple |
| Un as | As + 9; luego se agrega 5 | 20 y luego 15 | 20 y 15 | Cumple |
| Varios ases | As + As + 9; As + As + K; As + As + K + K | 21, 12 y 22 (solo la última se pasa) | 21, 12 y 22 | Cumple |
| Blackjack | As + 10 | Se reconoce Blackjack | Reconocido | Cumple |
| Veintiuno | 7 + 7 + 7 | 21, pero no es Blackjack | 21, no Blackjack | Cumple |
| Veinte y medio | J + 4 | Se reconoce Veinte y medio | Reconocido | Cumple |
| Veinte y medio contra 20 o menos | Jugador K + 4; computador 10 + 8 | Gana el jugador | Gana el jugador | Cumple |
| Veinte y medio contra 21 | Jugador K + 4; computador 7 + 5 + 9 | Gana el computador | Gana el computador | Cumple (falló antes de la corrección) |
| Prioridad del Blackjack | Jugador 21 en tres cartas; computador As + 10 | Gana el computador | Gana el computador | Cumple |
| Derrota por superar 21 | Jugador K + 9, pide y recibe 5 | Derrota inmediata; computador sin cartas | Derrota; computador con 0 cartas | Cumple |
| Empate | Jugador 10 + 8; computador 9 + 9 | Empate | Empate | Cumple |
| Computador se pasa | Jugador 10 + K; computador 10 + 5 + 7 | Gana el jugador | Gana el jugador | Cumple |
| Pagos | Saldo 1000, apuesta 100; los cuatro resultados | Derrota 900, empate 1000, victoria 1100, Blackjack 1150 | 900, 1000, 1100 y 1150 | Cumple |
| Reglas del computador | 20.000 rondas aleatorias | Pide con menos de 17 y se planta con 17 o más | Nunca se plantó con menos de 17 (salvo rondas resueltas por Blackjack) | Cumple |
| Sin cartas repetidas | 20.000 rondas aleatorias, revisando ambas manos | Ninguna carta repetida en la ronda | 0 repetidas | Cumple |
| Acciones fuera de turno | Pedir carta y plantarse después de terminar la ronda | Rechazadas; saldo sin cambios | Rechazadas; saldo igual | Cumple |
| Otra ronda | Terminar una ronda e iniciar otra con 100 | Se descuenta del saldo actualizado; manos vacías | Saldo actualizado; computador sin cartas | Cumple |

## Pruebas de la interfaz

Pruebas jugadas desde la ventana el 29/10/2026, con el mazo mezclado normalmente.

| Prueba | Datos y acción | Resultado esperado | Resultado obtenido | Estado |
|---|---|---|---|---|
| Estado inicial | Abrir el juego | Saldo 1000, apuesta 0, manos vacías; Pedir carta y Plantarse deshabilitados; mensaje para apostar | Saldo 1000, apuesta 0, manos vacías, botones deshabilitados y mensaje "Ingresa tu apuesta para comenzar." | Cumple |
| Apuesta inválida en pantalla | Escribir 15 y presionar Iniciar ronda | Mensaje que explica el error; no aparecen cartas | Mensaje "Ingrese un valor multiplo de 10 para apostar."; sin cartas y saldo 1000 | Cumple |
| Cartas en pantalla | Apostar 100 e iniciar ronda | Dos imágenes coherentes con su valor y palo; puntaje correcto; saldo 900 | Q de corazones y 7 de tréboles, puntaje 17; saldo 900 y apuesta 100; campo e Iniciar ronda deshabilitados | Cumple |
| Ver reglas | Abrir las reglas a mitad de la ronda y cerrarlas | Manos, saldo, apuesta y botones sin cambios | Las reglas se mostraron completas; al cerrarlas, la ronda siguió igual | Cumple |
| Cerrar con la X y cancelar | X de la ventana durante la ronda → Cancelar | Pide confirmación y conserva la partida | Apareció la confirmación; al cancelar, la ronda siguió igual | Cumple |
| Botones al terminar la ronda | Plantarse con 17 | El computador juega solo; Pedir carta y Plantarse quedan deshabilitados | Computador 20 (K + 10); mensaje "Perdiste la ronda. (Tú: 17 - Computador: 20)"; botones deshabilitados | Cumple |
| Blackjack del jugador | Apostar 900; salió As + K | Turno del computador automático; pago de 2,5 veces la apuesta | Mensaje "¡Blackjack! Ganaste 1,5 veces tu apuesta."; saldo de 0 a 2250 | Cumple |
| Mano 4 + 10 | Apostar 2250; salieron 4 y 10 (suma 14) | No es Veinte y medio, porque el enunciado lo define como figura + 4 | No se mostró mensaje de Veinte y medio | Cumple |
| Mensaje de Veinte y medio | Jugar hasta obtener figura + 4 | Aparece el mensaje de Veinte y medio y se puede pedir o plantarse | Se mostró el mensaje de Veinte y medio con Pedir carta y Plantarse habilitados | Cumple |
| Sin saldo | Perder con todo el saldo apostado | Mensaje de saldo insuficiente; no se puede iniciar otra ronda; Ver reglas y Cerrar juego disponibles | Saldo 0; mensaje "... No tienes saldo suficiente para otra ronda."; Iniciar ronda deshabilitado; Ver reglas y Cerrar juego activos | Cumple |
| Cerrar con el botón y aceptar | Cerrar juego → Aceptar | La aplicación termina | La aplicación se cerró | Cumple |
