import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game {
    private int round = 1;
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public void start(Player player, Monster monster) throws InterruptedException {
        // Display a message to indicate the game has started.
        System.out.println("\nGame started!");

        while (player.isAlive() && monster.isAlive()) {
            // Display the current round number.
            displayRoundInfo();

            // Player attacks the monster.
            player.performAttack(monster);

            // Check if the monster is defeated (not alive), offer to play another game and  break the loop if user decline.
            if (!monster.isAlive()) {
                anotherGame(player);
                break;
            }

            // Monster attacks the player.
            monster.performAttack(player);

            // Check if the player is defeated (not alive), and break the loop if it is.
            if (!player.isAlive()) {
                break;
            }

            // If the player has enough healing potions, perform the healing process.
            if (player.getHealingCount() > 0) {
                healingProcess(player);
            }

            System.out.println("\n------------------------------------------------------------");

            //noinspection BusyWait
            Thread.sleep(2000);
        }
    }

    public void welcomeMessage() {
        System.out.println("\t\t\tWelcome to the game Player vs Monster!");
    }

    // Create a new Player based on user input.
    public Player createPlayer() {
        return (Player) createCharacter(Player.class);
    }

    // Create a new Monster based on user input.
    public Monster createMonster() {
        long[] monsterStats = getRandomStats();
        return new Monster((int) monsterStats[0], (int) monsterStats[1], monsterStats[2], monsterStats[3], monsterStats[4]);
    }

    private long[] getRandomStats() {
        int attackPower = (int) (Math.random() * 30) + 1;
        int defence = (int) (Math.random() * 30) + 1;
        long health = (long) (Math.random() * Long.MAX_VALUE) + 1;
        long minDamage = (long) (Math.random() * Long.MAX_VALUE) + 1;
        long maxDamage = (long) (Math.random() * (Long.MAX_VALUE - minDamage + 1)) + minDamage;

        return new long[]{attackPower, defence, health, minDamage, maxDamage};
    }

    private void displayRoundInfo() {
        System.out.printf("\n[Round %d]%n", round++);
    }

    private Creature createCharacter(Class<? extends Creature> characterClass) {
        String character = characterClass.getSimpleName();
        System.out.printf("\n\t%s CREATION%n\n", character.toUpperCase());

        int attackPower = 0;
        int defense = 0;
        long health = 0;
        long minDamage = 0;
        long maxDamage = 0;

        while (attackPower == 0 || defense == 0 || health == 0 || minDamage == 0 || maxDamage == 0) {
            try {
                attackPower = (int) getInputInRange(character + "'s attack power", 1, 30);
                defense = (int) getInputInRange(character + "'s defense", 1, 30);
                health = getInputInRange(character + "'s health", 1, Long.MAX_VALUE);
                minDamage = getInputInRange(character + "'s minimal damage", 1, Long.MAX_VALUE);
                maxDamage = getInputInRange(character + "'s maximal damage", minDamage, Long.MAX_VALUE);
            } catch (Exception e) {
                System.out.println("Incorrect characteristics were entered. Try again and enter correct characteristics, please.\n");
            }
        }
        try {
            return characterClass.getDeclaredConstructor(int.class, int.class, long.class, long.class, long.class).newInstance(attackPower, defense, health, minDamage, maxDamage);
        } catch (Exception methodEx){
            return null;
        }
    }

    private long getInputInRange(String prompt, long minValue, long maxValue) throws IOException {
        long value;

        do {
            System.out.println("Enter " + prompt + ". It should be in range from " + minValue + " to " + maxValue + ": ");
            value = Long.parseLong(reader.readLine());
            if (value < minValue || value > maxValue) {
                System.out.println("Input out of range. Please enter a valid value.\n");
            }
        } while (value < minValue || value > maxValue);

        return value;
    }

    private void anotherGame(Player player) throws InterruptedException {
        System.out.println("\n------------------------------------------------------------\n");
        System.out.println("Would you like to fight with a new monster? Enter y/n");

        switch (getUserAnswer()) {
            case "y" -> startNewBattle(player);
            case "n" -> System.out.println("\nYou rejected to fight coward :c Poor you!");
        }
    }

    private void startNewBattle(Player player) throws InterruptedException {
        System.out.println("\nLet's start a new battle!");
        round = 1;
        player.setHealingCount(4);
        player.setHealth(player.getHealthMax());
        System.out.printf("Player was healed and potions were restored! Player's health: %d | Amount of potions: %d%n", player.getHealth(), player.getHealingCount());
        Monster newMonster = createMonster();
        start(player, newMonster);
    }

    private void healingProcess(Player player) {
        System.out.printf("\nYou have %d healing potions. Would you like to heal? Enter y/n%n", player.getHealingCount());

        switch (getUserAnswer()) {
            case "y" -> {
                player.heal();
                System.out.printf("Player was healed on %d points. Player's health: %d%n", player.getHealedAmount(), player.getHealth());
            }
            case "n" -> System.out.println("You rejected to heal. Wish you luck!");
        }
    }

    private String getUserAnswer() {
        String answer = "";

        do {
            try {
                answer = reader.readLine();
                if (!answer.equalsIgnoreCase("y") && !answer.equalsIgnoreCase("n")) {
                    throw new IllegalArgumentException();
                }
            } catch (Exception ex) {
                System.out.println("Incorrect answer was entered. Please enter y/n.");
            }
        } while (!answer.equalsIgnoreCase("y") && !answer.equalsIgnoreCase("n"));

        return answer;
    }

}
