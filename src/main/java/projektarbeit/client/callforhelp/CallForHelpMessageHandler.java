package projektarbeit.client.callforhelp;

import java.io.IOException;
import java.util.List;

import projektarbeit.client.MessageHandler;
import projektarbeit.client.model.Intent;
import projektarbeit.client.callforhelp.SendMailTo;

public class CallForHelpMessageHandler extends MessageHandler {
	
    public CallForHelpMessageHandler(List<String> message) {
        super(message);
    }
	 @Override
	 protected void onSessionStarted() {

	 }

	 @Override
	 protected String onLaunch() {
	    return "Who should I call";
	 }

	 @Override
	 protected String onIntent(Intent intent) {
	    String intentName = intent.getName();
	    String answer = null;
	    String slot = intent.getSlot("joe").getValue();
	    String mailAdress = "";
	    if(slot.contentEquals("joe")) {
	    	mailAdress = "khargoth24@t-online.de";
	    }

	    switch (intentName) {
	        case "CallForHelp":
	            answer = "Requested Help";
	            SendMailTo mail = new SendMailTo();
	            mail.sendToRecipient(mailAdress);
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
