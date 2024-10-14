public class CaesarCiper {
    private String alphabet;

    public CaesarCiper(String alphabet) {
        this.alphabet = alphabet;
    }

    public String encrypt(String input, int key) {
        StringBuilder result = new StringBuilder();
        input = input.toLowerCase();

        for (char character : input.toCharArray()) {
            int idx = alphabet.indexOf(character);
            if (idx != -1) {
                int encryptedIdx = (idx + key) % alphabet.length();
                result.append(alphabet.charAt(encryptedIdx));
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }


    public String decrypt(String input, int key) {
        StringBuilder result = new StringBuilder();
        input = input.toLowerCase();

        for (char character : input.toCharArray()) {
            int idx = alphabet.indexOf(character);
            if (idx != -1) {

                int decryptedIdx = (idx - key) % alphabet.length();
                if (decryptedIdx < 0) {
                    decryptedIdx += alphabet.length();
                }
                result.append(alphabet.charAt(decryptedIdx));
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }

}

