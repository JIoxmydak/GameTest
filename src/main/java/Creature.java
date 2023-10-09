public class Creature {
    private final int attackPower;
    private final int defence;
    private int health;
    private final int minDamage;
    private final int maxDamage;
    private int attackDamage;

    /**
     * Constructor for the Creature class, which represents characters in the game.
     *
     * @param attackPower The attack power of the creature.
     * @param defence The defense of the creature.
     * @param health The initial health of the creature.
     * @param minDamage The minimum damage the creature can deal.
     * @param maxDamage The maximum damage the creature can deal.
     */
    public Creature(int attackPower, int defence, int health, int minDamage, int maxDamage) {
        this.attackPower = attackPower;
        this.defence = defence;
        this.health = health;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
    }

    /**
     * Retrieves the current health of the creature.
     *
     * @return The current health of the creature.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Sets the health of the creature to a specific value.
     *
     * @param health The new health value to set.
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Checks if the creature is alive based on its health.
     *
     * @return true if the creature's health is greater than 0; false otherwise.
     */
    public boolean isAlive() {
        return health > 0;
    }

    /**
     * Performs an attack on a target creature.
     *
     * @param target The target creature to attack.
     */
    public void performAttack(Creature target) throws InterruptedException {
        String attackerName = getClass().getSimpleName();
        String targetName = target.getClass().getSimpleName();

        if (attack(target)) {
            if (!target.isAlive()) {
                System.out.printf("%s killed %s!%n", attackerName, targetName);
                System.out.println("\n------------------------------------------------------------\n");
            } else {
                System.out.printf("%s attacked %s! Damage dealt: %d\t%s's health: %d%n", attackerName, targetName, target.attackDamage, targetName, target.getHealth());
            }
        } else {
            System.out.println(attackerName + " missed!");
        }

        Thread.sleep(1000);
    }

    private int calculateDamage() {
        return (int) (Math.random() * (maxDamage - minDamage + 1)) + minDamage;
    }

    private void takeDamage(int damage) {
        attackDamage = damage;
        health -= damage;
        if (health < 0) {
            health = 0;
        }
    }

    private boolean attack(Creature target) {
        int attackModifier = attackPower - target.defence + 1;
        int diceAmount = Math.max(1, attackModifier);
        boolean isHit = false;

        while (diceAmount > 0) {
            int diceNumber = (int) (Math.random() * 5) + 1;
            if (diceNumber > 4) {
                isHit = true;
                break;
            }
            diceAmount--;
        }

        if (isHit) {
            target.takeDamage(calculateDamage());
        }

        return isHit;
    }
}
