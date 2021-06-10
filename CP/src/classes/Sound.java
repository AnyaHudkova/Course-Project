package classes;

import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound implements AutoCloseable {
	private boolean released = false;
	private AudioInputStream stream = null;
	private Clip clip = null;
	private boolean playing = false;
	
	public Sound(URL u) {
		try {
			stream = AudioSystem.getAudioInputStream(u);
			clip = AudioSystem.getClip();
			clip.open(stream);
			clip.addLineListener(new Listener());
			released = true;
		} catch (IOException | UnsupportedAudioFileException 
				| LineUnavailableException exc) {
			exc.printStackTrace();
			released = false;
			close();
		}
	}
	public boolean isReleased() {return released;}

	public boolean isPlaying() {return playing;}

	public void play(boolean breakOld) {
		if (released) {
			if (breakOld) {
				clip.stop();
				clip.setFramePosition(0);
				clip.start();
				playing = true;
			} else if (!isPlaying()) {
				clip.setFramePosition(0);
				clip.start();
				playing = true;
			}
		}
	}

	public void play() {play(true);}

	public void stop() {if (playing) {clip.stop();}}
	
	public void close() {
		if (clip != null)
			clip.close();
		if (stream != null)
			try {
				stream.close();
			} catch (IOException exc) {
				exc.printStackTrace();
			}
	}

	public void join() {
		if (!released) return;
		synchronized(clip) {
			try {
				while (playing)
					clip.wait();
			} catch (InterruptedException exc) {
				exc.printStackTrace();
			}
		}
	}
	
	public static Sound playSound(URL path) {
        try{
        Sound snd = new Sound(path);
        snd.play();
        return snd;}catch (Exception e) {
        e.printStackTrace();
        }
        return null;
    }
	
	private class Listener implements LineListener {
		public void update(LineEvent ev) {
			if (ev.getType() == LineEvent.Type.STOP) {
				playing = false;
				synchronized(clip) {
					clip.notify();
				}
			}
		}
	}
}
