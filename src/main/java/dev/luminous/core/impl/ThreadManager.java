package dev.flare24.core.impl;

import dev.flare24.api.utils.world.BlockUtil;
import dev.flare24.Flare;
import dev.flare24.api.events.eventbus.EventHandler;
import dev.flare24.api.events.eventbus.EventPriority;
import dev.flare24.api.events.impl.TickEvent;
import dev.flare24.mod.modules.impl.render.PlaceRender;

public class ThreadManager {
    public static ClientService clientService;

    public ThreadManager() {
        Flare.EVENT_BUS.subscribe(this);
        clientService = new ClientService();
        clientService.setName("FlareClientService");
        clientService.setDaemon(true);
        clientService.start();
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEvent(TickEvent event) {
        if (event.isPre()) {
            if (!clientService.isAlive()) {
                clientService = new ClientService();
                clientService.setName("FlareClientService");
                clientService.setDaemon(true);
                clientService.start();
            }
            BlockUtil.placedPos.forEach(pos -> PlaceRender.renderMap.put(pos, PlaceRender.INSTANCE.create(pos)));
            BlockUtil.placedPos.clear();
            Flare.SERVER.onUpdate();
            Flare.PLAYER.onUpdate();
            Flare.MODULE.onUpdate();
            Flare.GUI.onUpdate();
            Flare.POP.onUpdate();
        }
    }

    public static class ClientService extends Thread {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    if (Flare.MODULE != null) {
                        Flare.MODULE.onThread();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
