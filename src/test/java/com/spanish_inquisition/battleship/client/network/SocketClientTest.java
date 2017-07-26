package com.spanish_inquisition.battleship.client.network;

import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

/**
 * @author Michal_Partacz
 */
@Test
public class SocketClientTest {
    @Test
    public void testSendStringToServer() throws Exception {
        byte[] emptyPayload = new byte[1001];

        final Socket socket = mock(Socket.class);
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(emptyPayload);
        when(socket.getOutputStream()).thenReturn(byteArrayOutputStream);
        when(socket.getInputStream()).thenReturn(byteArrayInputStream);

        SocketClient socketClient = new SocketClient();
        socketClient.socket = socket;
        socketClient.setUpStreamsOnSocket();
        assertEquals(byteArrayOutputStream.size(), 0);
        String stringToServer = "Hello server!";
        String stringExpectedFromServer = stringToServer + System.getProperty("line.separator");
        socketClient.sendStringToServer(stringToServer);
        assertNotEquals(byteArrayOutputStream.size(), 0);
        assertEquals(byteArrayOutputStream.toByteArray(), stringExpectedFromServer.getBytes(StandardCharsets.UTF_8));
    }

    @Test(expectedExceptions = IOException.class)
    public void testNewSocketClient_withoutServer() throws Exception {
        SocketClient.createSocketClientWithSocket();
    }

    public void testSimpleSocket() throws IOException {
        byte[] emptyPayload = new byte[1001];

        final Socket socket = mock(Socket.class);
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(emptyPayload);
        when(socket.getOutputStream()).thenReturn(byteArrayOutputStream);
        when(socket.getInputStream()).thenReturn(byteArrayInputStream);

        SocketClient socketClient = new SocketClient();
        assertNull(socketClient.input);
        assertNull(socketClient.output);
        socketClient.socket = socket;
        socketClient.setUpStreamsOnSocket();
        assertNotNull(socketClient.input);
        assertNotNull(socketClient.output);
    }

}