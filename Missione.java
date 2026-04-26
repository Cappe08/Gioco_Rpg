public class Missione {

    private String descrizione;
    private boolean completata;
    private Indizio ricompensa;

    public Missione(String descrizione, Indizio ricompensa){
        this.descrizione = descrizione;
        this.completata = false;
        this.ricompensa = ricompensa;
    }

    public String getDescrizione(){
        return descrizione;
    }

    public boolean isCompletata(){
        return completata;
    }

    /**
     * Avvia la missione stampando l'obiettivo a schermo.
     */
    public void avviaMissione(){
        System.out.println("*** NUOVA MISSIONE ***");
        System.out.println("Obiettivo: " + descrizione);
    }

    /**
     * Segna la missione come completata.
     */
    public void completaMissione(){
        completata = true;
        System.out.println("Missione completata: " + descrizione);
    }

    /**
     * Restituisce la ricompensa se la missione e stata completata,
     * altrimenti avvisa che non e ancora finita.
     */
    public Indizio daiRicompensa(){
        if(completata){
            System.out.println("Ricevi come ricompensa: " + ricompensa.getNome());
            return ricompensa;
        } else {
            System.out.println("Devi prima completare la missione!");
            return null;
        }
    }
}