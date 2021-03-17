package com.YTrollman.CentrifugeTiers.tileentity;

import static com.resourcefulbees.resourcefulbees.tileentity.multiblocks.MultiBlockHelper.buildStructureBounds;
import static com.resourcefulbees.resourcefulbees.tileentity.multiblocks.MultiBlockHelper.buildStructureList;
import static net.minecraft.inventory.container.Container.consideredTheSameItem;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.YTrollman.CentrifugeTiers.block.CentrifugeCasingBlockTierCreative;
import com.YTrollman.CentrifugeTiers.config.CentrifugeConfig;
import com.YTrollman.CentrifugeTiers.container.CentrifugeMultiblockContainerTierCreative;
import com.YTrollman.CentrifugeTiers.registry.ModContainers;
import com.resourcefulbees.resourcefulbees.block.multiblocks.centrifuge.CentrifugeControllerBlock;
import com.resourcefulbees.resourcefulbees.capabilities.CustomEnergyStorage;
import com.resourcefulbees.resourcefulbees.capabilities.MultiFluidTank;
import com.resourcefulbees.resourcefulbees.lib.ModConstants;
import com.resourcefulbees.resourcefulbees.recipe.CentrifugeRecipe;
import com.resourcefulbees.resourcefulbees.registry.ModFluids;
import com.resourcefulbees.resourcefulbees.tileentity.multiblocks.MultiBlockHelper;
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
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

public class CentrifugeControllerTileEntityTierCreative extends CentrifugeControllerTileEntity {
	public int ItemMaxStackSize = CentrifugeConfig.CENTRIFUGE_TIER_CREATIVE_ITEM_MAX_STACK_SIZE.get();
    private final IntArray times = new IntArray(9) {
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
                case 6:
                    return CentrifugeControllerTileEntityTierCreative.this.time[6];
                case 7:
                    return CentrifugeControllerTileEntityTierCreative.this.time[7];
                case 8:
                    return CentrifugeControllerTileEntityTierCreative.this.time[8];
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
                    break;
                case 6:
                    CentrifugeControllerTileEntityTierCreative.this.time[6] = value;
                    break;
                case 7:
                    CentrifugeControllerTileEntityTierCreative.this.time[7] = value;
                    break;
                case 8:
                    CentrifugeControllerTileEntityTierCreative.this.time[8] = value;
            }

        }

        public int getCount() { return 9; }
    };

    public CentrifugeControllerTileEntityTierCreative(TileEntityType<?> tileEntityType) { super(tileEntityType); }

    @Override
    public void tick() {
        if (level != null && !level.isClientSide()) {
            if (isValidStructure() && (!requiresRedstone || isPoweredByRedstone)) {
                checkHoneycombSlots();
            }
            validateTime++;
            if (validateTime >= 0) {
                validateStructure(this.level);
            }
            if (dirty) {
                this.dirty = false;
                this.setChanged();
            }
        }
    }
    
    protected void processCompleted(int i) {
        if (recipes.get(i) != null) {
            if (inventoryHasSpace(recipes.get(i))) {
                consumeInput(i);
                ItemStack glass_bottle = itemStackHandler.getStackInSlot(BOTTLE_SLOT);
                List<ItemStack> depositStacks = new ArrayList<>();
                if (level != null) {
                    for (int j = 0; j < 3; j++) {
                        float nextFloat = level.random.nextFloat();
                        float chance;
                        switch (j) {
                            case 0:
                                if (recipes.get(i).hasFluidOutput) {
                                    chance = recipes.get(i).fluidOutput.get(0).getRight();
                                    if (chance >= nextFloat) {
                                    	for(int x = 0; x < CentrifugeConfig.CENTRIFUGE_TIER_CREATIVE_MUTLIPLIER.get(); x++) {
                                            fluidTanks.fill(i + 1, recipes.get(i).fluidOutput.get(0).getLeft().copy(), MultiFluidTank.FluidAction.EXECUTE);	
                                    	}
                                    }
                                    depositStacks.add(ItemStack.EMPTY);
                                } else {
                                    chance = recipes.get(i).itemOutputs.get(j).getRight();
                                    if (chance >= nextFloat) {
                                    	for(int x = 0; x < CentrifugeConfig.CENTRIFUGE_TIER_CREATIVE_MUTLIPLIER.get(); x++) {
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
                                	for(int x = 0; x < CentrifugeConfig.CENTRIFUGE_TIER_CREATIVE_MUTLIPLIER.get(); x++) {
                                        depositStacks.add(recipes.get(i).itemOutputs.get(j).getLeft().copy());                                		
                                	}
                                } else {
                                    depositStacks.add(ItemStack.EMPTY);
                                }
                                break;
                            case 2:
                                if (glass_bottle.isEmpty() || glass_bottle.getCount() < recipes.get(i).itemOutputs.get(j).getLeft().getCount()) {
                                	for(int x = 0; x < CentrifugeConfig.CENTRIFUGE_TIER_CREATIVE_MUTLIPLIER.get(); x++) {
                                        fluidTanks.fill(0, new FluidStack(ModFluids.HONEY_STILL.get(), ModConstants.HONEY_PER_BOTTLE), MultiFluidTank.FluidAction.EXECUTE);                                		
                                	}
                                    depositStacks.add(ItemStack.EMPTY);
                                } else {
                                    chance = recipes.get(i).itemOutputs.get(j).getRight();
                                    if (chance >= nextFloat) {
                                    	for(int x = 0; x < CentrifugeConfig.CENTRIFUGE_TIER_CREATIVE_MUTLIPLIER.get(); x++) {
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
    public int getNumberOfInputs() { return 9; }

    @Override
    public int getMaxTankCapacity() { return CentrifugeConfig.CENTRIFUGE_TIER_CREATIVE_MAX_TANK_CAPACITY.get(); }

    @Override
    public int getRecipeTime(int i) { return CentrifugeConfig.CENTRIFUGE_TIER_CREATIVE_RECIPE_TIME.get(); }

    @Override
    protected CustomEnergyStorage createEnergy() {
        return new CustomEnergyStorage(1, 0, 0) {
            @Override
            protected void onEnergyChanged() { setChanged(); }
        };
    }

    @Override
    protected MutableBoundingBox getBounds() {
    	if (CentrifugeConfig.CENTRIFUGE_TIER_CREATIVE_SIZE.get() == true)
    	{
            return buildStructureBounds(this.getBlockPos(), 3, 3, 3, -1, -1, -2, this.getBlockState().getValue(CentrifugeControllerBlock.FACING));
    	}
    	else if (CentrifugeConfig.CENTRIFUGE_TIER_CREATIVE_SIZE.get() == false)
    	{
            return buildStructureBounds(this.getBlockPos(), 3, 4, 3, -1, -1, -2, this.getBlockState().getValue(CentrifugeControllerBlock.FACING));
    	}
		return buildStructureBounds(this.getBlockPos(), 3, 3, 3, -1, -1, -2, this.getBlockState().getValue(CentrifugeControllerBlock.FACING));
    }
    
    protected void validateStructure(World world) {
        validateTime = 0;
        buildStructureList(getBounds(), structureBlocks, blockPos -> true, this.getBlockPos());
    	if (CentrifugeConfig.CENTRIFUGE_TIER_CREATIVE_SIZE.get() == true)
    	{
            validStructure = MultiBlockHelper.validateStructure(structureBlocks, validBlocks(), 26);
    	}
    	else if (CentrifugeConfig.CENTRIFUGE_TIER_CREATIVE_SIZE.get() == false)
    	{
            validStructure = MultiBlockHelper.validateStructure(structureBlocks, validBlocks(), 35);
    	}
        world.setBlockAndUpdate(worldPosition, getBlockState().setValue(CentrifugeControllerBlock.PROPERTY_VALID, validStructure));

        if (validStructure) {
            linkCasings(world);
        }
    }
    
    @Override
    protected Predicate<BlockPos> validBlocks() {
        return blockPos -> {
            assert level != null : "Validating Centrifuge - How is world null??";
            Block block = level.getBlockState(blockPos).getBlock();
            TileEntity tileEntity = level.getBlockEntity(blockPos);
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
        assert level != null;
        return new CentrifugeMultiblockContainerTierCreative(ModContainers.CENTRIFUGE_MULTIBLOCK_CONTAINER_TIER_CREATIVE.get(), id, level, worldPosition, playerInventory, times);
    }
}