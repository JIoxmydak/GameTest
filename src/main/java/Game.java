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
            System.out.printf("\n[Round %d]%n", round++);

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

    public void gameResult(Player player) {
        System.out.println("\n------------------------------------------------------------\n");
        String gameResult = player.isAlive() ? "Game result: Player won!\n" : "Game result: Monster won!\n";
        System.out.print(gameResult);
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

    private void anotherGame(Player player) throws InterruptedException {
        System.out.println("\nWould you like to fight with a new monster? Enter y/n");

        switch (getUserAnswer()) {
            case "y" -> {
                System.out.println("\nLet's start a new battle!");
                round = 1;
                player.setHealth(player.healthMax);
                System.out.printf("Player was healed! Player's health: %d%n", player.getHealth());
                Monster newMonster = this.createMonster();
                this.start(player, newMonster);
            }
            case "n" -> System.out.println("You rejected to fight coward :c Poor you!");
        }
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
