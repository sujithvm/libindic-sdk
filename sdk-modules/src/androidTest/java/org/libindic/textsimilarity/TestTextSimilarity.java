package org.libindic.textsimilarity;

import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.LargeTest;
import android.test.suitebuilder.annotation.MediumTest;
import android.test.suitebuilder.annotation.SmallTest;

/**
 * Created by sujith on 14/7/14.
 */
public class TestTextSimilarity extends AndroidTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @SmallTest
    @MediumTest
    @LargeTest
    public void testletterNgramEnglish() {
        TextSimilarity ts = new TextSimilarity();

        assertEquals(1.0, ts.compare("The quick brown fox jumps over the lazy dog", "The quick brown fox jumps over the lazy dog"));
        assertEquals(0.0, ts.compare("Alpha Beta Gamma Delta Omega Phi", "Sin Cos Tan Cosec Sec Cot"));
        assertEquals(0.4999999999999999, ts.compare("Julie loves me more than Linda loves me", "Jane likes me more than Julie loves me"));

    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
