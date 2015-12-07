package org.libindic.soundex;

import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.LargeTest;
import android.test.suitebuilder.annotation.MediumTest;
import android.test.suitebuilder.annotation.SmallTest;


/**
 * Created by sujith on 20/5/14.
 */
public class TestSoundex extends AndroidTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @SmallTest
    @MediumTest
    @LargeTest
    public void testConstructor() {
        Soundex obj = new Soundex();
        assertNotNull(obj);
    }

    @SmallTest
    @MediumTest
    @LargeTest
    public void testSoundex() {
        Soundex obj = new Soundex();
        assertNotNull(obj);

        assertEquals("v231", obj.soundex("vasudev"));
        assertEquals("R163", obj.soundex("Rupert"));
        assertEquals("ಬDNFQCPC", obj.soundex("ಬೆಂಗಳೂರು"));
        assertEquals("आNPMQ000", obj.soundex("आम्र् फल्"));
    }

    @SmallTest
    @MediumTest
    @LargeTest
    public void testCompare() {
        Soundex obj = new Soundex();
        assertNotNull(obj);

        assertEquals(-1, obj.compare("Bangalore", "ಬೆಂಗಳೂರು"));
        assertEquals(1, obj.compare("ಬೆಂಗಳೂರು", "बॆंगळूरु"));
        assertEquals(0, obj.compare("बॆंगळूरु", "बॆंगळूरु"));
        assertEquals(2, obj.compare("बॆंगळूरु", "आम्र् फल्"));
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
