package com.YTrollman.CentrifugeTiers.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

import com.YTrollman.CentrifugeTiers.tileentity.CentrifugeCasingTileEntityTier3;

public class CentrifugeCasingBlockTier3 extends Block {
    public CentrifugeCasingBlockTier3(Properties properties) {
        super(properties);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new CentrifugeCasingTileEntityTier3();
    }
}
