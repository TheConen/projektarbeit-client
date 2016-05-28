package projektarbeit.client;

import projektarbeit.client.simonsays.SimonSaysMessageHandler;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.*;

@ClientEndpoint
public class Websocket {

    private Session userSession = null;
    private URI endpointURI = null;
    private WebSocketContainer container = null;

    public Websocket(URI endpointURI) {
        this.endpointURI = endpointURI;
        try {
            container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this, endpointURI);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @OnOpen
    public void onOpen(Session userSession) {
        log("Opening websocket");
        this.userSession = userSession;
    }

    @OnClose
    public void onClose(CloseReason reason) {
        log("Connection lost. Reason: " + reason.getReasonPhrase());
    }

    @OnMessage
    public void onMessage(String message) {
        log("Received: " + message);

        List<String> messageParts = new ArrayList<>(Arrays.asList(message.split(";")));
        String skill = messageParts.remove(0);
        String requestId = messageParts.remove(0);

        String answer = null;

        switch (skill) {
            case "SimonSays":
                answer = new SimonSaysMessageHandler(messageParts).handleMessage();
                break;
            default:
                answer = "Unknown skill";
                break;
        }

        if (answer != null) {
            sendMessage(requestId + ";" + answer);
        }
    }

    @OnError
    public void onError(Throwable thr) {
        log("Error: " + thr.getMessage());
    }

    public void sendMessage(String message) {
        try {
            log("Sending: " + message);
            this.userSession.getBasicRemote().sendText(message);
        } catch (IOException e) {
            log("Sending message failed. Reason: " + e.getMessage());
        }
    }

    public boolean isConnected() {
        if(this.userSession == null) {
            return false;
        } else {
            return this.userSession.isOpen();
        }
    }

    public void reconnect() {
        try {
            log("Reconnecting...");
            this.userSession = container.connectToServer(this, endpointURI);
        } catch (Exception e) {
            log("Reconnect failed");
        }
    }

    private void log(String message) {
        String timeStamp = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date());
        System.out.println(timeStamp + "    " + endpointURI.toString() + "    " + message);
    }
}
