public abstract class Personaggio {

    protected final String nome;
    protected final String descrizione;
    protected int salute;

    public Personaggio(String nome, String descrizione, int saluteIniziale){
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
     * Ogni personaggio parla in modo diverso.
     * Le sottoclassi devono implementare questo metodo.
     */
    public abstract String parla();

    /**
     * Attacca un altro personaggio infliggendo 10 danni base.
     * Le sottoclassi possono sovrascriverlo per cambiare il danno.
     */
    public void attacca(Personaggio bersaglio){
        System.out.println(nome + " attacca " + bersaglio.getNome() + "!");
        bersaglio.subisciDanno(10);
    }

    /**
     * Riduce la salute del personaggio del valore indicato.
     * Se la salute scende sotto zero viene portata a zero.
     * (valori negativi curano il personaggio)
     */
    protected void subisciDanno(int danno){
        salute -= danno;
        if (salute < 0) salute = 0;
        if (danno > 0) {
            System.out.println(nome + " ha subito " + danno + " danni! Salute rimasta: " + salute);
        } else {
            System.out.println(nome + " si e curato di " + (-danno) + " punti! Salute: " + salute);
        }
    }

    @Override
    public String toString() {
        return nome + " - " + descrizione + " (salute: " + salute + ")";
    }
}