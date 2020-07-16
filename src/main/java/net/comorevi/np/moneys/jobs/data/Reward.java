package net.comorevi.np.moneys.jobs.data;

import cn.nukkit.block.BlockID;
import cn.nukkit.entity.mob.*;

public class Reward {
    public enum Mobs {
        ZOMBIE(EntityZombie.NETWORK_ID, 400),
        HUSK(EntityHusk.NETWORK_ID, 425),
        DROWNED(EntityDrowned.NETWORK_ID, 425),
        SKELETON(EntitySkeleton.NETWORK_ID, 400),
        CREEPER(EntityCreeper.NETWORK_ID, 500),
        SPIDER(EntitySpider.NETWORK_ID, 400);

        private final int id;
        private final int coin;

        Mobs(int id, int coin) {
            this.id = id;
            this.coin = coin;
        }

        public int getId() {
            return id;
        }

        public int getCoin() {
            return coin;
        }

        public static int getRewardCoinById(int id) {
            for (Mobs type : Mobs.values()) {
                if (type.getId() == id) return type.getCoin();
            }
            return 100;
        }
    }

    public enum Blocks {
        STONE(BlockID.STONE, 100),
        GRASS(BlockID.GRASS, 200),
        DIRT(BlockID.DIRT, 100),
        SAND(BlockID.SAND, 100),
        SANDSTONE(BlockID.SANDSTONE, 150),
        WOOD(BlockID.WOOD, 300),
        WOOD2(BlockID.WOOD2, 300),
        LEAVE(BlockID.LEAVE, 250),
        LEAVE2(BlockID.LEAVE2, 250),
        DIAMOND_ORE(BlockID.DIAMOND_ORE, 500),
        GOLD_ORE(BlockID.GOLD_ORE, 400),
        IRON_ORE(BlockID.IRON_ORE, 350),
        COAL_ORE(BlockID.COAL_ORE, 350),
        LAPIS_ORE(BlockID.LAPIS_ORE, 300),
        REDSTONE_ORE(BlockID.REDSTONE_ORE, 300),
        EMERALD_ORE(BlockID.EMERALD_ORE, 400);

        private final int id;
        private final int coin;

        Blocks(int id, int coin) {
            this.id = id;
            this.coin = coin;
        }

        public int getId() {
            return id;
        }

        public int getCoin() {
            return coin;
        }

        public static int getRewardCoinById(int id) {
            for (Blocks type : Blocks.values()) {
                if (type.getId() == id) return type.getCoin();
            }
            return 50;
        }
    }
}
