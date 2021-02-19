package com.YTrollman.CentrifugeTiers.block;

import com.YTrollman.CentrifugeTiers.config.CentrifugeConfig;
import com.YTrollman.CentrifugeTiers.registry.ModTileEntityTypes;
import com.YTrollman.CentrifugeTiers.tileentity.CentrifugeControllerTileEntityTier5;
import com.resourcefulbees.resourcefulbees.block.multiblocks.centrifuge.CentrifugeControllerBlock;
import com.resourcefulbees.resourcefulbees.tileentity.multiblocks.centrifuge.CentrifugeControllerTileEntity;
import com.resourcefulbees.resourcefulbees.utils.TooltipBuilder;

import net.minecraft.block.BlockState;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CentrifugeControllerBlockTier5 extends CentrifugeControllerBlock {
	private int number = CentrifugeConfig.CENTRIFUGE_TIER_5_MUTLIPLIER.get();
	private int number2 = CentrifugeConfig.CENTRIFUGE_TIER_5_ITEM_MAX_STACK_SIZE.get();
	private int number3 = CentrifugeConfig.CENTRIFUGE_TIER_5_MAX_TANK_CAPACITY.get();
	private int number4 = CentrifugeConfig.CENTRIFUGE_TIER_5_RF_PER_BLOCK.get();
	
    public CentrifugeControllerBlockTier5(Properties properties) { super(properties); }

    @Override
    public boolean hasTileEntity(BlockState state)
    {
    	return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) 
    { 
    	return new CentrifugeControllerTileEntityTier5(ModTileEntityTypes.CENTRIFUGE_CONTROLLER_ENTITY_TIER_5.get()); 
    }
    
    @OnlyIn(Dist.CLIENT)
    @Override
    public void addInformation(@Nonnull ItemStack stack, @Nullable IBlockReader worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
        if (Screen.hasShiftDown()){
            tooltip.addAll(new TooltipBuilder()
                    .addTip(I18n.format("block.ctiers.centrifuge_tier_5.tooltip.faster"), TextFormatting.YELLOW)
                    .addTip(I18n.format("block.ctiers.centrifuge_tier_5.tooltip.multi") + number, TextFormatting.YELLOW)
                    .addTip(I18n.format("block.ctiers.centrifuge_tier_5.tooltip.ItemMaxStackSize") + number2, TextFormatting.YELLOW)
                    .addTip(I18n.format("block.ctiers.centrifuge_tier_5.tooltip.MaxTankCapacity") + number3, TextFormatting.YELLOW)
                    .addTip(I18n.format("block.ctiers.centrifuge_tier_5.tooltip.rfperblock") + number4 + "x RF", TextFormatting.YELLOW)
                    .build());
        }
        else {
            tooltip.add(new StringTextComponent(TextFormatting.YELLOW + I18n.format("ctiers.left_shift_info")));
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
    
    @Nonnull
    @Override
    public ActionResultType onUse(@Nonnull BlockState state, World world, @Nonnull BlockPos pos, @Nonnull PlayerEntity player, @Nonnull Hand hand, @Nonnull BlockRayTraceResult rayTraceResult) {
        if (!world.isRemote) {
            ItemStack heldItem = player.getHeldItem(hand);
            boolean usingBucket = heldItem.getItem() instanceof BucketItem;
            CentrifugeControllerTileEntity controller = getControllerEntity(world, pos);

            if (controller != null && controller.isValidStructure()) {
                if (usingBucket) {
                    controller.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
                            .ifPresent(iFluidHandler -> FluidUtil.interactWithFluidHandler(player, hand, world, pos, null));
                } else if (!player.isSneaking()) {
                    NetworkHooks.openGui((ServerPlayerEntity) player, controller, pos);
                }
            }
            else
            {
                return ActionResultType.PASS;
            }
        }

        return ActionResultType.SUCCESS;
    }
}



