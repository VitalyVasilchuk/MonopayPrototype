package apps.basilisk.monopayprototype.utils;

import android.content.Context;
import android.os.Handler;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

import apps.basilisk.monopayprototype.R;
import ru.tinkoff.decoro.MaskImpl;
import ru.tinkoff.decoro.parser.UnderscoreDigitSlotsParser;
import ru.tinkoff.decoro.slots.Slot;
import ru.tinkoff.decoro.watchers.FormatWatcher;
import ru.tinkoff.decoro.watchers.MaskFormatWatcher;

public class FieldUtils {
    private static final long ERROR_DELAY = 3000;

    public static void showError(TextInputLayout textInputLayout, EditText editText, String message) {
        Context context = editText.getContext();
        textInputLayout.setErrorEnabled(true);
        textInputLayout.setError(message);
        textInputLayout.requestFocus();
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
        }

        new Handler().postDelayed(()-> {
            textInputLayout.setError(null);
            textInputLayout.setErrorEnabled(false);
        }, ERROR_DELAY);
    }

    public static void setupFieldPhoneIT(EditText editText) {
        Context context = editText.getContext();
        Slot[] slots = new UnderscoreDigitSlotsParser().parseSlots(context.getString(R.string.phone_mask_it));
        FormatWatcher formatWatcher = new MaskFormatWatcher(MaskImpl.createTerminated(slots));
        formatWatcher.installOn(editText);
        editText.setText("");
    }

    public static void setupFieldPhoneUA(EditText editText) {
        Context context = editText.getContext();
        Slot[] slots = new UnderscoreDigitSlotsParser().parseSlots(context.getString(R.string.phone_mask_ua));
        FormatWatcher formatWatcher = new MaskFormatWatcher(MaskImpl.createTerminated(slots));
        formatWatcher.installOn(editText);
        editText.setText("");
    }

}
