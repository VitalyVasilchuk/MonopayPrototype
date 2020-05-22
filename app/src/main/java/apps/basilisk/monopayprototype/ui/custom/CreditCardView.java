package apps.basilisk.monopayprototype.ui.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Arrays;
import java.util.List;

import apps.basilisk.monopayprototype.R;
import apps.basilisk.monopayprototype.databinding.CreditCardViewBinding;
import apps.basilisk.monopayprototype.utils.CardUtils;
import apps.basilisk.monopayprototype.utils.FieldUtils;

public class CreditCardView extends ConstraintLayout implements CreditCardViewModel.OnEvent {
    CreditCardViewBinding binding;
    private CreditCardViewModel model;

    public CreditCardView(Context context) {
        super(context);
        init(context);
    }

    public CreditCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CreditCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        model = new CreditCardViewModel(this);

        binding = CreditCardViewBinding.inflate(inflater, this, true);
        binding.etNumber.addTextChangedListener(new CardUtils.CardNumberFieldWatcher(binding.ivCardIcon));
        binding.setModel(model);

        List<String> itemsMm = Arrays.asList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12");
        ArrayAdapter adapterMm = new ArrayAdapter<>(context, R.layout.item_dropdown, itemsMm);
        binding.etMm.setAdapter(adapterMm);

        List<String> itemsYy = Arrays.asList("20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30");
        ArrayAdapter adapterYy = new ArrayAdapter<>(context, R.layout.item_dropdown, itemsYy);
        binding.etYy.setAdapter(adapterYy);
    }

    public boolean isValid(){
        return model.checkCardParameters();
    }

    @Override
    public void onEvent(CreditCardViewModel.Event event) {
        switch (event) {
            case ErrorCardNumber:
                FieldUtils.showError(binding.tilNumber, binding.etNumber, getContext().getString(R.string.payment_err_invalid_card_number));
                break;
            case ErrorCardMm:
                FieldUtils.showError(binding.tilMm, binding.etMm, getContext().getString(R.string.payment_err_invalid_mm));
                break;
            case ErrorCardYy:
                FieldUtils.showError(binding.tilYy, binding.etYy, getContext().getString(R.string.payment_err_invalid_yy));
                break;
            case ErrorCardCvv:
                FieldUtils.showError(binding.tilCvv, binding.etCvv, getContext().getString(R.string.payment_err_invalid_cvv));
                break;
        }
    }
}
