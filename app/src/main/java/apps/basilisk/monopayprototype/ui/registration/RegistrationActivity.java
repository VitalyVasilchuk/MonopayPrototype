package apps.basilisk.monopayprototype.ui.registration;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import java.util.Objects;

import apps.basilisk.monopayprototype.R;
import apps.basilisk.monopayprototype.databinding.ActivityRegistrationBinding;
import apps.basilisk.monopayprototype.utils.FieldUtils;

public class RegistrationActivity extends AppCompatActivity implements RegistrationViewModel.OnEvent {
    private ActivityRegistrationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        RegistrationViewModel viewModel = new RegistrationViewModel(this);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_registration);
        binding.setModel(viewModel);

        FieldUtils.setupFieldPhoneUA(binding.etPhone);
    }

    @Override
    public void onEvent(RegistrationViewModel.Event event) {
        switch (event) {
            case ErrorEmail:
                FieldUtils.showError(binding.tilEmail, binding.etEmail, getString(R.string.err_incorrect_value));
                break;
            case ErrorPhone:
                FieldUtils.showError(binding.tilPhone, binding.etPhone, getString(R.string.err_incorrect_value));
                break;
            case ErrorPasswordFirst:
                FieldUtils.showError(binding.tilPassword, binding.etPassword, getString(R.string.err_incorrect_value));
                break;
            case ErrorPasswordSecond:
                FieldUtils.showError(binding.tilPasswordConfirm, binding.etPasswordConfirm, getString(R.string.err_incorrect_value));
                break;
            case ErrorPasswordMismatch:
                FieldUtils.showError(binding.tilPasswordConfirm, binding.etPasswordConfirm, getString(R.string.err_password_mismatch));
                break;
        }
    }
}
