public class Player extends Creature {
    private final int healthMax;
    private int healingCount;
    private int healedAmount;
    private final double healingMultiplier;

    public Player(int attackPower, int defence, int health, int minDamage, int maxDamage) {
        // Call the constructor of the parent class (Creature).
        super(attackPower, defence, health, minDamage, maxDamage);

        // Initialize the maximum health to the player's starting health.
        healthMax = health;

        // Initialize the number of healing potions the player has.
        healingCount = 4;

        // Initialize the healing multiplier.
        healingMultiplier = 0.3;
    }


    public void heal() {
        // Check if the player can heal (has healing potions and is alive).
        if (healingCount > 0 && isAlive()) {

            // Calculate the amount of health to be restored.
            healedAmount = (int) (healthMax * healingMultiplier);

            // Increase the player's health without exceeding the maximum.
            setHealth(Math.min(healthMax, getHealth() + healedAmount));

            // Decrease the number of healing potions available.
            healingCount--;
        }
    }

    // Get the amount of health restored in the last healing.
    public int getHealedAmount() {
        return healedAmount;
    }

    // Get the remaining number of healing potions.
    public int getHealingCount() {
        return healingCount;
    }
}
