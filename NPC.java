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

@Override
public String parla(){
    return dialogoBase;
}

public void assegnaMissione(Missione m){
    this.missione = m;
    System.out.println(nome + " assigns you a quest!");
    m.avviaMissione();
}

public void consegnaIndizio(Personaggio giocatore){
    if(indizio != null){
        System.out.println(nome + " whispers a secret to you...");
        System.out.println(indizio.esamina());
    } else {
        System.out.println(nome + ": I have no other secrets to reveal.");
    }
}

public void setIndizio(Indizio indizio){
    this.indizio = indizio;
}

public Missione getMissione(){
    return missione;
}

public Indizio getIndizio(){
    return indizio;
}

public String parlaConScelta(){
    System.out.println(parla());
    System.out.print("Reply (1=Ask for info / 2=Greet): ");
    int scelta = Leggi.unInt();
    if(scelta == 1){
        return nome + ": The castle hides an ancient secret... look for the Unknown!";
    }
    return nome + ": Good luck, Rory!";
}
}
