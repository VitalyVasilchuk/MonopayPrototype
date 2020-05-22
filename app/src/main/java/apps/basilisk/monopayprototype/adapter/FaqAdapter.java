package apps.basilisk.monopayprototype.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import apps.basilisk.monopayprototype.databinding.ItemFaqBinding;
import apps.basilisk.monopayprototype.model.entity.FaqItem;

public class FaqAdapter extends RecyclerView.Adapter<FaqAdapter.FaqViewHolder> implements Filterable {
    private static final String TAG = "FaqAdapter";
    private List<FaqItem> faqItems;
    private List<FaqItem> faqItemsFull;

    public FaqAdapter() {
        faqItems = new ArrayList<>();
        faqItemsFull = new ArrayList<>();
    }

    @NonNull
    @Override
    public FaqViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new FaqViewHolder(ItemFaqBinding.inflate(inflater, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FaqViewHolder holder, int position) {
        if (position != RecyclerView.NO_POSITION) {
            holder.bind(faqItems.get(position), position);
        }
    }

    @Override
    public int getItemCount() {
        return faqItems != null ? faqItems.size() : 0;
    }

    public void setFaqItems(List<FaqItem> faqItems) {
        this.faqItems = faqItems;
        faqItemsFull = new ArrayList<>(faqItems);
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<FaqItem> filteredList = new ArrayList<>();
                if (constraint == null || constraint.length() == 0) {
                    for (FaqItem item : faqItemsFull) {
                        item.setConstraint("");
                        filteredList.add(item);
                    }
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();
                    for (FaqItem item : faqItemsFull) {
                        if (item.getQuestion().toLowerCase().contains(filterPattern) ||
                                item.getAnswer().toLowerCase().contains(filterPattern)) {
                            item.setConstraint(constraint.toString());
                            filteredList.add(item);
                        }
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults filterResults) {
                faqItems.clear();
                faqItems.addAll((List) filterResults.values);
                notifyDataSetChanged();
            }
        };
    }
    class FaqViewHolder extends RecyclerView.ViewHolder {
        private ItemFaqBinding binding;

        public FaqViewHolder(@NonNull ItemFaqBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(FaqItem faqItem, int position) {
            binding.setFaq(faqItem);
            binding.ivChevron.setOnClickListener(view -> {
                faqItem.setExpanded(!faqItem.isExpanded());
                notifyItemChanged(position);
            });
            binding.executePendingBindings();
        }
    }
}
