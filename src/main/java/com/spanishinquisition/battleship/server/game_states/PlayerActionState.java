package com.spanishinquisition.battleship.server.game_states;

import com.spanishinquisition.battleship.common.Header;
import com.spanishinquisition.battleship.server.Players;
import com.spanishinquisition.battleship.server.bus.MessageBus;

import static com.spanishinquisition.battleship.server.BattleshipServer.SERVER_ID;

public class PlayerActionState extends GameState {

    PlayerActionState(Players players, MessageBus requestBus) {
        super(players, requestBus);
    }

    @Override
    public GameState transform() {
        requestBus.addMessage(SERVER_ID, players.getCurrentPlayer().getPlayerId(), Header.PLAYER_TURN.name());
        requestBus.addMessage(SERVER_ID, players.getOpponentOf(players.getCurrentPlayer()).getPlayerId(),
                Header.OPPONENT_TURN.name());
        return new ShotState(players, requestBus);
    }
}