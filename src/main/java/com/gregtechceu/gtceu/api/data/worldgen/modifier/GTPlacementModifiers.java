package com.gregtechceu.gtceu.api.data.worldgen.modifier;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.common.worldgen.modifier.RubberTreeChancePlacement;
import com.mojang.serialization.Codec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("unchecked")
public class GTPlacementModifiers {

    private static final DeferredRegister<PlacementModifierType<?>> PLACEMENT_MODIFIER_TYPE = DeferredRegister.create(Registries.PLACEMENT_MODIFIER_TYPE, GTCEu.MOD_ID);

    public static final RegistryObject<PlacementModifierType<?>> BIOME_PLACEMENT =
            PLACEMENT_MODIFIER_TYPE.register("biome_placement", () -> () -> (Codec<PlacementModifier>)(Object)BiomePlacement.CODEC);

    public static final RegistryObject<PlacementModifierType<?>> DIMENSION_FILTER =
            PLACEMENT_MODIFIER_TYPE.register("dimension", () -> () -> (Codec<PlacementModifier>)(Object)DimensionFilter.CODEC);

    public static final RegistryObject<PlacementModifierType<?>> FREQUENCY =
            PLACEMENT_MODIFIER_TYPE.register("frequency", () -> () -> (Codec<PlacementModifier>)(Object)FrequencyModifier.CODEC);

    public static final RegistryObject<PlacementModifierType<?>> RUBBER_TREE_PLACEMENT =
            PLACEMENT_MODIFIER_TYPE.register("rubber_tree_chance", () -> () -> (Codec<PlacementModifier>)(Object)RubberTreeChancePlacement.CODEC);


    public static void init(IEventBus modBus) {
        PLACEMENT_MODIFIER_TYPE.register(modBus);
    }
}
