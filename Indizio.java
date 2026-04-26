public class Indizio{

private String nome;
private String descrizione;

public Indizio(String nome, String descrizione){
    this.nome = nome;
    this.descrizione = descrizione;
}

public String getNome(){
    return nome;
}

public String getDescrizione(){
    return descrizione;
}

public String esamina(){
    return "[ Clue ] " + nome + ": " + descrizione;
}

@Override
public String toString(){
    return nome + " - " + descrizione;
}
}
