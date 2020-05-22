package apps.basilisk.monopayprototype.model;

import java.util.List;

import apps.basilisk.monopayprototype.model.entity.DummyItem;
import apps.basilisk.monopayprototype.model.entity.FaqItem;
import apps.basilisk.monopayprototype.model.entity.ServiceItem;

public interface Repository {
    List<ServiceItem> getServices();

    List<DummyItem> getDummyItems(String templateTitle, int templateResId, String templateIdentifier, int count);

    List<FaqItem> getFaqItems(String language);
}
