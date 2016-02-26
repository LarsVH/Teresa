import marytts.LocalMaryInterface;
import marytts.MaryInterface;
import marytts.modules.synthesis.Voice;
import marytts.util.data.audio.AudioPlayer;

import javax.sound.sampled.AudioInputStream;
import java.util.Set;

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
        AudioInputStream audio = marytts.generateAudio(str);
        AudioPlayer player = new AudioPlayer(audio);
        player.start();
        player.join();
    }
}

