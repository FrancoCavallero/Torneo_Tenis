Crear un programa que simule un partido de tenis, el mismo debe solicitar al
usuario ingresar los datos del partido (jugadores, nombre del torneo, etc) y con
estos debe ejecutar punto por punto el transcurso del partido hasta llegar a su fin.
El programa deberá tener en cuenta la puntuación y reglas
utilizadas en este deporte.
Funcionalidades
Antes de comenzar la simulación, el usuario podrá ingresar los
siguientes datos:
● Nombres de los jugadores.
● Nombre del torneo.
● Definición de la cantidad de sets: al mejor de 3 o al mejor de 5.
● Probabilidad de que cada jugador gane el partido (tiene que ir del 1 al 100%
e influir en el resultado final).
El programa deberá simular punto por punto de forma automática, donde el
ganador de cada uno será determinado de forma aleatoria (teniendo en cuenta la
probabilidad definida al principio), y los mismos se tienen que visualizar por
pantalla. Datos a mostrar:
• Ganador del punto.
• Resultado parcial del game.
• Al comenzar un game, mostrar el jugador que tiene el saque.
• Al finalizar cada SET, se mostrará el ganador y el resultado parcial del partido.
Una vez finalizado el partido:
• Imprimir el resultado en formato de partido de tenis.
• Imprimir el nombre del ganador y del torneo.
• Preguntar al usuario si quiere jugar la revancha, en caso de que la respuesta
sea sí, simular nuevamente.
Test unitarios
Será necesario contar con al menos tres test unitarios que verifiquen el correcto
funcionamiento del programa. Los test se deberán realizar utilizando Junit.
A considerar
● No se evaluará de forma estricta la interfaz, por lo que puede hacerse por
consola.
● No es necesario que haya persistencia.
● El programa se tiene que hacer con Java 8.
● El programa (segunda parte de la prueba) no debe incluir conexión con base
de datos.
● El programa tiene que compilar y poder ejecutarse sin problemas.
