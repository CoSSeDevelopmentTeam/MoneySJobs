package net.comorevi.np.moneys.jobs.data;

public enum EnumJob {
    TREE_CUTTER("木こり", 0, 500, 1000, 2.5),
    MINER("鉱夫", 1, 500, 1000, 2.5),
    FARMER("農家", 2, 500, 1000, 2.5),
    SWORDSMAN("剣士", 3, 1500, 1000, 2.5);

    private final String name;
    private final int id;
    private final int cost;
    private final int initialCost;
    private final double levelCost;

    private EnumJob(String name, int id, int cost, int initialCost, double levelCost) {
        this.name = name;
        this.id = id;
        this.cost = cost;
        this.initialCost = initialCost;
        this.levelCost = levelCost;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getCost() {
        return cost;
    }

    public int getInitialCost() {
        return initialCost;
    }

    public double getLevelCost() {
        return levelCost;
    }

    public static EnumJob getJobById(int id) {
        for (EnumJob value : EnumJob.values()) {
            if (value.getId() == id) return value;
        }
        throw new IllegalArgumentException("指定されたID(" + id + ")のデータは見つかりませんでした。");
    }

    @Override
    public String toString() {
        return "EnumJob{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", cost=" + cost +
                ", levelCost=" + levelCost +
                '}';
    }
}
