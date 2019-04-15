package finalProject;

import javafx.scene.input.InputMethodTextRun;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Ventas {
    private static Scanner scn = new Scanner(System.in);

    public static void totalSales(ArrayList<File> Ventas, String destPath, String destFile, String usersPath, String usersFile) throws IOException {
        // Ventas totales con filtros extra.
        int totalTickets = 0;
        ArrayList<String> readDestinations = Reader.reading(destPath, destFile); // Lee el archivo de destinos.
        int[] ticketsByDest = new int[readDestinations.size()]; // Crea un arreglo de int igual al n�mero de destinos.
        ArrayList<String> readUsers; // Declara el ArrayList<String> readUsers.
        int totalTicketsOpt;
        int male = 0;
        int female = 0;
        do {
            System.out.println("Elija una opci�n:\n1. Totales por destino.\n2. Totales por g�nero.\n" +
                    "3. Regresar.");
            try { // Si el valor de entrada no es un n�mero entero, se atrapa el error.
                totalTicketsOpt = scn.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Ingres� un valor inv�lido: solo se permiten n�meros enteros. Int�ntelo de nuevo:");
                totalTicketsOpt = scn.nextInt();
            }
            for (int i = 0; i < Ventas.size(); i++) { // Lee cada archivo en la carpeta indicada.
                if (Ventas.get(i).exists()) {
                    ArrayList<String> readFile = Reader.reading(Ventas.get(i));
                    totalTickets += readFile.size(); // Se acumula la capacidad de readFile.
                    if (i == (Ventas.size() - 1)) { // Si esta es la �ltima iteraci�n del for, se imprime el n�mero de boletos vendidos.
                        System.out.println("El n�mero total de ventas es: " + totalTickets / 4);
                    }
                    switch (totalTicketsOpt) {
                        case 1:
                            ArrayList<String> readSales = Reader.reading(Ventas.get(i)); // Se lee archivo por archivo.
                            for (int j = 2; j < readSales.size(); j++) {
                                for (int k = 0; k < readDestinations.size(); k++) {
                                    if (readSales.get(j).equals(readDestinations.get(k))) {
                                        ticketsByDest[k] += 1; // Si el destino en cada archivo de ventas coincide con un destino
                                                               // existente, se incrementa su posici�n en el arreglo.
                                    }
                                }
                                j += 3; // Incrementa j para seguir buscando el destino.
                            }
                            if (i == (Ventas.size() - 1)) { // Si esta es la �ltima iteraci�n del for:
                                for (int j = 0; j < readDestinations.size(); j++) // Imprime las ventas por destino.
                                    System.out.println(readDestinations.get(j) + ": " + ticketsByDest[j] + ".");
                                System.out.println("Presione 'Enter' para continuar.");
                                System.in.read();
                            } else {
                                continue; // Si no, contin�a con el ciclo.
                            }
                            break;
                        case 2:
                            readUsers = Reader.reading(usersPath, usersFile); // Lee los usuarios.
                            readSales = Reader.reading(Ventas.get(i)); // Lee el archivo actual.
                            for (int j = 1; j < readSales.size(); j++) {
                                for (int k = 2; k < readUsers.size(); k++) {
                                    if (Integer.parseInt(readSales.get(j)) == Integer.parseInt(readUsers.get(k - 2))) {
                                        // Si coinciden las IDs:
                                        if (readUsers.get(k).equals("Femenino")) // Se eval�a "Masculino" o "Femenino".
                                            female++;
                                        else
                                            male++;
                                    }
                                    k += 5; // Incrementa para leer la siguiente ID.
                                }
                                j += 3; // Incrementa para leer la siguiente ID.
                            }
                            if (i == (Ventas.size() - 1)) { // Si es la �ltima iteraci�n del for:
                                System.out.println("N�mero de boletos comprados por hombres: " + male + "\nN�mero de boletos " +
                                        "comprados por mujeres: " + female); // Imprime los boletos vendidos a hombres
                                                                             // y a mujeres.
                            } else {
                                continue; // Si no, reinicia el ciclo.
                            }
                            break;
                        case 3:
                            break;
                        default:
                            System.out.println("Ha ingresado una opci�n inv�lida. Presione 'Enter' y vuelva a " +
                                    "intentarlo.");
                            System.in.read();
                            break;
                    }
                } else { // No se encuentra el archivo en la carpeta.
                    System.out.println("No hay registros. Presione 'Enter' para continuar.");
                    System.in.read();
                }
            }
        } while (totalTicketsOpt != 3);
    }

    public static void salesByDay(ArrayList<File> Ventas, String destPath, String destFile, String usersPath, String usersFile) throws IOException {
        // Obtiene las ventas en un d�a espec�fico.
        ArrayList<String> readDestinations = Reader.reading(destPath, destFile); // Lee el archivo de destinos.
        int[] ticketsByDest = new int[readDestinations.size()]; // Arreglo de int igual al n�mero de destinos.
        ArrayList<String> readUsers = Reader.reading(usersPath, usersFile); // Lee el archivo de usuarios.
        int totalTickOpt;
        int male;
        int female;
        do {
            System.out.println("Elija una opci�n:\n1. Por destino en un d�a.\n2. Por g�nero en un d�a.\n3. Regresar.");
            try { // Si el usuario ingresa un valor no entero, se le dice que lo vuelva a intentar.
                totalTickOpt = scn.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Ha ingresado un valor inv�lido: solo se permiten n�meros enteros. Int�ntelo de " +
                        "nuevo.");
                totalTickOpt = scn.nextInt();
            }
            switch (totalTickOpt) {
                case 1: // Por destino en un d�a.
                    ArrayList<String> readSales = new ArrayList<>(); // Declara el ArrayList<String> para las ventas.
                    String date = ToGet.individualDate(); // String para guardar la fecha. Ejecuta ToGet.individualDate().
                    for (int i = 0; i < Ventas.size(); i++) { // Compara el nombre de cada archivo con el nombre formado
                                                              // por ToGet.individualDate().
                        if ((PathStorage.getSalesFolder().toString() + File.separator + "Ventas " + date + ".txt").equals(Ventas.get(i).toString())) {
                            readSales = Reader.reading(Ventas.get(i)); // Cuando encuentra el archivo coincidente, lee el mismo.
                            for (int j = 2; j < readSales.size(); j++) {
                                for (int k = 0; k < readDestinations.size(); k++) {
                                    if (readSales.get(j).equals(readDestinations.get(k))) { // Compara los destinos.
                                        ticketsByDest[k] += 1; // Se incrementa la misma posici�n del arreglo.
                                    }
                                }
                                j += 3; // Incrementa j para seguir comparando los destinos.
                            }
                        }
                    }

// Autor: Sa�l Alberto Ramos Labor�n.
// Expediente: 217200160.
// Fecha: 17-05-2018.

                    System.out.println("El n�mero total de boletos vendidos en la fecha " + date + " es: " + readSales.size() / 4);
                    // Imprime el n�mero total de boletos en la fecha espec�fica.
                    for (int i = 0; i < readDestinations.size(); i++) { // Imprime cada destino y el n�mero de viajes por cada uno.
                        System.out.println(readDestinations.get(i) + ": " + ticketsByDest[i]);
                    }
                    System.out.println("Presione 'Enter' para continuar.");
                    System.in.read();
                    break;
                case 2:
                    date = ToGet.individualDate(); // Pide al usuario la fecha requerida.
                    ticketsByDest = new int[readDestinations.size()]; // Se vuelve a declarar el arreglo para eliminar los valores.
                    female = 0;
                    male = 0;
                    for (int i = 0; i < Ventas.size(); i++) {
                        if ((PathStorage.getSalesFolder().toString() + File.separator + "Ventas " + date + ".txt").equals(Ventas.get(i).toString())) {
                            // Se compara cada archivo en la carpeta con el nombre formado con la fecha.
                            readSales = Reader.reading(Ventas.get(i)); // Lee el archivo coincidente.
                            for (int j = 1; j < readSales.size(); j++) {
                                for (int k = 2; k < readUsers.size(); k++) {
                                    if (Integer.parseInt(readSales.get(j)) == Integer.parseInt(readUsers.get(k - 2))) {
                                        // Compara las IDs. Si son iguales:
                                        if (readUsers.get(k).equals("Femenino")) { // Si el g�nero es "Femenino":
                                            female += 1;
                                        } else { // Si no, asume:
                                            male += 1;
                                        }
                                    }
                                    k += 5; // Incrementa para seguir comparando las IDs.
                                }
                                j += 3; // Incrementa para seguir comparado las IDs.
                            }
                        }
                    }
                    System.out.println("N�mero de boletos comprados por hombres: " + male + ".\n" +
                            "N�mero de boletos comprados por mujeres: " + female + ".");
                    // Imprime el n�mero de boletos comprados por hombres y mujeres.
                    System.out.println("Presione 'Enter' para continuar.");
                    System.in.read();
                    break;
                case 3:
                    break;
                default: // Si no coincide ning�n caso:
                    System.out.println("Ingres� una opci�n inv�lida. Presione 'Enter' e int�ntelo de nuevo.");
                    System.in.read();
                    break;
            }
        } while (totalTickOpt != 3);
    }

    public static void salesByRange(ArrayList<File> Ventas, String destPath, String destFile, String usersPath, String usersFile) throws IOException {
        // Ventas en un rango de fechas.
        int totalTickets = 0;
        ArrayList<String> readDestinations = Reader.reading(destPath, destFile); // Lee los destinos.
        int[] ticketsByDest = new int[readDestinations.size()]; // Arreglo igual a la capacidad de readDestinations.
        ArrayList<String> readUsers = Reader.reading(usersPath, usersFile); // Lee los usuarios.
        int ticketsOpt;
        int male = 0;
        int female = 0;
        int init = 0;
        int last = 0;
        do {
            System.out.println("Elija una opci�n:\n1. Por destino en un rango de fechas.\n2. Por g�nero en un rango de fechas.\n" +
                    "3. Regresar.");
            try {
                ticketsOpt = scn.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Has ingresado un valor inv�lido: solo se admiten n�meros enteros. Int�ntelo de nuevo.");
                ticketsOpt = scn.nextInt();
            }
            switch (ticketsOpt) {
                case 1:
                    System.out.println("Fecha inicial: ");
                    String initDate = ToGet.individualDate(); // Pide la fecha inicio.
                    System.out.println("Fecha final: ");
                    String lastDate = ToGet.individualDate(); // Pide la fecha inicial.
                    ArrayList<String> readSales; // Declara el ArrayList<String> para las ventas.
                    for (int i = 0; i < Ventas.size(); i++) {
                        for (int j = 0; j < Ventas.size(); j++) { // Busca el archivo inicial y el final.
                            if ((PathStorage.getSalesFolder().toString() + File.separator + "Ventas " + initDate + ".txt").equals(Ventas.get(j).toString())) {
                                init = j;
                            } else if ((PathStorage.getSalesFolder().toString() + File.separator + "Ventas " + lastDate + ".txt").equals(Ventas.get(j).toString())) {
                                last = j;
                            }
                        }
                        for (int j = init; j < last; j++) { // Lee cada archivo entre el inicial y el final.
                            readSales = Reader.reading(Ventas.get(j));
                            totalTickets += readSales.size() / 4; // Acumula el n�mero de ventas.
                            if (j == (last - 1)) { // Si es la �ltima iteraci�n, se imprime el n�mero de ventas.
                                System.out.println("El n�mero total de boletos vendidos en el rango de fechas es: " + totalTickets);
                            }
                            for (int k = 2; k < readSales.size(); k++) { // Si coinciden los destinos:
                                for (int l = 0; l < readDestinations.size(); l++) {
                                    if (readSales.get(k).equalsIgnoreCase(readDestinations.get(l))) {
                                        ticketsByDest[l] += 1; // Se incrementa el valor de su posici�n en el arreglo.
                                    }
                                }
                                k += 3; // Incrementa k para seguir comparando los destinos.
                            }
                        }
                    }
                    for (int k = 0; k < readDestinations.size(); k++) { // Imprime cada destino y su n�mero de ventas.
                        System.out.println(readDestinations.get(k) + ": " + ticketsByDest[k] + ".");
                    }
                    System.out.println("Presiona 'Enter' para continuar.");
                    System.in.read();
                    break;
                case 2:
                    System.out.println("Fecha inicial: ");
                    initDate = ToGet.individualDate(); // Recibe la fecha inicial.
                    System.out.println("Fecha final: ");
                    lastDate = ToGet.individualDate(); // Reciba la fecha final.
                    totalTickets = 0;
                    for (int i = 0; i < Ventas.size(); i++) {
                        for (int j = 0; j < Ventas.size(); j++) { // Compara cada archivo hasta coincidir con el inicial y el final.
                            if ((PathStorage.getSalesFolder().toString() + File.separator + "Ventas " + initDate + ".txt").equals(Ventas.get(j).toString())) {
                                init = j;
                            } else if ((PathStorage.getSalesFolder().toString() + File.separator + "Ventas " + lastDate + ".txt").equals(Ventas.get(j).toString())) {
                                last = j;
                            }
                        }
                        for (int j = init; j < last; j++) {
                            readSales = Reader.reading(Ventas.get(i)); // Lee el archivo actual.
                            totalTickets += readSales.size() / 4; // Acumula las ventas.
                            for (int k = 1; k < readSales.size(); k++) {
                                for (int l = 2; l < readUsers.size(); l++) {
                                    // Compara las IDs.
                                    if (Integer.parseInt(readSales.get(k)) == Integer.parseInt(readUsers.get(l - 2))) {
                                        if (readUsers.get(l).equals("Femenino")) { // Si coincide con "Femenino":
                                            female += 1;
                                        } else { // Si no:
                                            male += 1;
                                        }
                                    }
                                    l += 5; // Incrementa para seguir comparando las IDs.
                                }
                                k += 3; // Incrementa para seguir comparando las IDs.
                            }
                        }
                    }
                    // Imprime los resultados.
                    System.out.println("El n�mero total de boletos comprados en el rango de fechas es: " + totalTickets + ".");
                    System.out.println("El n�mero de boletos comprados por hombres es: " + male + ".\n" +
                            "El n�mero de boletos comprados por mujeres es: " + female + ".");
                    System.out.println("Presione 'Enter' para continuar.");
                    System.in.read();
                    break;
                case 3:
                    break;
                default: // Si no coincide ning�n caso:
                    System.out.println("Ingres� una opci�n inv�lida. Presione 'Enter' e int�ntelo de nuevo.");
                    System.in.read();
                    break;
            }
        } while (ticketsOpt != 3);
    }

    public static void salesByAge(ArrayList<File> Ventas, String usersPath, String usersFile) throws IOException {
        // Ventas por rango de edad.
        int totalTickets = 0;
        ArrayList<String> readUsers = Reader.reading(usersPath, usersFile); // Lee los usuarios.
        int tickOpt = 0;
        do {
            int[] ticketsByAge = new int[4]; // Declara un arreglo de cuatro espacios.
            System.out.println("Elija una opci�n:\n1. 14-19 a�os.\n2. 20-35 a�os.\n3. 36-59 a�os.\n4. 60 a�os o m�s.\n" +
                    "5. Regresar.");
            try {
                tickOpt = scn.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Ha ingresado una opci�n inv�lida: solo se admiten n�meros enteos. Int�ntelo de " +
                        "nuevo.");
                tickOpt = scn.nextInt();
            }
            for (int i = 0; i < Ventas.size(); i++) {
                ArrayList<String> readSales = Reader.reading(Ventas.get(i));
                totalTickets += readSales.size() / 4;
                switch (tickOpt) {
                    case 1:
                        for (int j = 1; j < readSales.size(); j++) {
                            for (int k = 0; k < readUsers.size(); k++) { // Compara las IDs.
                                if(Integer.parseInt(readSales.get(j)) == Integer.parseInt(readUsers.get(k))){
                                    if(Integer.parseInt(readUsers.get(k + 3)) >= 14 && Integer.parseInt(readUsers.get(k + 3)) <= 19){
                                        ticketsByAge[tickOpt - 1]++; // Compara las edades con los l�mites del rango.
                                    }
                                }
                                k += 5; // Aumenta en 5 para comparar las IDs.
                            }
                            j += 3; // Aumenta en 3 para comparar las IDs.
                        }
                        //System.out.println("El n�mero de boletos comprados por usuarios entre 14 y 19 a�os es: " + ticketsByAge);
                        break;
                    case 2:
                        for (int j = 1; j < readSales.size(); j++) {
                            for (int k = 0; k < readUsers.size(); k++) { // Compara las IDs.
                                if(Integer.parseInt(readSales.get(j)) == Integer.parseInt(readUsers.get(k))){
                                    if(Integer.parseInt(readUsers.get(k + 3)) >= 20 && Integer.parseInt(readUsers.get(k + 3)) <= 35){
                                        ticketsByAge[tickOpt - 1]++; // Compara las edades con los l�mites del rango.
                                    }
                                }
                                k += 5; // Aumenta en 5 para comparar las IDs.
                            }
                            j += 3; // Aumenta en 3 para comparar las IDs.
                        }
                        //System.out.println("El n�mero de boletos comprados por usuarios entre 20 y 34 a�os es: " + ticketsByAge);
                        break;
                    case 3:
                        for (int j = 1; j < readSales.size(); j++) {
                            for (int k = 0; k < readUsers.size(); k++) { // Compara las IDs.
                                if(Integer.parseInt(readSales.get(j)) == Integer.parseInt(readUsers.get(k))){
                                    if(Integer.parseInt(readUsers.get(k + 3)) >= 36 && Integer.parseInt(readUsers.get(k + 3)) <= 59){
                                        ticketsByAge[tickOpt - 1]++; // Compara las edades con los l�mites del rango.
                                    }
                                }
                                k += 5; // Aumenta en 5 para comparar las IDs.
                            }
                            j += 3; // Aumenta en 3 para comparar las IDs.
                        }
                        //System.out.println("El n�mero de boletos comprados por usuarios entre 35 y 59 a�os es: " + ticketsByAge);
                        break;
                    case 4:
                        for (int j = 1; j < readSales.size(); j++) {
                            for (int k = 0; k < readUsers.size(); k++) { // Compara las IDs.
                                if(Integer.parseInt(readSales.get(j)) == Integer.parseInt(readUsers.get(k))){
                                    if(Integer.parseInt(readUsers.get(k + 3)) >= 60){
                                        ticketsByAge[tickOpt - 1]++; // Compara las edades con los l�mites del rango.
                                    }
                                }
                                k += 5; // Aumenta en 5 para comparar las IDs.
                            }
                            j += 3; // Aumenta en 3 para comparar las IDs.
                        }
                        //System.out.println("El n�mero de boletos comprados por usuarios mayores a 60 a�os es: " + ticketsByAge);
                        break;
                    case 5:
                        break;
                    default: // Se utiliza m�s tarde.
                        break;
                }
            }
            switch(tickOpt){ // Se ingresa el mismo par�metro que en el anterior switch.
                case 1: // 14-19.
                    System.out.println("El n�mero de boletos comprados por usuarios entre 14 y 19 a�os es: " + ticketsByAge[tickOpt - 1]);
                    break;
                case 2: // 20-35.
                    System.out.println("El n�mero de boletos comprados por usuarios entre 20 y 35 a�os es: " + ticketsByAge[tickOpt - 1]);
                    break;
                case 3: // 36-59.
                    System.out.println("El n�mero de boletos comprados por usuarios entre 36 y 59 a�os es: " + ticketsByAge[tickOpt - 1]);
                    break;
                case 4: // 60+.
                    System.out.println("El n�mero de boletos comprados por usuarios mayores a 60 a�os es: " + ticketsByAge[tickOpt - 1]);
                    break;
                case 5:
                    break;
                default: // Si no coincide con ning�n caso:
                    System.out.println("Ingres� una opci�n inv�lida. Presione 'Enter' para continuar.");
                    System.in.read();
                    break;
            }
        } while(tickOpt != 5);
    }

    public static void maxMinAvByRange(ArrayList<File> Ventas) throws IOException {
        // M�nimo, m�ximo y promedio de ventas en rango de fechas.
        System.out.println("Fecha inicial: ");
        String initDate = ToGet.individualDate(); // Se pide la fecha inicial.
        System.out.println("Fecha final: ");
        String lastDate = ToGet.individualDate(); // Se pide la fecha final.
        int init = 0;
        int last = 0;
        int max = 0;
        int min = 9000;
        int maxstor = -1;
        int minstor = -1;
        int[] average = new int[Ventas.size()]; // Se declara un arreglo del tama�o de Ventas.
        int avg = 0;
        ArrayList<String> readSales = new ArrayList<>(); // Se declara el ArrayList<String> en el que se leer�n las ventas.
        for (int i = 0; i < Ventas.size(); i++) {
            for (int j = 0; j < Ventas.size(); j++) { // Compara los archivos existentes con los de la fecha inicial y final.
                if ((PathStorage.getSalesFolder().toString() + File.separator + "Ventas " + initDate + ".txt").equals(Ventas.get(j).toString())) {
                    init = j;
                } else if ((PathStorage.getSalesFolder().toString() + File.separator + "Ventas " + lastDate + ".txt").equals(Ventas.get(j).toString())) {
                    last = j;
                }
            }
            for (int j = init; j < last; j++) {
                readSales = Reader.reading(Ventas.get(i)); // Lee el archivo actual.
                if((readSales.size() / 4) > max){ // Si el n�mero de ventas es mayor que el m�ximo:
                    max = readSales.size() / 4;
                    maxstor = j;
                }
                if((readSales.size() / 4) < min){ // Si el n�mero de ventas es menor que el m�nimo:
                    min = readSales.size() / 4;
                    minstor = j;
                }
                average[j] = readSales.size() / 4; // Se almacena el n�mero de ventas en el arrglo.
            }
            for (int j = 0; j < average.length; j++) { // Se le suma a avg cada valor del arreglo.
                avg += average[j];
            }
        }
        avg /= average.length; // Se divide para promediar.
        System.out.println("La venta m�nima ha sido de " + min + " boletos.");
        System.out.println("La venta m�xima ha sido de " + max + " boletos.");
        System.out.println("El promedio de ventas por d�a es " + avg + " boletos.");
        System.out.println("Presione 'Enter' para continuar.");
        System.in.read();
    }
}
