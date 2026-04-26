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

    public Oggetto get(int indice) {
        if (indice >= 0 && indice < oggetti.size()) {
            return oggetti.get(indice);
        }
        return null;
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