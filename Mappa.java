public class Mappa {
private final Luogo[][] griglia;
private final int larghezza;
private final int altezza;

public Mappa(int larghezza, int altezza){
    if(larghezza <= 0 || altezza <= 0){
        throw new IllegalArgumentException(
                "Map dimensions must be positive.");
    }
    this.larghezza = larghezza;
    this.altezza = altezza;
    this.griglia = new Luogo[altezza][larghezza];
}

public void posizionaLuogo(int x, int y, Luogo luogo){
    if(!coordinateValide(x, y)){
        throw new IllegalArgumentException(
                "Coordinates out of bounds: (" + x + "," + y + ")");
    }
    griglia[y][x] = luogo;
}

public Luogo getLuogo(int x, int y){
    if(!coordinateValide(x, y)){
        return null;
    }
    return griglia[y][x];
}

public boolean coordinateValide(int x, int y){
    return x >= 0 && x < larghezza && y >= 0 && y < altezza;
}

public boolean isAttraversabile(int x, int y){
    return coordinateValide(x, y) && griglia[y][x] != null;
}

public int getLarghezza(){
    return larghezza;
}

public int getAltezza(){
    return altezza;
}
}
