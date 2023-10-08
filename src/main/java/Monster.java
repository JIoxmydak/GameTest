public class Monster extends Creature {
    public Monster(int attackPower, int defence, long health, long minDamage, long maxDamage) {
        // Call the constructor of the parent class (Creature).
        super(attackPower, defence, health, minDamage, maxDamage);
        System.out.printf("\nYou will fight against a real monster!\nAttack power: %d | Defence: %d | Health: %d | Minimal damage: %d | Maximal Damage: %d%n", attackPower, defence, health, minDamage, maxDamage);
    }
}
