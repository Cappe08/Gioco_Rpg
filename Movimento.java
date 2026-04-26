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
    public Movimento(Mappa mappa, int xIniziale, int yIniziale){
        this.mappa = mappa;
        if(!mappa.isAttraversabile(xIniziale, yIniziale)){
            throw new IllegalArgumentException(
                    "Posizione iniziale non valida: (" + xIniziale + "," + yIniziale + ")");
        }
        this.x = xIniziale;
        this.y = yIniziale;
        this.gestoreInput = new GestoreInput();
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    /**
     * Restituisce il luogo in cui si trova attualmente il giocatore.
     *
     * @return il {@link Luogo} corrente
     */
    public Luogo getLuogoCorrente(){
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
    public void muovi(){
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
    public void muovi(Direzione direzione){
        int nuovaX = x;
        int nuovaY = y;

        if(direzione == Direzione.NORD){
            nuovaY--;
        } else if(direzione == Direzione.SUD){
            nuovaY++;
        } else if(direzione == Direzione.EST){
            nuovaX++;
        } else if(direzione == Direzione.OVEST){
            nuovaX--;
        }

        if(mappa.isAttraversabile(nuovaX, nuovaY)){
            x = nuovaX;
            y = nuovaY;
            System.out.println("Ti sei spostato in: " + getLuogoCorrente().getNome());
        } else {
            System.out.println("Non puoi andare in quella direzione.");
        }
    }
}