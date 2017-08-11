package com.spanishinquisition.battleship.server.game_states;

import com.spanishinquisition.battleship.server.bus.MessageBus;
import com.spanishinquisition.battleship.server.Players;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;

public class EndStateTest {
    @Test
    public void checkIfGameIsNotRunning() {
        Assert.assertFalse(new EndState(null, null).isGameRunning());
    }

    @Test
    public void checkIfStateIsNotTransformingIntoAnotherState() {
        Players players = mock(Players.class);
        MessageBus requestBus = mock(MessageBus.class);
        Assert.assertNull(new EndState(players, requestBus).transform());
    }
}