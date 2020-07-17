package net.comorevi.np.moneys.jobs.util;

import net.comorevi.np.moneys.jobs.data.PlayerData;

import java.util.LinkedHashMap;

public class DataStore {
    private static final DataStore instance = new DataStore();
    public static LinkedHashMap<String, PlayerData> list = new LinkedHashMap<>();

    public void addExp(String name, int amount) {
        list.get(name).addExp(amount);
    }

    public void addLevel(String name, int amount) {
        list.get(name).addLevel(amount);
    }

    private DataStore() {

    }
    public static DataStore getInstance() {
        return instance;
    }
}
