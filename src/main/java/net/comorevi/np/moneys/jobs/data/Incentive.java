package net.comorevi.np.moneys.jobs.data;

import cn.nukkit.block.Block;
import cn.nukkit.block.BlockID;
import cn.nukkit.entity.mob.*;

public class Incentive {
    public enum Creatures {
        ZOMBIE(EntityZombie.NETWORK_ID, 2.0, 5),
        HUSK(EntityHusk.NETWORK_ID, 2.0, 5),
        DROWNED(EntityDrowned.NETWORK_ID, 2.0, 5),
        SKELETON(EntitySkeleton.NETWORK_ID, 2.5, 5),
        CREEPER(EntityCreeper.NETWORK_ID, 2.5, 5),
        SPIDER(EntitySpider.NETWORK_ID, 2.0, 5);

        private final int id;
        private final double bonus;
        private final int exp;

        private Creatures(int id, double bonus, int exp) {
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
            for (Creatures value : Creatures.values()) {
                if (value.getId() == id) return value.getBonus();
            }
            return 1;
        }

        public static boolean isRegistered(int id) {
            for (Creatures value : Creatures.values()) {
                if (value.getId() == id) return true;
            }
            return false;
        }

        public static int getExpByNetworkID(int id) {
            for (Creatures value : Creatures.values()) {
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

    public enum Crops {
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

        private Crops(int id, double bonus, int exp) {
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
            for (Crops value : Crops.values()) {
                if (value.getId() == id) return value.getBonus();
            }
            return 1;
        }

        public static boolean isRegistered(int id) {
            for (Crops value : Crops.values()) {
                if (value.getId() == id) return true;
            }
            return false;
        }

        public static int getExpByNetworkID(int id) {
            for (Crops value : Crops.values()) {
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

    public enum Ore {
        COAL_ORE(Block.COAL_ORE, 1.2, 5),
        IRON_ORE(Block.IRON_ORE, 1.2, 5),
        GOLD_ORE(Block.GOLD_ORE, 1.5, 5),
        DIAMOND_ORE(Block.DIAMOND_ORE, 2.5, 5),
        LAPIS_ORE(Block.LAPIS_ORE, 2.0, 5),
        REDSTONE_ORE(Block.REDSTONE_ORE, 2.0, 5);

        private final int id;
        private final double bonus;
        private final int exp;

        private Ore(int id, double bonus, int exp) {
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
            for (Ore value : Ore.values()) {
                if (value.getId() == id) return value.getBonus();
            }
            return 1;
        }

        public static boolean isRegistered(int id) {
            for (Ore value : Ore.values()) {
                if (value.getId() == id) return true;
            }
            return false;
        }

        public static int getExpByNetworkID(int id) {
            for (Ore value : Ore.values()) {
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

    public enum Trees {
        WOOD(Block.WOOD, 2.0, 5),
        WOOD2(Block.WOOD2, 2.0, 5),
        LEAVE(Block.LEAVE, 1.5, 5),
        LEAVE2(Block.LEAVE2, 1.5, 5);

        private final int id;
        private final double bonus;
        private final int exp;

        private Trees(int id, double bonus, int exp) {
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
            for (Trees value : Trees.values()) {
                if (value.getId() == id) return value.getBonus();
            }
            return 1;
        }

        public static boolean isRegistered(int id) {
            for (Trees value : Trees.values()) {
                if (value.getId() == id) return true;
            }
            return false;
        }

        public static int getExpByNetworkID(int id) {
            for (Trees value : Trees.values()) {
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
}
