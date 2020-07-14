package net.comorevi.np.moneys.jobs.data;

import cn.nukkit.block.BlockID;
import cn.nukkit.entity.mob.*;

public class EnumDefault {
    public enum Mobs {
        ZOMBIE(EntityZombie.NETWORK_ID, 800),
        HUSK(EntityHusk.NETWORK_ID, 850),
        DROWNED(EntityDrowned.NETWORK_ID, 850),
        SKELETON(EntitySkeleton.NETWORK_ID, 800),
        CREEPER(EntityCreeper.NETWORK_ID, 1000),
        SPIDER(EntitySpider.NETWORK_ID, 800);

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
        STONE(BlockID.STONE, 200),
        GRASS(BlockID.GRASS, 400),
        DIRT(BlockID.DIRT, 200),
        SAND(BlockID.SAND, 200),
        SANDSTONE(BlockID.SANDSTONE, 300),
        WOOD(BlockID.WOOD, 600),
        WOOD2(BlockID.WOOD2, 600),
        LEAVE(BlockID.LEAVE, 500),
        LEAVE2(BlockID.LEAVE2, 500),
        DIAMOND_ORE(BlockID.DIAMOND_ORE, 1000),
        GOLD_ORE(BlockID.GOLD_ORE, 800),
        IRON_ORE(BlockID.IRON_ORE, 700),
        COAL_ORE(BlockID.COAL_ORE, 700),
        LAPIS_ORE(BlockID.LAPIS_ORE, 600),
        REDSTONE_ORE(BlockID.REDSTONE_ORE, 600),
        EMERALD_ORE(BlockID.EMERALD_ORE, 800);

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
