package net.comorevi.np.moneys.jobs;

import cn.nukkit.plugin.PluginBase;
import net.comorevi.np.moneys.jobs.command.JobsCommand;
import net.comorevi.np.moneys.jobs.util.DataStore;
import net.comorevi.np.moneys.jobs.util.JobDataHandler;

public class Main extends PluginBase {

    private static Main instance;

    @Override
    public void onLoad() {
        saveDefaultConfig();
    }

    @Override
    public void onEnable() {
        instance = this;
        getServer().getPluginManager().registerEvents(new EventListener(), this);
        getServer().getCommandMap().register("MoneySJobs", new JobsCommand("jobs", "ジョブシステムの管理画面を表示します。", "/jobs"));
    }

    @Override
    public void onDisable() {
        DataStore.list.keySet().forEach(name -> {
            JobDataHandler.getInstance().saveJobData(name);
        });
        DataStore.list.clear();
    }

    public static Main getInstance() {
        return instance;
    }
}
