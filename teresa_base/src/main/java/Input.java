import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

/**
 * @author Jari Van Melckebeke
 */
public class Input {
    private Configuration configuration;
    private LiveSpeechRecognizer recognizer;

    /**
     * deze methode zorgt voor de invoer
     *
     * @throws Exception
     */
    public Input() throws Exception {
        Configuration configuration = new Configuration();
        configuration = new Configuration();
        configuration
                .setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        configuration
                .setDictionaryPath(System.getProperty("user.dir") + "/src/main/resources/2805.dic");
        configuration
                .setLanguageModelPath(System.getProperty("user.dir") + "/src/main/resources/2805.lm");
        recognizer = new LiveSpeechRecognizer(configuration);
    }

    /**
     * deze methode zorgt voor de invoer van een commando, ongeacht of deze legitiem is of niet
     *
     * @return de ingesproken commando, inclusief 'teresa'
     * @throws Exception
     */
    public String getCommand(boolean isTeresaSaid) throws Exception {
        recognizer.startRecognition(true);
        SpeechResult result;
        System.out.println("ready");
        while ((result = recognizer.getResult()) != null) {
            System.out.format("Hypothesis: %s\n", result.getHypothesis());
            System.out.println(result.getResult().getBestResultNoFiller());
            if (result.getHypothesis().contains("TERESA") || isTeresaSaid) {
                recognizer.stopRecognition();
                return result.getHypothesis();
            }
        }
        recognizer.stopRecognition();
        return "";
    }
}

