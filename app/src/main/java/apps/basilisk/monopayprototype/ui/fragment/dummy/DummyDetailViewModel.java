package apps.basilisk.monopayprototype.ui.fragment.dummy;

import android.content.Context;
import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import apps.basilisk.monopayprototype.BR;
import apps.basilisk.monopayprototype.Demo;
import apps.basilisk.monopayprototype.R;
import apps.basilisk.monopayprototype.model.entity.PaymentDetails;

public class DummyDetailViewModel extends BaseObservable {
    private String nameDummy;
    private String identifier;
    private String amount;
    private float amountValue;
    private boolean stateLoading;
    private EPaymentStage paymentStage;
    private String resultOperation;
    private PaymentDetails details;

    public enum EPaymentStage {
        Prepare,
        Check,
        Done
    }

    public DummyDetailViewModel(String name) {
        this.nameDummy = name;
        this.details = new PaymentDetails();
        paymentStage = EPaymentStage.Prepare;
    }

    @Bindable
    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
        notifyPropertyChanged(BR.identifier);
    }

    @Bindable
    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
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
    public EPaymentStage getPaymentStage() {
        return paymentStage;
    }

    public void setPaymentStage(EPaymentStage paymentStage) {
        this.paymentStage = paymentStage;
        notifyPropertyChanged(BR.paymentStage);
    }

    @Bindable
    public String getResultOperation() {
        return resultOperation;
    }

    @Bindable
    public PaymentDetails getDetails() {
        return details;
    }

    public void setDetails(PaymentDetails details) {
        this.details = details;
        notifyPropertyChanged(BR.details);
    }

    public void setResultOperation(String resultOperation) {
        this.resultOperation = resultOperation;
        notifyPropertyChanged(BR.resultOperation);
    }

    public void clickSubmit(View view) {
        if (checkInputParameters()) {
            setStateLoading(true);
            Demo.simulateLoading(
                    () -> {
                        setStateLoading(false);
                        details.setNumber(String.valueOf(System.currentTimeMillis()));
                        details.setRecipient(nameDummy);
                        details.setPurpose(identifier);
                        details.setCommission("0.00 грн");
                        details.setAmountCommissionFree(amount + " грн");
                        details.setAmount(amount + " грн");
                        setDetails(details);
                        setPaymentStage(EPaymentStage.Check);
                    },
                    true);
        }
    }

    public void clickBack(Context context) {
        setPaymentStage(EPaymentStage.Prepare);
    }

    public void clickPay(Context context) {
        setStateLoading(true);
        Demo.simulateLoading(()->{
            setStateLoading(false);
            setResultOperation(context.getString(R.string.operation_completed_successfully));
            setPaymentStage(EPaymentStage.Done);
        },true);
    }

    private boolean checkInputParameters() {
        return true;
    }
}