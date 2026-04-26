public class GestoreInput {

    /**
     * Richiede all'utente una direzione e la converte nell'enum
     * corrispondente.
     *
     * @return il {@link Direzione} scelto dal giocatore
     * @throws IllegalArgumentException se il carattere inserito
     *         non corrisponde a nessuna direzione valida
     */
    public Direzione leggiDirezione(){
        System.out.print("Dove vuoi andare? (w=su, s=giu, d=dx, a=sx): ");
        char c = Leggi.unChar();

        if(c == 'w' || c == 'W'){
            return Direzione.NORD;
        } else if(c == 's' || c == 'S'){
            return Direzione.SUD;
        } else if(c == 'd' || c == 'D'){
            return Direzione.EST;
        } else if(c == 'a' || c == 'A'){
            return Direzione.OVEST;
        } else {
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
    public Direzione leggiDirezioneConRetry(){
        while(true){
            try {
                return leggiDirezione();
            } catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
                // Il ciclo continua, permettendo all'utente di riprovare
            }
        }
    }
}