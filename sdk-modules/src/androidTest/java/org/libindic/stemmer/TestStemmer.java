package org.libindic.stemmer;

import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.LargeTest;
import android.test.suitebuilder.annotation.MediumTest;
import android.test.suitebuilder.annotation.SmallTest;

import java.util.Map;

/**
 * Created by sujith on 22/5/14.
 */
public class TestStemmer extends AndroidTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @SmallTest
    @MediumTest
    @LargeTest
    public void testConstructor() {
        Stemmer obj = new Stemmer(getContext());
        assertNotNull(obj);
    }

    @SmallTest
    @MediumTest
    @LargeTest
    public void testGetModuleName() {
        Stemmer obj = new Stemmer(getContext());
        assertNotNull(obj);
        assertEquals(Stemmer.MODULE_NAME, obj.getModuleName());
    }

    @SmallTest
    @MediumTest
    @LargeTest
    public void testGetModuleInformation() {
        Stemmer obj = new Stemmer(getContext());
        assertNotNull(obj);
        assertEquals(Stemmer.MODULE_INFORMATION, obj.getModuleInformation());
    }

    @SmallTest
    @MediumTest
    @LargeTest
    public void testGetStemWordsAsArray() {
        Stemmer obj = new Stemmer(getContext());
        assertNotNull(obj);
        String[] arr = obj.getStemWordsAsArray("തുറക്കുന്ന");
        assertEquals("തുറക്കുക", arr[0]);

        arr = null;

        arr = obj.getStemWordsAsArray("ഇടുക്കി: മഴ കുറഞ്ഞ പശ്ചാത്തലത്തില്‍ ഇടുക്കി അണക്കെട്ട് മൂന്ന് ദിവസത്തേക്ക് തുറക്കേണ്ട");

        //assertEquals("ഇടുക്കുക", arr[0]);
        assertEquals("മഴ", arr[1]);
        assertEquals("കുറഞ്ഞ", arr[2]);
        assertEquals("പശ്ചാത്തലം", arr[3]);
        assertEquals("ഇടുക്കുക", arr[4]);
        assertEquals("അണക്കെട്ട്", arr[5]);
        assertEquals("മൂന്ന്", arr[6]);
        assertEquals("ദിവസം", arr[7]);
        assertEquals("തുറക്കേണ്ട", arr[8]);
    }

    @SmallTest
    @MediumTest
    @LargeTest
    public void testGetStemWordsAsMap() {
        Stemmer obj = new Stemmer(getContext());
        assertNotNull(obj);
        Map<String, String> map = obj.getStemWordsAsMap("തുറക്കുന്ന");
        assertEquals("തുറക്കുക", map.get("തുറക്കുന്ന"));

        map = null;

        map = obj.getStemWordsAsMap("ഇടുക്കി: മഴ കുറഞ്ഞ പശ്ചാത്തലത്തില്‍ ഇടുക്കി അണക്കെട്ട് മൂന്ന് ദിവസത്തേക്ക് തുറക്കേണ്ട");
        assertEquals("ദിവസം", map.get("ദിവസത്തേക്ക്"));
        assertEquals("ഇടുക്കുക", map.get("ഇടുക്കി"));
        assertEquals("കുറഞ്ഞ", map.get("കുറഞ്ഞ"));
        assertEquals("അണക്കെട്ട്", map.get("അണക്കെട്ട്"));
        assertEquals("മൂന്ന്", map.get("മൂന്ന്"));
        assertEquals("പശ്ചാത്തലം", map.get("പശ്ചാത്തലത്തില്‍"));
        assertEquals("തുറക്കേണ്ട", map.get("തുറക്കേണ്ട"));
        assertEquals("മഴ", map.get("മഴ"));
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
