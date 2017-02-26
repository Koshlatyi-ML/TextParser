package service;

import domain.Sentence;
import domain.Text;
import domain.Token;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class PrintService {
    private Map<Emphasis, Consumer<List<String>>> emphasisPrintMap
            = new HashMap<>();

    final String ANSI_RESET = "\u001B[0m";
    final String ANSI_RED = "\u001B[31m";

    {
        emphasisPrintMap.put(Emphasis.NONE, this::print);
        emphasisPrintMap.put(Emphasis.FIRST_LETTER, this::printLetterEmphasize);
        emphasisPrintMap.put(Emphasis.WHOLE_STRING, this::printStringEmphasize);
    }

    public void printWords(List<String> strings, Emphasis emphasis) {
        emphasisPrintMap.get(emphasis).accept(strings);
    }

    private void print(List<String> strings) {
        strings.forEach(System.out::println);
    }

    private void printLetterEmphasize(List<String> strings) {
        String letter = "";
        for (String str : strings) {
            if (str.substring(0,1).compareToIgnoreCase(letter) > 0) {
                letter = str.substring(0,1);
                System.out.print(ANSI_RED);
            }
            System.out.println(str + ANSI_RESET);
        }
    }

    private void printStringEmphasize(List<String> strings) {
        String old = "";
        for (String str : strings) {
            if (str.compareToIgnoreCase(old) > 0) {
                old = str;
                System.out.print(ANSI_RED);
            }
            System.out.println(str + ANSI_RESET);
        }
    }
}
