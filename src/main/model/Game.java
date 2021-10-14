package model;

import java.util.List;

import static model.GameGenre.*;
import static model.PlayStatus.*;

public class Game {
    //  Each game should hold information about its title (String), developer (String), genre (GameGenre), platforms it
    //  can be played on (List<String>), its year of release (int), the user's playing status on the game (PlayStatus),
    //  and whether this game is one of the user's favourites
    private String title;
    private String developer;
    private GameGenre genre;
    private List<String> platform;
    private int year;
    private PlayStatus playStatus;
    private boolean favourite;


    public Game(String title, String dev, GameGenre genre, List<String> platform, int year, PlayStatus playStatus) {
        this.title = title;
        developer = dev;
        this.genre = genre;
        this.platform = platform;
        this.year = year;
        this.playStatus = playStatus;
        favourite = false;
    }

    // getters
    public String getTitle() {
        return title;
    }

    public String getDeveloper() {
        return developer;
    }

    public GameGenre getGenre() {
        return genre;
    }

    public List<String> getPlatform() {
        return platform;
    }

    public int getYear() {
        return year;
    }

    public PlayStatus getPlayStatus() {
        return playStatus;
    }

    public boolean isFavourite() {
        return favourite;
    }

    // EFFECT: sets a playing status by its assigned string
    public void setPlayStatus(String ps) {
        if (ps.equals("Completed")) {
            playStatus = COMPLETED;
        } else if (ps.equals("Currently playing")) {
            playStatus = CURRENTLY_PLAYING;
        } else if (ps.equals("On hold")) {
            playStatus = ON_HOLD;
        } else if (ps.equals("Plan to play")) {
            playStatus = PLAN_TO_PLAY;
        }
    }

    // EFFECT: changes whether game is in favourites
    public void setFavourite(boolean b) {
        favourite = b;
    }

    // Change to strings

    // EFFECT: returns string of genre enumeration
    public String stringGenre() {
        if (genre.equals(FPS)) {
            return "FPS";
        } else if (genre.equals(RPG)) {
            return "RPG";
        } else if (genre.equals(ADVENTURE)) {
            return "Adventure";
        } else if (genre.equals(PUZZLE)) {
            return "Puzzle";
        } else if (genre.equals(HORROR)) {
            return "Horror";
        } else if (genre.equals(SIMULATION)) {
            return "Simulation";
        } else if (genre.equals(PLATFORMER)) {
            return "Platformer";
        }
        return "No genres assigned";
    }

    // EFFECT: returns string of playing status enumeration
    public String stringPlayStatus() {
        if (playStatus.equals(COMPLETED)) {
            return "Completed";
        } else if (playStatus.equals(CURRENTLY_PLAYING)) {
            return "Currently playing";
        } else if (playStatus.equals(ON_HOLD)) {
            return "On hold";
        } else if (playStatus.equals(PLAN_TO_PLAY)) {
            return "Plan to play";
        }
        return "No play status assigned";
    }

    // EFFECT: returns string of game details
    public String getGameDetails() {
        return "Title: " + getTitle() + "\n"
                + "Developed by: " + getDeveloper() + "\n"
                + "Genre(s):" + stringGenre() + "\n"
                + "Platform(s): " + String.join(", ", platform) + "\n"
                + "Released in: " + year + "\n"
                + "Playing Status: " + stringPlayStatus();
    }
}
