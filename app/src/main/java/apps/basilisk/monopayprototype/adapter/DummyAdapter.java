package apps.basilisk.monopayprototype.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import apps.basilisk.monopayprototype.databinding.ItemDummyBinding;
import apps.basilisk.monopayprototype.model.entity.DummyItem;

public class DummyAdapter extends RecyclerView.Adapter<DummyAdapter.DummyViewHolder> {
    private List<DummyItem> items;
    private OnClickListener onClickListener;

    public DummyAdapter() {
        items = new ArrayList<>();
    }

    @NonNull
    @Override
    public DummyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new DummyViewHolder(
                ItemDummyBinding.inflate(layoutInflater, parent, false)
        );
    }

    public List<DummyItem> getItems() {
        return items;
    }

    public void setItems(List<DummyItem> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull DummyViewHolder holder, int position) {
        if (position == RecyclerView.NO_POSITION) return;
        holder.bind(items.get(position), onClickListener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class DummyViewHolder extends RecyclerView.ViewHolder {
        ItemDummyBinding binding;

        public DummyViewHolder(@NonNull ItemDummyBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(DummyItem item, final OnClickListener onClickListener) {
            binding.setDummy(item);
            binding.setClick(onClickListener);
            binding.ivDummy.setColorFilter(item.getColorForeground());
            binding.cvDummy.setBackgroundColor(item.getColorBackground());
            binding.executePendingBindings();
        }

    }

    public interface OnClickListener{
        void onItemClickListener(View view, DummyItem item);
    }
}
