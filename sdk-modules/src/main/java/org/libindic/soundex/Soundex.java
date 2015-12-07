package org.libindic.soundex;

import org.libindic.common.CharacterMap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by sujith on 19/5/14.
 */
public class Soundex {

    private static Map<String, List<Character>> soundexMap = new HashMap<String, List<Character>>();

    static {
        soundexMap.put("soundex_en", Arrays.asList('0', '1', '2', '3', '0', '1', '2', '0', '0', '2', '2', '4', '5', '5', '0', '1', '2', '6', '2', '3', '0', '1', '0', '2', '0', '2'));
        soundexMap.put("soundex", Arrays.asList('0', 'N', '0', '0', 'A', 'A', 'B', 'B', 'C', 'C', 'P', 'Q', '0', 'D', 'D', 'D', 'E', 'E', 'E', 'E', 'F', 'F', 'F', 'F', 'G', 'H', 'H', 'H', 'H', 'G', 'I', 'I', 'I', 'I', 'J', 'K', 'K', 'K', 'K', 'L', 'L', 'M', 'M', 'M', 'M', 'N', 'O', 'P', 'P', 'Q', 'Q', 'Q', 'R', 'S', 'S', 'S', 'T', '0', '0', '0', '0', 'A', 'B', 'B', 'C', 'C', 'P', 'P', 'E', 'D', 'D', 'D', 'D', 'E', 'E', 'E', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'E', '0', '0', '0', '0', '0', '0', '0', '0', 'P', 'Q', 'Q', 'Q', '0', '0', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'J', 'J', 'Q', 'P', 'P', 'F'));
    }


    /**
     * Return the soundex code for given character
     *
     * @param ch Character whose soundex code is needed
     * @return Returns soundex code if character is found in charmap
     * else returns 0
     */
    public char soundexCode(char ch) {
        String lang = CharacterMap.getLanguage(ch);
        try {
            if (lang.equals("en_US")) {
                return Soundex.soundexMap.get("soundex_en").get(CharacterMap.charmap.get(lang).indexOf(ch));
            } else {
                return Soundex.soundexMap.get("soundex").get(CharacterMap.charmap.get(lang).indexOf(ch));
            }
        } catch (Exception e) {
            return '0';
        }
    }

    /**
     * Calculate soundex of given string.
     * This function calculates soundex for Indian language string
     * as well as English string.
     * Length default value 8
     *
     * @param name String whose Soundex value to be calculated
     * @return Soundex string of `name'
     */
    public String soundex(String name) {
        return soundex(name, 8);
    }

    /**
     * Calculate soundex of given string.
     * This function calculates soundex for Indian language string
     * as well as English string.
     * Length default value 8
     *
     * @param name   String whose Soundex value to be calculated
     * @param length Length of final Soundex string, if soundex
     *               caculated is more than this it will be
     *               truncated to length.
     * @return Soundex string of `name'
     */
    public String soundex(String name, int length) {

        if (name == null || name.length() == 0) return null;

        StringBuffer sndx = new StringBuffer("");
        char fc = name.charAt(0);

        for (char c : (name.substring(1).toLowerCase(Locale.getDefault())).toCharArray()) {
            char d = soundexCode(c);

            if (d == '0') {
                continue;
            }

            if (sndx.length() == 0) {
                sndx.append(d);
            } else if (d != sndx.charAt(sndx.length() - 1)) {
                sndx.append(d);
            }
        }
        sndx.insert(0, fc);

        if (CharacterMap.getLanguage(name.charAt(0)) == "en_US") {
            return sndx.toString();
        }

        if (sndx.length() < length) {
            while (sndx.length() != length) {
                sndx.append('0');
            }
            return sndx.substring(0, length);
        }
        return sndx.substring(0, length);
    }

    /**
     * Compare soundex of given strings
     * This function checks if 2 given strings are phonetically
     * sounds same by doing soundex code comparison
     *
     * @param string1 First string for comparison
     * @param string2 Second string for comparison
     * @return Returns 0 if both strings are same, 1 if strings
     * sound phonetically same, 2 if strings are
     * phonetically not same. We can't perform English
     * cross language comparision if English string is
     * passed as one function will return -1.
     */
    public int compare(String string1, String string2) {
        if (string1 == null || string2 == null ||
                string1.length() == 0 || string2.length() == 0) {
            return -1;
        }

        if (string1.equals(string2)) {
            return 0;
        }

        String stringLang1 = CharacterMap.getLanguage(string1.charAt(0));
        String stringLang2 = CharacterMap.getLanguage(string2.charAt(0));

        if ((stringLang1.equals("en_US") && !stringLang2.equals("en_US")) ||
                (!stringLang1.equals("en_US") && stringLang2.equals("en_US"))) {
            return -1;
        }

        String soundex1 = soundex(string1);
        String soundex2 = soundex(string2);

        if (soundex1.substring(1).equals(soundex2.substring(1))) {
            return 1;
        }
        return 2;
    }
}
