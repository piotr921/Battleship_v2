package com.spanishinquisition.battleship.server.game_states;

import com.spanishinquisition.battleship.server.Players;
import com.spanishinquisition.battleship.server.bus.MessageBus;

public class EndState extends GameState {
    EndState(Players players, MessageBus requestBus) {
        super(players, requestBus);
    }

    @Override
    public boolean isGameRunning() {
        return false;
    }

    @Override
    public GameState transform() {
        return null;
    }
}
