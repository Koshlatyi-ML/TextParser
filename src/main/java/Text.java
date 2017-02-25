import regex.RegularExpressions;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Text {
    private List<Sentence> sentences;

    private boolean hasValidated = false;
    private boolean isValid;

    private static final Pattern SENTENCE_PATTERN
            = RegularExpressions.sentencePattern();
    private static Matcher matcher;

    private Text(List<Sentence> sentences) {
        this.sentences = sentences;
    }

    public static Text forceParse(String text) {
        List<Sentence> sentences = new ArrayList<>();

        matcher = SENTENCE_PATTERN.matcher(text);
        while (matcher.find()) {
            sentences.add(Sentence.createSentence());
        }

        return new Text(sentences);
    }

    public static Text tryParse(String text) {
        List<Sentence> sentences = new ArrayList<>();

        matcher = SENTENCE_PATTERN.matcher(text);
        int currentStart;
        int previousEnd = 0;
        while (matcher.find()) {
            currentStart = matcher.start();

            if (currentStart != previousEnd) {
                throw new IllegalArgumentException();
            }

            previousEnd = currentStart;
        }

        if (matcher.end() != text.length()) {
            throw new IllegalArgumentException();
        }

        return new Text(sentences);
    }

    public void appendSentences(List<Sentence> sentences) {
        this.sentences.addAll(sentences);
    }

    public void setSentences(List<Sentence> sentences) {
        this.sentences = sentences;
    }

    public List<Sentence> getSentences() {
        return sentences;
    }
//    private Text(List<Sentence> sentences) {
//        this.sentences = sentences;
//    }
//
//    public List<Sentence> getSentences() {
//        return sentences;
//    }
}
