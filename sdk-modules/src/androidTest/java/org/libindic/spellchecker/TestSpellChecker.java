package org.libindic.spellchecker;

import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.LargeTest;
import android.test.suitebuilder.annotation.MediumTest;
import android.test.suitebuilder.annotation.SmallTest;


public class TestSpellChecker extends AndroidTestCase {
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @SmallTest
    @MediumTest
    @LargeTest
    public void testConstructor() {
        SpellChecker spellChecker = new SpellChecker(getContext());
        assertNotNull(spellChecker);
    }

    @SmallTest
    @MediumTest
    @LargeTest
    public void testCheck() {
        SpellChecker spellChecker = new SpellChecker(getContext());

        // test english
        assertTrue(spellChecker.check("Judicious"));
        assertFalse(spellChecker.check("Judecious"));

        // test malayalam
        assertTrue(spellChecker.check("മധുരം"));
        assertTrue(spellChecker.check("മാങ്ങ"));

        // test hindi
        assertTrue(spellChecker.check("खुला"));
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
