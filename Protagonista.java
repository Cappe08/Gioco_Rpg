public class Protagonista extends Personaggio {
private final Inventario inventario;
private int dannoArma = 15;

private int forza;
private int carisma;
private int agilita;

public Protagonista(String nome, String descrizione){
    super(nome, descrizione, 100);
    this.inventario = new Inventario();
    this.forza = 10;   
    this.carisma = 10;
    this.agilita = 10;
}

public int getForza(){ return forza; }
public void modificaForza(int variazione){ this.forza += variazione; }

public int getCarisma(){ return carisma; }
public void modificaCarisma(int variazione){ this.carisma += variazione; }

public int getAgilita(){ return agilita; }
public void modificaAgilita(int variazione){ this.agilita += variazione; }

public Inventario getInventario(){ return inventario; }

public void setDannoArma(int nuovoDanno){ this.dannoArma = nuovoDanno; }

public boolean tentaAzione(String stat, int difficolta){
    int valoreAttuale = 0;
    if(stat.equalsIgnoreCase("Forza")) valoreAttuale = forza;
    else if(stat.equalsIgnoreCase("Carisma")) valoreAttuale = carisma;
    else if(stat.equalsIgnoreCase("Agilita")) valoreAttuale = agilita;

    System.out.println("[Check " + stat + " | Required: " + difficolta + " | Your Level: " + valoreAttuale + "]");
    return valoreAttuale >= difficolta;
}

@Override
public String parla(){
    return "I am " + nome + ". And I will win this battle.";
}

public void raccogli(Oggetto oggetto){
    inventario.aggiungi(oggetto);
    System.out.println("You collected: " + oggetto.getNome());
}

@Override
public void attacca(Personaggio bersaglio){
    int dannoTotale = dannoArma + (forza / 2);
    System.out.println(nome + " lands a blow dealing " + dannoTotale + " damage!");
    bersaglio.subisciDanno(dannoTotale);
}

public void stampaStato(){
    System.out.println("\n=== CHARACTER SHEET: " + nome.toUpperCase() + " ===");
    System.out.println("Health: " + salute + "/100 | Base Weapon Damage: " + dannoArma);
    System.out.println("Strength: " + forza + " | Charisma: " + carisma + " | Agility: " + agilita);
    System.out.println("Backpack: ");
    inventario.mostra();
    System.out.println("=========================================\n");
}
}
