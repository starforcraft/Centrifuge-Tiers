package com.YTrollman.CentrifugeTiers.registry;

import com.YTrollman.CentrifugeTiers.CentrifugeTiers;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModCompressedBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, CentrifugeTiers.MOD_ID);
    
	//Compressed Iron Block
	public static final RegistryObject<Block> COMPRESSED_IRON_BLOCK = BLOCKS.register("compressed_iron_block", () -> new Block(Block.Properties.create(Material.IRON).requiresTool().hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> DOUBLE_COMPRESSED_IRON_BLOCK = BLOCKS.register("double_compressed_iron_block", () -> new Block(Block.Properties.create(Material.IRON).requiresTool().hardnessAndResistance(10.0F, 12.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> TRIPLE_COMPRESSED_IRON_BLOCK = BLOCKS.register("triple_compressed_iron_block", () -> new Block(Block.Properties.create(Material.IRON).requiresTool().hardnessAndResistance(20.0F, 24.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> QUADRUPLE_COMPRESSED_IRON_BLOCK = BLOCKS.register("quadruple_compressed_iron_block", () -> new Block(Block.Properties.create(Material.IRON).requiresTool().hardnessAndResistance(40.0F, 48.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> QUINTUPLE_COMPRESSED_IRON_BLOCK = BLOCKS.register("quintuple_compressed_iron_block", () -> new Block(Block.Properties.create(Material.IRON).requiresTool().hardnessAndResistance(80.0F, 96.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> SEXTUPLE_COMPRESSED_IRON_BLOCK = BLOCKS.register("sextuple_compressed_iron_block", () -> new Block(Block.Properties.create(Material.IRON).requiresTool().hardnessAndResistance(160.0F, 192.0F).sound(SoundType.METAL)));

	//Compressed Gold Block
	public static final RegistryObject<Block> COMPRESSED_GOLD_BLOCK = BLOCKS.register("compressed_gold_block", () -> new Block(Block.Properties.create(Material.IRON).requiresTool().hardnessAndResistance(3.0F, 6.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> DOUBLE_COMPRESSED_GOLD_BLOCK = BLOCKS.register("double_compressed_gold_block", () -> new Block(Block.Properties.create(Material.IRON).requiresTool().hardnessAndResistance(6.0F, 12.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> TRIPLE_COMPRESSED_GOLD_BLOCK = BLOCKS.register("triple_compressed_gold_block", () -> new Block(Block.Properties.create(Material.IRON).requiresTool().hardnessAndResistance(12.0F, 24.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> QUADRUPLE_COMPRESSED_GOLD_BLOCK = BLOCKS.register("quadruple_compressed_gold_block", () -> new Block(Block.Properties.create(Material.IRON).requiresTool().hardnessAndResistance(24.0F, 48.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> QUINTUPLE_COMPRESSED_GOLD_BLOCK = BLOCKS.register("quintuple_compressed_gold_block", () -> new Block(Block.Properties.create(Material.IRON).requiresTool().hardnessAndResistance(48.0F, 96.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> SEXTUPLE_COMPRESSED_GOLD_BLOCK = BLOCKS.register("sextuple_compressed_gold_block", () -> new Block(Block.Properties.create(Material.IRON).requiresTool().hardnessAndResistance(96.0F, 192.0F).sound(SoundType.METAL)));

	//Compressed Diamond Block
	public static final RegistryObject<Block> COMPRESSED_DIAMOND_BLOCK = BLOCKS.register("compressed_diamond_block", () -> new Block(Block.Properties.create(Material.IRON).requiresTool().hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> DOUBLE_COMPRESSED_DIAMOND_BLOCK = BLOCKS.register("double_compressed_diamond_block", () -> new Block(Block.Properties.create(Material.IRON).requiresTool().hardnessAndResistance(10.0F, 12.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> TRIPLE_COMPRESSED_DIAMOND_BLOCK = BLOCKS.register("triple_compressed_diamond_block", () -> new Block(Block.Properties.create(Material.IRON).requiresTool().hardnessAndResistance(20.0F, 24.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> QUADRUPLE_COMPRESSED_DIAMOND_BLOCK = BLOCKS.register("quadruple_compressed_diamond_block", () -> new Block(Block.Properties.create(Material.IRON).requiresTool().hardnessAndResistance(40.0F, 48.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> QUINTUPLE_COMPRESSED_DIAMOND_BLOCK = BLOCKS.register("quintuple_compressed_diamond_block", () -> new Block(Block.Properties.create(Material.IRON).requiresTool().hardnessAndResistance(80.0F, 96.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> SEXTUPLE_COMPRESSED_DIAMOND_BLOCK = BLOCKS.register("sextuple_compressed_diamond_block", () -> new Block(Block.Properties.create(Material.IRON).requiresTool().hardnessAndResistance(160.0F, 192.0F).sound(SoundType.METAL)));
	
	//Compressed Emerald Block
	public static final RegistryObject<Block> COMPRESSED_EMERALD_BLOCK = BLOCKS.register("compressed_emerald_block", () -> new Block(Block.Properties.create(Material.IRON).requiresTool().hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> DOUBLE_COMPRESSED_EMERALD_BLOCK = BLOCKS.register("double_compressed_emerald_block", () -> new Block(Block.Properties.create(Material.IRON).requiresTool().hardnessAndResistance(10.0F, 12.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> TRIPLE_COMPRESSED_EMERALD_BLOCK = BLOCKS.register("triple_compressed_emerald_block", () -> new Block(Block.Properties.create(Material.IRON).requiresTool().hardnessAndResistance(20.0F, 24.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> QUADRUPLE_COMPRESSED_EMERALD_BLOCK = BLOCKS.register("quadruple_compressed_emerald_block", () -> new Block(Block.Properties.create(Material.IRON).requiresTool().hardnessAndResistance(40.0F, 48.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> QUINTUPLE_COMPRESSED_EMERALD_BLOCK = BLOCKS.register("quintuple_compressed_emerald_block", () -> new Block(Block.Properties.create(Material.IRON).requiresTool().hardnessAndResistance(80.0F, 96.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> SEXTUPLE_COMPRESSED_EMERALD_BLOCK = BLOCKS.register("sextuple_compressed_emerald_block", () -> new Block(Block.Properties.create(Material.IRON).requiresTool().hardnessAndResistance(160.0F, 192.0F).sound(SoundType.METAL)));
}