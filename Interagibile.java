/**
 * Interfaccia per tutti gli elementi del gioco che prevedono un'interazione
 * complessa (Dialoghi, Eventi, Combattimenti).
 */
public interface Interagibile {
    /**
     * Avvia l'interazione con il protagonista.
     * @param tony Il protagonista che interagisce.
     * @return true se l'interazione ha portato a un combattimento o evento maggiore.
     */
    boolean interagisci(Protagonista tony);
}