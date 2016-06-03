package projektarbeit.client;

import projektarbeit.client.model.Intent;
import projektarbeit.client.model.Slot;

import java.util.HashMap;
import java.util.List;

public abstract class MessageHandler {

    protected String requestType = null;
    protected  List<String> remainingMessage = null;

    public MessageHandler(List<String> message) {
        requestType = message.remove(0);
        remainingMessage = message;
    }

    public String handleMessage() {
        String answer = null;
        switch (requestType) {
            case "SessionStarted" :
                onSessionStarted();
                break;
            case "Launch" :
                answer = onLaunch();
                break;
            case "Intent" :
                String intentName = remainingMessage.remove(0);
                HashMap<String, Slot> slots = new HashMap<>();

                for (String itr: remainingMessage) {
                    String messageParts[] = itr.split(",");
                    String slotName = messageParts[0];
                    String slotValue = messageParts[1];
                    Slot slot = new Slot(slotName, slotValue);
                    slots.put(slotName, slot);
                }

                Intent intent = new Intent(intentName, slots);
                answer = onIntent(intent);
                break;
            case "SessionEnded" :
                onSessionEnded();
                break;
            default:
                break;
        }

        return answer;
    }

    protected abstract void onSessionStarted();
    protected abstract String onLaunch();
    protected abstract String onIntent(Intent intent);
    protected abstract void onSessionEnded();

}
