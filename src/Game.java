import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game {
    private int round = 1;
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public Game() {
        System.out.println("\t\t\tWelcome to the game Player vs Monster!\n");
    }

    public void start(Player player, Monster monster) {
        System.out.println("\nGame started!");

        while (player.isAlive() && monster.isAlive()) {
            System.out.printf("\n[Round %d]%n", round++);

            playerAttack(player, monster);
            if (!monster.isAlive()) {
                break;
            }

            monsterAttack(monster, player);
            if (!player.isAlive()) {
                break;
            }

            if (player.getHealingCount() > 0) {
                healCondition(player);
            }

            System.out.println("\n------------------------------------------------------------");
        }

        winCondition(player);
    }

    public Player createPlayer() {
        System.out.println("\tPLAYER CREATION\n");
        return playerCreationInput();
    }

    private Player playerCreationInput() {
        int attackPower = 0;
        int defense = 0;
        int health = 0;
        int minDamage = 0;
        int maxDamage = 0;

        while (attackPower == 0 || defense == 0 || health == 0 || minDamage == 0 || maxDamage == 0) {
            try {
                attackPower = getInputInRange("Player's attack power", 1, 30);
                defense = getInputInRange("Player's defense", 1, 30);
                health = getInputInRange("Player's health", 1, Integer.MAX_VALUE);
                minDamage = getInputInRange("Player's minimal damage", 1, Integer.MAX_VALUE);
                maxDamage = getInputInRange("Player's maximal damage", minDamage, Integer.MAX_VALUE);

            } catch (Exception e) {
                System.out.println("Incorrect characteristics were entered. Try again and enter correct characteristics, please.\n");
            }
        }

        return new Player(attackPower, defense, health, minDamage, maxDamage);
    }

    public void playerAttack(Player player, Monster monster) {
        if (player.attack(monster)) {
            if (!monster.isAlive()) {
                System.out.println("Player killed monster!");
            } else {
                System.out.print("Player attacked monster! Damage dealt: " + monster.getAttackDamage());
                System.out.println("\tMonster health:   " + monster.getHealth());
            }
        } else {
            System.out.println("Player missed!");
        }
    }

    public Monster createMonster() {
        System.out.println("\tMONSTER CREATION\n");
        return monsterCreationInput();
    }

    private Monster monsterCreationInput() {
        int attackPower = 0;
        int defense = 0;
        int health = 0;
        int minDamage = 0;
        int maxDamage = 0;

        while (attackPower == 0 || defense == 0 || health == 0 || minDamage == 0 || maxDamage == 0) {
            try {
                attackPower = getInputInRange("Monster's attack power", 1, 30);
                defense = getInputInRange("Monster's defense", 1, 30);
                health = getInputInRange("Monster's health", 1, Integer.MAX_VALUE);
                minDamage = getInputInRange("Monster's minimal damage", 1, Integer.MAX_VALUE);
                maxDamage = getInputInRange("Monster's maximal damage", minDamage, Integer.MAX_VALUE);

            } catch (Exception e) {
                System.out.println("Incorrect characteristics were entered. Try again and enter correct characteristics, please.\n");
            }
        }

        return new Monster(attackPower, defense, health, minDamage, maxDamage);
    }

    public void monsterAttack(Monster monster, Player player) {
        if (monster.attack(player)) {
            if (!player.isAlive()) {
                System.out.println("Monster killed player!");
            } else {
                System.out.print("Monster attacked player! Damage dealt: " + player.getAttackDamage());
                System.out.println("\tPlayer health:   " + player.getHealth());
            }
        } else {
            System.out.println("Monster missed!");
        }
    }

    private int getInputInRange(String prompt, int minValue, int maxValue) throws IOException {
        int value;

        do {
            System.out.println("Enter " + prompt + ". It should be in range from " + minValue + " to " + maxValue + ": ");
            value = Integer.parseInt(reader.readLine());
            if (value < minValue || value > maxValue) {
                System.out.println("Input out of range. Please enter a valid value.\n");
            }
        } while (value < minValue || value > maxValue);

        return value;
    }

    public void winCondition(Player player) {
        System.out.println("\n------------------------------------------------------------");
        String gameResult = player.isAlive() ? "\nGame result: Player won!" : "\nGame result: Monster won!";
        System.out.print(gameResult);
    }

    public void healCondition(Player player) {
        System.out.printf("\nYou have %d healing potions. Would you like to heal? Enter y/n%n", player.getHealingCount());
        String answer = "";
            do {
                try {
                    switch (answer = reader.readLine()) {
                        case "y" -> {
                            player.heal();
                            System.out.printf("Player was healed on %d points. Player's health: %d%n", player.getHealedAmount(), player.getHealth());
                        }
                        case "n" -> System.out.println("You rejected to heal. Wish you luck!");
                    }
                    if (!answer.equalsIgnoreCase("y") && !answer.equalsIgnoreCase("n")) {
                        throw new IllegalArgumentException();
                    }
                } catch (Exception ex) {
                    System.out.println("Incorrect answer was entered. Please enter y/n.");
                }
            } while (!answer.equalsIgnoreCase("y") && !answer.equalsIgnoreCase("n"));
    }
}
