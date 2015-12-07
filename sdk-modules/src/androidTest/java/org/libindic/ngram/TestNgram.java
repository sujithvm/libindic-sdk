package org.libindic.ngram;

import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.LargeTest;
import android.test.suitebuilder.annotation.MediumTest;
import android.test.suitebuilder.annotation.SmallTest;

/**
 * Created by sujith on 13/7/14.
 */
public class TestNgram extends AndroidTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @SmallTest
    @MediumTest
    @LargeTest
    public void testletterNgramEnglish() {
        Ngram ngram = new Ngram();
        assertEquals("[ca, at, tt, te, er, rp, pi, il, ll, la, ar]",
                (ngram.letterNgram("catterpillar")).toString());

        assertEquals("[cat-, at-t, t-te, -ter, ter-, er-p, r-pi, -pil, pil-, il-l, l-la, -lar]",
                (ngram.syllableNgram("catterpillar", 4)).toString());

        assertEquals("[The quick, quick brown, brown fox, fox jumped, jumped over, over the, the lazy, lazy dog]",
                (ngram.wordNgram("The quick brown fox jumped over the lazy dog")).toString());

    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
