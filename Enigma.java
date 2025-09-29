/**
 * Enigma class - simple Enigma machine with 3 rotors.
 * Each character is encrypted/decrypted and then rotors rotate.
 */
public class Enigma {

    // Array of 5 predefined rotor configurations (hardcoded from the lab instructions)
    private String rotorInit[] = {
        "#GNUAHOVBIPWCJQXDKRYELSZFMT",
        "#EJOTYCHMRWAFKPUZDINSXBGLQV",
        "#BDFHJLNPRTVXZACEGIKMOQSUWY",
        "#NWDKHGXZVRIFJBLMAOPSCYUTQE",
        "#TGOWHLIFMCSZYRVXQABUPEJKND"
    };

    // Array that will hold the 3 active rotors (chosen by user when machine is built)
    private Rotor rotors[];

    /**
     * Constructor - sets up the Enigma machine with 3 chosen rotors and their start positions.
     *
     * @param id1   which rotor to use for the inner rotor (1–5)
     * @param id2   which rotor to use for the middle rotor (1–5)
     * @param id3   which rotor to use for the outer rotor (1–5)
     * @param start string of 3 characters for starting positions of the rotors
     */
    public Enigma(int id1, int id2, int id3, String start) {
        rotors = new Rotor[3];  // make space for 3 rotors

        // Create the inner rotor: use rotorInit[id1-1] and set its top char
        rotors[0] = new Rotor(rotorInit[id1 - 1], start.charAt(0));

        // Create the middle rotor: use rotorInit[id2-1] and set its top char
        rotors[1] = new Rotor(rotorInit[id2 - 1], start.charAt(1));

        // Create the outer rotor: use rotorInit[id3-1] and set its top char
        rotors[2] = new Rotor(rotorInit[id3 - 1], start.charAt(2));
    }

    /**
     * Encrypt a given message using the rotor substitution steps.
     *
     * @param message the plaintext message
     * @return encrypted message
     */
    public String encrypt(String message) {
        StringBuilder result = new StringBuilder(); // build result string efficiently

        // Loop through each character of the input message
        for (char c : message.toCharArray()) {
            // Step 1: Find index of char in inner rotor
            int idx = rotors[0].indexOf(c);

            // Step 2: Map to same index in outer rotor
            char mapped = rotors[2].charAt(idx);

            // Step 3: Find mapped char in middle rotor
            idx = rotors[1].indexOf(mapped);

            // Step 4: Map that index again in outer rotor → final encrypted char
            char out = rotors[2].charAt(idx);

            // Add encrypted char to result
            result.append(out);

            // Rotate the rotors after each character
            rotate();
        }
        return result.toString(); // return the encrypted string
    }

    /**
     * Decrypt a given message (reverse process of encrypt).
     */
    public String decrypt(String message) {
        StringBuilder result = new StringBuilder(); // build result string efficiently using stringbuilder

        // Loop through each character of the encrypted message
        for (char c : message.toCharArray()) {
            // Step 1: Find index of encrypted char in outer rotor
            int idx = rotors[2].indexOf(c);

            // Step 2: Use same index to get char from middle rotor
            char mapped = rotors[1].charAt(idx);

            // Step 3: Find index of that char in outer rotor
            idx = rotors[2].indexOf(mapped);

            // Step 4: Map that index in inner rotor → final decrypted char
            char out = rotors[0].charAt(idx);

            // Add decrypted char to result
            result.append(out);

            // Rotate the rotors after each character
            rotate();
        }
        return result.toString(); // return the decrypted string
    }

    /**
     * Rotate the rotors like an odometer:
     * - Inner rotor rotates every time
     * - If inner rotor makes a full rotation, middle rotor rotates
     * - If middle rotor also makes a full rotation, outer rotor rotates
     */
    private void rotate() {
        if (rotors[0].rotate()) {     // rotate inner rotor
            if (rotors[1].rotate()) { // rotate middle rotor if needed
                rotors[2].rotate();   // rotate outer rotor if needed
            }
        }
    }
}
