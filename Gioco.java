import java.util.HashMap;
import java.util.Map;

public class Gioco {

    public static void main(String[] args) {
        Mappa mappa = inizializzaMappa();
        Movimento movimento = new Movimento(mappa, 1, 1);
        Protagonista tony = new Protagonista("Tony", "L'eroe in cerca di gloria.");
        Map<String, Nemico> nemici = inizializzaNemici();
        Missione missione = new Missione("Libera Dublino", new Indizio("Vittoria", "Pace fatta."));

        System.out.println("=====================================");
        System.out.println("  DUBLINO MEDIEVALE - IL GIOCO RPG   ");
        System.out.println("=====================================");

        gameLoop(tony, movimento, nemici, missione);
    }

    private static Mappa inizializzaMappa() {
        Mappa mappa = new Mappa(3, 3);
        mappa.posizionaLuogo(0, 1, new Luogo("Roxy Bar", "Gente losca, musica e fumo."));
        mappa.posizionaLuogo(1, 1, new Luogo("Piazza", "Il mercato delle illusioni."));
        mappa.posizionaLuogo(2, 1, new Luogo("Torri", "Il patibolo dei giullari."));
        mappa.posizionaLuogo(1, 0, new Luogo("Porta della Citta", "Cancelli bloccati da guardie."));
        mappa.posizionaLuogo(1, 2, new Luogo("Castello", "La tana dell'Ignoto."));
        return mappa;
    }

    private static Map<String, Nemico> inizializzaNemici() {
        Map<String, Nemico> nemici = new HashMap<>();
        nemici.put("Porta della Citta", new Nemico("Guardia", "Uno scagnozzo", 70, 10));
        nemici.put("Castello", new Gigante());
        return nemici;
    }

    private static void combatti(Protagonista tony, Nemico nemico, Map<String, Nemico> nemici, String luogo) {
        System.out.println("\nSCONTRO CON " + nemico.getNome().toUpperCase() + " !!!");
        while (nemico.getSalute() > 0 && tony.getSalute() > 0) {
            MotoreEpisodi.attendi(1000);
            System.out.println("\nTu: " + tony.getSalute() + " HP | Nemico: " + nemico.getSalute() + " HP");
            System.out.println("1). Attacca \n2). Cura (Pozione)");
            System.out.print("Scelta: ");

            int scelta = Leggi.unInt();

            if (scelta == 2) {
                MotoreEpisodi.usaPozioneInCombattimento(tony);
            } else {
                tony.attacca(nemico);
            }

            if (nemico.getSalute() > 0) {
                MotoreEpisodi.attendi(1000);
                nemico.attacca(tony);
            }
        }
        if (nemico.getSalute() <= 0) {
            System.out.println("\nNemico sconfitto! Questa area è ora tranquilla");
            nemici.remove(luogo);
        }
    }

    private static void gameLoop(Protagonista tony, Movimento movimento, Map<String, Nemico> nemici, Missione missione) {
        boolean inGioco = true;
        while (inGioco) {

            Luogo luogoAttuale = movimento.getLuogoCorrente();
            Nemico nemicoAttuale = nemici.get(luogoAttuale.getNome());
            boolean presenzaNemico;

            if (nemicoAttuale == null) {
                presenzaNemico = false;
            } else {
                presenzaNemico = true;
            }

            System.out.println("\n");
            System.out.println("\n");
            System.out.println("Sei in: " + luogoAttuale.getNome().toUpperCase());
            System.out.println(luogoAttuale.getDescrizione());
            System.out.println("\n");
            System.out.println("\n");

            System.out.println("1. Muoviti \n2. Esamina / Interagisci  \n3. Zaino \n4. Stato");
            if (nemicoAttuale != null) {
                System.out.println("5. COMBATTI!");
            }
            System.out.print("Scegli cosa fare (es: 1):  ");
            int scelta = Leggi.unInt();

            if (scelta == 1) {
                movimento.muovi();
                System.out.println("\nTi stai muovendo tra i vicoli nebbiosi di Dublino");
                MotoreEpisodi.attendi(800);
            }
            else if (scelta == 2) {
                // TUTTA LA LOGICA E' ORA DELEGATA AL MOTORE
                MotoreEpisodi.gestisciInterazione(luogoAttuale.getNome(), tony, nemicoAttuale, nemici);
            }
            else if (scelta == 3) {
                tony.getInventario().mostra();
            }
            else if (scelta == 4) {
                tony.stampaStato();
            }
            else if (scelta == 5) {
                if (presenzaNemico == true) {
                    if (luogoAttuale.getNome().equals("Castello")) {
                        boolean guardieVive = nemici.containsKey("Porta della Citta");
                        if (guardieVive) {
                            MotoreEpisodi.attendi(1000);
                            System.out.println("\n[!] BARRIERA MAGICA!");
                            MotoreEpisodi.attendi(1500);
                            System.out.println("Provi ad avvicinarti, ma una barriera di sangue impenetrabile ti respinge violentemente indietro!");
                            MotoreEpisodi.attendi(2500);
                            System.out.println("Il Gigante ride: 'Sciocco! Finché le mie guardie vivono alla Porta della Città, il mio sigillo non può essere spezzato!'");
                            MotoreEpisodi.attendi(3000);
                            System.out.println(">>> Devi sconfiggere la Guardia alla Porta prima di poter iniziare questo scontro.");
                            MotoreEpisodi.attendi(2000);
                        } else {
                            MotoreEpisodi.combattiBossFinale(tony, nemicoAttuale, missione, nemici, luogoAttuale.getNome());
                        }
                    } else {
                        if (luogoAttuale.getNome().equals("Porta della Citta")) {
                            MotoreEpisodi.preCombattimentoPorta(tony, nemicoAttuale);
                        }
                        combatti(tony, nemicoAttuale, nemici, luogoAttuale.getNome());
                    }
                } else {
                    MotoreEpisodi.attendi(1000);
                    System.out.println("\nTi guardi intorno con l'arma in pugno... ma non c'è nessuno da combattere qui.");
                    MotoreEpisodi.attendi(1500);
                }
            } else {
                MotoreEpisodi.attendi(500);
                System.out.println("\nScelta non valida. Concentrati e riprova.");
                MotoreEpisodi.attendi(1000);
            }

            if ((tony.getSalute() <= 0) || missione.isCompletata()) {
                inGioco = false;
            }
        }
    }
}