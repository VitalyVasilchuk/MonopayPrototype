package apps.basilisk.monopayprototype.model.entity;

import java.io.Serializable;

public class ServiceItem implements Serializable {
    private String title;
    private String description;
    private int imageResId;
    private String itemName;
    private String itemIdentifier;

    public ServiceItem(String title, String description, int imageResId, String itemName, String itemIdentifier) {
        this.title = title;
        this.description = description;
        this.imageResId = imageResId;
        this.itemName = itemName;
        this.itemIdentifier = itemIdentifier;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemIdentifier() {
        return itemIdentifier;
    }

    public void setItemIdentifier(String itemIdentifier) {
        this.itemIdentifier = itemIdentifier;
    }

    @Override
    public String toString() {
        return "ServiceItem{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", imageResId=" + imageResId +
                ", itemName='" + itemName + '\'' +
                ", itemIdentifier='" + itemIdentifier + '\'' +
                '}';
    }
}
