import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Esercizio #2 - Calcolo km/litro con gestione divisione per zero
 * 
 * Programma che:
 * 1. Riceve dall'utente il numero di km percorsi
 * 2. Riceve i litri di carburante consumati
 * 3. Calcola e stampa i km/litro percorsi
 * 4. Gestisce l'eccezione di divisione per zero con try-catch
 * 5. Testa sia con interi che con double
 */
public class Esercizio2_CalcoloKmLitro {
    
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("=== ESERCIZIO #2 - CALCOLO KM/LITRO ===");
        
        boolean continua = true;
        
        while (continua) {
            try {
                System.out.println("\n--- MENU ---");
                System.out.println("1. Calcolo con numeri interi");
                System.out.println("2. Calcolo con numeri decimali (double)");
                System.out.println("0. Esci");
                System.out.print("Scegli un'opzione: ");
                
                int scelta = scanner.nextInt();
                
                switch (scelta) {
                    case 1:
                        calcoloConInteri();
                        break;
                    case 2:
                        calcoloConDouble();
                        break;
                    case 0:
                        System.out.println("Programma terminato.");
                        continua = false;
                        break;
                    default:
                        System.out.println("Opzione non valida!");
                }
                
            } catch (InputMismatchException e) {
                System.err.println("ERRORE: Inserire solo numeri validi!");
                scanner.nextLine(); // Pulisce il buffer
                // TODO: Usare LogBack per logging
                // logger.error("Input non valido nel menu: " + e.getMessage());
            }
        }
        
        scanner.close();
    }
    
    /**
     * Calcola i km/litro utilizzando numeri interi
     */
    private static void calcoloConInteri() {
        System.out.println("\n=== CALCOLO CON INTERI ===");
        
        try {
            // Input dei dati
            System.out.print("Inserisci i chilometri percorsi (numero intero): ");
            int km = scanner.nextInt();
            
            System.out.print("Inserisci i litri consumati (numero intero): ");
            int litri = scanner.nextInt();
            
            // Verifica divisione per zero
            if (litri == 0) {
                throw new ArithmeticException("Divisione per zero: impossibile calcolare km/litro con 0 litri consumati!");
            }
            
            // Calcolo con interi - risultato sarà un intero (divisione intera)
            int kmPerLitroIntero = km / litri;
            
            // Calcolo con casting a double per avere il risultato preciso
            double kmPerLitroDouble = (double) km / litri;
            
            // Stampa risultati
            System.out.println("\n--- RISULTATI CON INTERI ---");
            System.out.println("Km percorsi: " + km);
            System.out.println("Litri consumati: " + litri);
            System.out.println("Km/litro (divisione intera): " + kmPerLitroIntero);
            System.out.println("Km/litro (con casting a double): " + String.format("%.2f", kmPerLitroDouble));
            
            // Spiegazione del comportamento
            System.out.println("\nNOTA: La divisione intera tronca la parte decimale!");
            
        } catch (ArithmeticException e) {
            System.err.println("ERRORE ARITMETICO: " + e.getMessage());
            // TODO: Usare LogBack per logging
            // logger.error("Errore di divisione per zero con interi: " + e.getMessage());
            
        } catch (InputMismatchException e) {
            System.err.println("ERRORE: Inserire solo numeri interi!");
            scanner.nextLine(); // Pulisce il buffer
            // TODO: Usare LogBack per logging
            // logger.error("Input non valido per interi: " + e.getMessage());
            
        } catch (Exception e) {
            System.err.println("ERRORE GENERICO: " + e.getMessage());
            // TODO: Usare LogBack per logging
            // logger.error("Errore generico nel calcolo con interi: " + e.getMessage());
        }
    }
    
    /**
     * Calcola i km/litro utilizzando numeri double
     */
    private static void calcoloConDouble() {
        System.out.println("\n=== CALCOLO CON DOUBLE ===");
        
        try {
            // Input dei dati
            System.out.print("Inserisci i chilometri percorsi (numero decimale): ");
            double km = scanner.nextDouble();
            
            System.out.print("Inserisci i litri consumati (numero decimale): ");
            double litri = scanner.nextDouble();
            
            // Verifica divisione per zero
            if (litri == 0.0) {
                throw new ArithmeticException("Divisione per zero: impossibile calcolare km/litro con 0 litri consumati!");
            }
            
            // Verifica anche per valori molto piccoli che potrebbero causare problemi
            if (Math.abs(litri) < 0.0001) {
                throw new ArithmeticException("Valore troppo piccolo per i litri: potrebbe causare risultati imprecisi!");
            }
            
            // Calcolo con double
            double kmPerLitro = km / litri;
            
            // Stampa risultati
            System.out.println("\n--- RISULTATI CON DOUBLE ---");
            System.out.println("Km percorsi: " + km);
            System.out.println("Litri consumati: " + litri);
            System.out.println("Km/litro: " + String.format("%.4f", kmPerLitro));
            
            // Controllo per risultati speciali
            if (Double.isInfinite(kmPerLitro)) {
                System.out.println("ATTENZIONE: Il risultato è infinito!");
            } else if (Double.isNaN(kmPerLitro)) {
                System.out.println("ATTENZIONE: Il risultato non è un numero valido!");
            }
            
            // Valutazione dell'efficienza
            valutaEfficienza(kmPerLitro);
            
        } catch (ArithmeticException e) {
            System.err.println("ERRORE ARITMETICO: " + e.getMessage());
            // TODO: Usare LogBack per logging
            // logger.error("Errore di divisione per zero con double: " + e.getMessage());
            
        } catch (InputMismatchException e) {
            System.err.println("ERRORE: Inserire solo numeri decimali validi!");
            scanner.nextLine(); // Pulisce il buffer
            // TODO: Usare LogBack per logging
            // logger.error("Input non valido per double: " + e.getMessage());
            
        } catch (Exception e) {
            System.err.println("ERRORE GENERICO: " + e.getMessage());
            // TODO: Usare LogBack per logging
            // logger.error("Errore generico nel calcolo con double: " + e.getMessage());
        }
    }
    
    /**
     * Valuta l'efficienza del consumo carburante
     * @param kmPerLitro I km percorsi per litro
     */
    private static void valutaEfficienza(double kmPerLitro) {
        System.out.println("\n--- VALUTAZIONE EFFICIENZA ---");
        
        if (kmPerLitro >= 20) {
            System.out.println("🟢 Ottima efficienza! Consumo molto basso.");
        } else if (kmPerLitro >= 15) {
            System.out.println("🟡 Buona efficienza. Consumo nella media.");
        } else if (kmPerLitro >= 10) {
            System.out.println("🟠 Efficienza media. Si può migliorare.");
        } else if (kmPerLitro > 0) {
            System.out.println("🔴 Bassa efficienza. Consumo elevato.");
        } else {
            System.out.println("❌ Valore non valido per la valutazione.");
        }
    }
}

/*
 * NOTE PER L'ESECUZIONE:
 * 
 * 1. Compilare: javac Esercizio2_CalcoloKmLitro.java
 * 2. Eseguire: java Esercizio2_CalcoloKmLitro
 * 
 * ESEMPI DI TEST:
 * 
 * CON INTERI:
 * - Km: 100, Litri: 5 -> Risultato: 20 km/litro
 * - Km: 150, Litri: 7 -> Risultato: 21 km/litro (divisione intera), 21.43 (con casting)
 * - Km: 100, Litri: 0 -> Eccezione ArithmeticException
 * 
 * CON DOUBLE:
 * - Km: 123.5, Litri: 6.2 -> Risultato: 19.9194 km/litro
 * - Km: 200.0, Litri: 0.0 -> Eccezione ArithmeticException
 * - Km: 100.0, Litri: 0.00001 -> Eccezione per valore troppo piccolo
 * 
 * DIFFERENZE TRA INTERI E DOUBLE:
 * - Gli interi eseguono divisione intera (troncano i decimali)
 * - I double mantengono la precisione decimale
 * - I double possono gestire valori frazionari
 * - I double possono produrre Infinity o NaN in casi speciali
 * 
 * EXTRA - LOGBACK:
 * Per utilizzare LogBack, aggiungere le dipendenze al progetto e
 * sostituire i commenti TODO con il codice di logging appropriato.
 */