package net.comorevi.np.moneys.manager;

import cn.nukkit.utils.Config;
import cn.nukkit.utils.ConfigSection;
import net.comorevi.np.moneys.data.EnumJob;

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
            config.set(name + ".job", EnumJob.TREE_CUTTER.getId());
            config.set(name + ".level", 1);
            config.set(name + ".exp", 0);
            return save();
        }
    }

    public boolean addExp(String name, int value) {
        config.set(name + ".exp", config.getInt(name + ".exp") + value);
        return save();
    }

    public boolean levelUp(String name) {
        config.set(name + ".level", config.getInt(name + ".level") + 1);
        return save();
    }

    public boolean setJobData(String name, EnumJob job) {
        return setJobData(name, job, 0, 0);
    }

    public boolean setJobData(String name, EnumJob job, int level, int exp) {
        config.set(name + ".job", job.getId());
        config.set(name + ".level", level);
        config.set(name + ".exp", exp);
        return save();
    }

    public ConfigSection getJobData(String name) {
        if (!existsJobData(name)) {
            throw new IllegalArgumentException("入力されたプレイヤーのデータは見つかりませんでした: " + name);
        } else {
            return config.getSection(name);
        }
    }

    private boolean save() {
        return config.save();
    }

    public static JobDataHandler getInstance() {
        return instance;
    }
}
