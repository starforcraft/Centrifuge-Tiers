package com.YTrollman.CentrifugeTiers.tileentity;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.IntArray;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.YTrollman.CentrifugeTiers.block.CentrifugeCasingBlockTierCreative;
import com.YTrollman.CentrifugeTiers.container.CentrifugeMultiblockContainerTierCreative;
import com.YTrollman.CentrifugeTiers.registry.ModContainers;
import com.resourcefulbees.resourcefulbees.capabilities.CustomEnergyStorage;
import com.resourcefulbees.resourcefulbees.capabilities.MultiFluidTank;
import com.resourcefulbees.resourcefulbees.lib.ModConstants;
import com.resourcefulbees.resourcefulbees.recipe.CentrifugeRecipe;
import com.resourcefulbees.resourcefulbees.registry.ModFluids;
import com.resourcefulbees.resourcefulbees.tileentity.multiblocks.centrifuge.CentrifugeControllerTileEntity;

import static net.minecraft.inventory.container.Container.areItemsAndTagsEqual;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class CentrifugeControllerTileEntityTierCreative extends CentrifugeControllerTileEntity {
	public int ItemMaxStackSize = 16384;
    private final IntArray times = new IntArray(6) {
        public int get(int index) {
            switch(index) {
                case 0:
                    return CentrifugeControllerTileEntityTierCreative.this.time[0];
                case 1:
                    return CentrifugeControllerTileEntityTierCreative.this.time[1];
                case 2:
                    return CentrifugeControllerTileEntityTierCreative.this.time[2];
                case 3:
                    return CentrifugeControllerTileEntityTierCreative.this.time[3];
                case 4:
                    return CentrifugeControllerTileEntityTierCreative.this.time[4];
                case 5:
                    return CentrifugeControllerTileEntityTierCreative.this.time[5];
                default:
                    return 0;
            }
        }

        public void set(int index, int value) {
            switch(index) {
                case 0:
                    CentrifugeControllerTileEntityTierCreative.this.time[0] = value;
                    break;
                case 1:
                    CentrifugeControllerTileEntityTierCreative.this.time[1] = value;
                    break;
                case 2:
                    CentrifugeControllerTileEntityTierCreative.this.time[2] = value;
                    break;
                case 3:
                    CentrifugeControllerTileEntityTierCreative.this.time[3] = value;
                    break;
                case 4:
                    CentrifugeControllerTileEntityTierCreative.this.time[4] = value;
                    break;
                case 5:
                    CentrifugeControllerTileEntityTierCreative.this.time[5] = value;
            }

        }

        public int size() { return 6; }
    };

    public CentrifugeControllerTileEntityTierCreative(TileEntityType<?> tileEntityType) { super(tileEntityType); }

    @Override
    public void tick() {
        if (world != null && !world.isRemote()) {
            if (isValidStructure() && (!requiresRedstone || isPoweredByRedstone)) {
                for (int i = 0; i < honeycombSlots.length; i++) {
                    recipes.set(i, getRecipe(i));
                    if (canStartCentrifugeProcess(i)) {
                        isProcessing[i] = true;
                    }
                    if (isProcessing[i] && !processCompleted[i]) {
                        processRecipe(i);
                    }
                    if (processCompleted[i]) {
                        processCompleted(i);
                    }
                }
            }
            validateTime++;
            if (validateTime >= 20) {
                validateStructure(this.world, null);
            }
            if (dirty) {
                this.dirty = false;
                this.markDirty();
            }
        }
    }
    
    protected void processCompleted(int i) {
        if (recipes.get(i) != null) {
            if (inventoryHasSpace(recipes.get(i))) {
                consumeInput(i);
                ItemStack glass_bottle = itemStackHandler.getStackInSlot(BOTTLE_SLOT);
                List<ItemStack> depositStacks = new ArrayList<>();
                if (world != null) {
                    for (int j = 0; j < 3; j++) {
                        float nextFloat = world.rand.nextFloat();
                        float chance;
                        switch (j) {
                            case 0:
                                if (recipes.get(i).hasFluidOutput) {
                                    chance = recipes.get(i).fluidOutput.get(0).getRight();
                                    if (chance >= nextFloat) {
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                        fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                    }
                                    depositStacks.add(ItemStack.EMPTY);
                                } else {
                                    chance = recipes.get(i).itemOutputs.get(j).getRight();
                                    if (chance >= nextFloat) {
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    } else {
                                        depositStacks.add(ItemStack.EMPTY);
                                    }
                                }
                                break;
                            case 1:
                                chance = recipes.get(i).itemOutputs.get(j).getRight();
                                if (chance >= nextFloat) {
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                } else {
                                    depositStacks.add(ItemStack.EMPTY);
                                }
                                break;
                            case 2:
                                if (glass_bottle.isEmpty() || glass_bottle.getCount() < recipes.get(i).itemOutputs.get(j).getLeft().getCount()) {
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);  
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);  
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);  
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);  
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);  
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);  
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);  
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);  
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);  
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                    fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);  
                                    depositStacks.add(ItemStack.EMPTY);
                                } else {
                                    chance = recipes.get(i).itemOutputs.get(j).getRight();
                                    if (chance >= nextFloat) {
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    } else {
                                        depositStacks.add(ItemStack.EMPTY);
                                    }
                                }
                        }
                    }
                    if (!depositStacks.isEmpty()) {
                        depositItemStacks(depositStacks);
                    }
                }
                resetProcess(i);
            }
        } else {
            resetProcess(i);
        }
    }
    
    protected void processRecipe(int i) {
        if (canProcess(i)) {
            ++time[i];
            processCompleted[i] = time[i] >= getRecipeTime(i);
            this.dirty = true;
        } else {
            resetProcess(i);
        }
    }
    
    protected boolean canProcess(int i) { return !itemStackHandler.getStackInSlot(honeycombSlots[i]).isEmpty() && canProcessFluid(i) && canProcessEnergy(); }
    
    protected boolean canProcessEnergy(){ return true; }
    
    protected void depositItemStacks(List<ItemStack> itemStacks) {
        itemStacks.forEach(itemStack -> {
            int slotIndex = outputSlots[0];
            while (!itemStack.isEmpty() && slotIndex < itemStackHandler.getSlots()){
                ItemStack slotStack = itemStackHandler.getStackInSlot(slotIndex);

                int itemMaxStackSize = ItemMaxStackSize;

                if(slotStack.isEmpty()) {
                    itemStackHandler.setStackInSlot(slotIndex, itemStack.split(itemMaxStackSize));
                } else if (areItemsAndTagsEqual(itemStack, slotStack) && slotStack.getCount() != itemMaxStackSize) {
                    int combinedCount = itemStack.getCount() + slotStack.getCount();
                    if (combinedCount <= itemMaxStackSize) {
                        itemStack.setCount(0);
                        slotStack.setCount(combinedCount);
                    } else {
                        itemStack.shrink(itemMaxStackSize - slotStack.getCount());
                        slotStack.setCount(itemMaxStackSize);
                    }
                    itemStackHandler.setStackInSlot(slotIndex, slotStack);
                }

                ++slotIndex;
            }
        });
    }
    
    protected boolean inventoryHasSpace(CentrifugeRecipe recipe) {
        int emptySlots = 0;

        for (int i = outputSlots[0]; i < itemStackHandler.getSlots(); ++i) {
            if (itemStackHandler.getStackInSlot(i).isEmpty()) {
                emptySlots++;
            }
        }

        boolean hasSpace = true;
        int i = 0;
        while (recipe != null && hasSpace && i < recipe.itemOutputs.size()) {
            ItemStack output = recipe.itemOutputs.get(i).getLeft();
            if (!output.isEmpty() && !(i == 2 && itemStackHandler.getStackInSlot(BOTTLE_SLOT).isEmpty())) {
                int count = output.getCount();
                int j = outputSlots[0];

                while (count > 0 && j < itemStackHandler.getSlots()) {
                    ItemStack slotStack = itemStackHandler.getStackInSlot(j);

                    if (slotStack.isEmpty() && emptySlots != 0) {
                        count -= Math.min(count, ItemMaxStackSize);
                        emptySlots--;
                    } else if (areItemsAndTagsEqual(output, slotStack) && slotStack.getCount() != ItemMaxStackSize) {
                        count -= Math.min(count, ItemMaxStackSize - slotStack.getCount());
                    }

                    j++;
                }

                hasSpace = count <= 0;
            }
            i++;
        }

        return hasSpace;
    }
    
    @Override
    public int getNumberOfInputs() { return 6; }

    @Override
    public int getMaxTankCapacity() { return 5000000; }

    @Override
    public int getRecipeTime(int i) { return 1; }

    @Override
    protected CustomEnergyStorage createEnergy() {
        return new CustomEnergyStorage(1, 0, 0) {
            @Override
            protected void onEnergyChanged() { markDirty(); }
        };
    }

    @Override
    protected Predicate<BlockPos> validBlocks() {
        return blockPos -> {
            assert world != null : "Validating Centrifuge - How is world null??";
            Block block = world.getBlockState(blockPos).getBlock();
            TileEntity tileEntity = world.getTileEntity(blockPos);
            if (block instanceof CentrifugeCasingBlockTierCreative && tileEntity instanceof CentrifugeCasingTileEntityTierCreative) {
                CentrifugeCasingTileEntityTierCreative casing = (CentrifugeCasingTileEntityTierCreative) tileEntity;
                return !casing.isLinked() || (casing.getController() != null && casing.getController().equals(this));
            }
            return false;
        };
    }

    @Nullable
    @Override
    public Container createMenu(int id, @Nonnull PlayerInventory playerInventory, @Nonnull PlayerEntity playerEntity) {
        assert world != null;
        return new CentrifugeMultiblockContainerTierCreative(ModContainers.CENTRIFUGE_MULTIBLOCK_CONTAINER_TIER_CREATIVE.get(), id, world, pos, playerInventory, times);
    }
}
