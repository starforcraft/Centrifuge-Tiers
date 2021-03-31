package com.YTrollman.CentrifugeTiers.tileentity;

import static net.minecraft.inventory.container.Container.consideredTheSameItem;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.lang3.tuple.Pair;

import com.YTrollman.CentrifugeTiers.block.CentrifugeCasingBlockTier4;
import com.YTrollman.CentrifugeTiers.config.CentrifugeConfig;
import com.YTrollman.CentrifugeTiers.container.CentrifugeMultiblockContainerTier4;
import com.YTrollman.CentrifugeTiers.registry.ModContainers;
import com.resourcefulbees.resourcefulbees.capabilities.CustomEnergyStorage;
import com.resourcefulbees.resourcefulbees.config.Config;
import com.resourcefulbees.resourcefulbees.recipe.CentrifugeRecipe;
import com.resourcefulbees.resourcefulbees.tileentity.multiblocks.centrifuge.CentrifugeControllerTileEntity;

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
import net.minecraftforge.fluids.capability.IFluidHandler;

public class CentrifugeControllerTileEntityTier4 extends CentrifugeControllerTileEntity {
	public int ItemMaxStackSize = CentrifugeConfig.CENTRIFUGE_TIER_4_ITEM_MAX_STACK_SIZE.get();
    private final IntArray times = new IntArray(7) {
        public int get(int index) {
            switch(index) {
                case 0:
                    return CentrifugeControllerTileEntityTier4.this.time[0];
                case 1:
                    return CentrifugeControllerTileEntityTier4.this.time[1];
                case 2:
                    return CentrifugeControllerTileEntityTier4.this.time[2];
                case 3:
                    return CentrifugeControllerTileEntityTier4.this.time[3];
                case 4:
                    return CentrifugeControllerTileEntityTier4.this.time[4];
                case 5:
                    return CentrifugeControllerTileEntityTier4.this.time[5];
                case 6:
                    return CentrifugeControllerTileEntityTier4.this.time[6];
                default:
                    return 0;
            }
        }

        public void set(int index, int value) {
            switch(index) {
                case 0:
                    CentrifugeControllerTileEntityTier4.this.time[0] = value;
                    break;
                case 1:
                    CentrifugeControllerTileEntityTier4.this.time[1] = value;
                    break;
                case 2:
                    CentrifugeControllerTileEntityTier4.this.time[2] = value;
                    break;
                case 3:
                    CentrifugeControllerTileEntityTier4.this.time[3] = value;
                    break;
                case 4:
                    CentrifugeControllerTileEntityTier4.this.time[4] = value;
                    break;
                case 5:
                    CentrifugeControllerTileEntityTier4.this.time[5] = value;
                    break;
                case 6:
                    CentrifugeControllerTileEntityTier4.this.time[6] = value;
            }

        }

        public int getCount() { return 7; }
    };

    public CentrifugeControllerTileEntityTier4(TileEntityType<?> tileEntityType) { super(tileEntityType); }

    public void checkHoneycombSlots(){
        for (int i = 0; i < honeycombSlots.length; i++) {
            recipes.set(i, getRecipe(i));
            if (canStartCentrifugeProcess(i)) {
                isProcessing[i] = true;
            }
            if (isProcessing[i] && !processCompleted[i]) {
                processRecipe(i);
            }
            if (processCompleted[i]) {
            	completeProcess(i);	
            }
        }
    }
    
    @Override
    public void tick() {
        if (level != null && !level.isClientSide()) {
            if (isValidStructure() && (!requiresRedstone || isPoweredByRedstone)) {
                checkHoneycombSlots();
            }
            validateTime++;
            if (validateTime >= 10) {
                validateStructure(this.level);
            }
            if (dirty) {
                this.dirty = false;
                this.setChanged();
            }
        }
    }
    
    @Override
    protected void completeProcess(int i) {
        if (recipes.get(i) == null) {
            resetProcess(i);
            return;
        }
        if (!inventoryHasSpace(recipes.get(i))) {
            return;
        }
        if (!tanksHasSpace(recipes.get(i))) {
            return;
        }
        consumeInput(i);
        ItemStack glass_bottle = itemStackHandler.getStackInSlot(BOTTLE_SLOT);
        List<ItemStack> depositStacks = new ArrayList<>();
        if (level == null) {
            resetProcess(i);
            return;
        }
        CentrifugeRecipe recipe = recipes.get(i);

        for (int j = 0; j < recipe.itemOutputs.size(); j++) {
            float chance = recipe.itemOutputs.get(j).getRight();
            if (chance >= level.random.nextFloat()) {
            	for(int x = 0; x < CentrifugeConfig.CENTRIFUGE_TIER_4_MUTLIPLIER.get(); x++) {
                    depositStacks.add(recipe.itemOutputs.get(j).getLeft().copy());
            	}
                if (j == 2 && !recipe.noBottleInput) {
                	for(int x = 0; x < CentrifugeConfig.CENTRIFUGE_TIER_4_MUTLIPLIER.get(); x++) {
                        glass_bottle.shrink(recipes.get(i).itemOutputs.get(2).getLeft().getCount());	
                	}
                }
            }
        }
        for (Pair<FluidStack, Float> fluidOutput : recipe.fluidOutput) {
            float chance = fluidOutput.getRight();
            if (chance >= level.random.nextFloat()) {
                FluidStack fluid = fluidOutput.getLeft().copy();
                int tank = getValidTank(fluid);
            	for(int x = 0; x < CentrifugeConfig.CENTRIFUGE_TIER_4_MUTLIPLIER.get(); x++) {
            		if (tank != -1)fluidTanks.fill(tank, fluid, IFluidHandler.FluidAction.EXECUTE);	
            	}
            }
        }
        if (!depositStacks.isEmpty()) {
            depositItemStacks(depositStacks);
        }
        resetProcess(i);
    }
    
    private boolean tanksHasSpace(CentrifugeRecipe centrifugeRecipe) {
        if (centrifugeRecipe == null) return false;
        for (Pair<FluidStack, Float> f : centrifugeRecipe.fluidOutput) {
            if (f.getLeft().isEmpty()) continue;
            if (getValidTank(f.getKey()) < 0) {
                return false;
            }
        }
        return true;
    }
    
    private int getValidTank(FluidStack fluid) {
        for (int i = 0; i < fluidTanks.getTanks(); i++) {
            if (fluidTanks.getFluidInTank(i).getFluid() == fluid.getFluid() || fluidTanks.getFluidInTank(i).isEmpty()) {
                if (fluidTanks.getFluidInTank(i).getAmount() + fluid.getAmount() <= fluidTanks.getTankCapacity(i)) {
                    return i;
                } else {
                    return -1;
                }
            }
        }
        return -1;
    }
    
    protected void processRecipe(int i) {
        if (canProcess(i)) {
            energyStorage.consumeEnergy(Config.RF_TICK_CENTRIFUGE.get() * CentrifugeConfig.CENTRIFUGE_TIER_4_RF_PER_BLOCK.get());
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
            while (!itemStack.isEmpty() && slotIndex < itemStackHandler.getSlots()) {
                ItemStack slotStack = itemStackHandler.getStackInSlot(slotIndex);

                int itemMaxStackSize = ItemMaxStackSize;

                if (slotStack.isEmpty()) {
                    itemStackHandler.setStackInSlot(slotIndex, itemStack.split(itemMaxStackSize));
                } else if (consideredTheSameItem(itemStack, slotStack) && slotStack.getCount() != itemMaxStackSize) {
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
                    } else if (consideredTheSameItem(output, slotStack) && slotStack.getCount() != ItemMaxStackSize) {
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
    public int getNumberOfInputs() { return 7; }
    
    @Override
    public int getMaxTankCapacity() { return CentrifugeConfig.CENTRIFUGE_TIER_4_MAX_TANK_CAPACITY.get(); }

    @Override
    public int getRecipeTime(int i) { return getRecipe(i) != null ? Math.max(5, (int)(getRecipe(i).multiblockTime * CentrifugeConfig.CENTRIFUGE_TIER_4_RECIPE_TIME.get())) : Config.GLOBAL_CENTRIFUGE_RECIPE_TIME.get(); }

    @Override
    protected CustomEnergyStorage createEnergy() {
        return new CustomEnergyStorage(Config.MAX_CENTRIFUGE_RF.get() * CentrifugeConfig.CENTRIFUGE_TIER_4_RF_CAPACITY.get(), 4000, 0) {
            @Override
            protected void onEnergyChanged() { setChanged(); }
        };
    }

    @Override
    protected Predicate<BlockPos> validBlocks() {
        return blockPos -> {
            assert level != null : "Validating Centrifuge - How is world null??";
            Block block = level.getBlockState(blockPos).getBlock();
            TileEntity tileEntity = level.getBlockEntity(blockPos);
            if (block instanceof CentrifugeCasingBlockTier4 && tileEntity instanceof CentrifugeCasingTileEntityTier4) {
                CentrifugeCasingTileEntityTier4 casing = (CentrifugeCasingTileEntityTier4) tileEntity;
                return !casing.isLinked() || (casing.getController() != null && casing.getController().equals(this));
            }
            return false;
        };
    }

    @Nullable
    @Override
    public Container createMenu(int id, @Nonnull PlayerInventory playerInventory, @Nonnull PlayerEntity playerEntity) {
        assert level != null;
        return new CentrifugeMultiblockContainerTier4(ModContainers.CENTRIFUGE_MULTIBLOCK_CONTAINER_TIER_4.get(), id, level, worldPosition, playerInventory, times);
    }
}
