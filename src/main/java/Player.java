public class Player extends Creature {
    private final long healthMax;
    private int healingCount;
    private long healedAmount;
    private final double healingMultiplier;

    public Player(int attackPower, int defence, long health, long minDamage, long maxDamage) {
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
            healedAmount = (long) (healthMax * healingMultiplier);
            long healthAfterHealing = getHealth() + healedAmount;
            if (healthAfterHealing <= 0) {
                healthAfterHealing = healthMax;
            }
            setHealth(healthAfterHealing);
            // Decrease the number of healing potions available.
            healingCount--;
        }
    }

    // Get the amount of health restored in the last healing.
    public long getHealedAmount() {
        return healedAmount;
    }

    // Get the remaining number of healing potions.
    public int getHealingCount() {
        return healingCount;
    }

    public void setHealingCount(int healingCount) {
        this.healingCount = healingCount;
    }

    public long getHealthMax() {
        return healthMax;
    }
}
