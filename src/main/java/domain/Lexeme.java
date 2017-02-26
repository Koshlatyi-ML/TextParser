package domain;

import java.util.Objects;

public class Lexeme {
    private String lexeme;

    public Lexeme(String lexeme) {
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
        if (!(o instanceof Lexeme)) return false;
        Lexeme lexeme1 = (Lexeme) o;
        return Objects.equals(getLexeme(), lexeme1.getLexeme());
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
