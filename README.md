# EPICODE S2-L1 - Esercizi su Debugging, Gestione Errori e Eccezioni

## 📋 Descrizione

Questa repository contiene gli esercizi per la lezione S2-L1 di EPICODE, focalizzati su:
- **Debugging** con breakpoint e esecuzione step-by-step
- **Gestione degli errori** con try-catch
- **Eccezioni personalizzate** in Java
- **Logging** con LogBack

## 📁 Struttura del Progetto

```
S2-L1/
├── Esercizio0_Debugging.java          # Esercizio #0 - Debugging
├── Esercizio1_ArrayGestioneErrori.java # Esercizio #1 - Array con gestione errori
├── Esercizio2_CalcoloKmLitro.java      # Esercizio #2 - Calcolo km/litro
├── BancaException.java                 # Esercizio #3 - Eccezione personalizzata
├── ContoCorrente.java                  # Esercizio #3 - Classe conto corrente
├── ContoOnLine.java                    # Esercizio #3 - Classe conto online
├── Esercizio3_Main.java                # Esercizio #3 - Programma principale
├── logback.xml                         # Configurazione LogBack
├── pom.xml                             # Configurazione Maven
└── README.md                           # Questo file
```

## 🚀 Come Eseguire gli Esercizi

### Opzione 1: Compilazione Manuale (Senza Maven)

#### Prerequisiti
- Java JDK 11 o superiore
- Variabili d'ambiente JAVA_HOME e PATH configurate

#### Compilazione ed Esecuzione

```bash
# Compilare tutti i file Java
javac *.java

# Eseguire Esercizio #0 (Debugging)
java Esercizio0_Debugging

# Eseguire Esercizio #1 (Array)
java Esercizio1_ArrayGestioneErrori

# Eseguire Esercizio #2 (Km/Litro)
java Esercizio2_CalcoloKmLitro

# Eseguire Esercizio #3 (Sistema Bancario)
java Esercizio3_Main
```

### Opzione 2: Con Maven (Raccomandato per LogBack)

#### Prerequisiti
- Java JDK 11 o superiore
- Apache Maven 3.6 o superiore

#### Setup e Esecuzione

```bash
# Compilare il progetto
mvn compile

# Eseguire l'esercizio principale (Esercizio #3)
mvn exec:java

# Eseguire un esercizio specifico
mvn exec:java -Pesercizio0  # Debugging
mvn exec:java -Pesercizio1  # Array
mvn exec:java -Pesercizio2  # Km/Litro
mvn exec:java -Pesercizio3  # Sistema Bancario

# Creare JAR eseguibile
mvn clean package
java -jar target/s2-l1-esercizi-1.0.0.jar
```

## 📚 Descrizione degli Esercizi

### 🔧 Esercizio #0 - Debugging
**File:** `Esercizio0_Debugging.java`

**Obiettivo:** Prendere dimestichezza con il debugger

**Caratteristiche:**
- Programma con diversi punti di breakpoint suggeriti
- Esempi di variabili, cicli e chiamate a metodi
- Istruzioni dettagliate per l'uso del debugger

**Come usare:**
1. Aprire il file nell'IDE
2. Impostare breakpoint sulle righe indicate nei commenti
3. Avviare in modalità debug
4. Utilizzare Step Over, Step Into, Step Return

### 🔢 Esercizio #1 - Gestione Array con Errori
**File:** `Esercizio1_ArrayGestioneErrori.java`

**Obiettivo:** Gestire errori di violazione dei limiti dell'array

**Caratteristiche:**
- Array di 5 interi con valori casuali (1-10)
- Input utente per modificare valori
- Gestione `ArrayIndexOutOfBoundsException`
- Gestione `InputMismatchException`
- Loop fino a inserimento del valore 0

**Test suggeriti:**
- Inserire posizioni valide (0-4)
- Testare posizioni non valide (-1, 5, 10)
- Inserire caratteri non numerici

### ⛽ Esercizio #2 - Calcolo Km/Litro
**File:** `Esercizio2_CalcoloKmLitro.java`

**Obiettivo:** Gestire divisione per zero con try-catch

**Caratteristiche:**
- Calcolo con numeri interi e double
- Gestione `ArithmeticException` per divisione per zero
- Confronto comportamento interi vs double
- Valutazione efficienza carburante

**Test suggeriti:**
- Km: 100, Litri: 5 → 20 km/litro
- Km: 100, Litri: 0 → Eccezione
- Confrontare risultati interi vs double

### 🏦 Esercizio #3 - Sistema Bancario con Eccezioni
**File:** `BancaException.java`, `ContoCorrente.java`, `ContoOnLine.java`, `Esercizio3_Main.java`

**Obiettivo:** Implementare eccezioni personalizzate per operazioni bancarie

**Caratteristiche:**
- `BancaException` personalizzata
- `ContoCorrente` con controllo saldo negativo
- `ContoOnLine` con limite prelievo
- Menu interattivo completo
- Test automatico delle eccezioni

**Eccezioni gestite:**
- "il conto è in rosso" - quando il saldo diventa negativo
- "il prelievo non è disponibile" - quando si supera il limite

## 📊 Logging con LogBack

### Configurazione
Il file `logback.xml` configura il sistema di logging con:
- **Console:** Solo WARNING ed ERROR
- **File generale:** `logs/esercizi.log` (tutti i livelli)
- **File errori:** `logs/errori.log` (solo ERROR)
- **File bancario:** `logs/operazioni_bancarie.log` (operazioni specifiche)

### Utilizzo nei Programmi
Per abilitare il logging, aggiungere nelle classi:

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

private static final Logger logger = LoggerFactory.getLogger(NomeClasse.class);

// Utilizzo
logger.error("Messaggio di errore");
logger.warn("Messaggio di warning");
logger.info("Messaggio informativo");
logger.debug("Messaggio di debug");
```

### Livelli di Log
- **ERROR:** Errori gravi che richiedono attenzione
- **WARN:** Situazioni anomale ma gestibili
- **INFO:** Informazioni generali sull'esecuzione
- **DEBUG:** Informazioni dettagliate per il debugging

## 🧪 Test e Debugging

### Test Consigliati per Ogni Esercizio

#### Esercizio #1 (Array)
- ✅ Inserire valori in posizioni valide (0-4)
- ❌ Testare posizioni negative (-1, -5)
- ❌ Testare posizioni oltre il limite (5, 10, 100)
- ❌ Inserire testo invece di numeri
- ✅ Inserire 0 per uscire

#### Esercizio #2 (Km/Litro)
- ✅ Calcoli normali (es. 100 km, 5 litri)
- ❌ Divisione per zero (0 litri)
- ✅ Confronto interi vs double
- ❌ Input non numerici

#### Esercizio #3 (Sistema Bancario)
- ❌ Prelievo che porta il conto in rosso
- ❌ Prelievo oltre il limite del conto online
- ✅ Prelievi e depositi normali
- ✅ Visualizzazione stato conti
- ✅ Test automatico delle eccezioni

### Debugging nell'IDE

#### IntelliJ IDEA
1. Cliccare sul margine sinistro per impostare breakpoint
2. Tasto destro → "Debug 'NomeClasse.main()'"
3. Utilizzare i controlli: Step Over (F8), Step Into (F7), Resume (F9)

#### Eclipse
1. Doppio clic sul margine sinistro per breakpoint
2. Tasto destro → "Debug As" → "Java Application"
3. Utilizzare: Step Over (F6), Step Into (F5), Resume (F8)

#### VS Code
1. Installare "Extension Pack for Java"
2. Cliccare sul margine per breakpoint
3. F5 per avviare il debug
4. F10 (Step Over), F11 (Step Into), F5 (Continue)

## ⚠️ Risoluzione Problemi Comuni

### Errori di Compilazione
```bash
# Se manca JAVA_HOME
export JAVA_HOME=/path/to/java
export PATH=$JAVA_HOME/bin:$PATH

# Su Windows
set JAVA_HOME=C:\Program Files\Java\jdk-11
set PATH=%JAVA_HOME%\bin;%PATH%
```

### Errori Maven
```bash
# Pulire e ricompilare
mvn clean compile

# Verificare versione Java
mvn -version
java -version
```

### LogBack non Funziona
1. Verificare che `logback.xml` sia nel classpath
2. Con Maven: spostare in `src/main/resources/`
3. Verificare le dipendenze nel `pom.xml`

## 📖 Risorse Aggiuntive

### Documentazione
- [Oracle Java Documentation](https://docs.oracle.com/en/java/)
- [LogBack Documentation](http://logback.qos.ch/documentation.html)
- [Maven Getting Started](https://maven.apache.org/guides/getting-started/)

### Best Practices
- Sempre gestire le eccezioni specifiche prima di quelle generiche
- Utilizzare messaggi di errore informativi
- Non catturare eccezioni che non si possono gestire
- Utilizzare il logging appropriato per ogni livello di severità

## 👨‍💻 Autore

**Studente EPICODE**  
Corso: Full Stack Developer  
Lezione: S2-L1 - Debugging, Gestione Errori e Eccezioni

---

## 📝 Note

- Tutti i programmi sono stati testati con Java 11+
- I file di log vengono creati automaticamente nella cartella `logs/`
- Per domande o problemi, consultare la documentazione ufficiale Java
- Gli esercizi sono progressivi: iniziare dall'Esercizio #0 per familiarizzare con il debugging

**Buono studio! 🚀**