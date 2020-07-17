package net.comorevi.np.moneys.jobs.data;

public class PlayerData {
    private int jobId;
    private int exp;
    private int level;

    public PlayerData(int jobId, int exp, int level) {
        this.jobId = jobId;
        this.exp = exp;
        this.level = level;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getJobId() {
        return jobId;
    }

    public int getExp() {
        return exp;
    }

    public int getLevel() {
        return level;
    }

    public void addExp(int value) {
        this.exp += value;
    }

    public void addLevel(int value) {
        this.level += value;
    }
}
