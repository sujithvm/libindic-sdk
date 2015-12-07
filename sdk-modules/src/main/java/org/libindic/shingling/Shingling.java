package org.libindic.shingling;

import org.libindic.ngram.Ngram;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sujith on 14/7/14.
 */
public class Shingling {

    private Ngram ngram;
    private static final int DEFAULT_SHINGLES_WINDOW_SIZE = 4;

    public static final String MODULE_NAME = "Shingling Library";
    public static final String MODULE_INFORMATION = "Shingling Library for English and " +
            "Indian languages";

    /**
     * Constructor
     */
    public Shingling() {
        this.ngram = new Ngram();
    }

    /**
     * This function is used to split text into shingles
     * with default value of window size = 4
     *
     * @param text Text to be split into shingles.
     * @return text split into shingles.
     */
    public List<String> wshingling(String text) {
        return wshingling(text, DEFAULT_SHINGLES_WINDOW_SIZE);
    }

    /**
     * This function is used to split text into shingles
     *
     * @param text       Text to be split into shingles.
     * @param windowSize the window size for splitting the shingles.
     * @return text split into shingles.
     */
    public List<String> wshingling(String text, int windowSize) {
        List<String> ng = ngram.wordNgram(text, windowSize);
        List<String> shingling = new ArrayList<String>();
        for (String x : ng) {
            if (!(shingling.contains(x))) {
                shingling.add(x);
            }
        }
        return shingling;
    }

    /**
     * This function gives name of the module
     *
     * @return name of module
     */
    public String getModuleName() {
        return Shingling.MODULE_NAME;
    }

    /**
     * This function gives a brief description of the module
     *
     * @return brief information regarding the module
     */
    public String getModuleInformation() {
        return Shingling.MODULE_INFORMATION;
    }
}
