package net.comorevi.np.moneys.data;

import cn.nukkit.block.Block;

public enum EnumOre {
    COAL_ORE(Block.COAL_ORE, 1.2, 5),
    IRON_ORE(Block.IRON_ORE, 1.2, 5),
    GOLD_ORE(Block.GOLD_ORE, 1.5, 5),
    DIAMOND_ORE(Block.DIAMOND_ORE, 2.5, 5),
    LAPIS_ORE(Block.LAPIS_ORE, 2.0, 5),
    REDSTONE_ORE(Block.REDSTONE_ORE, 2.0, 5);

    private final int id;
    private final double bonus;
    private final int exp;

    private EnumOre(int id, double bonus, int exp) {
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

    public static double getBonusByBlockID(int id) {
        for (EnumOre value : EnumOre.values()) {
            if (value.getId() == id) return value.getBonus();
        }
        return 1;
    }

    public static boolean isRegistered(int id) {
        for (EnumOre value : EnumOre.values()) {
            if (value.getId() == id) return true;
        }
        return false;
    }

    public static int getExpByNetworkID(int id) {
        for (EnumOre value : EnumOre.values()) {
            if (value.getId() == id) return value.getExp();
        }
        return 1;
    }

    @Override
    public String toString() {
        return "EnumMiner{" +
                "id=" + id +
                ", bonus=" + bonus +
                '}';
    }
}
