package dev.flare24.api.events.impl;

import dev.flare24.api.events.Event;
import net.minecraft.entity.vehicle.BoatEntity;

public class BoatMoveEvent extends Event {

    private final BoatEntity boat;

    public BoatMoveEvent(BoatEntity boat) {
        super(Stage.Pre);
        this.boat = boat;
    }

    public BoatEntity getBoat() {
        return boat;
    }
}

