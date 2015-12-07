package org.libindic.guesslanguage;

/**
 * Created by sujith on 30/7/14.
 */
public class LanguageInfo {
    private String langTag;
    private int langId;
    private String langName;

    public LanguageInfo(String langTag, int langId, String langName) {
        this.langTag = langTag;
        this.langId = langId;
        this.langName = langName;
    }

    public String getLanguageTag() {
        return this.langTag;
    }

    public int getLanguageId() {
        return this.langId;
    }

    public String getLanguageName() {
        return this.langName;
    }

    @Override
    public String toString() {
        return "Language Tag : " + this.langTag + "\n" +
                "Language Id : " + this.langId + "\n" +
                "Language Name : " + this.langName;
    }
}
