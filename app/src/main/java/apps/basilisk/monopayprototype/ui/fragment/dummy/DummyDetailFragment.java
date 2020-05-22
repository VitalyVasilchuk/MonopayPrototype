package apps.basilisk.monopayprototype.ui.fragment.dummy;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import apps.basilisk.monopayprototype.databinding.FragmentDummyDetailBinding;
import apps.basilisk.monopayprototype.model.entity.DummyItem;
import apps.basilisk.monopayprototype.utils.FieldUtils;

public class DummyDetailFragment extends Fragment {
    private static final String TAG = "DummyDetailFragment";
    private static final String ARG_ITEM = "ARG_ITEM";

    private DummyDetailViewModel viewModel;
    private FragmentDummyDetailBinding binding;
    private DummyItem item;

    public static DummyDetailFragment newInstance(DummyItem item) {
        Log.d(TAG, "newInstance: " + item.toString());
        DummyDetailFragment fragment = new DummyDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_ITEM, item);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            item = (DummyItem) args.getSerializable(ARG_ITEM);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDummyDetailBinding.inflate(inflater, container, false);

        binding.setModel(new DummyDetailViewModel(item.getName()));
        binding.setItem(item);

        initUI();

        return binding.getRoot();
    }

    private void initUI() {
        binding.ivBack.setOnClickListener(v -> getParentFragmentManager().popBackStack());
        binding.ivBack.setColorFilter(item.getColorForeground());
        binding.cvHeader.setBackgroundColor(item.getColorBackground());
        binding.tvName.setTextColor(item.getColorForeground());
        binding.ivIcon.setImageResource(item.getIcon());
        binding.ivIcon.setColorFilter(item.getColorForeground());
        FieldUtils.setupFieldPhoneIT(binding.etNumberIt);
    }

}
