/**
 * Esercizio #3 - Eccezione personalizzata per operazioni bancarie
 * 
 * Classe BancaException che estende Exception per gestire
 * errori specifici nelle operazioni bancarie.
 */
public class BancaException extends Exception {
    
    /**
     * Costruttore che accetta un messaggio di errore
     * @param messaggio Il messaggio descrittivo dell'errore
     */
    public BancaException(String messaggio) {
        // Richiama il costruttore della superclasse Exception
        super(messaggio);
    }
    
    /**
     * Costruttore che accetta un messaggio e una causa
     * @param messaggio Il messaggio descrittivo dell'errore
     * @param causa La causa dell'eccezione
     */
    public BancaException(String messaggio, Throwable causa) {
        super(messaggio, causa);
    }
    
    /**
     * Costruttore che accetta solo una causa
     * @param causa La causa dell'eccezione
     */
    public BancaException(Throwable causa) {
        super(causa);
    }
}

/*
 * NOTE:
 * 
 * Questa classe rappresenta un'eccezione personalizzata per operazioni bancarie.
 * Estende la classe Exception, rendendola una "checked exception" che deve
 * essere gestita esplicitamente con try-catch o dichiarata con throws.
 * 
 * I costruttori forniti permettono di:
 * 1. Creare un'eccezione con solo un messaggio
 * 2. Creare un'eccezione con messaggio e causa
 * 3. Creare un'eccezione con solo la causa
 * 
 * Questo approccio segue le best practices per la creazione di eccezioni personalizzate in Java.
 */