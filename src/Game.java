import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game {
    private int round = 1; // Initialize the round counter to 1.
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); // Create a BufferedReader for user input.

    public Game() {
        System.out.println("\t\t\tWelcome to the game Player vs Monster!\n"); // Display a welcome message when the game is created.
    }

    public void start(Player player, Monster monster) {
        System.out.println("\nGame started!"); // Display a message to indicate that the game has started.

        while (player.isAlive() && monster.isAlive()) { // Continue the game as long as both the player and monster are alive.
            System.out.printf("\n[Round %d]%n", round++); // Display the current round number.

            playerAttack(player, monster); // Allow the player to attack the monster.
            if (!monster.isAlive()) { // Check if the monster is defeated.
                break; // If the monster is defeated, exit the game loop.
            }

            monsterAttack(monster, player); // Allow the monster to attack the player.
            if (!player.isAlive()) { // Check if the player is defeated.
                break; // If the player is defeated, exit the game loop.
            }

            if (player.getHealingCount() > 0) { // Check if the player has healing potions.
                healCondition(player); // Allow the player to use a healing potion.
            }

            System.out.println("\n------------------------------------------------------------"); // Display a separator between rounds.
        }

        winCondition(player); // Determine and display the winner of the game.
    }

    public Player createPlayer() {
        System.out.println("\tPLAYER CREATION\n"); // Display a message to indicate player creation.
        return playerCreationInput(); // Create a new Player based on user input.
    }

    private Player playerCreationInput() {
        int attackPower = 0;
        int defense = 0;
        int health = 0;
        int minDamage = 0;
        int maxDamage = 0;

        while (attackPower == 0 || defense == 0 || health == 0 || minDamage == 0 || maxDamage == 0) { // Validate input until all characteristics are provided.
            try {
                attackPower = getInputInRange("Player's attack power", 1, 30); // Get and validate player's attack power.
                defense = getInputInRange("Player's defense", 1, 30); // Get and validate player's defense.
                health = getInputInRange("Player's health", 1, Integer.MAX_VALUE); // Get and validate player's health.
                minDamage = getInputInRange("Player's minimal damage", 1, Integer.MAX_VALUE); // Get and validate player's minimal damage.
                maxDamage = getInputInRange("Player's maximal damage", minDamage, Integer.MAX_VALUE); // Get and validate player's maximal damage.

            } catch (Exception e) {
                System.out.println("Incorrect characteristics were entered. Try again and enter correct characteristics, please.\n");
            }
        }

        return new Player(attackPower, defense, health, minDamage, maxDamage); // Create a Player with validated characteristics.
    }

    public void playerAttack(Player player, Monster monster) {
        if (player.attack(monster)) { // If the player successfully attacks the monster.
            if (!monster.isAlive()) { // Check if the monster is defeated.
                System.out.println("Player killed monster!"); // Display a message indicating the monster is defeated.
            } else {
                System.out.printf("Player attacked monster! Damage dealt: %d\tMonster health: %d%n", monster.getAttackDamage(), monster.getHealth()); // Display the attack information and monster's health.
            }
        } else {
            System.out.println("Player missed!"); // Display a message indicating that the player missed the attack.
        }
    }

    public Monster createMonster() {
        System.out.println("\tMONSTER CREATION\n"); // Display a message to indicate monster creation.
        return monsterCreationInput(); // Create a new Monster based on user input.
    }

    private Monster monsterCreationInput() {
        int attackPower = 0;
        int defense = 0;
        int health = 0;
        int minDamage = 0;
        int maxDamage = 0;

        while (attackPower == 0 || defense == 0 || health == 0 || minDamage == 0 || maxDamage == 0) { // Validate input until all characteristics are provided.
            try {
                attackPower = getInputInRange("Monster's attack power", 1, 30); // Get and validate monster's attack power.
                defense = getInputInRange("Monster's defense", 1, 30); // Get and validate monster's defense.
                health = getInputInRange("Monster's health", 1, Integer.MAX_VALUE); // Get and validate monster's health.
                minDamage = getInputInRange("Monster's minimal damage", 1, Integer.MAX_VALUE); // Get and validate monster's minimal damage.
                maxDamage = getInputInRange("Monster's maximal damage", minDamage, Integer.MAX_VALUE); // Get and validate monster's maximal damage.

            } catch (Exception e) {
                System.out.println("Incorrect characteristics were entered. Try again and enter correct characteristics, please.\n");
            }
        }

        return new Monster(attackPower, defense, health, minDamage, maxDamage); // Create a Monster with validated characteristics.
    }

    public void monsterAttack(Monster monster, Player player) {
        if (monster.attack(player)) { // If the monster successfully attacks the player.
            if (!player.isAlive()) { // Check if the player is defeated.
                System.out.println("Monster killed player!"); // Display a message indicating the player is defeated.
            } else {
                System.out.printf("Monster attacked player! Damage dealt: %d\tPlayer health: %d", player.getAttackDamage(), player.getHealth()); // Display the attack information and player's health.
            }
        } else {
            System.out.println("Monster missed!"); // Display a message indicating that the monster missed the attack.
        }
    }

    private int getInputInRange(String prompt, int minValue, int maxValue) throws IOException {
        int value;

        do {
            System.out.println("Enter " + prompt + ". It should be in range from " + minValue + " to " + maxValue + ": "); // Prompt the user
            value = Integer.parseInt(reader.readLine()); // Read the user's input and convert it to an integer.
            if (value < minValue || value > maxValue) { // Check if the input is out of the specified range.
                System.out.println("Input out of range. Please enter a valid value.\n"); // Display an error message.
            }
        } while (value < minValue || value > maxValue); // Repeat the input process until a valid value is provided.

        return value; // Return the validated input value.
    }

    public void winCondition(Player player) {
        System.out.println("\n------------------------------------------------------------");
        String gameResult = player.isAlive() ? "\nGame result: Player won!" : "\nGame result: Monster won!";
        System.out.print(gameResult); // Display the game result based on whether the player is alive or not.
    }

    private void healCondition(Player player) {
        System.out.printf("\nYou have %d healing potions. Would you like to heal? Enter y/n%n", player.getHealingCount()); // Prompt the player to choose whether to heal.
        String answer = "";

        do {
            try {
                switch (answer = reader.readLine()) {
                    case "y" -> {
                        player.heal(); // If the player chooses to heal, call the heal method.
                        System.out.printf("Player was healed on %d points. Player's health: %d%n", player.getHealedAmount(), player.getHealth()); // Display the healing information.
                    }
                    case "n" -> System.out.println("You rejected to heal. Wish you luck!"); // If the player chooses not to heal, display a message.
                }
                if (!answer.equalsIgnoreCase("y") && !answer.equalsIgnoreCase("n")) {
                    throw new IllegalArgumentException(); // If an invalid choice is entered, throw an exception.
                }
            } catch (Exception ex) {
                System.out.println("Incorrect answer was entered. Please enter y/n."); // Display an error message for an incorrect input.
            }
        } while (!answer.equalsIgnoreCase("y") && !answer.equalsIgnoreCase("n")); // Repeat the process until a valid choice is made.
    }
}
