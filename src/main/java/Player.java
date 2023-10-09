public class Player extends Creature {
    private final int healthMax;
    private int healingCount;
    private final double healingMultiplier;

    /**
     * Constructor for the Player class, representing the player character.
     *
     * @param attackPower The attack power of the player.
     * @param defence The defense of the player.
     * @param health The initial health of the player.
     * @param minDamage The minimum damage the player can deal.
     * @param maxDamage The maximum damage the player can deal.
     */
    public Player(int attackPower, int defence, int health, int minDamage, int maxDamage) {
        super(attackPower, defence, health, minDamage, maxDamage);
        healthMax = health;
        healingCount = 4;
        healingMultiplier = 0.3;
    }

    /**
     * Allows the player to use a healing potion, restoring a portion of their health.
     */
    public void heal() {
        int healedAmount = (int) (healthMax * healingMultiplier);
        int healthAfterHealing = getHealth() + healedAmount;
        if (healthAfterHealing <= 0 || healthAfterHealing > healthMax) {
            healthAfterHealing = healthMax;
        }

        setHealth(healthAfterHealing);
        System.out.printf("Player was healed on %d points. Player's health: %d%n", healedAmount, getHealth());
        healingCount--;
    }

    /**
     * Retrieves the current count of healing potions available to the player.
     *
     * @return The count of available healing potions.
     */
    public int getHealingCount() {
        return healingCount;
    }

}
