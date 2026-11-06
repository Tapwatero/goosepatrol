package team2.eng1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundSystem {

    public static Sound footsteps;
    private static long id;
    private static boolean isPlaying;

    public void create() {

		// Load the walking sounds
		footsteps = Gdx.audio.newSound(Gdx.files.internal("sounds/player_footstep_loop.ogg"));

	}

    public static void footstep(boolean active) {
        if (active) {
            if(!isPlaying) {
                id = footsteps.play(1);
                footsteps.setLooping(id, true);
                isPlaying = true;
            }
        } else {
            footsteps.stop(id);
            isPlaying = false;
        }
    }

	public void dispose() {
		footsteps.dispose();
	}

}
