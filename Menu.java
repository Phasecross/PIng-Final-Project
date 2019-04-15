// Autor: Sa�l Alberto Ramos Labor�n.
// Expediente: 217200160.
// Fecha: 17-05-2018.

package finalProject;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;

public class Menu {
    private static Scanner scn = new Scanner(System.in);
    private static int ID = 1;
    private static int readID = 0;
    private static int unit = 1;
    private static int readUnit = 0;
    private static int tripID = 1;
    private static int readTripID = 0;
    private static int ticket = 1;
    private static int readTicket = 0;
    private static boolean control = true;
    private static File Users = new File(PathStorage.getUsersPath() + PathStorage.getUsersFile());
    // Archivo de los usuarios.
    private static File Autobuses = new File(PathStorage.getBusPath() + PathStorage.getBusFile());
    // Archivo de los autobuses.
    private static File Destinations = new File(PathStorage.getDestPath() + PathStorage.getDestFile());
    // Archivo de los destinos.
    private static File Trips = new File(PathStorage.getTripsPath() + PathStorage.getTripsFile());
    // Archivo de los viajes hechos.
    private static File Sales = new File(PathStorage.getSalesPath() + PathStorage.getSalesFile());
    // Archivo de ventas.
    private static File Central = new File(PathStorage.getCentralPath() + PathStorage.getCentralFile());
    // Archivo de la central.

    public static void main(String[] args) throws IOException {
        // Creaci�n de los directorios.
        TheCreator.toMakeDir(PathStorage.getCurrentUsersHomeDir(), PathStorage.getFolderName());
        TheCreator.toMakeDir(PathStorage.getFullDirectory(), "Usuarios\\");
        TheCreator.toMakeDir(PathStorage.getFullDirectory(), "Autobuses\\");
        TheCreator.toMakeDir(PathStorage.getFullDirectory(), "Destinos\\");
        TheCreator.toMakeDir(PathStorage.getFullDirectory(), "Viajes\\");
        TheCreator.toMakeDir(PathStorage.getFullDirectory(), "Ventas\\");
        TheCreator.toMakeDir(PathStorage.getFullDirectory(), "Central\\");
        int option = -2;
        do {
            // Primero se eval�a si el archivo de la central existe. Si no, te obliga a crearlo.
            try {
                if (option == -1)
                    scn.nextLine();
                option = -1;
                if (Central.exists()) {
                    System.out.println("Elige una opci�n:\n1. Usuarios.\n2. Viajes.\n3. Autobuses.\n4. Destinos.\n" +
                            "5. Control de ventas.\n6. Central.\n7. Acerca de.\n0. Salir.");
                    option = scn.nextInt();
                } else {
                    option = 6;
                }
                // Men� principal.
                switch (option) {
                    case 1:
                        String opt;
                        do {
                            System.out.println("Elige una opci�n:\na. Agregar usuarios.\nb. Modificar usuario.\n" +
                                    "c. Consultar informaci�n de un usuario.\nd. Eliminar usuario.\ne. Regresar.");
                            opt = scn.next();
                            switch (opt.toLowerCase()) {
                                case "a":
                                    // Agregar usuarios. Primero consigue la ID mayor actual, la incrementa y la asigna al usuario.
                                    ID = ToGet.ID(PathStorage.getUsersPath(), PathStorage.getUsersFile(), readID, ID, Users);
                                    Usuarios.toAdd(ID, Users);
                                    break;
                                case "b":
                                    // Llama al m�todo para modificar usuarios.
                                    ArrayList<String> readFile = Reader.reading(PathStorage.getUsersPath(), PathStorage.getUsersFile());
                                    Usuarios.toModify(readFile, Users);
                                    break;
                                case "c":
                                    // Llamada al m�todo para consultar informaci�n.
                                    readFile = Reader.reading(PathStorage.getUsersPath(), PathStorage.getUsersFile());
                                    Usuarios.toConsult(readFile);
                                    break;
                                case "d":
                                    // Elimina a un usuario con su ID por medio del m�todo toDelete.
                                    readFile = Reader.reading(PathStorage.getUsersPath(), PathStorage.getUsersFile());
                                    Usuarios.toDelete(readFile, Users);
                                    break;
                                case "e":
                                    control = false;
                                    break;
                                default:
                                    System.out.println("Ha ingresado una opci�n inv�lida. Presione 'Enter'" +
                                            " para continuar y vuelva a intentarlo.");
                                    System.in.read();
                                    break;
                            }
                        } while (control);
                        control = true;
                        break;
                    case 2:
                        String tripOpt;
                        do {
                            System.out.println("Elija una opci�n:\na. Agregar reservaci�n.\nb. Eliminar reservaci�n.\nc. Regresar.");
                            tripOpt = scn.next();
                            switch (tripOpt.toLowerCase()) {
                                case "a":
                                    // Agregar una reservaci�n.
                                    tripID = ToGet.tripID(PathStorage.getTripsPath(), PathStorage.getTripsFile(), readTripID, tripID, Trips);
                                    Viajes.toAdd(PathStorage.getUsersPath(), PathStorage.getUsersFile(), PathStorage.getDestPath(), PathStorage.getDestFile(), PathStorage.getBusPath(), PathStorage.getBusFile(), tripID, ticket, Sales, Users, Trips, Destinations, Autobuses);
                                    break;
                                case "b":
                                    // Eliminar una reservaci�n.
                                    ArrayList<String> readFile = Reader.reading(PathStorage.getUsersPath(), PathStorage.getUsersFile());
                                    Viajes.toDelete(readFile, PathStorage.getTripsPath(), PathStorage.getTripsFile(), Trips);
                                    break;
                                case "c":
                                    control = false;
                                    break;
                                default:
                                    System.out.println("Ha ingresado una opci�n inv�lida. Presione 'Enter'" +
                                            " para continuar y vuelva a intentarlo.");
                                    System.in.read();
                                    break;
                            }
                        } while (control);
                        control = true;
                        break;
                    case 3:
                        String busOpt;
                        do {
                            System.out.println("Elija una opci�n: \na. Agregar autobuses.\nb. Modificar un autob�s.\nc. Quitar un autob�s.\nd. Regresar.");
                            busOpt = scn.next();
                            switch (busOpt.toLowerCase()) {
                                case "a":
                                    // Agregar un autob�s. Primero consigue la mayor unidad actual y la incrementa.
                                    unit = ToGet.unit(PathStorage.getBusPath(), PathStorage.getBusFile(), readUnit, unit, Autobuses);
                                    Bus.toAdd(unit, Autobuses);
                                    break;
                                case "b":
                                    // Modifica un autob�s por el m�todo toModify.
                                    ArrayList<String> readFile = Reader.reading(PathStorage.getBusPath(), PathStorage.getBusFile());
                                    Bus.toModify(readFile, Autobuses);
                                    break;
                                case "c":
                                    // Elimina un autob�s con el m�todo toDelete.
                                    readFile = Reader.reading(PathStorage.getBusPath(), PathStorage.getBusFile());
                                    Bus.toDelete(readFile, Autobuses);
                                    break;
                                case "d":
                                    control = false;
                                    break;
                                default:
                                    System.out.println("Ha ingresado una opci�n inv�lida. Presione 'Enter'" +
                                            " para continuar y vuelva a intentarlo.");
                                    System.in.read();
                                    break;
                            }
                        } while (control);
                        control = true;
                        break;
                    case 4:
                        String destOpt;
                        do {
                            System.out.println("Elige una opci�n:\na. Agregar destino.\nb. Quitar destino.\nc. Regresar.");
                            destOpt = scn.next();
                            switch (destOpt.toLowerCase()) {
                                case "a":
                                    // Agrega un destino a la central con el m�todo toAdd.
                                    Destinos.toAdd(PathStorage.getDestPath(), PathStorage.getDestFile(), Destinations);
                                    break;
                                case "b":
                                    // Elimina un destino existente de la central con el m�todo toDelete.
                                    ArrayList<String> readFile = Reader.reading(PathStorage.getDestPath(), PathStorage.getDestFile());
                                    Destinos.toDelete(readFile, Destinations);
                                    break;
                                case "c":
                                    control = false;
                                    break;
                                default:
                                    System.out.println("Ha ingresado una opci�n inv�lida. Presione 'Enter'" +
                                            " para continuar y vuelva a intentarlo.");
                                    System.in.read();
                                    break;
                            }
                        } while (control);
                        control = true;
                        break;
                    case 5:
                        String salesOpt;
                        do {
                            System.out.println("Elija una opci�n:\na. N�mero de boletos vendidos totales.\nb. N�mero de boletos " +
                                    "vendidos por d�a.\nc. N�mero de boletos vendidos en un rango de fechas.\nd. N�mero de boletos " +
                                    "vendidos por rango de edad.\ne. M�ximo, m�nimo y promedio de ventas en un rango de fechas.\n" +
                                    "f. Regresar.");
                            salesOpt = scn.next();
                            switch (salesOpt.toLowerCase()) {
                                case "a":
                                    // Llama al m�todo totalSales, que lleva a un submen� extra con opciones espec�ficas.
                                    ArrayList<File> readFolder = new ArrayList<>();
                                    for (int i = 0; i < Reader.readFolder(PathStorage.getSalesFolder()).length; i++) {
                                        readFolder.add(Reader.readFolder(PathStorage.getSalesFolder())[i]);
                                    }
                                    Ventas.totalSales(readFolder, PathStorage.getDestPath(), PathStorage.getDestFile(), PathStorage.getUsersPath(), PathStorage.getUsersFile());
                                    break;
                                case "b":
                                    // Llama al m�todo salesByDay, que lleva a un submen� extra con opciones espec�ficas.
                                    readFolder = new ArrayList<>();
                                    for (int i = 0; i < Reader.readFolder(PathStorage.getSalesFolder()).length; i++) {
                                        readFolder.add(Reader.readFolder(PathStorage.getSalesFolder())[i]);
                                    }
                                    Ventas.salesByDay(readFolder, PathStorage.getDestPath(), PathStorage.getDestFile(), PathStorage.getUsersPath(), PathStorage.getUsersFile());
                                    break;
                                case "c":
                                    // Llama al m�todo salesByRange, que lleva a un submen� extra con opciones espec�ficas.
                                    readFolder = new ArrayList<>();
                                    for (int i = 0; i < Reader.readFolder(PathStorage.getSalesFolder()).length; i++) {
                                        readFolder.add(Reader.readFolder(PathStorage.getSalesFolder())[i]);
                                    }
                                    Ventas.salesByRange(readFolder, PathStorage.getDestPath(), PathStorage.getDestFile(), PathStorage.getUsersPath(), PathStorage.getUsersFile());
                                    break;
                                case "d":
                                    // Llama al m�todo salesByAge, que lleva a un submen� extra con opciones espec�ficas.
                                    readFolder = new ArrayList<>();
                                    for (int i = 0; i < Reader.readFolder(PathStorage.getSalesFolder()).length; i++) {
                                        readFolder.add(Reader.readFolder(PathStorage.getSalesFolder())[i]);
                                    }
                                    Ventas.salesByAge(readFolder, PathStorage.getUsersPath(), PathStorage.getUsersFile());
                                    break;
                                case "e":
                                    // Llama al m�todo maxMinAvByRange, que lleva a un submen� extra con opciones espec�ficas.
                                    readFolder = new ArrayList<>();
                                    for (int i = 0; i < Reader.readFolder(PathStorage.getSalesFolder()).length; i++) {
                                        readFolder.add(Reader.readFolder(PathStorage.getSalesFolder())[i]);
                                    }
                                    Ventas.maxMinAvByRange(readFolder);
                                    break;
                                case "f":
                                    control = false;
                                    break;
                                default:
                                    System.out.println("Ha ingresado una opci�n inv�lida. Presione 'Enter'" +
                                            " para continuar y vuelva a intentarlo.");
                                    System.in.read();
                                    break;
                            }
                        } while (control);
                        control = true;
                        break;
                    case 6:
                        String sixthOpt = null;
                        ArrayList<String> readFile;
                        // Primero eval�a la existencia de Central. Si no, te obliga a crearlo.
                        if (Central.exists()) {
                            System.out.println("Elija una opci�n:\na. Modificar n�mero de salas de recepci�n.\n" +
                                    "b. Modificar n�mero de asientos en recepci�n.\nc. Modificar n�mero de taquillas.\n" +
                                    "d. Modificar comodidades.\ne. Regresar.");
                            sixthOpt = scn.next();
                            switch (sixthOpt.toLowerCase()) {
                                case "a":
                                    // Modifica el n�mero de salas.
                                    readFile = Reader.reading(Central);
                                    System.out.println("Ingrese el n�mero de salas: ");
                                    int lobby = scn.nextInt();
                                    readFile.set(3, lobby + "");
                                    Modifier.toModify(readFile, Central);
                                    System.out.println("Se ha modificado el n�mero de salas de recepci�n." +
                                            " Presione 'Enter' para continuar.");
                                    System.in.read();
                                    break;
                                case "b":
                                    // Modifica el n�mero de asientos.
                                    readFile = Reader.reading(Central);
                                    System.out.println("Ingrese el n�mero de asientos en recepci�n: ");
                                    readFile.set(4, scn.nextInt() + "");
                                    Modifier.toModify(readFile, Central);
                                    System.out.println("Se ha modificado el n�mero de asientos. Presione " +
                                            "'Enter' para continuar.");
                                    System.in.read();
                                    break;
                                case "c":
                                    // Modifica el n�mero de taquillas.
                                    readFile = Reader.reading(Central);
                                    System.out.println("Ingrese el n�mero de taquillas: ");
                                    readFile.set(5, scn.nextInt() + "");
                                    Modifier.toModify(readFile, Central);
                                    System.out.println("Se ha modificado el n�mero de taquillas. Presione " +
                                            "'Enter' para continuar.");
                                    System.in.read();
                                    break;
                                case "d":
                                    // Modifica las comodidades.
                                    System.out.println("Elija una opci�n:\n1. Modificar n�mero de televisiones.\n2. " +
                                            "Aire acondicionado.");
                                    switch (scn.nextInt()) {
                                        case 1:
                                            // Televisi�n.
                                            readFile = Reader.reading(Central);
                                            System.out.println("�Hay televisi�n en recepci�n? Escriba 1 para 'S�'" +
                                                    " o 2 para 'No'.");
                                            if (scn.nextInt() == 1)
                                                readFile.set(6, "true");
                                            else
                                                readFile.set(6, "false");
                                            break;
                                        case 2:
                                            // Aire acondicionado.
                                            readFile = Reader.reading(Central);
                                            System.out.println("�Hay aire acondicionado en recepci�n? Escriba 1 para 'S�'" +
                                                    " o 2 para 'No'.");
                                            if (scn.nextInt() == 1)
                                                readFile.set(7, "true");
                                            else
                                                readFile.set(7, "false");
                                            break;
                                        default:
                                            break;
                                    }
                                    break;
                                case "e":
                                    break;
                                default:
                                    System.out.println("Ha ingresado una opci�n inv�lida. Presione 'Enter'" +
                                            " para continuar y vuelva a intentarlo.");
                                    System.in.read();
                                    break;
                            }
                        } else {
                            // Proceso de creaci�n del archivo Central.
                            boolean hasTV = false, hasAC = false; int busNum, baths, lobby, seats, taqNum;
                            System.out.println("Ingrese el nombre de la central: ");
                            String name = scn.nextLine();
                            scn.nextLine();
                            System.out.println("Ingrese el n�mero de autobuses: ");
                            try {
                                busNum = scn.nextInt();
                            } catch (InputMismatchException ex) {
                                System.out.println("Ha ingresado un par�metro inv�lido: solo se admiten n�meros " +
                                        "enteros. Vuelva a intentarlo: ");
                                busNum = scn.nextInt();
                            }
                            System.out.println("Ingrese el n�mero de ba�os: ");
                            try {
                                baths = scn.nextInt();
                            } catch (InputMismatchException ex) {
                                System.out.println("Ha ingresado un par�metro inv�lido: solo se admiten n�meros " +
                                        "enteros. Vuelva a intentarlo: ");
                                baths = scn.nextInt();
                            }
                            System.out.println("Ingrese el n�mero de salas: ");
                            try {
                                lobby = scn.nextInt();
                            } catch (InputMismatchException ex) {
                                System.out.println("Ha ingresado un par�metro inv�lido: solo se admiten n�meros " +
                                        "enteros. Vuelva a intentarlo: ");
                                lobby = scn.nextInt();
                            }
                            System.out.println("Ingrese el n�mero de asientos por sala: ");
                            try {
                                seats = scn.nextInt();
                            } catch (InputMismatchException ex) {
                                System.out.println("Ha ingresado un par�metro inv�lido: solo se admiten n�meros " +
                                        "enteros. Vuelva a intentarlo: ");
                                seats = scn.nextInt();
                            }
                            System.out.println("Ingrese el n�mero de taquillas: ");
                            try {
                                taqNum = scn.nextInt();
                            } catch (InputMismatchException ex) {
                                System.out.println("Ha ingresado un par�metro inv�lido: solo se admiten n�meros " +
                                        "enteros. Vuelva a intentarlo: ");
                                taqNum = scn.nextInt();
                            }
                            System.out.println("Escriba 1 si tiene TV, o 2 si no: ");
                            if (scn.nextInt() == 1)
                                hasTV = true;
                            System.out.println("Escriba 1 si tiene AC, o 2 si no: ");
                            if (scn.nextInt() == 1)
                                hasAC = true;
                            TheCreator.central(name, busNum, baths, lobby, seats, taqNum, hasTV, hasAC, Central);
                        }
                        break;
                    case 7:
                        System.out.println("Proyecto final para Programaci�n para Ingenieros I: Central de Autobuses.\n" +
                                "Integrantes:\n-Forte L�pez, Gabriel Arturo.\n-Ramos Labor�n, Sa�l Alberto.\n" +
                                "-Zatarain Palma, Carlos Alberto.");
                        System.out.println("Presione 'Enter' para continuar.");
                        System.in.read();
                        break;
                    default:
                        break;
                }
            } catch (InputMismatchException e) {
                option = -1;
                System.out.println("Ha ingresado una opci�n inv�lida. Presione una 'Enter' " +
                        "para continuar y vuelva a intentarlo.");
                System.in.read();
            }
        } while (option != 0);
    }
}