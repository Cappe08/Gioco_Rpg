/**
 * Classe che rappresenta un oggetto raccoglibile nel gioco.
 *
 * <p><b>Responsabilità:</b> memorizzare nome, descrizione ed effetto (es. cura, arma).</p>
 *
 * <p><b>Design:</b> POJO immutabile come Luogo.java del compagno.</p>
 */
public class Oggetto {
    private final String nome;
    private final String descrizione;
    private final int valore; // es. punti salute o danno

    public Oggetto(String nome, String descrizione, int valore) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.valore = valore;
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