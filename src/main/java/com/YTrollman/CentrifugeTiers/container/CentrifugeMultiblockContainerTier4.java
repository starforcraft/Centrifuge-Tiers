package com.YTrollman.CentrifugeTiers.container;

import com.YTrollman.CentrifugeTiers.registry.ModContainers;
import com.resourcefulbees.resourcefulbees.container.CentrifugeContainer;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.IntArray;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CentrifugeMultiblockContainerTier4 extends CentrifugeContainer {

    public CentrifugeMultiblockContainerTier4(int id, World world, BlockPos pos, PlayerInventory inv) {
        this(ModContainers.CENTRIFUGE_MULTIBLOCK_CONTAINER_TIER_4.get(), id, world, pos, inv, new IntArray(6));
    }

    public CentrifugeMultiblockContainerTier4(ContainerType<?> containerType, int id, World world, BlockPos pos, PlayerInventory inv, IntArray times) {
        super(containerType, id, world, pos, inv, times);
    }

    @Override
    protected void initialize() {
        inputXPos = 53;
        outputXPos = 44;
    }
}

