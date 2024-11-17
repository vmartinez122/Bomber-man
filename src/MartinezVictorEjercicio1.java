import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MartinezVictorEjercicio1 {
    public static void main(String[] args) {
        // Inicialización de clases
        Scanner input = new Scanner(System.in);
        Random rand  = new Random();
        // Variables de formato de texto
        final String ANSI_RED = "\u001B[31m"; // Color rojo
        final String ANSI_GREEN = "\u001B[32m"; // Color verde
        final String ANSI_YELLOW = "\u001B[33m"; // Color amarillo
        final String ANSI_RESET = "\u001B[0m";// Devolver color predeterminado

        // Declaración de variables
        final int MIN_VALUE = 1; // Valor mínimo de las casillas
        final int MAX_VALUE = 9; // Valor máximo de las casillas
        int[][] terrain; //Array mapa de juego
        boolean end = false; // Condición de salida de bucle de juego
        int width; // Ancho terreno
        int height; // Altura terreno
        // He considerado que el terreno donde jugaremos la partida ha de tener ciertas limitaciones en caso de que el jugador quiera acabar la partida
        // si tenemos un terreno de juego demasiado grande, va a ser difícil de representar por consola y para el jugador de calcular las coordenadas
        // las dimensiones 20x15, crean un cuadrado de tamaño considerable por consola, que necesitará un mínimo de 15 bombas para destruir por completo
        final int MAX_WIDTH = 20; // Ancho máximo terreno
        final int MAX_HEIGHT = 15; // Altura máxima terreno
        int coordX; // Posición x de la bomba
        int coordY; // Posición y de la bomba
        int action; // Int que utilizaremos para navegar el menú
        int score; // Puntuación de las explosiones
        ArrayList<Integer> Ranking = new ArrayList<>(); // ArrayList para sistema de explosiones


        // Generar terreno de juego

        // Solicitar 2 números para determinar las dimensiones del terreno de juego
        System.out.println("Ancho del terreno de juego (máx."+MAX_WIDTH+"):");
        do { // Utilizamos un bucle do-while para verificar que el valor introducido es un entero mayor que 0
            if (input.hasNextInt()) { // Necesitamos verificar que el valor sea entero antes de asignarlo a la variable
                width = input.nextInt();
                if (width > 0&&width<=MAX_WIDTH) { // El número debe ser mayor que 0 para poder generar el array
                    input.nextLine(); // Limpiamos el búfer
                    break; // El bucle se repetirá hasta que el valor introducido sea correcto
                }
            }
            System.out.println(ANSI_RED + "Error. Introduce un número mayor a 0, tamaño máximo: "+MAX_WIDTH+ANSI_RESET); // Mensaje de error
            input.nextLine(); // Limpiamos el búfer
        } while (true);

        System.out.println("Altura del terreno de juego (máx."+MAX_HEIGHT+"):");
        do { // Utilizamos el mismo bucle que para width, para comprobar que el valor introducido por el usuario sea correcto
            if (input.hasNextInt()) {
                height = input.nextInt();
                if (height > 0&&height<=MAX_HEIGHT) {
                    input.nextLine(); // Limpiamos el búfer
                    break;
                }
            }
            System.out.println(ANSI_RED + "Error. Introduce un número mayor a 0, tamaño máximo: "+MAX_HEIGHT+ANSI_RESET);
            input.nextLine();//Limpiamos el búfer
        } while (true);

        // Creamos un array con las dimensiones determinadas por el usuario
        terrain = new int[height][width];
        // Usamos dos bucles for para generar los valores dentro del array2D
        for (int row=0; row < terrain.length;++row) {
            for (int col=0; col<terrain[0].length; ++col) {
                // La clase random, contiene una función la cual devuelve un entero aleatorio entre un valor mínimo (incluido) y un valor máximo (no incluido)
                terrain[row][col] = rand.nextInt(MIN_VALUE, MAX_VALUE+1);
            }
        }

        while (!end) { // Bucle principal del juego, finalizará cuando end tenga valor true
            // Menú de acciones
            do { // Utilizamos un bucle para comprobar el formato del valor introducido por el usuario, si es inválido, mostramos el menú
                System.out.println(""" 
                                
                                [3] Ránking
                                [2] Poner bomba
                                [1] Mostrar matriz
                                [0] Salir""");
                if (input.hasNextInt()) {
                    action = input.nextInt();
                    if (action >= 0 && action <= 3) { // Si el valor está dentro del rango del menú
                        input.nextLine(); // Limpiamos el búfer
                        System.out.println(); // Espacio para separar las líneas
                        break;
                    }
                }
                System.out.println(ANSI_RED + "Error. Introduce una opción del menú [3 - 0]" + ANSI_RESET);
                input.nextLine();//Limpiamos el búfer
            } while (true);
            switch (action) { //Usamos la variable action, que hemos pedido al usuario para navegar por un condicional switch
                case 3: //Ránking, EXTRA
                    if (Ranking.isEmpty()){ // Validamos que haya algún valor a mostrar
                        System.out.println("No ha habido ninguna explosión.");
                    }else {
                        System.out.println(ANSI_YELLOW+"Ránking:"+ANSI_RESET);
                        //Usamos un bucle for para mostrar las puntuaciones almacenadas en el ArrayList
                        for (int num = 0; num < Ranking.size(); ++num) {
                            System.out.println("#"+(num+1) + ": " + Ranking.get(num));
                        }
                    }
                    break;
                case 2: // Poner bomba
                    // Pedimos la coordenada X
                    System.out.println(ANSI_YELLOW+"Poner bomba:"+ANSI_RESET);
                    do { // Utilizamos un bucle para comprobar el formato del valor introducido por el usuario
                        System.out.println("Coordenada X: ");
                        if (input.hasNextInt()) {
                            coordX = input.nextInt();
                            //Las coordenadas que introduce el usuario, no corresponden a la misma posición en el array
                            //hemos de tener en cuenta que la primera posición, será (1,1) para el usuario, lo que corresponde a la posición [0][0] en el array
                            if (coordX >= 1&&coordX<=width) {
                                --coordX; // Le restamos 1 al valor, para que corresponda con su posición en el array
                                input.nextLine(); // Limpiamos el búfer
                                break;
                            }
                        }
                        System.out.println(ANSI_RED + "Error. Introduce un número entre 1 y "+width +"."+ANSI_RESET);
                        input.nextLine();//Limpiamos el búfer
                    } while (true);

                    // Pedimos la coordenada Y
                    do { // Utilizamos un bucle para comprobar el formato del valor introducido por el usuario
                        System.out.println("Coordenada Y:");
                        if (input.hasNextInt()) {
                            coordY = input.nextInt();
                            if (coordY >= 1&&coordY<=height) {
                                --coordY; // Le restamos 1 al valor, para que corresponda con su posición en el array
                                input.nextLine(); // Limpiamos el búfer
                                break;
                            }
                        }
                        System.out.println(ANSI_RED + "Error. Introduce un número entre 1 y "+height +"."+ANSI_RESET);
                        input.nextLine();//Limpiamos el búfer
                    } while (true);

                    // Explosión de la bomba
                    score = 0; // Reiniciamos el valor de la puntuación cada vez que ponemos una nueva bomba
                    end = true; // Asignamos el valor true a la condición de salida del bucle, si no se actualiza, el programa acabará después de este bucle
                    //Utilzaremos un bucle para recorrer el array
                    for (int row=0; row < terrain.length;++row) { // Usamos dos bucles for para recorrer el array 2D
                        for (int col=0; col<terrain[0].length; ++col) {
                            // Si el valor de la columna o la fila es igual a su coordenada correspondiente, cambiaremos ese valor a 0
                            if(col==coordX||row==coordY){
                                score += terrain[row][col]; //Sumamos el valor de la posición a la puntuación final
                                terrain[row][col]= 0;
                            }
                            if(terrain[row][col]!=0){ //Si se encuentra un número con valor distinto a 0 en el array, aún quedan celdas por destruir, por lo tanto el programa continúa
                                end = false;
                            }
                        }
                    }
                    Ranking.add(score); // Añadimos la puntuación al ránking
                    System.out.println(ANSI_YELLOW+"Valor explosión("+(coordX+1)+","+(coordY+1)+"): "+score+ANSI_RESET); //Imprime valor de la explosión (En color amarillo)

                    if (end){
                        System.out.println(ANSI_YELLOW+"Todas las celdas han sido destruidas. Puntuación final:"+ANSI_RESET); //Notificación final de juego
                        // Utilizamos el bucle para mostrar las puntuaciones finales antes que acabe el programa,
                        // he considerado que el usuario podría querer saber su puntuación final al poner la última bomba
                        if (Ranking.isEmpty()){ // Comprobemos que haya valores para evitar errores
                            System.out.println("No ha habido ninguna explosión.");
                        }else {
                            //Usamos un bucle for para mostrar las puntuaciones almacenadas en el ArrayList
                            for (int num = 0; num < Ranking.size(); ++num) {
                                System.out.println("#"+(num+1) + ": " + Ranking.get(num));
                            }
                        }
                    }
                    break;
                case 1: // Mostrar matriz
                    System.out.println(ANSI_YELLOW+"Mostrar matriz:"+ANSI_RESET);
                    // Usamos dos bucles foreach para imprimir los valores  del array por pantalla
                    for (int[] row : terrain) {  // El primer bucle, almacena una fila del array2D en un array int por cada iteración
                        for (int elem : row) { // El segundo bucle, itera sobre cada número del array de la fila
                            if (elem==0){ // Imprime cada número por pantalla, separado por espacios, de color rojo si es un 0
                                System.out.print(ANSI_RED+elem + " ");
                            }else{
                                System.out.print(ANSI_GREEN+elem + " ");
                            }
                        }
                        System.out.println(ANSI_RESET); // Realiza un salto de línea para imprimir la siguiente fila en el array, devuelve el color original al tetxo de la consola
                    }
                    break;
                case 0: // Salir del programa
                    System.out.println("Saliendo del programa...");
                    end = true; // Actualiza la varible que interrumpe el bucle principal
                    break;
                default:
                    System.out.println(ANSI_RED + "Error. Acción inválida" + ANSI_RESET);
                    break;
            }
        }
    }
}
