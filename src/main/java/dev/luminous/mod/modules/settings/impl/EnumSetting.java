package dev.flare24.mod.modules.settings.impl;

import dev.flare24.Flare;
import dev.flare24.core.impl.ModuleManager;
import dev.flare24.mod.modules.settings.EnumConverter;
import dev.flare24.mod.modules.settings.Setting;

import java.util.function.BooleanSupplier;

public class EnumSetting<T extends Enum<T>> extends Setting {
    private T value;
    private final T defaultValue;
    public boolean popped = false;
    public EnumSetting(String name, T defaultValue) {
        super(name, ModuleManager.lastLoadMod.getName() + "_" + name);
        this.value = defaultValue;
        this.defaultValue = defaultValue;
    }

    public EnumSetting(String name, T defaultValue, BooleanSupplier visibilityIn) {
        super(name, ModuleManager.lastLoadMod.getName() + "_" + name, visibilityIn);
        this.value = defaultValue;
        this.defaultValue = defaultValue;
    }

    public void increaseEnum() {
        value = (T) EnumConverter.increaseEnum(value);
    }

    public final T getValue() {
        return this.value;
    }
    public void setEnumValue(String value) {
        for (T e : this.value.getDeclaringClass().getEnumConstants()) {
            if (!e.name().equalsIgnoreCase(value)) continue;
            this.value = e;
        }
    }
    @Override
    public void loadSetting() {
        EnumConverter converter = new EnumConverter();
        String enumString = Flare.CONFIG.getString(this.getLine());
        if (enumString == null) {
            value = defaultValue;
            return;
        }
        Enum<?> value = converter.get(defaultValue, enumString);
        if (value != null) {
            this.value = (T) value;
        } else {
            this.value = defaultValue;
        }
    }

    public boolean is(T mode) {
        return getValue() == mode;
    }
}