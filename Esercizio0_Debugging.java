/**
 * Esercizio #0 - Debugging con Breakpoint
 * 
 * Questo programma dimostra l'uso del debugger con breakpoint.
 * Impostare breakpoint sulle righe indicate nei commenti per
 * osservare l'esecuzione step-by-step.
 */
public class Esercizio0_Debugging {
    
    public static void main(String[] args) {
        System.out.println("Inizio programma di debugging");
        
        // BREAKPOINT 1: Impostare qui per vedere l'inizializzazione delle variabili
        int numero1 = 10;
        int numero2 = 5;
        
        System.out.println("Numero1: " + numero1 + ", Numero2: " + numero2);
        
        // BREAKPOINT 2: Impostare qui per vedere il calcolo della somma
        int somma = calcolaSomma(numero1, numero2);
        System.out.println("Somma: " + somma);
        
        // BREAKPOINT 3: Impostare qui per vedere il calcolo del prodotto
        int prodotto = calcolaProdotto(numero1, numero2);
        System.out.println("Prodotto: " + prodotto);
        
        // BREAKPOINT 4: Impostare qui per vedere l'iterazione del ciclo
        System.out.println("Conteggio da 1 a 5:");
        for (int i = 1; i <= 5; i++) {
            // BREAKPOINT 5: Impostare qui per vedere ogni iterazione
            System.out.println("Iterazione: " + i);
            int quadrato = i * i;
            System.out.println("Quadrato di " + i + " = " + quadrato);
        }
        
        // BREAKPOINT 6: Impostare qui per vedere la fine del programma
        System.out.println("Fine programma di debugging");
    }
    
    /**
     * Metodo per calcolare la somma di due numeri
     * BREAKPOINT 7: Impostare qui per vedere l'esecuzione del metodo
     */
    public static int calcolaSomma(int a, int b) {
        int risultato = a + b;
        return risultato; // BREAKPOINT 8: Impostare qui per vedere il valore di ritorno
    }
    
    /**
     * Metodo per calcolare il prodotto di due numeri
     * BREAKPOINT 9: Impostare qui per vedere l'esecuzione del metodo
     */
    public static int calcolaProdotto(int a, int b) {
        int risultato = a * b;
        return risultato; // BREAKPOINT 10: Impostare qui per vedere il valore di ritorno
    }
}

/*
 * ISTRUZIONI PER IL DEBUGGING:
 * 
 * 1. Aprire questo file nell'IDE (Eclipse, IntelliJ, VS Code, etc.)
 * 2. Impostare breakpoint cliccando sul margine sinistro delle righe indicate
 * 3. Avviare il programma in modalità debug (Debug As > Java Application)
 * 4. Utilizzare i controlli del debugger:
 *    - Step Over (F6): Esegue la riga corrente
 *    - Step Into (F5): Entra nei metodi chiamati
 *    - Step Return (F7): Esce dal metodo corrente
 *    - Resume (F8): Continua l'esecuzione fino al prossimo breakpoint
 * 5. Osservare le variabili nel pannello Variables/Watch
 * 6. Utilizzare la finestra Debug per vedere lo stack delle chiamate
 */