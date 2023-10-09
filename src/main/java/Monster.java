public class Monster extends Creature {
    /**
     * Constructor for the Monster class, representing enemy monsters in the game.
     *
     * @param attackPower The attack power of the monster.
     * @param defence The defense of the monster.
     * @param health The initial health of the monster.
     * @param minDamage The minimum damage the monster can deal.
     * @param maxDamage The maximum damage the monster can deal.
     */
    public Monster(int attackPower, int defence, int health, int minDamage, int maxDamage) {
        super(attackPower, defence, health, minDamage, maxDamage);
        System.out.printf("Attack power: %d | Defence: %d | Health: %d | Minimal damage: %d | Maximal Damage: %d%n", attackPower, defence, health, minDamage, maxDamage);
    }
}
