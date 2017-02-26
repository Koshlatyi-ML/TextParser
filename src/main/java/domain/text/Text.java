package domain.text;

import domain.Sentence;
import util.RegularExpressions;

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

    public Text(List<Sentence> sentences) {
        this.sentences = sentences;
    }

    public static Text forceParse(String text) {
        List<Sentence> sentences = new ArrayList<>();

        matcher = SENTENCE_PATTERN.matcher(text);
        while (matcher.find()) {
            sentences.add(Sentence.parse(matcher.group()));
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

            previousEnd = matcher.end();
            sentences.add(Sentence.parse(matcher.group()));
        }

        if (previousEnd != text.length()) {
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

    @Override
    public String toString() {
        String text = "";
        return sentences.stream()
                .map(Sentence::toString)
                .reduce(text, (s, s2) -> s + s2);
    }
}
