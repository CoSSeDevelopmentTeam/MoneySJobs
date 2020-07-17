package net.comorevi.np.moneys.jobs.util;

import cn.nukkit.utils.Config;
import cn.nukkit.utils.ConfigSection;
import net.comorevi.np.moneys.jobs.data.AvailableJobs;
import net.comorevi.np.moneys.jobs.data.PlayerData;

import java.io.File;

public class JobDataHandler {
    private static final JobDataHandler instance = new JobDataHandler();
    private final Config config;
    public static final int INITIAL_LEVEL = 1;

    private JobDataHandler() {
        config = new Config(new File("./plugins/MoneySJobs", "config.yml"), Config.YAML);
    }

    public boolean existsJobData(String name) {
        return config.exists(name);
    }

    public boolean addJobData(String name) {
        if (existsJobData(name)) {
            return false;
        } else {
            config.set(name + ".job", AvailableJobs.TREE_CUTTER.getId());
            config.set(name + ".level", 1);
            config.set(name + ".exp", 0);
            return save();
        }
    }

    public boolean loadJobData(String name) {
        if (!existsJobData(name)) {
            return false;
        } else {
            ConfigSection cs = config.getSection(name);
            DataStore.list.put(name, new PlayerData(cs.getInt("job"), cs.getInt("exp"), cs.getInt("level")));
            return true;
        }
    }

    public boolean saveJobData(String name) {
        PlayerData playerData = DataStore.list.get(name);
        config.set(name + ".job", playerData.getJobId());
        config.set(name + ".level", playerData.getLevel());
        config.set(name + ".exp", playerData.getExp());
        return save();
    }

    private boolean save() {
        return config.save();
    }

    public static JobDataHandler getInstance() {
        return instance;
    }
}
