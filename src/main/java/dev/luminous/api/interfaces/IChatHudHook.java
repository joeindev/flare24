package dev.flare24.api.interfaces;

import net.minecraft.text.Text;

public interface IChatHudHook {
    void addMessage(Text message, int id);
}
