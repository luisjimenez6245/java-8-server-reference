/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import java.sql.ResultSet;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author luis
 */
public class usuarioTest {
    
    public usuarioTest() {
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
     * Test of toJSON method, of class usuario.
     */
    @Test
    public void testToJSON() {
        System.out.println("toJSON");
        usuario instance = new usuario();
        String expResult = "";
        String result = instance.toJSON();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of parse method, of class usuario.
     */
    @Test
    public void testParse_HttpServletRequest() {
        System.out.println("parse");
        HttpServletRequest request = null;
        usuario instance = new usuario();
        usuario expResult = null;
        usuario result = instance.parse(request);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of parse method, of class usuario.
     */
    @Test
    public void testParse_ResultSet() {
        System.out.println("parse");
        ResultSet res = null;
        usuario instance = new usuario();
        usuario expResult = null;
        usuario result = instance.parse(res);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of parseList method, of class usuario.
     */
    @Test
    public void testParseList_HttpServletRequest() {
        System.out.println("parseList");
        HttpServletRequest request = null;
        usuario instance = new usuario();
        List<usuario> expResult = null;
        List<usuario> result = instance.parseList(request);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of parseList method, of class usuario.
     */
    @Test
    public void testParseList_ResultSet() {
        System.out.println("parseList");
        ResultSet res = null;
        usuario instance = new usuario();
        List<usuario> expResult = null;
        List<usuario> result = instance.parseList(res);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
