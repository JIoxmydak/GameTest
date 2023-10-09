import java.io.IOException;

public class Game {
    private final Creator creator = new Creator();

    /**
     * Constructor for the Game class.
     * Initializes the game by displaying a welcome message, creating a player,
     * starting a battle, and providing an option to restart the game.
     */
    public Game() throws InterruptedException, IOException {
        welcomeMessage();
        Player player = creator.createPlayer();
        Battle battle = new Battle();
        battle.start(player);
        restart();
        creator.close();
    }

    private void restart() throws InterruptedException, IOException {
        System.out.println("Would you like to start a new game? Enter y/n:");
        switch (creator.getUserAnswer()) {
            case "y" -> new Game();
            case "n" -> System.out.println("\nThanks for playing! Goodbye!");
        }
    }

    private void welcomeMessage() {
        System.out.println("\n\n\t\t\tWelcome to the game Player vs Monsters! Player must defeat all 10 monsters to complete the game, good luck!");
    }

}
