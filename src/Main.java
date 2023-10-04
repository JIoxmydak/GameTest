import java.util.Scanner;

/*  1. Сделать ввод данных с обработкой и выбрасыванием исключения под каждый кейс через bufferedReader.
    2. Сделать игру в отдельном методе.
    3. Добавить еще пару классов.
 */

public class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        Player player = null;
        Monster monster = null;
        System.out.println("2 creatures are required to start a game");
        System.out.println("Would you like to create a player or a monster? Enter p/m");
        String creatureType = console.nextLine();
        switch (creatureType) {
            case "p" -> {
                System.out.println("Enter player characteristics in the next order: attackPower(1-30) defence(1-30) health(>0) damageMin(>0) damageMax(>damageMin)");
                player = new Player(console.nextInt(), console.nextInt(), console.nextInt(), console.nextInt(), console.nextInt());
                System.out.println("Now you should enter monster characteristics in the next order: attackPower(1-30) defence(1-30) health(>0) damageMin(>0) damageMax(>damageMin)");
                monster = new Monster(console.nextInt(), console.nextInt(), console.nextInt(), console.nextInt(), console.nextInt());
            }
            case "m" -> {
                System.out.println("Enter monster characteristics in the next order: attackPower(1-30) defence(1-30) health(>0) damageMin(>0) damageMax(>damageMin)");
                monster = new Monster(console.nextInt(), console.nextInt(), console.nextInt(), console.nextInt(), console.nextInt());
                System.out.println("Now you should enter player characteristics in the next order: attackPower(1-30) defence(1-30) health(>0) damageMin(>0) damageMax(>damageMin)");
                player = new Player(console.nextInt(), console.nextInt(), console.nextInt(), console.nextInt(), console.nextInt());
            }
        }

        if (player != null) {
            while (player.isAlive() && monster.isAlive()) {
                if (player.attack(monster)) {
                    System.out.print("Player attacked monster! Damage dealt: " + monster.attackDamage);
                    System.out.println("\tMonster health:   " + monster.getHealth());
                } else {
                    System.out.println("Player missed!");
                }

                if (!monster.attack(player)) {
                    System.out.println("Monster missed!");
                } else {
                    System.out.print("Monster attacked player! Damage dealt: " + player.attackDamage);
                    System.out.println("\tPlayer health:   " + player.getHealth());
                    if (player.getHealingCount() > 0) {
                        System.out.println("Would you like to heal? Enter: y/n");
                        String answer = console.nextLine();
                        if (answer.equalsIgnoreCase("y")) {
                            player.heal();
                            System.out.println("Player was healed: +" + player.getHealedAmount());
                            System.out.println("Player health: " + player.getHealth());
                        }
                    }
                }
                System.out.println("------------------------------------------------------------");
            }

            if (player.isAlive()) {
                System.out.println("Player won!");
            } else {
                System.out.println("Monster won!");
            }
        }

    }
}