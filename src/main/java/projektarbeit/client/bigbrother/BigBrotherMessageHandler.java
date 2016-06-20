package projektarbeit.client.bigbrother;

import projektarbeit.client.MessageHandler;
import projektarbeit.client.model.Intent;

import java.io.IOException;
import java.util.List;

public class BigBrotherMessageHandler extends MessageHandler {

    static private Process p;
    final private String address = "http://192.168.1.10/videostream.cgi?user=admin&pwd=Abcd123.&rate=4096";

    public BigBrotherMessageHandler(List<String> message) {
        super(message);
    }

    @Override
    protected void onSessionStarted() {

    }

    @Override
    protected String onLaunch() {
        String answer = openBigBrother();
        return answer;
    }

    @Override
    protected String onIntent(Intent intent) {
        String intentName = intent.getName();
        String answer = null;
        switch (intentName){
            case "Open":
                answer = openBigBrother();
                break;
            case "Close":
                p.destroy();
                answer = "I closed Big Brother on your computer";
                break;
        }

        return answer;
    }

    @Override
    protected void onSessionEnded() {

    }

    private String openBigBrother() {
        String answer = null;
        if (p == null || !p.isAlive()) {
            try {
                p = new ProcessBuilder("C:\\Program Files (x86)\\VideoLAN\\VLC\\vlc.exe", address, "-f").start();
                answer = "I opened Big Brother on your computer";
            } catch (IOException e) {
                answer = "An error occured opening Big Brother";
                e.printStackTrace();
            }
        } else {
            answer = "Big Brother is already running on your computer";
        }
        return answer;
    }
}

