public class Player extends Creature {
    private final int healthMax; // The maximum health of the player.
    private int healingCount; // The number of healing potions the player has.
    private int healedAmount; // The amount of health restored when healing.
    private final double healingMultiplier; // The multiplier used to calculate the healing amount.

    public Player(int attackPower, int defence, int health, int minDamage, int maxDamage) {
        super(attackPower, defence, health, minDamage, maxDamage); // Call the constructor of the parent class (Creature).

        healthMax = health; // Initialize the maximum health to the player's starting health.
        healingCount = 4; // Initialize the number of healing potions the player has.
        healingMultiplier = 0.3; // Initialize the healing multiplier.
    }


    public void heal() {
        if (healingCount > 0 && isAlive()) { // Check if the player can heal (has healing potions and is alive).
            healedAmount = (int) (healthMax * healingMultiplier); // Calculate the amount of health to be restored.
            setHealth(Math.min(healthMax, getHealth() + healedAmount)); // Increase the player's health without exceeding the maximum.
            healingCount--; // Decrease the number of healing potions available.
        }
    }

    public int getHealedAmount() {
        return healedAmount; // Get the amount of health restored in the last healing.
    }

    public int getHealingCount() {
        return healingCount; // Get the remaining number of healing potions.
    }
}
