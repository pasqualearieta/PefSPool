package igpe.billiard.sound;

import jaco.mp3.player.MP3Player;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundProvider {

	private int indexOfCurrentMusic = 0;
	private boolean musicOn;
	private static Clip mediumCollision;
	private static Clip pocket;
	private static Clip strongCollision;
	private static Clip weakCollision;
	private static Vector<MP3Player> backgroundMusic;

	public static Vector<MP3Player> getBackgroundMusic() {
		return backgroundMusic;
	}

	public static void setBackgroundMusic(Vector<MP3Player> backgroundMusic) {
		SoundProvider.backgroundMusic = backgroundMusic;
	}

	static {
		try {

			backgroundMusic = new Vector<MP3Player>();
			backgroundMusic.add(0, new MP3Player(new File("resources/sound/background/Clash.mp3")));
			backgroundMusic.add(1, new MP3Player(new File("resources/sound/background/Virus.mp3")));
			backgroundMusic.add(2, new MP3Player(new File("resources/sound/background/CUBA.mp3")));
			backgroundMusic.add(3, new MP3Player(new File("resources/sound/background/BackgroundMusic.mp3")));
			backgroundMusic.add(4, new MP3Player(new File("resources/sound/background/Blame.mp3")));
			backgroundMusic.add(5, new MP3Player(new File("resources/sound/background/Dangerous.mp3")));
			backgroundMusic.add(6, new MP3Player(new File("resources/sound/background/PlayHard.mp3")));
			backgroundMusic.add(7, new MP3Player(new File("resources/sound/background/Titanium.mp3")));
			backgroundMusic.add(8, new MP3Player(new File("resources/sound/background/Tremor.mp3")));
			backgroundMusic.add(9, new MP3Player(new File("resources/sound/background/Backlash.mp3")));
			backgroundMusic.add(10, new MP3Player(new File("resources/sound/background/Proxy.mp3")));

			AudioInputStream pocketed = AudioSystem.getAudioInputStream(
					Thread.currentThread().getContextClassLoader().getResource("Sounds/Imbucata.wav"));
			pocket = AudioSystem.getClip();
			pocket.open(pocketed);

			AudioInputStream weak_Collision = AudioSystem.getAudioInputStream(
					Thread.currentThread().getContextClassLoader().getResource("Sounds/CollisioneDebole.wav"));
			weakCollision = AudioSystem.getClip();
			weakCollision.open(weak_Collision);

			AudioInputStream medium_Collision = AudioSystem.getAudioInputStream(
					Thread.currentThread().getContextClassLoader().getResource("Sounds/CollisioneMedia.wav"));
			mediumCollision = AudioSystem.getClip();
			mediumCollision.open(medium_Collision);

			AudioInputStream strong_Collision = AudioSystem.getAudioInputStream(
					Thread.currentThread().getContextClassLoader().getResource("Sounds/CollisioneForte.wav"));
			strongCollision = AudioSystem.getClip();
			strongCollision.open(strong_Collision);

		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			// do nothing
		}
	}

	public static Clip getCollisioneForte() {
		return strongCollision;
	}

	public static Clip getCollisioneMedia() {
		return mediumCollision;
	}

	public static Clip getCollisionePiccola() {
		return weakCollision;
	}

	public static Clip getImbucata() {
		return pocket;
	}

	public void activeMusic(final boolean isMusicOn) {
		musicOn = isMusicOn;
		for (int i = 0; i < backgroundMusic.size(); i++) {
			if (i != indexOfCurrentMusic)
				backgroundMusic.get(i).stop();
		}
		if (musicOn)
			backgroundMusic.get(indexOfCurrentMusic).play();
	}

	public void stopMusic() {
		backgroundMusic.get(indexOfCurrentMusic).stop();
	}

	public void pauseMusic() {
		backgroundMusic.get(indexOfCurrentMusic).pause();
	}

	public void nextMusic() {
		if (indexOfCurrentMusic + 1 == backgroundMusic.size()) {
			indexOfCurrentMusic = 0;
			activeMusic(musicOn);
		} else {
			indexOfCurrentMusic = indexOfCurrentMusic + 1;
			activeMusic(musicOn);
		}
	}

	public void previusMusic() {
		if (indexOfCurrentMusic == 0) {
			indexOfCurrentMusic = backgroundMusic.size() - 1;
			activeMusic(musicOn);
		} else {
			indexOfCurrentMusic = indexOfCurrentMusic - 1;
			activeMusic(musicOn);
		}
	}
}
