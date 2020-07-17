package net.comorevi.np.moneys.jobs.util;

import net.comorevi.np.moneys.jobs.data.AvailableJobs;
import net.comorevi.np.moneys.jobs.data.PlayerData;

public class LevelCalculator {
    private static final LevelCalculator instance = new LevelCalculator();

    public boolean levelUp(String name) {
        PlayerData data = DataStore.list.get(name);
        if (data.getExp() == 0) return false;

        return (data.getExp() >= calcNeededExp(AvailableJobs.getJobById(data.getJobId()), data.getLevel() + 1));
    }

    public int calcNeededExp(AvailableJobs job, int nextLv) {
        if (nextLv == JobDataHandler.INITIAL_LEVEL + 1) return job.getInitialCost();
        int result = job.getInitialCost();
        for (int i = JobDataHandler.INITIAL_LEVEL; i < nextLv; i++) {
            result *= job.getLevelCost();
        }
        return result;
    }

    private LevelCalculator() {

    }

    public static LevelCalculator getInstance() {
        return instance;
    }
}
