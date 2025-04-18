/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

/**
 *
 * @author hakim
 */
import adt.LinkedList;
import control.MatchingEngineController;
import entities.Match;


public class MatchingEngineUI {
    private MatchingEngineController control;

    public MatchingEngineUI(MatchingEngineController control) {
        this.control = control;
    }

    public void displayMatches() {
        LinkedList<Match> matches = control.getMatches();
        if (matches.isEmpty()) {
            System.out.println("No matches found.");
        } else {
            System.out.println("Matches:");
            for (Match match : matches) {
                System.out.println(match);
            }
        }
    }
}
