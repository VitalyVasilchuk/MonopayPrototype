package apps.basilisk.monopayprototype.ui.fragment.dummy;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import apps.basilisk.monopayprototype.Demo;
import apps.basilisk.monopayprototype.R;
import apps.basilisk.monopayprototype.model.Repository;
import apps.basilisk.monopayprototype.model.RepositoryImpl;
import apps.basilisk.monopayprototype.model.entity.DummyItem;

public class DummyViewModel extends ViewModel {
    private MutableLiveData<List<DummyItem>> data;
    private String templateTitle;
    private int templateResId;
    private String templateIdentifier;
    private MutableLiveData<Boolean> showProgress;

    public DummyViewModel() {
        data = new MutableLiveData<>();
        templateTitle = "Operator";
        templateResId = R.drawable.ic_noimage;
        showProgress = new MutableLiveData<>(true);
        loadData();
    }

    public LiveData<List<DummyItem>> getData() {
        return data;
    }

    public LiveData<Boolean> getProgressState(){
        return showProgress;
    }

    public void setTemplateTitle(String templateTitle) {
        this.templateTitle = templateTitle;
    }

    public void setTemplateResId(int templateResId) {
        this.templateResId = templateResId;
    }

    public void setTemplateIdentifier(String templateIdentifier) {
        this.templateIdentifier = templateIdentifier;
    }

    private void loadData() {
        showProgress.setValue(true);
        Demo.simulateLoading(() -> {
            Repository repository = new RepositoryImpl();
            data.setValue(repository.getDummyItems(templateTitle, templateResId, templateIdentifier, 20));
            showProgress.setValue(false);
        }, true);
    }
}
