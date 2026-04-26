public class Luogo {
private final String nome;
private final String descrizione;

public Luogo(String nome, String descrizione){
    this.nome = nome;
    this.descrizione = descrizione;
}

public String getNome(){
    return nome;
}

public String getDescrizione(){
    return descrizione;
}

@Override
public String toString(){
    return nome + " - " + descrizione;
}
}
