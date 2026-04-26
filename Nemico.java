public class Nemico extends Personaggio {

    private int danno;

    public Nemico(String nome, String descrizione, int salute, int danno){
        super(nome, descrizione, salute);
        this.danno = danno;
    }

    public int getDanno() {
        return danno;
    }

    /**
     * Il nemico dice una frase di minaccia.
     */
    @Override
    public String parla(){
        return nome + ": Preparati a combattere, avventuriero!";
    }

    /**
     * Attacca il bersaglio con il danno specifico di questo nemico.
     */
    @Override
    public void attacca(Personaggio bersaglio){
        System.out.println(nome + " attacca " + bersaglio.getNome() + " infliggendo " + danno + " danni!");
        bersaglio.subisciDanno(danno);
    }
}