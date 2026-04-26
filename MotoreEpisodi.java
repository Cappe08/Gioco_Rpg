import java.util.Map;

public class MotoreEpisodi {

    // VARIABILI DI STATO DELLA STORIA (Spostate qui da Gioco.java)
    private static boolean episodioLocandaFatto = false;
    private static boolean episodioBarFatto = false;
    private static boolean episodioPiazzaFatto = false;
    private static boolean episodioTorriFatto = false;

    // METODO PUBBLICO PER IL RITARDO (Così Gioco.java può usarlo senza duplicarlo)
    public static void attendi(int millisecondi) {
        try { Thread.sleep(millisecondi); } catch (InterruptedException e) {}
    }

    // NUOVO METODO GESTISCI INTERAZIONE (Spostato da Gioco.java)
    public static void gestisciInterazione(String luogo, Protagonista tony, Nemico nemico, Map<String, Nemico> nemici) {
        if (nemico != null) {
            if (luogo.equals("Porta della Citta")) {
                System.out.println("\n[ESAMINA]");
                attendi(1000);
                System.out.println("Ti nascondi dietro un carretto rovesciato per studiare la situazione.");
                attendi(2000);
                System.out.println("Un manipolo di guardie corrotte blocca il passaggio, i loro volti sono celati da elmi neri come la pece.");
                attendi(2500);
                System.out.println("Sembra che stiano canalizzando un'energia oscura e pulsante verso il Castello a sud attraverso un rituale empio.");
                attendi(3000);
                System.out.println("Finché queste guardie resteranno in vita, l'Ignoto trarrà potere da questo legame energetico per proteggersi...");
                attendi(2500);
                System.out.println("\n(Sei pronto a spezzare questa maledizione? Premi 5 per sfoderare l'arma e affrontarli!)");
            } else if (luogo.equals("Castello")) {
                System.out.println("\n[ESAMINA] Il Gigante Spettrale sovrasta le macerie.");
                attendi(1500);
                if (nemici.containsKey("Porta della Citta")) {
                    System.out.println("È circondato da un muro di energia rossa. È INATTACCABILE finché la fonte dell'energia non viene distrutta.");
                    attendi(3000);
                } else {
                    System.out.println("La barriera che lo proteggeva è svanita. Ora è vulnerabile!");
                    System.out.println("(Premi 5 per avviare lo scontro finale!)");
                    attendi(3000);

                }
            }
            return;
        }

        // LOGICA DEGLI EPISODI
        if (luogo.equals("Roxy Bar") && !episodioLocandaFatto) {
            boolean successo = episodioLocanda(tony);
            if (successo) {
                episodioBarFatto = true;
                episodioLocandaFatto = true;
            } else {
                System.out.println("\n[!] Hai fallito. Riposati e riprova! (Premi di nuovo 2)");
            }
        }
        else if (luogo.equals("Torri") && !episodioTorriFatto) {
            if (episodioBarFatto) {
                boolean successo = episodioTorri(tony);
                if (successo) {
                    episodioTorriFatto = true;
                } else {
                    System.out.println("\n[!] Hai perso. Preparati meglio e riprova! (Premi di nuovo 2)");
                }
            } else {
                System.out.println("\nDue soldati incrociano le alabarde.");
                System.out.println("Guardia: 'Vasco accetta solo chi si è dimostrato un duro al Roxy Bar. Vattene!'");
            }
        }
        else if (luogo.equals("Piazza") && !episodioPiazzaFatto) {
            boolean successo = episodioPiazza(tony);
            if (successo) {
                episodioPiazzaFatto = true;
            } else {
                System.out.println("\n[!] Hai fallito l'azione. Fai più attenzione e riprova!");
            }
        }
        else {
            System.out.println("\nHai già completato tutto il possibile in questa zona.");
        }
    }

    public static boolean episodioLocanda(Protagonista tony) {
        System.out.println("\n=== IL ROXY BAR ===");
        attendi(1000);
        System.out.println("Spingi la pesante porta di quercia ed entri nel Roxy Bar. C'è un fumo denso e un odore acre di birra scura e legno marcio.");
        attendi(2000);
        System.out.println("Il rumore di boccali che sbattono e risate sguaiate riempie l'aria della taverna.");
        attendi(1500);
        System.out.println("In un angolo buio, un certo 'PUPO' sta giocando d'azzardo accanitamente con dei loschi figuri, imprecando in toscano ogni volta che i dadi rotolano.");
        attendi(2500);
        System.out.println("Dietro il bancone, Michela, una barista dai capelli rossi fiammanti, sta pulendo un bicchiere. Ti scruta con occhi che hanno visto troppe risse.");
        attendi(2500);
        System.out.println("Michela: 'Non serviamo il tuo genere qui, straniero. A meno che tu non abbia oro sonante o una buona storia da raccontare.'");
        attendi(2500);

        System.out.println("\n1. [Diplomazia] Offri da bere a tutta la locanda e chiedi informazioni su Pupo e sul Castello. (Richiede Carisma 10)");
        System.out.println("2. [Intimidazione] Sbatti i pugni sul bancone e pretendi di sapere chi comanda in questa bettola. (Richiede Forza 10)");
        System.out.print("Scegli la tua mossa (1 o 2): ");
        int scelta = Leggi.unInt();

        if (scelta == 1) {
            attendi(1000);
            System.out.println("\nMetti mano alla sacca e lanci un paio di monete sul bancone: 'Giro di birra per tutti! E per il signore toscano in fondo, la vostra annata migliore!'");
            if (tony.tentaAzione("Carisma", 10)) {
                attendi(2500);
                System.out.println("\nLa locanda esplode in un boato di approvazione. I mercenari alzano i boccali verso di te.");
                attendi(2000);
                System.out.println("Pupo ti fa l'occhiolino e intona una canzone stonata sulla fortuna.");
                attendi(2000);
                System.out.println("Michela sorride, rivelando un'expresione finalmente dolce: 'Sei uno che sa come farsi degli amici. Pupo è innocuo, ma l'Ignoto al Castello no.'");
                attendi(3000);
                System.out.println("Michela: 'Hai la lingua sciolta, avventuriero. Questo ti servirà per farti strada alle Torri di Guardia.'");
                attendi(2000);
                System.out.println("\n>>> HAI SUPERATO LA PROVA! Ottieni l'approvazione del locale e +5 Carisma.");
                tony.modificaCarisma(5);
                attendi(2000);
                return true;
            } else {
                attendi(2000);
                System.out.println("\nLanci le monete, ma una cade a terra rotolando miseramente. Hai i soldi solo per un bicchiere d'acqua sporca.");
                attendi(2500);
                System.out.println("I mercenari ridono di te. Pupo ti lancia un dado in fronte urlando: 'Pezzente!'");
                attendi(2000);
                System.out.println("Michela scuote la testa: 'Torna quando avrai imparato le buone maniere. Ora sparisci.'");
                attendi(2000);
                System.out.println("Hai fallito l'approccio diplomatico.");
                attendi(2000);
                return false;
            }
        } else if (scelta == 2) {
            attendi(1000);
            System.out.println("\nTi avvicini a passi pesanti. Sferri un pugno violentissimo sul legno del bancone, facendo tremare i boccali di tutta la locanda.");
            if (tony.tentaAzione("Forza", 10)) {
                attendi(2500);
                System.out.println("\nIl tonfo echeggia nella stanza. Il bar cala in un silenzio tombale.");
                attendi(2000);
                System.out.println("Pupo si rintana sotto il tavolo. I mercenari abbassano lo sguardo sulle loro birre, fingendo di non averti visto.");
                attendi(2500);
                System.out.println("Michela non batte ciglio, ma nei suoi occhi c'è una scintilla di rispetto: 'Hai fegato, ragazzo. Qui comanda chi ha i pugni più duri.'");
                attendi(3000);
                System.out.println("Senza dire una parola, si china e ti allunga una fiala contenente un liquido rosso e denso.");
                attendi(2000);
                System.out.println("Michela: 'Prendi questa 'Pozione del Roxy'. Se vai al Castello, ti servirà più dell'oro.'");
                attendi(2000);
                System.out.println("\n>>> HAI SUPERATO LA PROVA! Ottieni la Pozione e +5 Forza.");
                tony.raccogli(new Oggetto("Pozione", "Cura 50 HP", -50, true));
                tony.modificaForza(5);
                attendi(2000);
                return true;
            } else {
                attendi(2000);
                System.out.println("\nSferri un pugno, ma colpisci l'angolo di ferro del bancone. Senti un 'crack' imbarazzante e stringi i denti per non urlare.");
                attendi(2500);
                System.out.println("Pupo scoppia in una risata fragorosa: 'L'ha preso lo spigolo! Maremma buhaiola!'");
                attendi(2000);
                System.out.println("Michela ti guarda con compassione: 'Il mio bancone è più duro di te. Mettiti del ghiaccio su quella mano e vattene.'");
                attendi(2500);
                System.out.println("\nVieni deriso dall'intera locanda. Esci dal Roxy Bar massaggiandoti la mano. Subisci 10 Danni.");
                tony.subisciDanno(10);
                attendi(2000);
                return false;
            }
        } else {
            attendi(1000);
            System.out.println("\nBalbetti qualcosa di incomprensibile. Michela ti indica la porta senza dire una parola.");
            attendi(2000);
            return false;
        }
    }

    public static boolean episodioPiazza(Protagonista tony) {
        System.out.println("\n=== IL MERCATO DELLE ILLUSIONI ===");
        attendi(1000);
        System.out.println("La Piazza brulica di persone. L'aria è un misto di spezie orientali e fumo denso.");
        attendi(1000);
        System.out.println("Ti fai largo a fatica tra la folla, finché una mano ossuta non ti afferra il mantello.");
        attendi(1000);
        System.out.println("È Ezio, il mercante più losco di Dublino. Ha un sorriso viscido e un banchetto pieno di cianfrusaglie.");
        attendi(1000);
        System.out.println("Ezio sussurra: 'Ehi, straniero! So che vai al Castello. Questa reliquia sacra ti renderà invincibile! Pagala o vattene!'");

        System.out.println("\n1. [Percezione] Aguzza la vista per smascherare la truffa e rubagli qualcosa di utile. (Richiede Agilità 10)");
        System.out.println("2. [Minaccia] Afferralo per il colletto e fagli sputare la merce vera. (Richiede Forza 15)");
        System.out.print("Scegli la tua mossa (1 o 2): ");
        int scelta = Leggi.unInt();

        if (scelta == 1) {
            attendi(1000);
            System.out.println("\nTi avvicini al banchetto, fingendo interesse per la patetica roccia dipinta che cerca di venderti...");
            if (tony.tentaAzione("Agilita", 10)) {
                attendi(1000);
                System.out.println("\nCon la coda dell'occhio noti una fiala che brilla di luce vera nascosta nella sua manica.");
                attendi(1000);
                System.out.println("Mentre Ezio gesticola, la tua mano scatta come un serpente e fai scivolare la fiala nella tua tasca.");
                attendi(1000);
                System.out.println("Ezio: 'Allora, compri o no? Bah, pezzente.' Ti allontani sorridendo col bottino.");
                attendi(1000);
                System.out.println("\n>>> HAI SUPERATO LA PROVA! Ottieni la Pozione Magica e +5 Agilità.");
                tony.raccogli(new Oggetto("Pozione Magica", "Cura 50 HP", -50, true));
                tony.modificaAgilita(5);
                return true;
            } else {
                attendi(1000);
                System.out.println("\nAllunghi la mano per frugare nelle sue tasche, ma sei goffo e fai cadere un mucchio di pentole.");
                attendi(1000);
                System.out.println("Ezio urla: 'AL LADRO! AL LADRO!'");
                attendi(1000);
                System.out.println("La folla inizia a guardarti male e sei costretto a fuggire a mani vuote tra i vicoli.");
                attendi(1000);
                System.out.println("Hai fallito il tentativo di furto.");
                attendi(1000);
                return false;
            }
        } else if (scelta == 2) {
            attendi(1000);
            System.out.println("\nNon hai tempo per questi giochetti. Afferri Ezio per le vesti logore e lo sollevi da terra con rabbia.");
            if (tony.tentaAzione("Forza", 15)) {
                attendi(1000);
                System.out.println("\nGli occhi del mercante si sbarrano per il terrore. I suoi piedi scalciano a vuoto.");
                attendi(1000);
                System.out.println("Ezio piagnucola: 'Calma! Era uno scherzo! Tieni la roba buona, ma lasciami andare!'");
                attendi(1000);
                System.out.println("Ti getta addosso una fiala curativa e scappa a gambe levate abbandonando il banchetto.");
                attendi(1000);
                System.out.println("\n>>> HAI SUPERATO LA PROVA! Ottieni la Pozione e +5 Forza.");
                tony.raccogli(new Oggetto("Pozione", "Cura 50 HP", -50, true));
                tony.modificaForza(5);
                attendi(2000);
                return true;
            } else {
                attendi(1000);
                System.out.println("\nProvi a sollevarlo, ma la tua presa cede. Ezio si libera con uno strattone e fischia forte.");
                attendi(1000);
                System.out.println("Due enormi scagnozzi incappucciati emergono dalla folla e ti riempiono di botte prima che tu possa estrarre l'arma.");
                attendi(1000);
                System.out.println("Ezio ride: 'Nessuno tocca Ezio senza pagare il prezzo!'");
                attendi(1000);
                System.out.println("\nLe guardie di Ezio ti pestano a sangue. Subisci 15 Danni.");
                attendi(2000);
                tony.subisciDanno(15);
                return false;
            }
        } else {
            attendi(1000);
            System.out.println("\nTi guardi intorno confuso, ignorando Ezio. Lui ti scambia per uno sprovveduto e se ne va a cercare un'altra vittima.");
            attendi(2000);
            return false;
        }
    }

    public static boolean episodioTorri(Protagonista tony) {
        System.out.println("\n=== IL GIUDIZIO DELLE TORRI ===");
        attendi(1000);
        System.out.println("Il vento freddo fischia tra le antiche pietre delle Torri di Guardia.");
        attendi(1500);
        System.out.println("Al centro del cortile, il Comandante Vasco passeggia nervosamente davanti a un patibolo in legno.");
        attendi(2000);
        System.out.println("In catene ci sono tre uomini dall'aspetto stravagante: tre giullari accusati di stonature moleste e disturbo della quiete pubblica.");
        attendi(2500);

        System.out.println("\nLigabue sospira guardando a ovest: 'Voglio tornare al Roxy Bar, c'era un'atmosfera decisamente migliore...'");
        attendi(2500);
        System.out.println("Jovanotti, saltellando sui talloni, cerca di sdrammatizzare: 'Ragazzi, pensate positivo! Magari è solo uno scherzo!'");
        attendi(2500);
        System.out.println("Morgan scuote la testa, profondamente indignato: 'Questo è un affronto all'arte! Voi soldati non capite il mio genio polifonico!'");
        attendi(3000);

        System.out.println("\nVasco sbatte il pugno sul tavolo e li zittisce con un urlo: 'Silenzio! Avete cantato l'ultima canzone. Il vostro tempo è scaduto!'");
        attendi(2500);
        System.out.println("Poi si gira verso di te, stringendo l'elsa del suo enorme spadone, e ti fulmina con lo sguardo: 'E tu chi saresti? Un loro fan?'");
        attendi(2000);

        System.out.println("\n1. [Diplomazia] Fai un discorso appassionato sull'arte per chiedere la grazia. (Richiede Carisma 15)");
        System.out.println("2. [Sfida] Estrai l'arma e sfida Vasco a duello per liberarli! (Richiede Forza 15)");
        System.out.print("Scegli la tua mossa (1 o 2): ");
        int scelta = Leggi.unInt();

        if (scelta == 1) {
            attendi(1000);
            System.out.println("\nFai un passo avanti, alzi le mani disarmato e inizi a parlare di libertà, musica e pietà...");
            if (tony.tentaAzione("Carisma", 15)) {
                attendi(2000);
                System.out.println("\nVasco ti ascolta in silenzio, quasi commosso. Poi abbassa l'arma e sbuffa: 'E va bene... mi hai convinto. Ma portateli via prima che cambi idea!'");
                attendi(3000);
                System.out.println("I tre giullari vengono liberati.");
                attendi(1500);
                System.out.println("Morgan ti si avvicina trionfante: 'Mio caro salvatore, la tua retorica è quasi paragonabile alla mia! Tieni questo oggetto, lo stavo analizzando ma ora è tuo.'");
                attendi(3000);
                System.out.println("Morgan inizia un discorso infinito sui microtoni e gli accordi diminuiti, ma tu afferri l'oggetto e scappi via prima di addormentarti.");
                attendi(3000);
                System.out.println("\n>>> HAI SUPERATO LA PROVA! Ottieni la Chiave Tattica e +5 Carisma.");
                tony.raccogli(new Oggetto("Chiave Tattica", "Evita le imboscate", 0, false));
                tony.modificaCarisma(5);
                attendi(2000);
                return true;
            } else {
                attendi(2000);
                System.out.println("\nInciampi sulle parole e perdi il filo del discorso. Vasco scoppia a ridere sguaiatamente.");
                attendi(2000);
                System.out.println("Vasco: 'Che discorso patetico! Guardie, portate via i prigionieri!'");
                attendi(2000);
                System.out.println("I giullari vengono trascinati via nelle segrete mentre Morgan urla: 'Incompetentiii!'");
                attendi(2000);
                System.out.println("Hai fallito l'azione diplomatica.");
                attendi(2000);
                return false;
            }
        } else if (scelta == 2) {
            attendi(1000);
            System.out.println("\nSfoderi l'arma con un gesto fulmineo e ti lanci verso il Comandante.");
            attendi(2000);
            System.out.println("Vasco sorride maliziosamente: 'Finalmente un po' di azione! Fammi vedere di che pasta sei fatto!'");
            if (tony.tentaAzione("Forza", 15)) {
                attendi(2000);
                System.out.println("\nLo scontro è brutale! Le lame scintillano creando scintille nel cortile, ma la tua potenza è travolgente.");
                attendi(2500);
                System.out.println("Con un colpo formidabile disarmi Vasco, che cade in ginocchio ansimando.");
                attendi(2000);
                System.out.println("Vasco: 'Sei forte... troppo forte. Hai vinto. I giullari sono tuoi.'");
                attendi(2000);
                System.out.println("I canzonieri fuggono a gambe levate. Raccogli da terra l'arma caduta a Vasco, una lama di pregevole fattura.");
                attendi(3000);
                System.out.println("\n>>> HAI SUPERATO LA PROVA! Ottieni l'arma di Vasco e +5 Forza.");
                tony.setDannoArma(35);
                tony.modificaForza(5);
                attendi(2000);
                return true;
            } else {
                attendi(2000);
                System.out.println("\nTi scagli contro Vasco, ma lui para il tuo colpo con irrisoria facilità.");
                attendi(2000);
                System.out.println("Con un rapido contrattacco, ti colpisce in pieno petto facendoti volare a terra.");
                attendi(2000);
                System.out.println("Vasco ti guarda dall'alto verso il basso con disprezzo: 'Sei solo chiacchiere. Fuori dalla mia vista!'");
                attendi(2500);
                System.out.println("\nVasco ti umilia davanti a tutti. Subisci 30 danni.");
                attendi(2000);
                tony.subisciDanno(30);
                return false;
            }
        } else {
            attendi(1000);
            System.out.println("\nRimani immobile e non fai una scelta valida. Vasco, infastidito, ti fa cacciare via dalle guardie.");
            attendi(2500);
            return false;
        }
    }

    public static void preCombattimentoPorta(Protagonista tony, Nemico guardia) {
        System.out.println("\n=== L'IMBOSCATA ALLA PORTA ===");
        attendi(1000);
        System.out.println("Ti avvicini ai pesanti cancelli della Porta della Città.");
        attendi(1500);
        System.out.println("Un gruppo di guardie con armature annerite ti sbarra la strada. I loro occhi sono vuoti, asserviti alla magia del Gigante.");
        attendi(2500);

        boolean haChiave = false;
        for (int i = 0; i < 10; i++) {
            Oggetto o = tony.getInventario().get(i);
            if (o != null && o.getNome().equals("Chiave Tattica")) haChiave = true;
        }

        System.out.println("\n1. [Tattica] Cerca un modo per usare l'ambiente a tuo vantaggio.");
        System.out.println("2. [Assalto] Carica a testa bassa sfoderando l'arma! (Richiede Forza 15)");
        System.out.print("Scegli la tua mossa (1 o 2): ");
        int scelta = Leggi.unInt();

        if (scelta == 1) {
            attendi(1000);
            System.out.println("\nScansi lo sguardo verso le mura e noti un argano arrugginito che regge una vecchia grata di ferro.");
            if (haChiave) {
                attendi(2000);
                System.out.println("Usi la 'Chiave Tattica' donata da Morgan per sbloccare di scatto l'ingranaggio!");
                attendi(2000);
                System.out.println("CRASH! La pesante grata crolla addosso alle guardie prima ancora di sguainare la spada.");
                attendi(1500);
                System.out.println("\n>>> VANTAGGIO TATTICO! Le guardie subiscono 30 Danni iniziali.");
                guardia.subisciDanno(30);
            } else {
                attendi(2000);
                System.out.println("Cerchi di manomettere l'argano, ma è bloccato. Ti servirebbe una chiave speciale...");
                attendi(2000);
                System.out.println("Il rumore allerta le guardie che ti attaccano di sorpresa! Subisci 10 Danni.");
                tony.subisciDanno(10);
            }
        } else if (scelta == 2) {
            attendi(1000);
            System.out.println("\nCon un urlo di battaglia, ti lanci con ferocia inaudita contro lo schieramento nemico!");
            if (tony.tentaAzione("Forza", 15)) {
                attendi(2000);
                System.out.println("Travolgi la prima linea con un impatto devastante, spezzando i loro scudi!");
                attendi(1500);
                System.out.println("\n>>> CARICA RIUSCITA! Le guardie subiscono 20 Danni iniziali.");
                guardia.subisciDanno(20);
            } else {
                attendi(2000);
                System.out.println("La tua carica è debole. I loro grossi scudi neri ti respingono violentemente all'indietro.");
                attendi(2000);
                System.out.println("Cadi a terra e subisci 15 Danni prima di poterti rialzare.");
                tony.subisciDanno(15);
            }
        } else {
            attendi(1000);
            System.out.println("\nEsiti troppo a lungo. Le guardie sogghignano e si preparano a colpirti.");
        }

        attendi(2000);
        System.out.println("\nCAPO DELLE GUARDIE: 'Il Gigante ci ha promesso la tua testa! PREPARATI A MORIRE!'");
        attendi(1500);
    }

    public static void combattiBossFinale(Protagonista tony, Nemico boss, Missione m, Map<String, Nemico> nemici, String luogo) {
        System.out.println("\n=======================================================");
        attendi(1500);
        System.out.println("   LO SCONTRO FINALE ALL'ALTARE INSANGUINATO");
        attendi(1500);
        System.out.println("=======================================================");
        attendi(2000);

        System.out.println("Varchi la soglia del Castello in rovina. L'aria è densa di cenere e odora di zolfo e morte.");
        attendi(2500);
        System.out.println("Sull'Altare Insanguinato, il Gigante Spettrale ti attende. È una mostruosità di ossa e ombre, alta tre volte un uomo normale.");
        attendi(3000);
        System.out.println("Il Gigante solleva la sua mazza colossale, indicandoti: 'Hai ucciso i miei servi... MA NON BASTERÀ A FERMARMI!'");
        attendi(3000);
        System.out.println("Gigante: 'LA TUA ANIMA BRUCERÀ IN ETERNO! PREPARATI A PERIRE, INSETTO!'\n");
        attendi(3000);

        int turno = 1;
        while (boss.getSalute() > 0 && tony.getSalute() > 0) {
            System.out.println("\n--- TURNO " + turno + " | Tony: " + tony.getSalute() + " HP | Boss: " + boss.getSalute() + " HP ---");
            attendi(1000);
            System.out.println("1. Attacco Rapido | 2. Assalto (Forza 15) | 3. Provoca (Carisma 15) | 4. Cura (Pozione)");
            System.out.print("Mossa: ");
            int mossa = Leggi.unInt();

            if (mossa == 4) {
                attendi(1000);
                System.out.println("\nArretri di un passo, schivando un fendente per un soffio, e cerchi freneticamente nello zaino...");
                attendi(1500);
                usaPozioneInCombattimento(tony);
                attendi(2000);
            } else if (mossa == 1) {
                attendi(1000);
                System.out.println("\nScatti in avanti, sfruttando la tua agilità, e sferri una serie di fendenti rapidi per bucare la sua guardia oscura!");
                attendi(2000);
                tony.attacca(boss);
                attendi(2000);
            } else if (mossa == 2) {
                attendi(1000);
                System.out.println("\nStringi l'arma con entrambe le mani. I tuoi muscoli si tendono allo spasimo mentre carichi un colpo devastante...");
                attendi(2500);
                if (tony.tentaAzione("Forza", 15)) {
                    attendi(1000);
                    System.out.println("COLPO DEVASTANTE! La tua lama fende l'aria con un fischio e squarcia in profondità l'armatura spettrale del mostro!");
                    attendi(2500);
                    boss.subisciDanno(45);
                } else {
                    attendi(1000);
                    System.out.println("Azione Fallita! L'arma sembra pesare tonnellate. Il Gigante para il tuo colpo goffo con un semplice gesto del braccio.");
                }
                attendi(2000);
            } else if (mossa == 3) {
                attendi(1000);
                System.out.println("\nPunti la spada verso il mostro e urli a pieni polmoni: 'Sei solo un'ombra codarda nascosta tra le macerie! Non fai paura a nessuno!'");
                attendi(3000);
                if (tony.tentaAzione("Carisma", 15)) {
                    attendi(1000);
                    System.out.println("Le tue parole fanno breccia! Il Gigante esita, accecato dall'ira, e la sua aura magica si sfalda perdendo potere! (-15 HP)");
                    attendi(3000);
                    boss.subisciDanno(15);
                } else {
                    attendi(1000);
                    System.out.println("La tua voce trema per la paura. Il Gigante ride con un boato che fa tremare le mura: 'LE TUE PAROLE SONO VENTO!'");
                }
                attendi(2500);
            } else {
                attendi(1000);
                System.out.println("\nEsiti per un istante di troppo, sopraffatto dal terrore... e perdi la tua finestra di attacco!");
                attendi(2000);
            }

            if (boss.getSalute() > 0) {
                attendi(1000);
                System.out.println("\nGli occhi del mostro brillano di malvagità. È il turno del Gigante...");
                attendi(2000);

                int danno;
                if (turno % 3 == 0) {
                    danno = 35;
                } else {
                    danno = 15;
                }
                if (turno % 3 == 0) {
                    System.out.println("Il Gigante solleva la sua immensa mazza e la fa schiantare al suolo! Un'onda d'urto di energia oscura ti investe in pieno!");
                } else {
                    System.out.println("Il Gigante sferra un rapido e violento attacco orizzontale con i suoi artigli d'ombra, cercando di falciarti a metà!");
                }
                attendi(3000);
                tony.subisciDanno(danno);
            }

            turno++;
            attendi(2000); // Pausa prima di ricominciare il ciclo
        }

        if (boss.getSalute() <= 0) {
            attendi(1500);
            System.out.println("\n>>> IL GIGANTE CACCIA UN URLO STRAZIANTE CHE FA TREMARE LE FONDAMENTA DEL CASTELLO! <<<");
            attendi(3000);
            System.out.println(">>> Il suo corpo si dissolve in un vortice di cenere e fumo nero, lasciando cadere la pesante mazza al suolo. <<<");
            attendi(3000);
            System.out.println(">>> L'alba sorge timida sulle rovine. HAI SALVATO LA CITTÀ DI DUBLINO! LA VITTORIA È TUA! <<<");
            attendi(3000);
            System.out.println("=======================================================");
            System.out.println("               >>> HAI FINITO IL GIOCO <<<");
            System.out.println("=======================================================");
            attendi(2500);
            System.out.println(">>> Copyright CAPPELLARO CESARE, ZANINI FRANCESCO e SMAL ANDRII <<<");
            System.out.println("=======================================================\n");
            nemici.remove(luogo);
            m.completaMissione();
        }
    }

    public static void usaPozioneInCombattimento(Protagonista tony) {
        int idx = -1;
        for (int i = 0; i < 10; i++) {
            Oggetto o = tony.getInventario().get(i);
            if (o != null && o.getNome().toLowerCase().contains("pozione")) { idx = i; break; }
        }
        if (idx != -1) {
            Oggetto p = tony.getInventario().usa(idx);
            tony.subisciDanno(p.getValore());
        } else {
            System.out.println("Non hai pozioni nello zaino!");
        }
    }
}