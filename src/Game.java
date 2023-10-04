import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Game {
    private int round = 1;
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public Game() {
        System.out.println("\t\t\tWelcome to the game Player vs Monster!\n");
    }

    public void start(Player player, Monster monster) {
        System.out.println("\nGame started!\n");

        while (player.isAlive() && monster.isAlive()) {
            System.out.printf("[Round %d]%n", round++);
            playerAttack(player, monster);
            monsterAttack(monster, player);
            if (player.getHealingCount() > 0) {
                healCondition(player);
            }
            System.out.println("\n------------------------------------------------------------");
        }

        winCondition(player);
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
                throw new IllegalArgumentException();
            }

            System.out.println("Enter player's defence. It should be in range from 1 to 30.");
            defence = Integer.parseInt(reader.readLine());
            if (defence > 30 || defence < 1) {
                throw new IllegalArgumentException();
            }

            System.out.println("Enter player's health. It should be more than 0.");
            health = Integer.parseInt(reader.readLine());
            if (health < 1) {
                throw new IllegalArgumentException();
            }

            System.out.println("Enter player's minimal damage. It should be more than 0.");
            damageMin = Integer.parseInt(reader.readLine());
            if (damageMin < 1) {
                throw new IllegalArgumentException();
            }
            System.out.println("Enter player's maximal damage. It should be more than minimal damage.");
            damageMax = Integer.parseInt(reader.readLine());
            if (damageMax < damageMin) {
                throw new IllegalArgumentException();
            }
        } catch (Exception e) {
            System.out.println("Incorrect characteristics were entered. Try again and enter correct characteristics, please.\n");
        }


        return new Player(attackPower, defence, health, damageMin, damageMax);
    }

    public void playerAttack(Player player, Monster monster) {
        if (player.attack(monster)) {
            System.out.print("Player attacked monster! Damage dealt: " + monster.getAttackDamage());
            System.out.println("\tMonster health:   " + monster.getHealth());
        } else {
            System.out.println("Player missed!");
        }
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
                throw new IllegalArgumentException();
            }

            System.out.println("Enter monster's defence. It should be in range from 1 to 30.");
            defence = Integer.parseInt(reader.readLine());
            if (defence > 30 || defence < 1) {
                throw new IllegalArgumentException();
            }

            System.out.println("Enter monster's health. It should be more than 0.");
            health = Integer.parseInt(reader.readLine());
            if (health < 1) {
                throw new IllegalArgumentException();
            }

            System.out.println("Enter monster's minimal damage. It should be more than 0.");
            damageMin = Integer.parseInt(reader.readLine());
            if (damageMin < 1) {
                throw new IllegalArgumentException();
            }

            System.out.println("Enter monster's maximal damage. It should be more than minimal damage.");
            damageMax = Integer.parseInt(reader.readLine());
            if (damageMax < damageMin) {
                throw new IllegalArgumentException();
            }
        } catch (Exception e) {
            System.out.println("Incorrect characteristics were entered. Try again and enter correct characteristics, please.");
        }

        return new Monster(attackPower, defence, health, damageMin, damageMax);
    }

    public void monsterAttack(Monster monster, Player player) {
        if (monster.attack(player)) {
            System.out.print("Monster attacked player! Damage dealt: " + player.getAttackDamage());
            System.out.println("\tPlayer health:   " + player.getHealth());
        } else {
            System.out.println("Monster missed!");
        }
    }

    public void winCondition(Player player) {
        if (player.isAlive()) {
            System.out.println("Player won!");
        } else {
            System.out.println("Monster won!");
        }
    }

    public void healCondition(Player player) {
        System.out.printf("\nYou have %d healing potions. Would you like to heal? Enter y/n%n", player.getHealingCount());
        String answer;

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
            System.out.println("Incorrect answer was entered. Try to heal in the next round! Enter correct answer next time or you can die.");
        }
    }
}
