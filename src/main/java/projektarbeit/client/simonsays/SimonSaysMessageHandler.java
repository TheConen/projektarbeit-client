package projektarbeit.client.simonsays;

import projektarbeit.client.MessageHandler;
import projektarbeit.client.model.Intent;

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
                answer= intent.getSlot("SimonSaysText").getValue();
                break;
            default:
                break;
        }

        return answer;
    }

    @Override
    protected void onSessionEnded() {

    }
}
