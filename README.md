# A Game Catalogue

## Application Details

The intended application is for gamers, or people who generally wish to organise their games.  This project is of 
interest to me because I like to keep track of what I've watched and read, and I wanted to organise my games in a 
similar way.  I like playing many video games, but I haven't seen a page or platform that allows you to organise 
both console and PC games like other websites that organise your movies, TV shows or books.

The application I'm making should allow the user to navigate on a simple digital catalogue for games;  Each game holds
basic details about it, such as its: title, genre(s), developer(s), platforms, publishing year and the user's gameplay 
status on that game.  I hope for the user to be able to view games through different filters, such as:
- All games
- Completed
- Currently playing
- Games on hold
- Plan to play

## User Stories

For my game catalogue:
- As a user, I want to be able to add multiple games to the catalogue's list of games
- As a user, I want to be able to view the game titles in the catalogue
- As a user, I want to be able to view game titles based on their playing status, and genre
- As a user, I want to be able to search for a game title and view the details of the game
- As a user, I want to be able to change the playing status of the game


- As a user, I want to be able to save my games to file
- As a user, I want to be able to load my games from file

(Code for saving and loading files referenced from JSON libraries)

## Phase 4: Task 2
### Event Log Printing Sample (Annotated)
<b><i>Starting application, creating a game catalogue under "Clank":</b></i><br>
Mon Nov 22 20:36:42 PST 2021<br> Game catalogue under Clank was made. <br><br>
<b><i>Loading previously saved data from "Kris":</b></i><br>
Mon Nov 22 20:36:46 PST 2021<br> Game catalogue under Kris was made. <br>
Mon Nov 22 20:36:46 PST 2021<br> Game titled Spiderman was created. <br>
Mon Nov 22 20:36:46 PST 2021<br> Spiderman added to your catalogue. <br>
Mon Nov 22 20:36:46 PST 2021<br> Game titled Cuphead was created. <br>
Mon Nov 22 20:36:46 PST 2021<br> Cuphead added to your catalogue. <br>
Mon Nov 22 20:36:46 PST 2021<br> Game titled Spiritfarer was created. <br>
Mon Nov 22 20:36:46 PST 2021<br> Spiritfarer added to your catalogue. <br>
Mon Nov 22 20:36:46 PST 2021<br> Updated Clank's game catalogue name with Kris's games. <br><br>
<b><i>Viewing all games:</b></i><br>
Mon Nov 22 20:36:48 PST 2021<br> Retrieved all games on Clank's game catalogue. <br>
Mon Nov 22 20:36:48 PST 2021<br> Retrieved all games on Clank's game catalogue.* <br><br>
<b><i>Searching for the game "Spiderman":</b></i><br>
Mon Nov 22 20:36:54 PST 2021<br> Retrieved all games on Clank's game catalogue. <br>
Mon Nov 22 20:36:56 PST 2021<br> Found the game called Spiderman <br><br>
<b><i>Filtering search by genre "Platformer":</b></i><br>
Mon Nov 22 20:37:00 PST 2021<br> Retrieved all games on Clank's game catalogue. <br>
Mon Nov 22 20:37:05 PST 2021<br> Filtered to games of genre: platformer. <br><br>
<b><i>Filtering search by play status "Currently playing":</b></i><br>
Mon Nov 22 20:37:08 PST 2021<br> Retrieved all games on Clank's game catalogue. <br>
Mon Nov 22 20:37:10 PST 2021<br> Filtered to games of play status: currently playing. <br><br>
<b><i>Adding the game "Pokemon Diamond":</b></i><br>
Mon Nov 22 20:38:34 PST 2021<br> Game titled Pokemon Diamond was created. <br>
Mon Nov 22 20:38:34 PST 2021<br> Pokemon Diamond added to your catalogue. <br><br>
<b><i>Changin the play status of a game to "On hold":</b></i><br>
Mon Nov 22 20:38:41 PST 2021<br> Retrieved all games on Clank's game catalogue. <br>
Mon Nov 22 20:39:09 PST 2021<br> Found the game called Spiritfarer <br>
Mon Nov 22 20:39:10 PST 2021<br> Spiritfarer's status changed to ON HOLD <br><br>
<b><i>Saving games to the file:</b></i><br>
Mon Nov 22 20:39:17 PST 2021<br> Saving games... <br><br>
<b><i>Exiting the program prints the Event Log above before clearing the log.</b></i><br>

<b>Notes:</b><br>
*Called twice to initially check if null, and then to produce value if not null<br>
## Phase 4: Task 3
I wrote the UI as one class for this project: 
- Would like to try refactoring separate frames and their buttons as separate classes so components are clearer, and 
easier to find
- Perhaps creating a helper method that would help create JFrames into the format my program looks, so I don't have to
make many calls to do the same thing