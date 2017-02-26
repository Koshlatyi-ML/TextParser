package domain;

import util.RegularExpressions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Text {
    private List<Sentence> sentences;

    private static final Pattern SENTENCE_PATTERN
            = RegularExpressions.sentencePattern();
    private static Matcher matcher;

    public Text(List<Sentence> sentences) {
        this.sentences = sentences;
    }

    public static Text forceParse(String text) {
        List<Sentence> sentences = new ArrayList<>();

        if (text != null) {
            matcher = SENTENCE_PATTERN.matcher(text);
            while (matcher.find()) {
                sentences.add(Sentence.parse(matcher.group()));
            }
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

    public void appendSentence(Sentence sentence) {
        this.sentences.add(sentence);
    }

    public void setSentences(List<Sentence> sentences) {
        this.sentences = sentences;
    }

    public List<Sentence> getSentences() {
        return sentences;
    }

    public void printWords() {
        List<String> words =  sentences.stream()
                .map(Sentence::getWordList)
                .flatMap(Collection::stream)
                .map(Token::toString)
                .sorted(String::compareToIgnoreCase)
                .collect(Collectors.toList());

        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_RED = "\u001B[31m";



        String letter = "";
        for (String word : words) {
            if (word.substring(0,1).compareToIgnoreCase(letter) > 0) {
                letter = word.substring(0,1);
                System.out.print(ANSI_RED);
            }
            System.out.println(word + ANSI_RESET);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Text)) return false;
        Text text = (Text) o;

        if(sentences.size() != text.getSentences().size()) {
            return  false;
        }

        List<Sentence> argSentences = text.getSentences();
        for (int i = 0; i < sentences.size(); i++) {
            if (!sentences.get(i).equals(argSentences.get(i))) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSentences());
    }

    @Override
    public String toString() {
        String text = "";
        return sentences.stream()
                .map(Sentence::toString)
                .reduce(text, (s, s2) -> s + s2);
    }
}
