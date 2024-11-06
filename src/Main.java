import java.util.Random;
import java.util.Scanner;

/*
Haz un programa en Java que solicite por consola al usuario dos números (filas, columnas) enteros que
representarán las filas y columnas que tendrá el terreno del juego. Si el usuario escoge un valor no válido,
le indicaremos que este valor es incorrecto y le volveremos a pedir la información.
*/

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random rand  = new Random();
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_GREEN = "\u001B[32m";

        boolean end = false;
        int height;
        int width;
        int coordX;
        int coordY;

        // Generar terreno de juego

        // Solicitar 2 números para determinar las dimensiones del terreno de juego
        System.out.println("Ancho del terreno de juego:");
        do { // Utilizamos un bucle do-while para verificar que el valor introducido es un entero mayor que 0
            if (input.hasNextInt()) { // Necesitamos verificar que el valor sea entero antes de asignarlo a la variable
                width = input.nextInt();
                if (width > 0) { // El número debe ser mayor que 0 para poder generar el array
                    input.nextLine(); // Limpiamos el búfer
                    break; // El bucle se repetirá hasta que el valor introducido sea correcto
                }
            }
            System.out.println(ANSI_RED + "Error. Introduce un número mayor a 0" + ANSI_RESET);
            input.nextLine(); // Limpiamos el búfer
        } while (true);

        System.out.println("Altura del terreno de juego:");
        do { // Utilizamos el mismo bucle que para width, para comprobar que el valor introducido por el usuario sea correcto
            if (input.hasNextInt()) {
                height = input.nextInt();
                if (height > 0) {
                    input.nextLine(); // Limpiamos el búfer
                    break;
                }
            }
            System.out.println(ANSI_RED + "Error. Introduce un número mayor a 0" + ANSI_RESET);
            input.nextLine();//Limpiamos el búfer
        } while (true);

        // Creamos un array con los valores determinados por el usuario
        int[][] terrain = new int[height][width];


        for (int row=0; row < terrain.length;++row) {
            for (int col=0; col<terrain[0].length; ++col) {
                terrain[row][col] = rand.nextInt(1, 9+1);
                System.out.print(terrain[row][col] + " ");
            }
            System.out.println();
        }

    while (!end) {
        // Menú de acciones
        int action;
        do { // Utilizamos un bucle de
            System.out.println(""" 
                            ---------
                            [2] Poner bomba
                            [1] Mostrar matriz
                            [0] Salir
                            ---------
                            """);
            if (input.hasNextInt()) {
                action = input.nextInt();
                if (action >= 0 && action <= 2) {
                    input.nextLine(); // Limpiamos el búfer
                    break;
                }
            }
            System.out.println(ANSI_RED + "Error. Introduce un número entre 2-0" + ANSI_RESET);
            input.nextLine();//Limpiamos el búfer
        } while (true);
        switch (action) {
            case 2: // Poner bomba
                // Pedimos la coordenada X
                do { // Utilizamos un bucle para comprobar el formato del valor introducido por el usuario
                    System.out.println("Coordenada X: ");
                    if (input.hasNextInt()) {
                        coordX = input.nextInt();
                        if (coordX > 1&&coordX<width) {
                            --coordX; // Le restamos uno al valor, para que corresponda con su posición en el array
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
                    System.out.print(coordX+",");
                    if (input.hasNextInt()) {
                        coordY = input.nextInt();
                        if (coordY > 1&&coordY<height) {
                            --coordY; // Le restamos uno al valor, para que corresponda con su posición en el array
                            input.nextLine(); // Limpiamos el búfer
                            break;
                        }
                    }
                    System.out.println(ANSI_RED + "Error. Introduce un número entre 1 y "+height +"."+ANSI_RESET);
                    input.nextLine();//Limpiamos el búfer
                } while (true);

                // Explosión de la bomba
                for (int row=0; row < terrain.length;++row) {
                    for (int col=0; col<terrain[0].length; ++col) {
                        if(row==coordX||col==coordY){
                            terrain[row][col]= 0;
                        }
                    }
                }

                break;
            case 1: // Mostrar matriz
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
                end = true;
                break;
            default:
                System.out.println(ANSI_RED + "Error. Acción inválida" + ANSI_RESET);
                break;
        }
    }

    }
}
