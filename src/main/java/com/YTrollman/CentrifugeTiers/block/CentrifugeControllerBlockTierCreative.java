package com.YTrollman.CentrifugeTiers.block;

import com.YTrollman.CentrifugeTiers.registry.ModTileEntityTypes;
import com.YTrollman.CentrifugeTiers.tileentity.CentrifugeControllerTileEntityTierCreative;
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

public class CentrifugeControllerBlockTierCreative extends CentrifugeControllerBlock {
    public CentrifugeControllerBlockTierCreative(Properties properties) { super(properties); }

    @Override
    public boolean hasTileEntity(BlockState state)
    {
    	return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) 
    { 
    	return new CentrifugeControllerTileEntityTierCreative(ModTileEntityTypes.CENTRIFUGE_CONTROLLER_ENTITY_TIER_CREATIVE.get()); 
    }
    
    @OnlyIn(Dist.CLIENT)
    @Override
    public void addInformation(@Nonnull ItemStack stack, @Nullable IBlockReader worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
        if (Screen.hasShiftDown()){
            tooltip.addAll(new TooltipBuilder()
                    .addTip(I18n.format("block.ctiers.centrifuge_tier_creative.tooltip.faster"), TextFormatting.YELLOW)
                    .addTip(I18n.format("block.ctiers.centrifuge_tier_creative.tooltip.multi"), TextFormatting.YELLOW)
                    .addTip(I18n.format("block.ctiers.centrifuge_tier_creative.tooltip.ItemMaxStackSize"), TextFormatting.YELLOW)
                    .addTip(I18n.format("block.ctiers.centrifuge_tier_creative.tooltip.MaxTankCapacity"), TextFormatting.YELLOW)
                    .addTip(I18n.format("block.ctiers.centrifuge_tier_creative.tooltip.structure_size"), TextFormatting.YELLOW)
                    .build());
        }
        else {
            tooltip.add(new StringTextComponent(TextFormatting.YELLOW + I18n.format("ctiers.left_shift_info")));
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
} 



