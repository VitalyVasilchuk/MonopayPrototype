package apps.basilisk.monopayprototype.ui.fragment.itit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import apps.basilisk.monopayprototype.R;
import apps.basilisk.monopayprototype.databinding.FragmentItitBinding;
import apps.basilisk.monopayprototype.model.entity.ServiceItem;
import apps.basilisk.monopayprototype.utils.FieldUtils;

public class ItitFragment extends Fragment implements ItitViewModel.OnEvent {
    private static final String TAG = "ItitFragment";

    private static final String ARG_SERVICE_ITEM = "ARG_SERVICE_ITEM";
    private static final long ERROR_DELAY = 3000;

    private ItitViewModel viewModel;
    private FragmentItitBinding binding;
    private ServiceItem serviceItem;

    public static ItitFragment newInstance(ServiceItem serviceItem) {
        ItitFragment fragment = new ItitFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_SERVICE_ITEM, serviceItem);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            serviceItem = (ServiceItem) args.getSerializable(ARG_SERVICE_ITEM);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentItitBinding.inflate(inflater, container, false);
        binding.setService(serviceItem);
        binding.setModel(new ItitViewModel(this));
        binding.ivClose.setOnClickListener(view -> getParentFragmentManager().popBackStack());

        FieldUtils.setupFieldPhoneIT(binding.etSenderNumber);
        FieldUtils.setupFieldPhoneIT(binding.etRecipientNumber);

        return binding.getRoot();
    }

    @Override
    public void onEvent(ItitViewModel.Event event) {
        switch (event) {
            case ErrorSenderNumber:
                FieldUtils.showError(binding.tilSenderNumber, binding.etSenderNumber, "вкажіть номер телефону Intertelecom");
                break;
            case ErrorRecipientNumber:
                FieldUtils.showError(binding.tilRecipientNumber, binding.etRecipientNumber, "вкажіть номер телефону Intertelecom");
                break;
            case ErrorAmount:
                FieldUtils.showError(binding.tilAmount, binding.etAmount, getString(R.string.err_incorrect_value));
                break;
        }
    }

}
