import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

/**
 * @author Jari Van Melckebeke
 */
public class Input {
<<<<<<< HEAD
=======
    private Configuration configuration;
>>>>>>> origin/master
    private LiveSpeechRecognizer recognizer;

    /**
     * deze methode zorgt voor de invoer
     *
     * @throws Exception
     */
    public Input() throws Exception {
<<<<<<< HEAD
        Configuration configuration = new Configuration();
=======
        configuration = new Configuration();
>>>>>>> origin/master
        configuration
                .setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        configuration
                .setDictionaryPath(System.getProperty("user.dir") + "/src/main/resources/5571.dic");
        configuration
                .setLanguageModelPath(System.getProperty("user.dir") + "/src/main/resources/5571.lm");
        recognizer = new LiveSpeechRecognizer(configuration);
    }

    /**
     * deze methode zorgt voor de invoer van een commando, ongeacht of deze legitiem is of niet
     *
     * @return de ingesproken commando, inclusief 'teresa'
     * @throws Exception
     */
<<<<<<< HEAD
    public String getCommand(boolean isTeresaSaid) throws Exception {
=======
    public String getCommand() throws Exception {
>>>>>>> origin/master
        recognizer.startRecognition(true);
        SpeechResult result;
        System.out.println("ready");
        while ((result = recognizer.getResult()) != null) {
            System.out.format("Hypothesis: %s\n", result.getHypothesis());
            System.out.println(result.getResult().getBestResultNoFiller());
<<<<<<< HEAD
            if (result.getHypothesis().contains("TERESA") || isTeresaSaid) {
                recognizer.stopRecognition();
                return result.getHypothesis();
=======
            if (result.getHypothesis().contains("TERESA")) {
                if (result.getHypothesis().equals("TERESA")) {
                    while ((result = recognizer.getResult()) != null) {
                        return result.getHypothesis();
                    }
                } else {
                    return result.getHypothesis();
                }
>>>>>>> origin/master
            }
        }
        recognizer.stopRecognition();
        return "";
    }
}
