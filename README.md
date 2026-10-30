# 21 Black Jack · Duelo contra el computador

Mini proyecto 1 — Fundamentos de Programación Orientada a Eventos (750014C)
Profesora: Paola J. Rodríguez Carrillo

Aplicación de escritorio en **Java y JavaFX** para jugar 21 Black Jack contra el computador: el jugador apuesta fichas ficticias, pide cartas o se planta, y el computador juega como banca siguiendo reglas fijas. La aplicación reconoce Blackjack, Veintiuno y Veinte y medio, y liquida cada ronda.

## Integrantes

| Nombre | Código | Correo institucional | Usuario de GitHub |
|---|---|---|---|
| Juan Ángel Mavesoy Rivadeneira | _2535293_ | _juan.mavesoy@correounivalle.edu.co_ | _mavesoy1119_ |
| Alejandra Villota | _2437438_ | _sarah.villota@correounivalle.edu.co_ | alevibs |

## Requisitos

- **JDK 17** o superior.
- **Maven** (no hace falta instalarlo: el proyecto incluye el *wrapper* `mvnw` / `mvnw.cmd`).
- JavaFX 21.0.6 (Maven lo descarga automáticamente; está declarado en `pom.xml`).
- Opcional: IntelliJ IDEA y Scene Builder para editar la vista.

## Cómo ejecutar

### Opción 1: IntelliJ IDEA
1. Abrir la carpeta del proyecto (`File → Open`) y esperar a que Maven cargue las dependencias.
2. Ejecutar la clase `org.example.blackjack21.Launcher` (botón ▶).

### Opción 2: línea de comandos
Desde la carpeta raíz del proyecto:

```bash
# Windows
mvnw.cmd clean javafx:run

# macOS / Linux
./mvnw clean javafx:run
```

## Cómo se juega

1. Ingresar una apuesta (entero, múltiplo de 10, mínimo 10 y sin superar el saldo) y presionar **Iniciar ronda**. El saldo inicial es de 1.000 fichas.
2. Con las dos cartas iniciales, elegir **Pedir carta** o **Plantarse**.
3. Al plantarse (o al llegar a 21), el computador recibe dos cartas y pide mientras tenga menos de 17.
4. Se muestra el resultado y se liquida la apuesta: victoria 2×, Blackjack 2,5×, empate devuelve la apuesta, derrota no paga.

**Ver reglas** muestra el reglamento completo sin alterar la partida. **Cerrar juego** (o la X de la ventana) pide confirmación.

## Organización (MVC)

```
src/main/java/org/example/blackjack21/
├── model/
│   ├── Carta.java        Palo, rango y valor base de una carta
│   ├── Mazo.java         Arreglo de 52 cartas, barajado (Fisher–Yates) y reparto sin repetición
│   ├── Mano.java         Lista enlazada de cartas, puntaje con ajuste de ases y jugadas especiales
│   ├── Apuesta.java      Validación y valor de la apuesta
│   └── BlackJack.java    Coordina la ronda: estados, turnos, turno del computador, resultado y pagos
├── controller/
│   └── BlackJackController.java   Atiende eventos, consulta el modelo y actualiza la vista
├── BlackJackApplication.java      Carga la vista y configura la ventana
└── Launcher.java                  Punto de entrada

src/main/resources/org/example/blackjack21/
├── juego-view.fxml       Vista (diseñada con Scene Builder)
└── imagenes/cartas/      Imágenes de las 52 cartas
```