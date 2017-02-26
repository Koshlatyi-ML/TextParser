package domain;

import domain.lexeme.Lexeme;
import domain.lexeme.LexemeParser;
import util.RegularExpressions;

import java.util.*;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sentence)) return false;
        Sentence sentence = (Sentence) o;

        if(lexemeList.size() != sentence.getLexemeList().size()
                || whitespaceList.size() != sentence.getWhitespaceList().size()
                || wordList.size() != sentence.getWordList().size()
                || markList.size() != sentence.getMarkList().size()) {
            return  false;
        }

        List<Lexeme> allLexemes = sentence.getLexemeList();
        for (int i = 0; i < allLexemes.size(); i++) {
            if (!lexemeList.get(i).equals(allLexemes.get(i))) {
                return false;
            }
        }

        List<Lexeme> markLexemes = sentence.getMarkList();
        for (int i = 0; i < allLexemes.size(); i++) {
            if (!markList.get(i).equals(markLexemes.get(i))) {
                return false;
            }
        }

        List<Lexeme> whitespaceLexemes = sentence.getWhitespaceList();
        for (int i = 0; i < allLexemes.size(); i++) {
            if (!whitespaceList.get(i).equals(whitespaceLexemes.get(i))) {
                return false;
            }
        }

        List<Lexeme> wordLexemes = sentence.getWordList();
        for (int i = 0; i < allLexemes.size(); i++) {
            if (!wordList.get(i).equals(wordLexemes.get(i))) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLexemeList(), getWordList(), getMarkList(), getWhitespaceList());
    }

    @Override
    public String toString() {
        String sentence = "";
        return lexemeList.stream()
                .map(Lexeme::toString)
                .reduce(sentence, (s, s2) -> s + s2);
    }
}
