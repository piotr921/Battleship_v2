package com.spanishinquisition.battleship.client.network;

import com.spanishinquisition.battleship.common.NetworkMessage;
import com.spanishinquisition.battleship.common.AppLogger;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author Michal_Partacz
 */
public class ResponsesBus {
    private Queue<NetworkMessage> serverResponses;

    ResponsesBus() {
        this.serverResponses = new ConcurrentLinkedQueue<>();
    }

    public boolean hasServerResponses() {
        return !this.serverResponses.isEmpty();
    }

    public NetworkMessage getAServerResponse() {
        return this.serverResponses.poll();
    }

    void addAServerResponse(String response) {
        AppLogger.logger.log(AppLogger.DEFAULT_LEVEL, "Response from server : " + response);
        if (response == null || response.isEmpty()) {
            AppLogger.logger.info("Response empty");
            return;
        }
        List<NetworkMessage> clientServerMessages = NetworkMessage.Parser.parseServerResponse(response);
        this.serverResponses.addAll(clientServerMessages);
    }
}
