package apps.basilisk.monopayprototype.utils;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;

public class SpannableUtils {
    public static SpannableString getSpannableSelectedPart(String source, String part) {
        SpannableString result = new SpannableString(source);
        source = source.toLowerCase();
        part = part.toLowerCase();
        int lastIdx = source.lastIndexOf(part);
        int start = -1;
        int end = 0;

        while (lastIdx != start) {
            start = source.indexOf(part, start + 1);
            end = start + part.length();

            if (start > -1 && end > start) {
                result.setSpan(new ForegroundColorSpan(Color.WHITE), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                result.setSpan(new BackgroundColorSpan(Color.BLACK), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }

        return result;
    }
}
