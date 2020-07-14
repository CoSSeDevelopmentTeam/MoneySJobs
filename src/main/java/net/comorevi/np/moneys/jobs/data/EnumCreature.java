package net.comorevi.np.moneys.jobs.data;

import cn.nukkit.entity.mob.*;

public enum EnumCreature {
    ZOMBIE(EntityZombie.NETWORK_ID, 2.0, 5),
    HUSK(EntityHusk.NETWORK_ID, 2.0, 5),
    DROWNED(EntityDrowned.NETWORK_ID, 2.0, 5),
    SKELETON(EntitySkeleton.NETWORK_ID, 2.5, 5),
    CREEPER(EntityCreeper.NETWORK_ID, 2.5, 5),
    SPIDER(EntitySpider.NETWORK_ID, 2.0, 5);

    private final int id;
    private final double bonus;
    private final int exp;

    private EnumCreature(int id, double bonus, int exp) {
        this.id = id;
        this.bonus = bonus;
        this.exp = exp;
    }

    public int getId() {
        return id;
    }

    public double getBonus() {
        return bonus;
    }

    public int getExp() {
        return exp;
    }

    public static double getBonusByEntityNetworkID(int id) {
        for (EnumCreature value : EnumCreature.values()) {
            if (value.getId() == id) return value.getBonus();
        }
        return 1;
    }

    public static boolean isRegistered(int id) {
        for (EnumCreature value : EnumCreature.values()) {
            if (value.getId() == id) return true;
        }
        return false;
    }

    public static int getExpByNetworkID(int id) {
        for (EnumCreature value : EnumCreature.values()) {
            if (value.getId() == id) return value.getExp();
        }
        return 1;
    }

    @Override
    public String toString() {
        return "EnumWarrior{" +
                "id=" + id +
                ", bonus=" + bonus +
                '}';
    }
}
