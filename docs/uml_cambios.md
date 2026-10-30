# Cambios entre el UML inicial y el UML final

Antes del diseño inicial, el código que traíamos del ejercicio de clase guardaba la mano del jugador en un ArrayList y manejaba el mazo y el reparto de cartas dentro del controlador. Al rediseñar, movimos esa lógica al modelo para cumplir con MVC y con las estructuras que exige el enunciado: un arreglo de 52 posiciones para el mazo y una lista enlazada para cada mano.

Entre el UML inicial y el final, la clase Mazo no cambió. Se mantuvo el arreglo de 52 cartas con un contador que solo avanza, lo que garantiza que ninguna carta se repita durante una ronda. Carta tampoco cambió en lo esencial: durante el diseño descartamos los métodos esAs() y esFigura() porque con getRango() era suficiente.

El cambio más importante fue el manejo del saldo. En el diseño inicial, el saldo lo guardaba la clase Apuesta, que además tenía métodos para apostar y acreditar. En el diseño final, el saldo pasó a la clase BlackJack, porque es la que liquida la ronda, y Apuesta quedó solo con la cantidad apostada y la validación de la entrada.

En BlackJack, los métodos pedirCarta() y plantarse() pasaron de no devolver nada a devolver un texto con el motivo cuando la acción no está permitida, para que el controlador pueda mostrarlo. El pago quedó concentrado en un solo método, finalizarRonda(), que cambia el estado y aplica el resultado al saldo una sola vez. En Mano, la verificación de pasarse de 21 no quedó como método propio, porque se resuelve comparando el puntaje directamente en BlackJack. Los enums Estado y Resultado se mantuvieron dentro de BlackJack, porque solo los usan esa clase y el controlador.

Por último, el controlador creció respecto al diseño inicial: además de los métodos de los botones, ahora tiene actualizarVista(), que concentra en un solo lugar cómo se dibuja el estado del juego, y confirmarCierre(), que se reutiliza tanto para el botón Cerrar juego como para la X de la ventana.
