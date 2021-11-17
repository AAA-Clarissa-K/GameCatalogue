package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.List;

import static model.GameGenre.*;
import static model.PlayStatus.*;

//  Each game should hold information about its title (String), developer (String), genre (GameGenre), platforms it
//  can be played on (List<String>), its year of release (int), the user's playing status on the game (PlayStatus),
public class Game implements Writable {
    private String title;
    private String developer;
    private GameGenre genre;
    private List<String> platform;
    private int year;
    private PlayStatus playStatus;

    // constructs a game
    public Game(String title, String dev, GameGenre genre, List<String> platform, int year, PlayStatus playStatus) {
        this.title = title;
        developer = dev;
        this.genre = genre;
        this.platform = platform;
        this.year = year;
        this.playStatus = playStatus;
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

    public int getYear() {
        return year;
    }

    public List<String> getPlatform() {
        return platform;
    }

    public PlayStatus getPlayStatus() {
        return playStatus;
    }

    // REQUIRES: string input is lowercase, one of: "completed", "currently playing", "on hold", "plan to play"
    // MODIFIES: this
    // EFFECTS: changes a game's playing status to another playing status based on its assigned string
    public void setPlayStatus(String ps) {
        if (ps.equals("completed")) {
            playStatus = COMPLETED;
        } else if (ps.equals("currently playing")) {
            playStatus = CURRENTLY_PLAYING;
        } else if (ps.equals("on hold")) {
            playStatus = ON_HOLD;
        } else if (ps.equals("plan to play")) {
            playStatus = PLAN_TO_PLAY;  // last possible string input given requires restriction
        }
    }

    // Converts enumeration to string
    // REQUIRES: inputs a valid GameGenre enumeration
    // EFFECTS: returns string of the same name to the genre enumeration
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
        } else {
            return "Platformer";        // final enumeration possible given requires restriction
        }
    }

    // REQUIRES: inputs a valid PlayStatus enumeration
    // EFFECT: returns string of the same name to the playing status enumeration
    public String stringPlayStatus() {
        if (playStatus.equals(COMPLETED)) {
            return "Completed";
        } else if (playStatus.equals(CURRENTLY_PLAYING)) {
            return "Currently playing";
        } else if (playStatus.equals(ON_HOLD)) {
            return "On hold";
        } else {
            return "Plan to play";      // final enumeration possible given requires restriction
        }
    }

    // EFFECT: returns string of game details: title, developer name, genre, platform(s), release year and play status
    public String getGameDetails() {
        return "Title: " + getTitle() + "\n"
                + "Developed by: " + getDeveloper() + "\n"
                + "Genre: " + stringGenre() + "\n"
                + "Platform(s): " + String.join(", ", platform) + "\n"
                + "Released in: " + year + "\n"
                + "Playing Status: " + stringPlayStatus();
    }

    // EFFECTS: converts the game into a JSONObject
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("developer", developer);
        json.put("genre", genre);
        json.put("platform", String.join(", ", platform));
        json.put("year", year);
        json.put("play status", playStatus);
        return json;
    }
}
