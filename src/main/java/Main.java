public class Main {
    public static void main(String[] args) throws InterruptedException {
        Game game = new Game();
        game.welcomeMessage();

        Player player = game.createPlayer();
        Monster monster = game.createMonster();

        game.start(player, monster);
    }
}
