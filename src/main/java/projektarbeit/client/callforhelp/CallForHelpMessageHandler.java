package projektarbeit.client.callforhelp;

import java.util.List;

import projektarbeit.client.MessageHandler;
import projektarbeit.client.model.Intent;
import projektarbeit.client.model.Slot;

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
        MailSender mail = null;

        switch (intentName) {
            case "CallForHelp":
                answer = "I sent out a distress call to your standard emergency contact.";
                mail = new MailSender();
                mail.send();
                break;
            case "CallPersonForHelp":
                Slot slot = intent.getSlot("Person");
                String person = slot.getValue().toLowerCase();
                String recipient = null;
                answer = "I sent out a distress call to " + person;
                switch (person) {
                    case "johannes":
                        recipient = "johannesconen@gmail.com";
                        break;
                    case "daniel":
                        recipient = "d-rothmann@web.de";
                        break;
                    case "dirk":
                        recipient = "dirk.hoffmann@hs-karlsruhe.de";
                        break;
                    case "isabel":
                        recipient = "i.dorner@enbw.com";
                        break;
                    case "thomas":
                        recipient = "thomas.fuchss@hs-karlsruhe.de";
                        break;
                    case "carsten":
                        recipient = "c.stauch@enbw.com";
                        break;
                    default:
                        recipient = "johannesconen@gmail.com";
                        answer = "I sent out a distress call to your standard emergency contact.";
                        break;
                }

                mail = new MailSender(recipient);
                mail.send();
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
