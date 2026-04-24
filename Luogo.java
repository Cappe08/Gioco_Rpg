/**
 * Rappresenta un singolo luogo nel mondo di gioco.
 *
 * <p><b>Responsabilita' (Single Responsibility):</b> memorizzare le
 * informazioni descrittive di una locazione (nome e descrizione).
 * Non contiene logica di gioco, ne' di posizionamento, ne' di
 * movimento.</p>
 *
 * <p><b>Design scelto:</b> POJO (Plain Old Java Object) con attributi
 * {@code final} per garantire che i dati essenziali non vengano
 * modificati dopo la creazione. Questo lo rende sicuro da condividere
 * tra piu' parti del programma senza rischi di modifiche accidentali.</p>
 *
 * <p><b>Alternative possibili (solo commentate):</b></p>
 * <ul>
 *   <li>Aggiungere un campo {@code boolean attraversabile} se in futuro
 *       si vogliono luoghi su cui il giocatore non puo' entrare
 *       (es. muri, fiumi, montagne) direttamente a livello di Luogo.</li>
 *   <li>Aggiungere una lista di oggetti o PNG presenti nel luogo,
 *       delegando pero' la gestione degli stessi ad altre classi per
 *       non violare il principio di responsabilita' singola.</li>
 * </ul>
 */
public class Luogo {
    private final String nome;
    private final String descrizione;

    /**
     * Crea un nuovo luogo.
     *
     * @param nome        il nome visualizzato del luogo (es. "Taverna")
     * @param descrizione la descrizione testuale mostrata al giocatore
     */
    public Luogo(String nome, String descrizione) {
        this.nome = nome;
        this.descrizione = descrizione;
    }

    public String getNome() {
        return nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Restituisce una rappresentazione leggibile del luogo.
     * Utile per essere stampata direttamente nel terminale.
     */
    @Override
    public String toString() {
        return nome + " - " + descrizione;
    }
}

