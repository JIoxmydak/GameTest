public abstract class Creature {
    private final int attackPower;
    private final int defence;
    private int health;
    private final int minDamage;
    private final int maxDamage;
    private int attackDamage;

    // Constructor for the Creature class, used to initialize its attributes.
    public Creature(int attackPower, int defence, int health, int minDamage, int maxDamage) {
        // Initialize the attack power attribute with the provided value.
        this.attackPower = attackPower;

        // Initialize the defense attribute with the provided value.
        this.defence = defence;

        // Initialize the health attribute with the provided value.
        this.health = health;

        // Initialize the minimum damage attribute with the provided value.
        this.minDamage = minDamage;

        // Initialize the maximum damage attribute with the provided value.
        this.maxDamage = maxDamage;
    }

    // Get the creature's current health.
    public int getHealth() {
        return health;
    }

    // Set the creature's health to a specific value.
    public void setHealth(int health) {
        this.health = health;
    }

    // Check if the creature is alive based on its health.
    public boolean isAlive() {
        return health > 0;
    }

    public void performAttack(Creature target) throws InterruptedException {
        // Get the names of the attacker and target creatures (e.g., "Player" or "Monster").
        String attackerName = getClass().getSimpleName();
        String targetName = target.getClass().getSimpleName();

        // Check if the attack is successful.
        if (this.attack(target)) {
            if (!target.isAlive()) {
                // If the attack defeats the target, display a victory message.
                System.out.printf("%s killed %s!%n", attackerName, targetName);
                System.out.println("\n------------------------------------------------------------\n");
            } else {
                // If the attack damages the target, display the damage dealt and target's health.
                System.out.printf("%s attacked %s! Damage dealt: %d\t%s's health: %d%n", attackerName, targetName, target.attackDamage, targetName, target.getHealth());
            }
        } else {
            // If the attack misses, display a "missed" message.
            System.out.println(attackerName + " missed!");
        }

        Thread.sleep(2000);
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
