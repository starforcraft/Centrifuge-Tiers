package com.YTrollman.CentrifugeTiers.tileentity;

import static net.minecraft.inventory.container.Container.consideredTheSameItem;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.item.Item;
import org.apache.commons.lang3.tuple.Pair;

import com.YTrollman.CentrifugeTiers.block.CentrifugeCasingBlockTier5;
import com.YTrollman.CentrifugeTiers.config.CentrifugeConfig;
import com.YTrollman.CentrifugeTiers.container.CentrifugeMultiblockContainerTier5;
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

public class CentrifugeControllerTileEntityTier5 extends CentrifugeControllerTileEntity {
	public int ItemMaxStackSize = CentrifugeConfig.CENTRIFUGE_TIER_5_ITEM_MAX_STACK_SIZE.get();
    private final IntArray times = new IntArray(8) {
        @Override
        public int get(int index) {
            if(getCount() - index >= 1) return CentrifugeControllerTileEntityTier5.this.time[index];
            return 0;
        }

        @Override
        public void set(int index, int value) {
            if(getCount() - index >= 1) CentrifugeControllerTileEntityTier5.this.time[index] = value;
        }

        @Override
        public int getCount() {
            return 8;
        }
    };
    private final List<String> blacklist = (List<String>) CentrifugeConfig.CENTRIFUGE_MULTIPLIER_BLACKLIST.get();
    private final int multiplier = CentrifugeConfig.CENTRIFUGE_TIER_5_MUTLIPLIER.get();

    public CentrifugeControllerTileEntityTier5(TileEntityType<?> tileEntityType) { super(tileEntityType); }

    public void checkHoneycombSlots(){
        for (int i = 0; i < honeycombSlots.length; i++) {
            recipes.set(i, getRecipe(i));
            if (canStartCentrifugeProcess(i)) isProcessing[i] = true;
            if (isProcessing[i] && !processCompleted[i]) processRecipe(i);
            if (processCompleted[i]) completeProcess(i);
        }
    }
    
    @Override
    public void tick() {
        if (level != null && !level.isClientSide()) {
            if (isValidStructure() && (!requiresRedstone || isPoweredByRedstone)) checkHoneycombSlots();
            validateTime++;
            if (validateTime >= 5) validateStructure(this.level);
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
        } else if(inventoryHasSpace(recipes.get(i)) && tanksHasSpace(recipes.get(i))){
            ItemStack centrifugeInput = itemStackHandler.getStackInSlot(honeycombSlots[i]);
            ItemStack glassBottle = itemStackHandler.getStackInSlot(BOTTLE_SLOT);
            List<ItemStack> depositStacks = new ArrayList<>();
            if (level != null) {
                CentrifugeRecipe recipe = recipes.get(i);
                for (int j = 0; j < recipe.itemOutputs.size(); j++) {
                    float chance = recipe.itemOutputs.get(j).getRight();
                    if (chance >= level.random.nextFloat()) {
                        ItemStack output = recipe.itemOutputs.get(j).getLeft().copy();
                        output.setCount(output.getCount() * (blacklist.contains(centrifugeInput.getItem().getRegistryName().toString()) ? 1 : multiplier));
                        depositStacks.add(output);
                        if (j == 2 && !recipe.noBottleInput) {
                            glassBottle.shrink(recipes.get(i).itemOutputs.get(2).getLeft().getCount() * multiplier);
                        }
                    }
                }

                for (Pair<FluidStack, Float> fluidStackFloatPair : recipe.fluidOutput) {
                    float chance = fluidStackFloatPair.getRight();
                    if (chance >= this.level.random.nextFloat()) {
                        FluidStack fluid = fluidStackFloatPair.getLeft().copy();
                        int tank = this.getValidTank(fluid);
                        if(fluid.getAmount() != 0) fluid.setAmount(fluid.getAmount() * (blacklist.contains(centrifugeInput.getItem().getRegistryName().toString()) ? 1 : multiplier));
                        if (tank != -1) {
                            this.fluidTanks.fill(tank, fluid, IFluidHandler.FluidAction.EXECUTE);
                        }
                    }
                }
                if (!depositStacks.isEmpty()) {
                    depositItemStacks(depositStacks);
                }
            }
            consumeInput(i);
            resetProcess(i);
        }
    }
    
    private boolean tanksHasSpace(CentrifugeRecipe centrifugeRecipe) {
        if (centrifugeRecipe == null) return false;
        for (Pair<FluidStack, Float> f : centrifugeRecipe.fluidOutput) {
            if (f.getLeft().isEmpty()) continue;
            return getValidTank(f.getKey()) >= 0;
        }
        return true;
    }

    private int getValidTank(FluidStack fluid) {
        for (int i = 0; i < fluidTanks.getTanks(); i++) {
            if (fluidTanks.getFluidInTank(i).getFluid() == fluid.getFluid() || fluidTanks.getFluidInTank(i).isEmpty()) {
                return (fluidTanks.getFluidInTank(i).getAmount() + fluid.getAmount() <= fluidTanks.getTankCapacity(i)) ? i : -1;
            }
        }
        return -1;
    }
    
    protected void processRecipe(int i) {
        if (canProcess(i)) {
            energyStorage.consumeEnergy(Config.RF_TICK_CENTRIFUGE.get() * CentrifugeConfig.CENTRIFUGE_TIER_5_RF_PER_BLOCK.get());
            ++time[i];
            processCompleted[i] = time[i] >= getRecipeTime(i);
            this.dirty = true;
        } else resetProcess(i);
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
            if (itemStackHandler.getStackInSlot(i).isEmpty()) emptySlots++;
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
    public int getNumberOfInputs() { return 8; }

    @Override
    public int getMaxTankCapacity() {
        return CentrifugeConfig.CENTRIFUGE_TIER_5_MAX_TANK_CAPACITY.get();
    }

    @Override
    public int getRecipeTime(int i) {
    return getRecipe(i) != null ? Math.max(5, (int)(getRecipe(i).multiblockTime * CentrifugeConfig.CENTRIFUGE_TIER_5_RECIPE_TIME.get())) : Config.GLOBAL_CENTRIFUGE_RECIPE_TIME.get();
    }

    @Override
    protected CustomEnergyStorage createEnergy() {
        return new CustomEnergyStorage(Config.MAX_CENTRIFUGE_RF.get() * CentrifugeConfig.CENTRIFUGE_TIER_5_RF_CAPACITY.get(), 8000, 0) {
            @Override
            protected void onEnergyChanged() {
                setChanged();
            }
        };
    }

    @Override
    protected Predicate<BlockPos> validBlocks() {
        return blockPos -> {
            assert level != null : "Validating Centrifuge - How is world null??";
            Block block = level.getBlockState(blockPos).getBlock();
            TileEntity tileEntity = level.getBlockEntity(blockPos);
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
        assert level != null;
        return new CentrifugeMultiblockContainerTier5(ModContainers.CENTRIFUGE_MULTIBLOCK_CONTAINER_TIER_5.get(), id, level, worldPosition, playerInventory, times);
    }
}
