import org.junit.Test;

import static org.junit.Assert.*;

public class TextTest {


    @Test
    public void parseText() throws Exception {
        String text = "I am the text!!!! " +
                      "I has a lot of words!?!!? " +
                      "One more answer?!. bla-bla-bla!!" +
                      "Plain sentence (yes, it is). Plain exclamation! Plain question?" +
                      "1. Statement four. " +
                      "? trash?! S.T.A.L.K.E.R. is the best game ever!!!" +
                      "\"I hope you will be here,\" he said. " +
                      "Welcome to the Wells Fargo Inc. - first bank in the USA..oops!!??.." +
                      "Well, this is the end...";
        Text.parseText(text);
    }

}