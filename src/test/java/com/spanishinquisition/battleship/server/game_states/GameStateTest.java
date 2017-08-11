package com.spanishinquisition.battleship.server.game_states;

import com.spanishinquisition.battleship.server.bus.MessageBus;
import com.spanishinquisition.battleship.server.Players;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;

public class GameStateTest {
    @Test
    public void checkIfGameIsRunning() {
        Players players = mock(Players.class);
        MessageBus requestBus = mock(MessageBus.class);
        Assert.assertTrue(new GameState(players, requestBus) {
            @Override
            public GameState transform() {
                return null;
            }
        }.isGameRunning());
    }
}