package dev.flare24.api.events.impl;

import dev.flare24.api.events.Event;

public class GameLeftEvent extends Event {
    public GameLeftEvent() {
        super(Stage.Post);
    }
}
