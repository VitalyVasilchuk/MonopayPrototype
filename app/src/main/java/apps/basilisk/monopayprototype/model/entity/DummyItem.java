package apps.basilisk.monopayprototype.model.entity;

import androidx.core.graphics.ColorUtils;

import com.amulyakhare.textdrawable.util.ColorGenerator;

import java.io.Serializable;

public class DummyItem implements Serializable {
    private static final int ALPHA_PERCENT = 20;

    private String name;
    private String description;
    private int icon;
    private String identifier;
    private int colorForeground;
    private int colorBackground;

    public DummyItem(String name, String description, int icon, String identifier) {
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.identifier = identifier;

        ColorGenerator generator = ColorGenerator.MATERIAL;
        this.colorForeground = generator.getColor(name);
        this.colorBackground = ColorUtils.setAlphaComponent(colorForeground, ALPHA_PERCENT);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public int getColorForeground() {
        return colorForeground;
    }

    public void setColorForeground(int colorForeground) {
        this.colorForeground = colorForeground;
    }

    public int getColorBackground() {
        return colorBackground;
    }

    public void setColorBackground(int colorBackground) {
        this.colorBackground = colorBackground;
    }

    @Override
    public String toString() {
        return "DummyItem{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", icon=" + icon +
                ", identifier='" + identifier + '\'' +
                ", colorForeground=" + colorForeground +
                ", colorBackground=" + colorBackground +
                '}';
    }
}
