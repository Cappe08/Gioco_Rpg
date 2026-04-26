import java.util.HashMap;
import java.util.Map;

public class Gioco {

    public static void main(String[] args){
        Mappa mappa = inizializzaMappa();
        Movimento movimento = new Movimento(mappa, 1, 1);
        Protagonista tony = new Protagonista("Tony", "The hero in search of glory.");
        Map<String, Nemico> nemici = inizializzaNemici();
        Missione missione = new Missione("Free Dublin", new Indizio("Victory", "Peace made."));

        System.out.println(" ");
        System.out.println("Tony of Dublin: Tales from the Roxy Bar    ");
        System.out.println(" ");

        gameLoop(tony, movimento, nemici, missione);
    }

    private static Mappa inizializzaMappa(){
        Mappa mappa = new Mappa(3, 3);
        mappa.posizionaLuogo(0, 1, new Luogo("Roxy Bar", "Shady people, music, and smoke."));
        mappa.posizionaLuogo(1, 1, new Luogo("square", "The market of illusions."));
        mappa.posizionaLuogo(2, 1, new Luogo("Towers", "The jesters' scaffold."));
        mappa.posizionaLuogo(1, 0, new Luogo("City Gate", "Gates blocked by guards."));
        mappa.posizionaLuogo(1, 2, new Luogo("Castle", "The lair of the Unknown."));
        return mappa;
    }

    private static Map<String, Nemico> inizializzaNemici(){
        Map<String, Nemico> nemici = new HashMap<>();
        nemici.put("City Gate", new Nemico("Guard", "A thug", 70, 10));
        nemici.put("Castle", new Gigante());
        return nemici;
    }

    private static void combatti(Protagonista tony, Nemico nemico, Map<String, Nemico> nemici, String luogo){
        System.out.println("\nCLASH WITH " + nemico.getNome().toUpperCase() + " !!!");
        while(nemico.getSalute() > 0 && tony.getSalute() > 0){
            MotoreEpisodi.attendi(1000);
            System.out.println("\nYou: " + tony.getSalute() + " HP | Enemy: " + nemico.getSalute() + " HP");
            System.out.println("1). Attack \n2). Heal (Potion)");
            System.out.print("Choice: ");

            int scelta = Leggi.unInt();

            if(scelta == 2){
                MotoreEpisodi.usaPozioneInCombattimento(tony);
            } else {
                tony.attacca(nemico);
            }

            if(nemico.getSalute() > 0){
                MotoreEpisodi.attendi(1000);
                nemico.attacca(tony);
            }
        }
        if(nemico.getSalute() <= 0){
            System.out.println("\nEnemy defeated! This area is now quiet");
            nemici.remove(luogo);
        }
    }

    private static void gameLoop(Protagonista tony, Movimento movimento, Map<String, Nemico> nemici, Missione missione){
        boolean inGioco = true;
        while(inGioco){

            Luogo luogoAttuale = movimento.getLuogoCorrente();
            Nemico nemicoAttuale = nemici.get(luogoAttuale.getNome());
            boolean presenzaNemico;

            if(nemicoAttuale == null){
                presenzaNemico = false;
            } else {
                presenzaNemico = true;
            }

            System.out.println("\n");
            System.out.println("\n");
            System.out.println("You are in: " + luogoAttuale.getNome().toUpperCase());
            System.out.println(luogoAttuale.getDescrizione());
            System.out.println("\n");
            System.out.println("\n");

            System.out.println("1. Move \n2. Examine / Interact  \n3. Backpack \n4. Status");
            if(nemicoAttuale != null){
                System.out.println("5. FIGHT!");
            }
            System.out.print("Choose what to do (e.g., 1):  ");
            int scelta = Leggi.unInt();

            if(scelta == 1){
                movimento.muovi();
                System.out.println("\nYou are moving through the foggy alleys of Dublin");
                MotoreEpisodi.attendi(800);
            }
            else if(scelta == 2){
                MotoreEpisodi.gestisciInterazione(luogoAttuale.getNome(), tony, nemicoAttuale, nemici);
            }
            else if(scelta == 3){
                tony.getInventario().mostra();
            }
            else if(scelta == 4){
                tony.stampaStato();
            }
            else if(scelta == 5){
                if(presenzaNemico == true){
                    if(luogoAttuale.getNome().equals("Castle")){
                        boolean guardieVive = nemici.containsKey("City Gate");
                        if(guardieVive){
                            MotoreEpisodi.attendi(1000);
                            System.out.println("\n[!] MAGIC BARRIER!");
                            MotoreEpisodi.attendi(1500);
                            System.out.println("You try to approach, but an impenetrable barrier of blood violently pushes you back!");
                            MotoreEpisodi.attendi(2500);
                            System.out.println("The Giant laughs: 'Fool! As long as my guards live at the City Gate, my seal cannot be broken!'");
                            MotoreEpisodi.attendi(3000);
                            System.out.println(">>> You must defeat the Guard at the Gate before starting this fight.");
                            MotoreEpisodi.attendi(2000);
                        } else {
                            MotoreEpisodi.combattiBossFinale(tony, nemicoAttuale, missione, nemici, luogoAttuale.getNome());
                        }
                    } else {
                        if(luogoAttuale.getNome().equals("City Gate")){
                            MotoreEpisodi.preCombattimentoPorta(tony, nemicoAttuale);
                        }
                        combatti(tony, nemicoAttuale, nemici, luogoAttuale.getNome());
                    }
                } else {
                    MotoreEpisodi.attendi(1000);
                    System.out.println("\nYou look around with your weapon drawn... but there is no one to fight here.");
                    MotoreEpisodi.attendi(1500);
                }
            } else {
                MotoreEpisodi.attendi(500);
                System.out.println("\nInvalid choice. Focus and try again.");
                MotoreEpisodi.attendi(1000);
            }

            if((tony.getSalute() <= 0) || missione.isCompletata()){
                inGioco = false;
            }
        }
    }
}