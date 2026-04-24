/**
 * Rappresenta il protagonista del gioco (Rory).
 * Estende Personaggio e gestisce l'inventario degli oggetti.
 */
public class Protagonista extends Personaggio {

    private final Inventario inventario;

    public Protagonista(String nome, String descrizione){
        super(nome, descrizione, 100);
        this.inventario = new Inventario();
    }

    public Inventario getInventario() {
        return inventario;
    }

    /**
     * Dialogo del protagonista (usato per interazioni future).
     */
    @Override
    public String parla(){
        return "Sono Rory, l'avventuriero! Sto esplorando Dublino medievale per scoprire il mistero del castello.";
    }

    /**
     * Il protagonista raccoglie un oggetto e lo aggiunge all'inventario.
     */
    public void raccogli(Oggetto oggetto){
        inventario.aggiungi(oggetto);
        System.out.println("Hai raccolto: " + oggetto);
    }

    /**
     * Stampa tutte le informazioni sul protagonista.
     */
    public void stampaStato() {
        System.out.println("=== " + nome + " ===");
        System.out.println(descrizione);
        System.out.println("Salute: " + salute);
        System.out.println("Inventario: " + inventario);
    }
}
