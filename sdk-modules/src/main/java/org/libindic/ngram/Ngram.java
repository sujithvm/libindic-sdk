package org.libindic.ngram;

import org.libindic.syllabifier.Syllabifier;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sujith on 13/7/14.
 */
public class Ngram {

    public static final int NGRAM_TYPE_WORD = 0;
    public static final int NGRAM_TYPE_SYLLABLE = 1;
    public static final int NGRAM_TYPE_LETTER = 2;

    public static final int DEFAULT_NGRAMS_WINDOW_SIZE = 2;

    private Syllabifier syllabifier;

    private static final String MODULE_NAME = "Ngram Library";
    private static final String MODULE_INFORMATION = "Ngram Library for English and Indian languages";

    /**
     * Constructor
     */
    public Ngram() {
        this.syllabifier = new Syllabifier();
    }

    /**
     * This function is used to get syllable ngrams
     * with default value of N = 2
     *
     * @param text The text to be split into ngrams.
     * @return list of syllable ngrams.
     */
    public List<String> syllableNgram(String text) {
        return syllableNgram(text, DEFAULT_NGRAMS_WINDOW_SIZE);
    }

    /**
     * This function is used to get syllable ngrams
     *
     * @param text       The text to be split into ngrams.
     * @param windowSize window size to be used while making the ngrams.
     * @return list of syllable ngrams.
     */
    public List<String> syllableNgram(String text, int windowSize) {

        String[] words = text.split(" ");
        List<String> ngrams = new ArrayList<String>();
        for (String word : words) {
            List<String> syllables = this.syllabifier.getSyllables(word);
            int syllableCount = syllables.size();
            int windowStart = 0;
            int windowEnd = 0;
            while (windowStart + windowSize <= syllableCount) {
                if (windowStart + windowSize < syllableCount) {
                    windowEnd = windowStart + windowSize;
                } else {
                    windowEnd = syllableCount;
                }
                String str = "";
                for (int i = windowStart; i < windowEnd; i++) {
                    str = str + syllables.get(i);
                }
                ngrams.add(str);
                windowStart = windowStart + 1;
            }
        }
        return ngrams;
    }

    /**
     * This function is used to get letter ngrams
     * with default value of N = 2
     *
     * @param word The word to be split into ngrams.
     * @return list of ngrams.
     */
    public List<String> letterNgram(String word) {
        return letterNgram(word, DEFAULT_NGRAMS_WINDOW_SIZE);
    }

    /**
     * This function is used to get letter ngrams
     *
     * @param word       The word to be split into ngrams.
     * @param windowSize window size to be used while making the ngrams.
     * @return list of ngrams.
     */
    public List<String> letterNgram(String word, int windowSize) {
        word = word.trim();
        List<String> ngrams = new ArrayList<String>();
        int letterCount = word.length();
        int windowStart = 0;
        int windowEnd = 0;

        while (windowStart + windowSize <= letterCount) {
            if (windowStart + windowSize < letterCount) {
                windowEnd = windowStart + windowSize;
            } else {
                windowEnd = letterCount;
            }
            ngrams.add(word.substring(windowStart, windowEnd));
            windowStart = windowStart + 1;
        }
        return ngrams;
    }

    /**
     * This function is used to get word ngrams
     * with default value of N = 2
     *
     * @param text The text to be split into ngrams.
     * @return list of word ngrams.
     */
    public List<String> wordNgram(String text) {
        return wordNgram(text, DEFAULT_NGRAMS_WINDOW_SIZE);
    }

    /**
     * This function is used to get word ngrams
     *
     * @param text       The text to be split into ngrams.
     * @param windowSize window size to be used while making the ngrams.
     * @return list of word ngrams.
     */
    public List<String> wordNgram(String text, int windowSize) {
        String[] words = text.split(" ");
        List<String> ngrams = new ArrayList<String>();
        int wordCount = words.length;
        int windowStart = 0;
        int windowEnd = 0;
        while (windowStart + windowSize <= wordCount) {
            if (windowStart + windowSize < wordCount) {
                windowEnd = windowStart + windowSize;
            } else {
                windowEnd = wordCount;
            }
            String str = "";
            for (int i = windowStart; i < windowEnd; i++) {
                str = str + words[i] + " ";
            }
            ngrams.add(str.trim());
            windowStart = windowStart + 1;
        }
        return ngrams;
    }


    /**
     * This function gives name of the module
     *
     * @return name of module
     */
    public String getModuleName() {
        return Ngram.MODULE_NAME;
    }

    /**
     * This function gives a brief description of the module
     *
     * @return brief information regarding the module
     */
    public String getModuleInformation() {
        return Ngram.MODULE_INFORMATION;
    }
}
