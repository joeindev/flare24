package dev.flare24.mod.modules.impl.render;

import dev.flare24.mod.modules.Module;
import dev.flare24.mod.modules.settings.impl.SliderSetting;

public class CustomFov extends Module {
    public static CustomFov INSTANCE;

    public final SliderSetting fov = add(new SliderSetting("Fov", 90, 30, 170, 1));
    public final SliderSetting itemFov = add(new SliderSetting("ItemFov", 70, 30, 170, 1));
    public CustomFov() {
        super("Fov", Category.Render);
        setChinese("自定义视角");
        INSTANCE = this;
    }
}
