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

    private static final AbstractBlock.Properties CENTRIFUGE_PROPERTIES = Block.Properties.create(Material.IRON).hardnessAndResistance(2).sound(SoundType.METAL);

    public static final RegistryObject<Block> CENTRIFUGE_CONTROLLER_TIER_3 = BLOCKS.register("centrifuge_controller_tier_3", () -> new CentrifugeControllerBlockTier3(CENTRIFUGE_PROPERTIES));
    public static final RegistryObject<Block> CENTRIFUGE_CASING_TIER_3 = BLOCKS.register("centrifuge_casing_tier_3", () -> new CentrifugeCasingBlockTier3(CENTRIFUGE_PROPERTIES));
    
    public static final RegistryObject<Block> CENTRIFUGE_CONTROLLER_TIER_4 = BLOCKS.register("centrifuge_controller_tier_4", () -> new CentrifugeControllerBlockTier4(CENTRIFUGE_PROPERTIES));
    public static final RegistryObject<Block> CENTRIFUGE_CASING_TIER_4 = BLOCKS.register("centrifuge_casing_tier_4", () -> new CentrifugeCasingBlockTier4(CENTRIFUGE_PROPERTIES));
    
    public static final RegistryObject<Block> CENTRIFUGE_CONTROLLER_TIER_5 = BLOCKS.register("centrifuge_controller_tier_5", () -> new CentrifugeControllerBlockTier5(CENTRIFUGE_PROPERTIES));
    public static final RegistryObject<Block> CENTRIFUGE_CASING_TIER_5 = BLOCKS.register("centrifuge_casing_tier_5", () -> new CentrifugeCasingBlockTier5(CENTRIFUGE_PROPERTIES));
    
    public static final RegistryObject<Block> CENTRIFUGE_CONTROLLER_TIER_CREATIVE = BLOCKS.register("centrifuge_controller_tier_creative", () -> new CentrifugeControllerBlockTierCreative(CENTRIFUGE_PROPERTIES));
    public static final RegistryObject<Block> CENTRIFUGE_CASING_TIER_CREATIVE = BLOCKS.register("centrifuge_casing_tier_creative", () -> new CentrifugeCasingBlockTierCreative(CENTRIFUGE_PROPERTIES));

	//Compressed Iron Block
	public static final RegistryObject<Block> COMPRESSED_IRON_BLOCK = BLOCKS.register("compressed_iron_block", () -> new Block(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> DOUBLE_COMPRESSED_IRON_BLOCK = BLOCKS.register("double_compressed_iron_block", () -> new Block(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(10.0F, 12.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> TRIPLE_COMPRESSED_IRON_BLOCK = BLOCKS.register("triple_compressed_iron_block", () -> new Block(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(20.0F, 24.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> QUADRUPLE_COMPRESSED_IRON_BLOCK = BLOCKS.register("quadruple_compressed_iron_block", () -> new Block(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(40.0F, 48.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> QUINTUPLE_COMPRESSED_IRON_BLOCK = BLOCKS.register("quintuple_compressed_iron_block", () -> new Block(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(80.0F, 96.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> SEXTUPLE_COMPRESSED_IRON_BLOCK = BLOCKS.register("sextuple_compressed_iron_block", () -> new Block(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(160.0F, 192.0F).sound(SoundType.METAL)));

	//Compressed Gold Block
	public static final RegistryObject<Block> COMPRESSED_GOLD_BLOCK = BLOCKS.register("compressed_gold_block", () -> new Block(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(3.0F, 6.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> DOUBLE_COMPRESSED_GOLD_BLOCK = BLOCKS.register("double_compressed_gold_block", () -> new Block(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(6.0F, 12.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> TRIPLE_COMPRESSED_GOLD_BLOCK = BLOCKS.register("triple_compressed_gold_block", () -> new Block(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(12.0F, 24.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> QUADRUPLE_COMPRESSED_GOLD_BLOCK = BLOCKS.register("quadruple_compressed_gold_block", () -> new Block(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(24.0F, 48.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> QUINTUPLE_COMPRESSED_GOLD_BLOCK = BLOCKS.register("quintuple_compressed_gold_block", () -> new Block(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(48.0F, 96.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> SEXTUPLE_COMPRESSED_GOLD_BLOCK = BLOCKS.register("sextuple_compressed_gold_block", () -> new Block(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(96.0F, 192.0F).sound(SoundType.METAL)));

	//Compressed Diamond Block
	public static final RegistryObject<Block> COMPRESSED_DIAMOND_BLOCK = BLOCKS.register("compressed_diamond_block", () -> new Block(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> DOUBLE_COMPRESSED_DIAMOND_BLOCK = BLOCKS.register("double_compressed_diamond_block", () -> new Block(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(10.0F, 12.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> TRIPLE_COMPRESSED_DIAMOND_BLOCK = BLOCKS.register("triple_compressed_diamond_block", () -> new Block(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(20.0F, 24.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> QUADRUPLE_COMPRESSED_DIAMOND_BLOCK = BLOCKS.register("quadruple_compressed_diamond_block", () -> new Block(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(40.0F, 48.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> QUINTUPLE_COMPRESSED_DIAMOND_BLOCK = BLOCKS.register("quintuple_compressed_diamond_block", () -> new Block(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(80.0F, 96.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> SEXTUPLE_COMPRESSED_DIAMOND_BLOCK = BLOCKS.register("sextuple_compressed_diamond_block", () -> new Block(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(160.0F, 192.0F).sound(SoundType.METAL)));
	
	//Compressed Emerald Block
	public static final RegistryObject<Block> COMPRESSED_EMERALD_BLOCK = BLOCKS.register("compressed_emerald_block", () -> new Block(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> DOUBLE_COMPRESSED_EMERALD_BLOCK = BLOCKS.register("double_compressed_emerald_block", () -> new Block(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(10.0F, 12.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> TRIPLE_COMPRESSED_EMERALD_BLOCK = BLOCKS.register("triple_compressed_emerald_block", () -> new Block(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(20.0F, 24.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> QUADRUPLE_COMPRESSED_EMERALD_BLOCK = BLOCKS.register("quadruple_compressed_emerald_block", () -> new Block(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(40.0F, 48.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> QUINTUPLE_COMPRESSED_EMERALD_BLOCK = BLOCKS.register("quintuple_compressed_emerald_block", () -> new Block(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(80.0F, 96.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> SEXTUPLE_COMPRESSED_EMERALD_BLOCK = BLOCKS.register("sextuple_compressed_emerald_block", () -> new Block(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(160.0F, 192.0F).sound(SoundType.METAL)));
}