/**
 * Esercizio #3 - Classe ContoCorrente refactorizzata
 * 
 * Classe che rappresenta un conto corrente con gestione delle eccezioni
 * personalizzate per operazioni bancarie non valide.
 */
public class ContoCorrente {
    
    // Attributi protetti per permettere l'accesso alle sottoclassi
    protected String titolare;
    protected int nMovimenti;
    protected final int maxMovimenti = 50;
    protected double saldo;
    
    /**
     * Costruttore della classe ContoCorrente
     * @param titolare Il nome del titolare del conto
     * @param saldo Il saldo iniziale del conto
     */
    public ContoCorrente(String titolare, double saldo) {
        this.titolare = titolare;
        this.saldo = saldo;
        this.nMovimenti = 0;
    }
    
    /**
     * Metodo per prelevare denaro dal conto
     * MODIFICATO: Ora lancia BancaException se il saldo diventa negativo
     * 
     * @param x L'importo da prelevare
     * @throws BancaException Se il prelievo porta il saldo sotto zero
     */
    public void preleva(double x) throws BancaException {
        // Calcola il nuovo saldo dopo il prelievo
        double nuovoSaldo;
        
        if (nMovimenti < maxMovimenti) {
            nuovoSaldo = saldo - x;
        } else {
            // Dopo il limite di movimenti, si applica una commissione di 0.50€
            nuovoSaldo = saldo - x - 0.50;
        }
        
        // Verifica se il saldo diventa negativo
        if (nuovoSaldo < 0) {
            // Incrementa comunque il contatore dei movimenti come richiesto
            nMovimenti++;
            
            // Lancia l'eccezione personalizzata
            throw new BancaException("il conto è in rosso");
        }
        
        // Se tutto è ok, aggiorna il saldo e incrementa i movimenti
        saldo = nuovoSaldo;
        nMovimenti++;
        
        System.out.println("Prelievo di €" + x + " effettuato con successo.");
        System.out.println("Nuovo saldo: €" + String.format("%.2f", saldo));
        System.out.println("Movimenti effettuati: " + nMovimenti + "/" + maxMovimenti);
    }
    
    /**
     * Metodo per depositare denaro nel conto
     * @param x L'importo da depositare
     */
    public void deposita(double x) {
        if (x <= 0) {
            System.out.println("ERRORE: L'importo da depositare deve essere positivo!");
            return;
        }
        
        if (nMovimenti < maxMovimenti) {
            saldo = saldo + x;
        } else {
            // Dopo il limite di movimenti, si applica una commissione di 0.50€
            saldo = saldo + x - 0.50;
        }
        
        nMovimenti++;
        
        System.out.println("Deposito di €" + x + " effettuato con successo.");
        System.out.println("Nuovo saldo: €" + String.format("%.2f", saldo));
        System.out.println("Movimenti effettuati: " + nMovimenti + "/" + maxMovimenti);
    }
    
    /**
     * Restituisce il saldo corrente del conto
     * @return Il saldo del conto
     */
    public double restituisciSaldo() {
        return saldo;
    }
    
    /**
     * Restituisce il titolare del conto
     * @return Il nome del titolare
     */
    public String getTitolare() {
        return titolare;
    }
    
    /**
     * Restituisce il numero di movimenti effettuati
     * @return Il numero di movimenti
     */
    public int getNumeroMovimenti() {
        return nMovimenti;
    }
    
    /**
     * Restituisce il numero massimo di movimenti consentiti
     * @return Il numero massimo di movimenti
     */
    public int getMaxMovimenti() {
        return maxMovimenti;
    }
    
    /**
     * Stampa le informazioni del conto
     */
    public void stampaInfo() {
        System.out.println("\n=== INFORMAZIONI CONTO CORRENTE ===");
        System.out.println("Titolare: " + titolare);
        System.out.println("Saldo: €" + String.format("%.2f", saldo));
        System.out.println("Movimenti: " + nMovimenti + "/" + maxMovimenti);
        
        if (nMovimenti >= maxMovimenti) {
            System.out.println("⚠️  ATTENZIONE: Limite movimenti raggiunto! Commissione di €0.50 per ogni operazione.");
        }
        
        if (saldo < 0) {
            System.out.println("🔴 CONTO IN ROSSO!");
        } else if (saldo < 100) {
            System.out.println("🟡 Saldo basso");
        } else {
            System.out.println("🟢 Saldo positivo");
        }
    }
}

/*
 * MODIFICHE APPORTATE RISPETTO ALLA VERSIONE ORIGINALE:
 * 
 * 1. Aggiunto throws BancaException al metodo preleva()
 * 2. Implementata la logica per verificare se il saldo diventa negativo
 * 3. Se il saldo diventa negativo:
 *    - Incrementa comunque nMovimenti (come richiesto)
 *    - Lancia BancaException con messaggio "il conto è in rosso"
 * 4. Cambiati gli attributi da private a protected per l'accesso dalle sottoclassi
 * 5. Aggiunti metodi getter per accedere agli attributi
 * 6. Aggiunto metodo deposita() per completezza
 * 7. Aggiunto metodo stampaInfo() per visualizzare lo stato del conto
 * 8. Migliorata la formattazione dell'output
 */