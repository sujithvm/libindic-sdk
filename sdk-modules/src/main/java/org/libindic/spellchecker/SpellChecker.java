package org.libindic.spellchecker;

/**
 * Created by sujith on 8/8/14.
 */

import android.content.Context;

import org.libindic.R;
import org.libindic.inexactsearch.InexactSearch;
import org.libindic.common.LanguageDetect;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SpellChecker {

    private Context mContext;
    private Map<String, List<String>> dictionaries;
    private static Map<String, Integer> resourceMap = new HashMap<>();
    private static final int DEFAULT_DISTANCE = 2;

    static {
        resourceMap.put("ml_IN", R.raw.silpa_sdk_spell_check_dic_ml_in);
        resourceMap.put("bn_IN", R.raw.silpa_sdk_spell_check_dic_bn_in);
        resourceMap.put("hi_IN", R.raw.silpa_sdk_spell_check_dic_hi_in);
        resourceMap.put("gu_IN", R.raw.silpa_sdk_spell_check_dic_gu_in);
        resourceMap.put("pa_IN", R.raw.silpa_sdk_spell_check_dic_pa_in);
        resourceMap.put("kn_IN", R.raw.silpa_sdk_spell_check_dic_kn_in);
        resourceMap.put("or_IN", R.raw.silpa_sdk_spell_check_dic_or_in);
        resourceMap.put("ta_IN", R.raw.silpa_sdk_spell_check_dic_ta_in);
        resourceMap.put("mr_IN", R.raw.silpa_sdk_spell_check_dic_mr_in);
        resourceMap.put("en_US", R.raw.silpa_sdk_spell_check_dic_en_us);
    }

    /**
     * Constructor
     *
     * @param context context of application
     */
    public SpellChecker(Context context) {
        this.mContext = context;
        this.dictionaries = new HashMap<>();
    }

    /**
     * This function is used to get a list of possible words
     *
     * @param word reference word
     * @return list of words
     */
    private List<String> getWordList(String word, String language) {

        if (word == null || word.length() == 0) {
            return null;
        }
        List<String> selectedWords = new ArrayList<>();
        List<String> dict = getDictionary(language);
        for (String d : dict) {
            if (d.length() > 0
                    && Character.toLowerCase(d.charAt(0)) == Character
                    .toLowerCase(word.charAt(0))) {
                selectedWords.add(d);
            }
        }
        return selectedWords;
    }

    private List<String> getDictionary(String language) {
        List<String> dict = this.dictionaries.get(language);
        if (dict != null) {
            return dict;
        }
        dict = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader
                    (this.mContext.getResources().openRawResource(resourceMap.get(language))));
            String line;
            while ((line = br.readLine()) != null) {
                dict.add(line);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.sort(dict);
        this.dictionaries.put(language, dict);
        return dict;
    }

    /**
     * This function calculates Levenshtein Distance between two strings
     *
     * @param str1 string1
     * @param str2 string2
     * @return int Levenshtein Distance
     */
    private int getLevenshteinDistance(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        int[][] arr = new int[len1 + 1][len2 + 1];
        for (int i = 0; i <= len1; i++)
            arr[i][0] = i;
        for (int i = 1; i <= len2; i++)
            arr[0][i] = i;
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                int m = (str1.charAt(i - 1) == str2.charAt(j - 1)) ? 0 : 1;
                arr[i][j] = Math.min(
                        Math.min(arr[i - 1][j] + 1, arr[i][j - 1] + 1),
                        arr[i - 1][j - 1] + m);
            }
        }
        return arr[len1][len2];
    }

    /**
     * This function provides a list of suggestions for a given word. Language of word
     * would be auto detected.
     *
     * @param word word for which  spelling suggestions are required.
     * @return list of words
     */
    public List<String> suggest(String word) {
        return suggest(word, null, DEFAULT_DISTANCE);
    }

    /**
     * This function provides a list of suggestions for a given word
     *
     * @param word     word for which  spelling suggestions are required.
     * @param language language of word
     * @return list of words
     */
    public List<String> suggest(String word, String language) {
        return suggest(word, language, DEFAULT_DISTANCE);
    }

    /**
     * This function provides a list of suggestions for a given word
     *
     * @param word     word for which  spelling suggestions are required.
     * @param language language of word
     * @param distance suggestion will contain words with length =word length +/-  distance
     * @return list of words
     */
    public List<String> suggest(String word, String language, int distance) {
        word = word.trim();
        if (word.equals("")) {
            return Collections.EMPTY_LIST;
        }

        if (language == null) {
            language = LanguageDetect.detectLanguage(word).get(word);
        }

        List<String> NWORDS = getWordList(word, language);

        if (NWORDS == null) {
            NWORDS = null;
        }

        if (NWORDS.contains(word)) {
            return Arrays.asList(new String[]{word});
        }

        List<String> candidates = new ArrayList<>();
        for (String candidate : NWORDS) {
            if (candidate.length() - word.length() > distance
                    || word.length() - candidate.length() > distance) {
                continue;
            }

            if (!(getLevenshteinDistance(candidate, word) > distance)) {
                candidates.add(candidate);
            }
        }

        candidates = filterCandidates(word, candidates);

        if (candidates.size() == 0) {
            int pos = 2;
            while (pos < word.length() - 2) {
                if (check(word.substring(0, pos), language)
                        && check(word.substring(pos), language)) {
                    candidates.add(word.substring(0, pos) + " "
                            + word.substring(pos));
                    candidates.add(word.substring(0, pos) + "-"
                            + word.substring(pos));
                }
                pos += 1;
            }
        }

        // Sort using Levenshtein Distance

        List<Map.Entry<String, Integer>> wordLevenshteinDistanceList = new LinkedList<>();
        for (String candidate : candidates) {
            wordLevenshteinDistanceList.add(new AbstractMap.SimpleEntry<String, Integer>
                    (candidate, getLevenshteinDistance(candidate, word)));
        }

        Collections.sort(wordLevenshteinDistanceList, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> obj1, Map.Entry<String, Integer> obj2) {
                return (obj1.getValue()).compareTo(obj2.getValue());
            }
        });

        List<String> filteredCandidates = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : wordLevenshteinDistanceList) {
            filteredCandidates.add(entry.getKey());
        }

        return filteredCandidates;
    }

    /**
     * This function is used to filter words based on inexactsearch
     *
     * @param word       word for which similarity is checked
     * @param candidates list of words to be checked
     * @return filtered list of words
     */
    private List<String> filterCandidates(String word, List<String> candidates) {
        List<String> filteredCandidates = new ArrayList<>();
        InexactSearch isearch = new InexactSearch();
        for (String candidate : candidates) {
            if (isearch.compare(word, candidate) >= 0.90) {
                filteredCandidates.add(candidate);
            }
        }
        return filteredCandidates;
    }

    /**
     * This function is used to check if word has correct spelling or not
     *
     * @param word word for which spelling is to be checked
     * @return true if spelling is correct else false
     */
    public boolean check(String word) {
        return check(word, null);
    }

    /**
     * This function is used to check if word has correct spelling or not
     *
     * @param word     word for which spelling is to be checked
     * @param language language of word
     * @return true if spelling is correct else false
     */
    public boolean check(String word, String language) {
        if (word == null) {
            return false;
        }
        word = word.trim();
        if (word.equals("")) {
            return true;
        }
        // checking it is a number
        if (word.matches("[0-9]+")) {
            return true;
        }

        if (language == null) {
            language = LanguageDetect.detectLanguage(word).get(word);
        }

        List<String> NWORDS = getWordList(word, language);

        return Collections.binarySearch(NWORDS, word) >= 0 ||
                Collections.binarySearch(NWORDS, word.toLowerCase()) >= 0;
    }

    private static final String PUNCTUATIONS_REGEX = "[!\"#$%&'()*+,-./:;<=>?@\\[\\]^_`{|}~\\\\]";

    /**
     * This function returns a list of misspelled words give a chunk of text.
     *
     * @param text text
     * @return list of misspelled words
     */
    public List<String> checkBatch(String text) {
        List<String> lst = new ArrayList<>();
        text = text.replaceAll(PUNCTUATIONS_REGEX, "");
        String[] words = text.split(" ");
        for (String word : words) {
            if (!check(word, null)) {
                lst.add(word);
            }
        }
        return lst;
    }
}
