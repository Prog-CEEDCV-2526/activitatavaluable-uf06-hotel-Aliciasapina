package com.hotel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

/**
 * Gestió de reserves d'un hotel.
 */
public class App {

    // --------- CONSTANTS I VARIABLES GLOBALS ---------

    // Tipus d'habitació
    public static final String TIPUS_ESTANDARD = "Estàndard";
    public static final String TIPUS_SUITE = "Suite";
    public static final String TIPUS_DELUXE = "Deluxe";

    // Serveis addicionals
    public static final String SERVEI_ESMORZAR = "Esmorzar";
    public static final String SERVEI_GIMNAS = "Gimnàs";
    public static final String SERVEI_SPA = "Spa";
    public static final String SERVEI_PISCINA = "Piscina";

    // Capacitat inicial
    public static final int CAPACITAT_ESTANDARD = 30;
    public static final int CAPACITAT_SUITE = 20;
    public static final int CAPACITAT_DELUXE = 10;

    // IVA
    public static final float IVA = 0.21f;

    // Scanner únic
    public static Scanner sc = new Scanner(System.in);

    // HashMaps de consulta
    public static HashMap<String, Float> preusHabitacions = new HashMap<String, Float>();
    public static HashMap<String, Integer> capacitatInicial = new HashMap<String, Integer>();
    public static HashMap<String, Float> preusServeis = new HashMap<String, Float>();

    // HashMaps dinàmics
    public static HashMap<String, Integer> disponibilitatHabitacions = new HashMap<String, Integer>();
    public static HashMap<Integer, ArrayList<String>> reserves = new HashMap<Integer, ArrayList<String>>();

    // Generador de nombres aleatoris per als codis de reserva
    public static Random random = new Random();

    // --------- MÈTODE MAIN ---------

    /**
     * Mètode principal. Mostra el menú en un bucle i gestiona l'opció triada
     * fins que l'usuari decideix eixir.
     */
    public static void main(String[] args) {
        inicialitzarPreus();

        int opcio = 0;
        do {
            mostrarMenu();
            opcio = llegirEnter("Seleccione una opció: ");
            gestionarOpcio(opcio);
        } while (opcio != 6);

        System.out.println("Eixint del sistema... Gràcies per utilitzar el gestor de reserves!");
    }

    // --------- MÈTODES DEMANATS ---------

    /**
     * Configura els preus de les habitacions, serveis addicionals i
     * les capacitats inicials en els HashMaps corresponents.
     */
    public static void inicialitzarPreus() {
        // Preus habitacions
        preusHabitacions.put(TIPUS_ESTANDARD, 50f);
        preusHabitacions.put(TIPUS_SUITE, 100f);
        preusHabitacions.put(TIPUS_DELUXE, 150f);

        // Capacitats inicials
        capacitatInicial.put(TIPUS_ESTANDARD, CAPACITAT_ESTANDARD);
        capacitatInicial.put(TIPUS_SUITE, CAPACITAT_SUITE);
        capacitatInicial.put(TIPUS_DELUXE, CAPACITAT_DELUXE);

        // Disponibilitat inicial (comença igual que la capacitat)
        disponibilitatHabitacions.put(TIPUS_ESTANDARD, CAPACITAT_ESTANDARD);
        disponibilitatHabitacions.put(TIPUS_SUITE, CAPACITAT_SUITE);
        disponibilitatHabitacions.put(TIPUS_DELUXE, CAPACITAT_DELUXE);

        // Preus serveis
        preusServeis.put(SERVEI_ESMORZAR, 10f);
        preusServeis.put(SERVEI_GIMNAS, 15f);
        preusServeis.put(SERVEI_SPA, 20f);
        preusServeis.put(SERVEI_PISCINA, 25f);
    }

    /**
     * Mostra el menú principal amb les opcions disponibles per a l'usuari.
     */
    public static void mostrarMenu() {
        System.out.println("\n===== MENÚ PRINCIPAL =====");
        System.out.println("1. Reservar una habitació");
        System.out.println("2. Alliberar una habitació");
        System.out.println("3. Consultar disponibilitat");
        System.out.println("4. Llistar reserves per tipus");
        System.out.println("5. Obtindre una reserva");
        System.out.println("6. Ixir");
    }

    /**
     * Processa l'opció seleccionada per l'usuari i crida el mètode corresponent.
     */
    public static void gestionarOpcio(int opcio) {
       //TODO:
       switch (opcio) {
        case 1:
            reservarHabitacio();
        break;
      //  case 2:
      //      alliberarHabitacio();
     //   break;
      //  case 3:
      //      consultarDisponibilitat();
      //  break;
      //  case 4:
      //      mostrarDadesReserva(); 
      //  break;
      //  case 5: 
      //      obtindreReservaPerTipus();
      //  break;
        case 6:
            System.out.println();
        break;
        default:
            System.out.println("Opció no vàlida. Torna a intentar-ho.");
        break;
       }
    }

    /**
     * Gestiona tot el procés de reserva: selecció del tipus d'habitació,
     * serveis addicionals, càlcul del preu total i generació del codi de reserva.
     */
    public static void reservarHabitacio() {
        System.out.println("\n===== RESERVAR HABITACIÓ =====");
         //TODO      
        // 1. Seleccionar el tipus d'habitació
        String tipusHabitacio = seleccionarTipusHabitacioDisponible();
        
        if (tipusHabitacio != null) {
            System.out.println(" Tipus d'habitació seleccionat: " + tipusHabitacio);

            // A PARTIR D'AQUÍ, CONTINUARIA LA LÒGICA DE RESERVA:
            
            // 2. Seleccionar serveis addicionals (TODO)
             ArrayList<String> serveis = seleccionarServeis(); 
            
            // 3. Demanar dies, calcular preu, etc. (TODO)
            // float preuFinal = calcularPreuTotal(tipusHabitacio, serveis);
            
            // 4. Generar codi de reserva i guardar-ho (TODO)
            // int codi = generarCodiReserva();
            // disponibilitatHabitacions.put(tipusHabitacio, disponibilitatHabitacions.get(tipusHabitacio) - 1);
            
            // ... mostrar missatge final ...
            
        } 
        // Si tipusHabitacio és null (l'usuari ha triat cancel·lar), el mètode acaba.        
    }

    /**
     * Pregunta a l'usuari un tipus d'habitació en format numèric i
     * retorna el nom del tipus.
     */
    public static String seleccionarTipusHabitacio() {
        //TODO:
       
        return null;
    }

    /**
     * Mostra la disponibilitat i el preu de cada tipus d'habitació,
     * demana a l'usuari un tipus i només el retorna si encara hi ha
     * habitacions disponibles. En cas contrari, retorna null.
     */
    public static String seleccionarTipusHabitacioDisponible() {
        System.out.println("\nTipus d'habitació disponibles:");
        System.out.println();
        //TODO:
        String tipusSeleccionat = null;
        int opcio = 0;

        // Array per ordenar els tipus i facilitar la selecció numèrica (1, 2, 3)
        String[] tipusArray = {TIPUS_ESTANDARD, TIPUS_SUITE, TIPUS_DELUXE};

        do {            
            //mostrar l'informació
            for (int i = 0; i < tipusArray.length; i++) {
                String tipus = tipusArray[i];
                int disponibles = disponibilitatHabitacions.get(tipus);
                float preu = preusHabitacions.get(tipus);
                
                // Formato de impresión: 1. Estàndard (30 disponibles) - 50.00€
                System.out.printf("%d. %s - %d disponibles - - %.0f€\n", 
                                  i + 1, tipus, disponibles, preu);
            }
            System.out.println();
            opcio = llegirEnter("Seleccione el tipus d'habitació: ");
            
        if (opcio >= 1 && opcio <= 3) {
                tipusSeleccionat = tipusArray[opcio - 1];
                int disponibles = disponibilitatHabitacions.get(tipusSeleccionat);

                if (disponibles > 0) {
                    // Selecció vàlida i hi ha disponibilitat
                    return tipusSeleccionat; 
                } else {
                    System.out.println(" Ho sentim, no queden habitacions " + tipusSeleccionat + " disponibles. Si us plau, tria una altra opció.");
                    tipusSeleccionat = null; // Forcem la repetició del bucle de selecció
                }
            
            } else {
                System.out.println("Opció no vàlida. Si us plau, introdueix un número entre 1 i 3.");
                tipusSeleccionat = null; 
            }

        } while (tipusSeleccionat == null);
        return null;
      }  
    

    /**
     * Permet triar serveis addicionals (entre 0 i 4, sense repetir) i
     * els retorna en un ArrayList de String.
     */
    public static ArrayList<String> seleccionarServeis() {
        //TODO:
     ArrayList<String> serveisSeleccionats = new ArrayList<>();
        boolean continuar = true;
        int opcioServei;

        // Array per ordenar els serveis i facilitar la selecció numèrica
        String[] serveisArray = {SERVEI_ESMORZAR, SERVEI_GIMNAS, SERVEI_SPA, SERVEI_PISCINA};

        System.out.println("\nSeleccione tipus d'habitació:");

        do {
            System.out.println("\nServeis adicionals (0-4):");
            System.out.println();
            
            // 0.finalitzar
            System.out.println("0. Finalitzar");
            
            // Bucle per mostrar els serveis amb preu
            for (int i = 0; i < serveisArray.length; i++) {
                String nomServei = serveisArray[i];
                float preu = preusServeis.get(nomServei);
                String estat = serveisSeleccionats.contains(nomServei) ? "": "";

                // Format d'impressió: 1. Esmorzar (10.00€) (AFEGIT)
                System.out.printf("%d. %s (%.0f€)%s\n", i + 1, nomServei, preu, estat);
            }
            System.out.println("----------------------------------------");

            // Preguntar si vol afegir un servei (s/n)
            System.out.print("Vol afegir un servei? (s/n): ");
            String resposta = sc.nextLine().toLowerCase();

            if (resposta.equals("s")) {
                opcioServei = llegirEnter("Seleccione servei (0-4): ");

                if (opcioServei >= 1 && opcioServei <= 4) {
                    String serveiTria = serveisArray[opcioServei - 1];
                    if (serveisSeleccionats.contains(serveiTria)) {
                    // *** NOVEtat: Missatge de servei ja seleccionat i confirmació d'eliminació ***
                    System.out.print(" El servei '" + serveiTria + "' ja està seleccionat. ");
                   // String respostaEliminar = sc.nextLine().trim().toLowerCase();
                    //if (serveisSeleccionats.contains(serveiTria)) {
                        // Si ja està afegit, l'eliminem
                    //    serveisSeleccionats.remove(serveiTria);
                    //    System.out.println(" Servei '" + serveiTria + "' ELIMINAT.");
                    } else if (serveisSeleccionats.size() < 4) {
                        // Si no està afegit i en queden, l'afegim
                        serveisSeleccionats.add(serveiTria);
                        System.out.println(" Servei afegit: '" + serveiTria );
                    } else {
                        System.out.println(" No es poden afegir més de 4 serveis addicionals.");
                    }
                } else if (opcioServei == 0) {
                    continuar = false; // Finalitza la selecció
                } else {
                    System.out.println("Opció no vàlida.");
                }
            } else if (resposta.equals("n")) {
                continuar = false; // Finalitza la selecció
            } else {
                System.out.println("Resposta no vàlida. Si us plau, introdueix 's' o 'n'.");
            }

            // Si ja s'han afegit tots els serveis possibles, sortim del bucle
            if (serveisSeleccionats.size() == 4) {
                System.out.println("\n S'han seleccionat tots els serveis disponibles. Finalitzant la selecció automàticament.");
                continuar = false;
            }

        } while (continuar);

        System.out.println("\nSelecció finalitzada. Total de serveis afegits: " + serveisSeleccionats.size());
        return serveisSeleccionats;
        //return null;
    }

    /**
     * Calcula i retorna el cost total de la reserva, incloent l'habitació,
     * els serveis seleccionats i l'IVA.
     */
    public static float calcularPreuTotal(String tipusHabitacio, ArrayList<String> serveisSeleccionats) {
        //TODO:
        return 0;
    }

    /**
     * Genera i retorna un codi de reserva únic de tres xifres
     * (entre 100 i 999) que no estiga repetit.
     */
    public static int generarCodiReserva() {
        //TODO:
        return 0;
    }

    /**
     * Permet alliberar una habitació utilitzant el codi de reserva
     * i actualitza la disponibilitat.
     */
    public static void alliberarHabitacio() {
        System.out.println("\n===== ALLIBERAR HABITACIÓ =====");
         // TODO: Demanar codi, tornar habitació i eliminar reserva
    }

    /**
     * Mostra la disponibilitat actual de les habitacions (lliures i ocupades).
     */
    public static void consultarDisponibilitat() {
        // TODO: Mostrar lliures i ocupades
    }

    /**
     * Funció recursiva. Mostra les dades de totes les reserves
     * associades a un tipus d'habitació.
     */
    public static void llistarReservesPerTipus(int[] codis, String tipus) {
         // TODO: Implementar recursivitat
    }

    /**
     * Permet consultar els detalls d'una reserva introduint el codi.
     */
    public static void obtindreReserva() {
        System.out.println("\n===== CONSULTAR RESERVA =====");
        // TODO: Mostrar dades d'una reserva concreta
 
    }

    /**
     * Mostra totes les reserves existents per a un tipus d'habitació
     * específic.
     */
    public static void obtindreReservaPerTipus() {
        System.out.println("\n===== CONSULTAR RESERVES PER TIPUS =====");
        // TODO: Llistar reserves per tipus
    }

    /**
     * Consulta i mostra en detall la informació d'una reserva.
     */
    public static void mostrarDadesReserva(int codi) {
       // TODO: Imprimir tota la informació d'una reserva
    }

    // --------- MÈTODES AUXILIARS (PER MILLORAR LEGIBILITAT) ---------

    /**
     * Llig un enter per teclat mostrant un missatge i gestiona possibles
     * errors d'entrada.
     */
    static int llegirEnter(String missatge) {
        int valor = 0;
        boolean correcte = false;
        while (!correcte) {
                System.out.print(missatge);
                valor = sc.nextInt();
                correcte = true;
        }
        return valor;
    }

    /**
     * Mostra per pantalla informació d'un tipus d'habitació: preu i
     * habitacions disponibles.
     */
    static void mostrarInfoTipus(String tipus) {
        int disponibles = disponibilitatHabitacions.get(tipus);
        int capacitat = capacitatInicial.get(tipus);
        float preu = preusHabitacions.get(tipus);
        System.out.println("- " + tipus + " (" + disponibles + " disponibles de " + capacitat + ") - " + preu + "€");
    }

    /**
     * Mostra la disponibilitat (lliures i ocupades) d'un tipus d'habitació.
     */
    static void mostrarDisponibilitatTipus(String tipus) {
        int lliures = disponibilitatHabitacions.get(tipus);
        int capacitat = capacitatInicial.get(tipus);
        int ocupades = capacitat - lliures;

        String etiqueta = tipus;
        if (etiqueta.length() < 8) {
            etiqueta = etiqueta + "\t"; // per a quadrar la taula
        }

        System.out.println(etiqueta + "\t" + lliures + "\t" + ocupades);
    }
}
