package com.YTrollman.CentrifugeTiers.registry;

import com.YTrollman.CentrifugeTiers.CentrifugeTiers;
import com.YTrollman.CentrifugeTiers.tileentity.CentrifugeCasingTileEntityTier3;
import com.YTrollman.CentrifugeTiers.tileentity.CentrifugeCasingTileEntityTier4;
import com.YTrollman.CentrifugeTiers.tileentity.CentrifugeCasingTileEntityTier5;
import com.YTrollman.CentrifugeTiers.tileentity.CentrifugeCasingTileEntityTierCreative;
import com.YTrollman.CentrifugeTiers.tileentity.CentrifugeControllerTileEntityTier3;
import com.YTrollman.CentrifugeTiers.tileentity.CentrifugeControllerTileEntityTier4;
import com.YTrollman.CentrifugeTiers.tileentity.CentrifugeControllerTileEntityTier5;
import com.YTrollman.CentrifugeTiers.tileentity.CentrifugeControllerTileEntityTierCreative;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTileEntityTypes {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, CentrifugeTiers.MOD_ID);

    public static final RegistryObject<TileEntityType<?>> CENTRIFUGE_CONTROLLER_ENTITY_TIER_3 = TILE_ENTITY_TYPES.register("centrifuge_controller_tier_3", () -> TileEntityType.Builder
            .of(() -> new CentrifugeControllerTileEntityTier3(ModTileEntityTypes.CENTRIFUGE_CONTROLLER_ENTITY_TIER_3.get()), ModBlocks.CENTRIFUGE_CONTROLLER_TIER_3.get())
            .build(null));
    public static final RegistryObject<TileEntityType<?>> CENTRIFUGE_CASING_ENTITY_TIER_3  = TILE_ENTITY_TYPES.register("centrifuge_casing_tier_3", () -> TileEntityType.Builder
            .of(() -> new CentrifugeCasingTileEntityTier3(ModTileEntityTypes.CENTRIFUGE_CASING_ENTITY_TIER_3.get()), ModBlocks.CENTRIFUGE_CASING_TIER_3.get())
            .build(null));
    
    public static final RegistryObject<TileEntityType<?>> CENTRIFUGE_CONTROLLER_ENTITY_TIER_4 = TILE_ENTITY_TYPES.register("centrifuge_controller_tier_4", () -> TileEntityType.Builder
            .of(() -> new CentrifugeControllerTileEntityTier4(ModTileEntityTypes.CENTRIFUGE_CONTROLLER_ENTITY_TIER_4.get()), ModBlocks.CENTRIFUGE_CONTROLLER_TIER_4.get())
            .build(null));
    public static final RegistryObject<TileEntityType<?>> CENTRIFUGE_CASING_ENTITY_TIER_4  = TILE_ENTITY_TYPES.register("centrifuge_casing_tier_4", () -> TileEntityType.Builder
            .of(() -> new CentrifugeCasingTileEntityTier4(ModTileEntityTypes.CENTRIFUGE_CASING_ENTITY_TIER_4.get()), ModBlocks.CENTRIFUGE_CASING_TIER_4.get())
            .build(null));
    
    public static final RegistryObject<TileEntityType<?>> CENTRIFUGE_CONTROLLER_ENTITY_TIER_5 = TILE_ENTITY_TYPES.register("centrifuge_controller_tier_5", () -> TileEntityType.Builder
            .of(() -> new CentrifugeControllerTileEntityTier5(ModTileEntityTypes.CENTRIFUGE_CONTROLLER_ENTITY_TIER_5.get()), ModBlocks.CENTRIFUGE_CONTROLLER_TIER_5.get())
            .build(null));
    public static final RegistryObject<TileEntityType<?>> CENTRIFUGE_CASING_ENTITY_TIER_5  = TILE_ENTITY_TYPES.register("centrifuge_casing_tier_5", () -> TileEntityType.Builder
            .of(() -> new CentrifugeCasingTileEntityTier5(ModTileEntityTypes.CENTRIFUGE_CASING_ENTITY_TIER_5.get()), ModBlocks.CENTRIFUGE_CASING_TIER_5.get())
            .build(null));
    
    public static final RegistryObject<TileEntityType<?>> CENTRIFUGE_CONTROLLER_ENTITY_TIER_CREATIVE = TILE_ENTITY_TYPES.register("centrifuge_controller_tier_creative", () -> TileEntityType.Builder
            .of(() -> new CentrifugeControllerTileEntityTierCreative(ModTileEntityTypes.CENTRIFUGE_CONTROLLER_ENTITY_TIER_CREATIVE.get()), ModBlocks.CENTRIFUGE_CONTROLLER_TIER_CREATIVE.get())
            .build(null));
    public static final RegistryObject<TileEntityType<?>> CENTRIFUGE_CASING_ENTITY_TIER_CREATIVE  = TILE_ENTITY_TYPES.register("centrifuge_casing_tier_creative", () -> TileEntityType.Builder
            .of(() -> new CentrifugeCasingTileEntityTierCreative(ModTileEntityTypes.CENTRIFUGE_CASING_ENTITY_TIER_CREATIVE.get()), ModBlocks.CENTRIFUGE_CASING_TIER_CREATIVE.get())
            .build(null));
}
