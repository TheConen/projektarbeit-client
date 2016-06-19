package projektarbeit.client.bigbrother;

import projektarbeit.client.MessageHandler;
import projektarbeit.client.model.Intent;

import java.io.IOException;
import java.util.List;

public class BigBrotherMessageHandler extends MessageHandler {

    public BigBrotherMessageHandler(List<String> message) {
        super(message);
    }

    @Override
    protected void onSessionStarted() {

    }

    @Override
    protected String onLaunch() {
        return "Big Brother Launch Request";
    }

    @Override
    protected String onIntent(Intent intent) {
        String intentName = intent.getName();
        String answer = "Big Brother Intent Request";

        return answer;
    }

    @Override
    protected void onSessionEnded() {

    }
}

