/**
 * Eccezione custom per gestire errori logici nel gioco (es. statistiche troppo basse,
 * inventario pieno, azioni non valide).
 */
public class RPGException extends Exception {
    public RPGException(String messaggio){
        super(messaggio);
    }
}