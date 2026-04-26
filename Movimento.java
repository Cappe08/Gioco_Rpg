public class Movimento {
private int x;
private int y;
private final Mappa mappa;
private final GestoreInput gestoreInput;

public Movimento(Mappa mappa, int xIniziale, int yIniziale){
    this.mappa = mappa;
    if(!mappa.isAttraversabile(xIniziale, yIniziale)){
        throw new IllegalArgumentException(
                "Invalid initial position: (" + xIniziale + "," + yIniziale + ")");
    }
    this.x = xIniziale;
    this.y = yIniziale;
    this.gestoreInput = new GestoreInput();
}

public int getX(){
    return x;
}

public int getY(){
    return y;
}

public Luogo getLuogoCorrente(){
    return mappa.getLuogo(x, y);
}

public void muovi(){
    System.out.println("You are in: " + getLuogoCorrente());

    Direzione direzione = gestoreInput.leggiDirezioneConRetry();
    muovi(direzione);
}

public void muovi(Direzione direzione){
    int nuovaX = x;
    int nuovaY = y;

    if(direzione == Direzione.NORD){
        nuovaY--;
    } else if(direzione == Direzione.SUD){
        nuovaY++;
    } else if(direzione == Direzione.EST){
        nuovaX++;
    } else if(direzione == Direzione.OVEST){
        nuovaX--;
    }

    if(mappa.isAttraversabile(nuovaX, nuovaY)){
        x = nuovaX;
        y = nuovaY;
        System.out.println("You moved to: " + getLuogoCorrente().getNome());
    } else {
        System.out.println("You cannot go in that direction.");
    }
}
}
