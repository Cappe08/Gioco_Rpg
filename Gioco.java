/**
 * CLASSE PRINCIPALE DEL GIOCO - Orchestratore completo (Gioco.java)
 *
 * <p><b>Responsabilità:</b> inizializzare mappa (come da idea iniziale), NPC, protagonista e gestire il game loop.</p>
 *
 * <p><b>Design:</b> usa tutte le classi esistenti + nuove. Input solo con Leggi.java.
 * Loop principale con scelte multiple. Associazione NPC → Luogo tramite HashMap (struttura dati dispense).</p>
 *
 * Il gioco è ora pienamente funzionante e fedele all'UML + idea iniziale.
 */
import java.util.HashMap;
import java.util.Map;

public class Gioco {

    public static void main(String[] args) {
        System.out.println("=====================================");
        System.out.println("BENVENUTO IN DUBLINO MEDIEVALE - RPG");
        System.out.println("=====================================");
        System.out.println("Protagonista: Rory\n");

        // 1. Creazione mappa (3x3 come da sketch idea iniziale)
        Mappa mappa = new Mappa(3, 3);

        Luogo locanda = new Luogo("Locanda", "Una locanda accogliente piena di avventurieri che raccontano storie.");
        Luogo piazza = new Luogo("Piazza", "La piazza centrale della città, animata e rumorosa.");
        Luogo torri = new Luogo("Torri", "Le alte torri di guardia che dominano la città.");
        Luogo castello = new Luogo("Castello", "Un castello cupo e misterioso... sembra nascondere un segreto.");
        Luogo porta = new Luogo("Porta della Citta", "L'ingresso principale, sorvegliato da guardie.");

        mappa.posizionaLuogo(0, 1, locanda);   // Ovest
        mappa.posizionaLuogo(1, 1, piazza);    // Centro
        mappa.posizionaLuogo(2, 1, torri);     // Est
        mappa.posizionaLuogo(1, 2, castello);  // Sud
        mappa.posizionaLuogo(1, 0, porta);     // Nord

        // 2. Movimento (usa classe del compagno)
        Movimento movimento = new Movimento(mappa, 1, 1); // partenza in Piazza

        // 3. Protagonista (file generato da Cesare)
        Protagonista rory = new Protagonista("Rory", "Un giovane avventuriero coraggioso in cerca di gloria.");

        // 4. NPC (associati ai luoghi come da idea iniziale)
        Map<String, NPC> npcs = new HashMap<>();
        npcs.put("Locanda", new NPC("Michela", "La locandiera gentile.", "Ciao Rory! Hai sentito del tesoro nascosto nel castello? Parla con Eoin in piazza!"));
        npcs.put("Piazza", new NPC("Eoin", "Il mercante chiacchierone.", "Ehi Rory! La città è in pericolo. L'Ignoto nel castello sa tutto..."));
        npcs.put("Torri", new NPC("Vanno", "La guardia delle torri.", "Benvenuto straniero. Le torri sono sicure... per ora."));
        npcs.put("Castello", new NPC("Ignoto", "Una figura misteriosa incappucciata.", "Finalmente sei arrivato, Rory... il segreto è qui. Ma dovrai combattere!"));

        // Oggetti iniziali (per demo)
        Oggetto spada = new Oggetto("Spada arrugginita", "Un'arma semplice ma utile", 15);
        rory.raccogli(spada);

        boolean inGioco = true;
        System.out.println("Premi INVIO per iniziare l'avventura...");
        Leggi.unoString(); // pausa iniziale

        while (inGioco) {
            Luogo corrente = movimento.getLuogoCorrente();
            System.out.println("\n=== " + corrente.getNome() + " ===");
            System.out.println(corrente.getDescrizione());

            // Verifica presenza NPC
            NPC npcPresente = npcs.get(corrente.getNome());
            if (npcPresente != null) {
                System.out.println("Qui c'è " + npcPresente.getNome() + "!");
            }

            // Menu principale (input con Leggi.java)
            System.out.println("\nCosa vuoi fare?");
            System.out.println("1. Muoviti");
            System.out.println("2. Parla con NPC (se presente)");
            System.out.println("3. Mostra inventario");
            System.out.println("4. Usa oggetto dall'inventario");
            System.out.println("5. Stampa stato Rory");
            System.out.println("6. Esci dal gioco");
            System.out.print("Scelta (1-6): ");

            int scelta = Leggi.unInt();

            switch (scelta) {
                case 1:
                    movimento.muovi(); // usa completamente la classe del compagno
                    break;
                case 2:
                    if (npcPresente != null) {
                        System.out.println(npcPresente.parlaConScelta());
                        // Possibile estensione: combattimento
                        System.out.print("Vuoi attaccare " + npcPresente.getNome() + "? (true/false): ");
                        if (Leggi.unBoolean()) {
                            rory.attacca(npcPresente);
                        }
                    } else {
                        System.out.println("Non c'è nessuno con cui parlare qui.");
                    }
                    break;
                case 3:
                    rory.getInventario().mostra();
                    break;
                case 4:
                    rory.getInventario().mostra();
                    if (!rory.getInventario().toString().equals("0 oggetti")) {
                        System.out.print("Quale oggetto vuoi usare? (numero): ");
                        int idx = Leggi.unInt() - 1;
                        Oggetto usato = rory.getInventario().usa(idx);
                        if (usato != null) {
                            rory.subisciDanno(-usato.getValore()); // effetto cura
                        }
                    }
                    break;
                case 5:
                    rory.stampaStato();
                    break;
                case 6:
                    System.out.println("Grazie per aver giocato a Dublino Medievale! Arrivederci, Rory.");
                    inGioco = false;
                    break;
                default:
                    System.out.println("Scelta non valida. Riprova.");
            }

            // Controllo fine gioco
            if (rory.getSalute() <= 0) {
                System.out.println("GAME OVER - Rory è caduto in battaglia!");
                inGioco = false;
            }
        }
    }
}