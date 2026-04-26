public class GestoreInput {

public Direzione leggiDirezione(){
    System.out.print("Where do you want to go? (w=up, s=down, d=right, a=left): ");
    char c = Leggi.unChar();

    if(c == 'w' || c == 'W'){
        return Direzione.NORD;
    } else if(c == 's' || c == 'S'){
        return Direzione.SUD;
    } else if(c == 'd' || c == 'D'){
        return Direzione.EST;
    } else if(c == 'a' || c == 'A'){
        return Direzione.OVEST;
    } else {
        throw new IllegalArgumentException(
                "Direction not recognized. Use w, a, s, d.");
    }
}

public Direzione leggiDirezioneConRetry(){
    while(true){
        try {
            return leggiDirezione();
        } catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
}
}
