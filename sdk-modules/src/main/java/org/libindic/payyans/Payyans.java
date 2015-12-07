package org.libindic.payyans;

import android.content.Context;
import android.util.Log;

import org.libindic.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sujith on 20/5/14.
 */
public class Payyans {

    public static final int ASCII_TO_UNICODE = 0;
    public static final int UNICODE_TO_ASCII = 1;

    public static final int FONT_MAP_AMBILI = 0;
    public static final int FONT_MAP_CHARAKA = 1;
    public static final int FONT_MAP_HARITHA = 2;
    public static final int FONT_MAP_INDULEKHA = 3;
    public static final int FONT_MAP_KARTHIKA = 4;
    public static final int FONT_MAP_MANORAMA = 5;
    public static final int FONT_MAP_MATWEB = 6;
    public static final int FONT_MAP_NANDINI = 7;
    public static final int FONT_MAP_PANCHARI = 8;
    public static final int FONT_MAP_REVATHI = 9;
    public static final int FONT_MAP_TEMPLATE = 10;
    public static final int FONT_MAP_UMA = 11;
    public static final int FONT_MAP_VALLUVAR = 12;

    public static final String MODULE_NAME = "Payyans";
    public static final String MODULE_INFORMATION = "ASCII data - Unicode Convertor based on font maps";

    private static final int[] FONT_MAPS_RESOURCE_RAW_ID = {R.raw.silpa_sdk_ambili,
            R.raw.silpa_sdk_charaka, R.raw.silpa_sdk_haritha, R.raw.silpa_sdk_indulekha,
            R.raw.silpa_sdk_karthika, R.raw.silpa_sdk_manorama, R.raw.silpa_sdk_matweb,
            R.raw.silpa_sdk_nandini, R.raw.silpa_sdk_panchari, R.raw.silpa_sdk_revathi,
            R.raw.silpa_sdk_template, R.raw.silpa_sdk_uma, R.raw.silpa_sdk_valluvar};

    protected static final int DEFAULT_FONT_MAP = Payyans.FONT_MAP_AMBILI;
    protected static final int DEFAULT_DIRECTION = Payyans.ASCII_TO_UNICODE;

    private static final String LOG_TAG = MODULE_NAME;

    /**
     * Context of application
     */
    private Context mContext;

    /**
     * Direction of conversion.
     * Unicode to ASCII or
     * ASCII to Unicode
     */
    private int mDirection;

    /**
     * Font map rules
     */
    private int mFontMap;

    /**
     * Font map res id
     */
    private int mMappingFontRawResourceId;

    /**
     * Map to store all rules
     */
    private Map<String, String> mRulesDict;


    /**
     * Constructor
     * Default font map - ambili
     * Default direction - ASCII to Unicode
     *
     * @param context context
     */
    public Payyans(Context context) {
        this(context, DEFAULT_FONT_MAP, DEFAULT_DIRECTION);
    }

    /**
     * Constructor
     * Default direction - ASCII to Unicode
     *
     * @param context context
     * @param fontMap font map rules from Constants
     */
    public Payyans(Context context, int fontMap) {
        this(context, fontMap, DEFAULT_DIRECTION);
    }

    /**
     * Constructor
     *
     * @param context   context
     * @param fontMap   font map rules from Constants
     * @param direction Unicode to ASCII or ASCII to Unicode
     *                  Constants
     */
    public Payyans(Context context, int fontMap, int direction) {
        this.mDirection = direction;
        this.mContext = context;
        this.mFontMap = fontMap;
        this.mMappingFontRawResourceId = Payyans.FONT_MAPS_RESOURCE_RAW_ID[this.mFontMap];
        this.mRulesDict = new HashMap<String, String>();
        init();
    }

    /**
     * Function to get font map
     *
     * @return int - check Payyans font map constants
     */
    public int getFontMap() {
        return this.mFontMap;
    }

    /**
     * Function to get current direction of conversion
     *
     * @return Payyans.ASCII_TO_UNICODE or Payyans.UNICODE_TO_ASCII
     */
    public int getDirection() {
        return this.mDirection;
    }

    /**
     * This function is used to explicitly set font map
     *
     * @param fontMap fontmap
     */
    public void setFontMap(int fontMap) {
        this.mFontMap = fontMap;
        this.mMappingFontRawResourceId = Payyans.FONT_MAPS_RESOURCE_RAW_ID[this.mFontMap];
        this.mRulesDict = new HashMap<String, String>();
        init();
    }

    /**
     * This function is used to explicitly set direction of conversion
     * Payyans.ASCII_TO_UNICODE to convert from ASCII to Unicode
     * Payyans.UNICODE_TO_ASCII to convert from Unicode to ASCII
     *
     * @param direction direction of conversion
     */
    public void setDirection(int direction) {
        this.mDirection = direction;
        this.mRulesDict = new HashMap<String, String>();
        init();
    }

    /**
     * This function is used to load rules on object creation
     */
    private void init() {
        loadRules();
    }

    /**
     * This function is used to load mapping rules from respective
     * font maps
     */
    private void loadRules() {

        String line;
        BufferedReader br;
        int lineNumber = 0;

        try {
            br = new BufferedReader(new InputStreamReader(this.mContext.getResources().openRawResource(mMappingFontRawResourceId)));

            while (true) {
                try {
                    line = new String((br.readLine().getBytes("UTF-8")), "UTF-8");
                    line = line.trim();
                    lineNumber = lineNumber + 1;
                } catch (Exception e) {
                    Log.e(LOG_TAG, "Error : " + e.getMessage() + " " +
                            " Loading rules terminated");
                    br.close();
                    break;
                }

                // blank string - stop
                if (line.equals("")) {
                    break;
                }

                // comment - ignore
                if (line.startsWith("#")) {
                    // comment
                    continue;
                }

                if (!line.contains("=") || line.split("=").length != 2) {
                    Log.e(LOG_TAG, "Error: Syntax Error in the Ascii to Unicode Map " +
                            "in line number " + lineNumber);
                    // ignore error and continue;
                    continue;
                }

                String[] tokens = line.split("=");
                String lhs = tokens[0];
                String rhs = tokens[1];
                lhs = lhs.trim();
                rhs = rhs.trim();

                if (this.mDirection == Payyans.ASCII_TO_UNICODE) {
                    this.mRulesDict.put(lhs, rhs);
                } else {
                    this.mRulesDict.put(rhs, lhs);
                }
            }
            br.close();
        } catch (IOException ioe) {
            Log.e(LOG_TAG, "Error : " + ioe.getMessage());
            return;
        }
    }

    /**
     * This function converts ASCII text to Unicode
     *
     * @param asciiText ASCII text to be converted
     * @return converted Unicode text
     * @throws UnsupportedEncodingException
     */
    private String ASCII2UNICODE(String asciiText) throws UnsupportedEncodingException {

        asciiText = new String((asciiText.getBytes("UTF-8")), "UTF-8");

        int index = 0;
        int postIndex = 0;
        String prebaseLetter = "";
        String postbaseLetter = "";
        String unicodeText = "";
        String nextUcodeLetter = "";
        String unicodeLetter = "";

        while (index < asciiText.length()) {
            for (int charNo = 3; charNo >= 1; charNo--) {
                if (index + charNo > asciiText.length()) continue;
                String letter = asciiText.substring(index, index + charNo);
                if (this.mRulesDict.containsKey(letter)) {
                    unicodeLetter = this.mRulesDict.get(letter);
                    if (this.isPrebase(unicodeLetter)) {
                        prebaseLetter = unicodeLetter;
                    } else {
                        postIndex = index + charNo;
                        if (postIndex < asciiText.length()) {
                            letter = asciiText.charAt(postIndex) + "";
                            if (this.mRulesDict.containsKey(letter)) {
                                nextUcodeLetter = this.mRulesDict.get(letter);
                                if (this.isPostbase(nextUcodeLetter)) {
                                    postbaseLetter = nextUcodeLetter;
                                    index = index + 1;
                                }
                            }
                        }
                        if ((new String((unicodeLetter.getBytes("UTF-8")), "UTF-8")).equals("എ")
                                || (new String((unicodeLetter.getBytes("UTF-8")), "UTF-8"))
                                .equals("ഒ")) {
                            unicodeText = unicodeText + postbaseLetter +
                                    this.getVowelSign(prebaseLetter, unicodeLetter);
                        } else {
                            unicodeText = unicodeText + unicodeLetter + postbaseLetter +
                                    prebaseLetter;
                        }
                        prebaseLetter = "";
                        postbaseLetter = "";
                    }
                    index = index + charNo;
                    break;
                } else {
                    if (charNo == 1) {
                        unicodeText = unicodeText + letter;
                        index = index + 1;
                        break;
                    }
                    unicodeLetter = letter;
                }
            }
        }
        return unicodeText;
    }

    /**
     * This function converts Unicode text to ASCII
     *
     * @param unicodeText Unicode text to be converted
     * @return converted ASCII text
     * @throws UnsupportedEncodingException
     */
    private String Unicode2ASCII(String unicodeText) throws UnsupportedEncodingException {

        unicodeText = new String((unicodeText.getBytes("UTF-8")), "UTF-8");

        int index = 0;
        String asciiText = "";
        String asciiLetter = "";

        while (index < unicodeText.length()) {
            for (int charNo = 3; charNo >= 1; charNo--) {
                if (index + charNo > unicodeText.length()) continue;
                String letter = unicodeText.substring(index, index + charNo);
                if (this.mRulesDict.containsKey(letter)) {
                    asciiLetter = this.mRulesDict.get(letter);
                    letter = new String((letter.getBytes("UTF-8")), "UTF-8");

                    if (letter.equals("ൈ")) {
                        int len = asciiText.length();
                        asciiText = asciiText.substring(0, len - 1) + asciiLetter
                                + asciiText.substring(len - 1, len);
                    } else if (letter.equals("ോ") || letter.equals("ൊ") || letter.equals("ൌ")) {
                        int len = asciiText.length();
                        asciiText = asciiText.substring(0, len - 1)
                                + asciiLetter.charAt(0) + asciiText.substring(len - 1, len)
                                + asciiLetter.charAt(1);
                    } else if (letter.equals("െ") || letter.equals("േ") || letter.equals("്ര")) {
                        int len = asciiText.length();
                        asciiText = asciiText.substring(0, len - 1) + asciiLetter
                                + asciiText.substring(len - 1, len);
                    } else {
                        asciiText = asciiText + asciiLetter;
                    }
                    index = index + charNo;
                    break;
                } else {
                    if (charNo == 1) {
                        index = index + 1;
                        asciiText = asciiText + letter;
                        break;
                    }
                    asciiLetter = letter;
                }
            }
        }
        return asciiText;
    }

    /**
     * getVowelSign
     *
     * @param vowelLetter
     * @param vowelSignLetter
     * @return string
     * @throws UnsupportedEncodingException
     */
    public String getVowelSign(String vowelLetter, String vowelSignLetter)
            throws UnsupportedEncodingException {

        String vowel = new String((vowelLetter.getBytes("UTF-8")), "UTF-8");
        String vowelSign = new String((vowelSignLetter.getBytes("UTF-8")), "UTF-8");

        String strVowelSign;

        if (vowel.equals("എ") && vowelSign.equals("െ")) {
            strVowelSign = "ഐ";
        } else if (vowel.equals("ഒ") && vowelSign.equals("ാ")) {
            strVowelSign = "ഓ";
        } else if (vowel.equals("ഒ") && vowelSign.equals("ൗ")) {
            strVowelSign = "ഔ";
        } else {
            strVowelSign = vowelLetter + vowelSignLetter;
        }
        return strVowelSign;
    }

    /**
     * Checks if a post base character
     *
     * @param letter single character string to be checked
     * @return true if post base character else false
     * @throws UnsupportedEncodingException
     */
    public boolean isPostbase(String letter) throws UnsupportedEncodingException {
        letter = new String((letter.getBytes("UTF-8")), "UTF-8");
        if (letter.equals("്യ") || letter.equals("്വ")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if a pre base character
     *
     * @param letter single character string to be checked
     * @return true if pre base character else false
     * @throws UnsupportedEncodingException
     */
    public boolean isPrebase(String letter) throws UnsupportedEncodingException {
        letter = new String((letter.getBytes("UTF-8")), "UTF-8");
        if (letter.equals("േ") || letter.equals("ൈ") || letter.equals("ൊ") ||
                letter.equals("ോ") || letter.equals("ൌ") || letter.equals("്ര")
                || letter.equals("െ")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This function converts Ascii to Unicode and vice-versa
     * depending on direction of conversion specified.
     *
     * @param text string to be converted
     * @return converted string
     */
    public String getConvertText(String text) {
        try {
            text = new String((text.getBytes("UTF-8")), "UTF-8");

            if (this.mDirection == Payyans.ASCII_TO_UNICODE) {
                return ASCII2UNICODE(text);
            } else {
                return Unicode2ASCII(text);
            }

        } catch (UnsupportedEncodingException e) {
            Log.e(LOG_TAG, "Encoding of given argument not supported. null returned");
            return null;
        }
    }

    /**
     * This function gives name of the module
     *
     * @return name of module
     */
    public String getModuleName() {
        return Payyans.MODULE_NAME;
    }

    /**
     * This function gives a brief description of the module
     *
     * @return brief information regarding the module
     */
    public String getModuleInformation() {
        return Payyans.MODULE_INFORMATION;
    }
}
