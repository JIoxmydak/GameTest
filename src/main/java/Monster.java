public class Monster extends Creature {
    public Monster(int attackPower, int defence, int health, int minDamage, int maxDamage) {
        // Call the constructor of the parent class (Creature).
        super(attackPower, defence, health, minDamage, maxDamage);
        System.out.printf("Attack power: %d | Defence: %d | Health: %d | Minimal damage: %d | Maximal Damage: %d%n", attackPower, defence, health, minDamage, maxDamage);
    }
}
