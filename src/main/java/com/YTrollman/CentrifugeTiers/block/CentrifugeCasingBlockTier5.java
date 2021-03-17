package com.YTrollman.CentrifugeTiers.block;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.YTrollman.CentrifugeTiers.registry.ModTileEntityTypes;
import com.YTrollman.CentrifugeTiers.tileentity.CentrifugeCasingTileEntityTier5;
import com.resourcefulbees.resourcefulbees.block.multiblocks.centrifuge.CentrifugeCasingBlock;
import com.resourcefulbees.resourcefulbees.tileentity.multiblocks.centrifuge.CentrifugeControllerTileEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fml.network.NetworkHooks;

public class CentrifugeCasingBlockTier5 extends CentrifugeCasingBlock {
    public CentrifugeCasingBlockTier5(Properties properties) { super(properties); }

    @Override
    public boolean hasTileEntity(BlockState state) { return true; }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new CentrifugeCasingTileEntityTier5(ModTileEntityTypes.CENTRIFUGE_CASING_ENTITY_TIER_5.get());
    }

    @Override
    protected CentrifugeControllerTileEntity getControllerEntity(World world, BlockPos pos) {
        TileEntity tileEntity = world.getBlockEntity(pos);
        if (tileEntity instanceof CentrifugeCasingTileEntityTier5) {
            return ((CentrifugeCasingTileEntityTier5) tileEntity).getController();
        }
        return null;
    }
    
    //TODO this can break if other casings are powered need to find an alternative solution.
    // may have to loop through every casing and check if any are still powered.
    @Override
    public void neighborChanged(@Nonnull BlockState state, @Nonnull World world, @Nonnull BlockPos pos, @Nonnull Block changedBlock, @Nonnull BlockPos changedBlockPos, boolean bool) {
    	CentrifugeControllerTileEntity centrifugeControllerTileEntity = getControllerEntity(world, pos);
        if (centrifugeControllerTileEntity != null) {
            centrifugeControllerTileEntity.setIsPoweredByRedstone(world.hasNeighborSignal(pos));
        }
    }
    
    @Nonnull
    @Override
    public ActionResultType use(@Nonnull BlockState state, @Nonnull World world, @Nonnull BlockPos pos, @Nonnull PlayerEntity player, @Nonnull Hand hand, @Nonnull BlockRayTraceResult rayTraceResult) {
        if (!world.isClientSide) {
            ItemStack heldItem = player.getItemInHand(hand);
            boolean usingBucket = heldItem.getItem() instanceof BucketItem;
            CentrifugeControllerTileEntity controller = getControllerEntity(world, pos);

                if (controller != null && controller.isValidStructure()) {
                    if (usingBucket) {
                        controller.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
                                .ifPresent(iFluidHandler -> FluidUtil.interactWithFluidHandler(player, hand, world, pos, null));
                    } else if(!player.isShiftKeyDown()) {
                        NetworkHooks.openGui((ServerPlayerEntity) player, controller, controller.getBlockPos());
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
