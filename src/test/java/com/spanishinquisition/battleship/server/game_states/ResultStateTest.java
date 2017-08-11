package com.spanishinquisition.battleship.server.game_states;

import com.spanishinquisition.battleship.server.bus.MessageBus;
import com.spanishinquisition.battleship.server.Player;
import com.spanishinquisition.battleship.server.Players;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;

public class ResultStateTest {
    @Test
    public void testIfGoesToEndState() {
        Player p1 = mock(Player.class);
        Player p2 = mock(Player.class);
        Players players = new Players(p1,p2);
        MessageBus messageBus = mock(MessageBus.class);
        Assert.assertEquals(EndState.class, new ResultState(players, messageBus).transform().getClass());
    }
}