import java.util.Scanner;

/*
Haz un programa en Java que solicite por consola al usuario dos números (filas, columnas) enteros que
representarán las filas y columnas que tendrá el terreno del juego. Si el usuario escoge un valor no válido,
le indicaremos que este valor es incorrecto y le volveremos a pedir la información.
*/

public class Main {
    public static void main(String[] args) {
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_RESET = "\u001B[0m";
        Scanner input = new Scanner(System.in);

        // Generar terreno de juego
        int height;
        int width;
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

        // Usamos dos bucles foreach para imprimir el array por pantalla
        for (int[] row : terrain) { // El primer bucle, almacena una fila del array2D en un array int por cada iteración
            for (int elem : row) { // El segundo bucle, itera sobre cada número del array de la fila
                System.out.print(elem + " "); // Imprime cada número por pantalla, separado por espacios
            }
            System.out.println(); // Realiza un salto de línea para imprimir la siguiente fila en el array
        }
    }
}
