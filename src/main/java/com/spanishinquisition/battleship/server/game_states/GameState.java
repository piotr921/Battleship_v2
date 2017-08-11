package com.spanishinquisition.battleship.server.game_states;

import com.spanishinquisition.battleship.server.Players;
import com.spanishinquisition.battleship.server.bus.MessageBus;

public abstract class GameState {
    protected Players players;
    protected MessageBus requestBus;

    protected GameState(Players players, MessageBus requestBus) {
        this.players = players;
        this.requestBus = requestBus;
    }

    public boolean isGameRunning() {
        return true;
    }

    public abstract GameState transform();
}