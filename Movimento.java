/**
 * Gestisce il movimento del personaggio giocante sulla {@link Mappa}.
 *
 * <p><b>Responsabilita' (Single Responsibility):</b> tenere traccia
 * della posizione corrente (x,y) del giocatore e applicare gli
 * spostamenti richiesti tramite l'enum {@link Direzione}.
 * La lettura dell'input da terminale e' stata estratta in
 * {@link GestoreInput}: in questo modo {@code Movimento} non dipende
 * piu' direttamente da {@link Leggi} e risulta facilmente testabile
 * con unit test (e' possibile chiamare {@code muovi(Direzione)}
 * passando direttamente la direzione desiderata, senza leggere
 * dall'utente).</p>
 *
 * <p><b>Design scelto:</b> il costruttore richiede solo la
 * {@link Mappa} e le coordinate di partenza. Il metodo
 * {@link #muovi()} delega la lettura a {@link GestoreInput} e
 * poi applica lo spostamento chiamando {@link #muovi(Direzione)}.
 * Questo pattern e' chiamato <i>Template Method</i> semplificato:
 * l'orchestrazione rimane in {@code muovi()}, mentre la logica pura
 * dello spostamento e' isolata in {@code muovi(Direzione)}.</p>
 *
 * <p><b>Alternative possibili (solo commentate):</b></p>
 * <ul>
 *   <li><b>Iniezione di GestoreInput:</b> si potrebbe passare
 *       {@link GestoreInput} al costruttore di {@code Movimento}
 *       invece di crearlo internamente. Questo renderebbe ancora
 *       piu' flessibile la classe (si potrebbe sostituire
 *       {@code GestoreInput} con una versione che legge da file
 *       o da rete), ma per semplicita' attuale la dipendenza e'
 *       istanziata direttamente.</li>
 *   <li><b>Ritorno del risultato:</b> {@code muovi(Direzione)}
 *       potrebbe restituire un {@code boolean} (true se lo
 *       spostamento e' avvenuto, false altrimenti) o un oggetto
 *       {@code RisultatoMovimento} con informazioni piu' dettagliate.
 *       Attualmente il risultato e' comunicato con {@code System.out}
 *       per semplicita'.</li>
 * </ul>
 */
public class Movimento {
    private int x;
    private int y;
    private final Mappa mappa;
    private final GestoreInput gestoreInput;

    /**
     * Crea il gestore del movimento posizionando il giocatore sulle
     * coordinate iniziali.
     *
     * @param mappa       la mappa di gioco (deve essere gia' popolata)
     * @param xIniziale   colonna di partenza
     * @param yIniziale   riga di partenza
     * @throws IllegalArgumentException se la posizione iniziale non
     *         e' attraversabile (fuori mappa o cella vuota)
     */
    public Movimento(Mappa mappa, int xIniziale, int yIniziale) {
        this.mappa = mappa;
        if (!mappa.isAttraversabile(xIniziale, yIniziale)) {
            throw new IllegalArgumentException(
                "Posizione iniziale non valida: (" + xIniziale + "," + yIniziale + ")");
        }
        this.x = xIniziale;
        this.y = yIniziale;
        this.gestoreInput = new GestoreInput();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * Restituisce il luogo in cui si trova attualmente il giocatore.
     *
     * @return il {@link Luogo} corrente
     */
    public Luogo getLuogoCorrente() {
        return mappa.getLuogo(x, y);
    }

    /**
     * Esegue un ciclo completo di movimento interagendo con l'utente:
     * <ol>
     *   <li>Stampa la posizione attuale.</li>
     *   <li>Legge la direzione tramite {@link GestoreInput},
     *       con retry automatico in caso di input non valido.</li>
     *   <li>Delega lo spostamento a {@link #muovi(Direzione)}.</li>
     * </ol>
     *
     * <p>Questo metodo e' adatto all'uso in un loop di gioco,
     * mentre {@link #muovi(Direzione)} e' ideale per i test
     * automatici.</p>
     */
    public void muovi() {
        System.out.println("Sei in: " + getLuogoCorrente());

        Direzione direzione = gestoreInput.leggiDirezioneConRetry();
        muovi(direzione);
    }

    /**
     * Applica uno spostamento nella direzione specificata.
     * Questo metodo <b>non</b> legge alcun input da terminale:
     * e' la parte puramente logica del movimento, testabile
     * in modo isolato.
     *
     * @param direzione la {@link Direzione} in cui muoversi
     */
    public void muovi(Direzione direzione) {
        int nuovaX = x;
        int nuovaY = y;

        switch (direzione) {
            case NORD:
                nuovaY--;
                break;
            case SUD:
                nuovaY++;
                break;
            case EST:
                nuovaX++;
                break;
            case OVEST:
                nuovaX--;
                break;
        }

        if (mappa.isAttraversabile(nuovaX, nuovaY)) {
            x = nuovaX;
            y = nuovaY;
            System.out.println("Ti sei spostato in: " + getLuogoCorrente().getNome());
        } else {
            System.out.println("Non puoi andare in quella direzione.");
        }
    }
}