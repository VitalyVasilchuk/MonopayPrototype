package apps.basilisk.monopayprototype.ui.fragment.faq;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import apps.basilisk.monopayprototype.Demo;
import apps.basilisk.monopayprototype.model.Repository;
import apps.basilisk.monopayprototype.model.RepositoryImpl;
import apps.basilisk.monopayprototype.model.entity.FaqItem;

public class FaqViewModel extends ViewModel {
    private MutableLiveData<List<FaqItem>> faqItems;
    private MutableLiveData<Boolean> showProgress;

    public FaqViewModel() {
        faqItems = new MutableLiveData<>();
        showProgress = new MutableLiveData<>();
        loadFaqItems();
    }

    public LiveData<List<FaqItem>> getFaqItems() {
        return faqItems;
    }

    public LiveData<Boolean> getProgress() {
        return showProgress;
    }

    private void loadFaqItems() {
        showProgress.setValue(true);
        Demo.simulateLoading(() -> {
            Repository repository = new RepositoryImpl();
            List<FaqItem> faqItems = repository.getFaqItems("");
            this.faqItems.setValue(faqItems);
            showProgress.setValue(false);
        }, false);
    }
}
