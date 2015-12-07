package org.libindic.inexactsearch;

import org.libindic.soundex.Soundex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sujith on 15/7/14.
 */
public class InexactSearch {

    private Soundex soundex;

    private static final String MODULE_NAME = "Inexact Search";
    private static final String MODULE_INFORMATION = "This class provides methods for fuzzy " +
            "searching using word distance as well as phonetics.";

    /**
     * Constructor
     */
    public InexactSearch() {
        soundex = new Soundex();
    }

    private double countCommon(List<String> shrtBigr, List<String> lngBigr, double average) {
        double common = 0.0;
        for (String bigr : shrtBigr) {
            int indexShrt = shrtBigr.indexOf(bigr);
            if (lngBigr.contains(bigr)) {
                int indexLng = lngBigr.indexOf(bigr);
                if (indexLng == indexShrt) {
                    common += 1.0;
                } else {
                    double dislocation = 1.0 * (indexLng - indexShrt) / average;
                    if (dislocation < 0) {
                        dislocation *= -1;
                    }
                    common += 1.0 - dislocation;
                }
            }
        }
        return common;
    }

    /**
     * This function is used to create bigram
     *
     * @param string string text
     * @return list of strings
     */
    private List<String> createBigram(String string) {
        List<String> bigram = new ArrayList<String>();
        for (int i = 1; i < string.length(); i++) {
            bigram.add(string.substring(i - 1, i + 1));
        }
        return bigram;
    }

    /**
     * @param str1 string 1 for comparison
     * @param str2 string 2 for comparison
     * @return score between 0.0 and 1.0
     */
    public double bigramAverage(String str1, String str2) {
        if (str1.equals(str2)) {
            return 1;
        }
        List<String> bigr1 = createBigram(str1);
        List<String> bigr2 = createBigram(str2);

        double average = (bigr1.size() + bigr2.size()) / 2.0;
        double common = 0.0;

        if (bigr1.size() < bigr2.size()) {
            common = countCommon(bigr1, bigr2, average);
        } else {
            common = countCommon(bigr2, bigr1, average);
        }
        return common / average;
    }

    /**
     * Compare strings using soundex if not possible gives biggram avearage.
     *
     * @param string1 string 1 for comparison.
     * @param string2 string 2 for comparison.
     * @return score between 0.0 and 1.0
     */
    public double compare(String string1, String string2) {
        double weight = 0.0;

        if (string1.equals(string2)) {
            return 1.0;
        }
        int soundexMatch = soundex.compare(string1, string2);

        if (soundexMatch == 1) {
            weight = 0.9;
        }

        if (weight == 0.0) {
            return bigramAverage(string1, string2);
        } else {
            return weight;
        }
    }

    /**
     * Searches for the key in the given text.
     *
     * @param text text in which search has to be done.
     * @param key  key which has to be searched
     * @return A dictionary with words in the string as keys and
     * the score against the key as the value
     */
    public Map<String, Double> search(String text, String key) {
        key = key.trim();
        String[] words = text.split(" ");
        Map<String, Double> searchResults = new HashMap<String, Double>();
        for (String word : words) {
            word = word.trim();
            searchResults.put(word, compare(word, key));
        }
        return searchResults;
    }

    /**
     * This function gives name of the module
     *
     * @return name of module
     */
    public String getModuleName() {
        return InexactSearch.MODULE_NAME;
    }

    /**
     * This function gives a brief description of the module
     *
     * @return brief information regarding the module
     */
    public String getModuleInformation() {
        return InexactSearch.MODULE_INFORMATION;
    }
}
