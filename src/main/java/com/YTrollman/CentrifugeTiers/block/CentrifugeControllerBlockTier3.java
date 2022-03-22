package com.YTrollman.CentrifugeTiers.block;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.YTrollman.CentrifugeTiers.config.CentrifugeConfig;
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

public class CentrifugeControllerBlockTier3 extends CentrifugeControllerBlock {
	private int number = CentrifugeConfig.CENTRIFUGE_TIER_3_MUTLIPLIER.get();
	private int number2 = CentrifugeConfig.CENTRIFUGE_TIER_3_ITEM_MAX_STACK_SIZE.get();
	private int number3 = CentrifugeConfig.CENTRIFUGE_TIER_3_MAX_TANK_CAPACITY.get();
	private int number4 = CentrifugeConfig.CENTRIFUGE_TIER_3_RF_PER_BLOCK.get();
	
    public CentrifugeControllerBlockTier3(Properties properties) {
        super(properties);
    }
    
    @Override
    public boolean hasTileEntity(BlockState state)
    {
    	return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
    	return new CentrifugeControllerTileEntityTier3(ModTileEntityTypes.CENTRIFUGE_CONTROLLER_ENTITY_TIER_3.get()); 
    }
    
    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(@Nonnull ItemStack stack, @Nullable IBlockReader worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
        if (Screen.hasShiftDown()){
            tooltip.addAll(new TooltipBuilder()
                    .addTip(I18n.get("block.ctiers.centrifuge_tier_3.tooltip.faster"), TextFormatting.YELLOW)
                    .addTip(I18n.get("block.ctiers.centrifuge_tier_3.tooltip.multi") + number, TextFormatting.YELLOW)
                    .addTip(I18n.get("block.ctiers.centrifuge_tier_3.tooltip.ItemMaxStackSize") + number2, TextFormatting.YELLOW)
                    .addTip(I18n.get("block.ctiers.centrifuge_tier_3.tooltip.MaxTankCapacity") + number3, TextFormatting.YELLOW)
                    .addTip(I18n.get("block.ctiers.centrifuge_tier_3.tooltip.rfperblock") + number4 + "x RF", TextFormatting.YELLOW)
                    .build());
        } else {
            tooltip.add(new StringTextComponent(TextFormatting.YELLOW + I18n.get("ctiers.left_shift_info")));
        }
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
    }
}



