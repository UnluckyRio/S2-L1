import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Esercizio #3 - Classe Main per testare il sistema bancario
 * 
 * Programma principale che:
 * 1. Istanzia un ContoCorrente e un ContoOnLine
 * 2. Permette all'utente di effettuare prelievi
 * 3. Gestisce le eccezioni BancaException senza far crashare l'applicazione
 * 4. Usa LogBack per il logging degli errori (TODO)
 */
public class Esercizio3_Main {
    
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("=== ESERCIZIO #3 - SISTEMA BANCARIO CON ECCEZIONI ===");
        
        // Istanziazione dei conti come richiesto
        ContoCorrente contoCorrente = new ContoCorrente("Mario Rossi", 1000.0);
        ContoOnLine contoOnLine = new ContoOnLine("Giulia Bianchi", 800.0, 500.0);
        
        System.out.println("\n🏦 Sistema bancario inizializzato!");
        System.out.println("📋 Conti creati:");
        System.out.println("   1. Conto Corrente - " + contoCorrente.getTitolare() + " (€" + contoCorrente.restituisciSaldo() + ")");
        System.out.println("   2. Conto Online - " + contoOnLine.getTitolare() + " (€" + contoOnLine.restituisciSaldo() + ", limite: €" + contoOnLine.getMaxPrelievo() + ")");
        
        boolean continua = true;
        
        while (continua) {
            try {
                System.out.println("\n" + "=".repeat(50));
                System.out.println("MENU PRINCIPALE");
                System.out.println("=".repeat(50));
                System.out.println("1. Preleva da Conto Corrente");
                System.out.println("2. Preleva da Conto Online");
                System.out.println("3. Deposita su Conto Corrente");
                System.out.println("4. Deposita su Conto Online");
                System.out.println("5. Visualizza stato Conto Corrente");
                System.out.println("6. Visualizza stato Conto Online");
                System.out.println("7. Test automatico eccezioni");
                System.out.println("0. Esci");
                System.out.print("\nScegli un'opzione: ");
                
                int scelta = scanner.nextInt();
                
                switch (scelta) {
                    case 1:
                        gestisciPrelievo(contoCorrente, "Conto Corrente");
                        break;
                    case 2:
                        gestisciPrelievo(contoOnLine, "Conto Online");
                        break;
                    case 3:
                        gestisciDeposito(contoCorrente, "Conto Corrente");
                        break;
                    case 4:
                        gestisciDeposito(contoOnLine, "Conto Online");
                        break;
                    case 5:
                        contoCorrente.stampaInfo();
                        break;
                    case 6:
                        contoOnLine.stampaInfo();
                        break;
                    case 7:
                        testAutomaticoEccezioni(contoCorrente, contoOnLine);
                        break;
                    case 0:
                        System.out.println("\n👋 Grazie per aver utilizzato il sistema bancario!");
                        continua = false;
                        break;
                    default:
                        System.out.println("❌ Opzione non valida! Riprova.");
                }
                
            } catch (InputMismatchException e) {
                System.err.println("❌ ERRORE: Inserire solo numeri per il menu!");
                scanner.nextLine(); // Pulisce il buffer
                // TODO: Usare LogBack per logging
                // logger.error("Input non valido nel menu principale: " + e.getMessage());
            }
        }
        
        scanner.close();
    }
    
    /**
     * Gestisce l'operazione di prelievo con gestione delle eccezioni
     * @param conto Il conto da cui prelevare
     * @param tipoConto Il tipo di conto (per i messaggi)
     */
    private static void gestisciPrelievo(ContoCorrente conto, String tipoConto) {
        try {
            System.out.println("\n💰 PRELIEVO DA " + tipoConto.toUpperCase());
            System.out.println("Saldo attuale: €" + String.format("%.2f", conto.restituisciSaldo()));
            
            if (conto instanceof ContoOnLine) {
                ContoOnLine contoOnline = (ContoOnLine) conto;
                System.out.println("Limite prelievo: €" + String.format("%.2f", contoOnline.getMaxPrelievo()));
            }
            
            System.out.print("Inserisci l'importo da prelevare: €");
            double importo = scanner.nextDouble();
            
            if (importo <= 0) {
                System.out.println("❌ L'importo deve essere positivo!");
                return;
            }
            
            // Tentativo di prelievo - può lanciare BancaException
            conto.preleva(importo);
            System.out.println("✅ Prelievo completato con successo!");
            
        } catch (BancaException e) {
            // Gestione dell'eccezione personalizzata
            System.err.println("🚫 ERRORE BANCARIO: " + e.getMessage());
            
            // Messaggi specifici basati sul tipo di errore
            if (e.getMessage().equals("il conto è in rosso")) {
                System.err.println("   💡 Suggerimento: Effettua un deposito prima di prelevare.");
                // TODO: Usare LogBack per logging
                // logger.warn("Tentativo di prelievo con saldo insufficiente su " + tipoConto + ": " + e.getMessage());
            } else if (e.getMessage().equals("il prelievo non è disponibile")) {
                System.err.println("   💡 Suggerimento: Riduci l'importo del prelievo o contatta la banca per aumentare il limite.");
                // TODO: Usare LogBack per logging
                // logger.warn("Tentativo di prelievo oltre il limite su " + tipoConto + ": " + e.getMessage());
            }
            
        } catch (InputMismatchException e) {
            System.err.println("❌ ERRORE: Inserire solo numeri decimali!");
            scanner.nextLine(); // Pulisce il buffer
            // TODO: Usare LogBack per logging
            // logger.error("Input non valido per importo prelievo: " + e.getMessage());
            
        } catch (Exception e) {
            System.err.println("❌ ERRORE GENERICO: " + e.getMessage());
            // TODO: Usare LogBack per logging
            // logger.error("Errore generico durante prelievo da " + tipoConto + ": " + e.getMessage());
        }
    }
    
    /**
     * Gestisce l'operazione di deposito
     * @param conto Il conto su cui depositare
     * @param tipoConto Il tipo di conto (per i messaggi)
     */
    private static void gestisciDeposito(ContoCorrente conto, String tipoConto) {
        try {
            System.out.println("\n💳 DEPOSITO SU " + tipoConto.toUpperCase());
            System.out.println("Saldo attuale: €" + String.format("%.2f", conto.restituisciSaldo()));
            System.out.print("Inserisci l'importo da depositare: €");
            
            double importo = scanner.nextDouble();
            
            if (importo <= 0) {
                System.out.println("❌ L'importo deve essere positivo!");
                return;
            }
            
            conto.deposita(importo);
            System.out.println("✅ Deposito completato con successo!");
            
        } catch (InputMismatchException e) {
            System.err.println("❌ ERRORE: Inserire solo numeri decimali!");
            scanner.nextLine(); // Pulisce il buffer
            // TODO: Usare LogBack per logging
            // logger.error("Input non valido per importo deposito: " + e.getMessage());
        }
    }
    
    /**
     * Esegue test automatici per dimostrare la gestione delle eccezioni
     * @param contoCorrente Il conto corrente da testare
     * @param contoOnLine Il conto online da testare
     */
    private static void testAutomaticoEccezioni(ContoCorrente contoCorrente, ContoOnLine contoOnLine) {
        System.out.println("\n🧪 TEST AUTOMATICO DELLE ECCEZIONI");
        System.out.println("=".repeat(40));
        
        // Test 1: Prelievo che porta il conto corrente in rosso
        System.out.println("\n📋 Test 1: Prelievo eccessivo da Conto Corrente");
        try {
            double saldoAttuale = contoCorrente.restituisciSaldo();
            double prelievoEccessivo = saldoAttuale + 100; // Più del saldo disponibile
            System.out.println("Tentativo di prelievo di €" + prelievoEccessivo + " (saldo: €" + saldoAttuale + ")");
            contoCorrente.preleva(prelievoEccessivo);
        } catch (BancaException e) {
            System.out.println("✅ Eccezione catturata correttamente: " + e.getMessage());
            // TODO: Usare LogBack per logging
            // logger.info("Test eccezione conto in rosso completato: " + e.getMessage());
        }
        
        // Test 2: Prelievo oltre il limite del conto online
        System.out.println("\n📋 Test 2: Prelievo oltre il limite del Conto Online");
        try {
            double limitePrelievo = contoOnLine.getMaxPrelievo();
            double prelievoOltreLimite = limitePrelievo + 100; // Oltre il limite
            System.out.println("Tentativo di prelievo di €" + prelievoOltreLimite + " (limite: €" + limitePrelievo + ")");
            contoOnLine.preleva(prelievoOltreLimite);
        } catch (BancaException e) {
            System.out.println("✅ Eccezione catturata correttamente: " + e.getMessage());
            // TODO: Usare LogBack per logging
            // logger.info("Test eccezione limite prelievo completato: " + e.getMessage());
        }
        
        // Test 3: Prelievo valido per dimostrare che il sistema funziona normalmente
        System.out.println("\n📋 Test 3: Prelievo valido dal Conto Online");
        try {
            double prelievalido = 50.0; // Importo sicuramente valido
            System.out.println("Tentativo di prelievo di €" + prelievalido);
            contoOnLine.preleva(prelievalido);
            System.out.println("✅ Prelievo completato senza eccezioni!");
        } catch (BancaException e) {
            System.out.println("❌ Eccezione inaspettata: " + e.getMessage());
        }
        
        System.out.println("\n🏁 Test automatico completato!");
        System.out.println("   Le eccezioni sono state gestite correttamente senza far crashare l'applicazione.");
    }
}

/*
 * CARATTERISTICHE IMPLEMENTATE:
 * 
 * 1. ✅ Istanziazione di ContoCorrente e ContoOnLine
 * 2. ✅ Interfaccia utente per effettuare prelievi
 * 3. ✅ Gestione completa delle eccezioni BancaException
 * 4. ✅ L'applicazione non crasha mai grazie ai try-catch
 * 5. ✅ Messaggi di errore informativi per l'utente
 * 6. ✅ Test automatico per dimostrare il funzionamento delle eccezioni
 * 7. ✅ Gestione di InputMismatchException per input non validi
 * 8. ✅ Menu interattivo completo
 * 9. ✅ Possibilità di visualizzare lo stato dei conti
 * 10. ✅ Funzionalità di deposito per ripristinare i saldi
 * 
 * ECCEZIONI GESTITE:
 * - BancaException con "il conto è in rosso" (da ContoCorrente)
 * - BancaException con "il prelievo non è disponibile" (da ContoOnLine)
 * - InputMismatchException per input non numerici
 * - Exception generica per altri errori imprevisti
 * 
 * TODO - LOGBACK:
 * Per implementare LogBack:
 * 1. Aggiungere dipendenze LogBack al progetto
 * 2. Creare file di configurazione logback.xml
 * 3. Sostituire i commenti TODO con chiamate al logger
 * 4. Configurare diversi livelli di log (ERROR, WARN, INFO)
 * 
 * ISTRUZIONI PER L'ESECUZIONE:
 * 1. Compilare tutti i file: javac *.java
 * 2. Eseguire: java Esercizio3_Main
 * 3. Seguire il menu interattivo per testare le funzionalità
 * 4. Utilizzare l'opzione 7 per il test automatico delle eccezioni
 */