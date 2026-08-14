package com.gregtechceu.gtceu.integration.jade.provider;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.feature.IRecipeLogicMachine;
import com.gregtechceu.gtceu.api.machine.trait.recipe.RecipeLogic;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;

import snownee.jade.api.BlockAccessor;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public class MachineModeProvider extends MachineTraitProvider<RecipeLogic, CompoundTag> {

    public MachineModeProvider() {
        super(GTCEu.id("machine_mode"), RecipeLogic.class);
    }

    @Override
    protected CompoundTag write(RecipeLogic recipeLogic) {
        var compoundTag = new CompoundTag();
        GTRecipeType[] recipeTypes = recipeLogic.getMachine().getDefinition().getRecipeTypes();
        if (recipeTypes.length > 1) {
            ListTag recipeTypesTagList = new ListTag();
            GTRecipeType currentRecipeType = recipeLogic.getRecipeType();
            int currentRecipeTypeIndex = -1;
            for (int i = 0; i < recipeTypes.length; i++) {
                if (recipeTypes[i] == currentRecipeType) {
                    currentRecipeTypeIndex = i;
                }
                recipeTypesTagList.add(StringTag.valueOf(recipeTypes[i].registryName.toString()));
            }
            compoundTag.put("RecipeTypes", recipeTypesTagList);
            compoundTag.putInt("CurrentRecipeType", currentRecipeTypeIndex);
        }
        return compoundTag;
    }

    @Override
    protected void addTooltip(CompoundTag data, ITooltip tooltip, Player player, BlockAccessor block,
                              BlockEntity blockEntity, IPluginConfig config) {
        if (data.contains("RecipeTypes") && data.contains("CurrentRecipeType")) {
            int currentRecipeTypeIndex = data.getInt("CurrentRecipeType");
            ListTag recipeTypesTagList = data.getList("RecipeTypes", StringTag.TAG_STRING);
            if (block.showDetails()) {
                tooltip.add(Component.translatable("gtceu.top.machine_mode"));
                for (int i = 0; i < recipeTypesTagList.size(); i++) {
                    ResourceLocation recipeType = ResourceLocation.parse(recipeTypesTagList.getString(i));
                    MutableComponent text;
                    if (currentRecipeTypeIndex == i) {
                        text = Component.literal(" > ").withStyle(ChatFormatting.BLUE);
                    } else {
                        text = Component.literal("   ");
                    }
                    text.append(
                            Component.translatable("%s.%s".formatted(recipeType.getNamespace(), recipeType.getPath())));
                    tooltip.add(text);
                }
            } else {
                ResourceLocation recipeType = ResourceLocation.parse(
                        recipeTypesTagList.getString(currentRecipeTypeIndex));
                tooltip.add(Component.translatable("gtceu.top.machine_mode").append(
                        Component.translatable("%s.%s".formatted(recipeType.getNamespace(), recipeType.getPath()))));
            }

        }
    }
}
