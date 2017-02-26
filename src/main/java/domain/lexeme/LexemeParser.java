package domain.lexeme;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeParser {
    private List<Lexeme> storage;
    private Pattern regex;

    public LexemeParser(List<Lexeme> storage, Pattern regex) {
        this.storage = storage;
        this.regex = regex;
    }

    public Lexeme parseAndStore(String str, int index) {
        Matcher matcher = regex.matcher(str);
        if (!matcher.find(index)) {
            throw new IllegalArgumentException();
        }

        Lexeme whitespace = new Lexeme(matcher.group());
        storage.add(whitespace);

        return whitespace;
    }
}
