package domain;

import util.RegularExpressions;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sentence {
    private List<Token> tokenList;

    private List<Token> wordList;
    private List<Token> markList;
    private List<Token> whitespaceList;

    private Sentence(List<Token> tokenList, List<Token> wordList,
                     List<Token> markList, List<Token> whitespaceList) {
        this.tokenList = tokenList;
        this.wordList = wordList;
        this.markList = markList;
        this.whitespaceList = whitespaceList;
    }

    public static Sentence parse(String str) {
        List<Token> tokenList = new ArrayList<>();

        List<Token> wordList = new ArrayList<>();
        List<Token> markList = new ArrayList<>();
        List<Token> whitespaceList = new ArrayList<>();

        Map<Pattern, List<Token>> patternStorageMap = new HashMap<>();
        patternStorageMap.put(RegularExpressions.markPattern(), markList);
        patternStorageMap.put(RegularExpressions.wordPattern(), wordList);
        patternStorageMap.put(RegularExpressions.whitespacePattern(), whitespaceList);

        int index = 0;
        while (index < str.length()) {
            String input = "" + str.charAt(index);

            final int currentIndex = index;
            Token nextToken = patternStorageMap.keySet().stream()
                    .filter(pattern -> input.matches(pattern.toString()))
                    .map(pattern -> {
                        Matcher matcher = pattern.matcher(str);
                        matcher.find(currentIndex);

                        Token newToken = new Token(matcher.group());
                        patternStorageMap.get(pattern).add(newToken);

                        return newToken;
                    })
                    .findFirst().orElseThrow(IllegalArgumentException::new);

            tokenList.add(nextToken);
            index += nextToken.getToken().length();
        }

        return new Sentence(tokenList, wordList, markList, whitespaceList);
    }

    public List<Token> getTokenList() {
        return tokenList;
    }

    public List<Token> getWordList() {
        return wordList;
    }

    public List<Token> getMarkList() {
        return markList;
    }

    public List<Token> getWhitespaceList() {
        return whitespaceList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sentence)) return false;
        Sentence sentence = (Sentence) o;

        if(tokenList.size() != sentence.getTokenList().size()
                || whitespaceList.size() != sentence.getWhitespaceList().size()
                || wordList.size() != sentence.getWordList().size()
                || markList.size() != sentence.getMarkList().size()) {
            return  false;
        }

        List<Token> allTokens = sentence.getTokenList();
        for (int i = 0; i < allTokens.size(); i++) {
            if (!tokenList.get(i).equals(allTokens.get(i))) {
                return false;
            }
        }

        List<Token> markTokens = sentence.getMarkList();
        for (int i = 0; i < markTokens.size(); i++) {
            if (!markList.get(i).equals(markTokens.get(i))) {
                return false;
            }
        }

        List<Token> whitespaceTokens = sentence.getWhitespaceList();
        for (int i = 0; i < whitespaceTokens.size(); i++) {
            if (!whitespaceList.get(i).equals(whitespaceTokens.get(i))) {
                return false;
            }
        }

        List<Token> wordTokens = sentence.getWordList();
        for (int i = 0; i < wordTokens.size(); i++) {
            if (!wordList.get(i).equals(wordTokens.get(i))) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTokenList(), getWordList(), getMarkList(), getWhitespaceList());
    }

    @Override
    public String toString() {
        String sentence = "";
        return tokenList.stream()
                .map(Token::toString)
                .reduce(sentence, (s, s2) -> s + s2);
    }
}
