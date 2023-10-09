import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game {
    private int round = 1;
    private int monsterLevel = 1;
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private final Player player;

    public Game() throws InterruptedException {
        welcomeMessage();
        player = createPlayer();
        start();
        try {
            reader.close();
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }

    }

    private void start() throws InterruptedException {
        Thread.sleep(1000);
        Monster monster = createMonster();
        Thread.sleep(1000);
        // Display a message to indicate the game has started.

        while (player.isAlive() && monster.isAlive()) {
            // Display the current round number.
            displayRoundInfo();

            // Player attacks the monster.
            player.performAttack(monster);

            // Check if the monster is defeated (not alive), offer to play another game and  break the loop if user decline.
            if (!monster.isAlive()) {
                if (monsterLevel == 10) {
                    System.out.println("Congratulations! Player defeated all monsters and completed the game!");
                    break;
                }
                anotherGame();
                break;
            }

            // Monster attacks the player.
            monster.performAttack(player);

            // Check if the player is defeated (not alive), and break the loop if it is.
            if (!player.isAlive()) {
                System.out.printf("Game over! Player was defeated by level %d monster!%n", monsterLevel);
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

    private void welcomeMessage() {
        System.out.println("\n\n\t\t\tWelcome to the game Player vs Monsters! Player must defeat all 10 monsters to complete the game, good luck!");
    }

    // Create a new Player based on user input.
    private Player createPlayer() {
        return (Player) createCharacter(Player.class);
    }

    // Create a new Monster based on user input.
    private Monster createMonster() {
        int[] monsterStats = getRandomStats(monsterLevel);
        System.out.printf("\nYou will fight against level %d Monster!%n", monsterLevel);
        return new Monster(monsterStats[0], monsterStats[1], monsterStats[2], monsterStats[3], monsterStats[4]);
    }

    private void anotherGame() throws InterruptedException {
        System.out.printf("Would you like to fight a level %d monster? Enter y/n%n", monsterLevel + 1);

        switch (getUserAnswer()) {
            case "y" -> startNewBattle();
            case "n" -> System.out.println("\nYou rejected to fight, coward. Poor you!");
        }
    }

    private void startNewBattle() throws InterruptedException {
        System.out.println("\nLet's start a new battle!");
        round = 1;
        monsterLevel++;
        Thread.sleep(1000);
        start();
    }

    private void displayRoundInfo() {
        System.out.printf("\n[Round %d]%n", round++);
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
                health = getInputInRange(character + "'s health", 1, 100);
                minDamage = getInputInRange(character + "'s minimal damage", 1, 50);
                maxDamage = getInputInRange(character + "'s maximal damage", minDamage + 1, 100);
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

    private void healingProcess(Player player) {
        System.out.printf("\nYou have %d healing potions. Would you like to heal? Enter y/n%n", player.getHealingCount());

        switch (getUserAnswer()) {
            case "y" -> player.heal();
            case "n" -> System.out.println("You rejected to heal. Wish you luck!");
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

    private int[] getRandomStats(int monsterLevel) {
        int monsterStatIndex = monsterLevel - 1;
        int[] attackPower = new int[10];
        int[] defence = new int[10];
        int[] health = new int[10];
        int[] minDamage = new int[10];
        int[] maxDamage = new int[10];

        for (int i = 1, j = 0; i <= 10; i++, j++) {
            attackPower[j] = (int) (Math.random() * (3 * i)) + 1;
            defence[j] = (int) (Math.random() * (3 * i)) + 1;
        }

        for (int i = 1, j = 0; i <= 10; i++, j++) {
            health[j] = (int) (Math.random() * (i * 10)) + 1;
            minDamage[j] = (int) (Math.random() * (i * 5)) + 1;
            maxDamage[j] = (int) (Math.random() * (i * 10 - minDamage[j] + 1)) + minDamage[j];
        }

        return new int[]{attackPower[monsterStatIndex], defence[monsterStatIndex], health[monsterStatIndex], minDamage[monsterStatIndex], maxDamage[monsterStatIndex]};
    }

}
