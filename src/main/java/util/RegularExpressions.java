package util;

import java.util.regex.Pattern;

public class RegularExpressions {
    private static final String SENTENCE_BEGIN_REGEX = "[A-Z0-9\"]";

    private static final String PERIOD_END_REGEX = "\\.(\\.{2})?(\\s+)?";
    private static final String EXCLAMATION_END_REGEX = "!(!{2}|\\?)?(\\s+)?";
    private static final String QUESTION_END_REGEX = "\\?(\\?{2}|!)?(\\s+)?";

    private static final String SENTENCE_END_REGEX
            = "(" + PERIOD_END_REGEX +
                "|" + EXCLAMATION_END_REGEX +
                "|" + QUESTION_END_REGEX +
              ")";

    private static final String NON_TERMINAL_MEMBER = "[^!?.]";
    private static final String PERIOD_AFTER_DIGIT = "\\.(?<=(\\d\\.))";
    private static final String PERIOD_SENTENCE_MEMBER = "\\.(?=([^.\\s]" +
                                                        "|(\\s[^A-Z!?.])))";

    private static final String SENTENCE_MIDST
            = "(" + NON_TERMINAL_MEMBER +
                "|" + PERIOD_SENTENCE_MEMBER +
                "|" + PERIOD_AFTER_DIGIT +
              ")*";

    public static Pattern sentencePattern() {
            return Pattern.compile(SENTENCE_BEGIN_REGEX
                    + SENTENCE_MIDST
                    + SENTENCE_END_REGEX);
    }

    public static Pattern whitespacePattern() {
        return Pattern.compile("\\s+");
    }

    public static Pattern wordPattern() {
        return Pattern.compile("[a-zA-Z]+"); //TODO implement hyphen
    }
    public static Pattern markPattern() {
        return Pattern.compile("[^\\s+a-zA-Z]+");
    }
}
