package com.YTrollman.CentrifugeTiers.container;

import com.YTrollman.CentrifugeTiers.registry.RegistryHandler;
import com.YTrollman.CentrifugeTiers.tileentity.CentrifugeControllerTileEntityTier4;
import com.resourcefulbees.resourcefulbees.container.OutputSlot;
import com.resourcefulbees.resourcefulbees.container.SlotItemHandlerUnconditioned;
import com.resourcefulbees.resourcefulbees.utils.CustomEnergyStorage;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;
import net.minecraft.util.IntReferenceHolder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nonnull;

public class CentrifugeMultiblockContainerTier4 extends Container {

    public CentrifugeControllerTileEntityTier4 centrifugeTileEntityTier4;
    public PlayerEntity player;
    public final IIntArray times;

    public CentrifugeMultiblockContainerTier4(int id, World world, BlockPos pos, PlayerInventory inv) {
        this(id, world, pos, inv, new IntArray(5));
    }

    public CentrifugeMultiblockContainerTier4(int id, World world, BlockPos pos, PlayerInventory inv, IIntArray times) {
        super(RegistryHandler.CENTRIFUGE_MULTIBLOCK_CONTAINER_TIER_4.get(), id);

        this.player = inv.player;
        this.times = times;

        centrifugeTileEntityTier4 = (CentrifugeControllerTileEntityTier4) world.getTileEntity(pos);

        int b = 5;
        for (int i = 0; i < 2; i++) {
	        this.addSlot(new SlotItemHandlerUnconditioned(centrifugeTileEntityTier4.h, CentrifugeControllerTileEntityTier4.BOTTLE_SLOT[i], b, 12){
	            public boolean isItemValid(ItemStack stack){
	                return stack.getItem().equals(Items.GLASS_BOTTLE);
	            }
	        });
	        b += 18;
        }

        int x = 53;
        for (int i = 0; i < 5; i++) {
            this.addSlot(new SlotItemHandlerUnconditioned(centrifugeTileEntityTier4.h, CentrifugeControllerTileEntityTier4.HONEYCOMB_SLOT[i], x, 8){
                public boolean isItemValid(ItemStack stack){
                    return !stack.getItem().equals(Items.GLASS_BOTTLE);
                }
            });
            x += 18;
        }


        for (int i = 0; i < 8; i++) {
            this.addSlot(new OutputSlot(centrifugeTileEntityTier4.h, CentrifugeControllerTileEntityTier4.OUTPUT_SLOTS[i], 26 + i * 18, 44)); //xPos, yPos y= breite, x = tiefe
            this.addSlot(new OutputSlot(centrifugeTileEntityTier4.h, CentrifugeControllerTileEntityTier4.OUTPUT_SLOTS[i + 6], 26 + i * 18, 62));
            this.addSlot(new OutputSlot(centrifugeTileEntityTier4.h, CentrifugeControllerTileEntityTier4.OUTPUT_SLOTS[i + 12], 26 + i * 18, 80));
        }

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(inv, j + i * 9 + 9, 8 + j * 18, 104 + i * 18));
            }
        }

        for (int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(inv, k, 8 + k * 18, 162));
        }
        trackPower();
        this.trackIntArray(times);
    }

    private void trackPower() {
        trackInt(new IntReferenceHolder() {
            @Override
            public int get() {
                return getEnergy() & 0xffff;
            }

            @Override
            public void set(int value) {
            	centrifugeTileEntityTier4.getCapability(CapabilityEnergy.ENERGY).ifPresent(h -> {
                    int energyStored = h.getEnergyStored() & 0xffff0000;
                    ((CustomEnergyStorage)h).setEnergy(energyStored + (value & 0xffff));
                });
            }
        });
        trackInt(new IntReferenceHolder() {
            @Override
            public int get() {
                return (getEnergy() >> 16) & 0xffff;
            }

            @Override
            public void set(int value) {
            	centrifugeTileEntityTier4.getCapability(CapabilityEnergy.ENERGY).ifPresent(h -> {
                    int energyStored = h.getEnergyStored() & 0x0000ffff;
                    ((CustomEnergyStorage)h).setEnergy(energyStored | (value << 16));
                });
            }
        });
    }

    public int getEnergy() {
        return centrifugeTileEntityTier4.getCapability(CapabilityEnergy.ENERGY).map(IEnergyStorage::getEnergyStored).orElse(0);
    }

    public int getTime(int i) {
        return this.times.get(i);
    }
    
    @Override
    public boolean canInteractWith(@Nonnull PlayerEntity player) {
        return true;
    }

    @Nonnull
    @Override
    public ItemStack transferStackInSlot(@Nonnull PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (index < 31) {
                if (!this.mergeItemStack(itemstack1, 31, inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            }
            else if (itemstack1.getItem().equals(Items.GLASS_BOTTLE) && !this.mergeItemStack(itemstack1, 0, 2, false)) {
                return ItemStack.EMPTY;
            }
            else if (!this.mergeItemStack(itemstack1, 0, 5, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
        }
        return itemstack;
    }
}

