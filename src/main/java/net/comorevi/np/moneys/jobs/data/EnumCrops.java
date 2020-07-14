package net.comorevi.np.moneys.jobs.data;

import cn.nukkit.block.Block;

public enum EnumCrops {
    WHEAT_CROPS(Block.WHEAT_BLOCK, 2.0, 5),
    CACTUS(Block.CACTUS, 1.5, 5),
    PUMPKIN(Block.PUMPKIN, 3.0, 5),
    MELON_BLOCK(Block.MELON_BLOCK, 3.0, 5),
    CARROTS(Block.CARROT_BLOCK, 2.5, 5),
    POTATOES(Block.POTATO_BLOCK, 2.5, 5),
    BEETROOT(Block.BEETROOT_BLOCK, 3.0, 5);

    private final int id;
    private final double bonus;
    private final int exp;

    private EnumCrops(int id, double bonus, int exp) {
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
        for (EnumCrops value : EnumCrops.values()) {
            if (value.getId() == id) return value.getBonus();
        }
        return 1;
    }

    public static boolean isRegistered(int id) {
        for (EnumCrops value : EnumCrops.values()) {
            if (value.getId() == id) return true;
        }
        return false;
    }

    public static int getExpByNetworkID(int id) {
        for (EnumCrops value : EnumCrops.values()) {
            if (value.getId() == id) return value.getExp();
        }
        return 1;
    }

    @Override
    public String toString() {
        return "EnumCrops{" +
                "id=" + id +
                ", bonus=" + bonus +
                '}';
    }
}
