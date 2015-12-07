package org.libindic.payyans;

import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.LargeTest;
import android.test.suitebuilder.annotation.MediumTest;
import android.test.suitebuilder.annotation.SmallTest;

/**
 * Created by sujith on 20/5/14.
 */
public class TestConstants extends AndroidTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @SmallTest
    @MediumTest
    @LargeTest
    public void testDirectionConstants() {
        assertEquals(0, Payyans.ASCII_TO_UNICODE);
        assertEquals(1, Payyans.UNICODE_TO_ASCII);
    }

    @SmallTest
    @MediumTest
    @LargeTest
    public void testFontMapConstants() {
        assertEquals(0, Payyans.FONT_MAP_AMBILI);
        assertEquals(1, Payyans.FONT_MAP_CHARAKA);
        assertEquals(2, Payyans.FONT_MAP_HARITHA);
        assertEquals(3, Payyans.FONT_MAP_INDULEKHA);
        assertEquals(4, Payyans.FONT_MAP_KARTHIKA);
        assertEquals(5, Payyans.FONT_MAP_MANORAMA);
        assertEquals(6, Payyans.FONT_MAP_MATWEB);
        assertEquals(7, Payyans.FONT_MAP_NANDINI);
        assertEquals(8, Payyans.FONT_MAP_PANCHARI);
        assertEquals(9, Payyans.FONT_MAP_REVATHI);
        assertEquals(10, Payyans.FONT_MAP_TEMPLATE);
        assertEquals(11, Payyans.FONT_MAP_UMA);
        assertEquals(12, Payyans.FONT_MAP_VALLUVAR);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
