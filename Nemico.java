public class Nemico extends Personaggio {

private int danno;

public Nemico(String nome, String descrizione, int salute, int danno){
    super(nome, descrizione, salute);
    this.danno = danno;
}

public int getDanno(){
    return danno;
}

@Override
public String parla(){
    return nome + ": Prepare to fight, adventurer!";
}

@Override
public void attacca(Personaggio bersaglio){
    System.out.println(nome + " attacks " + bersaglio.getNome() + " dealing " + danno + " damage!");
    bersaglio.subisciDanno(danno);
}
}
