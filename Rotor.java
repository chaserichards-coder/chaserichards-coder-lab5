/**
 * Rotor class - models a single rotor that can rotate and map characters.
 */
public class Rotor {

    private String rotorValues;  // characters on the rotor
    private char startChar;      // starting top character
    private char currentTop;     // current top character

    /**
     * Make a rotor with values and starting position.
     */
    public Rotor(String v, char c) {
        this.rotorValues = new String(v);
        this.startChar = c;
        this.currentTop = rotorValues.charAt(0);

        // rotate until the startChar is on top
        while (this.currentTop != c) {
            this.rotate();
        }
    }

    /**
     * Rotate one step clockwise.
     * Return true if back at starting position.
     */
    public boolean rotate() {
        rotorValues = rotorValues.charAt(rotorValues.length() - 1) 
                    + rotorValues.substring(0, rotorValues.length() - 1);

        currentTop = rotorValues.charAt(0);
        return currentTop == startChar;
    }

    /**
     * Find index of a character.
     */
    public int indexOf(char c) {
        return rotorValues.indexOf(c);
    }

    /**
     * Get character at an index.
     */
    public char charAt(int idx) {
        return rotorValues.charAt(idx);
    }

    /** commented out ecause of no need for debugging
     * Show current rotor string (for debugging).
     */
    //@Override
    //public String toString() {
      //  return rotorValues;
    //}
}
