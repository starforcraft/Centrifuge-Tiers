package com.YTrollman.CentrifugeTiers.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

import com.YTrollman.CentrifugeTiers.tileentity.CentrifugeCasingTileEntityTier4;

public class CentrifugeCasingBlockTier4 extends Block {
    public CentrifugeCasingBlockTier4(Properties properties) {
        super(properties);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new CentrifugeCasingTileEntityTier4();
    }
}
