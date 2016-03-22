package base;

import marytts.LocalMaryInterface;
import marytts.MaryInterface;
import marytts.exceptions.MaryConfigurationException;
import marytts.exceptions.SynthesisException;
import marytts.util.data.audio.AudioPlayer;

import javax.sound.sampled.AudioInputStream;
import java.util.Set;

/**
 * @author Jari Van Melckebeke
 */
public class Output {
    MaryInterface marytts;

    public Output() {
        try {
            marytts = new LocalMaryInterface();
            Set<String> voices = marytts.getAvailableVoices();
            voices.forEach(System.out::println);
        } catch (MaryConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void say(String text) {
        try {
            AudioInputStream stream = marytts.generateAudio(text);
            AudioPlayer player = new AudioPlayer(stream);
            player.run();
            player.join();
        } catch (SynthesisException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
