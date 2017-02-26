package domain;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class SentenceTest {

    @Test
    public void hashCodeTest() throws Exception {
        Sentence sentence1 = Sentence.parse("\"Never gonna give you up\" - said Rick.\n");
        Sentence sentence2 = Sentence.parse("\"Never gonna give you up\" - said Rick.\n");

        assertTrue(sentence1.hashCode() == sentence2.hashCode());
    }

    @Test
    public void equasTrue() throws Exception {
        Sentence sentence1 = Sentence.parse("\"Never gonna give you up\" - said Rick.\n");
        Sentence sentence2 = Sentence.parse("\"Never gonna give you up\" - said Rick.\n");

        assertTrue(sentence1.equals(sentence2));
    }

    @Test
    public void equalsFalse() throws Exception {
        Sentence sentence1 = Sentence.parse("\"Never gonna give you up\" - said Rick.\n");
        Sentence sentence2 = Sentence.parse("\"Never gonna give you up\" - said Rick...\n");

        assertFalse(sentence1.equals(sentence2));
    }

    @Test
    public void toStringTest() throws Exception {
        Sentence sentence = Sentence.parse("\"Never gonna give you up\" - said Rick.\n");

        assertEquals("\"Never gonna give you up\" - said Rick.\n", sentence.toString());

    }

    @Test
    public void parseLexemeListSame() throws Exception {
        Sentence sentence = Sentence.parse("\"You'll never gonna give you up\" - said Rick.\n");

        List<Token> tokenList = new ArrayList<>();
        tokenList.add(new Token("\""));
        tokenList.add(new Token("You'll"));
        tokenList.add(new Token(" "));
        tokenList.add(new Token("never"));
        tokenList.add(new Token(" "));
        tokenList.add(new Token("gonna"));
        tokenList.add(new Token(" "));
        tokenList.add(new Token("give"));
        tokenList.add(new Token(" "));
        tokenList.add(new Token("you"));
        tokenList.add(new Token(" "));
        tokenList.add(new Token("up"));
        tokenList.add(new Token("\""));
        tokenList.add(new Token(" "));
        tokenList.add(new Token("-"));
        tokenList.add(new Token(" "));
        tokenList.add(new Token("said"));
        tokenList.add(new Token(" "));
        tokenList.add(new Token("Rick"));
        tokenList.add(new Token("."));
        tokenList.add(new Token("\n"));

        boolean equals = true;
        if (tokenList.size() == sentence.getTokenList().size()) {
            for (int i = 0; i < tokenList.size(); i++) {
                if (!tokenList.get(i).equals(sentence.getTokenList().get(i))) {
                    equals = false;
                    break;
                }
            }

        } else {
            equals = false;
        }

        assertTrue(equals);
    }

    @Test
    public void parseWordListSame() throws Exception {
        Sentence sentence = Sentence.parse("\"Never gonna give yours' up\" - said alt-right Rick.\n");

        List<Token> words = new ArrayList<>();
        words.add(new Token("Never"));
        words.add(new Token("gonna"));
        words.add(new Token("give"));
        words.add(new Token("yours'"));
        words.add(new Token("up"));
        words.add(new Token("said"));
        words.add(new Token("alt-right"));
        words.add(new Token("Rick"));

        boolean equals = true;
        if (words.size() == sentence.getWordList().size()) {
            for (int i = 0; i < words.size(); i++) {
                if (!words.get(i).equals(sentence.getWordList().get(i))) {
                    equals = false;
                    break;
                }
            }

        } else {
            equals = false;
        }

        assertTrue(equals);
    }

    @Test
    public void parseMarkListSame() throws Exception {
        Sentence sentence = Sentence.parse("\"Never gonna give you up\" - said Rick.\n");

        List<Token> marks = new ArrayList<>();
        marks.add(new Token("\""));
        marks.add(new Token("\""));
        marks.add(new Token("-"));
        marks.add(new Token("."));

        boolean equals = true;
        if (marks.size() == sentence.getMarkList().size()) {
            for (int i = 0; i < marks.size(); i++) {
                if (!marks.get(i).equals(sentence.getMarkList().get(i))) {
                    equals = false;
                    break;
                }
            }

        } else {
            equals = false;
        }

        assertTrue(equals);
    }

    @Test
    public void parseWhitespaceListSame() throws Exception {
        Sentence sentence = Sentence.parse("\"Never gonna give you up\" - said Rick.\n");

        List<Token> whitespaces = new ArrayList<>();
        whitespaces.add(new Token(" "));
        whitespaces.add(new Token(" "));
        whitespaces.add(new Token(" "));
        whitespaces.add(new Token(" "));
        whitespaces.add(new Token(" "));
        whitespaces.add(new Token(" "));
        whitespaces.add(new Token(" "));
        whitespaces.add(new Token("\n"));

        boolean equals = true;
        if (whitespaces.size() == sentence.getWhitespaceList().size()) {
            for (int i = 0; i < whitespaces.size(); i++) {
                if (!whitespaces.get(i).equals(sentence.getWhitespaceList().get(i))) {
                    equals = false;
                    break;
                }
            }

        } else {
            equals = false;
        }

        assertTrue(equals);
    }

}