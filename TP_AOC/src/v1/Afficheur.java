package v1;

import java.util.*;

/**
 * 
 */
public interface Afficheur {

    /**
     * @param numLED
     */
    public void allumerLED(int numLED);

    /**
     * @param numLED
     */
    public void eteindreLED(int numLED);

    /**
     * Affiche un entier sur l'afficheur externe du métronome
     * @param valeurTempo
     */
    public void afficherTempo(int valeurTempo);

}