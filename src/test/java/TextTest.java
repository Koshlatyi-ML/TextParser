import domain.Sentence;
import org.junit.Test;
import domain.text.Text;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class TextTest {
    @Test(expected = IllegalArgumentException.class)
    public void tryParseFirstInvalid() throws Exception {
        String text = " Sentence one. Sentence two.";
        Text.tryParse(text);
    }

    @Test(expected = IllegalArgumentException.class)
    public void tryParseLastInvalid() throws Exception {
        String text = "Sentence one. Sentence two. ";
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
                "Welcome to the Wells Fargo Inc. - first bank in the USA." +
                "Well, this is the end...   \n";
        Text.tryParse(text);

        List<Sentence> sentences = new ArrayList<>();
        // todo fill sentences
    }


    @Test
    public void parseText() throws Exception {
        String text = "I am the domain.text!!!! " +
                      "I has a lot of words!?!!? " +
                      "One more answer?!. bla-bla-bla!!" +
                      "Plain sentence (yes, it is). Plain exclamation! Plain question?" +
                      "1. Statement four. " +
                      "? trash?! S.T.A.L.K.E.R. is the best game ever!!!" +
                      "\"I hope you will be here,\" he said. " +
                      "Welcome to the Wells Fargo Inc. - first bank in the USA..oops!!??.." +
                      "Well, this is the end...";
        Text.tryParse(text);
    }

}