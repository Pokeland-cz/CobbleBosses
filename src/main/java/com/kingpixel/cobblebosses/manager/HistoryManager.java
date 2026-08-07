package com.kingpixel.cobblebosses.manager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kingpixel.cobblebosses.CobbleBosses;
import com.kingpixel.cobblebosses.model.BossSpawnLog;
import com.kingpixel.cobbleutils.CobbleUtils;
import com.kingpixel.cobbleutils.util.Utils;
import lombok.Getter;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class HistoryManager {

    private static final int MAX_HISTORY_SIZE = 100;
    
    @Getter
    private List<BossSpawnLog> logs = new LinkedList<>();

    public void init() {
        CompletableFuture<Boolean> futureRead = Utils.readFileAsync(CobbleBosses.PATH, "history.json", el -> {
            Gson gson = Utils.newGson();
            Type listType = new TypeToken<LinkedList<BossSpawnLog>>() {}.getType();
            List<BossSpawnLog> loaded = gson.fromJson(el, listType);
            if (loaded != null) {
                this.logs = loaded;
            }
        });

        if (!futureRead.join()) {
            save();
        }
    }

    public void addLog(BossSpawnLog log) {
        logs.add(log);
        if (logs.size() > MAX_HISTORY_SIZE) {
            logs.removeFirst();
        }
        save();
    }

    private void save() {
        Gson gson = Utils.newGson();
        String data = gson.toJson(logs);
        Utils.writeFileAsync(CobbleBosses.PATH, "history.json", data);
    }
}
