public class Creature {
    private final int attackPower;
    private final int defence;
    private int health;
    private final int damageMin;
    private final int damageMax;
    int attackDamage;
    public Creature(int attackPower, int defence, int health, int damageMin, int damageMax) {



        this.attackPower = attackPower;
        this.defence = defence;
        this.health = health;
        this.damageMin = damageMin;
        this.damageMax = damageMax;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return this.health;
    }

    public boolean isAlive() {
        if (health <= 0) {
            System.out.println();
        }
        return health > 0;
    }

    private int calculateDamage() {
        return (int) (Math.random() * (damageMax - damageMin + 1)) + damageMin;
    }

    private void takeDamage(int damage) {
        health -= damage;
        if (health < 0 ) {
            health = 0;
        }
        attackDamage = damage;
    }

    public boolean attack(Creature opponent) {
        int attackModifier = attackPower - opponent.defence + 1;
        int diceAmount = Math.max(1, attackModifier);
        boolean isHit = false;

        while (diceAmount > 0) {
            int diceNumber = (int) (Math.random() * (6 - 1)) + 1;
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
