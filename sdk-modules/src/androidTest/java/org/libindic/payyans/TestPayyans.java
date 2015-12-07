package org.libindic.payyans;

import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.LargeTest;
import android.test.suitebuilder.annotation.MediumTest;
import android.test.suitebuilder.annotation.SmallTest;

import java.io.UnsupportedEncodingException;

/**
 * Created by sujith on 20/5/14.
 */
public class TestPayyans extends AndroidTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @SmallTest
    @MediumTest
    @LargeTest
    public void testConstructor() {
        Payyans obj;

        obj = new Payyans(getContext());
        assertNotNull(obj);


        obj = new Payyans(getContext(), Payyans.FONT_MAP_AMBILI);
        assertNotNull(obj);
        obj = new Payyans(getContext(), Payyans.FONT_MAP_CHARAKA);
        assertNotNull(obj);
        obj = new Payyans(getContext(), Payyans.FONT_MAP_HARITHA);
        assertNotNull(obj);
        obj = new Payyans(getContext(), Payyans.FONT_MAP_INDULEKHA);
        assertNotNull(obj);
        obj = new Payyans(getContext(), Payyans.FONT_MAP_KARTHIKA);
        assertNotNull(obj);
        obj = new Payyans(getContext(), Payyans.FONT_MAP_MANORAMA);
        assertNotNull(obj);
        obj = new Payyans(getContext(), Payyans.FONT_MAP_MATWEB);
        assertNotNull(obj);
        obj = new Payyans(getContext(), Payyans.FONT_MAP_NANDINI);
        assertNotNull(obj);
        obj = new Payyans(getContext(), Payyans.FONT_MAP_PANCHARI);
        assertNotNull(obj);
        obj = new Payyans(getContext(), Payyans.FONT_MAP_REVATHI);
        assertNotNull(obj);
        obj = new Payyans(getContext(), Payyans.FONT_MAP_TEMPLATE);
        assertNotNull(obj);
        obj = new Payyans(getContext(), Payyans.FONT_MAP_UMA);
        assertNotNull(obj);
        obj = new Payyans(getContext(), Payyans.FONT_MAP_VALLUVAR);
        assertNotNull(obj);


        obj = new Payyans(getContext(), Payyans.FONT_MAP_AMBILI, Payyans.ASCII_TO_UNICODE);
        assertNotNull(obj);
        obj = new Payyans(getContext(), Payyans.FONT_MAP_CHARAKA, Payyans.ASCII_TO_UNICODE);
        assertNotNull(obj);
        obj = new Payyans(getContext(), Payyans.FONT_MAP_HARITHA, Payyans.ASCII_TO_UNICODE);
        assertNotNull(obj);
        obj = new Payyans(getContext(), Payyans.FONT_MAP_INDULEKHA, Payyans.ASCII_TO_UNICODE);
        assertNotNull(obj);
        obj = new Payyans(getContext(), Payyans.FONT_MAP_KARTHIKA, Payyans.ASCII_TO_UNICODE);
        assertNotNull(obj);
        obj = new Payyans(getContext(), Payyans.FONT_MAP_MANORAMA, Payyans.ASCII_TO_UNICODE);
        assertNotNull(obj);
        obj = new Payyans(getContext(), Payyans.FONT_MAP_MATWEB, Payyans.ASCII_TO_UNICODE);
        assertNotNull(obj);
        obj = new Payyans(getContext(), Payyans.FONT_MAP_NANDINI, Payyans.ASCII_TO_UNICODE);
        assertNotNull(obj);
        obj = new Payyans(getContext(), Payyans.FONT_MAP_PANCHARI, Payyans.ASCII_TO_UNICODE);
        assertNotNull(obj);
        obj = new Payyans(getContext(), Payyans.FONT_MAP_REVATHI, Payyans.ASCII_TO_UNICODE);
        assertNotNull(obj);
        obj = new Payyans(getContext(), Payyans.FONT_MAP_TEMPLATE, Payyans.ASCII_TO_UNICODE);
        assertNotNull(obj);
        obj = new Payyans(getContext(), Payyans.FONT_MAP_UMA, Payyans.ASCII_TO_UNICODE);
        assertNotNull(obj);
        obj = new Payyans(getContext(), Payyans.FONT_MAP_VALLUVAR, Payyans.ASCII_TO_UNICODE);
        assertNotNull(obj);

        obj = new Payyans(getContext(), Payyans.FONT_MAP_AMBILI, Payyans.UNICODE_TO_ASCII);
        assertNotNull(obj);
        obj = new Payyans(getContext(), Payyans.FONT_MAP_CHARAKA, Payyans.UNICODE_TO_ASCII);
        assertNotNull(obj);
        obj = new Payyans(getContext(), Payyans.FONT_MAP_HARITHA, Payyans.UNICODE_TO_ASCII);
        assertNotNull(obj);
        obj = new Payyans(getContext(), Payyans.FONT_MAP_INDULEKHA, Payyans.UNICODE_TO_ASCII);
        assertNotNull(obj);
        obj = new Payyans(getContext(), Payyans.FONT_MAP_KARTHIKA, Payyans.UNICODE_TO_ASCII);
        assertNotNull(obj);
        obj = new Payyans(getContext(), Payyans.FONT_MAP_MANORAMA, Payyans.UNICODE_TO_ASCII);
        assertNotNull(obj);
        obj = new Payyans(getContext(), Payyans.FONT_MAP_MATWEB, Payyans.UNICODE_TO_ASCII);
        assertNotNull(obj);
        obj = new Payyans(getContext(), Payyans.FONT_MAP_NANDINI, Payyans.UNICODE_TO_ASCII);
        assertNotNull(obj);
        obj = new Payyans(getContext(), Payyans.FONT_MAP_PANCHARI, Payyans.UNICODE_TO_ASCII);
        assertNotNull(obj);
        obj = new Payyans(getContext(), Payyans.FONT_MAP_REVATHI, Payyans.UNICODE_TO_ASCII);
        assertNotNull(obj);
        obj = new Payyans(getContext(), Payyans.FONT_MAP_TEMPLATE, Payyans.UNICODE_TO_ASCII);
        assertNotNull(obj);
        obj = new Payyans(getContext(), Payyans.FONT_MAP_UMA, Payyans.UNICODE_TO_ASCII);
        assertNotNull(obj);
        obj = new Payyans(getContext(), Payyans.FONT_MAP_VALLUVAR, Payyans.UNICODE_TO_ASCII);
        assertNotNull(obj);
    }

    @SmallTest
    @MediumTest
    @LargeTest
    public void testIsPrebase() throws UnsupportedEncodingException {
        Payyans obj = new Payyans(getContext());
        assertNotNull(obj);
        assertEquals(true, obj.isPrebase("േ"));
        assertEquals(true, obj.isPrebase("ൈ"));
        assertEquals(true, obj.isPrebase("ൊ"));
        assertEquals(true, obj.isPrebase("ോ"));
        assertEquals(true, obj.isPrebase("ൌ"));
        assertEquals(true, obj.isPrebase("്ര"));
        assertEquals(true, obj.isPrebase("െ"));
        assertEquals(false, obj.isPrebase("ഇ"));
    }

    @SmallTest
    @MediumTest
    @LargeTest
    public void testIsPostbase() throws UnsupportedEncodingException {
        Payyans obj = new Payyans(getContext());
        assertNotNull(obj);

        assertEquals(true, obj.isPostbase("്യ"));
        assertEquals(true, obj.isPostbase("്വ"));
        assertEquals(false, obj.isPostbase("ഇ"));
    }

    @SmallTest
    @MediumTest
    @LargeTest
    public void testGetConvertedText() {
        Payyans obj = new Payyans(getContext(), Payyans.FONT_MAP_AMBILI, Payyans.ASCII_TO_UNICODE);
        assertNotNull(obj);
        assertEquals("വലഹഹീ", obj.getConvertText("hello"));
        assertEquals("ഠവല ൂൗശരസ യൃീംി ളീഃ ഷൗാു ീെ്‌ലൃ വേല ഹമ്വ്യ റീഴ", obj.getConvertText("The quick brown fox jumps over the lazy dog"));

        obj = null;

        obj = new Payyans(getContext(), Payyans.FONT_MAP_AMBILI, Payyans.UNICODE_TO_ASCII);
        assertNotNull(obj);
        assertEquals("hello", obj.getConvertText("വലഹഹീ"));
        assertEquals("The quick brown fox jump sover the lazy dog", obj.getConvertText("ഠവല ൂൗശരസ യൃീംി ളീഃ ഷൗാു ീെ്‌ലൃ വേല ഹമ്വ്യ റീഴ"));

    }

    @SmallTest
    @MediumTest
    @LargeTest
    public void testGetModuleName() {
        Payyans obj = new Payyans(getContext());
        assertNotNull(obj);
        assertEquals(Payyans.MODULE_NAME, obj.getModuleName());
    }

    @SmallTest
    @MediumTest
    @LargeTest
    public void testGetModuleInformation() {
        Payyans obj = new Payyans(getContext());
        assertNotNull(obj);
        assertEquals(Payyans.MODULE_INFORMATION, obj.getModuleInformation());
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
