import java.util.Random;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Esercizio #1 - Array con gestione errori
 * 
 * Programma che:
 * 1. Crea un array di 5 interi con valori casuali tra 1 e 10
 * 2. Stampa l'array in console
 * 3. Chiede all'utente di inserire un numero in una posizione specificata
 * 4. Stampa il nuovo stato dell'array
 * 5. Ripete fino a quando l'utente non inserisce 0
 * 6. Gestisce errori di violazione dei limiti dell'array
 */
public class Esercizio1_ArrayGestioneErrori {
    
    // Array di 5 interi
    private static int[] numeri = new int[5];
    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();
    
    public static void main(String[] args) {
        System.out.println("=== ESERCIZIO #1 - GESTIONE ARRAY CON ERRORI ===");
        
        // Inizializza l'array con valori casuali tra 1 e 10
        inizializzaArrayCasuale();
        
        // Stampa l'array iniziale
        System.out.println("\nArray iniziale:");
        stampaArray();
        
        // Loop principale del programma
        boolean continua = true;
        while (continua) {
            try {
                System.out.println("\n--- MENU ---");
                System.out.println("Inserisci un nuovo valore (0 per uscire):");
                
                int valore = leggiIntero("Valore: ");
                
                // Se l'utente inserisce 0, esce dal programma
                if (valore == 0) {
                    System.out.println("Programma terminato.");
                    continua = false;
                    continue;
                }
                
                // Chiede la posizione dove inserire il valore
                int posizione = leggiIntero("Posizione (0-4): ");
                
                // Inserisce il valore nella posizione specificata
                inserisciValore(valore, posizione);
                
                // Stampa il nuovo stato dell'array
                System.out.println("\nNuovo stato dell'array:");
                stampaArray();
                
            } catch (ArrayIndexOutOfBoundsException e) {
                System.err.println("ERRORE: Posizione non valida! L'array ha indici da 0 a 4.");
                // TODO: Usare LogBack per logging
                // logger.error("Tentativo di accesso a posizione non valida: " + e.getMessage());
                
            } catch (InputMismatchException e) {
                System.err.println("ERRORE: Inserire solo numeri interi!");
                scanner.nextLine(); // Pulisce il buffer di input
                // TODO: Usare LogBack per logging
                // logger.error("Input non valido: " + e.getMessage());
                
            } catch (Exception e) {
                System.err.println("ERRORE GENERICO: " + e.getMessage());
                // TODO: Usare LogBack per logging
                // logger.error("Errore generico: " + e.getMessage());
            }
        }
        
        scanner.close();
    }
    
    /**
     * Inizializza l'array con valori casuali tra 1 e 10
     */
    private static void inizializzaArrayCasuale() {
        System.out.println("Inizializzazione array con valori casuali tra 1 e 10...");
        
        for (int i = 0; i < numeri.length; i++) {
            // Genera numero casuale tra 1 e 10 (inclusi)
            numeri[i] = random.nextInt(10) + 1;
        }
    }
    
    /**
     * Stampa l'array corrente
     */
    private static void stampaArray() {
        System.out.print("Array: [");
        for (int i = 0; i < numeri.length; i++) {
            System.out.print(numeri[i]);
            if (i < numeri.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
        
        // Stampa anche gli indici per chiarezza
        System.out.print("Indici: [");
        for (int i = 0; i < numeri.length; i++) {
            System.out.print(i);
            if (i < numeri.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
    
    /**
     * Inserisce un valore in una posizione specificata dell'array
     * @param valore Il valore da inserire
     * @param posizione La posizione dove inserire il valore
     * @throws ArrayIndexOutOfBoundsException se la posizione non è valida
     */
    private static void inserisciValore(int valore, int posizione) 
            throws ArrayIndexOutOfBoundsException {
        
        // Verifica esplicita dei limiti dell'array
        if (posizione < 0 || posizione >= numeri.length) {
            throw new ArrayIndexOutOfBoundsException(
                "Posizione " + posizione + " non valida. Usare valori tra 0 e " + (numeri.length - 1));
        }
        
        int vecchioValore = numeri[posizione];
        numeri[posizione] = valore;
        
        System.out.println("Valore inserito con successo!");
        System.out.println("Posizione " + posizione + ": " + vecchioValore + " -> " + valore);
    }
    
    /**
     * Legge un intero dall'input utente con gestione errori
     * @param messaggio Il messaggio da mostrare all'utente
     * @return L'intero inserito dall'utente
     * @throws InputMismatchException se l'input non è un intero valido
     */
    private static int leggiIntero(String messaggio) throws InputMismatchException {
        System.out.print(messaggio);
        return scanner.nextInt();
    }
}

/*
 * NOTE PER L'ESECUZIONE:
 * 
 * 1. Compilare: javac Esercizio1_ArrayGestioneErrori.java
 * 2. Eseguire: java Esercizio1_ArrayGestioneErrori
 * 
 * ESEMPI DI TEST:
 * - Inserire valori validi (posizioni 0-4)
 * - Testare posizioni non valide (-1, 5, 10) per vedere la gestione errori
 * - Inserire caratteri non numerici per testare InputMismatchException
 * - Inserire 0 per uscire dal programma
 * 
 * EXTRA - LOGBACK:
 * Per utilizzare LogBack, aggiungere le dipendenze al progetto e
 * sostituire i commenti TODO con il codice di logging appropriato.
 */