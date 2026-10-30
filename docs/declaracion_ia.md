# Declaración de uso de IA

Juan Ángel Mavesoy y Alejandra Villota usamos Claude (Anthropic) como asistente durante el proyecto.

**Para qué se usó.** Claude se usó para explicar conceptos que no teníamos claros (MVC, enums, constructores, `this`, `@Override`, arreglos frente a listas, el barajado de Fisher–Yates y el manejo de Git), para guiarnos en Scene Builder y en IntelliJ, para revisar el código a medida que lo escribíamos y proponer correcciones, y para hacer pruebas del juego.

**Partes apoyadas.** El código de las clases lo escribimos nosotros, con explicaciones y revisiones de Claude. Después de integrar el trabajo de los dos, Claude revisó el proyecto completo, detectó errores y propuso correcciones, que nosotros aplicamos y probamos: Veinte y medio contra 21, el nombre de la imagen del As de corazones, el tamaño de la ventana, la confirmación al cerrar con la X y los mensajes del juego. También nos apoyó en la elaboración del README, los diagramas UML, el texto de cambios entre ellos y la matriz de pruebas, a partir de nuestro código y de los resultados de las pruebas. Las pruebas del modelo se ejecutaron con un programa de prueba sobre nuestras clases, y las de la interfaz, jugando en la ventana del juego.

**Cómo verificamos.** Contrastamos las respuestas con el enunciado y las historias de usuario. Un ejemplo concreto: Claude afirmó primero que una mano de Veinte y medio debía perder contra 18. No estuvimos de acuerdo, revisamos la HU 8.4 y comprobamos que el Veinte y medio le gana a cualquier mano de 20 o menos, pero pierde contra 21. Con eso descartamos la primera indicación y corregimos el error real: el código daba la victoria al jugador aunque el computador tuviera 21. Lo comprobamos con la mano K + 4 contra 7 + 5 + 9.

**Qué aceptamos, modificamos o descartamos.** Aceptamos las explicaciones y las correcciones después de probarlas. Descartamos métodos que no aportaban (`esAs()`). También decidimos no renombrar commits ya publicados, para conservar el historial auténtico.
