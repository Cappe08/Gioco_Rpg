public class Gigante extends Nemico{

    public Gigante(){
        super("Gigante", "Il terribile antagonista che domina il castello", 150, 20);
    }

    /**
     * Attacco speciale del Gigante: fa molti piu danni del normale.
     */
    public void attaccoSpeciale(Personaggio giocatore){
        System.out.println("Il Gigante urla e usa la SCHIACCIATA DEVASTANTE!");
        System.out.println("Un colpo tremendo si abbatte su " + giocatore.getNome() + "!");
        giocatore.subisciDanno(40);
    }

    /**
     * Il Gigante ha un dialogo piu minaccioso.
     */
    @Override
    public String parla(){
        return "GIGANTE: Ah ah ah! Nessuno puo fermarmi! Questa citta e mia per sempre!";
    }
}