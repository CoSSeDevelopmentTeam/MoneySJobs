package net.comorevi.np.moneys.data;

import cn.nukkit.block.Block;

public enum EnumTree {
    WOOD(Block.WOOD, 2.0, 5),
    WOOD2(Block.WOOD2, 2.0, 5),
    LEAVE(Block.LEAVE, 1.5, 5),
    LEAVE2(Block.LEAVE2, 1.5, 5);

    private final int id;
    private final double bonus;
    private final int exp;

    private EnumTree(int id, double bonus, int exp) {
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
        for (EnumTree value : EnumTree.values()) {
            if (value.getId() == id) return value.getBonus();
        }
        return 1;
    }

    public static boolean isRegistered(int id) {
        for (EnumTree value : EnumTree.values()) {
            if (value.getId() == id) return true;
        }
        return false;
    }

    public static int getExpByNetworkID(int id) {
        for (EnumTree value : EnumTree.values()) {
            if (value.getId() == id) return value.getExp();
        }
        return 1;
    }

    @Override
    public String toString() {
        return "EnumTreeCutter{" +
                "id=" + id +
                ", bonus=" + bonus +
                '}';
    }
}
