package net.comorevi.np.moneys.jobs;

import cn.nukkit.plugin.PluginBase;
import net.comorevi.np.moneys.jobs.command.JobsCommand;

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

    public static Main getInstance() {
        return instance;
    }
}
