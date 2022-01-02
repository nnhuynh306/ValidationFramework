package validators.builtin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexValidator<String> extends BuiltInValidator<String> {
    private final String regex;

    public RegexValidator( String regex) {
        super();
        this.regex = regex;
    }

    @Override
    protected java.lang.String createDefaultFailedMessage() {
        return getName()+ " must be match with regex" + regex;
    }

    @Override
    public boolean isValid(String str) {
        Pattern pattern = Pattern.compile((java.lang.String) regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher((CharSequence) str);
        return matcher.find();
    }
}
