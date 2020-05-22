package apps.basilisk.monopayprototype.ui.fragment.payment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import apps.basilisk.monopayprototype.databinding.FragmentPaymentBinding;
import apps.basilisk.monopayprototype.model.entity.ServiceItem;

public class PaymentFragment extends Fragment {
    private static final String TAG = "PaymentFragment";
    private static final String ARG_SERVICE_ITEM = "ARG_SERVICE_ITEM";

    private PaymentViewModel viewModel;
    private FragmentPaymentBinding binding;
    private ServiceItem serviceItem;

    public static PaymentFragment newInstance(ServiceItem serviceItem) {
        PaymentFragment fragment = new PaymentFragment();
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
        binding = FragmentPaymentBinding.inflate(inflater, container, false);
        binding.setService(serviceItem);
        binding.setModel(new PaymentViewModel());
        binding.ivClose.setOnClickListener(view -> getParentFragmentManager().popBackStack());
        return binding.getRoot();
    }

}
