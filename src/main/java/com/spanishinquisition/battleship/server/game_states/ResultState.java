package com.spanishinquisition.battleship.server.game_states;

import com.spanishinquisition.battleship.common.Header;
import com.spanishinquisition.battleship.common.NetworkMessage;
import com.spanishinquisition.battleship.server.Player;
import com.spanishinquisition.battleship.server.Players;
import com.spanishinquisition.battleship.server.bus.MessageBus;
import com.spanishinquisition.battleship.server.BattleshipServer;

public class ResultState extends GameState {
    ResultState(Players players, MessageBus requestBus) {
        super(players, requestBus);
    }

    @Override
    public GameState transform() {
        buildWinnerResponse();
        return new EndState(players, requestBus);
    }

    private void buildWinnerResponse() {
        Player winner = players.getWinner();
        if (winner != null) {
            NetworkMessage winningMessage = new NetworkMessage(Header.GAME_WON, "true");
            NetworkMessage losingMessage = new NetworkMessage(Header.GAME_WON, "false");
            requestBus.addMessage(BattleshipServer.SERVER_ID, players.getWinner().getPlayerId(),
                    NetworkMessage.Parser.parseMessageToString(winningMessage));
            requestBus.addMessage(BattleshipServer.SERVER_ID, players.getOpponentOf(players.getWinner()).getPlayerId(),
                    NetworkMessage.Parser.parseMessageToString(losingMessage));
        }
    }
}
