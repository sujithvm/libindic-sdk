package org.libindic.shingling;

import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.LargeTest;
import android.test.suitebuilder.annotation.MediumTest;
import android.test.suitebuilder.annotation.SmallTest;

/**
 * Created by sujith on 14/7/14.
 */
public class TestShingling extends AndroidTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @SmallTest
    @MediumTest
    @LargeTest
    public void testletterNgramEnglish() {
        Shingling shingling = new Shingling();

        assertEquals("[a rose, rose is, is a]",
                (shingling.wshingling("a rose is a rose is a rose", 2)).toString());

        assertEquals("[The quick brown fox, quick brown fox jumps, brown fox jumps over, " +
                        "fox jumps over the, jumps over the lazy, over the lazy dog]",
                shingling.wshingling("The quick brown fox jumps over the lazy dog").toString()
        );
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
