package com.spanishinquisition.battleship.server;

import com.spanishinquisition.battleship.server.fleet.Fleet;
import com.spanishinquisition.battleship.server.fleet.Ship;

import static com.spanishinquisition.battleship.common.AppLogger.DEFAULT_LEVEL;
import static com.spanishinquisition.battleship.common.AppLogger.logger;

public class Player {
    private ClientConnectionHandler client;
    private Fleet fleet;

    public Player(ClientConnectionHandler client) {
        this.client = client;
    }

    public int getPlayerId() {
        return client.getClientId();
    }

    public void setFleet(Fleet fleet) {
        logger.log(DEFAULT_LEVEL, "Setting players fleet " + fleet.toString());
        this.fleet = fleet;
    }

    public Fleet getFleet() {
        return fleet;
    }

    public boolean hasNoFleet() {
        return fleet.hasNoShips();
    }

    public boolean fleetGotHit(Integer targetedField) {
        return fleet.pointIsClaimedByFleet(targetedField);
    }

    public boolean gotDestroyedShip() {
        return fleet.hasCurrentDestroyedShip();
    }

    public Ship pullDestroyedShip() {
        return fleet.pullDestroyedShip();
    }
}
