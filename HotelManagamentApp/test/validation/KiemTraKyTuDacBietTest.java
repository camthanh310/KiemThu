/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validation;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Hau
 */
public class KiemTraKyTuDacBietTest {
    
    public KiemTraKyTuDacBietTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of textCharSpecial method, of class KiemTraKyTuDacBiet.
     */
    @Test
    public void kiemTraKyTuDacBiet() {
        boolean expectResult = true;
        boolean actualResult = false;
        String text = "2!";
        actualResult = KiemTraKyTuDacBiet.kiemTraKyTuDacBiet(text);
        assertEquals(expectResult, actualResult);
    }
    
}
