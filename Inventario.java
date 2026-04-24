/**
 * Classe che gestisce l'inventario del protagonista.
 *
 * <p><b>Responsabilità:</b> contenere e manipolare oggetti (usa array o ArrayList come nelle dispense 1.3 Array e 4.2 Strutture dati).</p>
 *
 * <p><b>Design:</b> composizione con Protagonista. Limite massimo di oggetti per realismo RPG.</p>
 */
import java.util.ArrayList;
import java.util.List;

public class Inventario {
    private final List<Oggetto> oggetti;
    private static final int CAPACITA_MAX = 10;

    public Inventario() {
        this.oggetti = new ArrayList<>();
    }

    public void aggiungi(Oggetto o) {
        if (oggetti.size() < CAPACITA_MAX) {
            oggetti.add(o);
            System.out.println("Oggetto aggiunto all'inventario!");
        } else {
            System.out.println("Inventario pieno!");
        }
    }

    public void mostra() {
        if (oggetti.isEmpty()) {
            System.out.println("Inventario vuoto.");
            return;
        }
        System.out.println("=== INVENTARIO ===");
        for (int i = 0; i < oggetti.size(); i++) {
            System.out.println((i + 1) + ". " + oggetti.get(i));
        }
    }

    public Oggetto usa(int indice) {
        if (indice >= 0 && indice < oggetti.size()) {
            Oggetto o = oggetti.remove(indice);
            System.out.println("Hai usato: " + o.getNome());
            return o;
        }
        return null;
    }

    @Override
    public String toString() {
        return oggetti.size() + " oggetti";
    }
}