public class Creature {
    private final int attackPower; // The creature's attack power.
    private final int defence; // The creature's defense.
    private int health; // The creature's current health.
    private final int minDamage; // The minimum damage the creature can deal.
    private final int maxDamage; // The maximum damage the creature can deal.
    private int attackDamage; // The damage dealt in the current attack.

    public Creature(int attackPower, int defence, int health, int minDamage, int maxDamage) {
        this.attackPower = attackPower; // Initialize the creature's attack power.
        this.defence = defence; // Initialize the creature's defense.
        this.health = health; // Initialize the creature's health.
        this.minDamage = minDamage; // Initialize the minimum damage.
        this.maxDamage = maxDamage; // Initialize the maximum damage.
    }

    public int getHealth() {
        return this.health; // Get the creature's current health.
    }

    public int getAttackDamage() {
        return attackDamage; // Get the damage dealt in the current attack.
    }

    public void setHealth(int health) {
        this.health = health; // Set the creature's health to a specific value.
    }

    public boolean isAlive() {
        return health > 0; // Check if the creature is alive based on its health.
    }

    private int calculateDamage() {
        return (int) (Math.random() * (maxDamage - minDamage + 1)) + minDamage; // Calculate a random damage value within the specified range.
    }

    private void takeDamage(int damage) {
        attackDamage = damage; // Record the damage dealt in the current attack.
        health -= damage; // Reduce the creature's health by the received damage.
        if (health < 0) {
            health = 0; // Ensure that health does not go below 0.
        }
    }

    public boolean attack(Creature opponent) {
        int attackModifier = attackPower - opponent.defence + 1; // Calculate the attack modifier.
        int diceAmount = Math.max(1, attackModifier); // Determine the number of dice rolls for the attack.
        boolean isHit = false;

        while (diceAmount > 0) {
            int diceNumber = (int) (Math.random() * 5) + 1; // Simulate a dice roll with 5 sides.
            if (diceNumber > 4) {
                isHit = true; // If the dice roll is successful (5 or higher), the attack hits.
                break;
            }
            diceAmount--;
        }

        if (isHit) {
            opponent.takeDamage(calculateDamage()); // If the attack hits, calculate and apply damage to the opponent.
        }

        return isHit; // Return whether the attack was successful (hit the opponent).
    }
}
