package net.comorevi.np.moneys.jobs;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockBreakEvent;
import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.event.entity.EntityDeathEvent;
import cn.nukkit.event.player.PlayerJoinEvent;
import net.comorevi.moneyapi.MoneySAPI;
import net.comorevi.np.moneys.jobs.data.*;
import net.comorevi.np.moneys.jobs.manager.JobDataHandler;
import net.comorevi.np.moneys.jobs.manager.LevelCalculator;

public class EventListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        JobDataHandler.getInstance().addJobData(event.getPlayer().getName());
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        EnumJob job = EnumJob.getJobById(JobDataHandler.getInstance().getJobData(event.getPlayer().getName()).getInt("job"));
        if (job == EnumJob.TREE_CUTTER) {
            MoneySAPI.getInstance().addCoin(event.getPlayer(), (int) (EnumDefault.Blocks.getRewardCoinById(event.getBlock().getId()) * EnumTree.getBonusByBlockID(event.getBlock().getId()) * JobDataHandler.getInstance().getJobData(event.getPlayer().getName()).getInt("level")));
            if (EnumTree.isRegistered(event.getBlock().getId())) {
                JobDataHandler.getInstance().addExp(event.getPlayer().getName(), EnumTree.getExpByNetworkID(event.getBlock().getId()));
                if (LevelCalculator.getInstance().levelUp(event.getPlayer().getName())) JobDataHandler.getInstance().levelUp(event.getPlayer().getName());
            }
        } else if (job == EnumJob.MINER) {
            MoneySAPI.getInstance().addCoin(event.getPlayer(), (int) (EnumDefault.Blocks.getRewardCoinById(event.getBlock().getId()) * EnumOre.getBonusByBlockID(event.getBlock().getId()) * JobDataHandler.getInstance().getJobData(event.getPlayer().getName()).getInt("level")));
            if (EnumOre.isRegistered(event.getBlock().getId())) {
                JobDataHandler.getInstance().addExp(event.getPlayer().getName(), EnumOre.getExpByNetworkID(event.getBlock().getId()));
                if (LevelCalculator.getInstance().levelUp(event.getPlayer().getName())) JobDataHandler.getInstance().levelUp(event.getPlayer().getName());
            }
        } else if (job == EnumJob.FARMER) {
            MoneySAPI.getInstance().addCoin(event.getPlayer(), (int) (EnumDefault.Blocks.getRewardCoinById(event.getBlock().getId()) * EnumCrops.getBonusByBlockID(event.getBlock().getId()) * JobDataHandler.getInstance().getJobData(event.getPlayer().getName()).getInt("level")));
            if (EnumCrops.isRegistered(event.getBlock().getId())) {
                JobDataHandler.getInstance().addExp(event.getPlayer().getName(), EnumCrops.getExpByNetworkID(event.getBlock().getId()));
                if (LevelCalculator.getInstance().levelUp(event.getPlayer().getName())) JobDataHandler.getInstance().levelUp(event.getPlayer().getName());
            }
        } else {
            MoneySAPI.getInstance().addCoin(event.getPlayer(), EnumDefault.Blocks.getRewardCoinById(event.getBlock().getId()));
        }
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (event.getEntity().getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK || event.getEntity().getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.PROJECTILE) {
            if (event.getEntity().getLastDamageCause() instanceof EntityDamageByEntityEvent) {
                EntityDamageByEntityEvent event2 = (EntityDamageByEntityEvent) event.getEntity().getLastDamageCause();
                if (event2.getDamager() instanceof Player) {
                    int defReward = EnumDefault.Mobs.getRewardCoinById(event.getEntity().getNetworkId());
                    MoneySAPI.getInstance().addCoin((Player) event2.getDamager(), JobDataHandler.getInstance().getJobData(((Player) event2.getDamager()).getName()).getInt("job") == EnumJob.SWORDSMAN.getId() ? (int) (defReward * EnumCreature.getBonusByEntityNetworkID(event.getEntity().getNetworkId()) * JobDataHandler.getInstance().getJobData(event2.getDamager().getName()).getInt("level")) : defReward);
                    if (EnumCreature.isRegistered(event.getEntity().getNetworkId())) {
                        JobDataHandler.getInstance().addExp(event2.getDamager().getName(), EnumCreature.getExpByNetworkID(event.getEntity().getNetworkId()));
                        if (LevelCalculator.getInstance().levelUp(event2.getDamager().getName())) JobDataHandler.getInstance().levelUp(event2.getDamager().getName());
                    }
                }
            }
        }
    }
}
