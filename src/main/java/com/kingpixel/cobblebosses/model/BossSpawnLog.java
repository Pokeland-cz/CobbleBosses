package com.kingpixel.cobblebosses.model;

import lombok.Data;

@Data
public class BossSpawnLog {
    private String bossId;
    private String pokemonName;
    private int level;
    private String world;
    private int x;
    private int y;
    private int z;
    private long timestamp;

    public BossSpawnLog() {}

    public BossSpawnLog(String bossId, String pokemonName, int level, String world, int x, int y, int z, long timestamp) {
        this.bossId = bossId;
        this.pokemonName = pokemonName;
        this.level = level;
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.timestamp = timestamp;
    }
}
