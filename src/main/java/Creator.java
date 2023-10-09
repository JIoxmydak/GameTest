import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Creator {
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Creates a player character.
     *
     * @return A new Player instance.
     */
    public Player createPlayer() {
        return (Player) createCharacter(Player.class);
    }

    /**
     * Creates a monster with random stats based on the given level.
     *
     * @param monsterLevel The level of the monster to be created.
     * @return A new Monster instance with random stats.
     */
    public Monster createMonster(int monsterLevel) {
        int[] monsterStats = getRandomStats(monsterLevel);
        System.out.printf("\nYou will fight against a level %d Monster!%n", monsterLevel);
        return new Monster(monsterStats[0], monsterStats[1], monsterStats[2], monsterStats[3], monsterStats[4]);
    }

    /**
     * Get user input for yes/no questions and ensure a valid response.
     */
    public String getUserAnswer() {
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

    /**
     * Close the input reader.
     */
    public void close() throws IOException{
        reader.close();
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
                minDamage = getInputInRange(character + "'s minimal damage", 1, 99);
                maxDamage = getInputInRange(character + "'s maximal damage", minDamage + 1, 100);
            } catch (Exception e) {
                System.out.println("Incorrect characteristics were entered. Try again and enter correct characteristics, please.\n\n");
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
