package org.libindic.stemmer;

import android.content.Context;
import android.util.AttributeSet;

import org.libindic.render.IndicTextView;

import java.util.Map;

/**
 * Created by sujith on 13/6/14.
 */
public class StemmerTextView extends IndicTextView implements StemmerInterface {

    /**
     * Context of application
     */
    private Context mContext;

    /**
     * Stemmer object to stem words
     */
    private Stemmer stemmer;


    /**
     * Constructor
     *
     * @param context context of application
     */
    public StemmerTextView(Context context) {
        super(context);
        init();
    }

    /**
     * Constructor
     *
     * @param context context of application
     * @param attrs   attribute set
     */
    public StemmerTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * Constructor
     *
     * @param context  context of application
     * @param attrs    attribute set
     * @param defStyle default style
     */
    public StemmerTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        this.mContext = getContext();
        this.stemmer = new Stemmer(this.mContext);
    }


    /**
     * This function is used to get all stemmed words
     * in an array
     *
     * @return string array
     */
    @Override
    public String[] getStemWordsAsArray() {
        return this.stemmer.getStemWordsAsArray(getText().toString());
    }

    /**
     * This function is used to get all stemmed words
     * as a map
     *
     * @return map
     */
    @Override
    public Map<String, String> getStemWordsAsMap() {
        return this.stemmer.getStemWordsAsMap(getText().toString());
    }

    /**
     * This function gives name of the module
     *
     * @return name of module
     */
    @Override
    public String getModuleName() {
        return this.stemmer.getModuleName();
    }

    /**
     * This function gives a brief description of the module
     *
     * @return brief information regarding the module
     */
    @Override
    public String getModuleInformation() {
        return this.stemmer.getModuleInformation();
    }
}
