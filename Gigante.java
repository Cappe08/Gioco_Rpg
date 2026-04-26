public class Gigante extends Nemico{

public Gigante(){
    super("Gigante", "The terrible antagonist who dominates the castle", 150, 20);
}

public void attaccoSpeciale(Personaggio giocatore){
    System.out.println("The Giant roars and uses DEVASTATING SMASH!");
    System.out.println("A tremendous blow strikes " + giocatore.getNome() + "!");
    giocatore.subisciDanno(40);
}

@Override
public String parla(){
    return "GIANT: Ha ha ha! No one can stop me! This city is mine forever!";
}
}
