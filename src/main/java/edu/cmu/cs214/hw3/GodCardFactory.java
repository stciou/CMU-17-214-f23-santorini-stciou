package edu.cmu.cs214.hw3;

import edu.cmu.cs214.hw3.godcards.Demeter;
import edu.cmu.cs214.hw3.godcards.Hephaestus;
import edu.cmu.cs214.hw3.godcards.Minotaur;
import edu.cmu.cs214.hw3.godcards.Pan;
import edu.cmu.cs214.hw3.godcards.GodCard;

public class GodCardFactory {

    public static GodCard createGodCard(String godCardName, Board board) {
        switch (godCardName.toLowerCase()) {
            case "demeter":
                return new Demeter();
            case "hephaestus":
                return new Hephaestus();
            case "minotaur":
                return new Minotaur(board);
            case "pan":
                return new Pan();
            default:
                throw new IllegalArgumentException("Unknown God Card: " + godCardName);
        }
    }
}