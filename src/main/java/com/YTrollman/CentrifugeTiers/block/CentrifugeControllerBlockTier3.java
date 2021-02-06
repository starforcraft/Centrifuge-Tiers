package com.YTrollman.CentrifugeTiers.block;

import com.YTrollman.CentrifugeTiers.registry.ModTileEntityTypes;
import com.YTrollman.CentrifugeTiers.tileentity.CentrifugeControllerTileEntityTier3;
import com.resourcefulbees.resourcefulbees.block.multiblocks.centrifuge.CentrifugeControllerBlock;
import com.resourcefulbees.resourcefulbees.utils.TooltipBuilder;

import net.minecraft.block.BlockState;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CentrifugeControllerBlockTier3 extends CentrifugeControllerBlock {
    public CentrifugeControllerBlockTier3(Properties properties) { super(properties); }

    @Override
    public boolean hasTileEntity(BlockState state)
    {
    	return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) 
    { 
    	return new CentrifugeControllerTileEntityTier3(ModTileEntityTypes.CENTRIFUGE_CONTROLLER_ENTITY_TIER_3.get()); 
    }
    
    @OnlyIn(Dist.CLIENT)
    @Override
    public void addInformation(@Nonnull ItemStack stack, @Nullable IBlockReader worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
        if (Screen.hasShiftDown()){
            tooltip.addAll(new TooltipBuilder()
                    .addTip(I18n.format("block.ctiers.centrifuge_tier_3.tooltip.faster"), TextFormatting.YELLOW)
                    .addTip(I18n.format("block.ctiers.centrifuge_tier_3.tooltip.multi"), TextFormatting.YELLOW)
                    .addTip(I18n.format("block.ctiers.centrifuge_tier_3.tooltip.ItemMaxStackSize"), TextFormatting.YELLOW)
                    .addTip(I18n.format("block.ctiers.centrifuge_tier_3.tooltip.MaxTankCapacity"), TextFormatting.YELLOW)
                    .addTip(I18n.format("block.ctiers.centrifuge_tier_3.tooltip.rfperblock"), TextFormatting.YELLOW)
                    .build());
        }
        else {
            tooltip.add(new StringTextComponent(TextFormatting.YELLOW + I18n.format("ctiers.left_shift_info")));
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}



