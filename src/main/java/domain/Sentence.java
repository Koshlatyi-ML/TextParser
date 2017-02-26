package domain;

import domain.lexeme.Lexeme;
import domain.lexeme.LexemeParser;
import util.RegularExpressions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Sentence {
    private List<Lexeme> lexemeList;

    private List<Lexeme> wordList;
    private List<Lexeme> markList;
    private List<Lexeme> whitespaceList;

    private Sentence(List<Lexeme> lexemeList, List<Lexeme> wordList,
                    List<Lexeme> markList, List<Lexeme> whitespaceList) {
        this.lexemeList = lexemeList;
        this.wordList = wordList;
        this.markList = markList;
        this.whitespaceList = whitespaceList;
    }

    public static Sentence parse(String str) {
        List<Lexeme> lexemeList = new ArrayList<>();

        List<Lexeme> wordList = new ArrayList<>();
        List<Lexeme> markList = new ArrayList<>();
        List<Lexeme> whitespaceList = new ArrayList<>();

        Map<Pattern, LexemeParser> patternParserMap = new HashMap<>();
        initMap(patternParserMap, wordList, markList, whitespaceList);


        int index = 0;
        while (index < str.length()) {
            String input = "" + str.charAt(index);

            final int currentIndex = index;
            Lexeme nextLexeme = patternParserMap.keySet().stream()
                    .filter(pattern -> input.matches(pattern.toString()))
                    .map(pattern -> patternParserMap.get(pattern)
                                    .parseAndStore(str, currentIndex))
                    .findFirst()
                    .orElseThrow(IllegalArgumentException::new);

            lexemeList.add(nextLexeme);
            index += nextLexeme.getLexeme().length();
        }

        return new Sentence(lexemeList, wordList, markList, whitespaceList);
    }

    private static void initMap(Map<Pattern, LexemeParser> patternParserMap,
                             List<Lexeme> wordList, List<Lexeme> markList,
                             List<Lexeme> whitespaceList) {

        Pattern markPattern = RegularExpressions.markPattern();
        Pattern wordPattern = RegularExpressions.wordPattern();
        Pattern whitespacePattern = RegularExpressions.whitespacePattern();

        patternParserMap.put(markPattern, new LexemeParser(markList, markPattern));
        patternParserMap.put(wordPattern, new LexemeParser(wordList, wordPattern));
        patternParserMap.put(whitespacePattern, new LexemeParser(whitespaceList, whitespacePattern));
    }

    public List<Lexeme> getLexemeList() {
        return lexemeList;
    }

    public List<Lexeme> getWordList() {
        return wordList;
    }

    public List<Lexeme> getMarkList() {
        return markList;
    }

    public List<Lexeme> getWhitespaceList() {
        return whitespaceList;
    }

    @Override
    public String toString() {
        String sentence = "";
        return lexemeList.stream()
                .map(Lexeme::toString)
                .reduce(sentence, (s, s2) -> s + s2);
    }
}
