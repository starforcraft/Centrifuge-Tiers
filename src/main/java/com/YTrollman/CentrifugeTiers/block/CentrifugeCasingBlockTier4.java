package com.YTrollman.CentrifugeTiers.block;

import com.YTrollman.CentrifugeTiers.registry.ModTileEntityTypes;
import com.YTrollman.CentrifugeTiers.tileentity.CentrifugeCasingTileEntityTier4;
import com.resourcefulbees.resourcefulbees.block.multiblocks.centrifuge.CentrifugeCasingBlock;
import com.resourcefulbees.resourcefulbees.tileentity.multiblocks.centrifuge.CentrifugeControllerTileEntity;
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CentrifugeCasingBlockTier4 extends CentrifugeCasingBlock {
    public CentrifugeCasingBlockTier4(Properties properties) { super(properties); }

    @Override
    public boolean hasTileEntity(BlockState state) { return true; }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new CentrifugeCasingTileEntityTier4(ModTileEntityTypes.CENTRIFUGE_CASING_ENTITY_TIER_4.get());
    }

    @Override
    protected CentrifugeControllerTileEntity getControllerEntity(World world, BlockPos pos) {
        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity instanceof CentrifugeCasingTileEntityTier4) {
            return ((CentrifugeCasingTileEntityTier4) tileEntity).getController();
        }
        return null;
    }
    
    @Nonnull
    @Override
    public ActionResultType onUse(@Nonnull BlockState state, @Nonnull World world, @Nonnull BlockPos pos, @Nonnull PlayerEntity player, @Nonnull Hand hand, @Nonnull BlockRayTraceResult rayTraceResult) {
        if (!world.isRemote) {
            ItemStack heldItem = player.getHeldItem(hand);
            boolean usingBucket = heldItem.getItem() instanceof BucketItem;
            CentrifugeControllerTileEntity controller = getControllerEntity(world, pos);

                if (controller != null && controller.isValidStructure()) {
                    if (usingBucket) {
                        controller.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
                                .ifPresent(iFluidHandler -> FluidUtil.interactWithFluidHandler(player, hand, world, pos, null));
                    } else if(!player.isSneaking()) {
                        NetworkHooks.openGui((ServerPlayerEntity) player, controller, controller.getPos());
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
