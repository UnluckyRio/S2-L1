/**
 * Esercizio #3 - Classe ContoOnLine refactorizzata
 * 
 * Sottoclasse di ContoCorrente che rappresenta un conto online
 * con limite massimo di prelievo e gestione eccezioni personalizzate.
 */
public class ContoOnLine extends ContoCorrente {
    
    private double maxPrelievo;
    
    /**
     * Costruttore della classe ContoOnLine
     * @param titolare Il nome del titolare del conto
     * @param saldo Il saldo iniziale del conto
     * @param maxP Il limite massimo di prelievo per operazione
     */
    public ContoOnLine(String titolare, double saldo, double maxP) {
        super(titolare, saldo);
        this.maxPrelievo = maxP;
    }
    
    /**
     * Stampa le informazioni complete del conto online
     * MODIFICATO: Corretto l'accesso agli attributi protetti
     */
    public void stampaSaldo() {
        System.out.println("\n=== INFORMAZIONI CONTO ONLINE ===");
        System.out.println("Titolare: " + titolare);
        System.out.println("Saldo: €" + String.format("%.2f", saldo));
        System.out.println("Num movimenti: " + nMovimenti);
        System.out.println("Massimo movimenti: " + maxMovimenti);
        System.out.println("Massimo prelievo possibile: €" + String.format("%.2f", maxPrelievo));
        
        // Indicatori di stato
        if (saldo < 0) {
            System.out.println("🔴 CONTO IN ROSSO!");
        } else if (saldo < 100) {
            System.out.println("🟡 Saldo basso");
        } else {
            System.out.println("🟢 Saldo positivo");
        }
        
        if (nMovimenti >= maxMovimenti) {
            System.out.println("⚠️  ATTENZIONE: Limite movimenti raggiunto! Commissione di €0.50 per ogni operazione.");
        }
    }
    
    /**
     * Metodo per prelevare denaro dal conto online
     * MODIFICATO: Ora lancia BancaException se x > maxPrelievo
     * 
     * @param x L'importo da prelevare
     * @throws BancaException Se il prelievo supera il limite massimo o porta il saldo sotto zero
     */
    @Override
    public void preleva(double x) throws BancaException {
        // Verifica se l'importo supera il limite massimo di prelievo
        if (x > maxPrelievo) {
            // Lancia l'eccezione personalizzata come richiesto
            throw new BancaException("il prelievo non è disponibile");
        }
        
        // Se l'importo è valido, chiama il metodo della superclasse
        // che gestirà anche il controllo del saldo negativo
        super.preleva(x);
        
        System.out.println("Prelievo online completato. Limite prelievo: €" + String.format("%.2f", maxPrelievo));
    }
    
    /**
     * Restituisce il limite massimo di prelievo
     * @return Il limite massimo di prelievo
     */
    public double getMaxPrelievo() {
        return maxPrelievo;
    }
    
    /**
     * Imposta un nuovo limite massimo di prelievo
     * @param nuovoLimite Il nuovo limite di prelievo
     */
    public void setMaxPrelievo(double nuovoLimite) {
        if (nuovoLimite > 0) {
            this.maxPrelievo = nuovoLimite;
            System.out.println("Limite di prelievo aggiornato a: €" + String.format("%.2f", maxPrelievo));
        } else {
            System.out.println("ERRORE: Il limite di prelievo deve essere positivo!");
        }
    }
    
    /**
     * Verifica se un importo può essere prelevato
     * @param importo L'importo da verificare
     * @return true se il prelievo è possibile, false altrimenti
     */
    public boolean isPrelievoPossibile(double importo) {
        return importo <= maxPrelievo && importo <= saldo;
    }
    
    /**
     * Stampa le informazioni complete del conto (override del metodo della superclasse)
     */
    @Override
    public void stampaInfo() {
        stampaSaldo(); // Usa il metodo specifico per il conto online
    }
}

/*
 * MODIFICHE APPORTATE RISPETTO ALLA VERSIONE ORIGINALE:
 * 
 * 1. Aggiunto @Override al metodo preleva() per chiarire che sovrascrive il metodo della superclasse
 * 2. Aggiunto throws BancaException al metodo preleva()
 * 3. Implementata la logica per verificare se x > maxPrelievo
 * 4. Se x > maxPrelievo:
 *    - Lancia BancaException con messaggio "il prelievo non è disponibile"
 * 5. Se x <= maxPrelievo:
 *    - Chiama super.preleva(x) che gestirà anche il controllo del saldo negativo
 * 6. Corretti gli accessi agli attributi della superclasse (ora sono protected)
 * 7. Aggiunti metodi getter e setter per maxPrelievo
 * 8. Aggiunto metodo isPrelievoPossibile() per verifiche preventive
 * 9. Migliorata la formattazione dell'output
 * 10. Aggiunto override del metodo stampaInfo()
 * 
 * COMPORTAMENTO DELLE ECCEZIONI:
 * - Se x > maxPrelievo: BancaException con "il prelievo non è disponibile"
 * - Se x <= maxPrelievo ma il saldo diventa negativo: BancaException con "il conto è in rosso"
 * - Entrambe le eccezioni devono essere gestite nel codice chiamante
 */