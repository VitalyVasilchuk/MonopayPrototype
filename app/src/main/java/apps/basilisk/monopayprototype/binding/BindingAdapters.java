package apps.basilisk.monopayprototype.binding;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import apps.basilisk.monopayprototype.utils.SpannableUtils;

public class BindingAdapters {
    @BindingAdapter("imageResId")
    public static void loadImageRes(ImageView imageView, int imageResId) {
        imageView.setImageResource(imageResId);
    }

    @BindingAdapter({"android:text", "constraint"})
    public static void selectConstraint(TextView textView, String text, String constraint) {
        textView.setText(SpannableUtils.getSpannableSelectedPart(text, constraint));
    }
}
