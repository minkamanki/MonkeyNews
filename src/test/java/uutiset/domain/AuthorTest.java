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
public class AuthorTest {
    Author a;
    public AuthorTest() {
        a = new Author();
    }
    

    /**
     * Test of getName ja setName
     */
    @Test
    public void testGetName() {
        a.setName("K");       
        assertEquals("K", a.getName());
    }

    /**
     * Test of getArticles ja setArticles
     */
    @Test
    public void testGetArticles() {
        
        Article article = new Article();
        List<Article> result = new ArrayList<>();
        result.add(article);
        a.setArticles(result);

        assertEquals(result, a.getArticles());
    }

}
