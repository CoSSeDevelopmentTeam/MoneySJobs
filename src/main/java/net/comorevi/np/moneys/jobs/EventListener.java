package net.comorevi.np.moneys.jobs;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockBreakEvent;
import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.event.entity.EntityDeathEvent;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerQuitEvent;
import cn.nukkit.level.Sound;
import cn.nukkit.utils.TextFormat;
import net.comorevi.np.moneys.MoneySAPI;
import net.comorevi.np.moneys.jobs.data.*;
import net.comorevi.np.moneys.jobs.util.DataStore;
import net.comorevi.np.moneys.jobs.util.JobDataHandler;
import net.comorevi.np.moneys.jobs.util.LevelCalculator;

public class EventListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        JobDataHandler.getInstance().addJobData(event.getPlayer().getName());
        JobDataHandler.getInstance().loadJobData(event.getPlayer().getName());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (DataStore.list.containsKey(event.getPlayer().getName())) {
            JobDataHandler.getInstance().saveJobData(event.getPlayer().getName());
            DataStore.list.remove(event.getPlayer().getName());
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        PlayerData data = DataStore.list.get(event.getPlayer().getName());
        AvailableJobs job = AvailableJobs.getJobById(data.getJobId());
        if (!event.getBlock().getLevel().getName().equals("resource") && (job == AvailableJobs.TREE_CUTTER || job == AvailableJobs.MINER || job == AvailableJobs.SWORDSMAN)) return;
        if (job == AvailableJobs.TREE_CUTTER) {
            MoneySAPI.getInstance().addCoin(event.getPlayer(), (int) (Reward.Blocks.getRewardCoinById(event.getBlock().getId()) * Incentive.Trees.getBonusByBlockID(event.getBlock().getId()) * data.getLevel()));
            if (Incentive.Trees.isRegistered(event.getBlock().getId())) {
                data.addExp(Incentive.Trees.getExpByNetworkID(event.getBlock().getId()));
                if (LevelCalculator.getInstance().levelUp(event.getPlayer().getName())) levelUp(event.getPlayer());
            }
        } else if (job == AvailableJobs.MINER) {
            MoneySAPI.getInstance().addCoin(event.getPlayer(), (int) (Reward.Blocks.getRewardCoinById(event.getBlock().getId()) * Incentive.Ore.getBonusByBlockID(event.getBlock().getId()) * data.getLevel()));
            if (Incentive.Ore.isRegistered(event.getBlock().getId())) {
                data.addExp(Incentive.Ore.getExpByNetworkID(event.getBlock().getId()));
                if (LevelCalculator.getInstance().levelUp(event.getPlayer().getName())) levelUp(event.getPlayer());
            }
        } else if (job == AvailableJobs.FARMER) {
            if (event.getBlock().getLevel().getName().equals("resource")) {
                MoneySAPI.getInstance().addCoin(event.getPlayer(), (int) (Reward.Blocks.getRewardCoinById(event.getBlock().getId()) * Incentive.Crops.getBonusByBlockID(event.getBlock().getId()) * data.getLevel()));
            } else {
                if (Incentive.Crops.isRegistered(event.getBlock().getId())) {
                    MoneySAPI.getInstance().addCoin(event.getPlayer(), (int) (Reward.Blocks.getRewardCoinById(event.getBlock().getId()) * Incentive.Crops.getBonusByBlockID(event.getBlock().getId()) * data.getLevel()));
                    data.addExp(Incentive.Crops.getExpByNetworkID(event.getBlock().getId()));
                    if (LevelCalculator.getInstance().levelUp(event.getPlayer().getName())) levelUp(event.getPlayer());
                }
            }

        } else {
            MoneySAPI.getInstance().addCoin(event.getPlayer(), Reward.Blocks.getRewardCoinById(event.getBlock().getId()));
        }
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (!event.getEntity().getLevel().getName().equals("resource")) return;
        if (event.getEntity().getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK || event.getEntity().getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.PROJECTILE) {
            if (event.getEntity().getLastDamageCause() instanceof EntityDamageByEntityEvent) {
                EntityDamageByEntityEvent event2 = (EntityDamageByEntityEvent) event.getEntity().getLastDamageCause();
                if (event2.getDamager() instanceof Player) {
                    PlayerData data = DataStore.list.get(event2.getDamager().getName());
                    int defReward = Reward.Mobs.getRewardCoinById(event.getEntity().getNetworkId());
                    MoneySAPI.getInstance().addCoin((Player) event2.getDamager(), data.getJobId() == AvailableJobs.SWORDSMAN.getId() ? (int) (defReward * Incentive.Creatures.getBonusByEntityNetworkID(event.getEntity().getNetworkId()) * data.getLevel()) : defReward);
                    if (Incentive.Creatures.isRegistered(event.getEntity().getNetworkId())) {
                        data.addExp(Incentive.Creatures.getExpByNetworkID(event.getEntity().getNetworkId()));
                        if (LevelCalculator.getInstance().levelUp(event2.getDamager().getName())) levelUp((Player) event2.getDamager());
                    }
                }
            }
        }
    }

    private void levelUp(Player player) {
        player.getLevel().addSound(player.getPosition().up(10), Sound.RANDOM_LEVELUP);
        player.sendTitle(TextFormat.YELLOW + "L" + TextFormat.WHITE + "evel" + TextFormat.RED + "U" + TextFormat.WHITE + "p!!");
        DataStore.getInstance().addLevel(player.getName(), 1);
    }
}
