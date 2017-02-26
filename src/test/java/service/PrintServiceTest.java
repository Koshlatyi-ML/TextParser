package service;

import domain.Text;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class PrintServiceTest {
    private static PrintService printService = new PrintService();
    private static String text = "I am the domain.text!!! " +
            "I has a lot of words!? \n" +
            "One more answer?!\t\t " +
            "Plain sentence (yes, it is). Plain exclamation! Plain question?" +
            "1. Statement four. " +
            "S.T.A.L.K.E.R. is the best game ever!!!" +
            "\"I hope you will be here,\" he said. " +
            "Welcome to the Wells Fargo Inc. - first bank in the USA. " +
            "Well, this is the end...   \n";
    private static List<String> stringList;

    @BeforeClass
    public static void init() {
        stringList = Arrays.asList(text.split("\\s+"));
        stringList.sort(String::compareToIgnoreCase);
    }

    @Test
    public void printWordsNoneEmphasis() throws Exception {
        printHeader("NONE-EMPHASIS");
        printService.printWords(stringList, Emphasis.NONE);
    }


    @Test
    public void printWordsFirstLetterEmphasis() throws Exception {
        printHeader("FIRST-LETTER-EMPHASIS");
        printService.printWords(stringList,  Emphasis.FIRST_LETTER);
    }

    @Test
    public void printWordsWholeStringEmphasis() throws Exception {
        printHeader("WHOLE-STRING-EMPHASIS");
        printService.printWords(stringList, Emphasis.WHOLE_STRING);
    }

    private void printHeader(String message) {
        for (int i = 0; i < 30; i++) {
            System.out.print("-");
        }
        System.out.print(message);
        for (int i = 0; i < 30; i++) {
            System.out.print("-");
        }
        System.out.print("\n");
    }
}
