public class Main {
    public static void main(String[] args) {
            Game game = new Game();
            Player player = game.createPlayer();
            Monster monster = game.createMonster();
            game.start(player, monster);
    }
}