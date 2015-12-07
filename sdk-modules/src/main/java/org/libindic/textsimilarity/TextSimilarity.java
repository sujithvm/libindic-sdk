package org.libindic.textsimilarity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by sujith on 14/7/14.
 */
class Ngram {

    private int n;
    private String text;
    private Map<String, Double> d;
    private static final String NGRAM_JOINER = " ";
    protected static final int DEFAULT_NGRAMS_N = 3;

    /**
     * Constructor with default N = 3
     *
     * @param text string text
     */
    public Ngram(String text) {
        this(text, DEFAULT_NGRAMS_N);
    }

    /**
     * Constructor
     *
     * @param text string text
     * @param n    value for n grams
     */
    public Ngram(String text, int n) {
        this.text = text;
        this.n = n;
        this.d = textToNgrams(this.text, this.n);
    }

    public boolean contains(String word) {
        return this.d.get(word) != null;
    }

    /**
     * Get value of a word
     *
     * @param word string word
     * @return double value between 0 - 1
     */
    public double getItem(String word) {
        return this.d.get(word);
    }

    /**
     * This function compares two n grams
     *
     * @param other ngram object
     * @return double value between 0 - 1
     */
    public double mul(Ngram other) {
        if (this.n != other.n) {
            throw new IllegalArgumentException("Error : two ngrams of different n's are being compared");
        }
        if (this.text.equals(other.text)) {
            return 1.0;
        }
        double sum = 0.0;
        for (Map.Entry<String, Double> entry : this.d.entrySet()) {
            String key = entry.getKey();
            if (other.contains(key)) {
                sum += (this.getItem(key) * other.getItem(key));
            }
        }
        return sum;
    }

    protected List<String> tokenize(String text) {
        return new ArrayList<String>(Arrays.asList(text.replaceAll("[^\\w\\n ]|\\xe2", "")
                .toLowerCase(Locale.getDefault()).split(" ")));
    }

    /**
     * This method is run on the text right before tokenization
     *
     * @param text string text
     * @return string
     */
    protected String normalize(String text) {
        return text.toLowerCase(Locale.getDefault());
    }

    /**
     * This function is used to make n grams
     *
     * @param text string text
     * @return list of string
     */
    protected List<String> makeNgrams(String text) {
        text = this.normalize(text);
        List<String> tokens = this.tokenize(text);
        List<String> ngrams = new ArrayList<String>();
        for (int i = 0; i < tokens.size(); i++) {
            String str = "";
            // TODO use joiner to join
            for (int j = i; j < i + this.n && j < tokens.size(); j++) {
                str = str + tokens.get(j) + NGRAM_JOINER;
            }
            str = str.trim();
            ngrams.add(str);
        }
        return ngrams;
    }

    /**
     * This funtion returns a map (String, Double)
     * where String - word, Double - values
     *
     * @param text string text
     * @param n    ngrams n value
     * @return map
     */
    protected Map<String, Double> textToNgrams(String text, int n) {
        Map<String, Double> d = new HashMap<String, Double>();
        for (String ngram : this.makeNgrams(text)) {
            if (d.get(ngram) != null) {
                d.put(ngram, d.get(ngram) + 1.0);
            } else {
                d.put(ngram, 1.0);
            }
        }

        double sum = 0;
        for (double x : d.values()) {
            sum += (x * x);
        }
        double norm = Math.sqrt(sum);

        for (Map.Entry<String, Double> entry : d.entrySet()) {
            String key = entry.getKey();
            double value = entry.getValue();
            d.put(key, 1.0 * value / norm);
        }
        return d;
    }
}

class CharNgrams extends Ngram {

    /**
     * Constructor
     *
     * @param text string text
     */
    public CharNgrams(String text) {
        this(text, DEFAULT_NGRAMS_N);
    }

    /**
     * Constructor
     *
     * @param text string text
     * @param n    ngrams n value
     */
    public CharNgrams(String text, int n) {
        super(text, n);
    }

    /**
     * Overridden tokenize function
     *
     * @param st string text
     * @return list of words
     */
    public List<String> tokenize(String st) {
        List<String> lst = new ArrayList<String>();
        for (char ch : st.toCharArray()) {
            if (Character.isLetterOrDigit(ch)) {
                lst.add(ch + "");
            }
        }
        return lst;
    }
}

public class TextSimilarity {

    private static final String MODULE_NAME = "Text Similarity";
    private static final String MODULE_INFORMATION = "Compare strings using an n-grams model and cosine similarity";

    /**
     * This function compares two strings and returns a comparison value between 0 and 1
     *
     * @param text1 text1 to be compared
     * @param text2 text2 to be compared
     * @return double value between 0 - 1
     */
    public double compare(String text1, String text2) {
        Ngram n1 = new Ngram(text1);
        Ngram n2 = new Ngram(text2);

        return n1.mul(n2);
    }

    /**
     * This function gives name of the module
     *
     * @return name of module
     */
    public String getModuleName() {
        return TextSimilarity.MODULE_NAME;
    }

    /**
     * This function gives a brief description of the module
     *
     * @return brief information regarding the module
     */
    public String getModuleInformation() {
        return TextSimilarity.MODULE_INFORMATION;
    }

}
