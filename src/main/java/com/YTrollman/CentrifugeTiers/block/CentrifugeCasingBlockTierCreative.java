package com.YTrollman.CentrifugeTiers.block;

import com.YTrollman.CentrifugeTiers.tileentity.CentrifugeCasingTileEntityTierCreative;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class CentrifugeCasingBlockTierCreative extends Block {
    public CentrifugeCasingBlockTierCreative(Properties properties) {
        super(properties);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new CentrifugeCasingTileEntityTierCreative();
    }
}
