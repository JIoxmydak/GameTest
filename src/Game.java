import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game {
    private int round = 1;
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public Game() {
        // Display a welcome message when the game is created.
        System.out.println("\t\t\tWelcome to the game Player vs Monster!");
    }

    public void start(Player player, Monster monster) {
        // Display a message to indicate the game has started.
        System.out.println("\nGame started!");

        while (player.isAlive() && monster.isAlive()) {
            // Display the current round number.
            System.out.printf("\n[Round %d]%n", round++);

            // Player attacks the monster.
            player.performAttack(monster);

            // Check if the monster is defeated (not alive), and break the loop if it is.
            if (!monster.isAlive()) {
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
        }

        // Determine the win condition for the game.
        winCondition(player);
    }

    // Create a new Player based on user input.
    public Player createPlayer() {
        return (Player) createCharacter(Player.class);
    }

    // Create a new Monster based on user input.
    public Monster createMonster() {
        return (Monster) createCharacter(Monster.class);
    }

    private Creature createCharacter(Class<? extends Creature> characterClass) {
        String character = characterClass.getSimpleName();
        System.out.printf("\n\t%s CREATION%n\n", character.toUpperCase());

        int attackPower = 0;
        int defense = 0;
        int health = 0;
        int minDamage = 0;
        int maxDamage = 0;

        while (attackPower == 0 || defense == 0 || health == 0 || minDamage == 0 || maxDamage == 0) {
            try {
                attackPower = getInputInRange(character + "'s attack power", 1, 30);
                defense = getInputInRange(character + "'s defense", 1, 30);
                health = getInputInRange(character + "'s health", 1, Integer.MAX_VALUE);
                minDamage = getInputInRange(character + "'s minimal damage", 1, Integer.MAX_VALUE);
                maxDamage = getInputInRange(character + "'s maximal damage", minDamage, Integer.MAX_VALUE);
            } catch (Exception e) {
                System.out.println("Incorrect characteristics were entered. Try again and enter correct characteristics, please.\n");
            }
        }
        try {
            return characterClass.getDeclaredConstructor(int.class, int.class, int.class, int.class, int.class).newInstance(attackPower, defense, health, minDamage, maxDamage);
        } catch (Exception methodEx){
            return null;
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

    private void winCondition(Player player) {
        System.out.println("\n------------------------------------------------------------");
        String gameResult = player.isAlive() ? "\nGame result: Player won!" : "\nGame result: Monster won!";
        System.out.print(gameResult);
    }

    private void healingProcess(Player player) {
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
