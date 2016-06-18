package projektarbeit.client.callforhelp;

import java.util.List;

import projektarbeit.client.MessageHandler;
import projektarbeit.client.model.Intent;

public class CallForHelpMessageHandler extends MessageHandler {

    public CallForHelpMessageHandler(List<String> message) {
        super(message);
    }

    @Override
    protected void onSessionStarted() {

    }

    @Override
    protected String onLaunch() {
        String answer = "I sent out a distress call to your standard emergency contact.";
        MailSender mail = new MailSender();
        mail.send();
        return answer;
    }

    @Override
    protected String onIntent(Intent intent) {
        String intentName = intent.getName();
        String answer = null;

        switch (intentName) {
            case "CallForHelp":
                answer = "I sent out a distress call to your standard emergency contact.";
                MailSender mail = new MailSender();
                mail.send();
                break;
            case "CallPersonForHelp":
                answer = "This function is not implemented yet";
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
