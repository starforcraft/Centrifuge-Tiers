package com.YTrollman.CentrifugeTiers.registry;

import com.YTrollman.CentrifugeTiers.CentrifugeTiers;
import com.YTrollman.CentrifugeTiers.block.CentrifugeCasingBlockTier3;
import com.YTrollman.CentrifugeTiers.block.CentrifugeCasingBlockTier4;
import com.YTrollman.CentrifugeTiers.block.CentrifugeCasingBlockTier5;
import com.YTrollman.CentrifugeTiers.block.CentrifugeCasingBlockTierCreative;
import com.YTrollman.CentrifugeTiers.block.CentrifugeControllerBlockTier3;
import com.YTrollman.CentrifugeTiers.block.CentrifugeControllerBlockTier4;
import com.YTrollman.CentrifugeTiers.block.CentrifugeControllerBlockTier5;
import com.YTrollman.CentrifugeTiers.block.CentrifugeControllerBlockTierCreative;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, CentrifugeTiers.MOD_ID);

    private static final AbstractBlock.Properties CENTRIFUGE_PROPERTIES = AbstractBlock.Properties.of(Material.METAL).strength(2).sound(SoundType.METAL);

    public static final RegistryObject<Block> CENTRIFUGE_CONTROLLER_TIER_3 = BLOCKS.register("centrifuge_controller_tier_3", () -> new CentrifugeControllerBlockTier3(CENTRIFUGE_PROPERTIES));
    public static final RegistryObject<Block> CENTRIFUGE_CASING_TIER_3 = BLOCKS.register("centrifuge_casing_tier_3", () -> new CentrifugeCasingBlockTier3(CENTRIFUGE_PROPERTIES));
    
    public static final RegistryObject<Block> CENTRIFUGE_CONTROLLER_TIER_4 = BLOCKS.register("centrifuge_controller_tier_4", () -> new CentrifugeControllerBlockTier4(CENTRIFUGE_PROPERTIES));
    public static final RegistryObject<Block> CENTRIFUGE_CASING_TIER_4 = BLOCKS.register("centrifuge_casing_tier_4", () -> new CentrifugeCasingBlockTier4(CENTRIFUGE_PROPERTIES));
    
    public static final RegistryObject<Block> CENTRIFUGE_CONTROLLER_TIER_5 = BLOCKS.register("centrifuge_controller_tier_5", () -> new CentrifugeControllerBlockTier5(CENTRIFUGE_PROPERTIES));
    public static final RegistryObject<Block> CENTRIFUGE_CASING_TIER_5 = BLOCKS.register("centrifuge_casing_tier_5", () -> new CentrifugeCasingBlockTier5(CENTRIFUGE_PROPERTIES));
    
    public static final RegistryObject<Block> CENTRIFUGE_CONTROLLER_TIER_CREATIVE = BLOCKS.register("centrifuge_controller_tier_creative", () -> new CentrifugeControllerBlockTierCreative(CENTRIFUGE_PROPERTIES));
    public static final RegistryObject<Block> CENTRIFUGE_CASING_TIER_CREATIVE = BLOCKS.register("centrifuge_casing_tier_creative", () -> new CentrifugeCasingBlockTierCreative(CENTRIFUGE_PROPERTIES));
}