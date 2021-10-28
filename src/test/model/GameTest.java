package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.GameGenre.*;
import static model.PlayStatus.*;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private Game compGame;
    private Game currGame;
    private Game onHoldGame;
    private Game planToPlayGame;
    private Game fpsGame;
    private Game horrorGame;
    private Game platGame;

    @BeforeEach
    public void setUp() {
        List<String> compGamePlatforms = new ArrayList<>(Arrays.asList("PlayStation 4", "PlayStation 5"));
        compGame = new Game("Marvel's Spider-Man", "Insomniac Games", RPG,
                compGamePlatforms, 2018, COMPLETED);
        List<String> currGamePlatforms = new ArrayList<>(Arrays.asList("Nintendo Switch", "macOS"));
        currGame = new Game("Spiritfarer", "Thunder Lotus Games", ADVENTURE,
                currGamePlatforms, 2020, CURRENTLY_PLAYING);
        List<String> onHoldGamePlatforms = new ArrayList<>(Arrays.asList("PlayStation 4", "macOS", "Microsoft Windows"));
        onHoldGame = new Game("The Witness", "Thekla Inc.", PUZZLE,
                onHoldGamePlatforms, 2016, ON_HOLD);
        List<String> planToPlayGamePlatforms = new ArrayList<>(Arrays.asList("Nintendo Switch", "Microsoft Windows"));
        planToPlayGame = new Game("Slime Rancher", "Monomi Park", SIMULATION,
                planToPlayGamePlatforms, 2016, PLAN_TO_PLAY);

        List<String> fpsPlatform = new ArrayList<>(Arrays.asList("Microsoft Windows", "PlayStation 4"));
        fpsGame = new Game("Valorant", "Riot Games", FPS,
                fpsPlatform,2019, CURRENTLY_PLAYING);
        List<String> horrorPlatform = new ArrayList<>(Arrays.asList("Microsoft Windows", "macOS"));
        horrorGame = new Game("Phasmaphobia", "Kinetic Games", HORROR,
                horrorPlatform,2019, ON_HOLD);
        List<String> platPlatform = new ArrayList<>(Arrays.asList("macOS", "Microsoft Windows"));
        platGame = new Game("Getting Over It", "Bennett Foddy", PLATFORMER,
                platPlatform,2019, PLAN_TO_PLAY);
    }

    // getters

    @Test
    public void testGetTitle() {
        assertEquals("Marvel's Spider-Man", compGame.getTitle());
    }

    @Test
    public void testGetDeveloper() {
        assertEquals("Insomniac Games", compGame.getDeveloper());
    }

    @Test
    public void testGetGenre() {
        assertEquals(RPG, compGame.getGenre());
    }

    @Test
    public void testGetYear() {
        assertEquals(2018, compGame.getYear());
    }

    @Test
    public void testGetPlatform() {
        assertEquals(Arrays.asList("PlayStation 4", "PlayStation 5"), compGame.getPlatform());
    }

    @Test
    public void testGetPlayStatus() {
        assertEquals(COMPLETED, compGame.getPlayStatus());
    }

    // Changing play status
    @Test
    public void testSetPlayStatusToCompleted() {
        assertEquals(CURRENTLY_PLAYING, currGame.getPlayStatus());
        currGame.setPlayStatus("completed");
        assertEquals(COMPLETED, currGame.getPlayStatus());
    }

    @Test
    public void testSetPlayStatusToCurrentlyPlaying() {
        assertEquals(PLAN_TO_PLAY, planToPlayGame.getPlayStatus());
        planToPlayGame.setPlayStatus("currently playing");
        assertEquals(CURRENTLY_PLAYING, planToPlayGame.getPlayStatus());
    }

    @Test
    public void testSetPlayStatusOnHold() {
        assertEquals(CURRENTLY_PLAYING, currGame.getPlayStatus());

        currGame.setPlayStatus("on hold");
        assertEquals(ON_HOLD, currGame.getPlayStatus());
    }

    @Test
    public void testSetPlayStatusPlanToPlay() {
        assertEquals(COMPLETED, compGame.getPlayStatus());
        compGame.setPlayStatus("plan to play");
        assertEquals(PLAN_TO_PLAY, compGame.getPlayStatus());
    }

    // Convert Game Genre to String
    @Test
    public void testStringGenreFPS() {
        assertEquals("FPS", fpsGame.stringGenre());
    }

    @Test
    public void testStringGenreRPG() {
        assertEquals("RPG", compGame.stringGenre());
    }

    @Test
    public void testStringGenreAdventure() {
        assertEquals("Adventure", currGame.stringGenre());
    }

    @Test
    public void testStringGenrePuzzle() {
        assertEquals("Puzzle", onHoldGame.stringGenre());
    }

    @Test
    public void testStringGenreHorror() {
        assertEquals("Horror", horrorGame.stringGenre());
    }

    @Test
    public void testStringGenreSimulation() {
        assertEquals("Simulation", planToPlayGame.stringGenre());
    }

    @Test
    public void testStringGenrePlatformer() {
        assertEquals("Platformer", platGame.stringGenre());
    }

    // Convert Play Status to String
    @Test
    public void testStringPlayStatusCompleted() {
        assertEquals("Completed", compGame.stringPlayStatus());
    }

    @Test
    public void testStringPlayStatusCurrentlyPlaying() {
        assertEquals("Currently playing", currGame.stringPlayStatus());
    }

    @Test
    public void testStringPlayStatusOnHold() {
        assertEquals("On hold", onHoldGame.stringPlayStatus());
    }

    @Test
    public void testStringPlayStatusPlanToPlay() {
        assertEquals("Plan to play", planToPlayGame.stringPlayStatus());
    }

    @Test
    public void testStringPlayStatusReachesNull() {
        assertEquals("Plan to play", planToPlayGame.stringPlayStatus());
    }

    // Viewing game details
    @Test
    public void testGetGameDetail() {
        String expected = "Title: " + "The Witness" + "\n" +
                "Developed by: " + "Thekla Inc." + "\n" +
                "Genre: " + "Puzzle" + "\n" +
                "Platform(s): " + "PlayStation 4, macOS, Microsoft Windows" + "\n" +
                "Released in: " + "2016" + "\n" +
                "Playing Status: " + "On hold";
        assertEquals(expected, onHoldGame.getGameDetails());
    }
}