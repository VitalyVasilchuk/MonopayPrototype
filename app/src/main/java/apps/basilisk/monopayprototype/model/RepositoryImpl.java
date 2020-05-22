package apps.basilisk.monopayprototype.model;

import java.util.ArrayList;
import java.util.List;

import apps.basilisk.monopayprototype.Demo;
import apps.basilisk.monopayprototype.model.entity.DummyItem;
import apps.basilisk.monopayprototype.model.entity.FaqItem;
import apps.basilisk.monopayprototype.model.entity.ServiceItem;

public class RepositoryImpl implements Repository {
    @Override
    public List<ServiceItem> getServices() {
        List<ServiceItem> services = new ArrayList<>();
        for (Demo.EService eService : Demo.EService.values()) {
            services.add(
                    new ServiceItem(
                            eService.getTitle(), eService.getDescription(), eService.getUrlImage(),
                            eService.getItemName(), eService.getItemIdentifier()
                    )
            );
        }
        return services;
    }

    @Override
    public List<DummyItem> getDummyItems(String templateTitle, int templateResId, String templateIdentifier, int count) {
        List<DummyItem> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(
                    new DummyItem(String.format("%s #%s", templateTitle, i + 1), "", templateResId, templateIdentifier)
            );
        }
        return list;
    }

    @Override
    public List<FaqItem> getFaqItems(String language) {
        final int ITEM_COUNT = 10;
        List<FaqItem> faqItems = new ArrayList<>();
        for (int i = 0; i < ITEM_COUNT; i++) {
            faqItems.add(new FaqItem(String.format("Question number %s?", i+1), "Answer answer answer.\nAnswer answer answer answer answer answer."));
        }
        faqItems.add(0, new FaqItem("Безпека платежів", "Всеукраїнський сервіс прийому платежів MONOPAY працює відповідно до міжнародного стандарту безпеки PCI DSS 3.2 (Payment Card Industry Data Security Standard). PCI DSS (Payment Card Industry Data Security Standard) - стандарт безпеки даних індустрії платіжних карт, розроблений Радою за стандартами безпеки індустрії платіжних карт (Payment Card Industry Security Standards Council, PCI SSC). Будь-яка організація, яка планує приймати й обробляти дані банківських карт на своєму сайті, повинна відповідати вимогам PCI DSS.\n" +
                "\n" +
                "Visa і Mastercard. Всі оплати з використанням банківських карт Visa і Mastercard здійснюються з урахуванням заходів безпеки міжнародних платіжних систем Visa і Mastercard.\n" +
                "\n" +
                "Протокол HTTPS. Забезпечує безпечне і захищене отримання ідентифікації від віддаленого комп'ютера. При вході в систему звертайте увагу на адресний рядок Вашого браузера. Так як веб-вузол сервісу Monopay.com.ua має справжній і дійсний сертифікат безпеки, то при вході в адресному рядку браузера повинні відображатися перші символи адреси https: // замість http: //."));
        faqItems.add(0, new FaqItem("Що таке MONOPAY", "Всеукраїнський сервіс прийому платежів та оплати всіх можливих послуг через Інтернет, надається фінансовою компанією ТОВ \"Адеміо\", що працює в рамках ВПС \"Flashpay\". На сайті MONOPAY власники банківських карток Visa та MasterCard можуть оплатити послуги понад 1000 постачальникам товарів та послуг, що надають свої послуги по всій території України.\n" +
                "\n" +
                "Сервіс Monopay.com.ua працює у відповідності з міжнародними стандартами безпеки PCI DSS 3.2."));

        return faqItems;
    }

}
