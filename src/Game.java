import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game {
    private int round = 1;
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public Game() {
        System.out.println("Welcome to the game Player vs Monster!\n");
    }

    public void start(Player player, Monster monster) {
        System.out.println("\nGame was started!\n");
        while (player.isAlive() && monster.isAlive()) {
            System.out.printf("[Round %d]%n", round++);

            if (player.attack(monster)) {
                System.out.print("Player attacked monster! Damage dealt: " + monster.attackDamage);
                System.out.println("\tMonster health:   " + monster.getHealth());
            } else {
                System.out.println("Player missed!");
            }

            if (monster.attack(player)) {
                System.out.print("Monster attacked player! Damage dealt: " + player.attackDamage);
                System.out.println("\tPlayer health:   " + player.getHealth());
            } else {
                System.out.println("Monster missed!");
            }
            System.out.println("------------------------------------------------------------");
        }

        if (player.isAlive()) {
            System.out.println("Player won!");
        } else {
            System.out.println("Monster won!");
        }
    }

    public Player createPlayer() {
        int attackPower = 0;
        int defence = 0;
        int health = 0;
        int damageMin = 0;
        int damageMax = 0;

        try {
            System.out.println("Enter player's attack power. It should be in range from 1 to 30.");
            attackPower = Integer.parseInt(reader.readLine());
            if (attackPower > 30 || attackPower < 1) {
                throw new IllegalArgumentException("Attack power should be in range from 1 to 30. Enter correct attack power, please.");
            }

            System.out.println("Enter player's defence. It should be in range from 1 to 30.");
            defence = Integer.parseInt(reader.readLine());
            if (defence > 30 || defence < 1) {
                throw new IllegalArgumentException("Defence should be in range from 1 to 30. Enter correct defence, please.");
            }

            System.out.println("Enter player's health. It should be more than 0.");
            health = Integer.parseInt(reader.readLine());
            if (health < 1) {
                throw new IllegalArgumentException("Health should be more than 0. Enter correct amount of health, please.");
            }

            System.out.println("Enter player's minimal damage. It should be more than 0.");
            damageMin = Integer.parseInt(reader.readLine());
            if (damageMin < 1) {
                throw new IllegalArgumentException("Minimal damage should be more than 0. Enter correct minimal damage, please.");
            }
            System.out.println("Enter player's maximal damage. It should be more than minimal damage.");
            damageMax = Integer.parseInt(reader.readLine());
            if (damageMax < damageMin) {
                throw new IllegalArgumentException("Maximal damage can't be less then minimal damage. Enter correct maximal damage, please.");
            }
        } catch (IOException e) {
            System.out.println("Incorrect characteristics were entered. Try again and enter correct characteristics, please.");
        }
        return new Player(attackPower, defence, health, damageMin, damageMax);
    }

    public Monster createMonster() {
        int attackPower = 0;
        int defence = 0;
        int health = 0;
        int damageMin = 0;
        int damageMax = 0;

        try {
            System.out.println("Enter monster's attack power. It should be in range from 1 to 30.");
            attackPower = Integer.parseInt(reader.readLine());
            if (attackPower > 30 || attackPower < 1) {
                throw new IllegalArgumentException("Attack power should be in range of 1 to 30. Enter correct attack power, please.");
            }

            System.out.println("Enter monster's defence. It should be in range from 1 to 30.");
            defence = Integer.parseInt(reader.readLine());
            if (defence > 30 || defence < 1) {
                throw new IllegalArgumentException("Defence should be in range of 1 to 30. Enter correct defence, please.");
            }

            System.out.println("Enter monster's health. It should be more than 0.");
            health = Integer.parseInt(reader.readLine());
            if (health < 1) {
                throw new IllegalArgumentException("Health should be more than 0. Enter correct amount of health, please.");
            }

            System.out.println("Enter monster's minimal damage. It should be more than 0.");
            damageMin = Integer.parseInt(reader.readLine());
            if (damageMin < 1) {
                throw new IllegalArgumentException("Minimal damage should be more than 0. Enter correct minimal damage, please.");
            }
            System.out.println("Enter monster's maximal damage. It should be more than minimal damage.");
            damageMax = Integer.parseInt(reader.readLine());
            if (damageMax < damageMin) {
                throw new IllegalArgumentException("Maximal damage can't be less then minimal damage. Enter correct maximal damage, please.");
            }
        } catch (IOException e) {
            System.out.println("Incorrect characteristics were entered. Try again and enter correct characteristics, please.");
        }
        return new Monster(attackPower, defence, health, damageMin, damageMax);
    }
}
