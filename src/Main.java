public class Main {
    public static void main(String[] args) {
        Player player;
        Monster monster;
        Game game = new Game();

        System.out.println("\tPLAYER CREATION\n");
        player = game.createPlayer();
        while (player.getAttackPower() == 0 || player.getDefence() == 0 || player.getHealth() == 0 || player.getDamageMin() == 0 || player.getDamageMax() == 0) {
            player = game.createPlayer();
        }
        System.out.println("\tPLAYER WAS SUCCESSFULLY CREATED!\n");

        System.out.println("\tMONSTER CREATION\n");
        monster = game.createMonster();
        while (monster.getAttackPower() == 0 || monster.getDefence() == 0 || monster.getHealth() == 0 || monster.getDamageMin() == 0 || monster.getDamageMax() == 0) {
            monster = game.createMonster();
        }
        System.out.println("\tMONSTER WAS SUCCESSFULLY CREATED!\n");

        game.start(player, monster);
    }
}