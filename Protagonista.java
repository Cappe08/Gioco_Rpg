public class Protagonista extends Personaggio {
    private final Inventario inventario;
    private int dannoArma = 15;

    private int forza;
    private int carisma;
    private int agilita;

    public Protagonista(String nome, String descrizione){
        super(nome, descrizione, 100);
        this.inventario = new Inventario();
        this.forza = 10;   // Statistiche di base
        this.carisma = 10;
        this.agilita = 10;
    }

    public int getForza(){ return forza; }
    public void modificaForza(int variazione){ this.forza += variazione; }

    public int getCarisma(){ return carisma; }
    public void modificaCarisma(int variazione){ this.carisma += variazione; }

    public int getAgilita(){ return agilita; }
    public void modificaAgilita(int variazione){ this.agilita += variazione; }

    public Inventario getInventario(){ return inventario; }

    public void setDannoArma(int nuovoDanno){ this.dannoArma = nuovoDanno; }

    /**
     * Sistema di Skill Check Deterministico.
     * Controlla se la statistica del giocatore è maggiore o uguale alla difficoltà.
     */
    public boolean tentaAzione(String stat, int difficolta){
        int valoreAttuale = 0;
        if(stat.equalsIgnoreCase("Forza")) valoreAttuale = forza;
        else if(stat.equalsIgnoreCase("Carisma")) valoreAttuale = carisma;
        else if(stat.equalsIgnoreCase("Agilita")) valoreAttuale = agilita;

        System.out.println("[Controllo " + stat + " | Richiesto: " + difficolta + " | Tuo Livello: " + valoreAttuale + "]");
        return valoreAttuale >= difficolta;
    }

    @Override
    public String parla(){
        return "Sono " + nome + ". E vincerò questa battaglia.";
    }

    public void raccogli(Oggetto oggetto){
        inventario.aggiungi(oggetto);
        System.out.println("Hai raccolto: " + oggetto.getNome());
    }

    @Override
    public void attacca(Personaggio bersaglio){
        int dannoTotale = dannoArma + (forza / 2);
        System.out.println(nome + " sferra un colpo infliggendo " + dannoTotale + " danni!");
        bersaglio.subisciDanno(dannoTotale);
    }

    public void stampaStato(){
        System.out.println("\n=== SCHEDA PERSONAGGIO: " + nome.toUpperCase() + " ===");
        System.out.println("Salute: " + salute + "/100 | Danno Base Arma: " + dannoArma);
        System.out.println("Forza: " + forza + " | Carisma: " + carisma + " | Agilità: " + agilita);
        System.out.println("Inventario: ");
        inventario.mostra();
        System.out.println("=========================================\n");
    }
}
