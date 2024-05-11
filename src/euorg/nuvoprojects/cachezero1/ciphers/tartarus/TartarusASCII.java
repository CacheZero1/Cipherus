package euorg.nuvoprojects.cachezero1.ciphers.tartarus;

import java.util.HashMap;

public class TartarusASCII {

    private char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private HashMap<Character, Integer> asciiHashMap;
    private HashMap<Integer, Character> letterMap;

    // Init the Map
    public TartarusASCII() {

        asciiHashMap = new HashMap<Character, Integer>();
        letterMap = new HashMap<Integer, Character>();

        asciiHashMap.put(' ', 0);
        letterMap.put(0, ' ');

        asciiHashMap.put(',', 1);
        letterMap.put(1, ',');

        asciiHashMap.put('.', 2);
        letterMap.put(2, '.');

        asciiHashMap.put(';', 3);
        letterMap.put(3, ';');

        asciiHashMap.put('\'', 4);
        letterMap.put(4, '\'');

        asciiHashMap.put('\n', 5);
        letterMap.put(5, '\n');

        for (int start = 65; start < 91; start++) {

            asciiHashMap.put(alphabet[start - 65], start);
            letterMap.put(start, alphabet[start - 65]);

        }

        System.out.println(asciiHashMap);
        System.out.println(letterMap);
        

    }
    
    // Translate letters into ASCII
    public Integer translateToASCII(char character) {

        return asciiHashMap.get(character);

    }

    // Translate ASCII into letters
    public Character translateFromASCII(int value) {

        return letterMap.get(value);

    }

}
