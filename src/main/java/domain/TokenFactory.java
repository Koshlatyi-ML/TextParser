package domain;

import java.util.HashMap;
import java.util.Map;

public class TokenFactory {
    private static TokenFactory instance = new TokenFactory();
    private static Map<String, Token> tokensPool;

    private TokenFactory() {
        tokensPool = new HashMap<>();
    }

    public static TokenFactory getInstance() {
        return instance;
    }

    public Token createToken(String value) {
        if (value == null) {
            throw new IllegalArgumentException();
        }

        return tokensPool.computeIfAbsent(value, k -> new Token(value));
    }
}
