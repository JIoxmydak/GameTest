public class Main {
    public static void main(String[] args) {
        // Create an instance of the Game class to start the game.
        Game game = new Game();

        // Create a player by calling the createPlayer() method in the game.
        Player player = game.createPlayer();

        // Create a monster by calling the createMonster() method in the game.
        Monster monster = game.createMonster();

        // Start the game by calling the start() method with the player and monster as arguments.
        game.start(player, monster) ;
    }
}