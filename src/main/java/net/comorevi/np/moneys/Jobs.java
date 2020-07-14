package net.comorevi.np.moneys;

import cn.nukkit.plugin.PluginBase;
import net.comorevi.np.moneys.command.JobsCommand;

public class Jobs extends PluginBase {

    private static Jobs instance;

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

    public static Jobs getInstance() {
        return instance;
    }
}
