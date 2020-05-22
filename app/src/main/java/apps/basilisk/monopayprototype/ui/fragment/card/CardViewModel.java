package apps.basilisk.monopayprototype.ui.fragment.card;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import apps.basilisk.monopayprototype.BR;
import apps.basilisk.monopayprototype.Demo;
import apps.basilisk.monopayprototype.R;
import apps.basilisk.monopayprototype.utils.CardUtils;

public class CardViewModel extends BaseObservable {
    private static final String TAG = "CardViewModel";
    private static final long ERROR_DELAY = 3000;
    private static final float AMOUNT_LIMIT_MIN = 1;
    private static final float AMOUNT_LIMIT_MAX = 25000;

    private String message;
    private OnEvent event;

    private String cardNumber = "4444555566661111";
    private String cardExpiryDate;
    private String cardMm;
    private String cardYY;
    private String cardCvv;

    private String cardNumberRecipient = "5555666644441111";
    private String amount;
    private float amountValue;

    private boolean errorCardNumber;
    private boolean errorCardMm;
    private boolean errorCardYY;
    private boolean errorCardCvv;
    private boolean errorCardNumberRecipient;
    private boolean errorAmount;

    private float commission;
    private float total;

    private int cvvLength;
    private boolean stateLoading;
    private String resultOperation;

    public interface OnEvent {
        void onEvent(Event event);
    }

    public enum Event {
        ErrorCardNumber
    }

    public CardViewModel(String message, OnEvent onEvent) {
        this.message = message;
        this.event = onEvent;
        cvvLength = 3;
        commission = 0;
        total = 0;
    }

    @Bindable
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
        notifyPropertyChanged(BR.message);
    }

    @Bindable
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        notifyPropertyChanged(BR.cardNumber);

        cvvLength = (!TextUtils.isEmpty(cardNumber) && cardNumber.length() > 19) ? 4 : 3;
        notifyPropertyChanged(BR.cvvLength);
    }

    @Bindable
    public String getCardExpiryDate() {
        return cardExpiryDate;
    }

    public void setCardExpiryDate(String cardExpiryDate) {
        this.cardExpiryDate = cardExpiryDate;
        notifyPropertyChanged(BR.cardExpiryDate);
    }

    @Bindable
    public String getCardMm() {
        return cardMm;
    }

    public void setCardMm(String cardMm) {
        this.cardMm = cardMm;
        notifyPropertyChanged(BR.cardMm);
    }

    @Bindable
    public String getCardYY() {
        return cardYY;
    }

    public void setCardYY(String cardYY) {
        this.cardYY = cardYY;
        notifyPropertyChanged(BR.cardYY);
    }

    @Bindable
    public String getCardCvv() {
        return cardCvv;
    }

    public void setCardCvv(String cardCvv) {
        this.cardCvv = cardCvv;
        notifyPropertyChanged(BR.cardCvv);
    }

    @Bindable
    public int getCvvLength() {
        return cvvLength;
    }

    public void setCvvLength(int cvvLength) {
        this.cvvLength = cvvLength;
        notifyPropertyChanged(BR.cvvLength);
    }

    @Bindable
    public String getCardNumberRecipient() {
        return cardNumberRecipient;
    }

    public void setCardNumberRecipient(String cardNumberRecipient) {
        this.cardNumberRecipient = cardNumberRecipient;
        notifyPropertyChanged(BR.cardNumberRecipient);
    }

    @Bindable
    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
        try {
            amountValue = Float.parseFloat(amount);
            setCommission(amountValue * 0.015f + 2.5f);
            setTotal(amountValue + commission);
        } catch (NumberFormatException e) {
            setCommission(0);
            setTotal(0);
        }
        notifyPropertyChanged(BR.amount);
    }

    @Bindable
    public String getResultOperation() {
        return resultOperation;
    }

    public void setResultOperation(String resultOperation) {
        this.resultOperation = resultOperation;
        notifyPropertyChanged(BR.resultOperation);
    }

    @Bindable
    public boolean isErrorCardNumber() {
        return errorCardNumber;
    }

    public void setErrorCardNumber(boolean errorCardNumber) {
        this.errorCardNumber = errorCardNumber;
        notifyPropertyChanged(BR.errorCardNumber);
    }

    @Bindable
    public boolean isErrorCardMm() {
        return errorCardMm;
    }

    public void setErrorCardMm(boolean errorCardMm) {
        this.errorCardMm = errorCardMm;
        notifyPropertyChanged(BR.errorCardMm);
    }

    @Bindable
    public boolean isErrorCardYY() {
        return errorCardYY;
    }

    public void setErrorCardYY(boolean errorCardYY) {
        this.errorCardYY = errorCardYY;
        notifyPropertyChanged(BR.errorCardYY);
    }

    @Bindable
    public boolean isErrorCardCvv() {
        return errorCardCvv;
    }

    public void setErrorCardCvv(boolean errorCardCvv) {
        this.errorCardCvv = errorCardCvv;
        notifyPropertyChanged(BR.errorCardCvv);
    }

    @Bindable
    public boolean isErrorCardNumberRecipient() {
        return errorCardNumberRecipient;
    }

    @Bindable
    public boolean isErrorAmount() {
        return errorAmount;
    }

    public void setErrorAmount(boolean errorAmount) {
        this.errorAmount = errorAmount;
        notifyPropertyChanged(BR.errorAmount);
    }

    @Bindable
    public float getCommission() {
        return commission;
    }

    public void setCommission(float commission) {
        this.commission = commission;
        notifyPropertyChanged(BR.commission);
    }

    @Bindable
    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
        notifyPropertyChanged(BR.total);
    }

    @Bindable
    public boolean isStateLoading() {
        return stateLoading;
    }

    public void setStateLoading(boolean stateLoading) {
        this.stateLoading = stateLoading;
        notifyPropertyChanged(BR.stateLoading);
    }

    public void setErrorCardNumberRecipient(boolean errorCardNumberRecipient) {
        this.errorCardNumberRecipient = errorCardNumberRecipient;
        notifyPropertyChanged(BR.errorCardNumberRecipient);
    }

    public void clickButtonPaymentCard(Context context) {
        if (checkCardParameters() && checkRecipientCardParameters() && checkAmount()) {
            String message = (String.format("%s (%s)\n%s/%s @ %s\n---\n%s\n%s", cardNumber,
                    CardUtils.getType(cardNumber.replace(" ", "")), cardMm, cardYY, cardCvv, cardNumberRecipient, total));

            setStateLoading(true);
            Demo.simulateLoading(
                    () -> {
                        setStateLoading(false);
                        setAmount("");
                        setCardCvv("");
                        setResultOperation(context.getString(R.string.operation_completed_successfully));
                    },
                    true);
        }
    }

    private boolean checkCardParameters() {
        setErrorCardNumber(false);
        setErrorCardMm(false);
        setErrorCardYY(false);
        setErrorCardCvv(false);

        if (TextUtils.isEmpty(cardNumber) || !CardUtils.isValidCardNumber(cardNumber.replace(" ", ""))) {
            setErrorCardNumber(true);
            new Handler().postDelayed(() -> setErrorCardNumber(false), ERROR_DELAY);
        } else if (TextUtils.isEmpty(cardMm) || !CardUtils.isValidExpireMonth(Integer.parseInt(cardMm))) {
            setErrorCardMm(true);
            new Handler().postDelayed(() -> setErrorCardMm(false), ERROR_DELAY);
        } else if (TextUtils.isEmpty(cardYY) || !CardUtils.isValidExpireYear(Integer.parseInt(cardYY))) {
            setErrorCardYY(true);
            new Handler().postDelayed(() -> setErrorCardYY(false), ERROR_DELAY);
        } else if (TextUtils.isEmpty(cardNumber) || TextUtils.isEmpty(cardCvv) || !CardUtils.isValidCvv(cardNumber.replace(" ", ""), cardCvv)) {
            setErrorCardCvv(true);
            new Handler().postDelayed(() -> setErrorCardCvv(false), ERROR_DELAY);
        } else return true;

        return false;
    }

    private boolean checkRecipientCardParameters() {
        setErrorCardNumberRecipient(false);

        if (TextUtils.isEmpty(cardNumberRecipient) || !CardUtils.isValidCardNumber(cardNumberRecipient.replace(" ", ""))) {
            setErrorCardNumberRecipient(true);
            new Handler().postDelayed(() -> setErrorCardNumberRecipient(false), ERROR_DELAY);
        } else return true;

        return false;
    }

    private boolean checkAmount() {
        setErrorAmount(false);
        if (total < AMOUNT_LIMIT_MIN || amountValue > AMOUNT_LIMIT_MAX) {
            setErrorAmount(true);
            new Handler().postDelayed(() -> setErrorAmount(false), ERROR_DELAY);
        } else {
            return true;
        }

        return false;
    }
}

