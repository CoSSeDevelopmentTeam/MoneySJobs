package net.comorevi.np.moneys.jobs.data;

import cn.nukkit.block.BlockID;

public enum EnumCrops {
    WHEAT_CROPS(BlockID.WHEAT_BLOCK, 1.5, 5),
    CACTUS(BlockID.CACTUS, 1.5, 5),
    PUMPKIN(BlockID.PUMPKIN, 2.5, 5),
    MELON_BLOCK(BlockID.MELON_BLOCK, 2.5, 5),
    CARROTS(BlockID.CARROT_BLOCK, 2.0, 5),
    POTATOES(BlockID.POTATO_BLOCK, 2.0, 5),
    BEETROOT(BlockID.BEETROOT_BLOCK, 2.0, 5);

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
