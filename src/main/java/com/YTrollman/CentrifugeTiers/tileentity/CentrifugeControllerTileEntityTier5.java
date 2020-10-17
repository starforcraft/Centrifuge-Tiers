package com.YTrollman.CentrifugeTiers.tileentity;

import com.YTrollman.CentrifugeTiers.block.CentrifugeCasingBlockTier5;
import com.YTrollman.CentrifugeTiers.block.CentrifugeControllerBlockTier5;
import com.YTrollman.CentrifugeTiers.container.CentrifugeMultiblockContainerTier5;
import com.YTrollman.CentrifugeTiers.registry.RegistryHandler;
import com.google.gson.JsonElement;
import com.resourcefulbees.resourcefulbees.config.Config;
import com.resourcefulbees.resourcefulbees.container.AutomationSensitiveItemStackHandler;
import com.resourcefulbees.resourcefulbees.lib.BeeConstants;
import com.resourcefulbees.resourcefulbees.recipe.CentrifugeRecipe;
import com.resourcefulbees.resourcefulbees.utils.CustomEnergyStorage;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static net.minecraft.inventory.container.Container.areItemsAndTagsEqual;

public class CentrifugeControllerTileEntityTier5 extends TileEntity implements ITickableTileEntity, INamedContainerProvider {

    public static final int[] BOTTLE_SLOT = {1,2,3};
    public static final int[] HONEYCOMB_SLOT = {4,5,6,7,8,9,10};
    public static final int[] OUTPUT_SLOTS = {11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34};

    public AutomationSensitiveItemStackHandler h = new CentrifugeControllerTileEntityTier5.TileStackHandler(35);
    public final CustomEnergyStorage energyStorage = createEnergy();
    public final LazyOptional<IItemHandler> lazyOptional = LazyOptional.of(() -> h);
    public final LazyOptional<IEnergyStorage> energy = LazyOptional.of(() -> energyStorage);
    public int[] time = {0,0,0,0,0,0,0};
    public List<CentrifugeRecipe> recipe = new ArrayList<>(Arrays.asList(null,null,null,null,null,null,null));
    public ItemStack failedMatch = ItemStack.EMPTY;
    public int totalTime = 10;
    private int validateTime = 210;
    public boolean validStructure;
    private final List<BlockPos> STRUCTURE_BLOCKS = new ArrayList<>();
    protected final IIntArray times = new IIntArray() {
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
            }

        }

        public int size() {
            return 7;
        }
    };


    public CentrifugeControllerTileEntityTier5() {
        super(RegistryHandler.CENTRIFUGE_CONTROLLER_ENTITY_TIER_5.get());
    }


    @Override
    public void tick() {
        if (world != null && !world.isRemote()) {
            boolean dirty = false;
            for (int i = 0; i < 7; i++) {
                for (int b = 0; b < 3; b++) {
	                if (!h.getStackInSlot(HONEYCOMB_SLOT[i]).isEmpty() && !h.getStackInSlot(BOTTLE_SLOT[b]).isEmpty()) {
	                    CentrifugeRecipe irecipe = getRecipe(i);
	                    if (this.canProcess(irecipe, i, b)) {
	                        this.totalTime = 10;
	                        ++this.time[i];
	                        if (this.time[i] == this.totalTime) {
	                            energyStorage.consumeEnergy(Config.RF_TICK_CENTRIFUGE.get() + 1999);
	                            this.time[i] = 0;
	                            this.totalTime = 10;
	                            this.processItem(irecipe, i, b);
	                            dirty = true;
	                        }
	                    } else {
	                        this.time[i] = 0;
	                    }
	                } else {
	                    this.time[i] = 0;
	                }
	                if (dirty) {
	                    this.markDirty();
	                }
	            }
            }
            if (validateTime != 0) {
                validateTime--;
            }
            else {
                validStructure = validateStructure(this.world, null);
                validateTime = 210;
            }
        }
    }

    protected boolean canProcess(@Nullable CentrifugeRecipe recipe, int slot, int bottleslot) {
        if (recipe != null && validStructure) {
            if (!Config.MULTIBLOCK_RECIPES_ONLY.get() || recipe.multiblock) {
                ItemStack glass_bottle = h.getStackInSlot(BOTTLE_SLOT[bottleslot]);
                ItemStack combs = h.getStackInSlot(HONEYCOMB_SLOT[slot]);
                JsonElement count = recipe.ingredient.serialize().getAsJsonObject().get(BeeConstants.INGREDIENT_COUNT);
                int inputAmount = count != null ? count.getAsInt() : 1;

                return inventoryHasSpace() && glass_bottle.getItem() == Items.GLASS_BOTTLE && energyStorage.getEnergyStored() >= recipe.time * Config.RF_TICK_CENTRIFUGE.get() && combs.getCount() >= inputAmount && glass_bottle.getCount() >= recipe.outputs.get(2).getLeft().getCount();
            }else return false;
        }
        return false;
    }

    private void processItem(@Nullable CentrifugeRecipe recipe, int slot, int bottleslot) {
        if (recipe != null && this.canProcess(recipe, slot, bottleslot)) {
            JsonElement count = recipe.ingredient.serialize().getAsJsonObject().get(BeeConstants.INGREDIENT_COUNT);
            int inputAmount = count !=null ? count.getAsInt() : 1;
            ItemStack comb = h.getStackInSlot(HONEYCOMB_SLOT[slot]);
            ItemStack glass_bottle = h.getStackInSlot(BOTTLE_SLOT[bottleslot]);
            if (world != null)
                for(int i = 0; i < 3; i++){
                    Pair<ItemStack, Double> output = recipe.outputs.get(i);
                    if (output.getRight() >= world.rand.nextDouble()) {
                        if (inventoryHasSpace()) {
                            depositItemStacks(output.getLeft().copy());
                            depositItemStacks(output.getLeft().copy());
                            depositItemStacks(output.getLeft().copy());
                            depositItemStacks(output.getLeft().copy());
                            depositItemStacks(output.getLeft().copy());
                            if (i == 2) glass_bottle.shrink(recipe.outputs.get(2).getLeft().getCount());
                            if (i == 2) glass_bottle.shrink(recipe.outputs.get(2).getLeft().getCount());
                            if (i == 2) glass_bottle.shrink(recipe.outputs.get(2).getLeft().getCount());
                            if (i == 2) glass_bottle.shrink(recipe.outputs.get(2).getLeft().getCount());
                            if (i == 2) glass_bottle.shrink(recipe.outputs.get(2).getLeft().getCount());
                        }
                    }
                }
            comb.shrink(inputAmount);
        }
        time[slot] = 0;
    }

    protected CentrifugeRecipe getRecipe(int i) {
        ItemStack input = h.getStackInSlot(HONEYCOMB_SLOT[i]);
        if (input.isEmpty() || input == failedMatch) return null;
        if (world != null)
            if (this.recipe.get(i) !=null && this.recipe.get(i).matches(new RecipeWrapper(h), world)) return this.recipe.get(i);
            else {
                Inventory recipeInv = new Inventory(input);
                CentrifugeRecipe rec = world.getRecipeManager().getRecipe(CentrifugeRecipe.CENTRIFUGE_RECIPE_TYPE, recipeInv, this.world).orElse(null);
                if (rec == null) failedMatch = input;
                else failedMatch = ItemStack.EMPTY;
                this.recipe.set(i, rec);
                return this.recipe.get(i);
            }
        return null;
    }

    public void depositItemStacks(ItemStack itemstack){
        int slotIndex = 11;
        while (!itemstack.isEmpty()){
            ItemStack slotStack = h.getStackInSlot(slotIndex);

            int itemMaxStackSize = 256;

            if(slotStack.isEmpty()) {
                int count = itemstack.getCount();
                slotStack = itemstack.copy();

                if (count > itemMaxStackSize) {
                    slotStack.setCount(itemMaxStackSize);
                    itemstack.setCount(count-itemMaxStackSize);
                } else {
                    itemstack.setCount(0);
                }
                h.setStackInSlot(slotIndex, slotStack);
            } else if (areItemsAndTagsEqual(itemstack, slotStack)) {
                int j = itemstack.getCount() + slotStack.getCount();
                if (j <= itemMaxStackSize) {
                    itemstack.setCount(0);
                    slotStack.setCount(j);
                    h.setStackInSlot(slotIndex, slotStack);
                } else if (slotStack.getCount() < itemMaxStackSize) {
                    itemstack.shrink(itemMaxStackSize - slotStack.getCount());
                    slotStack.setCount(itemMaxStackSize);
                    h.setStackInSlot(slotIndex, slotStack);
                }
            }

            ++slotIndex;
        }
    }


    public boolean inventoryHasSpace(){
        for (int i = 11; i < 35; ++i){
            if (h.getStackInSlot(i).isEmpty()){
                return true;
            }
        }
        return false;
    }

    @Nonnull
    @Override
    public CompoundNBT write(@Nonnull CompoundNBT tag) {
        super.write(tag);
        return saveToNBT(tag);
    }

    public CompoundNBT saveToNBT(CompoundNBT tag) {
        CompoundNBT inv = this.h.serializeNBT();
        tag.put("inv", inv);
        tag.putIntArray("time", time);
        tag.putInt("totalTime", totalTime);
        tag.put("energy", energyStorage.serializeNBT());
        tag.putBoolean("valid", validStructure);
        return tag;
    }

    public void loadFromNBT(CompoundNBT tag) {
        CompoundNBT invTag = tag.getCompound("inv");
        h.deserializeNBT(invTag);
        time = tag.getIntArray("time");
        totalTime = tag.getInt("totalTime");
        energyStorage.deserializeNBT(tag.getCompound("energy"));
        validStructure = tag.getBoolean("valid");
    }

    @Override
    public void read(@Nonnull BlockState state, @Nonnull CompoundNBT tag) {
        this.loadFromNBT(tag);
        super.read(state, tag);
    }

    @Nonnull
    @Override
    public CompoundNBT getUpdateTag() {
        CompoundNBT nbtTagCompound = new CompoundNBT();
        write(nbtTagCompound);
        return nbtTagCompound;
    }

    @Override
    public void handleUpdateTag(@Nonnull BlockState state, CompoundNBT tag) {
        this.read(state, tag);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap.equals(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)) return lazyOptional.cast();
        if (cap.equals(CapabilityEnergy.ENERGY)) return energy.cast();
        return super.getCapability(cap, side);
    }

    public AutomationSensitiveItemStackHandler.IAcceptor getAcceptor() {
        return (slot, stack, automation) ->
            !automation ||
            (
                (slot == HONEYCOMB_SLOT[0] || slot == HONEYCOMB_SLOT[1] || slot == HONEYCOMB_SLOT[2]|| slot == HONEYCOMB_SLOT[3]|| slot == HONEYCOMB_SLOT[4]|| slot == HONEYCOMB_SLOT[5]|| slot == HONEYCOMB_SLOT[6]) &&
                !stack.getItem().equals(Items.GLASS_BOTTLE)
            )||(
                slot == BOTTLE_SLOT[0] || slot == BOTTLE_SLOT[1] || slot == BOTTLE_SLOT[2]|| slot == BOTTLE_SLOT[3] &&
                stack.getItem().equals(Items.GLASS_BOTTLE)
            );
    }

    public AutomationSensitiveItemStackHandler.IRemover getRemover() {
        return (slot, automation) -> !automation || slot > 7;
    }

    @Nullable
    @Override
    public Container createMenu(int id, @Nonnull PlayerInventory playerInventory, @Nonnull PlayerEntity playerEntity) {
        return new CentrifugeMultiblockContainerTier5(id, world, pos, playerInventory, times);
    }

    @Nonnull
    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("gui.ctiers.centrifuge_tier_5");
    }

    private CustomEnergyStorage createEnergy() {
        return new CustomEnergyStorage(Config.MAX_CENTRIFUGE_RF.get() * 5, 10000, 0) {
            @Override
            protected void onEnergyChanged() {
                markDirty();
            }
        };
    }

    public MutableBoundingBox buildStructureBounds() {
        MutableBoundingBox box;
        int posX = this.getPos().getX();
        int posY = this.getPos().getY();
        int posZ = this.getPos().getZ();

        switch (this.getBlockState().get(CentrifugeControllerBlockTier5.FACING)) {
            case NORTH:
                box = new MutableBoundingBox(posX + 1 , posY -1 , posZ, posX - 1, posY + 2, posZ + 2);
                break;
            case EAST:
                box = new MutableBoundingBox(posX, posY - 1, posZ + 1, posX - 2, posY + 2, posZ - 1);
                break;
            case SOUTH:
                box = new MutableBoundingBox(posX - 1, posY - 1, posZ, posX + 1, posY + 2, posZ - 2);
                break;
            default:
                box = new MutableBoundingBox(posX, posY - 1, posZ - 1, posX + 2, posY + 2, posZ + 1);
        }
        return box;
    }

    public void setCasingsToNotLinked(MutableBoundingBox box){
        BlockPos.getAllInBox(box).forEach((pos -> {
            if (world !=null) {
                Block block = world.getBlockState(pos).getBlock();
                if (block instanceof CentrifugeCasingBlockTier5) {
                    CentrifugeCasingTileEntityTier5 casingTE = (CentrifugeCasingTileEntityTier5) world.getTileEntity(pos);
                    if (casingTE != null) {
                        casingTE.setControllerPos(null);
                    }
                }
            }
        }));
    }

    public void buildStructureList(){
        MutableBoundingBox box = buildStructureBounds();
        STRUCTURE_BLOCKS.clear();
        BlockPos.getAllInBox(box).forEach((pos -> {
            if (world !=null) {
                Block block = world.getBlockState(pos).getBlock();
                if (block instanceof CentrifugeCasingBlockTier5) {
                	CentrifugeCasingTileEntityTier5 casingTE = (CentrifugeCasingTileEntityTier5) world.getTileEntity(pos);
                    if (casingTE != null) {
                        casingTE.setControllerPos(this.pos);
                        STRUCTURE_BLOCKS.add(pos);
                    }
                }
            }
        }));
    }

    public boolean validateStructure(World world, @Nullable ServerPlayerEntity player){
        MutableBoundingBox box = buildStructureBounds();
        buildStructureList();
        boolean validStructure = STRUCTURE_BLOCKS.size() == 35;
        if (world !=null) {
            if (!validStructure) {
                setCasingsToNotLinked(box);
                world.setBlockState(pos, getBlockState().with(CentrifugeControllerBlockTier5.PROPERTY_VALID, false));
            } else {
                world.setBlockState(pos, getBlockState().with(CentrifugeControllerBlockTier5.PROPERTY_VALID, true));
            }
        }

        if (!validStructure && player !=null){
            player.sendStatusMessage(new StringTextComponent("Centrifuge Multiblock Invalid"), false);
        }
        return validStructure;
    }

    //endregion

    protected class TileStackHandler extends AutomationSensitiveItemStackHandler {
        protected TileStackHandler(int slots) {
            super(slots);
        }

        @Override
        public AutomationSensitiveItemStackHandler.IAcceptor getAcceptor() {
            return CentrifugeControllerTileEntityTier5.this.getAcceptor();
        }

        @Override
        public AutomationSensitiveItemStackHandler.IRemover getRemover() {
            return CentrifugeControllerTileEntityTier5.this.getRemover();
        }

        @Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            markDirty();
        }
    }
}

