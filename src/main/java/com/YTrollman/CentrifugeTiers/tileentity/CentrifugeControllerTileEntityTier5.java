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

import com.YTrollman.CentrifugeTiers.block.CentrifugeCasingBlockTier5;
import com.YTrollman.CentrifugeTiers.config.CentrifugeConfig;
import com.YTrollman.CentrifugeTiers.container.CentrifugeMultiblockContainerTier5;
import com.YTrollman.CentrifugeTiers.registry.ModContainers;
import com.resourcefulbees.resourcefulbees.capabilities.CustomEnergyStorage;
import com.resourcefulbees.resourcefulbees.capabilities.MultiFluidTank;
import com.resourcefulbees.resourcefulbees.config.Config;
import com.resourcefulbees.resourcefulbees.lib.ModConstants;
import com.resourcefulbees.resourcefulbees.recipe.CentrifugeRecipe;
import com.resourcefulbees.resourcefulbees.registry.ModFluids;
import com.resourcefulbees.resourcefulbees.tileentity.multiblocks.centrifuge.CentrifugeControllerTileEntity;

import static net.minecraft.inventory.container.Container.areItemsAndTagsEqual;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class CentrifugeControllerTileEntityTier5 extends CentrifugeControllerTileEntity {
	public int ItemMaxStackSize = 1024;
    private final IntArray times = new IntArray(8) {
        public int get(int index) {
            switch(index) {
                case 0:
                    return CentrifugeControllerTileEntityTier5.this.time[0];
                case 1:
                    return CentrifugeControllerTileEntityTier5.this.time[1];
                case 2:
                    return CentrifugeControllerTileEntityTier5.this.time[2];
                case 3:
                    return CentrifugeControllerTileEntityTier5.this.time[3];
                case 4:
                    return CentrifugeControllerTileEntityTier5.this.time[4];
                case 5:
                    return CentrifugeControllerTileEntityTier5.this.time[5];
                case 6:
                    return CentrifugeControllerTileEntityTier5.this.time[6];
                case 7:
                    return CentrifugeControllerTileEntityTier5.this.time[7];
                default:
                    return 0;
            }
        }

        public void set(int index, int value) {
            switch(index) {
                case 0:
                    CentrifugeControllerTileEntityTier5.this.time[0] = value;
                    break;
                case 1:
                    CentrifugeControllerTileEntityTier5.this.time[1] = value;
                    break;
                case 2:
                    CentrifugeControllerTileEntityTier5.this.time[2] = value;
                    break;
                case 3:
                    CentrifugeControllerTileEntityTier5.this.time[3] = value;
                    break;
                case 4:
                    CentrifugeControllerTileEntityTier5.this.time[4] = value;
                    break;
                case 5:
                    CentrifugeControllerTileEntityTier5.this.time[5] = value;
                    break;
                case 6:
                    CentrifugeControllerTileEntityTier5.this.time[6] = value;
                    break;
                case 7:
                    CentrifugeControllerTileEntityTier5.this.time[7] = value;
            }

        }

        public int size() { return 8; }
    };

    public CentrifugeControllerTileEntityTier5(TileEntityType<?> tileEntityType) { super(tileEntityType); }

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
                                    	for(int x = 0; x < CentrifugeConfig.CENTRIFUGE_TIER_5_MUTLIPLIER.get(); x++) {
                                            fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);
                                    	}
                                    }
                                    depositStacks.add(ItemStack.EMPTY);
                                } else {
                                    chance = recipes.get(i).itemOutputs.get(j).getRight();
                                    if (chance >= nextFloat) {
                                    	for(int x = 0; x < CentrifugeConfig.CENTRIFUGE_TIER_5_MUTLIPLIER.get(); x++) {
                                            depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    	}
                                    } else {
                                        depositStacks.add(ItemStack.EMPTY);
                                    }
                                }
                                break;
                            case 1:
                                chance = recipes.get(i).itemOutputs.get(j).getRight();
                                if (chance >= nextFloat) {
                                	for(int x = 0; x < CentrifugeConfig.CENTRIFUGE_TIER_5_MUTLIPLIER.get(); x++) {
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());	
                                	}
                                } else {
                                    depositStacks.add(ItemStack.EMPTY);
                                }
                                break;
                            case 2:
                                if (glass_bottle.isEmpty() || glass_bottle.getCount() < recipes.get(i).itemOutputs.get(j).getLeft().getCount()) {
                                	for(int x = 0; x < CentrifugeConfig.CENTRIFUGE_TIER_5_MUTLIPLIER.get(); x++) {
                                        fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);
                                	}
                                    depositStacks.add(ItemStack.EMPTY);
                                } else {
                                    chance = recipes.get(i).itemOutputs.get(j).getRight();
                                    if (chance >= nextFloat) {
                                    	for(int x = 0; x < CentrifugeConfig.CENTRIFUGE_TIER_5_MUTLIPLIER.get(); x++) {
                                            glass_bottle.shrink(recipes.get(i).itemOutputs.get(j).getLeft().getCount());
                                            depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());
                                    	}
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
            energyStorage.consumeEnergy(Config.RF_TICK_CENTRIFUGE.get() * CentrifugeConfig.CENTRIFUGE_TIER_5_RF_PER_BLOCK.get());
            ++time[i];
            processCompleted[i] = time[i] >= getRecipeTime(i);
            this.dirty = true;
        } else {
            resetProcess(i);
        }
    }
    
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
    public int getNumberOfInputs() { return 8; }

    @Override
    public int getMaxTankCapacity() { return 400000; }

    @Override
    public int getRecipeTime(int i) { return getRecipe(i) != null ? Math.max(5, (int)(getRecipe(i).multiblockTime * CentrifugeConfig.CENTRIFUGE_TIER_5_RECIPE_TIME.get())) : Config.GLOBAL_CENTRIFUGE_RECIPE_TIME.get(); }

    @Override
    protected CustomEnergyStorage createEnergy() {
        return new CustomEnergyStorage(Config.MAX_CENTRIFUGE_RF.get() * CentrifugeConfig.CENTRIFUGE_TIER_5_RF_CAPACITY.get(), 8000, 0) {
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
            if (block instanceof CentrifugeCasingBlockTier5 && tileEntity instanceof CentrifugeCasingTileEntityTier5) {
                CentrifugeCasingTileEntityTier5 casing = (CentrifugeCasingTileEntityTier5) tileEntity;
                return !casing.isLinked() || (casing.getController() != null && casing.getController().equals(this));
            }
            return false;
        };
    }

    @Nullable
    @Override
    public Container createMenu(int id, @Nonnull PlayerInventory playerInventory, @Nonnull PlayerEntity playerEntity) {
        assert world != null;
        return new CentrifugeMultiblockContainerTier5(ModContainers.CENTRIFUGE_MULTIBLOCK_CONTAINER_TIER_5.get(), id, world, pos, playerInventory, times);
    }
}
