package projektarbeit.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class ProjektarbeitClientApplication {

    static private Websocket simonSaysWebsocket;
    static final private String SIMONSAYSURI = "ws://projektarbeit.jconen.de/simonsaysws";

    public static void main(String[] args) {
		SpringApplication.run(ProjektarbeitClientApplication.class, args);

        simonSaysWebsocket = initializeWebsocket(SIMONSAYSURI);

    }

    private static Websocket initializeWebsocket(String uriString) {
        try {
            Websocket websocket = new Websocket(new URI(uriString));
            ScheduledExecutorService scheduledKeepalive = Executors.newSingleThreadScheduledExecutor();
            scheduledKeepalive.scheduleAtFixedRate(() -> {
                if (websocket.isConnected()) {
                    websocket.sendMessage("keep-alive");
                } else {
                    websocket.reconnect();
                }
            }, 1, 30, TimeUnit.SECONDS);
            return websocket;
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }
}
