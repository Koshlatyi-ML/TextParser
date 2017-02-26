package domain;

import java.util.Objects;

public class Token {
    private String value;

    Token(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Token)) return false;
        Token token1 = (Token) o;
        return Objects.equals(getValue(), token1.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }

    @Override
    public String toString() {
        return value;
    }
}
