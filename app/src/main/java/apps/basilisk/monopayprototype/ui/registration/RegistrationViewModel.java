package apps.basilisk.monopayprototype.ui.registration;

import android.content.Context;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import apps.basilisk.monopayprototype.BR;
import apps.basilisk.monopayprototype.Demo;
import apps.basilisk.monopayprototype.utils.Validator;

public class RegistrationViewModel extends BaseObservable {
    private String email;
    private String phone;
    private String passwordFirst;
    private String passwordSecond;
    private boolean stateLoading;

    public enum Event {
        ErrorEmail,
        ErrorPhone,
        ErrorPasswordFirst,
        ErrorPasswordMismatch,
        ErrorPasswordSecond
    }
    public interface OnEvent {
        void onEvent(Event event);
    }

    private OnEvent eventListener;

    public RegistrationViewModel(OnEvent eventListener) {
        this.eventListener = eventListener;
    }

    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }

    @Bindable
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
        notifyPropertyChanged(BR.phone);
    }

    @Bindable
    public String getPasswordFirst() {
        return passwordFirst;
    }

    public void setPasswordFirst(String passwordFirst) {
        this.passwordFirst = passwordFirst;
        notifyPropertyChanged(BR.passwordFirst);
    }

    @Bindable
    public String getPasswordSecond() {
        return passwordSecond;
    }

    public void setPasswordSecond(String passwordSecond) {
        this.passwordSecond = passwordSecond;
        notifyPropertyChanged(BR.passwordSecond);
    }

    @Bindable
    public boolean isStateLoading() {
        return stateLoading;
    }

    public void setStateLoading(boolean stateLoading) {
        this.stateLoading = stateLoading;
        notifyPropertyChanged(BR.stateLoading);
    }

    public void clickSubmit(Context context) {
        if (checkInputParameters()) {
            setStateLoading(true);
            Demo.simulateLoading(
                    () -> {
                        setStateLoading(false);
                        setPasswordFirst("");
                        setPasswordSecond("");
//                        setResultOperation(context.getString(R.string.operation_completed_successfully));
                    },
                    true);
        }
    }

    private boolean checkInputParameters() {
        if (!Validator.isValidEmail(email)) {
            if (eventListener != null) eventListener.onEvent(Event.ErrorEmail);
        } else if (!Validator.isValidPhoneUA(phone)) {
            if (eventListener != null) eventListener.onEvent(Event.ErrorPhone);
        } else if (!Validator.isValidPassword(passwordFirst)) {
            if (eventListener != null) eventListener.onEvent(Event.ErrorPasswordFirst);
        } else if (!Validator.isValidPassword(passwordSecond)) {
            if (eventListener != null) eventListener.onEvent(Event.ErrorPasswordSecond);
        } else if (!passwordFirst.equals(passwordSecond)) {
            if (eventListener != null) eventListener.onEvent(Event.ErrorPasswordMismatch);
        } else {
            return true;
        }
        return false;
    }
}
