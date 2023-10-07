public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("\t\t\tWelcome to the game Player vs Monster!");

        Game game = new Game();
        Player player = game.createPlayer();
        Monster monster = game.createMonster();

        game.start(player, monster);
        game.gameResult(player);
    }
}