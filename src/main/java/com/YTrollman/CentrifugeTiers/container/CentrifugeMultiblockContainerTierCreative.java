package com.YTrollman.CentrifugeTiers.container;

import com.YTrollman.CentrifugeTiers.registry.RegistryHandler;
import com.YTrollman.CentrifugeTiers.tileentity.CentrifugeControllerTileEntityTierCreative;
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

public class CentrifugeMultiblockContainerTierCreative extends Container {

    public CentrifugeControllerTileEntityTierCreative centrifugeTileEntityTierCreative;
    public PlayerEntity player;
    public final IIntArray times;

    public CentrifugeMultiblockContainerTierCreative(int id, World world, BlockPos pos, PlayerInventory inv) {
        this(id, world, pos, inv, new IntArray(7));
    }

    public CentrifugeMultiblockContainerTierCreative(int id, World world, BlockPos pos, PlayerInventory inv, IIntArray times) {
        super(RegistryHandler.CENTRIFUGE_MULTIBLOCK_CONTAINER_TIER_CREATIVE.get(), id);

        this.player = inv.player;
        this.times = times;

        centrifugeTileEntityTierCreative = (CentrifugeControllerTileEntityTierCreative) world.getTileEntity(pos);

        int b = 6;
        for (int i = 0; i < 2; i++) {
	        this.addSlot(new SlotItemHandlerUnconditioned(centrifugeTileEntityTierCreative.h, CentrifugeControllerTileEntityTierCreative.BOTTLE_SLOT[i], b, 4){
	            public boolean isItemValid(ItemStack stack){
	                return stack.getItem().equals(Items.GLASS_BOTTLE);
	            }
	        });
	        b += 18;
        }
        
        int b2 = 6;
        for (int i = 2; i < 4; i++) {
            this.addSlot(new SlotItemHandlerUnconditioned(centrifugeTileEntityTierCreative.h, CentrifugeControllerTileEntityTierCreative.BOTTLE_SLOT[i], b2, 22){
                public boolean isItemValid(ItemStack stack){
                    return stack.getItem().equals(Items.GLASS_BOTTLE);
                }    
            });
	        b2 += 18;
        }
        
        int x = 45;
        for (int i = 0; i < 7; i++) {
            this.addSlot(new SlotItemHandlerUnconditioned(centrifugeTileEntityTierCreative.h, CentrifugeControllerTileEntityTierCreative.HONEYCOMB_SLOT[i], x, 8){
                public boolean isItemValid(ItemStack stack){
                    return !stack.getItem().equals(Items.GLASS_BOTTLE);
                }
            });
            x += 18;
        }


        for (int i = 0; i < 8; i++) {
            this.addSlot(new OutputSlot(centrifugeTileEntityTierCreative.h, CentrifugeControllerTileEntityTierCreative.OUTPUT_SLOTS[i], 26 + i * 18, 44));
            this.addSlot(new OutputSlot(centrifugeTileEntityTierCreative.h, CentrifugeControllerTileEntityTierCreative.OUTPUT_SLOTS[i + 8], 26 + i * 18, 62));
            this.addSlot(new OutputSlot(centrifugeTileEntityTierCreative.h, CentrifugeControllerTileEntityTierCreative.OUTPUT_SLOTS[i + 16], 26 + i * 18, 80));
            this.addSlot(new OutputSlot(centrifugeTileEntityTierCreative.h, CentrifugeControllerTileEntityTierCreative.OUTPUT_SLOTS[i + 24], 26 + i * 18, 98));
        }

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(inv, j + i * 9 + 9, 8 + j * 18, 123 + i * 18));
            }
        }

        for (int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(inv, k, 8 + k * 18, 181));
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
            	centrifugeTileEntityTierCreative.getCapability(CapabilityEnergy.ENERGY).ifPresent(h -> {
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
            	centrifugeTileEntityTierCreative.getCapability(CapabilityEnergy.ENERGY).ifPresent(h -> {
                    int energyStored = h.getEnergyStored() & 0x0000ffff;
                    ((CustomEnergyStorage)h).setEnergy(energyStored | (value << 16));
                });
            }
        });
    }

    public int getEnergy() {
        return centrifugeTileEntityTierCreative.getCapability(CapabilityEnergy.ENERGY).map(IEnergyStorage::getEnergyStored).orElse(0);
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
            if (index < 43) {
                if (!this.mergeItemStack(itemstack1, 43, inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            }
            else if (itemstack1.getItem().equals(Items.GLASS_BOTTLE) && !this.mergeItemStack(itemstack1, 0, 4, false)) {
                return ItemStack.EMPTY;
            }
            else if (!this.mergeItemStack(itemstack1, 0, 7, false)) {
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

