package team2.eng1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import java.util.ArrayList;
import java.util.Arrays;

public class SoundSystem {

    public static Sound playerFootstep1;
    public static Sound playerFootstep2;
    public static Sound playerFootstep3;
    public static Sound playerFootstep4;
    public static Sound playerFootstep5;
    public static Sound playerFootstep6;
    public static Sound busMove;
    public static Sound busSkid;
    public static Sound gooseEat;
    public static Sound gooseHiss1;
    public static Sound gooseHiss2;
    public static Sound gooseHiss3;
    public static Sound gooseHiss4;
    public static Sound gooseHiss5;
    public static Sound gooseHonk1;
    public static Sound gooseHonk2;
    public static Sound gooseHonk3;
    public static Sound gooseHonk4;
    public static Sound gooseHonk5;
    public static Sound gooseHonk6;
    public static Sound gooseHonk7;
    public static Sound gooseHonk8;
    public static Sound gooseHonk9;
    public static Sound gooseHonk10;
    public static Sound gooseWing1;
    public static Sound gooseWing2;
    public static Sound peashooterShoot1;
    public static Sound peashooterShoot2;
    public static Sound peashooterSplat1;
    public static Sound pineappleArmed;
    public static Sound pineappleExplode;
    public static Music gooseHorde1;
    public static Music gooseHorde2;
    public static Music gooseHorde3;
    public static ArrayList<Sound> playerFootstep;
    public static ArrayList<Sound> gooseHiss;
    public static ArrayList<Sound> gooseHonk;
    public static ArrayList<Sound> gooseWing;
    public static ArrayList<Sound> peashooterShoot;

    public static int currentlyPlaying = 0;

    public void create() {

		// Load all currently used sounds
        // Player sounds are collected here
		playerFootstep1 = Gdx.audio.newSound(Gdx.files.internal("sounds/player_footstep_1.ogg"));
        playerFootstep2 = Gdx.audio.newSound(Gdx.files.internal("sounds/player_footstep_2.ogg"));
        playerFootstep3 = Gdx.audio.newSound(Gdx.files.internal("sounds/player_footstep_3.ogg"));
        playerFootstep4 = Gdx.audio.newSound(Gdx.files.internal("sounds/player_footstep_4.ogg"));
        playerFootstep5 = Gdx.audio.newSound(Gdx.files.internal("sounds/player_footstep_5.ogg"));
        playerFootstep6 = Gdx.audio.newSound(Gdx.files.internal("sounds/player_footstep_6.ogg"));

        // Bus sounds are collected here
        busMove = Gdx.audio.newSound(Gdx.files.internal("sounds/bus_movement_1.ogg"));
        busSkid = Gdx.audio.newSound(Gdx.files.internal("sounds/bus_skid_1.ogg"));

        // Goose sounds are collected here
        gooseEat = Gdx.audio.newSound(Gdx.files.internal("sounds/goose_eat_1.ogg"));
        gooseHiss1 = Gdx.audio.newSound(Gdx.files.internal("sounds/goose_hiss_1.ogg"));
        gooseHiss2 = Gdx.audio.newSound(Gdx.files.internal("sounds/goose_hiss_2.ogg"));
        gooseHiss3 = Gdx.audio.newSound(Gdx.files.internal("sounds/goose_hiss_3.ogg"));
        gooseHiss4 = Gdx.audio.newSound(Gdx.files.internal("sounds/goose_hiss_4.ogg"));
        gooseHiss5 = Gdx.audio.newSound(Gdx.files.internal("sounds/goose_hiss_5.ogg"));
        gooseHonk1 = Gdx.audio.newSound(Gdx.files.internal("sounds/goose_honk_1.ogg"));
        gooseHonk2 = Gdx.audio.newSound(Gdx.files.internal("sounds/goose_honk_2.ogg"));
        gooseHonk3 = Gdx.audio.newSound(Gdx.files.internal("sounds/goose_honk_3.ogg"));
        gooseHonk4 = Gdx.audio.newSound(Gdx.files.internal("sounds/goose_honk_4.ogg"));
        gooseHonk5 = Gdx.audio.newSound(Gdx.files.internal("sounds/goose_honk_5.ogg"));
        gooseHonk6 = Gdx.audio.newSound(Gdx.files.internal("sounds/goose_honk_6.ogg"));
        gooseHonk7 = Gdx.audio.newSound(Gdx.files.internal("sounds/goose_honk_7.ogg"));
        gooseHonk8 = Gdx.audio.newSound(Gdx.files.internal("sounds/goose_honk_8.ogg"));
        gooseHonk9 = Gdx.audio.newSound(Gdx.files.internal("sounds/goose_honk_9.ogg"));
        gooseHonk10 = Gdx.audio.newSound(Gdx.files.internal("sounds/goose_honk_10.ogg"));
        gooseWing1 = Gdx.audio.newSound(Gdx.files.internal("sounds/goose_wing_1.ogg"));
        gooseWing2 = Gdx.audio.newSound(Gdx.files.internal("sounds/goose_wing_2.ogg"));

        // Peashooter sounds are collected here
        peashooterShoot1 = Gdx.audio.newSound(Gdx.files.internal("sounds/item_peashooter_shoot_1.ogg"));
        peashooterShoot2 = Gdx.audio.newSound(Gdx.files.internal("sounds/item_peashooter_shoot_2.ogg"));
        peashooterSplat1 = Gdx.audio.newSound(Gdx.files.internal("sounds/item_peashooter_splat_1.ogg"));

        // Pineapple Grenade sounds are collected here
        pineappleArmed = Gdx.audio.newSound(Gdx.files.internal("sounds/item_pineapple_armed_1.ogg"));
        pineappleExplode = Gdx.audio.newSound(Gdx.files.internal("sounds/item_pineapple_explode_1.ogg"));

        // Initialise groups of sounds for randomisation
        playerFootstep = new ArrayList<Sound>(
            Arrays.asList(playerFootstep1, playerFootstep2, playerFootstep3, playerFootstep4, playerFootstep5, playerFootstep6)
        );
        gooseHiss = new ArrayList<Sound>(Arrays.asList(gooseHiss1, gooseHiss2, gooseHiss3, gooseHiss4, gooseHiss5));
        gooseHonk = new ArrayList<Sound>(
            Arrays.asList(gooseHonk1, gooseHonk2, gooseHonk3, gooseHonk4, gooseHonk5, gooseHonk6, gooseHonk7, gooseHonk8, gooseHonk9, gooseHonk10)
        );
        gooseWing = new ArrayList<Sound>(Arrays.asList(gooseWing1, gooseWing2));
        peashooterShoot = new ArrayList<Sound>(Arrays.asList(peashooterShoot1, peashooterShoot2));

        // Load all currently used music
        gooseHorde1 = Gdx.audio.newMusic(Gdx.files.internal("sounds/music/goose_horde_1.ogg"));
        gooseHorde2 = Gdx.audio.newMusic(Gdx.files.internal("sounds/music/goose_horde_2.ogg"));
        gooseHorde3 = Gdx.audio.newMusic(Gdx.files.internal("sounds/music/goose_horde_3.ogg"));

        // Quieten the geese horde noises to prevent player annoyance
        gooseHorde1.setVolume(0.1f);
        gooseHorde2.setVolume(0.1f);
        gooseHorde3.setVolume(0.1f);

        // To allow the geese horde "music" to randomise, we need to be able to tell when they end
        gooseHorde1.setOnCompletionListener(new Music.OnCompletionListener() {
            @Override
            public void onCompletion(Music music) {  
                int random = (int) (Math.random() * 2) + 1;
                switch (random) {
                    case 1:
                        gooseHorde2.play();
                        currentlyPlaying = 2;
                        break;
                    case 2:
                        gooseHorde3.play();
                        currentlyPlaying = 3;
                        break;
                } 

            }
        });
        gooseHorde2.setOnCompletionListener(new Music.OnCompletionListener() {
            @Override
            public void onCompletion(Music music) {  
                int random = (int) (Math.random() * 2) + 1;
                switch (random) {
                    case 1:
                        gooseHorde1.play();
                        currentlyPlaying = 1;
                        break;
                    case 2:
                        gooseHorde3.play();
                        currentlyPlaying = 3;
                        break;
                } 

            }
        });
        gooseHorde3.setOnCompletionListener(new Music.OnCompletionListener() {
            @Override
            public void onCompletion(Music music) {  
                int random = (int) (Math.random() * 2) + 1;
                switch (random) {
                    case 1:
                        gooseHorde1.play();
                        break;
                    case 2:
                        gooseHorde2.play();
                        break;
                } 

            }
        });
	}

    public static void playSound(Sound sound, float volume, float pitch) {
        long id = sound.play(volume);
        sound.setPitch(id, pitch);
    }

    public static void playSoundRandom(ArrayList<Sound> sounds, float volume, float pitch) {
        int rand = (int) (Math.random() * sounds.size());
        Sound sound = sounds.get(rand);
        long id = sound.play(volume);
        sound.setPitch(id, pitch);
    }

    public static void playMusic(Music music, float volume) {
        music.play();
        music.setLooping(true);
        music.setVolume(volume);
    }

    // For the geese horde, currently plays at all times but could be changed to only when there are geese on screen.
    // I should probably add fading to the end of each sound to make it smoother, or alternatively just make it wait before playing the next sound.
    // This is not implemented yet, nor is it a need to have feature.
    public static void geeseHorde(boolean active) {
        if (active) {
            switch (currentlyPlaying) {
                case 0:
                    int random = (int) (Math.random() * 3) + 1;
                    switch (random) {
                        case 1:
                            gooseHorde1.play();
                            currentlyPlaying = 1;
                            break;
                        case 2:
                            gooseHorde2.play();
                            currentlyPlaying = 2;
                            break;
                        case 3:
                            gooseHorde3.play();
                            currentlyPlaying = 3;
                            break;
                    }
                    break;
                case 1:
                    gooseHorde1.play();
                    break;
                case 2:
                    gooseHorde2.play();
                    break;
                case 3:
                    gooseHorde3.play();
                    break;
            }
        } else {
            if (gooseHorde1.isPlaying()) {
                gooseHorde1.pause();
            } else if (gooseHorde2.isPlaying()) {
                gooseHorde2.pause();
            } else if (gooseHorde3.isPlaying()) {
                gooseHorde3.pause();
            }
        }
    }

	public void dispose() {
		playerFootstep1.dispose();
        playerFootstep2.dispose();
        playerFootstep3.dispose();
        playerFootstep4.dispose();
        playerFootstep5.dispose();
        playerFootstep6.dispose();
        busMove.dispose();
        busSkid.dispose();
        gooseEat.dispose();
        gooseHiss1.dispose();
        gooseHiss2.dispose();
        gooseHiss3.dispose();
        gooseHiss4.dispose();
        gooseHiss5.dispose();
        gooseHonk1.dispose();
        gooseHonk2.dispose();
        gooseHonk3.dispose();
        gooseHonk4.dispose();
        gooseHonk5.dispose();
        gooseHonk6.dispose();
        gooseHonk7.dispose();
        gooseHonk8.dispose();
        gooseHonk9.dispose();
        gooseHonk10.dispose();
        gooseWing1.dispose();
        gooseWing2.dispose();
        peashooterShoot1.dispose();
        peashooterShoot2.dispose();
        peashooterSplat1.dispose();
        pineappleArmed.dispose();
        pineappleExplode.dispose();
        gooseHorde1.dispose();
        gooseHorde2.dispose();
        gooseHorde3.dispose();
	}

}
