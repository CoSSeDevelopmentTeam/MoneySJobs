package net.comorevi.np.moneys.jobs.manager;

import net.comorevi.np.moneys.jobs.data.EnumJob;

public class LevelCalculator {
    private static final LevelCalculator instance = new LevelCalculator();

    public boolean levelUp(String name) {
        int currentExp = JobDataHandler.getInstance().getJobData(name).getInt("exp");
        if (currentExp == 0) return false;

        return (currentExp >= calcNeededExp(EnumJob.getJobById(JobDataHandler.getInstance().getJobData(name).getInt("job")), JobDataHandler.getInstance().getJobData(name).getInt("level")) + 1);
    }

    public int calcNeededExp(EnumJob job, int targetLevel) {
        int result = job.getInitialCost();
        if (targetLevel == JobDataHandler.INITIAL_LEVEL + 1) return job.getInitialCost();
        for (int i = JobDataHandler.INITIAL_LEVEL; i < targetLevel; i++) {
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
