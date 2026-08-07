package com.kingpixel.cobblebosses.model;

import lombok.Data;

/**
 * Configuration for boss stats and battle mechanics.
 */
@Data
public class BossStatsConfig {
    private boolean perfectIvs = false;
    private boolean perfectEvs = false;
    private boolean immuneToStatus = true;

    public BossStatsConfig() {
    }
}
