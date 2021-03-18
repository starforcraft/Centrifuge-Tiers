package com.YTrollman.CentrifugeTiers.tileentity;

import javax.annotation.Nullable;

import com.resourcefulbees.resourcefulbees.tileentity.multiblocks.centrifuge.CentrifugeCasingTileEntity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;

public class CentrifugeCasingTileEntityTierCreative extends CentrifugeCasingTileEntity {
    private BlockPos controllerPos;
    
    @Override
    public void setControllerPos(@Nullable BlockPos controllerPos) {
        this.controllerPos = controllerPos;
    }
    
    @Override
    public boolean isLinked() {
        return controllerPos != null;
    }
    
    public CentrifugeCasingTileEntityTierCreative(TileEntityType<?> tileEntityType) 
    { 
    	super(tileEntityType); 
    }
    
    @Override
    public void setRemoved() {
        CentrifugeControllerTileEntityTierCreative controller = getController();
        if (controller != null) {
            controller.invalidateStructure();
        }
        super.setRemoved();
    }

    @Override
    public CentrifugeControllerTileEntityTierCreative getController() {
        if (isLinked() && this.level != null) {
            TileEntity tileEntity = this.level.getBlockEntity(controllerPos);
            if (tileEntity instanceof CentrifugeControllerTileEntityTierCreative) {
                return (CentrifugeControllerTileEntityTierCreative) tileEntity;
            } else {
                setControllerPos(null);
            }
        }
        return null;
    }
}
