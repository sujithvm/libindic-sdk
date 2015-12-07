package org.libindic.inexactsearch;

import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.LargeTest;
import android.test.suitebuilder.annotation.MediumTest;
import android.test.suitebuilder.annotation.SmallTest;

/**
 * Created by sujith on 21/7/14.
 */
public class TestInexactSearch extends AndroidTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @SmallTest
    @MediumTest
    @LargeTest
    public void testCompare() {
        InexactSearch is = new InexactSearch();
        assertEquals(0.9, is.compare("ಮಾವಿನ ಹಣ್ಣು", "माविन हण्णु "));
        assertEquals(0.0, is.compare("ಮಾವಿನ ಹಣ್ಣು", "mango"));
        assertEquals(1.0, is.compare("ಮಾವಿನ ಹಣ್ಣು", "ಮಾವಿನ ಹಣ್ಣು"));
    }

    @SmallTest
    @MediumTest
    @LargeTest
    public void testBigramAverage() {
        InexactSearch is = new InexactSearch();
        assertEquals(0.11999999999999997, is.bigramAverage("toxicity", "city"));
        assertEquals(0.875, is.bigramAverage("Mangalore", "Bangalore"));
        assertEquals(0.43209876543209874, is.bigramAverage("Cauliflower", "Sunflower"));
        assertEquals(0.22222222222222224, is.bigramAverage("apple", "Pineapple"));
        assertEquals(1.0, is.bigramAverage("mango", "mango"));
        assertEquals(0.1234567901234568, is.bigramAverage("apple", "planet"));
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
