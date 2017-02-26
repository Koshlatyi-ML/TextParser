package domain;

import org.junit.Test;
import static org.junit.Assert.*;

public class TokenTest {
    @Test
    public void equalsTrue() throws Exception {
        Token token1 = new Token("token");
        Token token2 = new Token("token");

        assertTrue(token1.equals(token2));
    }

    @Test
    public void equalsFalse() throws Exception {
        Token token1 = new Token("token");
        Token token2 = new Token("not-token");

        assertFalse(token1.equals(token2));

    }


    @Test
    public void hashCodeTest() throws Exception {
        Token token1 = new Token("token");
        Token token2 = new Token("token");

        assertEquals(token1.hashCode(), token2.hashCode());
    }

    @Test
    public void toStringTest() throws Exception {
        Token token = new Token("token");

        assertEquals("token", token.toString());
    }

}