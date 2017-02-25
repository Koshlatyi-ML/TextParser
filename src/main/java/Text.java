import regex.RegularExpressions;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Text {
//    private List<Sentence> sentences;

    private static final Pattern SENTENCE_PATTERN
            = RegularExpressions.sentencePattern();

    private static Matcher matcher;

    public static void parseText(String text) {
        List<String> strings = new ArrayList<>();

        matcher = SENTENCE_PATTERN.matcher(text);
        while (matcher.find()) {
            System.out.println(matcher.start() + " " + matcher.end()); //todo prev end != current start
            System.out.println(matcher.group());
        }
    }

//    private Text(List<Sentence> sentences) {
//        this.sentences = sentences;
//    }
//
//    public List<Sentence> getSentences() {
//        return sentences;
//    }
}
