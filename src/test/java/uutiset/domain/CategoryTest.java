/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uutiset.domain;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Minka
 */
public class CategoryTest {

    Category c;

    public CategoryTest() {
        c = new Category();
    }

    /**
     * Test of getName ja setName
     */
    @Test
    public void testGetName() {
        c.setName("K");
        assertEquals("K", c.getName());
    }

    /**
     * Test of boolean
     */
    @Test
    public void testNav() {
        c.setNav(true);
        assertTrue(c.isNav());
    }
    /**
     * Test of boolean
     */
    @Test
    public void testNavNot() {
        c.setNav(false);
        assertFalse(c.isNav());
    }
    /**
     * Test of getArticles ja setArticles
     */
    @Test
    public void testGetArticles() {

        Article article = new Article();
        List<Article> result = new ArrayList<>();
        result.add(article);
        c.setArticles(result);

        assertEquals(result, c.getArticles());
    }

}
