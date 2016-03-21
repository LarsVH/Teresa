package base; /**
 * @author Jari Van Melckebeke
 */

import com.darkprograms.speech.microphone.Microphone;
import com.darkprograms.speech.recognizer.GoogleResponse;
import com.darkprograms.speech.recognizer.Recognizer;
import database.ManageCommand;
import database.ManagePerson;
import net.sourceforge.javaflacencoder.FLACFileWriter;

import java.io.File;
import java.lang.reflect.Method;
import java.util.TreeMap;

public class Main {
    private static Output output;
    private static Streak streak;
    private static TreeMap<String, Method> commands;
    private static ManageCommand manageCommand;
    private static ManagePerson managePerson;
    private static BaseFunctions baseFunctions;
    private static Resources resources;

    /**
     * in deze methode vind de main runtime plaats.
     *
     * @param args default system variables
     */
    public static void main(String[] args) throws Exception {
        if (!new File(System.getProperty("user.dir") + "/src/main/resources/prop.xml").exists()) {
            resources = Resources.createNew();
        } else resources = Resources.loadLib(new File(System.getProperty("user.dir") + "/src/main/resources/prop.xml"));
        streak = new Streak();
        manageCommand = new ManageCommand();
        baseFunctions = new BaseFunctions();
        //managePerson = new ManagePerson();
        run();
    }

    private static void run() throws Exception {
        System.out.println("system started");

        Microphone mic = new Microphone(FLACFileWriter.FLAC);
        File file = new File("/tmp/testfile2.flac");
        output = new Output();
        System.setProperty("google-api-key", "AIzaSyBzplCOQ_kvqG-TssHoifEyL7EWG9Dx9po");
        Recognizer recognizer = new Recognizer(Recognizer.Languages.ENGLISH_US, System.getProperty("google-api-key"));
        ManageCommand manageCommand = new ManageCommand();
        output.say("ready");
        while (!BaseFunctions.isDoExit()) {

            try {
                mic.captureAudioToFile(file);
            } catch (Exception ex) {
                System.out.println("error: microphone is not availible");
                ex.printStackTrace();
            }
            try {
                System.out.println("recording...");
                Thread.sleep(5000);
                mic.close();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            mic.close();
            System.out.println("recording stopped");
            int maxNumOfResponses = 4;
            //System.out.println("Sample rate is: " + (int) mic.getAudioFormat().getSampleRate());
            GoogleResponse response = recognizer.getRecognizedDataForFlac(file, maxNumOfResponses, (int) mic.getAudioFormat().getSampleRate());
            System.out.println("Google Response: " + response.getResponse());
            if (response.getResponse() != null) {
                BaseFunctions.goThroughBaseFunctions(response.getResponse());
                if (BaseFunctions.isTeresaSaid()) {
                    Method method = getMethod(response.getResponse());
                    output.say(String.valueOf(method.invoke(response.getResponse())));
                }
            }
        }


        file.deleteOnExit();
    }

    private static Method getMethod(String response) throws Exception {
        return manageCommand.getMethod(response);
    }
}