package apps.basilisk.monopayprototype.ui.fragment.itit;

import android.content.Context;
import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

import apps.basilisk.monopayprototype.Demo;
import apps.basilisk.monopayprototype.R;
import apps.basilisk.monopayprototype.utils.Validator;

public class ItitViewModel extends BaseObservable {
    private static final String TAG = "ItitViewModel";
    private static final float AMOUNT_LIMIT_MIN = 1;
    private static final float AMOUNT_LIMIT_MAX = 14999;

    private String senderNumber;
    private String recipientNumber;
    private String amount;
    private float amountValue;
    private boolean stateLoading;
    private String resultOperation;

    private OnEvent eventListener;

    public enum Event {
        ErrorSenderNumber,
        ErrorRecipientNumber,
        ErrorAmount,
    }

    public interface OnEvent {
        void onEvent(Event event);
    }

    public ItitViewModel(OnEvent eventListener) {
        this.eventListener = eventListener;
    }

    @Bindable
    public String getSenderNumber() {
        return senderNumber;
    }

    public void setSenderNumber(String senderNumber) {
        this.senderNumber = senderNumber;
        notifyPropertyChanged(BR.senderNumber);
    }

    @Bindable
    public String getRecipientNumber() {
        return recipientNumber;
    }

    public void setRecipientNumber(String recipientNumber) {
        this.recipientNumber = recipientNumber;
        notifyPropertyChanged(BR.recipientNumber);
    }

    @Bindable
    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
        try {
            amountValue = Float.parseFloat(amount);
        } catch (NumberFormatException e) {
            amountValue = 0;
        }
        notifyPropertyChanged(BR.amount);
    }

    @Bindable
    public boolean isStateLoading() {
        return stateLoading;
    }

    public void setStateLoading(boolean stateLoading) {
        this.stateLoading = stateLoading;
        notifyPropertyChanged(BR.stateLoading);
    }

    @Bindable
    public String getResultOperation() {
        return resultOperation;
    }

    public void setResultOperation(String resultOperation) {
        this.resultOperation = resultOperation;
        notifyPropertyChanged(BR.resultOperation);
    }

    public void setEventListener(OnEvent eventListener) {
        this.eventListener = eventListener;
    }

    public void clickSubmit(Context context) {
        if (checkInputParameters()) {
            Log.d(TAG, String.format("clickSubmit(): %s\n%s\n%s", senderNumber, recipientNumber, amount));
            setStateLoading(true);
            Demo.simulateLoading(
                    () -> {
                        setStateLoading(false);
                        setAmount("");
                        setResultOperation(context.getString(R.string.operation_completed_successfully));
                    },
                    true);
        }
    }

    private boolean checkInputParameters() {
        if (!Validator.isValidPhoneIT(senderNumber)) {
            if (eventListener != null) eventListener.onEvent(Event.ErrorSenderNumber);
        } else if (!Validator.isValidPhoneIT(recipientNumber)) {
            if (eventListener != null) eventListener.onEvent(Event.ErrorRecipientNumber);
        } else if (amountValue < AMOUNT_LIMIT_MIN || amountValue > AMOUNT_LIMIT_MAX) {
            if (eventListener != null) eventListener.onEvent(Event.ErrorAmount);
        }
        else {
            return true;
        }
        return false;
    }
}
