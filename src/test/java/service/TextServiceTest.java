package service;

import domain.Text;
import org.junit.Test;

import static org.junit.Assert.*;

public class TextServiceTest {
    @Test
    public void printSortedWords() throws Exception {
        String text = "I am the domain.text!!! " +
                "I has a lot of words!? \n" +
                "One more answer?!\t\t " +
                "Plain sentence (yes, it is). Plain exclamation! Plain question?" +
                "1. Statement four. " +
                "S.T.A.L.K.E.R. is the best game ever!!!" +
                "\"I hope you will be here,\" he said. " +
                "Welcome to the Wells Fargo Inc. - first bank in the USA. " +
                "Well, this is the end...   \n";

        new TextService(Text.tryParse(text)).printSortedWords(Emphasis.FIRST_LETTER);
    }
}