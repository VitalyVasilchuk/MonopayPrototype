package apps.basilisk.monopayprototype.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import apps.basilisk.monopayprototype.databinding.ItemServiceBinding;
import apps.basilisk.monopayprototype.model.entity.ServiceItem;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder> {
    private List<ServiceItem> services;
    private OnClickListener onClickListener;

    public ServiceAdapter() {
        services = new ArrayList<>();
    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new ServiceViewHolder(
                ItemServiceBinding.inflate(layoutInflater, parent, false)
        );
    }

    public List<ServiceItem> getServices() {
        return services;
    }

    public void setServices(List<ServiceItem> services) {
        this.services.clear();
        this.services.addAll(services);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder holder, int position) {
        if (position == RecyclerView.NO_POSITION) return;
        holder.bind(services.get(position), onClickListener);
    }

    @Override
    public int getItemCount() {
        return services.size();
    }

    static class ServiceViewHolder extends RecyclerView.ViewHolder {
        ItemServiceBinding binding;

        public ServiceViewHolder(@NonNull ItemServiceBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(ServiceItem serviceItem, final OnClickListener onClickListener) {
            binding.setService(serviceItem);
            binding.setClick(onClickListener);
            binding.executePendingBindings();
        }
    }

    public interface OnClickListener {
        void onItemClickListener(View view, ServiceItem serviceItem);
    }
}
