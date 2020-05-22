package apps.basilisk.monopayprototype.ui.fragment.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;

import apps.basilisk.monopayprototype.Demo;
import apps.basilisk.monopayprototype.R;
import apps.basilisk.monopayprototype.adapter.ServiceAdapter;
import apps.basilisk.monopayprototype.databinding.FragmentHomeBinding;
import apps.basilisk.monopayprototype.model.Repository;
import apps.basilisk.monopayprototype.model.RepositoryImpl;
import apps.basilisk.monopayprototype.model.entity.ServiceItem;
import apps.basilisk.monopayprototype.ui.ServiceActivity;
import apps.basilisk.monopayprototype.ui.fragment.payment.PaymentFragment;
import apps.basilisk.monopayprototype.ui.fragment.card.CardFragment;
import apps.basilisk.monopayprototype.ui.fragment.dummy.DummyFragment;
import apps.basilisk.monopayprototype.ui.fragment.itit.ItitFragment;

public class HomeFragment extends Fragment implements ServiceAdapter.OnClickListener {
    private static final String TAG = "HomeFragment";
    private static final int SPAN_COUNT = 1;

    private HomeViewModel viewModel;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentHomeBinding binding = FragmentHomeBinding.inflate(inflater, container, false);

        ServiceAdapter serviceAdapter = new ServiceAdapter();
        serviceAdapter.setOnClickListener(this);

        DividerItemDecoration itemDecorator = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.divider));

        binding.rvService.setLayoutManager(new GridLayoutManager(getContext(), SPAN_COUNT));
        binding.rvService.setHasFixedSize(true);
        binding.rvService.setAdapter(serviceAdapter);
        binding.rvService.addItemDecoration(itemDecorator);

        Repository repository = new RepositoryImpl();
        serviceAdapter.setServices(repository.getServices());

        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        showActionBar();
        return binding.getRoot();
    }

    private void showActionBar() {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            ActionBar actionBar = activity.getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
        }
    }

    @Override
    public void onItemClickListener(View view, ServiceItem serviceItem) {
        ServiceActivity activity = (ServiceActivity) getActivity();
        if (activity != null) {
            if (Demo.EService.MoneyTransfer.getTitle().equals(serviceItem.getTitle())) {
                activity.switchFragment(CardFragment.newInstance(serviceItem), true, true);
            } else if (Demo.EService.Itit.getTitle().equals(serviceItem.getTitle())) {
                activity.switchFragment(ItitFragment.newInstance(serviceItem), true, true);
            } else if (Demo.EService.Payment.getTitle().equals(serviceItem.getTitle())) {
                activity.switchFragment(PaymentFragment.newInstance(serviceItem), true, true);
            } else {
                activity.switchFragment(DummyFragment.newInstance(serviceItem), true, true);
            }
        }
    }
}
