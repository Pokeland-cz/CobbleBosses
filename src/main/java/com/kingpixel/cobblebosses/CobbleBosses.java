package com.kingpixel.cobblebosses;

import com.cobblemon.mod.common.Cobblemon;
import com.kingpixel.cobblebosses.command.CommandTree;
import com.kingpixel.cobblebosses.config.BossesConfig;
import com.kingpixel.cobblebosses.config.Config;
import com.kingpixel.cobblebosses.config.Lang;
import com.kingpixel.cobblebosses.events.BattleEvents;
import com.kingpixel.cobblebosses.events.CaptureEvents;
import com.kingpixel.cobblebosses.events.SpawningEvents;
import com.kingpixel.cobblebosses.events.StartBattleEvent;
import dev.architectury.event.events.common.CommandRegistrationEvent;
import dev.architectury.event.events.common.LifecycleEvent;
import net.fabricmc.api.ModInitializer;
import net.minecraft.server.MinecraftServer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kingpixel.cobblebosses.model.SmogonSet;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class CobbleBosses implements ModInitializer {
  public static final String MOD_ID = "cobblebosses";
  public static final String MOD_NAME = "CobbleBosses";
  public static final String PATH = "/config/" + MOD_ID;
  public static final String PATH_LANG = PATH + "/lang/";
  public static final String PATH_BOSSES = PATH + "/bosses/";
  public static final String TAG_BOSS_ID = "boss";
  public static MinecraftServer server;
  public static Config config = new Config();
  public static Lang language = new Lang();
  public static BossesConfig bossesConfig = new BossesConfig();
  public static int oldLevelCap = 100;
  public static int maxLevelCap = 1000;
  public static Map<String, SmogonSet> competitiveSets = new HashMap<>();


  @Override public void onInitialize() {
    events();
  }

  public static void load() {
    files();
    tasks();
    loadCompetitiveSets();
  }

  private static void loadCompetitiveSets() {
    try {
      java.io.InputStream is = CobbleBosses.class.getResourceAsStream("/competitive_sets.json");
      if (is != null) {
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, SmogonSet>>() {}.getType();
        competitiveSets = gson.fromJson(new InputStreamReader(is, StandardCharsets.UTF_8), type);
        System.out.println("[CobbleBosses] Loaded " + competitiveSets.size() + " competitive sets.");
      } else {
        System.out.println("[CobbleBosses] competitive_sets.json not found in resources.");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static void tasks() {

  }


  private static void files() {
    config.init();
    language.init();
    bossesConfig.init();
  }


  private static void events() {
    files();


    CommandRegistrationEvent.EVENT.register((dispatcher, registry, selection) -> {
      CommandTree.register(dispatcher, registry);
    });

    LifecycleEvent.SERVER_STARTED.register(server -> {
      load();
      oldLevelCap = Cobblemon.INSTANCE.getConfig().getMaxPokemonLevel();
    });


    LifecycleEvent.SERVER_LEVEL_LOAD.register(level -> server = level.getServer());

    SpawningEvents.register();
    BattleEvents.register();
    CaptureEvents.register();
    StartBattleEvent.register();
  }
}
