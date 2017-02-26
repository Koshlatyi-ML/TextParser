package domain;

import domain.Sentence;
import org.junit.Test;
import domain.Text;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class TextTest {
    @Test
    public void hashCodeTest() throws Exception {
        String text = "I am the domain.text!!! " +
                "I has a lot of words!? \n" +
                "One more answer?!\t\t ";

        List<Sentence> sentences = new ArrayList<>();
        sentences.add(Sentence.parse("I am the domain.text!!! "));
        sentences.add(Sentence.parse("I has a lot of words!? \n"));
        sentences.add(Sentence.parse("One more answer?!\t\t "));
        Text etalon = new Text(sentences);

        assertEquals(etalon.hashCode(), Text.tryParse(text).hashCode());
    }
    @Test
    public void equalsTrue() throws Exception {
        String text = "I am the domain.text!!! " +
                "I has a lot of words!? \n" +
                "One more answer?!\t\t ";

        assertTrue(Text.forceParse(text).equals(Text.forceParse(text)));
    }
    @Test
    public void equalsFalse() throws Exception {
        String text1 = "I am the domain.text!!! " +
                "I has a lot of words!? \n" +
                "One more answer?!\t\t ";

        String text2 = "I am not the domain.text!!! " +
                "I has a lot of words!? \n" +
                "One more answer?!\t\t ";


        assertFalse(Text.forceParse(text1).equals(Text.forceParse(text2)));
    }



    @Test
    public void toStringTest() throws Exception {
        String text = "I am the domain.text!!! " +
                "I has a lot of words!? \n" +
                "One more answer?!\t\t ";

        assertEquals(text, Text.tryParse(text).toString());
    }

    @Test
    public void appendSentence() throws Exception {
        List<Sentence> sentences = new ArrayList<>();
        sentences.add(Sentence.parse("I am the domain.text!!! "));
        sentences.add(Sentence.parse("I has a lot of words!? \n"));
        sentences.add(Sentence.parse("One more answer?!\t\t "));

        Text text = new Text(sentences);
        text.appendSentence(Sentence.parse("Plain sentence (yes, it is). "));

        sentences.add(Sentence.parse("Plain sentence (yes, it is). "));

        assertEquals(sentences, text.getSentences());
    }

    @Test
    public void setSentences() throws Exception {
        List<Sentence> sentences = new ArrayList<>();
        sentences.add(Sentence.parse("I am the domain.text!!! "));

        Text text = new Text(sentences);

        List<Sentence> newSentences = new ArrayList<>();
        sentences.add(Sentence.parse("S.T.A.L.K.E.R. is the best game ever!!!"));
        sentences.add(Sentence.parse("\"I hope you will be here,\" he said. "));
        sentences.add(Sentence.parse("Welcome to the Wells Fargo Inc. - first bank in the USA. "));

        text.setSentences(newSentences);

        assertEquals(newSentences, text.getSentences());
    }

    @Test(expected = NullPointerException.class)
    public void tryParseNull() throws Exception {
        Text text = Text.tryParse(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void tryParseFirstInvalid() throws Exception {
        String text = " Sentence one. Sentence two.";
        Text.tryParse(text);
    }

    @Test(expected = IllegalArgumentException.class)
    public void tryParseMidstInvalid() throws Exception {
        String text = "Sentence one. !Sentence two.";
        Text.tryParse(text);
    }

    @Test
    public void tryParseValid() throws Exception {
        String text = "I am the domain.text!!! " +
                "I has a lot of words!? \n" +
                "One more answer?!\t\t " +
                "Plain sentence (yes, it is). Plain exclamation! Plain question?" +
                "1. Statement four. " +
                "S.T.A.L.K.E.R. is the best game ever!!!" +
                "\"I hope you will be here,\" he said. " +
                "Welcome to the Wells Fargo Inc. - first bank in the USA. " +
                "Well, this is the end...   \n";

        List<Sentence> sentences = new ArrayList<>();
        sentences.add(Sentence.parse("I am the domain.text!!! "));
        sentences.add(Sentence.parse("I has a lot of words!? \n"));
        sentences.add(Sentence.parse("One more answer?!\t\t "));
        sentences.add(Sentence.parse("Plain sentence (yes, it is). "));
        sentences.add(Sentence.parse("Plain exclamation! "));
        sentences.add(Sentence.parse("Plain question?"));
        sentences.add(Sentence.parse("1. Statement four. "));
        sentences.add(Sentence.parse("S.T.A.L.K.E.R. is the best game ever!!!"));
        sentences.add(Sentence.parse("\"I hope you will be here,\" he said. "));
        sentences.add(Sentence.parse("Welcome to the Wells Fargo Inc. - first bank in the USA. "));
        sentences.add(Sentence.parse("Well, this is the end...   \n"));
        Text etalon = new Text(sentences);

        assertEquals(etalon, Text.tryParse(text));
    }

    @Test
    public void forceParseNull() throws Exception {
        Text text = Text.forceParse(null);
        assertTrue(text.getSentences().isEmpty());
    }

    @Test
    public void forseParse() throws Exception {
        String text = "I am the domain.text!!!! " +
                      "I has a lot of words!?!!? " +
                      "One more answer?!. bla-bla-bla!!" +
                      "Plain sentence (yes, it is). Plain exclamation! Plain question?" +
                      "1. Statement four. " +
                      "? trash?! S.T.A.L.K.E.R. is the best game ever!!!" +
                      "\"I hope you will be here,\" he said. " +
                      "Welcome to the Wells Fargo Inc. - first bank in the USA..oops!!??.." +
                      "Well, this is the end...";

        List<Sentence> sentences = new ArrayList<>();
        sentences.add(Sentence.parse("I am the domain.text!!!"));
        sentences.add(Sentence.parse("I has a lot of words!?"));
        sentences.add(Sentence.parse("One more answer?!"));
        sentences.add(Sentence.parse("Plain sentence (yes, it is). "));
        sentences.add(Sentence.parse("Plain exclamation! "));
        sentences.add(Sentence.parse("Plain question?"));
        sentences.add(Sentence.parse("1. Statement four. "));
        sentences.add(Sentence.parse("S.T.A.L.K.E.R. is the best game ever!!!"));
        sentences.add(Sentence.parse("\"I hope you will be here,\" he said. "));
        sentences.add(Sentence.parse("Welcome to the Wells Fargo Inc. - first bank in the USA."));
        sentences.add(Sentence.parse("Well, this is the end..."));
        Text etalon = new Text(sentences);

        assertEquals(etalon, Text.forceParse(text));
    }

}