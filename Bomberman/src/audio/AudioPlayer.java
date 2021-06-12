package audio;



import javax.sound.sampled.*;

import game.Settings.AudioSettings;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AudioPlayer {

    private AudioSettings audioSettings;
    private List<AudioClip> audioClips;
    
    //Constructeur de classe.
    public AudioPlayer(AudioSettings audioSettings) {
        this.audioSettings = audioSettings;
        audioClips = new ArrayList<>();
    }
    
    //Mise a jour du clip.
    public void update() {
        audioClips.forEach(audioClip -> audioClip.update(audioSettings));

        List.copyOf(audioClips).forEach(audioClip -> {
            if(audioClip.hasFinishedPlaying()) {
                audioClip.cleanUp();
                audioClips.remove(audioClip);
            }
        });
    }
    
    //Jouer de la musique
    public void playMusic(String fileName) {
        final Clip clip = getClip(fileName);
        final MusicClip musicClip = new MusicClip(clip);
        musicClip.setVolume(audioSettings);
        audioClips.add(musicClip);
    }

    //Recuperer le clip.
    private Clip getClip(String fileName) {
        final URL soundFile = AudioPlayer.class.getResource("/res/sounds/" + fileName);
        try(AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile)) {
            final Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.setMicrosecondPosition(0);
            return clip;

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }

        return null;
    }
}
