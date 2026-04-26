import java.util.Map;

public class MotoreEpisodi {

    private static boolean episodioLocandaFatto = false;
    private static boolean episodioBarFatto = false;
    private static boolean episodioPiazzaFatto = false;
    private static boolean episodioTorriFatto = false;

    public static void attendi(int millisecondi){
        try { Thread.sleep(millisecondi); } catch(InterruptedException e){}
    }

    public static void gestisciInterazione(String luogo, Protagonista tony, Nemico nemico, Map<String, Nemico> nemici){
        if(nemico != null){
            if(luogo.equals("City Gate")){
                System.out.println("\n[EXAMINE]");
                attendi(1000);
                System.out.println("You hide behind an overturned cart to study the situation.");
                attendi(2000);
                System.out.println("A handful of corrupted guards blocks the passage, their faces hidden by pitch-black helmets.");
                attendi(2500);
                System.out.println("They seem to be channeling a dark, pulsating energy toward the Castle to the south through an unholy ritual.");
                attendi(3000);
                System.out.println("As long as these guards remain alive, the Unknown will draw power from this energy link to protect itself...");
                attendi(2500);
                System.out.println("\n(Are you ready to break this curse? Press 5 to draw your weapon and face them!)");
            } else if(luogo.equals("Castle")){
                System.out.println("\n[EXAMINE] The Spectral Giant towers over the rubble.");
                attendi(1500);
                if(nemici.containsKey("City Gate")){
                    System.out.println("It is surrounded by a wall of red energy. It is UNASSAILABLE until the source of the energy is destroyed.");
                    attendi(3000);
                } else {
                    System.out.println("The barrier protecting it has vanished. It is now vulnerable!");
                    System.out.println("(Press 5 to start the final showdown!)");
                    attendi(3000);

                }
            }
            return;
        }

        if(luogo.equals("Roxy Bar") && !episodioLocandaFatto){
            boolean successo = episodioLocanda(tony);
            if(successo){
                episodioBarFatto = true;
                episodioLocandaFatto = true;
            } else {
                System.out.println("\n[!] You failed. Rest and try again! (Press 2 again)");
            }
        }
        else if(luogo.equals("Towers") && !episodioTorriFatto){
            if(episodioBarFatto){
                boolean successo = episodioTorri(tony);
                if(successo){
                    episodioTorriFatto = true;
                } else {
                    System.out.println("\n[!] You lost. Prepare better and try again! (Press 2 again)");
                }
            } else {
                System.out.println("\nTwo soldiers cross their halberds.");
                System.out.println("Guard: 'Vasco only accepts those who proved themselves tough at the Roxy Bar. Go away!'");
            }
        }
        else if(luogo.equals("square") && !episodioPiazzaFatto){
            boolean successo = episodioPiazza(tony);
            if(successo){
                episodioPiazzaFatto = true;
            } else {
                System.out.println("\n[!] You failed the action. Be more careful and try again!");
            }
        }
        else {
            System.out.println("\nYou have already completed everything possible in this area.");
        }
    }

    public static boolean episodioLocanda(Protagonista tony){
        System.out.println("\n=== THE ROXY BAR ===");
        attendi(1000);
        System.out.println("You push the heavy oak door and enter the Roxy Bar. There is thick smoke and a pungent smell of dark beer and rotten wood.");
        attendi(2000);
        System.out.println("The clinking of mugs and raucous laughter fills the tavern air.");
        attendi(1500);
        System.out.println("In a dark corner, a certain 'PUPO' is gambling fiercely with some shady figures, swearing in Tuscan every time the dice roll.");
        attendi(2500);
        System.out.println("Behind the counter, Michela, a bartender with fiery red hair, is cleaning a glass. She scrutinizes you with eyes that have seen too many brawls.");
        attendi(2500);
        System.out.println("Michela: 'We don't serve your kind here, stranger. Unless you have clinking gold or a good story to tell.'");
        attendi(2500);

        System.out.println("\n1. [Diplomacy] Buy a round for the whole tavern and ask about Pupo and the Castle. (Requires Charisma 10)");
        System.out.println("2. [Intimidation] Slam your fists on the counter and demand to know who runs this joint. (Requires Strength 10)");
        System.out.print("Choose your move (1 or 2): ");
        int scelta = Leggi.unInt();

        if(scelta == 1){
            attendi(1000);
            System.out.println("\nYou reach into your pouch and toss a few coins on the counter: 'A round of beer for everyone! And for the Tuscan gentleman in the back, your best vintage!'");
            if(tony.tentaAzione("Carisma", 10)){
                attendi(2500);
                System.out.println("\nThe tavern erupts in a roar of approval. The mercenaries raise their mugs to you.");
                attendi(2000);
                System.out.println("Pupo winks at you and sings an off-key song about luck.");
                attendi(2000);
                System.out.println("Michela smiles, revealing a finally sweet expression: 'You know how to make friends. Pupo is harmless, but the Unknown at the Castle is not.'");
                attendi(3000);
                System.out.println("Michela: 'You have a silver tongue, adventurer. This will help you make your way to the Guard Towers.'");
                attendi(2000);
                System.out.println("\n>>> YOU PASSED THE TEST! You get the tavern's approval and +5 Charisma.");
                tony.modificaCarisma(5);
                attendi(2000);
                return true;
            } else {
                attendi(2000);
                System.out.println("\nYou toss the coins, but one falls to the ground, rolling miserably. You only have money for a glass of dirty water.");
                attendi(2500);
                System.out.println("The mercenaries laugh at you. Pupo throws a die at your forehead, yelling: 'Beggar!'");
                attendi(2000);
                System.out.println("Michela shakes her head: 'Come back when you've learned some manners. Now get lost.'");
                attendi(2000);
                System.out.println("You failed the diplomatic approach.");
                attendi(2000);
                return false;
            }
        } else if(scelta == 2){
            attendi(1000);
            System.out.println("\nYou approach with heavy steps. You deliver a violent punch to the wooden counter, making the mugs of the whole tavern shake.");
            if(tony.tentaAzione("Forza", 10)){
                attendi(2500);
                System.out.println("\nThe thud echoes in the room. The bar falls into a dead silence.");
                attendi(2000);
                System.out.println("Pupo cowers under the table. The mercenaries lower their gaze to their beers, pretending not to have seen you.");
                attendi(2500);
                System.out.println("Michela doesn't bat an eye, but in her eyes there is a spark of respect: 'You've got guts, kid. Whoever has the hardest fists rules here.'");
                attendi(3000);
                System.out.println("Without saying a word, she bends down and hands you a vial containing a thick, red liquid.");
                attendi(2000);
                System.out.println("Michela: 'Take this 'Roxy Potion'. If you go to the Castle, you'll need it more than gold.'");
                attendi(2000);
                System.out.println("\n>>> YOU PASSED THE TEST! You obtain the Potion and +5 Strength.");
                tony.raccogli(new Oggetto("Pozione", "Heals 50 HP", -50, true));
                tony.modificaForza(5);
                attendi(2000);
                return true;
            } else {
                attendi(2000);
                System.out.println("\nYou throw a punch, but you hit the iron corner of the counter. You hear an embarrassing 'crack' and grit your teeth to keep from screaming.");
                attendi(2500);
                System.out.println("Pupo bursts into a roaring laugh: 'He hit the edge! Bloody hell!'");
                attendi(2000);
                System.out.println("Michela looks at you with pity: 'My counter is harder than you. Put some ice on that hand and get out.'");
                attendi(2500);
                System.out.println("\nYou are mocked by the whole tavern. You leave the Roxy Bar massaging your hand. You take 10 Damage.");
                tony.subisciDanno(10);
                attendi(2000);
                return false;
            }
        } else {
            attendi(1000);
            System.out.println("\nYou stammer something incomprehensible. Michela points to the door without saying a word.");
            attendi(2000);
            return false;
        }
    }

    public static boolean episodioPiazza(Protagonista tony){
        System.out.println("\n=== THE MARKET OF ILLUSIONS ===");
        attendi(1000);
        System.out.println("The Square is teeming with people. The air is a mix of oriental spices and thick smoke.");
        attendi(1000);
        System.out.println("You struggle through the crowd until a bony hand grabs your cloak.");
        attendi(1000);
        System.out.println("It's Ezio, the shadiest merchant in Dublin. He has a slimy smile and a stall full of junk.");
        attendi(1000);
        System.out.println("Ezio whispers: 'Hey, stranger! I know you're going to the Castle. This sacred relic will make you invincible! Pay up or get lost!'");

        System.out.println("\n1. [Perception] Sharpen your sight to expose the scam and steal something useful. (Requires Agility 10)");
        System.out.println("2. [Threat] Grab him by the collar and make him spit out the real goods. (Requires Strength 15)");
        System.out.print("Choose your move (1 or 2): ");
        int scelta = Leggi.unInt();

        if(scelta == 1){
            attendi(1000);
            System.out.println("\nYou approach the stall, feigning interest in the pathetic painted rock he's trying to sell you...");
            if(tony.tentaAzione("Agilita", 10)){
                attendi(1000);
                System.out.println("\nOut of the corner of your eye, you notice a vial glowing with real light hidden in his sleeve.");
                attendi(1000);
                System.out.println("While Ezio gestures, your hand strikes like a snake and you slip the vial into your pocket.");
                attendi(1000);
                System.out.println("Ezio: 'So, are you buying or not? Bah, beggar.' You walk away smiling with the loot.");
                attendi(1000);
                System.out.println("\n>>> YOU PASSED THE TEST! You obtain the Magic Potion and +5 Agility.");
                tony.raccogli(new Oggetto("Pozione Magica", "Heals 50 HP", -50, true));
                tony.modificaAgilita(5);
                return true;
            } else {
                attendi(1000);
                System.out.println("\nYou reach out to rummage through his pockets, but you're clumsy and knock over a pile of pots.");
                attendi(1000);
                System.out.println("Ezio screams: 'THIEF! THIEF!'");
                attendi(1000);
                System.out.println("The crowd starts glaring at you, and you are forced to flee empty-handed through the alleys.");
                attendi(1000);
                System.out.println("You failed the theft attempt.");
                attendi(1000);
                return false;
            }
        } else if(scelta == 2){
            attendi(1000);
            System.out.println("\nYou don't have time for these games. You grab Ezio by his tattered clothes and angrily lift him off the ground.");
            if(tony.tentaAzione("Forza", 15)){
                attendi(1000);
                System.out.println("\nThe merchant's eyes widen in terror. His feet kick at the air.");
                attendi(1000);
                System.out.println("Ezio whimpers: 'Easy! It was a joke! Take the good stuff, but let me go!'");
                attendi(1000);
                System.out.println("He throws a healing vial at you and runs away as fast as his legs can carry him, abandoning the stall.");
                attendi(1000);
                System.out.println("\n>>> YOU PASSED THE TEST! You obtain the Potion and +5 Strength.");
                tony.raccogli(new Oggetto("Pozione", "Heals 50 HP", -50, true));
                tony.modificaForza(5);
                attendi(2000);
                return true;
            } else {
                attendi(1000);
                System.out.println("\nYou try to lift him, but your grip slips. Ezio breaks free with a jerk and whistles loudly.");
                attendi(1000);
                System.out.println("Two huge hooded thugs emerge from the crowd and beat you up before you can draw your weapon.");
                attendi(1000);
                System.out.println("Ezio laughs: 'No one touches Ezio without paying the price!'");
                attendi(1000);
                System.out.println("\nEzio's guards beat you bloody. You take 15 Damage.");
                attendi(2000);
                tony.subisciDanno(15);
                return false;
            }
        } else {
            attendi(1000);
            System.out.println("\nYou look around confused, ignoring Ezio. He mistakes you for a fool and walks away to find another victim.");
            attendi(2000);
            return false;
        }
    }

    public static boolean episodioTorri(Protagonista tony){
        System.out.println("\n=== THE JUDGMENT OF THE TOWERS ===");
        attendi(1000);
        System.out.println("The cold wind whistles through the ancient stones of the Guard Towers.");
        attendi(1500);
        System.out.println("In the center of the courtyard, Commander Vasco paces nervously in front of a wooden scaffold.");
        attendi(2000);
        System.out.println("In chains are three extravagant-looking men: three jesters accused of annoying off-key singing and disturbing the peace.");
        attendi(2500);

        System.out.println("\nLigabue sighs, looking west: 'I want to go back to the Roxy Bar, the atmosphere was definitely better... '");
        attendi(2500);
        System.out.println("Jovanotti, bouncing on his heels, tries to lighten the mood: 'Guys, think positive! Maybe it's just a joke!'");
        attendi(2500);
        System.out.println("Morgan shakes his head, deeply indignant: 'This is an affront to art! You soldiers don't understand my polyphonic genius!'");
        attendi(3000);

        System.out.println("\nVasco slams his fist on the table and silences them with a shout: 'Silence! You've sung your last song. Your time is up!'");
        attendi(2500);
        System.out.println("Then he turns to you, gripping the hilt of his enormous broadsword, and glares at you: 'And who might you be? One of their fans?'");
        attendi(2000);

        System.out.println("\n1. [Diplomacy] Give a passionate speech about art to ask for a pardon. (Requires Charisma 15)");
        System.out.println("2. [Challenge] Draw your weapon and challenge Vasco to a duel to free them! (Requires Strength 15)");
        System.out.print("Choose your move (1 or 2): ");
        int scelta = Leggi.unInt();

        if(scelta == 1){
            attendi(1000);
            System.out.println("\nYou step forward, raise your hands unarmed, and start talking about freedom, music, and mercy...");
            if(tony.tentaAzione("Carisma", 15)){
                attendi(2000);
                System.out.println("\nVasco listens to you in silence, almost moved. Then he lowers his weapon and snorts: 'Fine... you convinced me. But take them away before I change my mind!'");
                attendi(3000);
                System.out.println("The three jesters are freed.");
                attendi(1500);
                System.out.println("Morgan approaches you triumphantly: 'My dear savior, your rhetoric is almost comparable to mine! Take this item, I was analyzing it but now it's yours.'");
                attendi(3000);
                System.out.println("Morgan begins an endless speech about microtones and diminished chords, but you grab the item and run away before falling asleep.");
                attendi(3000);
                System.out.println("\n>>> YOU PASSED THE TEST! You obtain the Chiave Tattica and +5 Charisma.");
                tony.raccogli(new Oggetto("Chiave Tattica", "Avoids ambushes", 0, false));
                tony.modificaCarisma(5);
                attendi(2000);
                return true;
            } else {
                attendi(2000);
                System.out.println("\nYou stumble over your words and lose your train of thought. Vasco bursts into loud laughter.");
                attendi(2000);
                System.out.println("Vasco: 'What a pathetic speech! Guards, take the prisoners away!'");
                attendi(2000);
                System.out.println("The jesters are dragged away to the dungeons while Morgan screams: 'Incompetentss!'");
                attendi(2000);
                System.out.println("You failed the diplomatic action.");
                attendi(2000);
                return false;
            }
        } else if(scelta == 2){
            attendi(1000);
            System.out.println("\nYou draw your weapon with a lightning-fast motion and lunge at the Commander.");
            attendi(2000);
            System.out.println("Vasco smiles maliciously: 'Finally, some action! Show me what you're made of!'");
            if(tony.tentaAzione("Forza", 15)){
                attendi(2000);
                System.out.println("\nThe clash is brutal! Blades flash, creating sparks in the courtyard, but your power is overwhelming.");
                attendi(2500);
                System.out.println("With a formidable blow, you disarm Vasco, who falls to his knees panting.");
                attendi(2000);
                System.out.println("Vasco: 'You are strong... too strong. You win. The jesters are yours.'");
                attendi(2000);
                System.out.println("The singers flee as fast as they can. You pick up Vasco's fallen weapon from the ground, a blade of exquisite workmanship.");
                attendi(3000);
                System.out.println("\n>>> YOU PASSED THE TEST! You obtain Vasco's weapon and +5 Strength.");
                tony.setDannoArma(35);
                tony.modificaForza(5);
                attendi(2000);
                return true;
            } else {
                attendi(2000);
                System.out.println("\nYou lunge at Vasco, but he parries your blow with laughable ease.");
                attendi(2000);
                System.out.println("With a quick counterattack, he strikes you squarely in the chest, sending you flying to the ground.");
                attendi(2000);
                System.out.println("Vasco looks down at you with contempt: 'You're all talk. Out of my sight!'");
                attendi(2500);
                System.out.println("\nVasco humiliates you in front of everyone. You take 30 damage.");
                attendi(2000);
                tony.subisciDanno(30);
                return false;
            }
        } else {
            attendi(1000);
            System.out.println("\nYou stand still and don't make a valid choice. Vasco, annoyed, has the guards throw you out.");
            attendi(2500);
            return false;
        }
    }

    public static void preCombattimentoPorta(Protagonista tony, Nemico guardia){
        System.out.println("\n=== THE AMBUSH AT THE GATE ===");
        attendi(1000);
        System.out.println("You approach the heavy gates of the City Gate.");
        attendi(1500);
        System.out.println("A group of guards with blackened armor blocks your path. Their eyes are empty, enslaved by the Giant's magic.");
        attendi(2500);

        boolean haChiave = false;
        for(int i = 0; i < 10; i++){
            Oggetto o = tony.getInventario().get(i);
            if(o != null && o.getNome().equals("Chiave Tattica")) haChiave = true;
        }

        System.out.println("\n1. [Tactics] Look for a way to use the environment to your advantage.");
        System.out.println("2. [Assault] Charge head down with your weapon drawn! (Requires Strength 15)");
        System.out.print("Choose your move (1 or 2): ");
        int scelta = Leggi.unInt();

        if(scelta == 1){
            attendi(1000);
            System.out.println("\nYou glance toward the walls and notice a rusty winch holding up an old iron grate.");
            if(haChiave){
                attendi(2000);
                System.out.println("You use the 'Chiave Tattica' given by Morgan to snap the gear open!");
                attendi(2000);
                System.out.println("CRASH! The heavy grate collapses on the guards before they even draw their swords.");
                attendi(1500);
                System.out.println("\n>>> TACTICAL ADVANTAGE! The guards take 30 initial Damage.");
                guardia.subisciDanno(30);
            } else {
                attendi(2000);
                System.out.println("You try to tamper with the winch, but it's jammed. You would need a special key...");
                attendi(2000);
                System.out.println("The noise alerts the guards who attack you by surprise! You take 10 Damage.");
                tony.subisciDanno(10);
            }
        } else if(scelta == 2){
            attendi(1000);
            System.out.println("\nWith a battle cry, you launch yourself with unprecedented ferocity against the enemy formation!");
            if(tony.tentaAzione("Forza", 15)){
                attendi(2000);
                System.out.println("You overrun the front line with a devastating impact, shattering their shields!");
                attendi(1500);
                System.out.println("\n>>> CHARGE SUCCESSFUL! The guards take 20 initial Damage.");
                guardia.subisciDanno(20);
            } else {
                attendi(2000);
                System.out.println("Your charge is weak. Their large black shields violently push you back.");
                attendi(2000);
                System.out.println("You fall to the ground and take 15 Damage before you can get back up.");
                tony.subisciDanno(15);
            }
        } else {
            attendi(1000);
            System.out.println("\nYou hesitate too long. The guards sneer and prepare to strike.");
        }

        attendi(2000);
        System.out.println("\nCAPTAIN OF THE GUARDS: 'The Giant promised us your head! PREPARE TO DIE!'");
        attendi(1500);
    }

    public static void combattiBossFinale(Protagonista tony, Nemico boss, Missione m, Map<String, Nemico> nemici, String luogo){
        System.out.println("\n=======================================================");
        attendi(1500);
        System.out.println("   THE FINAL SHOWDOWN AT THE BLOODY ALTAR");
        attendi(1500);
        System.out.println("=======================================================");
        attendi(2000);

        System.out.println("You cross the threshold of the ruined Castle. The air is thick with ash and smells of sulfur and death.");
        attendi(2500);
        System.out.println("On the Bloody Altar, the Spectral Giant awaits you. It is a monstrosity of bones and shadows, three times as tall as a normal man.");
        attendi(3000);
        System.out.println("The Giant raises its colossal mace, pointing at you: 'You killed my servants... BUT IT WON'T BE ENOUGH TO STOP ME!'");
        attendi(3000);
        System.out.println("Giant: 'YOUR SOUL WILL BURN ETERNALLY! PREPARE TO PERISH, INSECT!'\n");
        attendi(3000);

        int turno = 1;
        while(boss.getSalute() > 0 && tony.getSalute() > 0){
            System.out.println("\n--- TURN " + turno + " | Tony: " + tony.getSalute() + " HP | Boss: " + boss.getSalute() + " HP ---");
            attendi(1000);
            System.out.println("1. Quick Attack | 2. Assault (Strength 15) | 3. Taunt (Charisma 15) | 4. Heal (Potion)");
            System.out.print("Move: ");
            int mossa = Leggi.unInt();

            if(mossa == 4){
                attendi(1000);
                System.out.println("\nYou step back, dodging a slash by a hair's breadth, and frantically search your backpack...");
                attendi(1500);
                usaPozioneInCombattimento(tony);
                attendi(2000);
            } else if(mossa == 1){
                attendi(1000);
                System.out.println("\nYou dash forward, using your agility, and deliver a series of quick slashes to pierce its dark guard!");
                attendi(2000);
                tony.attacca(boss);
                attendi(2000);
            } else if(mossa == 2){
                attendi(1000);
                System.out.println("\nYou grip your weapon with both hands. Your muscles tense to the limit as you charge a devastating blow...");
                attendi(2500);
                if(tony.tentaAzione("Forza", 15)){
                    attendi(1000);
                    System.out.println("DEVASTATING BLOW! Your blade cleaves the air with a hiss and deeply tears the monster's spectral armor!");
                    attendi(2500);
                    boss.subisciDanno(45);
                } else {
                    attendi(1000);
                    System.out.println("Action Failed! The weapon seems to weigh tons. The Giant parries your clumsy blow with a simple wave of its arm.");
                }
                attendi(2000);
            } else if(mossa == 3){
                attendi(1000);
                System.out.println("\nYou point your sword at the monster and yell at the top of your lungs: 'You're just a cowardly shadow hiding in the rubble! You don't scare anyone!'");
                attendi(3000);
                if(tony.tentaAzione("Carisma", 15)){
                    attendi(1000);
                    System.out.println("Your words hit the mark! The Giant hesitates, blinded by wrath, and its magic aura crumbles, losing power! (-15 HP)");
                    attendi(3000);
                    boss.subisciDanno(15);
                } else {
                    attendi(1000);
                    System.out.println("Your voice trembles with fear. The Giant laughs with a roar that shakes the walls: 'YOUR WORDS ARE WIND!'");
                }
                attendi(2500);
            } else {
                attendi(1000);
                System.out.println("\nYou hesitate for a moment too long, overwhelmed by terror... and miss your attack window!");
                attendi(2000);
            }

            if(boss.getSalute() > 0){
                attendi(1000);
                System.out.println("\nThe monster's eyes glow with malice. It is the Giant's turn...");
                attendi(2000);

                int danno;
                if(turno % 3 == 0){
                    danno = 35;
                } else {
                    danno = 15;
                }
                if(turno % 3 == 0){
                    System.out.println("The Giant raises its immense mace and slams it into the ground! A shockwave of dark energy hits you head-on!");
                } else {
                    System.out.println("The Giant unleashes a rapid and violent horizontal attack with its shadow claws, trying to cut you in half!");
                }
                attendi(3000);
                tony.subisciDanno(danno);
            }

            turno++;
            attendi(2000);
        }

        if(boss.getSalute() <= 0){
            attendi(1500);
            System.out.println("\n>>> THE GIANT LETS OUT AN AGONIZING SCREAM THAT SHAKES THE FOUNDATIONS OF THE CASTLE! <<<");
            attendi(3000);
            System.out.println(">>> Its body dissolves into a vortex of ash and black smoke, dropping the heavy mace to the ground. <<<");
            attendi(3000);
            System.out.println(">>> Dawn rises timidly over the ruins. YOU HAVE SAVED THE CITY OF DUBLINO! VICTORY IS YOURS! <<<");
            attendi(3000);
            System.out.println("=======================================================");
            System.out.println("               >>> YOU HAVE FINISHED THE GAME <<<");
            System.out.println("=======================================================");
            attendi(2500);
            System.out.println(">>> Copyright CAPPELLARO CESARE, ZANINI FRANCESCO e SMAL ANDRII <<<");
            System.out.println("=======================================================\n");
            nemici.remove(luogo);
            m.completaMissione();
        }
    }

    public static void usaPozioneInCombattimento(Protagonista tony){
        int idx = -1;
        for(int i = 0; i < 10; i++){
            Oggetto o = tony.getInventario().get(i);
            if(o != null && o.getNome().toLowerCase().contains("pozione")){ idx = i; break; }
        }
        if(idx != -1){
            Oggetto p = tony.getInventario().usa(idx);
            tony.subisciDanno(p.getValore());
        } else {
            System.out.println("You don't have any potions in your backpack!");
        }
    }
}