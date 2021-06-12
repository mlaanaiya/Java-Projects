package audio;

import game.Settings.AudioSettings;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public abstract class AudioClip {

    private final Clip clip;
    //Constructeur de classe.
    public AudioClip(Clip clip) {
        this.clip = clip;
        clip.start();
    }
    //Modifier le volume.
    public void update(AudioSettings audioSettings) {
        setVolume(audioSettings);
    }
    //Regler le volume de musique
    void setVolume(AudioSettings audioSettings) {
        final FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        float range = control.getMaximum() - control.getMinimum();
        float gain = (range * getVolume(audioSettings)) + control.getMinimum();
        
        control.setValue(gain);
    }

    protected abstract float getVolume(AudioSettings audioSettings);
    //Determiner si le clip a finis ou non .
    public boolean hasFinishedPlaying() {
        return !clip.isRunning();
    }
    //Terminaison du clip.
    public void cleanUp() {
        clip.close();
    }
}
