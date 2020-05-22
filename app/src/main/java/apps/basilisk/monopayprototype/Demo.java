package apps.basilisk.monopayprototype;

import android.os.Handler;

public class Demo {
    public static final int LOADING_SHORT = 1000;
    public static final int LOADING_LONG = 3000;

    public static void simulateLoading(Runnable onLoaded, boolean isLong) {
        new Handler().postDelayed(onLoaded, isLong ? LOADING_LONG : LOADING_SHORT);
    }

    public enum EService {
        MoneyTransfer("Переказ з картки на картку",
                "0,5%+2,5 грн для зареєстрованих користувачів",
                R.drawable.ic_logo_mtransfer, "", ""),
        Itit("Переказ з номера Інтертелеком",
                "Безкоштовний переказ з номера на номер Інтертелеком",
                R.drawable.ic_logo_itit, "", ""),
        Mobile("Поповнення мобiльного",
                "Комісія 0% по платежу з номера Інтертелеком",
                R.drawable.ic_logo_mobile, "Оператор", "Номер телефону"),
        Finance("Фінансові послуги",
                "Переказ на карти, погашення кредитів MoneyVeo, ccloan та інших",
                R.drawable.ic_logo_finance, "Фінансовый\nоператор", "Номер договору"),
        Communal("Комунальні послуги",
                "Комісія 0% по платежу з номера Інтертелеком",
                R.drawable.ic_logo_communal, "Комунальний\nоператор", "Особовий рахунок"),
        Payment("Платіж за реквізитами",
                "Платіж за довільними реквізитами на IBAN",
                R.drawable.ic_logo_payment, "Призначення\nплатежу", ""),
        Phone("Телефонний зв'язок",
                "Комісія 0% по платежу з номера Інтертелеком",
                R.drawable.ic_logo_phone, "Оператор\nзв'язку", "Номер телефону"),
        Internet("Інтернет та ТБ",
                "Комісія 0% по платежу з номера Інтертелеком",
                R.drawable.ic_logo_internet, "Провайдер", "Номер договору"),
        Budget("Платежі до бюджету",
                "Комісія 0% по платежу з номера Інтертелеком",
                R.drawable.ic_logo_budget, "Призначення\nплатежу", "Ідентифікатор"),
        Taxi("Таксі",
                "Комісія 0% по платежу з номера Інтертелеком",
                R.drawable.ic_logo_taxi, "Перевезник","Номер позивного"),
        Games("Ігри та розваги",
                "Комісія 0% по платежу з номера Інтертелеком",
                R.drawable.ic_logo_game, "Гра", "Ім'я користувача"),
        Charity("Соціальні проекти",
                "Комісія 0% по платежу з номера Інтертелеком",
                R.drawable.ic_logo_charity, "Проект", "Особовий рахунок"),
        Other("Інші послуги",
                "Комісія 0% по платежу з номера Інтертелеком",
                R.drawable.ic_logo_other, "Послуга", "Ідентифікатор");

        private String title;
        private String description;
        private int urlImage;
        private String itemName;
        private String itemIdentifier;

        EService(String title, String description, int urlImage, String itemName, String itemIdentifier) {
            this.title = title;
            this.description = description;
            this.urlImage = urlImage;
            this.itemName = itemName;
            this.itemIdentifier = itemIdentifier;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public int getUrlImage() {
            return urlImage;
        }

        public String getItemName() {
            return itemName;
        }

        public String getItemIdentifier() {
            return itemIdentifier;
        }
    }

}
