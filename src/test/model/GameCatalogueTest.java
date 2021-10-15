package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.GameGenre.*;
import static model.PlayStatus.*;
import static org.junit.jupiter.api.Assertions.*;

public class GameCatalogueTest {
    private GameCatalogue myGameCatalogue;
    private Game compGame;
    private Game currGame;
    private Game onHoldGame;
    private Game planToPlayGame;
    private Game fpsGame;
    private Game horrorGame;
    private Game platGame;

    @BeforeEach
    public void setUp() {
        myGameCatalogue = new GameCatalogue("Clarissa");
        List<String> compGamePlatforms = new ArrayList<>(Arrays.asList("PlayStation 4", "PlayStation 5"));
        compGame = new Game("Marvel's Spider-Man", "Insomniac Games", RPG,
                compGamePlatforms, 2018, COMPLETED);
        List<String> currGamePlatforms = new ArrayList<>(Arrays.asList("Nintendo Switch", "macOS"));
        currGame = new Game("Spiritfarer", "Thunder Lotus Games", ADVENTURE,
                currGamePlatforms, 2020, CURRENTLY_PLAYING);
        List<String> onHoldGamePlatforms = new ArrayList<>(Arrays.asList("PlayStation 4", "macOS",
                "Microsoft Windows"));
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
    public void testGetUsername() {
        assertEquals("Clarissa", myGameCatalogue.getUsername());
    }

    @Test
    public void testGetGameNull() {
        assertNull(myGameCatalogue.getGame("Spiritfarer"));
    }

    @Test
    public void testGetGameIsFound() {
        myGameCatalogue.addGame(currGame);

        assertEquals(currGame, myGameCatalogue.getGame("Spiritfarer"));
    }

    // Adding a game
    @Test
    public void testAddGameToEmpty() {
        myGameCatalogue.addGame(currGame);

        List<Game> gamesList = myGameCatalogue.getAllGames();

        assertEquals(1, gamesList.size());
        assertTrue(gamesList.contains(currGame));
    }

    @Test
    public void testAddGameToMultiple() {
        myGameCatalogue.addGame(currGame);
        myGameCatalogue.addGame(compGame);
        myGameCatalogue.addGame(onHoldGame);

        List<Game> gamesList = myGameCatalogue.getAllGames();

        assertEquals(3, gamesList.size());
        assertTrue(gamesList.contains(currGame));
        assertTrue(gamesList.contains(compGame));
        assertTrue(gamesList.contains(onHoldGame));
    }

    // retrieving all the games
    @Test
    public void testGetAllGamesEmpty() {
        List<Game> gamesList = myGameCatalogue.getAllGames();

        assertEquals(0, gamesList.size());
    }

    @Test
    public void testGetAllGamesSome() {
        myGameCatalogue.addGame(currGame);
        myGameCatalogue.addGame(compGame);

        List<Game> gamesList = myGameCatalogue.getAllGames();

        assertEquals(2, gamesList.size());
        assertTrue(gamesList.contains(currGame));
        assertTrue(gamesList.contains(compGame));
    }

    // retrieving game titles
    @Test
    public void testGetAllGameTitlesEmpty() {
        assertEquals("There are no games in your game catalogue.", myGameCatalogue.getAllGameTitles());
    }

    @Test
    public void testGetAllGameTitlesSome() {
        myGameCatalogue.addGame(currGame);
        myGameCatalogue.addGame(compGame);

        assertEquals("Spiritfarer, Marvel's Spider-Man", myGameCatalogue.getAllGameTitles());
    }

    // Searching for a game
    @Test
    public void testSearchGameFound() {
        myGameCatalogue.addGame(horrorGame);
        myGameCatalogue.addGame(compGame);

        assertEquals(horrorGame.getGameDetails(), myGameCatalogue.searchGameDetails("Phasmaphobia"));
    }

    @Test
    public void testSearchGameNotFound() {
        myGameCatalogue.addGame(horrorGame);
        myGameCatalogue.addGame(compGame);

        assertEquals("Game with this title couldn't be found...", myGameCatalogue.searchGameDetails("Spiritfarer"));
    }

    // Assorting by play status
    @Test
    public void testAssortByPlayStatusCompletedOne() {
        myGameCatalogue.addGame(compGame);
        myGameCatalogue.addGame(currGame);
        myGameCatalogue.addGame(onHoldGame);
        myGameCatalogue.addGame(planToPlayGame);

        myGameCatalogue.addGame(fpsGame);
        myGameCatalogue.addGame(horrorGame);
        myGameCatalogue.addGame(platGame);

        String expected = "Marvel's Spider-Man";

        assertEquals(expected, myGameCatalogue.assortByPlayStatus("completed"));
    }

    @Test
    public void testAssortByPlayStatusCurrentlyPlaying() {
        myGameCatalogue.addGame(compGame);
        myGameCatalogue.addGame(currGame);
        myGameCatalogue.addGame(onHoldGame);
        myGameCatalogue.addGame(planToPlayGame);

        myGameCatalogue.addGame(fpsGame);
        myGameCatalogue.addGame(horrorGame);
        myGameCatalogue.addGame(platGame);

        String expected = "Spiritfarer, Valorant";

        assertEquals(expected, myGameCatalogue.assortByPlayStatus("currently playing"));
    }

    @Test
    public void testAssortByPlayStatusOnHold() {
        myGameCatalogue.addGame(compGame);
        myGameCatalogue.addGame(currGame);
        myGameCatalogue.addGame(onHoldGame);
        myGameCatalogue.addGame(planToPlayGame);

        myGameCatalogue.addGame(fpsGame);
        myGameCatalogue.addGame(horrorGame);
        myGameCatalogue.addGame(platGame);

        String expected = "The Witness, Phasmaphobia";

        assertEquals(expected, myGameCatalogue.assortByPlayStatus("on hold"));
    }

    @Test
    public void testAssortByPlayStatusPlanToPlay() {
        myGameCatalogue.addGame(compGame);
        myGameCatalogue.addGame(currGame);
        myGameCatalogue.addGame(onHoldGame);
        myGameCatalogue.addGame(planToPlayGame);

        myGameCatalogue.addGame(fpsGame);
        myGameCatalogue.addGame(horrorGame);
        myGameCatalogue.addGame(platGame);

        String expected = "Slime Rancher, Getting Over It";

        assertEquals(expected, myGameCatalogue.assortByPlayStatus("plan to play"));
    }

    @Test
    public void testAssortByPlayStatusNotFound() {
        myGameCatalogue.addGame(compGame);
        myGameCatalogue.addGame(currGame);
        myGameCatalogue.addGame(onHoldGame);
        myGameCatalogue.addGame(planToPlayGame);

        myGameCatalogue.addGame(fpsGame);
        myGameCatalogue.addGame(horrorGame);
        myGameCatalogue.addGame(platGame);

        assertEquals("You have no games with this play status.",
                myGameCatalogue.assortByPlayStatus("unsure"));
    }

    // Assorting by genres
    @Test
    public void testAssortByGenreFPS() {
        myGameCatalogue.addGame(compGame);

        myGameCatalogue.addGame(fpsGame);
        myGameCatalogue.addGame(horrorGame);
        myGameCatalogue.addGame(platGame);

        assertEquals("Valorant", myGameCatalogue.assortByGenre("fps"));
    }

    @Test
    public void testAssortByGenreRPG() {
        myGameCatalogue.addGame(compGame);
        myGameCatalogue.addGame(fpsGame);
        myGameCatalogue.addGame(horrorGame);
        myGameCatalogue.addGame(platGame);

        assertEquals("Marvel's Spider-Man", myGameCatalogue.assortByGenre("rpg"));
    }

    @Test
    public void testAssortByGenreAdventure() {
        myGameCatalogue.addGame(compGame);
        myGameCatalogue.addGame(currGame);
        myGameCatalogue.addGame(horrorGame);
        myGameCatalogue.addGame(platGame);

        assertEquals("Spiritfarer", myGameCatalogue.assortByGenre("adventure"));
    }

    @Test
    public void testAssortByGenrePuzzle() {
        myGameCatalogue.addGame(compGame);
        myGameCatalogue.addGame(currGame);
        myGameCatalogue.addGame(horrorGame);
        myGameCatalogue.addGame(onHoldGame);

        assertEquals("The Witness", myGameCatalogue.assortByGenre("puzzle"));
    }

    @Test
    public void testAssortByGenreHorror() {
        myGameCatalogue.addGame(compGame);
        myGameCatalogue.addGame(planToPlayGame);
        myGameCatalogue.addGame(horrorGame);
        myGameCatalogue.addGame(platGame);

        assertEquals("Phasmaphobia", myGameCatalogue.assortByGenre("horror"));
    }

    @Test
    public void testAssortByGenreSimulation() {
        myGameCatalogue.addGame(compGame);
        myGameCatalogue.addGame(planToPlayGame);
        myGameCatalogue.addGame(horrorGame);
        myGameCatalogue.addGame(platGame);

        assertEquals("Slime Rancher", myGameCatalogue.assortByGenre("simulation"));
    }

    @Test
    public void testAssortByGenrePlatformer() {
        myGameCatalogue.addGame(compGame);
        myGameCatalogue.addGame(planToPlayGame);
        myGameCatalogue.addGame(horrorGame);
        myGameCatalogue.addGame(platGame);

        assertEquals("Getting Over It", myGameCatalogue.assortByGenre("platformer"));
    }

    @Test
    public void testAssortByGenreNotFound() {
        myGameCatalogue.addGame(compGame);
        myGameCatalogue.addGame(planToPlayGame);
        myGameCatalogue.addGame(horrorGame);
        myGameCatalogue.addGame(platGame);

        assertEquals("You have no games in this genre.", myGameCatalogue.assortByGenre("jrpg"));
    }
}
