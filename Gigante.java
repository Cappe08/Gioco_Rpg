/**
 * Il Gigante è il nemico finale del gioco.
 * Ha più salute degli altri nemici e può usare un attacco speciale potente.
 */
public class Gigante extends Nemico{

    public Gigante(){
        super("Gigante", "Il terribile antagonista che domina il castello", 150, 20);
    }

    /**
     * Attacco speciale del Gigante: fa molti più danni del normale.
     */
    public void attaccoSpeciale(Personaggio giocatore){
        System.out.println("Il Gigante urla e usa la SCHIACCIATA DEVASTANTE!");
        System.out.println("Un colpo tremendo si abbatte su " + giocatore.getNome() + "!");
        giocatore.subisciDanno(40);
    }

    /**
     * Il Gigante ha un dialogo più minaccioso.
     */
    @Override
    public String parla(){
        return "GIGANTE: Ah ah ah! Nessuno può fermarmi! Questa città è mia per sempre!";
    }
}
