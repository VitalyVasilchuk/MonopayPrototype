package apps.basilisk.monopayprototype.ui.fragment.payment;

import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import apps.basilisk.monopayprototype.BR;
import apps.basilisk.monopayprototype.Demo;

public class PaymentViewModel extends BaseObservable {
    private String senderName;
    private String senderPhone;
    private String recipientName;
    private String recipientCode;
    private String recipientIban;
    private String recipientBank;
    private String paymentPurpose;
    private String paymentAmount;
    private boolean stateLoading;


    @Bindable
    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
        notifyPropertyChanged(BR.senderName);
    }

    @Bindable
    public String getSenderPhone() {
        return senderPhone;
    }

    public void setSenderPhone(String senderPhone) {
        this.senderPhone = senderPhone;
        notifyPropertyChanged(BR.senderPhone);
    }

    @Bindable
    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
        notifyPropertyChanged(BR.recipientName);
    }

    @Bindable
    public String getRecipientCode() {
        return recipientCode;
    }

    public void setRecipientCode(String recipientCode) {
        this.recipientCode = recipientCode;
        notifyPropertyChanged(BR.recipientCode);
    }

    @Bindable
    public String getRecipientIban() {
        return recipientIban;
    }

    public void setRecipientIban(String recipientIban) {
        this.recipientIban = recipientIban;
        notifyPropertyChanged(BR.recipientIban);
    }

    @Bindable
    public String getRecipientBank() {
        return recipientBank;
    }

    public void setRecipientBank(String recipientBank) {
        this.recipientBank = recipientBank;
        notifyPropertyChanged(BR.recipientBank);
    }

    @Bindable
    public String getPaymentPurpose() {
        return paymentPurpose;
    }

    public void setPaymentPurpose(String paymentPurpose) {
        this.paymentPurpose = paymentPurpose;
        notifyPropertyChanged(BR.paymentPurpose);
    }

    @Bindable
    public String getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(String paymentAmount) {
        this.paymentAmount = paymentAmount;
        notifyPropertyChanged(BR.paymentAmount);
    }

    @Bindable
    public boolean isStateLoading() {
        return stateLoading;
    }

    public void setStateLoading(boolean stateLoading) {
        this.stateLoading = stateLoading;
        notifyPropertyChanged(androidx.databinding.library.baseAdapters.BR.stateLoading);
    }

    public void clickSubmit(View view) {
        if (checkInputParameters()) {
            setStateLoading(true);
            Demo.simulateLoading(
                    () -> {
                        setStateLoading(false);
                    },
                    true);
        }

    }

    private boolean checkInputParameters() {
        return true;
    }
}
