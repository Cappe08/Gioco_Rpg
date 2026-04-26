public class Mappa {
    private final Luogo[][] griglia;
    private final int larghezza;
    private final int altezza;

    /**
     * Crea una mappa vuota delle dimensioni specificate.
     * All'inizio ogni cella contiene {@code null}.
     *
     * @param larghezza numero di colonne (deve essere > 0)
     * @param altezza   numero di righe (deve essere > 0)
     */
    public Mappa(int larghezza, int altezza) {
        if (larghezza <= 0 || altezza <= 0) {
            throw new IllegalArgumentException(
                    "Le dimensioni della mappa devono essere positive.");
        }
        this.larghezza = larghezza;
        this.altezza = altezza;
        // y = riga, x = colonna  =>  griglia[y][x]
        this.griglia = new Luogo[altezza][larghezza];
    }

    /**
     * Posiziona un luogo nelle coordinate indicate.
     *
     * @param x     colonna (0-based, da sinistra verso destra)
     * @param y     riga    (0-based, dall'alto verso il basso)
     * @param luogo il {@link Luogo} da collocare; {@code null} per
     *              lasciare la cella vuota (non attraversabile)
     */
    public void posizionaLuogo(int x, int y, Luogo luogo) {
        if (!coordinateValide(x, y)) {
            throw new IllegalArgumentException(
                    "Coordinate fuori dai limiti: (" + x + "," + y + ")");
        }
        griglia[y][x] = luogo;
    }

    /**
     * Recupera il luogo presente alle coordinate specificate.
     *
     * @param x colonna
     * @param y riga
     * @return il {@link Luogo} o {@code null} se la cella e' vuota
     *         o le coordinate sono fuori mappa
     */
    public Luogo getLuogo(int x, int y) {
        if (!coordinateValide(x, y)) {
            return null;
        }
        return griglia[y][x];
    }

    /**
     * Verifica che le coordinate rientrino nei bordi della mappa.
     *
     * @param x colonna da controllare
     * @param y riga    da controllare
     * @return {@code true} se (x,y) e' dentro la griglia
     */
    public boolean coordinateValide(int x, int y) {
        return x >= 0 && x < larghezza && y >= 0 && y < altezza;
    }

    /**
     * Determina se il giocatore puo' trovarsi sulla cella (x,y).
     * Attualmente una cella e' attraversabile solo se esiste e
     * contiene un {@link Luogo} (non e' {@code null}).
     *
     * @param x colonna
     * @param y riga
     * @return {@code true} se il movimento e' consentito
     */
    public boolean isAttraversabile(int x, int y) {
        return coordinateValide(x, y) && griglia[y][x] != null;
    }

    public int getLarghezza() {
        return larghezza;
    }

    public int getAltezza() {
        return altezza;
    }
}