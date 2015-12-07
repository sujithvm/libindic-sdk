package org.libindic.characterdetails;

/**
 * Created by sujith on 23/5/14.
 */
public class CharacterDetailsObject {

    /**
     * Check if character is digit
     */
    private boolean isDigit;

    /**
     * Check if character is alphabet
     */
    private boolean isAlphabet;

    /**
     * Check if character is alpha numeric
     */
    private boolean isAlphaNumeric;

    /**
     * HTML entity number
     */
    private int htmlEntity;

    /**
     * Name of character
     */
    private String name;

    /**
     * Unicode code point
     */
    private String codePoint;

    /**
     * Canonical decomposition
     */
    private String canonicalDecomposition;


    /**
     * Default constructor
     */
    public CharacterDetailsObject() {
        this(false, false, false, 0, "", "", "");
    }

    /**
     * Constructor
     *
     * @param isDigit                if character is digit
     * @param isAlphabet             if character is alphabet
     * @param isAlphaNumeric         if character is alphanumeric
     * @param htmlEntity             HTML entity
     * @param name                   name of character
     * @param codePoint              unicode code point
     * @param canonicalDecomposition canonical decomposition
     */
    public CharacterDetailsObject(boolean isDigit, boolean isAlphabet, boolean isAlphaNumeric,
                                  int htmlEntity,
                                  String name, String codePoint, String canonicalDecomposition) {
        this.isDigit = isDigit;
        this.isAlphabet = isAlphabet;
        this.isAlphaNumeric = isAlphaNumeric;
        this.htmlEntity = htmlEntity;
        this.name = name;
        this.codePoint = codePoint;
        this.canonicalDecomposition = canonicalDecomposition;
    }

    // Setters

    public void setDigit(boolean isDigit) {
        this.isDigit = isDigit;
    }

    public void setAlphabet(boolean isAlphabet) {
        this.isAlphabet = isAlphabet;
    }

    public void setAlphaNumeric(boolean isAlphaNumeric) {
        this.isAlphaNumeric = isAlphaNumeric;
    }

    public void setHtmlEntity(int htmlEntity) {
        this.htmlEntity = htmlEntity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCodePoint(String codePoint) {
        this.codePoint = codePoint;
    }

    public void setCanonicalDecomposition(String canonicalDecomposition) {
        this.canonicalDecomposition = canonicalDecomposition;
    }

    // Getters

    public boolean isDigit() {
        return this.isDigit;
    }

    public boolean isAlphabet() {
        return this.isAlphabet;
    }

    public boolean isAlphaNumeric() {
        return this.isAlphaNumeric;
    }

    public int getHtmlEntity() {
        return this.htmlEntity;
    }

    public String getName() {
        return this.name;
    }

    public String getCodePoint() {
        return this.codePoint;
    }

    public String getCanonicalDecomposition() {
        return this.canonicalDecomposition;
    }

    /**
     * Override toString
     *
     * @return print CharacterDetails
     */
    @Override
    public String toString() {
        return "[ " + "Digit : " + this.isDigit + "\n" +
                "HTML Entity : " + this.htmlEntity + "\n" +
                "Name : " + this.name + "\n" +
                "Alphabet : " + this.isAlphabet + "\n" +
                "Canonical Decomposition : " + this.canonicalDecomposition + "\n" +
                "AlphaNumeric : " + this.isAlphaNumeric + "\n" +
                "Code point : " + this.codePoint + " ]";
    }

    /**
     * Checks if two character objects are equal or not
     *
     * @param obj CharacterDetails object
     * @return true if equal else false
     */
    public boolean equals(CharacterDetailsObject obj) {
        return ((this.isDigit() == obj.isDigit) &&
                (this.getHtmlEntity() == obj.getHtmlEntity()) &&
                (this.getName().equals(obj.getName())) &&
                (this.isAlphabet() == obj.isAlphabet()) &&
                (this.getCanonicalDecomposition().equals(obj.getCanonicalDecomposition())) &&
                (this.isAlphaNumeric() == obj.isAlphaNumeric()) &&
                (this.getCodePoint().equals(obj.getCodePoint())));

    }
}
