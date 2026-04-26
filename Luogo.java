public class Luogo {
    private final String nome;
    private final String descrizione;

    /**
     * Crea un nuovo luogo.
     *
     * @param nome        il nome visualizzato del luogo (es. "Taverna")
     * @param descrizione la descrizione testuale mostrata al giocatore
     */
    public Luogo(String nome, String descrizione){
        this.nome = nome;
        this.descrizione = descrizione;
    }

    public String getNome(){
        return nome;
    }

    public String getDescrizione(){
        return descrizione;
    }

    /**
     * Restituisce una rappresentazione leggibile del luogo.
     * Utile per essere stampata direttamente nel terminale.
     */
    @Override
    public String toString(){
        return nome + " - " + descrizione;
    }
}