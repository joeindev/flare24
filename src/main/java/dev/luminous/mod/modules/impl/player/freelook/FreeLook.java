package dev.flare24.mod.modules.impl.player.freelook;

import dev.flare24.Flare;
import dev.flare24.api.events.eventbus.EventHandler;
import dev.flare24.api.events.impl.Render3DEvent;
import dev.flare24.mod.modules.Module;

public class FreeLook extends Module {
    public static FreeLook INSTANCE;
    public FreeLook() {
        super("FreeLook", Category.Player);
        setChinese("自由视角");
        camera = new CameraState();
        INSTANCE = this;
        Flare.EVENT_BUS.subscribe(new FreeLookUpdate());
    }

    private final CameraState camera;

    public CameraState getCameraState() {
        return camera;
    }

    public class FreeLookUpdate {
        @EventHandler
        public void onRender3D(Render3DEvent event) {
            CameraState camera = getCameraState();
            var doLock = isOn() && !camera.doLock;
            var doUnlock = !isOn() && camera.doLock;

            if (doLock) {
                if (!camera.doTransition) {
                    camera.lookYaw = camera.originalYaw();
                    camera.lookPitch = camera.originalPitch();
                }

                camera.doLock = true;
            }

            if (doUnlock) {
                camera.doLock = false;
                camera.doTransition = true;

                camera.transitionInitialYaw = camera.lookYaw;
                camera.transitionInitialPitch = camera.lookPitch;
            }
        }
    }

}
