/**
 * Classe astratta che rappresenta un personaggio nel gioco (usa le dispense di Programmazione OO 1 e 2).
 *
 * <p><b>Responsabilità (Single Responsibility):</b> gestire attributi comuni, salute, interazioni base
 * e template method per parla()/attacca().</p>
 *
 * <p><b>Design:</b> classe astratta con metodi concreti + astratti. Polimorfismo per NPC e Protagonista.
 * Attributi final dove possibile per immutabilità parziale.</p>
 */
public abstract class Personaggio {
    protected final String nome;
    protected final String descrizione;
    protected int salute;

    public Personaggio(String nome, String descrizione, int saluteIniziale) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.salute = saluteIniziale;
    }

    public String getNome() {
        return nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public int getSalute() {
        return salute;
    }

    /**
     * Template method: comportamento base di interazione (polimorfismo).
     * Le sottoclassi lo sovrascrivono.
     */
    public abstract String parla();

    /**
     * Metodo per attacco semplice (estendibile con ricorsione o combattimento completo).
     * @param bersaglio il personaggio da attaccare
     */
    public void attacca(Personaggio bersaglio) {
        // Logica base (può essere estesa)
        System.out.println(nome + " attacca " + bersaglio.getNome() + "!");
        bersaglio.subisciDanno(10);
    }

    protected void subisciDanno(int danno) {
        salute -= danno;
        if (salute < 0) salute = 0;
        System.out.println(nome + " ha subito " + danno + " danni! Salute rimasta: " + salute);
    }

    @Override
    public String toString() {
        return nome + " - " + descrizione + " (salute: " + salute + ")";
    }
}