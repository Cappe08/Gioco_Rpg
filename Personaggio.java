public abstract class Personaggio {

protected final String nome;
protected final String descrizione;
protected int salute;

public Personaggio(String nome, String descrizione, int saluteIniziale){
    this.nome = nome;
    this.descrizione = descrizione;
    this.salute = saluteIniziale;
}

public String getNome(){
    return nome;
}

public String getDescrizione(){
    return descrizione;
}

public int getSalute(){
    return salute;
}

public abstract String parla();

public void attacca(Personaggio bersaglio){
    System.out.println(nome + " attacks " + bersaglio.getNome() + "!");
    bersaglio.subisciDanno(10);
}

protected void subisciDanno(int danno){
    salute -= danno;
    if(salute < 0) salute = 0;
    if(danno > 0){
        System.out.println(nome + " took " + danno + " damage! Health remaining: " + salute);
    } else {
        System.out.println(nome + " healed for " + (-danno) + " points! Health: " + salute);
    }
}

@Override
public String toString(){
    return nome + " - " + descrizione + " (health: " + salute + ")";
}
}
