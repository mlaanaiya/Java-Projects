package Game;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

public class audio implements Runnable {
	private Thread thread;
	private boolean running = false;
	public audio()
    {
        this.start();
    }

    public void start()
    {
        this.thread = new Thread(this);
        this.running = true;
        this.thread.start();
    }

    //
    private boolean playSong = false;
    private AudioInputStream inputStream;
    private String url;
    private Clip clip;

    public void playBackGround(String string) {
        if(this.clip != null)
        {
            this.clip.stop();
            this.clip.close();
        }
        try
        {
            this.clip = AudioSystem.getClip();
        }
        catch(LineUnavailableException e)
        {
            e.printStackTrace();
        }
        url = string + ".wav";
        this.playSong = true;
        this.inputStream = null;
    }

	@Override
	public void run() {
		while(running) {
			if(inputStream == null && playSong)
		    {
		                this.playSong = false;
		                try
		                {
		                    this.inputStream = AudioSystem.getAudioInputStream(new File(url));
		                    this.clip.open(inputStream);
		                    this.clip.loop(10);
		                }
		                catch(Exception e)
		                {
		                    e.printStackTrace();
		                }
		    }
		}
	}
}
	