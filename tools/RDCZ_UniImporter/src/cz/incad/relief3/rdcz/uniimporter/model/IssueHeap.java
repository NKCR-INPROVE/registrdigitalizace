/* *****************************************************************************
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.incad.relief3.rdcz.uniimporter.model;

import cz.incad.relief3.rdcz.uniimporter.model.enums.IssueStateEnum;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *******************************************************************************
 * Třída zodpovídá za postupné zpracování souborů, které prošly validační
 * kontrolou názvu souboru.
 *
 * @author martin
 */
public class IssueHeap {

    private static final Logger LOG = Logger.getLogger(IssueHeap.class.getName());
    private static final IssueHeap _instance = new IssueHeap();
    private List<OneIssue> lIssues;

    /**
     ***************************************************************************
     * Privátní konstruktor - třída je definována jako 'Jedináček'.
     */
    private IssueHeap() {
        LOG.info("Initializing...");
        this.lIssues = new LinkedList<OneIssue>();
    }

    /**
     ***************************************************************************
     * Třída je nadefinovaná jako 'Jedináček', metoda vrátí vždy odkaz na
     * stejnou instanci třídy.
     *
     * @return
     */
    public static IssueHeap getInstance() {
        return _instance;
    }

    /**
     ***************************************************************************
     * Metoda přidá do seznamu nové issue.
     *
     * @param issue
     */
    public void addIssue(OneIssue issue) {
        LOG.log(Level.INFO, "Adding new issue.");
        synchronized (this) {
            this.lIssues.add(issue);
        }
    }

    /**
     ***************************************************************************
     *
     * @param state
     * @return
     */
    public OneIssue getOneIssue(IssueStateEnum state) {
        try {
            synchronized (this) {
                for (int i = 0; i < this.lIssues.size(); i++) {
                    if (lIssues.get(i).issueState.compareTo(state) == 0) {
                        return lIssues.get(i);
                    }
                }
                return null;
            }

        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Neocekavana chyba: " + ex.getMessage(), ex);
            //Vracíme null, jako by se nic nenašlo.
            return null;
        }
    }

    /**
     ***************************************************************************
     *
     * @param state
     * @param dedicatedSigla
     * @return
     */
    public OneIssue getOneIssue(IssueStateEnum state, String dedicatedSigla) {
        try {
            synchronized (this) {
                for (int i = 0; i < this.lIssues.size(); i++) {
                    if (lIssues.get(i).issueState.compareTo(state) == 0 && dedicatedSigla.equalsIgnoreCase(lIssues.get(i).fileSigla)) {
                        return lIssues.get(i);
                    }
                }
                return null;
            }

        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Neocekavana chyba: " + ex.getMessage(), ex);
            //Vracíme null, jako by se nic nenašlo.
            return null;
        }
    }

    /**
     ***************************************************************************
     *
     * @return
     */
    public int size() {
        synchronized (this) {
            return this.lIssues.size();
        }
    }

    /**
     ***************************************************************************
     *
     * @param issue
     * @return
     */
    public boolean removeIssue(OneIssue issue) {
        return this.lIssues.remove(issue);
    }

}
