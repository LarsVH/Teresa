import marytts.LocalMaryInterface;
import marytts.MaryInterface;
import marytts.exceptions.MaryConfigurationException;
import marytts.exceptions.SynthesisException;
import marytts.util.data.audio.AudioPlayer;

import javax.sound.sampled.AudioInputStream;

/**
 * @author Jari Van Melckebeke
 */
public class Output {
    /**
     * Deze methode zorgt ervoor dat TERESA kan spreken
     *
     * @param str de text die ze moet zeggen
     * @throws Exception
     */
    public static void speak(String str) throws Exception {
        MaryInterface marytts = new LocalMaryInterface();
        AudioInputStream audio;
        try {
            audio = marytts.generateAudio(str);
        } catch (SynthesisException e) {
            audio = marytts.generateAudio(getSpeakable(str));
        }
        AudioPlayer player = new AudioPlayer(audio);
        player.start();
        player.join();
    }

    /**
     * deze methode transformeert de niet uitspreekbare delen naar "error 0 0 1"
     *
     * @param str de niet-uitspreekbare string
     * @return de getransformeerde zin.
     * @throws MaryConfigurationException
     */
    private static String getSpeakable(String str) throws MaryConfigurationException {
        str = str.replace('\n', ' ');
        String[] words = str.split(" ");
        System.out.println(str);
        String out = "";
        MaryInterface marytts = new LocalMaryInterface();
        for (String word : words) {
            try {
                marytts.generateAudio(word);
                out += word + " ";
            } catch (SynthesisException e) {
                if (word.contains("ij")) {
                    word = word.replaceAll("ij", "ei");
                    try {
                        marytts.generateAudio(word);
                        out += word + " ";
                    } catch (SynthesisException e1) {
                        out += "error 0 0 1";
                    }
                } else {
                    out += "error 0 0 1";
                }
            }
        }
        System.out.println(out);
        return out;
    }
}

