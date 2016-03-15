/**
 * @author Jari Van Melckebeke
 */

import com.darkprograms.speech.microphone.Microphone;
import com.darkprograms.speech.recognizer.GoogleResponse;
import com.darkprograms.speech.recognizer.Recognizer;
import net.sourceforge.javaflacencoder.FLACFileWriter;

import java.io.File;

public class Main {
    /**
     * in deze methode vind de main runtime plaats.
     *
     * @param args default system variables
     */
    public static void main(String[] args) throws Exception {
        System.out.println("system started");

        Microphone mic = new Microphone(FLACFileWriter.FLAC);
        File file = new File("/tmp/testfile2.flac");
        try {
            mic.captureAudioToFile(file);
        } catch (Exception ex) {
            System.out.println("error: microphone is not availible");
            ex.printStackTrace();
        }

        System.setProperty("google-api-key", "AIzaSyBzplCOQ_kvqG-TssHoifEyL7EWG9Dx9po");
        while (!BaseFunctions.doExit) {
            try {
                System.out.println("recording...");
                Thread.sleep(5000);
                mic.close();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            mic.close();
            System.out.println("recording stopped");

            Recognizer recognizer = new Recognizer(Recognizer.Languages.ENGLISH_US, System.getProperty("google-api-key"));
            int maxNumOfResponses = 4;
            System.out.println("Sample rate is: " + (int) mic.getAudioFormat().getSampleRate());
            GoogleResponse response = recognizer.getRecognizedDataForFlac(file, maxNumOfResponses, (int) mic.getAudioFormat().getSampleRate());
            System.out.println("Google Response: " + response.getResponse());

            BaseFunctions.goThroughBaseFunctions(response.getResponse());
        }


        file.deleteOnExit();
    }

    static class BaseFunctions {
        public static boolean doExit = false;
        private static boolean teresaSaid = false;

        public BaseFunctions() {

        }

        public static void goThroughBaseFunctions(String response) {
            switch (response) {
                case "quit":
                    doExit = true;
                    break;
                case "teresa":
                    teresaSaid = true;
                    break;
                case "thank you":
                    teresaSaid = false;
                    break;
            }
        }
    }
}