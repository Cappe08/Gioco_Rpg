/**
 * Rappresenta un personaggio non giocante (NPC).
 * Ogni NPC ha un dialogo base e puo avere una missione e un indizio da dare al giocatore.
 */
public class NPC extends Personaggio {

    private final String dialogoBase;
    private Missione missione;
    private Indizio indizio;

    public NPC(String nome, String descrizione, String dialogoBase){
        super(nome, descrizione, 50);
        this.dialogoBase = dialogoBase;
        this.missione = null;
        this.indizio = null;
    }

    /**
     * Restituisce il dialogo base dell'NPC.
     */
    @Override
    public String parla(){
        return dialogoBase;
    }

    /**
     * L'NPC assegna una missione al giocatore e la avvia subito.
     */
    public void assegnaMissione(Missione m){
        this.missione = m;
        System.out.println(nome + " ti assegna una missione!");
        m.avviaMissione();
    }

    /**
     * L'NPC consegna il suo indizio al giocatore (se ce l'ha).
     */
    public void consegnaIndizio(Personaggio giocatore){
        if (indizio != null) {
            System.out.println(nome + " ti sussurra un segreto...");
            System.out.println(indizio.esamina());
        } else {
            System.out.println(nome + ": Non ho altri segreti da rivelare.");
        }
    }

    public void setIndizio(Indizio indizio) {
        this.indizio = indizio;
    }

    public Missione getMissione() {
        return missione;
    }

    public Indizio getIndizio() {
        return indizio;
    }

    /**
     * Dialogo interattivo: il giocatore sceglie come rispondere all'NPC.
     * Usa Leggi.java per leggere l'input.
     */
    public String parlaConScelta(){
        System.out.println(parla());
        System.out.print("Rispondi (1=Chiedi info / 2=Saluta): ");
        int scelta = Leggi.unInt();
        if (scelta == 1) {
            return nome + ": Il castello nasconde un antico segreto... cerca l'Ignoto!";
        }
        return nome + ": Buona fortuna, Rory!";
    }
}
