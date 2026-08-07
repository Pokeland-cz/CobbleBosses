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
    private boolean focusSash = false;
    private boolean useSmogonSet = false;
    private int maxPartySize = 3;

    public BossStatsConfig() {
    }
}
