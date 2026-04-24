/**
 * Gestisce la lettura dell'input utente dal terminale.
 *
 * <p><b>Responsabilita' (Single Responsibility):</b> convertire
 * i caratteri digitati dal giocatore in valori del tipo
 * {@link Direzione}, utilizzando esclusivamente la classe
 * {@link Leggi} fornita. Non contiene logica di movimento ne'
 * di gioco.</p>
 *
 * <p><b>Design scelto:</b> il metodo {@link #leggiDirezione()}
 * lancia una {@code IllegalArgumentException} quando l'input
 * non e' riconosciuto, rendendo esplicito l'errore. Per non
 * interrompere il flusso del gioco, il metodo
 * {@link #leggiDirezioneConRetry()} richiede ripetutamente
 * l'input finche' non ne riceve uno valido. In questo modo
 * {@link Movimento} rimane indipendente dalla sorgente
 * dell'input e la gestione degli errori e' centralizzata.</p>
 *
 * <p><b>Alternative possibili (solo commentate):</b></p>
 * <ul>
 *   <li><b>Scanner invece di Leggi:</b> si potrebbe usare
 *       {@code java.util.Scanner}, ma il progetto richiede
 *       l'utilizzo di {@link Leggi} gia' esistente.</li>
 *   <li><b>Ritorno di {@code null}:</b> invece di lanciare
 *       un'eccezione, si potrebbe restituire {@code null} e
 *       lasciare al chiamante il compito di verificare il
 *       risultato. Questo evita le eccezioni ma rende il codice
 *       meno esplicito e piu' soggetto a {@code NullPointerException}
 *       se il controllo viene dimenticato.</li>
 * </ul>
 */
public class GestoreInput {

    /**
     * Richiede all'utente una direzione e la converte nell'enum
     * corrispondente.
     *
     * @return il {@link Direzione} scelto dal giocatore
     * @throws IllegalArgumentException se il carattere inserito
     *         non corrisponde a nessuna direzione valida
     */
    public Direzione leggiDirezione() {
        System.out.print("Dove vuoi andare? (n=su, s=giu, e=dx, o=sx): ");
        char c = Leggi.unChar();

        switch (c) {
            case 'w':
            case 'W':
                return Direzione.NORD;
            case 'a':
            case 'A':
                return Direzione.SUD;
            case 'd':
            case 'D':
                return Direzione.EST;
            case 's':
            case 'S':
                return Direzione.OVEST;
            default:
                throw new IllegalArgumentException(
                    "Direzione non riconosciuta. Usa w, a, s, d.");
        }
    }

    /**
     * Richiede una direzione all'utente e ripete la richiesta
     * finche' non viene fornito un input valido.
     *
     * <p>Questo metodo garantisce che il flusso di gioco non
     * venga mai interrotto da un errore di input: l'utente
     * viene semplicemente invitato a riprovare.</p>
     *
     * @return il {@link Direzione} scelto dal giocatore (sempre valido)
     */
    public Direzione leggiDirezioneConRetry() {
        while (true) {
            try {
                return leggiDirezione();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                // Il ciclo continua, permettendo all'utente di riprovare
            }
        }
    }
}

