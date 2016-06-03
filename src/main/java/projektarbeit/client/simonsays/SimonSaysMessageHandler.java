package projektarbeit.client.simonsays;

import projektarbeit.client.MessageHandler;
import projektarbeit.client.model.Intent;

import java.io.IOException;
import java.util.List;

public class SimonSaysMessageHandler extends MessageHandler {

    public SimonSaysMessageHandler(List<String> message) {
        super(message);
    }

    @Override
    protected void onSessionStarted() {

    }

    @Override
    protected String onLaunch() {
        return "You need to tell me what you want me to say.";
    }

    @Override
    protected String onIntent(Intent intent) {
        String intentName = intent.getName();
        String answer = null;

        switch (intentName) {
            case "SimonSays":
                answer = "Simon says: " + intent.getSlot("SimonSaysText").getValue();
                break;
            default:
                break;
        }

        try {
            Process p = new ProcessBuilder("cmd.exe", "/c", "start", "cmd.exe", "/k", "echo", answer).start();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return answer;
    }

    @Override
    protected void onSessionEnded() {

    }
}
