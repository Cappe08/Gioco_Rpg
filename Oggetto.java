public class Oggetto {
    private final String nome;
    private final String descrizione;
    private final int valore; // es. punti salute o danno
    private final boolean consumabile;

    public Oggetto(String nome, String descrizione, int valore) {
        this(nome, descrizione, valore, true);
    }

    public Oggetto(String nome, String descrizione, int valore, boolean consumabile) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.valore = valore;
        this.consumabile = consumabile;
    }

    public boolean isConsumabile() {
        return consumabile;
    }

    public String getNome() {
        return nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public int getValore() {
        return valore;
    }

    @Override
    public String toString() {
        return nome + " (" + descrizione + ") - valore: " + valore;
    }
}