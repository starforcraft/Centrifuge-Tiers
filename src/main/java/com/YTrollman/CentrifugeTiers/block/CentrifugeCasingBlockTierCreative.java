package com.YTrollman.CentrifugeTiers.block;

import com.YTrollman.CentrifugeTiers.registry.ModTileEntityTypes;
import com.YTrollman.CentrifugeTiers.tileentity.CentrifugeCasingTileEntityTierCreative;
import com.resourcefulbees.resourcefulbees.block.multiblocks.centrifuge.CentrifugeCasingBlock;
import com.resourcefulbees.resourcefulbees.tileentity.multiblocks.centrifuge.CentrifugeControllerTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class CentrifugeCasingBlockTierCreative extends CentrifugeCasingBlock {
    public CentrifugeCasingBlockTierCreative(Properties properties) { super(properties); }

    @Override
    public boolean hasTileEntity(BlockState state) { return true; }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new CentrifugeCasingTileEntityTierCreative(ModTileEntityTypes.CENTRIFUGE_CASING_ENTITY_TIER_CREATIVE.get());
    }

    @Override
    protected CentrifugeControllerTileEntity getControllerEntity(World world, BlockPos pos) {
        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity instanceof CentrifugeCasingTileEntityTierCreative) {
            return ((CentrifugeCasingTileEntityTierCreative) tileEntity).getController();
        }
        return null;
    }
}
