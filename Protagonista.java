/**
 * Classe concreta che rappresenta il protagonista (Rory) del gioco.
 *
 * <p><b>Responsabilità:</b> gestire inventario, posizione (delegata a Movimento), stato del giocatore.</p>
 *
 * <p><b>Design:</b> estende Personaggio, composizione con Inventario (HAS-A).
 * Usa polimorfismo per override di parla().</p>
 */
public class Protagonista extends Personaggio {
    private final Inventario inventario;

    public Protagonista(String nome, String descrizione) {
        super(nome, descrizione, 100); // salute iniziale
        this.inventario = new Inventario();
    }

    public Inventario getInventario() {
        return inventario;
    }

    /**
     * Override: dialogo personalizzato del protagonista (per interazioni future).
     */
    @Override
    public String parla() {
        return "Sono Rory, l'avventuriero! Sto esplorando Dublino medievale per scoprire il mistero del castello.";
    }

    /**
     * Metodo specifico per raccogliere oggetti.
     */
    public void raccogli(Oggetto oggetto) {
        inventario.aggiungi(oggetto);
        System.out.println("Hai raccolto: " + oggetto);
    }

    /**
     * Stampa stato completo del protagonista.
     */
    public void stampaStato() {
        System.out.println("=== " + nome + " ===");
        System.out.println(descrizione);
        System.out.println("Salute: " + salute);
        System.out.println("Inventario: " + inventario);
    }
}