package domain;

import java.util.Objects;

public class Token {
    private String lexeme;

    public Token(String lexeme) {
        this.lexeme = lexeme;
    }

    public String getLexeme() {
        return lexeme;
    }

    public void setLexeme(String lexeme) {
        this.lexeme = lexeme;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Token)) return false;
        Token token1 = (Token) o;
        return Objects.equals(getLexeme(), token1.getLexeme());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLexeme());
    }

    @Override
    public String toString() {
        return lexeme;
    }
}
