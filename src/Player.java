public class Player extends Creature {
    private final int healthMax;
    private int healingCount;
    private int healedAmount;
    private final double healingMultiplier;

    public Player(int attackPower, int defence, int health, int damageMin, int damageMax) {
        super(attackPower, defence, health, damageMin, damageMax);
        healthMax = health;
        healingCount = 4;
        healingMultiplier = 0.3;
    }

    public void heal() {
        if (healingCount > 0 && isAlive()) {

            healedAmount = (int) (getHealth() * healingMultiplier);
            setHealth(Math.min(healthMax, getHealth() + healedAmount));
            healingCount--;
        }
    }

    public int getHealedAmount() {
        return healedAmount;
    }

    public int getHealingCount() {
        return healingCount;
    }
}
