package regex;

import java.util.regex.Pattern;

public class RegularExpressions {
    private static final String SENTENCE_BEGIN_REGEX = "[A-Z0-9\"]";

    private static final String DOT_END_REGEX = "\\.(\\.{2})?";
    private static final String EXCLAMATION_END_REGEX = "!(!{2}|\\?)?";
    private static final String QUESTION_END_REGEX = "\\?(\\?{2}|!)?";

    private static final String SENTENCE_END_REGEX
            = "(" + DOT_END_REGEX +
                "|" + EXCLAMATION_END_REGEX +
                "|" + QUESTION_END_REGEX +
              ")";

    private static final String NON_TERMINAL_MEMBER = "[^!?.]";
    private static final String DOT_AFTER_DIGIT = "\\.(?<=(\\d\\.))";
    private static final String DOT_SENTENCE_MEMBER = "\\.(?=([^.\\s]|(\\s[^A-Z!?.])))";

    private static final String SENTENCE_MIDST
            = "(" + NON_TERMINAL_MEMBER +
                "|" + DOT_SENTENCE_MEMBER +
                "|" + DOT_AFTER_DIGIT +
              ")*";

    public static Pattern sentencePattern() {
            return Pattern.compile(SENTENCE_BEGIN_REGEX
                    + SENTENCE_MIDST
                    + SENTENCE_END_REGEX);
    }
}
