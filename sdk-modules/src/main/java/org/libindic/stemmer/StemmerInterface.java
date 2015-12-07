package org.libindic.stemmer;

import java.util.Map;

/**
 * Created by sujith on 13/6/14.
 */
public interface StemmerInterface {

    /**
     * This function is used to get all stemmed words
     * in an array
     *
     * @return string array
     */
    public String[] getStemWordsAsArray();

    /**
     * This function is used to get all stemmed words
     * as a map
     *
     * @return map
     */
    public Map<String, String> getStemWordsAsMap();

    /**
     * This function gives name of the module
     *
     * @return name of module
     */
    public String getModuleName();

    /**
     * This function gives a brief description of the module
     *
     * @return brief information regarding the module
     */
    public String getModuleInformation();
}
