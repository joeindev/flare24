package dev.flare24.asm.mixins;

import dev.flare24.Flare;
import dev.flare24.api.events.impl.MouseUpdateEvent;
import dev.flare24.mod.gui.clickgui.ClickGuiScreen;
import net.minecraft.client.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static dev.flare24.api.utils.Wrapper.mc;
@Mixin(Mouse.class)
public class MixinMouse {
    @Inject(method = "onMouseButton", at = @At("HEAD"))
    private void onMouse(long window, int button, int action, int mods, CallbackInfo ci) {
        int key = -(button + 2);
        if (mc.currentScreen instanceof ClickGuiScreen && action == 1 && Flare.MODULE.setBind(key)) {
            return;
        }
        if (action == 1) {
            Flare.MODULE.onKeyPressed(key);
        }
        if (action == 0) {
            Flare.MODULE.onKeyReleased(key);
        }
    }

    @Inject(method = "updateMouse", at = @At("RETURN"))
    private void updateHook(CallbackInfo ci) {
        Flare.EVENT_BUS.post(new MouseUpdateEvent());
    }
}
