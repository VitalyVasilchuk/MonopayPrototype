package apps.basilisk.monopayprototype.ui.fragment.dummy;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import apps.basilisk.monopayprototype.adapter.DummyAdapter;
import apps.basilisk.monopayprototype.databinding.FragmentDummyBinding;
import apps.basilisk.monopayprototype.model.entity.DummyItem;
import apps.basilisk.monopayprototype.model.entity.ServiceItem;
import apps.basilisk.monopayprototype.ui.ServiceActivity;

public class DummyFragment extends Fragment implements DummyAdapter.OnClickListener {
    private static final String TAG = "DummyFragment";
    private static final String ARG_SERVICE_ITEM = "ARG_SERVICE_ITEM";
    private static final int SPAN_COUNT = 2;
    private DummyViewModel viewModel;
    private ServiceItem serviceItem;

    public static DummyFragment newInstance(ServiceItem serviceItem) {
        Log.d(TAG, "newInstance: " + serviceItem.toString());
        DummyFragment fragment = new DummyFragment();
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
        FragmentDummyBinding binding = FragmentDummyBinding.inflate(inflater, container, false);
        binding.setService(serviceItem);

        DummyAdapter dummyAdapter = new DummyAdapter();
        dummyAdapter.setOnClickListener(this);
        binding.rvDummy.setLayoutManager(new GridLayoutManager(getContext(), SPAN_COUNT));
        binding.rvDummy.setAdapter(dummyAdapter);

        viewModel = new ViewModelProvider(this).get(DummyViewModel.class);
        viewModel.setTemplateTitle(serviceItem.getItemName());
        viewModel.setTemplateResId(serviceItem.getImageResId());
        viewModel.setTemplateIdentifier(serviceItem.getItemIdentifier());
        viewModel.getData().observe(getViewLifecycleOwner(), dummyAdapter::setItems);
        viewModel.getProgressState().observe(getViewLifecycleOwner(), isLoading -> {
            binding.progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
        });

        binding.ivClose.setOnClickListener(view -> getParentFragmentManager().popBackStack());

        return binding.getRoot();
    }

    @Override
    public void onItemClickListener(View view, DummyItem item) {
        //Toast.makeText(getContext(), item.getName, Toast.LENGTH_SHORT).show();
        ServiceActivity activity = (ServiceActivity) getActivity();
        if (activity != null) {
            activity.switchFragment(DummyDetailFragment.newInstance(item), true, true);
        }
    }
}
