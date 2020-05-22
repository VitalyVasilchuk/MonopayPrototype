package apps.basilisk.monopayprototype.ui.fragment.card;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import apps.basilisk.monopayprototype.R;
import apps.basilisk.monopayprototype.databinding.FragmentCardBinding;
import apps.basilisk.monopayprototype.model.entity.ServiceItem;
import apps.basilisk.monopayprototype.utils.CardUtils;

public class CardFragment extends Fragment implements CardViewModel.OnEvent {
    private static final String TAG = "CardFragment";
    private static final String ARG_SERVICE_ITEM = "ARG_SERVICE_ITEM";
    private static final long DELAY_MILLIS = 3000;

    private CardViewModel viewModel;
    private FragmentCardBinding binding;
    private ServiceItem serviceItem;

    public static CardFragment newInstance(ServiceItem serviceItem) {
        CardFragment fragment = new CardFragment();
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
        binding = FragmentCardBinding.inflate(inflater, container, false);
        binding.setService(serviceItem);
        binding.setModel(new CardViewModel("Переказ можливий тільки в гривні і тільки між картками Українських банків.", this));
        binding.etCardNumber.addTextChangedListener(new CardUtils.CardNumberFieldWatcher(binding.ivCardIcon));
        binding.etCardNumberRecipient.addTextChangedListener(new CardUtils.CardNumberFieldWatcher(binding.ivCardIconRecipient));
        binding.ivClose.setOnClickListener(view -> getParentFragmentManager().popBackStack());

        return binding.getRoot();
    }

    @Override
    public void onEvent(CardViewModel.Event event) {
        switch (event) {
            case ErrorCardNumber:
                binding.etCardNumber.setBackgroundColor(getResources().getColor(R.color.color_error_bg));
                binding.etCardNumber.postDelayed((Runnable) () -> {
                    binding.etCardNumber.setBackgroundColor(getResources().getColor(R.color.white));
                }, DELAY_MILLIS);
                break;
        }

    }

}
