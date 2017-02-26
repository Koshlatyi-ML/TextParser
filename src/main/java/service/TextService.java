package service;

import domain.Sentence;
import domain.Text;
import domain.Token;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class TextService {
    private Text text;

    public TextService(Text text) {
        this.text = text;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

    public void printSortedWords(Emphasis emphasis) {
        List<String> words = text.getSentences().stream()
                .map(Sentence::getWordList)
                .flatMap(Collection::stream)
                .map(Token::toString)
                .sorted(String::compareToIgnoreCase)
                .collect(Collectors.toList());

        new PrintService().printWords(words, emphasis);
    }
}
