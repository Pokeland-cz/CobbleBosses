package com.kingpixel.cobblebosses.mixins;

import com.cobblemon.mod.common.config.CobblemonConfig;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.kingpixel.cobblebosses.CobbleBosses;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = Pokemon.class, remap = false)
public class PokemonLevelCapMixin {

    @Redirect(
        method = { 
            "setLevel", 
            "setExperience$common", 
            "setExperienceAndUpdateLevel", 
            "addExperience", 
            "canLevelUpFurther" 
        },
        at = @At(value = "INVOKE", target = "Lcom/cobblemon/mod/common/config/CobblemonConfig;getMaxPokemonLevel()I")
    )
    private int redirectMaxPokemonLevel(CobblemonConfig instance) {
        Pokemon pokemon = (Pokemon) (Object) this;
        if (pokemon.getPersistentData().contains(CobbleBosses.TAG_BOSS_ID)) {
            return Math.max(instance.getMaxPokemonLevel(), CobbleBosses.maxLevelCap);
        }
        return instance.getMaxPokemonLevel();
    }
}
