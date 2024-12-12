package dev.flare24.mod.modules.impl.client;

import dev.flare24.mod.modules.settings.impl.SliderSetting;
import dev.flare24.mod.gui.font.FontRenderers;
import dev.flare24.mod.modules.Module;

public class FontSetting extends Module {
    public static FontSetting INSTANCE;
    public final SliderSetting size = add(new SliderSetting("Size", 8, 1, 15, 1));
    public final SliderSetting yOffset = add(new SliderSetting("Offset", 0, -5, 15, 0.1));
    public FontSetting() {
        super("Font", Category.Client);
        setChinese("字体设置");
        INSTANCE = this;
    }

    @Override
    public void enable() {
        try {
            FontRenderers.createDefault(size.getValueFloat());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
