package apps.basilisk.monopayprototype.ui.fragment.faq;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import apps.basilisk.monopayprototype.R;
import apps.basilisk.monopayprototype.adapter.FaqAdapter;
import apps.basilisk.monopayprototype.databinding.FragmentFaqBinding;

public class FaqFragment extends Fragment {

    private FaqViewModel viewModel;
    private FragmentFaqBinding binding;
    private FaqAdapter adapter;

    public static FaqFragment newInstance() {
        return new FaqFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentFaqBinding.inflate(inflater, container, false);
        binding.ivClose.setOnClickListener(view -> getParentFragmentManager().popBackStack());

        DividerItemDecoration itemDecorator = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.divider));

        adapter = new FaqAdapter();

        binding.rvFaq.setHasFixedSize(true);
        binding.rvFaq.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvFaq.setAdapter(adapter);
        binding.rvFaq.addItemDecoration(itemDecorator);

        viewModel = new ViewModelProvider(this).get(FaqViewModel.class);
        viewModel.getFaqItems().observe(getViewLifecycleOwner(), adapter::setFaqItems);
        viewModel.getProgress().observe(getViewLifecycleOwner(), isLoading -> {
            binding.progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
        });

        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                adapter.getFilter().filter(editable);
            }
        });
        return binding.getRoot();
    }

}
