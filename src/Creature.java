public class Creature {
    private final int attackPower;
    private final int defence;
    private int health;
    private final int minDamage;
    private final int maxDamage;
    private int attackDamage;

    public Creature(int attackPower, int defence, int health, int minDamage, int maxDamage) {
        this.attackPower = attackPower;
        this.defence = defence;
        this.health = health;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
    }

    public int getHealth() {
        return this.health;
    }
    public int getAttackDamage() {
        return attackDamage;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public boolean isAlive() {
        return health > 0;
    }
    private int calculateDamage() {
        return (int) (Math.random() * (maxDamage - minDamage + 1)) + minDamage;
    }

    private void takeDamage(int damage) {
        attackDamage = damage;
        health -= damage;
        if (health < 0 ) {
            health = 0;
        }
    }

    public boolean attack(Creature opponent) {
        int attackModifier = attackPower - opponent.defence + 1;
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
            opponent.takeDamage(calculateDamage());
        }

        return isHit;
    }
}
