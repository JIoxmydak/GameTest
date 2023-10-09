public class Battle {
    private int monsterLevel = 1;
    private final Creator creator = new Creator();
    private int round = 1;

    /**
     * Starts a battle between the player and a monster.
     *
     * @param player The player participating in the battle.
     */
    public void start(Player player) throws InterruptedException {
        Monster monster = creator.createMonster(monsterLevel);
        Thread.sleep(1000);

        while (player.isAlive() && monster.isAlive()) {
            displayRoundInfo();

            player.performAttack(monster);

            if (!monster.isAlive()) {
                if (monsterLevel == 10) {
                    System.out.println("Congratulations! Player defeated all monsters and completed the game!\n");
                    break;
                }
                fightNewMonster(player);
                break;
            }

            monster.performAttack(player);

            if (!player.isAlive()) {
                System.out.printf("Game over! Player was defeated by level %d monster!%n\n", monsterLevel);
                break;
            }

            if (player.getHealingCount() > 0) {
                healingProcess(player);
            }

            System.out.println("\n------------------------------------------------------------");
            Thread.sleep(2000);
        }
    }

    private void fightNewMonster(Player player) throws InterruptedException {
        System.out.printf("Would you like to fight a level %d monster? Enter y/n%n", monsterLevel + 1);

        switch (creator.getUserAnswer()) {
            case "y" -> startNewBattle(player);
            case "n" -> System.out.println("\nYou rejected to fight, coward. Poor you!");
        }
    }

    private void startNewBattle(Player player) throws InterruptedException {
        System.out.println("\nLet's start a new battle!");
        round = 1;
        monsterLevel++;
        Thread.sleep(1000);
        start(player);
    }

    private void displayRoundInfo() {
        System.out.printf("\n[Round %d]%n", round++);
    }

    private void healingProcess(Player player) {
        System.out.printf("\nYou have %d healing potions. Would you like to heal? Enter y/n%n", player.getHealingCount());

        switch (creator.getUserAnswer()) {
            case "y" -> player.heal();
            case "n" -> System.out.println("You rejected to heal. Wish you luck!");
        }
    }
}
