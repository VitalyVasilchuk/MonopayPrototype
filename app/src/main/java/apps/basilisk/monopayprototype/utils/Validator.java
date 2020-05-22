package apps.basilisk.monopayprototype.utils;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private static final String TAG = "Validator";

    public final static Pattern PHONE_UA = Pattern.compile("^380\\(\\d{2}\\)(\\d{3}-\\d{2}-\\d{2})$");
    public final static Pattern PHONE_IT = Pattern.compile("^380\\(94\\)(\\d{3}-\\d{2}-\\d{2})$");
    public final static Pattern PASSWORD = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*?_~\\-£()])(?=\\S+$).{9,}$");

    public static boolean match(Pattern pattern, String input) {
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public static boolean isValidEmail(String input) {
        return !TextUtils.isEmpty(input) && android.util.Patterns.EMAIL_ADDRESS.matcher(input).matches();
    }

    public static boolean isValidPassword(String input) {
        return !TextUtils.isEmpty(input) && match(PASSWORD, input);
    }

    public static boolean isValidPhoneUA(String input) {
        return !TextUtils.isEmpty(input) && match(PHONE_UA, input);
    }
    public static boolean isValidPhoneIT(String input) {
        return !TextUtils.isEmpty(input) && match(PHONE_IT, input);
    }
}
