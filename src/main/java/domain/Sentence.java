package domain;

import util.RegularExpressions;

import java.util.*;
import java.util.regex.Matcher;
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

        Map<Pattern, List<Lexeme>> patternStorageMap = new HashMap<>();
        patternStorageMap.put(RegularExpressions.markPattern(), markList);
        patternStorageMap.put(RegularExpressions.wordPattern(), wordList);
        patternStorageMap.put(RegularExpressions.whitespacePattern(), whitespaceList);

        int index = 0;
        while (index < str.length()) {
            String input = "" + str.charAt(index);

            final int currentIndex = index;
            Lexeme nextLexeme = patternStorageMap.keySet().stream()
                    .filter(pattern -> input.matches(pattern.toString()))
                    .map(pattern -> {
                        Matcher matcher = pattern.matcher(str);
                        matcher.find(currentIndex);

                        Lexeme newLexeme = new Lexeme(matcher.group());
                        patternStorageMap.get(pattern).add(newLexeme);

                        return newLexeme;
                    })
                    .findFirst().orElseThrow(IllegalArgumentException::new);

            lexemeList.add(nextLexeme);
            index += nextLexeme.getLexeme().length();
        }

        return new Sentence(lexemeList, wordList, markList, whitespaceList);
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
        for (int i = 0; i < markLexemes.size(); i++) {
            if (!markList.get(i).equals(markLexemes.get(i))) {
                return false;
            }
        }

        List<Lexeme> whitespaceLexemes = sentence.getWhitespaceList();
        for (int i = 0; i < whitespaceLexemes.size(); i++) {
            if (!whitespaceList.get(i).equals(whitespaceLexemes.get(i))) {
                return false;
            }
        }

        List<Lexeme> wordLexemes = sentence.getWordList();
        for (int i = 0; i < wordLexemes.size(); i++) {
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
