import regex.RegularExpressions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextValidator {
    private static final Pattern SENTENCE_PATTERN
            = RegularExpressions.sentencePattern();
    private static Matcher matcher;

    private TextValidator() {

    }

    public boolean validate(String text) {
        boolean isValid = true;

        matcher = SENTENCE_PATTERN.matcher(text);
        int currentStart;
        int previousEnd = 0;
        while (matcher.find()) {
            currentStart = matcher.start();

            if (currentStart != previousEnd) {
                isValid= false;
                break;
            }

            previousEnd = currentStart;
        }

        if (matcher.end() != text.length()) {
            isValid = false;
        }

        return isValid;
    }
}
