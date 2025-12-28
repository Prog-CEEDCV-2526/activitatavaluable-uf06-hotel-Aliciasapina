package com.hotel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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
        System.out.println("4. Consultar dades d'una reserva");
        System.out.println("5. Consultar reserves per tipus");
        System.out.println("6. Ixir");
    }

    /**
     * Processa l'opció seleccionada per l'usuari i crida el mètode corresponent.
     */
    public static void gestionarOpcio(int opcio) {
        // TODO:
        switch (opcio) {
            case 1:
                reservarHabitacio();
            break;
            case 2:
                alliberarHabitacio();
            break;
            case 3:
                consultarDisponibilitat();
            break;
            case 4:
                obtindreReserva();
            break;
            case 5:
                obtindreReservaPerTipus();
            break;
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
        // TODO
        String tipusHabitacio = seleccionarTipusHabitacioDisponible();
        if (tipusHabitacio != null) {
            // 2. Selección de servicios y cálculo de precio
            ArrayList<String> serveis = seleccionarServeis();
            float preuFinal = calcularPreuTotal(tipusHabitacio, serveis);

            // 3. Generar código único (el método debe usar reserves.containsKey)
            int codiGenerat = generarCodiReserva();

            ArrayList<String> dadesReserva = new ArrayList<>();
            dadesReserva.add(tipusHabitacio); // Posición 0
            dadesReserva.add(String.valueOf(preuFinal)); // Posición 1

            for (String s : serveis) {
                dadesReserva.add(s);
            }

            reserves.put(codiGenerat, dadesReserva);

            int disponibles = disponibilitatHabitacions.get(tipusHabitacio);
            disponibilitatHabitacions.put(tipusHabitacio, disponibles - 1);

            System.out.println("\nReserva creada amb èxit!");
            System.out.println("\nCodi de reserva: " + codiGenerat);
        } else {

            System.out.println("\nNo s'ha pogut realitzar la reserva.");
        }
    }

    /**
     * Pregunta a l'usuari un tipus d'habitació en format numèric i
     * retorna el nom del tipus.
     */
    public static String seleccionarTipusHabitacio() {
        // TODO:
        String[] tipusArray = { TIPUS_ESTANDARD, TIPUS_SUITE, TIPUS_DELUXE };

        System.out.print("\nSeleccione el tipus d'habitació: ");
        
        int opcio = sc.nextInt();
        sc.nextLine(); 

        if (opcio >= 1 && opcio <= 3) {
            return tipusArray[opcio - 1];
        } else {
            System.out.println("Opció no vàlida.");
            return null;
        }
    }

    /**
     * Mostra la disponibilitat i el preu de cada tipus d'habitació,
     * demana a l'usuari un tipus i només el retorna si encara hi ha
     * habitacions disponibles. En cas contrari, retorna null.
     */
    public static String seleccionarTipusHabitacioDisponible() {
        System.out.println("\nTipus d'habitació disponibles:\n");
        String[] tipusArray = { TIPUS_ESTANDARD, TIPUS_SUITE, TIPUS_DELUXE };

        for (int i = 0; i < tipusArray.length; i++) {
            String tipus = tipusArray[i];
            int lliures = disponibilitatHabitacions.get(tipus);
            float preu = preusHabitacions.get(tipus);
            System.out.println((i + 1) + ". " + tipus + " - " + lliures + " disponibles - " + preu + "€");
        }

        // Crida al mètode anterior per obtindre la tria de l'usuari
        String seleccionada = seleccionarTipusHabitacio();

        // Comprovem si el tipus existeix i si queden habitacions lliures
        if (seleccionada != null && disponibilitatHabitacions.get(seleccionada) > 0) {
            return seleccionada;
        } else {
            if (seleccionada != null) {
                System.out.println("Ho sentim, no queden habitacions " + seleccionada + " disponibles.");
            }
            return null;
        }
    }

    /**
     * Permet triar serveis addicionals (entre 0 i 4, sense repetir) i
     * els retorna en un ArrayList de String.
     */
    public static ArrayList<String> seleccionarServeis() {
        // TODO:
    ArrayList<String> serveisSeleccionats = new ArrayList<>();
        boolean continuar = true;
        int opcioServei;
        String[] serveisArray = { SERVEI_ESMORZAR, SERVEI_GIMNAS, SERVEI_SPA, SERVEI_PISCINA };

        System.out.println("\nServeis adicionals (0-4):");
        System.out.println();

        // 0.finalitzar
        System.out.println("0. Finalitzar");

        // Bucle per mostrar els serveis amb preu
        for (int i = 0; i < serveisArray.length; i++) {
            String nomServei = serveisArray[i];
            float preu = preusServeis.get(nomServei);
            String estat = serveisSeleccionats.contains(nomServei) ? "" : "";
            System.out.printf("%d. %s (%.0f€)%s\n", i + 1, nomServei, preu, estat);
        }
        
        
        do {
            // Preguntar si vol afegir un servei (s/n)
            System.out.print("\nVol afegir un servei? (s/n): ");
            String resposta = sc.nextLine();
            //toLowerCase();

            if (resposta.equals("s")) {
                System.out.print("Seleccione servei: ");
               // opcioServei = llegirEnter("Seleccione servei (0-4): ");
                opcioServei = sc.nextInt();
                sc.nextLine();
                if (opcioServei >= 1 && opcioServei <= 4) {
                    String serveiTria = serveisArray[opcioServei - 1];
                    if (serveisSeleccionats.contains(serveiTria)) {
                        System.out.print("\nEl servei '" + serveiTria + "' ja està seleccionat. ");
                    } else if (serveisSeleccionats.size() < 4) {
                        // Si no està afegit i en queden, l'afegim
                        serveisSeleccionats.add(serveiTria);
                        System.out.println("\nServei afegit: " + serveiTria);
                    } else {
                        System.out.println("\nNo es poden afegir més de 4 serveis addicionals.");
                    }
                } else if (opcioServei == 0) {
                    continuar = false;
                } else {
                    System.out.println("Opció no vàlida.");
                }
            } else if (resposta.equals("n")) {
                continuar = false;
            } else {
                System.out.println("Resposta no vàlida. Si us plau, introdueix 's' o 'n'.");
            }

            // Si ja s'han afegit tots els serveis possibles, sortim del bucle
            if (serveisSeleccionats.size() == 4) {
                System.out.println("\n S'han seleccionat tots els serveis disponibles. Finalitzant la selecció automàticament.");
                continuar = false;
            }

        } while (continuar);

        return serveisSeleccionats;
    }

    /**
     * Calcula i retorna el cost total de la reserva, incloent l'habitació,
     * els serveis seleccionats i l'IVA.
     */
    public static float calcularPreuTotal(String tipusHabitacio, ArrayList<String> serveisSeleccionats) {
        // TODO:
        float preuBaseHabitacio = preusHabitacions.getOrDefault(tipusHabitacio, 0.0f);
        float costServeis = 0.0f;
        // 1. Càlcul del cost total dels serveis
        for (String servei : serveisSeleccionats) {
            costServeis += preusServeis.getOrDefault(servei, 0.0f);
        }
        // 2. Càlcul del cost de l'habitació
        float costHabitacio = preuBaseHabitacio;
        // 3. Càlcul del subtotal
        float subtotal = costHabitacio + costServeis;
        // 4. Càlcul del total d'IVA
        float impostos = subtotal * IVA;
        // 5. Càlcul del preu final
        float preuFinal = subtotal + impostos;

        // Mostra un desglossament detallat
        System.out.println("\nCalculem el total...");
        System.out.printf("\nPreu Habitació: %.0f€\n", costHabitacio);
        System.out.printf("\nServeis: (%s) %.0f€\n", serveisSeleccionats, costServeis);
        System.out.printf("\nSubtotal: %.0f€\n", subtotal);
        System.out.printf("\nIVA (%.0f%%): %.2f€\n", IVA * 100, impostos);
        System.out.printf("\nTOTAL: %.2f€\n", preuFinal);
   
        return preuFinal;
    }

    /**
     * Genera i retorna un codi de reserva únic de tres xifres
     * (entre 100 i 999) que no estiga repetit.
     */

    public static int generarCodiReserva() {
        // TODO:
        int codiGenerat;
        do {
            codiGenerat = random.nextInt(900) + 100;
        } while (reserves.containsKey(codiGenerat)); 
        return codiGenerat;
    }

    /**
     * Permet alliberar una habitació utilitzant el codi de reserva
     * i actualitza la disponibilitat.
     */
    public static void alliberarHabitacio() {
        System.out.println("\n===== ALLIBERAR HABITACIÓ =====");
        System.out.print("Introdueix el codi de reserva: ");
        int codi = sc.nextInt();
        sc.nextLine();

        if (reserves.containsKey(codi)) {
            String tipusHabitacio = reserves.get(codi).get(0);
            reserves.remove(codi);
            // Actualitzem disponibilitat
            disponibilitatHabitacions.put(tipusHabitacio, disponibilitatHabitacions.get(tipusHabitacio) + 1);

            System.out.println("\nReserva trobada!");
            System.out.println("\nHabitació alliberada correctament.");
            System.out.println("\nDisponibilitat actualizada.");
        } else {
            System.out.println("No s'ha trobat cap reserva amb aquest codi.");
        }
    }

    /**
     * Mostra la disponibilitat actual de les habitacions (lliures i ocupades).
     */
    // public static final Map<String, Integer> TOTAL_HABITACIONS = Map.of(
    // TIPUS_ESTANDARD, 30, TIPUS_SUITE, 20, TIPUS_DELUXE, 10);

    public static void consultarDisponibilitat() {
        // TODO: Mostrar lliures i ocupades
        System.out.println("\n===== DISPONIBILITAT D'HABITACIONS =====");
        String[] tipusArray = { TIPUS_ESTANDARD, TIPUS_SUITE, TIPUS_DELUXE };

   
        System.out.println("----------------------------------------");
        System.out.printf("%-15s %-10s %-10s\n", "Tipus", "Lliures", "Ocupades");
        System.out.println("----------------------------------------");

        for (String tipus : tipusArray) {
            int lliures = disponibilitatHabitacions.get(tipus);

            int total = capacitatInicial.get(tipus);

            int ocupades = total - lliures;

            // Alineación a la izquierda para que las columnas no se desplacen
            System.out.printf("%-15s %-10d %-10d\n", tipus, lliures, ocupades);
        }
        System.out.println("----------------------------------------");
    }

    /**
     * Funció recursiva. Mostra les dades de totes les reserves
     * associades a un tipus d'habitació.
     */
    public static void llistarReservesPerTipus(int[] codis, String tipus) {
        // TODO: Implementar recursivitat
        if (codis == null || codis.length == 0) {
            return;
        }
        int codiActual = codis[0];

        ArrayList<String> dades = reserves.get(codiActual);
        if (dades != null && dades.get(0).equals(tipus)) {
            System.out.println("\nCodi: " + codiActual);
            mostrarDadesReserva(codiActual);
        }

        // Creem un nou array sense el primer element
        int[] restaDeCodis = new int[codis.length - 1];
        System.arraycopy(codis, 1, restaDeCodis, 0, codis.length - 1);

        // Crida recursiva amb la resta de l'array
        llistarReservesPerTipus(restaDeCodis, tipus);
    }

    /**
     * Permet consultar els detalls d'una reserva introduint el codi.
     */
    public static void obtindreReserva() {
        System.out.println("\n===== CONSULTAR RESERVA =====");
        // TODO: Mostrar dades d'una reserva concreta
        int codi = llegirEnter("Introdueix el codi de reserva: ");
        System.out.println("\nDades de la reserva:");

        if (reserves.containsKey(codi)) {
            mostrarDadesReserva(codi);
        } else {
            System.out.println("\nNo s'ha trobat cap reserva amb aquest codi.");
        }
    }

    /**
     * Mostra totes les reserves existents per a un tipus d'habitació
     * específic.
     */
    public static void obtindreReservaPerTipus() {
        System.out.println("\n===== CONSULTAR RESERVES PER TIPUS =====");

        System.out.println("\nSeleccione tipus:\n");
        String[] tipusArray = { TIPUS_ESTANDARD, TIPUS_SUITE, TIPUS_DELUXE };
        for (int i = 0; i < tipusArray.length; i++) {
            System.out.println((i + 1) + ". " + tipusArray[i]);
        }

        System.out.print("\nOpció: ");
        int opcio = sc.nextInt();
        sc.nextLine(); 

        if (opcio >= 1 && opcio <= tipusArray.length) {
            String tipusSeleccionat = tipusArray[opcio - 1];
            
            // Verifiquem si hi ha alguna reserva d'aquest tipus abans de llistar
            boolean hiHaReserves = false;
            for (ArrayList<String> dades : reserves.values()) {
                if (dades.get(0).equals(tipusSeleccionat)) {
                    hiHaReserves = true;
                    break;
                }
            }

            if (!hiHaReserves) {
                // Frase que apareix si no hi ha reserves d'eixe tipus
                System.out.println("\nNo hi ha cap reserva registrada per al tipus: " + tipusSeleccionat);
            } else {
                System.out.println("\nReserves de tipus: " + tipusSeleccionat);;
                
                Object[] clausObj = reserves.keySet().toArray();
                int[] totsElsCodis = new int[clausObj.length];
                for (int i = 0; i < clausObj.length; i++) {
                    totsElsCodis[i] = (int) clausObj[i];
                }

                llistarReservesPerTipus(totsElsCodis, tipusSeleccionat);
                System.out.println("\n(No hi ha més reserves d'aquest tipus.)");
            }
        } else {
            System.out.println("Opció no vàlida.");
        }
    }

    /**
     * Consulta i mostra en detall la informació d'una reserva.
     */
    public static void mostrarDadesReserva(int codi) {
        // TODO: Imprimir tota la informació d'una reserva
        ArrayList<String> dades = reserves.get(codi);
        if (dades == null)
            return;

        String tipus = dades.get(0); // Posición 0
        String preu = dades.get(1); // Posición 1

        System.out.println("·Tipus d'habitació: " + tipus);
        System.out.println("·Cost total: " + preu + "€");
        System.out.println("·Serveis addicionals:");

        // Los servicios empiezan en la posición 2
        if (dades.size() <= 2) {
            System.out.println(" (Cap servei)");
        } else {
            for (int i = 2; i < dades.size(); i++) {
                System.out.println("- " + dades.get(i));
            }
        }
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