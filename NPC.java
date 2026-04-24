/**
 * Classe concreta per i PNG (personaggi non giocanti).
 *
 * <p><b>Responsabilità:</b> fornire dialoghi fissi o ramificati e interazioni con il giocatore.</p>
 *
 * <p><b>Design:</b> estende Personaggio. Ogni NPC è associato a un luogo (gestito in Gioco).</p>
 */
public class NPC extends Personaggio {
    private final String dialogoBase;

    public NPC(String nome, String descrizione, String dialogoBase) {
        super(nome, descrizione, 50); // salute NPC (per futuri combattimenti)
        this.dialogoBase = dialogoBase;
    }

    /**
     * Override: ogni NPC ha un dialogo unico (basato sull'idea iniziale: Eoin, Michela, ecc.).
     */
    @Override
    public String parla() {
        return dialogoBase;
    }

    /**
     * Metodo per dialogo avanzato (pronto per estensione con scelte multiple via Leggi.java).
     */
    public String parlaConScelta() {
        System.out.println(parla());
        System.out.print("Rispondi (1=Chiedi info / 2=Saluta): ");
        int scelta = Leggi.unInt();
        if (scelta == 1) {
            return nome + ": Il castello nasconde un antico segreto... cerca l'Ignoto!";
        }
        return nome + ": Buona fortuna, Rory!";
    }
}