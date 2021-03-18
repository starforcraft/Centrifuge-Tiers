package com.YTrollman.CentrifugeTiers.block;

import javax.annotation.Nullable;

import com.YTrollman.CentrifugeTiers.registry.ModTileEntityTypes;
import com.YTrollman.CentrifugeTiers.tileentity.CentrifugeCasingTileEntityTier5;
import com.resourcefulbees.resourcefulbees.block.multiblocks.centrifuge.CentrifugeCasingBlock;
import com.resourcefulbees.resourcefulbees.tileentity.multiblocks.centrifuge.CentrifugeControllerTileEntity;

import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

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
}
