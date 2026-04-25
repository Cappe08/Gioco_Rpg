/**
 * CLASSE PRINCIPALE DEL GIOCO - Orchestratore (Gioco.java)
 *
 * <p><b>Responsabilita':</b> coordinare l'avvio e il loop di gioco.
 * Ogni fase (inizializzazione mappa, NPC, nemici, missioni, game loop)
 * e' delegata a metodi privati statici per rispettare SRP e KISS.</p>
 *
 * <p><b>Design:</b> il metodo {@code main} e' ridotto a una sequenza
 * dichiarativa di chiamate. Il game loop delega ogni azione del menu
 * a un metodo dedicato. Le classi {@link Missione}, {@link Nemico},
 * {@link Gigante} e {@link Indizio} sono ora integralmente usate.</p>
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Gioco {

    public static void main(String[] args) {
        stampaBenvenuto();

        // 1. Inizializzazione mondo di gioco
        Mappa mappa = inizializzaMappa();
        Movimento movimento = new Movimento(mappa, 1, 1); // partenza in Piazza
        Protagonista rory = new Protagonista("Rory", "Un giovane avventuriero coraggioso in cerca di gloria.");

        Map<String, Nemico> nemici = inizializzaNemici();
        Map<String, NPC> npcs = inizializzaNPC();
        Missione missionePrincipale = inizializzaMissione();
        npcs.get("Piazza").assegnaMissione(missionePrincipale);

        List<Indizio> indiziRaccolti = new ArrayList<>();

        // Oggetto iniziale
        rory.raccogli(new Oggetto("Spada arrugginita", "Un'arma semplice ma utile", 15, false));

        System.out.println("Premi INVIO per iniziare l'avventura...");
        Leggi.unoString();

        // 2. Game loop
        gameLoop(rory, movimento, npcs, nemici, missionePrincipale, indiziRaccolti);

        System.out.println("\nGrazie per aver giocato a Dublino Medievale! Arrivederci, Rory.");
    }

    /* ================================================================ */
    /*  SEZIONE AVVIO                                                   */
    /* ================================================================ */

    private static void stampaBenvenuto() {
        System.out.println("=====================================");
        System.out.println("BENVENUTO IN DUBLINO MEDIEVALE - RPG");
        System.out.println("=====================================");
        System.out.println("Protagonista: Rory\n");
    }

    private static Mappa inizializzaMappa() {
        Mappa mappa = new Mappa(3, 3);
        mappa.posizionaLuogo(0, 1, new Luogo("Locanda", "Una locanda accogliente piena di avventurieri che raccontano storie."));
        mappa.posizionaLuogo(1, 1, new Luogo("Piazza", "La piazza centrale della citta, animata e rumorosa."));
        mappa.posizionaLuogo(2, 1, new Luogo("Torri", "Le alte torri di guardia che dominano la citta."));
        mappa.posizionaLuogo(1, 2, new Luogo("Castello", "Un castello cupo e misterioso... sembra nascondere un segreto."));
        mappa.posizionaLuogo(1, 0, new Luogo("Porta della Citta", "L'ingresso principale, sorvegliato da guardie."));
        return mappa;
    }

    private static Map<String, Nemico> inizializzaNemici() {
        Map<String, Nemico> nemici = new HashMap<>();
        nemici.put("Castello", new Gigante());
        return nemici;
    }

    private static Map<String, NPC> inizializzaNPC() {
        Map<String, NPC> npcs = new HashMap<>();
        npcs.put("Locanda", new NPC("Michela", "La locandiera gentile.", "Ciao Rory! Hai sentito del tesoro nascosto nel castello? Parla con Eoin in piazza!"));
        npcs.put("Piazza", new NPC("Eoin", "Il mercante chiacchierone.", "Ehi Rory! La citta e in pericolo. L'Ignoto nel castello sa tutto..."));
        npcs.put("Torri", new NPC("Vanno", "La guardia delle torri.", "Benvenuto straniero. Le torri sono sicure... per ora."));
        npcs.put("Castello", new NPC("Ignoto", "Una figura misteriosa incappucciata.", "Finalmente sei arrivato, Rory... il segreto e qui. Ma dovrai combattere!"));
        return npcs;
    }

    private static Missione inizializzaMissione() {
        Indizio ricompensa = new Indizio("Pergamena Antica", "Il tesoro di Dublino e nascosto sotto la torre piu alta del castello.");
        return new Missione("Sconfiggi il Gigante che minaccia il castello", ricompensa);
    }

    /* ================================================================ */
    /*  SEZIONE GAME LOOP                                               */
    /* ================================================================ */

    private static void gameLoop(Protagonista rory, Movimento movimento,
                                 Map<String, NPC> npcs, Map<String, Nemico> nemici,
                                 Missione missione, List<Indizio> indiziRaccolti) {
        boolean inGioco = true;
        while (inGioco) {
            Luogo corrente = movimento.getLuogoCorrente();
            stampaLuogoCorrente(corrente);

            NPC npcPresente = npcs.get(corrente.getNome());
            Nemico nemicoPresente = nemici.get(corrente.getNome());

            if (npcPresente != null) {
                System.out.println("Qui c'e " + npcPresente.getNome() + "!");
            }
            if (nemicoPresente != null) {
                System.out.println("Attenzione! " + nemicoPresente.getNome() + " ti blocca il passo!");
            }

            int scelta = leggiSceltaMenu();

            switch (scelta) {
                case 1:
                    movimento.muovi();
                    break;
                case 2:
                    interagisciConNPC(npcPresente);
                    break;
                case 3:
                    rory.getInventario().mostra();
                    break;
                case 4:
                    usaOggetto(rory);
                    break;
                case 5:
                    rory.stampaStato();
                    break;
                case 6:
                    if (nemicoPresente != null) {
                        combatti(rory, nemicoPresente, missione, indiziRaccolti, nemici, corrente.getNome());
                    } else {
                        System.out.println("Non c'e nessuno da combattere qui.");
                    }
                    break;
                case 7:
                    mostraMissioneEIndizi(missione, indiziRaccolti);
                    break;
                case 8:
                    System.out.print("Sei sicuro di voler uscire? (true/false): ");
                    inGioco = !Leggi.unBoolean();
                    break;
                default:
                    System.out.println("Scelta non valida. Riprova.");
            }

            if (rory.getSalute() <= 0) {
                System.out.println("GAME OVER - Rory e caduto in battaglia!");
                inGioco = false;
            }
        }
    }

    private static void stampaLuogoCorrente(Luogo luogo) {
        System.out.println("\n=== " + luogo.getNome() + " ===");
        System.out.println(luogo.getDescrizione());
    }

    private static int leggiSceltaMenu() {
        System.out.println("\nCosa vuoi fare?");
        System.out.println("1. Muoviti");
        System.out.println("2. Parla con NPC (se presente)");
        System.out.println("3. Mostra inventario");
        System.out.println("4. Usa oggetto dall'inventario");
        System.out.println("5. Stampa stato Rory");
        System.out.println("6. Combatti nemico (se presente)");
        System.out.println("7. Mostra missioni e indizi");
        System.out.println("8. Esci dal gioco");
        System.out.print("Scelta (1-8): ");
        return Leggi.unInt();
    }

    /* ================================================================ */
    /*  SEZIONE AZIONI DI GIOCO                                         */
    /* ================================================================ */

    private static void interagisciConNPC(NPC npc) {
        if (npc != null) {
            System.out.println(npc.parlaConScelta());
        } else {
            System.out.println("Non c'e nessuno con cui parlare qui.");
        }
    }

    private static void usaOggetto(Protagonista rory) {
        rory.getInventario().mostra();
        System.out.print("Quale oggetto vuoi usare? (numero): ");
        int idx = Leggi.unInt() - 1;
        Oggetto oggetto = rory.getInventario().get(idx);
        if (oggetto == null) {
            System.out.println("Scelta non valida.");
            return;
        }
        if (!oggetto.isConsumabile()) {
            System.out.println(oggetto.getNome() + " non e consumabile. Non puoi usarlo cosi.");
            return;
        }
        Oggetto usato = rory.getInventario().usa(idx);
        if (usato != null) {
            // valore negativo = cura (subisciDanno con valore negativo aumenta la salute)
            rory.subisciDanno(-usato.getValore());
        }
    }

    private static void combatti(Protagonista rory, Nemico nemico, Missione missione,
                                 List<Indizio> indiziRaccolti, Map<String, Nemico> nemici,
                                 String nomeLuogo) {
        int turno = 0;
        while (nemico.getSalute() > 0 && rory.getSalute() > 0) {
            turno++;
            System.out.println("\n--- Turno " + turno + " ---");
            System.out.println("Rory attacca " + nemico.getNome() + "!");
            rory.attacca(nemico);

            if (nemico.getSalute() <= 0) {
                System.out.println(nemico.getNome() + " e stato sconfitto!");
                nemici.remove(nomeLuogo);
                if (!missione.isCompletata()) {
                    missione.completaMissione();
                    Indizio ricompensa = missione.daiRicompensa();
                    if (ricompensa != null) {
                        indiziRaccolti.add(ricompensa);
                    }
                }
                return;
            }

            // Turno del nemico
            if (nemico instanceof Gigante && turno % 2 == 0) {
                ((Gigante) nemico).attaccoSpeciale(rory);
            } else {
                nemico.attacca(rory);
            }
        }
    }

    private static void mostraMissioneEIndizi(Missione missione, List<Indizio> indiziRaccolti) {
        System.out.println("\n=== MISSIONE ATTIVA ===");
        System.out.println("Obiettivo: " + missione.getDescrizione());
        System.out.println("Stato: " + (missione.isCompletata() ? "Completata" : "In corso"));

        if (indiziRaccolti.isEmpty()) {
            System.out.println("Indizi raccolti: nessuno.");
        } else {
            System.out.println("Indizi raccolti:");
            for (Indizio i : indiziRaccolti) {
                System.out.println(" - " + i.esamina());
            }
        }
    }
}

