package ui;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import static model.GameGenre.*;
import static model.PlayStatus.*;

public class GameCatalogueUI extends JFrame {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 800;
    protected static final Color BACKGROUND_COLOUR = new Color(211, 189, 161);
    protected static final Color FONT_COLOUR = new Color(103, 49, 32);
    protected static final Font MAIN_TITLE_FONT = new Font("SansSerif",Font.BOLD,20);
    protected static final Font TITLE_FONT = new Font("SansSerif",Font.BOLD,15);
    protected static final Font REG_FONT = new Font("SansSerif",Font.PLAIN,15);
    private ImageIcon banner;
    private ImageIcon banner2;
    private ImageIcon gameImage;
    private GameSortingApp gameCatalogueApp;

    // Username prompt
    private JLabel titleLabel;
    private JPanel usernamePanel;
    private JTextField usernameField;
    private JButton submitUsername;

    // Main menu components
    private JLabel viewLabel;
    private JButton viewGames;
    private JButton searchGame;
    private JButton searchGenre;
    private JButton searchStatus;
    private JLabel editGames;
    private JButton addGame;
    private JButton changeStatus;
    private JLabel saveLoadGame;
    private JButton saveGame;
    private JButton loadGame;
    private JLabel exitLabel;
    private JButton exitButton;

    // Game prompt components
    private JFrame addGamePage;
    private JTextField gameTitleField;
    private JTextField gameDevField;
    private JButton genreFpsButton;
    private JButton genreRpgButton;
    private JButton genreAdventureButton;
    private JButton genrePuzzleButton;
    private JButton genreSimulationButton;
    private JButton genreHorrorButton;
    private JButton genrePlatformerButton;
    private GameGenre genreButtonOutput;
    private JTextField gamePlatformField;
    private JTextField gameYearField;
    private JButton statusComplButton;
    private JButton statusCurrButton;
    private JButton statusHoldButton;
    private JButton statusPlanButton;
    private PlayStatus statusButtonOutput;

    // searches
    private Game intendedGame;
    private JPanel gameTitlePanel;
    private JFrame searchGameFrame;
    private JLabel searchLabel;
    private JTextField searchGameField;
    private JButton toggleGameSearch;
    private JFrame inputStatusPage;
    private JFrame changeStatusPage;
    private JButton searchGameForStatus;
    private JButton submitChange;

    // Display game page component
    private JFrame gamePage;

    // EFFECTS: sets up window where Game Catalogue will run
    public GameCatalogueUI() {
        super("Game Catalogue");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(BACKGROUND_COLOUR);

        initComponents();
        setVisible(true);
    }

    protected void initComponents() {
        titleLabel = new FormattedLabel("Welcome to your Game Catalogue",MAIN_TITLE_FONT);
        banner = new ImageIcon("src/Banner.jpg");
        banner2 = new ImageIcon("src/banner2.jpg");
        gameImage = new ImageIcon("src/gameCover.jpg");
        usernamePanel = null;
        usernameField = new JTextField(20);
        gameCatalogueApp = new GameSortingApp(this);
        submitUsername = new UsernameButton();

        initMainMenuComponents();
        initGamePromptComponents();

        gameTitlePanel = new JPanel();
        gameTitlePanel.setLayout(new GridLayout(0,5));

        intendedGame = null;
        searchGameFrame = new JFrame("Search Game");
        searchLabel = new JLabel();
        searchGameFrame.setSize(WIDTH / 2, HEIGHT / 5);
        searchLabel = new FormattedLabel("  What game are you looking for?", TITLE_FONT);
        searchGameFrame.add(searchLabel);
        searchGameField = new JTextField(20);
        toggleGameSearch = new SubmitButton();
        initChangeStatusComponents();
        gamePage = new JFrame();
    }

    private void initChangeStatusComponents() {
        inputStatusPage = new JFrame("Input Play Status");
        inputStatusPage.setSize(WIDTH / 3, HEIGHT / 3);
        inputStatusPage.getContentPane().setBackground(BACKGROUND_COLOUR);
        inputStatusPage.setLayout(new GridLayout(0,1));
        JLabel inputStatusPrompt = new FormattedLabel("What playing status is this game?", TITLE_FONT);
        inputStatusPrompt.setHorizontalAlignment(JLabel.CENTER);
        inputStatusPage.add(inputStatusPrompt);

        changeStatusPage = new JFrame();
        changeStatusPage.setLayout(new GridLayout(0,1));
        JLabel changeStatusPrompt = new FormattedLabel("  Which game would you like to change?", TITLE_FONT);
        changeStatusPage.add(changeStatusPrompt);
        searchGameForStatus = new SubmitButton();
        submitChange = new SubmitButton();
    }

    // EFFECTS: initializes main menu components
    private void initMainMenuComponents() {
        viewLabel = new FormattedLabel("  To view games:", TITLE_FONT);
        viewGames = new MainMenuButton("View all games");
        searchGame = new MainMenuButton("Search for a game");
        searchGenre = new MainMenuButton("Search by genre");
        searchStatus = new MainMenuButton("Search by playing status");
        editGames = new FormattedLabel("  To edit games:",TITLE_FONT);
        addGame = new MainMenuButton("Add a game");
        changeStatus = new MainMenuButton("Change a game's playing status");
        saveLoadGame = new FormattedLabel("  To save or load games:",TITLE_FONT);
        saveGame = new MainMenuButton("Save games to a file");
        loadGame = new MainMenuButton("Load games from a file");
        exitLabel = new FormattedLabel("  To exit game catalogue",TITLE_FONT);
        exitButton = new MainMenuButton("Exit game catalogue");
    }

    // EFFECTS: initializes game prompt components
    private void initGamePromptComponents() {
        addGamePage = new JFrame("Add a Game");
        gameTitleField = new JTextField(20);
        gameDevField = new JTextField(20);
        genreFpsButton = new GenreButton("FPS");
        genreRpgButton = new GenreButton("RPG");
        genreAdventureButton = new GenreButton("Adventure");
        genrePuzzleButton = new GenreButton("Puzzle");
        genreSimulationButton = new GenreButton("Simulation");
        genreHorrorButton = new GenreButton("Horror");
        genrePlatformerButton = new GenreButton("Platformer");
        gamePlatformField = new JTextField(20);
        gameYearField = new JTextField(20);
        genreButtonOutput = null;
        statusComplButton = new StatusButton("Completed");
        statusCurrButton = new StatusButton("Currently playing");
        statusHoldButton = new StatusButton("On hold");
        statusPlanButton = new StatusButton("Plan to play");
        statusButtonOutput = null;
    }

    // EFFECTS: creates a welcome screen for the opening, prompts for username
    public void startTitleScreen() {
        titleLabel.setIcon(banner);
        titleLabel.setHorizontalTextPosition(JLabel.CENTER); // in relation to an image
        titleLabel.setVerticalTextPosition(JLabel.BOTTOM);
//        welcomeLabel.setIconTextGap(0); // sets gaps of text to image
        titleLabel.setHorizontalAlignment(JLabel.CENTER); // in relation to the label
        titleLabel.setVerticalAlignment(JLabel.CENTER);

        usernamePanel = displayAskUsername();
        add(titleLabel, BorderLayout.NORTH);
        add(usernamePanel, BorderLayout.CENTER);
        setVisible(true);
    }

    // EFFECTS: helper to display prompt for username
    public JPanel displayAskUsername() {
        JPanel usernamePrompt = new JPanel();
        usernamePrompt.setBackground(BACKGROUND_COLOUR);
        JLabel usernameLabel = new JLabel("What username would you like?");
        usernameLabel.setForeground(FONT_COLOUR);
        usernamePrompt.add(usernameLabel);
        usernamePrompt.add(usernameField);
        submitUsername = new UsernameButton();
        usernamePrompt.add(submitUsername);
        setVisible(true);

        return usernamePrompt;
    }

    // EFFECTS: displays main menu of game catalogue options to user
    private void displayMainMenu() {
        remove(titleLabel);
        remove(usernamePanel);

        String catalogueName = gameCatalogueApp.gameCatalogue.getUsername() + "'s Game Catalogue";

        titleLabel = new FormattedLabel(catalogueName, MAIN_TITLE_FONT);
        titleLabel.setIcon(banner);
        titleLabel.setHorizontalTextPosition(JLabel.CENTER);
        titleLabel.setVerticalTextPosition(JLabel.BOTTOM);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setVerticalAlignment(JLabel.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        JPanel mainMenu = createMainMenu(new JPanel());
        mainMenu.setBackground(BACKGROUND_COLOUR);
        mainMenu.setLayout(new GridLayout(0,1));

        add(mainMenu,BorderLayout.CENTER);
        setVisible(true);
    }

    private JPanel createMainMenu(JPanel menu) {
        menu.add(viewLabel);
        menu.add(viewGames);
        menu.add(searchGame);
        menu.add(searchGenre);
        menu.add(searchStatus);
        menu.add(editGames);
        menu.add(addGame);
        menu.add(changeStatus);
        menu.add(saveLoadGame);
        menu.add(saveGame);
        menu.add(loadGame);
        menu.add(exitLabel);
        menu.add(exitButton);

        return menu;
    }
    
//    // EFFECTS: returns game details if there is a game with the matching string title,
//    //          if getting game produces null, says game title can't be found
//    private void searchGameDetails() {
//        String targetGame = input.next();
//        if (gameCatalogue.getGame(targetGame) == null) {
//            errorPopup("Game with this title couldn't be found...", "Game Not Found");
//        } else {
//            System.out.println(gameCatalogue.getGame(targetGame).getGameDetails());
//        }
//    }
//
//    // EFFECTS: processes user input to search by genre if the given genre is valid,
//    //          if invalid, prompts for another attempt
//    private void assortValidGenre() {
//        System.out.println("- FPS");
//        System.out.println("- RPG");
//        System.out.println("- Adventure");
//        System.out.println("- Puzzle");
//        System.out.println("- Horror");
//        System.out.println("- Simulation");
//        System.out.println("- Platformer");
//        String givenGenre = input.next().toLowerCase();
//        if (!(givenGenre.equals("fps") || givenGenre.equals("rpg") || givenGenre.equals("adventure")
//                || givenGenre.equals("puzzle") || givenGenre.equals("horror") || givenGenre.equals("simulation")
//                || givenGenre.equals("platformer"))) {
//            System.out.println("Invalid game genre, please try an available genre below.");
//            assortValidGenre();
//        } else {
//            if (gameCatalogue.assortByGenre(givenGenre).isEmpty()) {
//                System.out.println("You have no games in this genre.");
//            } else {
//                System.out.println(gameCatalogue.assortByGenre(givenGenre));
//            }
//        }
//    }

    // Represents the username submit button
    private class UsernameButton extends JButton implements ActionListener {

        UsernameButton() {
            super("submit");
            setBounds(0,0,100,50);
            addActionListener(this);
        }

        public void actionPerformed(ActionEvent e) {
            if (usernameField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter a username",
                        "Empty Username",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                gameCatalogueApp.gameCatalogue = new GameCatalogue(usernameField.getText());
                displayMainMenu();
            }
        }
    }

    // Represents main menu button and functions
    private class MainMenuButton extends JButton implements ActionListener {

        MainMenuButton(String option) {
            super(option);
            addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == viewGames) {
                checkGameTitles(viewGames);
            } else if (e.getSource() == searchGame) {
                checkGameTitles(searchGame);
            } else if (e.getSource() == searchGenre) {
                checkGameTitles(searchGenre);
            } else if (e.getSource() == searchStatus) {
                checkGameTitles(searchStatus);
            } else if (e.getSource() == addGame) {
                addGamePage();
            } else if (e.getSource() == changeStatus) {
                checkGameTitles(changeStatus);
            } else if (e.getSource() == saveGame) {
                saveButton();
            } else if (e.getSource() == loadGame) {
                gameCatalogueApp.loadGameCatalogue(gameCatalogueApp.gameCatalogue.getUsername());
                displayMainMenu();
            } else if (e.getSource() == exitButton) {
                endMessage();
                dispose();
                gameCatalogueApp.exitProgram();
            }
        }

        // EFFECTS: if no games to save, send pop-up message
        //          otherwise, save games to file
        private void saveButton() {
            if (gameCatalogueApp.gameCatalogue.getAllGames().isEmpty()) {
                message("There are no games in your game catalogue to save.",
                        "No Games", "plain");
            } else {
                gameCatalogueApp.saveGameCatalogue();
                displayMainMenu();
            }
        }
    }

    private void createSearchPage(JButton command) {
        searchGameFrame.getContentPane().setBackground(BACKGROUND_COLOUR);
        searchGameFrame.setVisible(true);
        searchGameFrame.setLayout(new GridLayout(0,1));
        if (command.equals(searchGame)) {
            searchGameFrame.add(searchGameField);
            searchGameFrame.add(toggleGameSearch);
        }
    }

    // EFFECTS: creates the change status page
    private void changeStatusPage() {
        changeStatusPage.getContentPane().setBackground(BACKGROUND_COLOUR);
        changeStatusPage.setSize(WIDTH / 2, HEIGHT / 2);
        changeStatusPage.setLayout(new GridLayout(0,1));
        changeStatusPage.add(searchGameField);
        changeStatusPage.add(searchGameForStatus);
        changeStatusPage.setVisible(true);
    }

    // Represents submit game button and functions
    private class SubmitButton extends JButton implements ActionListener {

        SubmitButton() {
            super("submit");
            addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Game searchGame = gameCatalogueApp.gameCatalogue.getGame(searchGameField.getText());
            if (searchGameField.getText().isEmpty() || searchGame == null) {
                message("No such game was found in your catalogue", "Game Not Found", "plain");
            } else if (e.getSource() == toggleGameSearch) {
                searchGameFrame.dispose();
                gameCatalogueApp.searchGameDetails(searchGameField.getText());
            } else if (e.getSource() == searchGameForStatus) {
                changeStatusPage.dispose();
                intendedGame = gameCatalogueApp.gameCatalogue.getGame(searchGameField.getText());
                inputStatusPage.add(createStatusPanel());
                inputStatusPage.add(submitChange);
                inputStatusPage.setVisible(true);
            } else if (e.getSource() == submitChange) {
                inputStatusPage.dispose();
                gameCatalogueApp.gameCatalogue.getGame(intendedGame.getTitle()).setPlayStatus(
                        statusButtonOutput.toString().toLowerCase());
                intendedGame = null;
                clearFields();
                message("Successfully changed game status", "Game Status Changed", "plain");
            }
        }
    }

    // EFFECTS: creates game detail page
    protected void createGameDetailPage(Game searchGame) {
        JFrame gameDetailFrame = new JFrame("Game Details");
        gameDetailFrame.getContentPane().setBackground(BACKGROUND_COLOUR);
        gameDetailFrame.setSize(250,400);
        JLabel gameDetail = new FormattedLabel("Details of this Game", MAIN_TITLE_FONT);
        gameDetail.setIcon(gameImage);
        gameDetail.setHorizontalTextPosition(JLabel.CENTER);
        gameDetail.setVerticalTextPosition(JLabel.TOP);
        gameDetail.setHorizontalAlignment(JLabel.CENTER);
        gameDetail.setVerticalAlignment(JLabel.CENTER);

        JPanel finalDetailPanel = createDetailPanel(searchGame);
        gameDetailFrame.add(gameDetail, BorderLayout.NORTH);
        gameDetailFrame.add(finalDetailPanel, BorderLayout.CENTER);
        gameDetailFrame.setVisible(true);
    }

    private JPanel createDetailPanel(Game searchGame) {
        JPanel detailPanel = new JPanel(new GridLayout(0,2));
        detailPanel.setBackground(BACKGROUND_COLOUR);
        JLabel title = new FormattedLabel("Title: ", TITLE_FONT);
        JLabel gameTitle = new FormattedLabel(searchGame.getTitle(), REG_FONT);
        JLabel dev = new FormattedLabel("Developed by: ", TITLE_FONT);
        JLabel gameDev = new FormattedLabel(searchGame.getDeveloper(), REG_FONT);
        JLabel genre = new FormattedLabel("Genre: ", TITLE_FONT);
        JLabel gameGenre = new FormattedLabel(searchGame.stringGenre(), REG_FONT);
        JLabel platform = new FormattedLabel("Platform(s): ", TITLE_FONT);
        String compiledPlat = String.join(", ", searchGame.getPlatform());
        JLabel gamePlat = new FormattedLabel(compiledPlat, REG_FONT);
        JLabel year = new FormattedLabel("Year: ", TITLE_FONT);
        JLabel gameYear = new FormattedLabel(String.valueOf(searchGame.getYear()), REG_FONT);
        JLabel status = new FormattedLabel("Playing Status: ", TITLE_FONT);
        JLabel gameStatus = new FormattedLabel(searchGame.stringPlayStatus(), REG_FONT);

        addDetailComponents(detailPanel, title, gameTitle, dev, gameDev, genre, gameGenre, platform, gamePlat,
                year, gameYear, status, gameStatus);

        return detailPanel;
    }

    private void addDetailComponents(JPanel detailPanel, JLabel title, JLabel gameTitle, JLabel dev, JLabel gameDev,
                                     JLabel genre, JLabel gameGenre, JLabel platform, JLabel gamePlat, JLabel year,
                                     JLabel gameYear, JLabel status, JLabel gameStatus) {
        detailPanel.add(title);
        detailPanel.add(gameTitle);
        detailPanel.add(dev);
        detailPanel.add(gameDev);
        detailPanel.add(genre);
        detailPanel.add(gameGenre);
        detailPanel.add(platform);
        detailPanel.add(gamePlat);
        detailPanel.add(year);
        detailPanel.add(gameYear);
        detailPanel.add(status);
        detailPanel.add(gameStatus);
    }

    // EFFECTS: toggles viewing all games
    private void checkGameTitles(JButton command) {
        if (gameCatalogueApp.gameCatalogue.getAllGameTitles().isEmpty()) {
            message("There are no games in your game catalogue.", "Empty Catalogue", "plain");
        } else {
            String gameCombined = gameCatalogueApp.gameCatalogue.getAllGameTitles();
            ArrayList<String> gameTitles = new ArrayList(Arrays.asList(gameCombined.split(", ")));

            if (command.equals(viewGames)) {
                showGamePage("All Games on File", gameTitles);
            } else if (command.equals(searchGame)) {
                createSearchPage(searchGame);
            } else if (command.equals(searchGenre)) {
//            searchGame("genre");
            } else if (command.equals(searchStatus)) {
//                searchGame("status");
            } else if (command.equals(changeStatus)) {
                changeStatusPage();
            }
        }
    }

    // EFFECTS: shows game page of game titles and cover template
    protected void showGamePage(String title, ArrayList<String> gameTitles) {
        gamePage.setSize(WIDTH,HEIGHT / 2);
        gamePage.getContentPane().setBackground(BACKGROUND_COLOUR);
        gameTitlePanel.setBackground(BACKGROUND_COLOUR);
        titleLabel = new FormattedLabel(title, MAIN_TITLE_FONT);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setIcon(banner2);
        titleLabel.setHorizontalTextPosition(JLabel.CENTER);
        titleLabel.setVerticalTextPosition(JLabel.CENTER);
        gamePage.add(titleLabel, BorderLayout.NORTH);
        for (String gameName: gameTitles) {
            JLabel game = new FormattedLabel(gameName,TITLE_FONT);
            game.setIcon(gameImage);
            game.setHorizontalTextPosition(JLabel.CENTER);
            game.setVerticalTextPosition(JLabel.BOTTOM);
            game.setHorizontalAlignment(JLabel.CENTER);
            game.setVerticalAlignment(JLabel.CENTER);
            gameTitlePanel.add(game);
        }
        gamePage.add(gameTitlePanel);
        gamePage.setVisible(true);
    }

    private void addGamePage() {
        addGamePage = new JFrame();
        addGamePage.setSize(WIDTH / 3 * 2,HEIGHT / 3 * 2);
        addGamePage.getContentPane().setBackground(BACKGROUND_COLOUR);
        addGamePage.setLayout(new GridLayout(0,1));
        JLabel addGamePageTitle = new FormattedLabel("Add a Game", MAIN_TITLE_FONT);
        addGamePageTitle.setHorizontalAlignment(JLabel.CENTER); // in relation to the label
        addGamePageTitle.setVerticalAlignment(JLabel.CENTER);
        addGamePage.add(addGamePageTitle);
        gamePromptComponents();
        addGamePage.setVisible(true);
    }

    // EFFECTS: creates game prompt components
    private void gamePromptComponents() {
        JLabel titlePrompt = new FormattedLabel("  What's the name of the game?", TITLE_FONT);
        addGamePage.add(titlePrompt);
        addGamePage.add(gameTitleField);
        JLabel devPrompt = new FormattedLabel("  Who developed the game?", TITLE_FONT);
        addGamePage.add(devPrompt);
        addGamePage.add(gameDevField);
        JLabel genrePrompt = new FormattedLabel("  What is the game genre?", TITLE_FONT);
        JPanel genrePanel = createGenrePanel();
        addGamePage.add(genrePrompt);
        addGamePage.add(genrePanel);
        JLabel platformPrompt = new FormattedLabel(
                "  What platforms can you play this on? Please separate with commas.", TITLE_FONT);
        addGamePage.add(platformPrompt);
        addGamePage.add(gamePlatformField);
        JLabel yearPrompt = new FormattedLabel("  Which year was it released?", TITLE_FONT);
        addGamePage.add(yearPrompt);
        addGamePage.add(gameYearField);
        JLabel statusPrompt = new FormattedLabel("  What is its playing status?", TITLE_FONT);
        JPanel statusPanel = createStatusPanel();
        addGamePage.add(statusPrompt);
        addGamePage.add(statusPanel);
        addGamePage.add(new SubmitGameButton("submit game"));
    }

    // Represents submit game button and functions
    private class SubmitGameButton extends JButton implements ActionListener {

        SubmitGameButton(String option) {
            super(option);
            addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (gameTitleField.getText().isEmpty() || gameDevField.getText().isEmpty()
                    || gamePlatformField.getText().isEmpty() || gameYearField.getText().isEmpty()
                    || genreButtonOutput == null || statusButtonOutput == null) {
                JOptionPane.showMessageDialog(null,
                        "One of your fields is empty. Please make sure you've typed all the game details",
                        "Empty Field",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    int year = Integer.parseInt(gameYearField.getText());
                    Game newGame = new Game(gameTitleField.getText(), gameDevField.getText(), genreButtonOutput,
                            Arrays.asList(gamePlatformField.getText().split(",")), year, statusButtonOutput);
                    gameCatalogueApp.gameCatalogue.addGame(newGame);
                    message("Game successfully added to your catalogue", "Game successfully added",
                            "plain");
                    clearFields();
                    addGamePage.dispose();
                } catch (Exception exception) {
                    message("You did not enter a valid year, please try again", "Invalid year",
                            "plain");
                }
            }
        }
    }

    private JPanel createGenrePanel() {
        JPanel genrePanel = new JPanel();
        genrePanel.setBackground(BACKGROUND_COLOUR);
        genrePanel.setLayout(new FlowLayout());
        genrePanel.add(genreFpsButton);
        genrePanel.add(genreRpgButton);
        genrePanel.add(genreAdventureButton);
        genrePanel.add(genrePuzzleButton);
        genrePanel.add(genreHorrorButton);
        genrePanel.add(genreSimulationButton);
        genrePanel.add(genrePlatformerButton);
        return genrePanel;
    }

    // Represents submit game button and functions
    private class GenreButton extends JButton implements ActionListener {

        GenreButton(String option) {
            super(option);
            setSize(200,80);
            addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == genreFpsButton) {
                genreButtonOutput = FPS;
            } else if (e.getSource() == genreRpgButton) {
                genreButtonOutput = RPG;
            } else if (e.getSource() == genreAdventureButton) {
                genreButtonOutput = ADVENTURE;
            } else if (e.getSource() == genrePuzzleButton) {
                genreButtonOutput = PUZZLE;
            } else if (e.getSource() == genreHorrorButton) {
                genreButtonOutput = HORROR;
            } else if (e.getSource() == genreSimulationButton) {
                genreButtonOutput = SIMULATION;
            } else if (e.getSource() == genrePlatformerButton) {
                genreButtonOutput = PLATFORMER;
            }
        }
    }

    private JPanel createStatusPanel() {
        JPanel statusPanel = new JPanel();
        statusPanel.setBackground(BACKGROUND_COLOUR);
        statusPanel.setLayout(new FlowLayout());
        statusPanel.add(statusComplButton);
        statusPanel.add(statusCurrButton);
        statusPanel.add(statusHoldButton);
        statusPanel.add(statusPlanButton);
        return statusPanel;
    }

    // Represents submit game button and functions
    private class StatusButton extends JButton implements ActionListener {

        StatusButton(String option) {
            super(option);
            setSize(200,80);
            addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == statusComplButton) {
                statusButtonOutput = COMPLETED;
            } else if (e.getSource() == statusCurrButton) {
                statusButtonOutput = CURRENTLY_PLAYING;
            } else if (e.getSource() == statusHoldButton) {
                statusButtonOutput = ON_HOLD;
            } else if (e.getSource() == statusPlanButton) {
                statusButtonOutput = PLAN_TO_PLAY;
            }
        }
    }

    // EFFECTS: clears search game input fields, and button outputs
    private void clearFields() {
        gameTitleField.setText("");
        gameDevField.setText("");
        genreButtonOutput = null;
        gamePlatformField.setText("");
        gameYearField.setText("");
        statusButtonOutput = null;
        searchGameField.setText("");
    }

    // EFFECTS: creates a plain or error message pop-ups
    protected void message(String message, String title, String type) {
        if (type.equals("plain")) {
            JOptionPane.showMessageDialog(null, message, title,
                    JOptionPane.PLAIN_MESSAGE);
        } else if (type.equals("error")) {
            JOptionPane.showMessageDialog(null, message, title,
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // EFFECTS: creates endMessage pop-up to say goodbye to the user
    protected void endMessage() {
        if (gameCatalogueApp.gameCatalogue == null) {
            JOptionPane.showMessageDialog(null, "Goodbye! Happy Gaming :)",
                    "See you next time!", JOptionPane.PLAIN_MESSAGE);
            setVisible(true);
        } else {
            String goodbye = "Goodbye, " + gameCatalogueApp.gameCatalogue.getUsername() + "!  Happy gaming :)";
            JOptionPane.showMessageDialog(null, goodbye, "See you next time!",
                    JOptionPane.PLAIN_MESSAGE);
            setVisible(true);
        }
    }

}
