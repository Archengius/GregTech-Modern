package com.gregtechceu.gtceu.api.machine.feature;

import com.gregtechceu.gtceu.api.blockentity.IGregtechBlockEntity;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.trait.MachineTraitHolder;
import net.minecraft.core.Direction;

public interface IMachineFeature extends IGregtechBlockEntity {
    MachineDefinition getDefinition();
    MachineTraitHolder getTraitHolder();
    Direction getFrontFacing();
    boolean hasFrontFacing();
    Direction getUpwardsFacing();
}
