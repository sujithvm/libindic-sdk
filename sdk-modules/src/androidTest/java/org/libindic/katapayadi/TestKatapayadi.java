package org.libindic.katapayadi;

import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.LargeTest;
import android.test.suitebuilder.annotation.MediumTest;
import android.test.suitebuilder.annotation.SmallTest;

import java.util.List;

/**
 * Created by sujith on 31/5/14.
 */
public class TestKatapayadi extends AndroidTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @SmallTest
    @MediumTest
    @LargeTest
    public void testConstructor() {
        Katapayadi obj = new Katapayadi();
        assertNotNull(obj);
    }

    @SmallTest
    @MediumTest
    @LargeTest
    public void testGetNumber() {
        Katapayadi obj = new Katapayadi();
        assertNotNull(obj);
        assertEquals("31415926536", obj.getKatapayadiNumber("ചണ്ഡാംശുചന്ദ്രാധമകുംഭിപാല"));
    }

    @SmallTest
    @MediumTest
    @LargeTest
    public void testGetSwarasthanas() {
        Katapayadi obj = new Katapayadi();
        assertNotNull(obj);

        List<String> swarasthans;

        swarasthans = obj.getSwarasthanas("1");
        assertEquals("[Sa, Ri1, Ga1, Ma1, Pa, Da1, Ni1, Sa]", swarasthans.toString());
        swarasthans = obj.getSwarasthanas("2");
        assertEquals("[Sa, Ri1, Ga1, Ma1, Pa, Da1, Ni2, Sa]", swarasthans.toString());
        swarasthans = obj.getSwarasthanas("3");
        assertEquals("[Sa, Ri1, Ga1, Ma1, Pa, Da1, Ni3, Sa]", swarasthans.toString());
        swarasthans = obj.getSwarasthanas("4");
        assertEquals("[Sa, Ri1, Ga1, Ma1, Pa, Da2, Ni2, Sa]", swarasthans.toString());
        swarasthans = obj.getSwarasthanas("5");
        assertEquals("[Sa, Ri1, Ga1, Ma1, Pa, Da2, Ni3, Sa]", swarasthans.toString());

    }

    @SmallTest
    @MediumTest
    @LargeTest
    public void testGetModuleName() {
        Katapayadi obj = new Katapayadi();
        assertNotNull(obj);
        assertEquals(Katapayadi.MODULE_NAME, obj.getModuleName());
    }

    @SmallTest
    @MediumTest
    @LargeTest
    public void testGetModuleInformation() {
        Katapayadi obj = new Katapayadi();
        assertNotNull(obj);
        assertEquals(Katapayadi.MODULE_INFORMATION, obj.getModuleInformation());
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
