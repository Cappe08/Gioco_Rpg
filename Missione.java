public class Missione {

private String descrizione;
private boolean completata;
private Indizio ricompensa;

public Missione(String descrizione, Indizio ricompensa){
    this.descrizione = descrizione;
    this.completata = false;
    this.ricompensa = ricompensa;
}

public String getDescrizione(){
    return descrizione;
}

public boolean isCompletata(){
    return completata;
}

public void avviaMissione(){
    System.out.println("*** NEW QUEST ***");
    System.out.println("Objective: " + descrizione);
}

public void completaMissione(){
    completata = true;
    System.out.println("Quest completed: " + descrizione);
}

public Indizio daiRicompensa(){
    if(completata){
        System.out.println("You receive as a reward: " + ricompensa.getNome());
        return ricompensa;
    } else {
        System.out.println("You must complete the quest first!");
        return null;
    }
}
}
