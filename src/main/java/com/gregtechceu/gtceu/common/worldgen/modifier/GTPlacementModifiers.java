package com.gregtechceu.gtceu.common.worldgen.modifier;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.worldgen.modifier.BiomePlacement;
import com.gregtechceu.gtceu.api.data.worldgen.modifier.DimensionFilter;
import com.gregtechceu.gtceu.api.data.worldgen.modifier.FrequencyModifier;
import com.mojang.serialization.Codec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class GTPlacementModifiers {

    private static final DeferredRegister<PlacementModifierType<?>> PLACEMENT_MODIFIER_TYPE = DeferredRegister.create(Registries.PLACEMENT_MODIFIER_TYPE, GTCEu.MOD_ID);

    public static final RegistryObject<PlacementModifierType<BiomePlacement>> BIOME_PLACEMENT =
            PLACEMENT_MODIFIER_TYPE.register("biome_placement", () -> () -> BiomePlacement.CODEC);
    public static final RegistryObject<PlacementModifierType<DimensionFilter>> DIMENSION_FILTER =
            PLACEMENT_MODIFIER_TYPE.register("dimension", () -> () -> DimensionFilter.CODEC);
    public static final RegistryObject<PlacementModifierType<FrequencyModifier>> FREQUENCY =
            PLACEMENT_MODIFIER_TYPE.register("frequency", () -> () -> FrequencyModifier.CODEC);
    public static final RegistryObject<PlacementModifierType<RubberTreeChancePlacement>> RUBBER_TREE_PLACEMENT =
            PLACEMENT_MODIFIER_TYPE.register("rubber_tree_chance", () -> () -> RubberTreeChancePlacement.CODEC);


    public static void init(IEventBus modBus) {
        PLACEMENT_MODIFIER_TYPE.register(modBus);
    }
}
