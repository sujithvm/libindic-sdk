package org.libindic.characterdetails;

import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.LargeTest;
import android.test.suitebuilder.annotation.MediumTest;
import android.test.suitebuilder.annotation.SmallTest;

import java.util.Map;

/**
 * Created by sujith on 24/5/14.
 */
public class TestCharacterDetails extends AndroidTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @SmallTest
    @MediumTest
    @LargeTest
    public void testGetModuleName() {
        CharacterDetails obj = new CharacterDetails(getContext());
        assertEquals(CharacterDetails.MODULE_NAME, obj.getModuleName());
    }

    @SmallTest
    @MediumTest
    @LargeTest
    public void testGetModuleInformation() {
        CharacterDetails obj = new CharacterDetails(getContext());
        assertEquals(CharacterDetails.MODULE_INFORMATION, obj.getModuleInformation());
    }

    @SmallTest
    @MediumTest
    @LargeTest
    public void testGetCharacterDetails() {
        CharacterDetails obj = new CharacterDetails(getContext());
        CharacterDetailsObject details;

        details = obj.getCharacterDetails('S');
        assertEquals(false, details.isDigit());
        assertEquals(true, details.isAlphabet());
        assertEquals(true, details.isAlphaNumeric());
        assertEquals(83, details.getHtmlEntity());
        assertEquals("LATIN CAPITAL LETTER S", details.getName());
        assertEquals("\\u0053", details.getCodePoint());
        assertEquals("S", details.getCanonicalDecomposition());

        details = obj.getCharacterDetails('ക');
        assertEquals(false, details.isDigit());
        assertEquals(true, details.isAlphabet());
        assertEquals(true, details.isAlphaNumeric());
        assertEquals(3349, details.getHtmlEntity());
        assertEquals("MALAYALAM LETTER KA", details.getName());
        assertEquals("\\u0D15", details.getCodePoint());
        assertEquals("ക", details.getCanonicalDecomposition());

        details = obj.getCharacterDetails('क');
        assertEquals(false, details.isDigit());
        assertEquals(true, details.isAlphabet());
        assertEquals(true, details.isAlphaNumeric());
        assertEquals(2325, details.getHtmlEntity());
        assertEquals("DEVANAGARI LETTER KA", details.getName());
        assertEquals("\\u0915", details.getCodePoint());
        assertEquals("क", details.getCanonicalDecomposition());

        details = obj.getCharacterDetails('আ');
        assertEquals(false, details.isDigit());
        assertEquals(true, details.isAlphabet());
        assertEquals(true, details.isAlphaNumeric());
        assertEquals(2438, details.getHtmlEntity());
        assertEquals("BENGALI LETTER AA", details.getName());
        assertEquals("\\u0986", details.getCodePoint());
        assertEquals("আ", details.getCanonicalDecomposition());

        details = obj.getCharacterDetails('ਉ');
        assertEquals(false, details.isDigit());
        assertEquals(true, details.isAlphabet());
        assertEquals(true, details.isAlphaNumeric());
        assertEquals(2569, details.getHtmlEntity());
        assertEquals("GURMUKHI LETTER U", details.getName());
        assertEquals("\\u0A09", details.getCodePoint());
        assertEquals("ਉ", details.getCanonicalDecomposition());

        details = obj.getCharacterDetails('ઊ');
        assertEquals(false, details.isDigit());
        assertEquals(true, details.isAlphabet());
        assertEquals(true, details.isAlphaNumeric());
        assertEquals(2698, details.getHtmlEntity());
        assertEquals("GUJARATI LETTER UU", details.getName());
        assertEquals("\\u0A8A", details.getCodePoint());
        assertEquals("ઊ", details.getCanonicalDecomposition());

        details = obj.getCharacterDetails('ଉ');
        assertEquals(false, details.isDigit());
        assertEquals(true, details.isAlphabet());
        assertEquals(true, details.isAlphaNumeric());
        assertEquals(2825, details.getHtmlEntity());
        assertEquals("ORIYA LETTER U", details.getName());
        assertEquals("\\u0B09", details.getCodePoint());
        assertEquals("ଉ", details.getCanonicalDecomposition());

        details = obj.getCharacterDetails('இ');
        assertEquals(false, details.isDigit());
        assertEquals(true, details.isAlphabet());
        assertEquals(true, details.isAlphaNumeric());
        assertEquals(2951, details.getHtmlEntity());
        assertEquals("TAMIL LETTER I", details.getName());
        assertEquals("\\u0B87", details.getCodePoint());
        assertEquals("இ", details.getCanonicalDecomposition());

        details = obj.getCharacterDetails('ఋ');
        assertEquals(false, details.isDigit());
        assertEquals(true, details.isAlphabet());
        assertEquals(true, details.isAlphaNumeric());
        assertEquals(3083, details.getHtmlEntity());
        assertEquals("TELUGU LETTER VOCALIC R", details.getName());
        assertEquals("\\u0C0B", details.getCodePoint());
        assertEquals("ఋ", details.getCanonicalDecomposition());

        details = obj.getCharacterDetails('4');
        assertEquals(true, details.isDigit());
        assertEquals(false, details.isAlphabet());
        assertEquals(true, details.isAlphaNumeric());
        assertEquals(52, details.getHtmlEntity());
        assertEquals("DIGIT FOUR", details.getName());
        assertEquals("\\u0034", details.getCodePoint());
        assertEquals("4", details.getCanonicalDecomposition());

        details = obj.getCharacterDetails('0');
        assertEquals(true, details.isDigit());
        assertEquals(false, details.isAlphabet());
        assertEquals(true, details.isAlphaNumeric());
        assertEquals(48, details.getHtmlEntity());
        assertEquals("DIGIT ZERO", details.getName());
        assertEquals("\\u0030", details.getCodePoint());
        assertEquals("0", details.getCanonicalDecomposition());

        details = obj.getCharacterDetails('!');
        assertEquals(false, details.isDigit());
        assertEquals(false, details.isAlphabet());
        assertEquals(false, details.isAlphaNumeric());
        assertEquals(33, details.getHtmlEntity());
        assertEquals("EXCLAMATION MARK", details.getName());
        assertEquals("\\u0021", details.getCodePoint());
        assertEquals("!", details.getCanonicalDecomposition());

        details = obj.getCharacterDetails('൩');
        assertEquals(true, details.isDigit());
        assertEquals(false, details.isAlphabet());
        assertEquals(true, details.isAlphaNumeric());
        assertEquals(3433, details.getHtmlEntity());
        assertEquals("MALAYALAM DIGIT THREE", details.getName());
        assertEquals("\\u0D69", details.getCodePoint());
        assertEquals("൩", details.getCanonicalDecomposition());

        details = obj.getCharacterDetails('౪');
        assertEquals(true, details.isDigit());
        assertEquals(false, details.isAlphabet());
        assertEquals(true, details.isAlphaNumeric());
        assertEquals(3178, details.getHtmlEntity());
        assertEquals("TELUGU DIGIT FOUR", details.getName());
        assertEquals("\\u0C6A", details.getCodePoint());
        assertEquals("౪", details.getCanonicalDecomposition());
    }

    @SmallTest
    @MediumTest
    @LargeTest
    public void testGetCharacterDetailsAsMap() {

        CharacterDetails obj = new CharacterDetails(getContext());
        Map<Character, CharacterDetailsObject> map = obj.getCharacterDetailsAsMap("$V.#ऐকਕઔଓஒகఖಕഌ௧৪");

        assertEquals(false, map.get('$').isDigit());
        assertEquals(false, map.get('$').isAlphabet());
        assertEquals(false, map.get('$').isAlphaNumeric());
        assertEquals(36, map.get('$').getHtmlEntity());
        assertEquals("DOLLAR SIGN", map.get('$').getName());
        assertEquals("\\u0024", map.get('$').getCodePoint());
        assertEquals("$", map.get('$').getCanonicalDecomposition());

        assertEquals(false, map.get('V').isDigit());
        assertEquals(true, map.get('V').isAlphabet());
        assertEquals(true, map.get('V').isAlphaNumeric());
        assertEquals(86, map.get('V').getHtmlEntity());
        assertEquals("LATIN CAPITAL LETTER V", map.get('V').getName());
        assertEquals("\\u0056", map.get('V').getCodePoint());
        assertEquals("V", map.get('V').getCanonicalDecomposition());

        assertEquals(false, map.get('.').isDigit());
        assertEquals(false, map.get('.').isAlphabet());
        assertEquals(false, map.get('.').isAlphaNumeric());
        assertEquals(46, map.get('.').getHtmlEntity());
        assertEquals("FULL STOP", map.get('.').getName());
        assertEquals("\\u002E", map.get('.').getCodePoint());
        assertEquals(".", map.get('.').getCanonicalDecomposition());

        assertEquals(false, map.get('#').isDigit());
        assertEquals(false, map.get('#').isAlphabet());
        assertEquals(false, map.get('#').isAlphaNumeric());
        assertEquals(35, map.get('#').getHtmlEntity());
        assertEquals("NUMBER SIGN", map.get('#').getName());
        assertEquals("\\u0023", map.get('#').getCodePoint());
        assertEquals("#", map.get('#').getCanonicalDecomposition());

        assertEquals(false, map.get('ऐ').isDigit());
        assertEquals(true, map.get('ऐ').isAlphabet());
        assertEquals(true, map.get('ऐ').isAlphaNumeric());
        assertEquals(2320, map.get('ऐ').getHtmlEntity());
        assertEquals("DEVANAGARI LETTER AI", map.get('ऐ').getName());
        assertEquals("\\u0910", map.get('ऐ').getCodePoint());
        assertEquals("ऐ", map.get('ऐ').getCanonicalDecomposition());

        assertEquals(false, map.get('ক').isDigit());
        assertEquals(true, map.get('ক').isAlphabet());
        assertEquals(true, map.get('ক').isAlphaNumeric());
        assertEquals(2453, map.get('ক').getHtmlEntity());
        assertEquals("BENGALI LETTER KA", map.get('ক').getName());
        assertEquals("\\u0995", map.get('ক').getCodePoint());
        assertEquals("ক", map.get('ক').getCanonicalDecomposition());

        assertEquals(false, map.get('ਕ').isDigit());
        assertEquals(true, map.get('ਕ').isAlphabet());
        assertEquals(true, map.get('ਕ').isAlphaNumeric());
        assertEquals(2581, map.get('ਕ').getHtmlEntity());
        assertEquals("GURMUKHI LETTER KA", map.get('ਕ').getName());
        assertEquals("\\u0A15", map.get('ਕ').getCodePoint());
        assertEquals("ਕ", map.get('ਕ').getCanonicalDecomposition());

        assertEquals(false, map.get('ઔ').isDigit());
        assertEquals(true, map.get('ઔ').isAlphabet());
        assertEquals(true, map.get('ઔ').isAlphaNumeric());
        assertEquals(2708, map.get('ઔ').getHtmlEntity());
        assertEquals("GUJARATI LETTER AU", map.get('ઔ').getName());
        assertEquals("\\u0A94", map.get('ઔ').getCodePoint());
        assertEquals("ઔ", map.get('ઔ').getCanonicalDecomposition());

        assertEquals(false, map.get('ଓ').isDigit());
        assertEquals(true, map.get('ଓ').isAlphabet());
        assertEquals(true, map.get('ଓ').isAlphaNumeric());
        assertEquals(2835, map.get('ଓ').getHtmlEntity());
        assertEquals("ORIYA LETTER O", map.get('ଓ').getName());
        assertEquals("\\u0B13", map.get('ଓ').getCodePoint());
        assertEquals("ଓ", map.get('ଓ').getCanonicalDecomposition());

        assertEquals(false, map.get('ஒ').isDigit());
        assertEquals(true, map.get('ஒ').isAlphabet());
        assertEquals(true, map.get('ஒ').isAlphaNumeric());
        assertEquals(2962, map.get('ஒ').getHtmlEntity());
        assertEquals("TAMIL LETTER O", map.get('ஒ').getName());
        assertEquals("\\u0B92", map.get('ஒ').getCodePoint());
        assertEquals("ஒ", map.get('ஒ').getCanonicalDecomposition());

        assertEquals(false, map.get('க').isDigit());
        assertEquals(true, map.get('க').isAlphabet());
        assertEquals(true, map.get('க').isAlphaNumeric());
        assertEquals(2965, map.get('க').getHtmlEntity());
        assertEquals("TAMIL LETTER KA", map.get('க').getName());
        assertEquals("\\u0B95", map.get('க').getCodePoint());
        assertEquals("க", map.get('க').getCanonicalDecomposition());

        assertEquals(false, map.get('ఖ').isDigit());
        assertEquals(true, map.get('ఖ').isAlphabet());
        assertEquals(true, map.get('ఖ').isAlphaNumeric());
        assertEquals(3094, map.get('ఖ').getHtmlEntity());
        assertEquals("TELUGU LETTER KHA", map.get('ఖ').getName());
        assertEquals("\\u0C16", map.get('ఖ').getCodePoint());
        assertEquals("ఖ", map.get('ఖ').getCanonicalDecomposition());

        assertEquals(false, map.get('ಕ').isDigit());
        assertEquals(true, map.get('ಕ').isAlphabet());
        assertEquals(true, map.get('ಕ').isAlphaNumeric());
        assertEquals(3221, map.get('ಕ').getHtmlEntity());
        assertEquals("KANNADA LETTER KA", map.get('ಕ').getName());
        assertEquals("\\u0C95", map.get('ಕ').getCodePoint());
        assertEquals("ಕ", map.get('ಕ').getCanonicalDecomposition());

        assertEquals(false, map.get('ഌ').isDigit());
        assertEquals(true, map.get('ഌ').isAlphabet());
        assertEquals(true, map.get('ഌ').isAlphaNumeric());
        assertEquals(3340, map.get('ഌ').getHtmlEntity());
        assertEquals("MALAYALAM LETTER VOCALIC L", map.get('ഌ').getName());
        assertEquals("\\u0D0C", map.get('ഌ').getCodePoint());
        assertEquals("ഌ", map.get('ഌ').getCanonicalDecomposition());

        assertEquals(true, map.get('৪').isDigit());
        assertEquals(false, map.get('৪').isAlphabet());
        assertEquals(true, map.get('৪').isAlphaNumeric());
        assertEquals(2538, map.get('৪').getHtmlEntity());
        assertEquals("BENGALI DIGIT FOUR", map.get('৪').getName());
        assertEquals("\\u09EA", map.get('৪').getCodePoint());
        assertEquals("৪", map.get('৪').getCanonicalDecomposition());

        assertEquals(true, map.get('௧').isDigit());
        assertEquals(false, map.get('௧').isAlphabet());
        assertEquals(true, map.get('௧').isAlphaNumeric());
        assertEquals(3047, map.get('௧').getHtmlEntity());
        assertEquals("TAMIL DIGIT ONE", map.get('௧').getName());
        assertEquals("\\u0BE7", map.get('௧').getCodePoint());
        assertEquals("௧", map.get('௧').getCanonicalDecomposition());
    }

    @SmallTest
    @MediumTest
    @LargeTest
    public void testGetCharacterDetailsAsArray() {
        CharacterDetails obj = new CharacterDetails(getContext());
        CharacterDetailsObject[] arr = obj.getCharacterDetailsAsArray("शઽಶശಹਸল%+X7୭९");

        assertEquals(false, arr[0].isDigit());
        assertEquals(true, arr[0].isAlphabet());
        assertEquals(true, arr[0].isAlphaNumeric());
        assertEquals(2358, arr[0].getHtmlEntity());
        assertEquals("DEVANAGARI LETTER SHA", arr[0].getName());
        assertEquals("\\u0936", arr[0].getCodePoint());
        assertEquals("श", arr[0].getCanonicalDecomposition());

        assertEquals(false, arr[1].isDigit());
        assertEquals(true, arr[1].isAlphabet());
        assertEquals(true, arr[1].isAlphaNumeric());
        assertEquals(2749, arr[1].getHtmlEntity());
        assertEquals("GUJARATI SIGN AVAGRAHA", arr[1].getName());
        assertEquals("\\u0ABD", arr[1].getCodePoint());
        assertEquals("ઽ", arr[1].getCanonicalDecomposition());

        assertEquals(false, arr[2].isDigit());
        assertEquals(true, arr[2].isAlphabet());
        assertEquals(true, arr[2].isAlphaNumeric());
        assertEquals(3254, arr[2].getHtmlEntity());
        assertEquals("KANNADA LETTER SHA", arr[2].getName());
        assertEquals("\\u0CB6", arr[2].getCodePoint());
        assertEquals("ಶ", arr[2].getCanonicalDecomposition());

        assertEquals(false, arr[3].isDigit());
        assertEquals(true, arr[3].isAlphabet());
        assertEquals(true, arr[3].isAlphaNumeric());
        assertEquals(3382, arr[3].getHtmlEntity());
        assertEquals("MALAYALAM LETTER SHA", arr[3].getName());
        assertEquals("\\u0D36", arr[3].getCodePoint());
        assertEquals("ശ", arr[3].getCanonicalDecomposition());

        assertEquals(false, arr[4].isDigit());
        assertEquals(true, arr[4].isAlphabet());
        assertEquals(true, arr[4].isAlphaNumeric());
        assertEquals(3257, arr[4].getHtmlEntity());
        assertEquals("KANNADA LETTER HA", arr[4].getName());
        assertEquals("\\u0CB9", arr[4].getCodePoint());
        assertEquals("ಹ", arr[4].getCanonicalDecomposition());

        assertEquals(false, arr[5].isDigit());
        assertEquals(true, arr[5].isAlphabet());
        assertEquals(true, arr[5].isAlphaNumeric());
        assertEquals(2616, arr[5].getHtmlEntity());
        assertEquals("GURMUKHI LETTER SA", arr[5].getName());
        assertEquals("\\u0A38", arr[5].getCodePoint());
        assertEquals("ਸ", arr[5].getCanonicalDecomposition());

        assertEquals(false, arr[6].isDigit());
        assertEquals(true, arr[6].isAlphabet());
        assertEquals(true, arr[6].isAlphaNumeric());
        assertEquals(2482, arr[6].getHtmlEntity());
        assertEquals("BENGALI LETTER LA", arr[6].getName());
        assertEquals("\\u09B2", arr[6].getCodePoint());
        assertEquals("ল", arr[6].getCanonicalDecomposition());

        assertEquals(false, arr[7].isDigit());
        assertEquals(false, arr[7].isAlphabet());
        assertEquals(false, arr[7].isAlphaNumeric());
        assertEquals(37, arr[7].getHtmlEntity());
        assertEquals("PERCENT SIGN", arr[7].getName());
        assertEquals("\\u0025", arr[7].getCodePoint());
        assertEquals("%", arr[7].getCanonicalDecomposition());

        assertEquals(false, arr[8].isDigit());
        assertEquals(false, arr[8].isAlphabet());
        assertEquals(false, arr[8].isAlphaNumeric());
        assertEquals(43, arr[8].getHtmlEntity());
        assertEquals("PLUS SIGN", arr[8].getName());
        assertEquals("\\u002B", arr[8].getCodePoint());
        assertEquals("+", arr[8].getCanonicalDecomposition());

        assertEquals(false, arr[9].isDigit());
        assertEquals(true, arr[9].isAlphabet());
        assertEquals(true, arr[9].isAlphaNumeric());
        assertEquals(88, arr[9].getHtmlEntity());
        assertEquals("LATIN CAPITAL LETTER X", arr[9].getName());
        assertEquals("\\u0058", arr[9].getCodePoint());
        assertEquals("X", arr[9].getCanonicalDecomposition());

        assertEquals(true, arr[10].isDigit());
        assertEquals(false, arr[10].isAlphabet());
        assertEquals(true, arr[10].isAlphaNumeric());
        assertEquals(55, arr[10].getHtmlEntity());
        assertEquals("DIGIT SEVEN", arr[10].getName());
        assertEquals("\\u0037", arr[10].getCodePoint());
        assertEquals("7", arr[10].getCanonicalDecomposition());

        assertEquals(true, arr[11].isDigit());
        assertEquals(false, arr[11].isAlphabet());
        assertEquals(true, arr[11].isAlphaNumeric());
        assertEquals(2925, arr[11].getHtmlEntity());
        assertEquals("ORIYA DIGIT SEVEN", arr[11].getName());
        assertEquals("\\u0B6D", arr[11].getCodePoint());
        assertEquals("୭", arr[11].getCanonicalDecomposition());

        assertEquals(true, arr[12].isDigit());
        assertEquals(false, arr[12].isAlphabet());
        assertEquals(true, arr[12].isAlphaNumeric());
        assertEquals(2415, arr[12].getHtmlEntity());
        assertEquals("DEVANAGARI DIGIT NINE", arr[12].getName());
        assertEquals("\\u096F", arr[12].getCodePoint());
        assertEquals("९", arr[12].getCanonicalDecomposition());
    }

    @SmallTest
    @MediumTest
    @LargeTest
    public void testEquals() {
        CharacterDetails obj = new CharacterDetails(getContext());
        assertEquals(true, obj.getCharacterDetails('ക').equals(obj.getCharacterDetails('ക')));
        assertEquals(false, obj.getCharacterDetails('ऐ').equals(obj.getCharacterDetails('ಶ')));
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
