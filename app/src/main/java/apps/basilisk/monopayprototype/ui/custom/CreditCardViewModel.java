package apps.basilisk.monopayprototype.ui.custom;

import android.text.TextUtils;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import apps.basilisk.monopayprototype.BR;
import apps.basilisk.monopayprototype.utils.CardUtils;

public class CreditCardViewModel extends BaseObservable {
    private static final String TAG = "CreditCardViewModel";
    private static final long ERROR_DELAY = 3000;
    private static final float AMOUNT_LIMIT_MIN = 1;
    private static final float AMOUNT_LIMIT_MAX = 25000;

    private String cardNumber;
    private String cardExpiryDate;
    private String cardMm;
    private String cardYy;
    private String cardCvv;

    private int cvvLength;

    private OnEvent eventListener;

    public interface OnEvent {
        void onEvent(Event event);
    }

    public enum Event {
        ErrorCardNumber,
        ErrorCardMm,
        ErrorCardYy,
        ErrorCardCvv
    }

    public CreditCardViewModel(OnEvent eventListener) {
        this.eventListener = eventListener;
        cvvLength = 3;
        setDemoValue();
    }

    private void setDemoValue() {
        cardNumber = "4444555566661111";
//        cardMm = "12";
//        cardYy = "24";
        cardCvv = "123";
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
    public String getCardYy() {
        return cardYy;
    }

    public void setCardYy(String cardYy) {
        this.cardYy = cardYy;
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

    public boolean checkCardParameters() {
        if (TextUtils.isEmpty(cardNumber) || !CardUtils.isValidCardNumber(cardNumber.replace(" ", ""))) {
            if (eventListener != null) eventListener.onEvent(Event.ErrorCardNumber);
        } else if (TextUtils.isEmpty(cardMm) || !CardUtils.isValidExpireMonth(Integer.parseInt(cardMm))) {
            if (eventListener != null) eventListener.onEvent(Event.ErrorCardMm);
        } else if (TextUtils.isEmpty(cardYy) || !CardUtils.isValidExpireYear(Integer.parseInt(cardYy))) {
            if (eventListener != null) eventListener.onEvent(Event.ErrorCardYy);
        } else if (TextUtils.isEmpty(cardCvv) || !CardUtils.isValidCvv(cardNumber.replace(" ", ""), cardCvv)) {
            if (eventListener != null) eventListener.onEvent(Event.ErrorCardCvv);
        } else return true;

        return false;
    }
}

