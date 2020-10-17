package com.YTrollman.CentrifugeTiers.registry;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.YTrollman.CentrifugeTiers.CentrifugeTiers;
import com.YTrollman.CentrifugeTiers.block.CentrifugeCasingBlockTier2;
import com.YTrollman.CentrifugeTiers.block.CentrifugeCasingBlockTier3;
import com.YTrollman.CentrifugeTiers.block.CentrifugeCasingBlockTier4;
import com.YTrollman.CentrifugeTiers.block.CentrifugeCasingBlockTier5;
import com.YTrollman.CentrifugeTiers.block.CentrifugeCasingBlockTierCreative;
import com.YTrollman.CentrifugeTiers.block.CentrifugeControllerBlockTier2;
import com.YTrollman.CentrifugeTiers.block.CentrifugeControllerBlockTier3;
import com.YTrollman.CentrifugeTiers.block.CentrifugeControllerBlockTier4;
import com.YTrollman.CentrifugeTiers.block.CentrifugeControllerBlockTier5;
import com.YTrollman.CentrifugeTiers.block.CentrifugeControllerBlockTierCreative;
import com.YTrollman.CentrifugeTiers.container.CentrifugeMultiblockContainerTier2;
import com.YTrollman.CentrifugeTiers.container.CentrifugeMultiblockContainerTier3;
import com.YTrollman.CentrifugeTiers.container.CentrifugeMultiblockContainerTier4;
import com.YTrollman.CentrifugeTiers.container.CentrifugeMultiblockContainerTier5;
import com.YTrollman.CentrifugeTiers.container.CentrifugeMultiblockContainerTierCreative;
import com.YTrollman.CentrifugeTiers.tileentity.CentrifugeCasingTileEntityTier2;
import com.YTrollman.CentrifugeTiers.tileentity.CentrifugeCasingTileEntityTier3;
import com.YTrollman.CentrifugeTiers.tileentity.CentrifugeCasingTileEntityTier4;
import com.YTrollman.CentrifugeTiers.tileentity.CentrifugeCasingTileEntityTier5;
import com.YTrollman.CentrifugeTiers.tileentity.CentrifugeCasingTileEntityTierCreative;
import com.YTrollman.CentrifugeTiers.tileentity.CentrifugeControllerTileEntityTier2;
import com.YTrollman.CentrifugeTiers.tileentity.CentrifugeControllerTileEntityTier3;
import com.YTrollman.CentrifugeTiers.tileentity.CentrifugeControllerTileEntityTier4;
import com.YTrollman.CentrifugeTiers.tileentity.CentrifugeControllerTileEntityTier5;
import com.YTrollman.CentrifugeTiers.tileentity.CentrifugeControllerTileEntityTierCreative;
import com.resourcefulbees.resourcefulbees.registry.ItemGroupResourcefulBees;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.*;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistryHandler {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, CentrifugeTiers.MOD_ID);
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, CentrifugeTiers.MOD_ID);
	public static final DeferredRegister<TileEntityType<?>>	TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, CentrifugeTiers.MOD_ID);
	public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = DeferredRegister.create(ForgeRegistries.CONTAINERS, CentrifugeTiers.MOD_ID);

    public static void init() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		ITEMS.register(bus);
		BLOCKS.register(bus);
		TILE_ENTITY_TYPES.register(bus);
		CONTAINER_TYPES.register(bus);
	}

    //Tier 2
	public static final RegistryObject<Block> CENTRIFUGE_CONTROLLER_TIER_2 = BLOCKS.register("centrifuge_controller_tier_2", () -> new CentrifugeControllerBlockTier2(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(2).sound(SoundType.METAL)));
	public static final RegistryObject<Block> CENTRIFUGE_CASING_TIER_2 = BLOCKS.register("centrifuge_casing_tier_2", () -> new CentrifugeCasingBlockTier2(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(2).sound(SoundType.METAL)));

	public static final RegistryObject<Item> CENTRIFUGE_CONTROLLER_ITEM_TIER_2 = ITEMS.register("centrifuge_controller_tier_2", () -> new BlockItem(CENTRIFUGE_CONTROLLER_TIER_2.get(), new Item.Properties().group(ItemGroupResourcefulBees.RESOURCEFUL_BEES)));
	public static final RegistryObject<Item> CENTRIFUGE_CASING_ITEM_TIER_2 = ITEMS.register("centrifuge_casing_tier_2", () -> new BlockItem(CENTRIFUGE_CASING_TIER_2.get(), new Item.Properties().group(ItemGroupResourcefulBees.RESOURCEFUL_BEES)));
	
	public static final RegistryObject<ContainerType<CentrifugeMultiblockContainerTier2>> CENTRIFUGE_MULTIBLOCK_CONTAINER_TIER_2 = CONTAINER_TYPES.register("centrifuge_multiblock_tier_2", () -> IForgeContainerType
			.create((id,inv,c) -> new CentrifugeMultiblockContainerTier2(id, inv.player.world, c.readBlockPos(), inv)));
	
	public static final RegistryObject<TileEntityType<?>> CENTRIFUGE_CONTROLLER_ENTITY_TIER_2 = TILE_ENTITY_TYPES.register("centrifuge_controller_tier_2", () -> TileEntityType.Builder
			.create(CentrifugeControllerTileEntityTier2::new, CENTRIFUGE_CONTROLLER_TIER_2.get())
			.build(null));
	
	public static final RegistryObject<TileEntityType<?>> CENTRIFUGE_CASING_ENTITY_TIER_2 = TILE_ENTITY_TYPES.register("centrifuge_casing_tier_2", () -> TileEntityType.Builder
			.create(CentrifugeCasingTileEntityTier2::new, CENTRIFUGE_CASING_TIER_2.get())
			.build(null));
	
    //Tier 3
	public static final RegistryObject<Block> CENTRIFUGE_CONTROLLER_TIER_3 = BLOCKS.register("centrifuge_controller_tier_3", () -> new CentrifugeControllerBlockTier3(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(2).sound(SoundType.METAL)));
	public static final RegistryObject<Block> CENTRIFUGE_CASING_TIER_3 = BLOCKS.register("centrifuge_casing_tier_3", () -> new CentrifugeCasingBlockTier3(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(2).sound(SoundType.METAL)));

	public static final RegistryObject<Item> CENTRIFUGE_CONTROLLER_ITEM_TIER_3 = ITEMS.register("centrifuge_controller_tier_3", () -> new BlockItem(CENTRIFUGE_CONTROLLER_TIER_3.get(), new Item.Properties().group(ItemGroupResourcefulBees.RESOURCEFUL_BEES)));
	public static final RegistryObject<Item> CENTRIFUGE_CASING_ITEM_TIER_3 = ITEMS.register("centrifuge_casing_tier_3", () -> new BlockItem(CENTRIFUGE_CASING_TIER_3.get(), new Item.Properties().group(ItemGroupResourcefulBees.RESOURCEFUL_BEES)));
	
	public static final RegistryObject<ContainerType<CentrifugeMultiblockContainerTier3>> CENTRIFUGE_MULTIBLOCK_CONTAINER_TIER_3 = CONTAINER_TYPES.register("centrifuge_multiblock_tier_3", () -> IForgeContainerType
			.create((id,inv,c) -> new CentrifugeMultiblockContainerTier3(id, inv.player.world, c.readBlockPos(), inv)));
	
	public static final RegistryObject<TileEntityType<?>> CENTRIFUGE_CONTROLLER_ENTITY_TIER_3 = TILE_ENTITY_TYPES.register("centrifuge_controller_tier_3", () -> TileEntityType.Builder
			.create(CentrifugeControllerTileEntityTier3::new, CENTRIFUGE_CONTROLLER_TIER_3.get())
			.build(null));
	
	public static final RegistryObject<TileEntityType<?>> CENTRIFUGE_CASING_ENTITY_TIER_3 = TILE_ENTITY_TYPES.register("centrifuge_casing_tier_3", () -> TileEntityType.Builder
			.create(CentrifugeCasingTileEntityTier3::new, CENTRIFUGE_CASING_TIER_3.get())
			.build(null));
	
    //Tier 4
	public static final RegistryObject<Block> CENTRIFUGE_CONTROLLER_TIER_4 = BLOCKS.register("centrifuge_controller_tier_4", () -> new CentrifugeControllerBlockTier4(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(2).sound(SoundType.METAL)));
	public static final RegistryObject<Block> CENTRIFUGE_CASING_TIER_4 = BLOCKS.register("centrifuge_casing_tier_4", () -> new CentrifugeCasingBlockTier4(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(2).sound(SoundType.METAL)));

	public static final RegistryObject<Item> CENTRIFUGE_CONTROLLER_ITEM_TIER_4 = ITEMS.register("centrifuge_controller_tier_4", () -> new BlockItem(CENTRIFUGE_CONTROLLER_TIER_4.get(), new Item.Properties().group(ItemGroupResourcefulBees.RESOURCEFUL_BEES)));
	public static final RegistryObject<Item> CENTRIFUGE_CASING_ITEM_TIER_4 = ITEMS.register("centrifuge_casing_tier_4", () -> new BlockItem(CENTRIFUGE_CASING_TIER_4.get(), new Item.Properties().group(ItemGroupResourcefulBees.RESOURCEFUL_BEES)));
	
	public static final RegistryObject<ContainerType<CentrifugeMultiblockContainerTier4>> CENTRIFUGE_MULTIBLOCK_CONTAINER_TIER_4 = CONTAINER_TYPES.register("centrifuge_multiblock_tier_4", () -> IForgeContainerType
			.create((id,inv,c) -> new CentrifugeMultiblockContainerTier4(id, inv.player.world, c.readBlockPos(), inv)));
	
	public static final RegistryObject<TileEntityType<?>> CENTRIFUGE_CONTROLLER_ENTITY_TIER_4 = TILE_ENTITY_TYPES.register("centrifuge_controller_tier_4", () -> TileEntityType.Builder
			.create(CentrifugeControllerTileEntityTier4::new, CENTRIFUGE_CONTROLLER_TIER_4.get())
			.build(null));
	
	public static final RegistryObject<TileEntityType<?>> CENTRIFUGE_CASING_ENTITY_TIER_4 = TILE_ENTITY_TYPES.register("centrifuge_casing_tier_4", () -> TileEntityType.Builder
			.create(CentrifugeCasingTileEntityTier4::new, CENTRIFUGE_CASING_TIER_4.get())
			.build(null));
	
    //Tier 5
	public static final RegistryObject<Block> CENTRIFUGE_CONTROLLER_TIER_5 = BLOCKS.register("centrifuge_controller_tier_5", () -> new CentrifugeControllerBlockTier5(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(2).sound(SoundType.METAL)));
	public static final RegistryObject<Block> CENTRIFUGE_CASING_TIER_5 = BLOCKS.register("centrifuge_casing_tier_5", () -> new CentrifugeCasingBlockTier5(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(2).sound(SoundType.METAL)));

	public static final RegistryObject<Item> CENTRIFUGE_CONTROLLER_ITEM_TIER_5 = ITEMS.register("centrifuge_controller_tier_5", () -> new BlockItem(CENTRIFUGE_CONTROLLER_TIER_5.get(), new Item.Properties().group(ItemGroupResourcefulBees.RESOURCEFUL_BEES)));
	public static final RegistryObject<Item> CENTRIFUGE_CASING_ITEM_TIER_5 = ITEMS.register("centrifuge_casing_tier_5", () -> new BlockItem(CENTRIFUGE_CASING_TIER_5.get(), new Item.Properties().group(ItemGroupResourcefulBees.RESOURCEFUL_BEES)));
	
	public static final RegistryObject<ContainerType<CentrifugeMultiblockContainerTier5>> CENTRIFUGE_MULTIBLOCK_CONTAINER_TIER_5 = CONTAINER_TYPES.register("centrifuge_multiblock_tier_5", () -> IForgeContainerType
			.create((id,inv,c) -> new CentrifugeMultiblockContainerTier5(id, inv.player.world, c.readBlockPos(), inv)));
	
	public static final RegistryObject<TileEntityType<?>> CENTRIFUGE_CONTROLLER_ENTITY_TIER_5 = TILE_ENTITY_TYPES.register("centrifuge_controller_tier_5", () -> TileEntityType.Builder
			.create(CentrifugeControllerTileEntityTier5::new, CENTRIFUGE_CONTROLLER_TIER_5.get())
			.build(null));
	
	public static final RegistryObject<TileEntityType<?>> CENTRIFUGE_CASING_ENTITY_TIER_5 = TILE_ENTITY_TYPES.register("centrifuge_casing_tier_5", () -> TileEntityType.Builder
			.create(CentrifugeCasingTileEntityTier5::new, CENTRIFUGE_CASING_TIER_5.get())
			.build(null));
    //Tier Creative
	public static final RegistryObject<Block> CENTRIFUGE_CONTROLLER_TIER_CREATIVE = BLOCKS.register("centrifuge_controller_tier_creative", () -> new CentrifugeControllerBlockTierCreative(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(2).sound(SoundType.METAL)));
	public static final RegistryObject<Block> CENTRIFUGE_CASING_TIER_CREATIVE = BLOCKS.register("centrifuge_casing_tier_creative", () -> new CentrifugeCasingBlockTierCreative(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(2).sound(SoundType.METAL)));

	public static final RegistryObject<Item> CENTRIFUGE_CONTROLLER_ITEM_TIER_CREATIVE = ITEMS.register("centrifuge_controller_tier_creative", () -> new BlockItem(CENTRIFUGE_CONTROLLER_TIER_CREATIVE.get(), new Item.Properties().group(ItemGroupResourcefulBees.RESOURCEFUL_BEES)));
	public static final RegistryObject<Item> CENTRIFUGE_CASING_ITEM_TIER_CREATIVE = ITEMS.register("centrifuge_casing_tier_creative", () -> new BlockItem(CENTRIFUGE_CASING_TIER_CREATIVE.get(), new Item.Properties().group(ItemGroupResourcefulBees.RESOURCEFUL_BEES)));
	
	public static final RegistryObject<ContainerType<CentrifugeMultiblockContainerTierCreative>> CENTRIFUGE_MULTIBLOCK_CONTAINER_TIER_CREATIVE = CONTAINER_TYPES.register("centrifuge_multiblock_tier_creative", () -> IForgeContainerType
			.create((id,inv,c) -> new CentrifugeMultiblockContainerTierCreative(id, inv.player.world, c.readBlockPos(), inv)));
	
	public static final RegistryObject<TileEntityType<?>> CENTRIFUGE_CONTROLLER_ENTITY_TIER_CREATIVE = TILE_ENTITY_TYPES.register("centrifuge_controller_tier_creative", () -> TileEntityType.Builder
			.create(CentrifugeControllerTileEntityTierCreative::new, CENTRIFUGE_CONTROLLER_TIER_CREATIVE.get())
			.build(null));
	
	public static final RegistryObject<TileEntityType<?>> CENTRIFUGE_CASING_ENTITY_TIER_CREATIVE = TILE_ENTITY_TYPES.register("centrifuge_casing_tier_creative", () -> TileEntityType.Builder
			.create(CentrifugeCasingTileEntityTierCreative::new, CENTRIFUGE_CASING_TIER_CREATIVE.get())
			.build(null));
	
    //Compressed Cobblestone
	public static final RegistryObject<Block> COMPRESSED_COBBLESTONE = BLOCKS.register("compressed_cobblestone", () -> new Block(Block.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(2.0F, 6.0F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> DOUBLE_COMPRESSED_COBBLESTONE = BLOCKS.register("double_compressed_cobblestone", () -> new Block(Block.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(4.0F, 12.0F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> TRIPLE_COMPRESSED_COBBLESTONE = BLOCKS.register("triple_compressed_cobblestone", () -> new Block(Block.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(8.0F, 24.0F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> QUADRUPLE_COMPRESSED_COBBLESTONE = BLOCKS.register("quadruple_compressed_cobblestone", () -> new Block(Block.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(16.0F, 48.0F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> QUINTUPLE_COMPRESSED_COBBLESTONE = BLOCKS.register("quintuple_compressed_cobblestone", () -> new Block(Block.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(32.0F, 96.0F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> SEXTUPLE_COMPRESSED_COBBLESTONE = BLOCKS.register("sextuple_compressed_cobblestone", () -> new Block(Block.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(64.0F, 192.0F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> SEPTUPLICATE_COMPRESSED_COBBLESTONE = BLOCKS.register("septuplicate_compressed_cobblestone", () -> new Block(Block.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(128.0F, 384.0F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> OCTUPLE_COMPRESSED_COBBLESTONE = BLOCKS.register("octuple_compressed_cobblestone", () -> new Block(Block.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(256.0F, 768.0F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> NINEFOLD_COMPRESSED_COBBLESTONE = BLOCKS.register("ninefold_compressed_cobblestone", () -> new Block(Block.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(512.0F, 1536.0F).sound(SoundType.STONE)));

	public static final RegistryObject<Item> COMPRESSED_COBBLESTONE_ITEM = ITEMS.register("compressed_cobblestone", () -> new BlockItem(COMPRESSED_COBBLESTONE.get(), new Item.Properties().group(ItemGroupResourcefulBees.RESOURCEFUL_BEES)){
		@OnlyIn(Dist.CLIENT)
		@Override
		public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
			tooltip.add(new StringTextComponent(TextFormatting.GOLD + I18n.format("item.ctiers.compressed_cobblestone")));
			super.addInformation(stack, worldIn, tooltip, flagIn);
		}
	});
	public static final RegistryObject<Item> DOUBLE_COMPRESSED_COBBLESTONE_ITEM = ITEMS.register("double_compressed_cobblestone", () -> new BlockItem(DOUBLE_COMPRESSED_COBBLESTONE.get(), new Item.Properties().group(ItemGroupResourcefulBees.RESOURCEFUL_BEES)){
		@OnlyIn(Dist.CLIENT)
		@Override
		public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
			tooltip.add(new StringTextComponent(TextFormatting.GOLD + I18n.format("item.ctiers.double_compressed_cobblestone")));
			super.addInformation(stack, worldIn, tooltip, flagIn);
		}
	});
	public static final RegistryObject<Item> TRIPLE_COMPRESSED_COBBLESTONE_ITEM = ITEMS.register("triple_compressed_cobblestone", () -> new BlockItem(TRIPLE_COMPRESSED_COBBLESTONE.get(), new Item.Properties().group(ItemGroupResourcefulBees.RESOURCEFUL_BEES)){
		@OnlyIn(Dist.CLIENT)
		@Override
		public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
			tooltip.add(new StringTextComponent(TextFormatting.GOLD + I18n.format("item.ctiers.triple_compressed_cobblestone")));
			super.addInformation(stack, worldIn, tooltip, flagIn);
		}
	});
	public static final RegistryObject<Item> QUADRUPLE_COMPRESSED_COBBLESTONE_ITEM = ITEMS.register("quadruple_compressed_cobblestone", () -> new BlockItem(QUADRUPLE_COMPRESSED_COBBLESTONE.get(), new Item.Properties().group(ItemGroupResourcefulBees.RESOURCEFUL_BEES)){
		@OnlyIn(Dist.CLIENT)
		@Override
		public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
			tooltip.add(new StringTextComponent(TextFormatting.GOLD + I18n.format("item.ctiers.quadruple_compressed_cobblestone")));
			super.addInformation(stack, worldIn, tooltip, flagIn);
		}
	});
	public static final RegistryObject<Item> QUINTUPLE_COMPRESSED_COBBLESTONE_ITEM = ITEMS.register("quintuple_compressed_cobblestone", () -> new BlockItem(QUINTUPLE_COMPRESSED_COBBLESTONE.get(), new Item.Properties().group(ItemGroupResourcefulBees.RESOURCEFUL_BEES)){
		@OnlyIn(Dist.CLIENT)
		@Override
		public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
			tooltip.add(new StringTextComponent(TextFormatting.GOLD + I18n.format("item.ctiers.quintuple_compressed_cobblestone")));
			super.addInformation(stack, worldIn, tooltip, flagIn);
		}
	});
	public static final RegistryObject<Item> SEXTUPLE_COMPRESSED_COBBLESTONE_ITEM = ITEMS.register("sextuple_compressed_cobblestone", () -> new BlockItem(SEXTUPLE_COMPRESSED_COBBLESTONE.get(), new Item.Properties().group(ItemGroupResourcefulBees.RESOURCEFUL_BEES)){
		@OnlyIn(Dist.CLIENT)
		@Override
		public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
			tooltip.add(new StringTextComponent(TextFormatting.GOLD + I18n.format("item.ctiers.sextuple_compressed_cobblestone")));
			super.addInformation(stack, worldIn, tooltip, flagIn);
		}
	});
	public static final RegistryObject<Item> SEPTUPLICATE_COMPRESSED_COBBLESTONE_ITEM = ITEMS.register("septuplicate_compressed_cobblestone", () -> new BlockItem(SEPTUPLICATE_COMPRESSED_COBBLESTONE.get(), new Item.Properties().group(ItemGroupResourcefulBees.RESOURCEFUL_BEES)){
		@OnlyIn(Dist.CLIENT)
		@Override
		public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
			tooltip.add(new StringTextComponent(TextFormatting.GOLD + I18n.format("item.ctiers.septuplicate_compressed_cobblestone")));
			super.addInformation(stack, worldIn, tooltip, flagIn);
		}
	});
	public static final RegistryObject<Item> OCTUPLE_COMPRESSED_COBBLESTONE_ITEM = ITEMS.register("octuple_compressed_cobblestone", () -> new BlockItem(OCTUPLE_COMPRESSED_COBBLESTONE.get(), new Item.Properties().group(ItemGroupResourcefulBees.RESOURCEFUL_BEES)){
		@OnlyIn(Dist.CLIENT)
		@Override
		public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
			tooltip.add(new StringTextComponent(TextFormatting.GOLD + I18n.format("item.ctiers.octuple_compressed_cobblestone")));
			super.addInformation(stack, worldIn, tooltip, flagIn);
		}
	});
	public static final RegistryObject<Item> NINEFOLD_COMPRESSED_COBBLESTONE_ITEM = ITEMS.register("ninefold_compressed_cobblestone", () -> new BlockItem(NINEFOLD_COMPRESSED_COBBLESTONE.get(), new Item.Properties().group(ItemGroupResourcefulBees.RESOURCEFUL_BEES)){
		@OnlyIn(Dist.CLIENT)
		@Override
		public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
			tooltip.add(new StringTextComponent(TextFormatting.GOLD + I18n.format("item.ctiers.ninefold_compressed_cobblestone")));
			super.addInformation(stack, worldIn, tooltip, flagIn);
		}
	});
	
	//Compressed Iron Block
	public static final RegistryObject<Block> COMPRESSED_IRON_BLOCK = BLOCKS.register("compressed_iron_block", () -> new Block(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> DOUBLE_COMPRESSED_IRON_BLOCK = BLOCKS.register("double_compressed_iron_block", () -> new Block(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(10.0F, 12.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> TRIPLE_COMPRESSED_IRON_BLOCK = BLOCKS.register("triple_compressed_iron_block", () -> new Block(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(20.0F, 24.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> QUADRUPLE_COMPRESSED_IRON_BLOCK = BLOCKS.register("quadruple_compressed_iron_block", () -> new Block(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(40.0F, 48.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> QUINTUPLE_COMPRESSED_IRON_BLOCK = BLOCKS.register("quintuple_compressed_iron_block", () -> new Block(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(80.0F, 96.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> SEXTUPLE_COMPRESSED_IRON_BLOCK = BLOCKS.register("sextuple_compressed_iron_block", () -> new Block(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(160.0F, 192.0F).sound(SoundType.METAL)));
	
	
	
	public static final RegistryObject<Item> COMPRESSED_IRON_BLOCK_ITEM = ITEMS.register("compressed_iron_block", () -> new BlockItem(COMPRESSED_IRON_BLOCK.get(), new Item.Properties().group(ItemGroupResourcefulBees.RESOURCEFUL_BEES)){
		@OnlyIn(Dist.CLIENT)
		@Override
		public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
			tooltip.add(new StringTextComponent(TextFormatting.GOLD + I18n.format("item.ctiers.compressed_iron_block")));
			super.addInformation(stack, worldIn, tooltip, flagIn);
		}
	});
	public static final RegistryObject<Item> DOUBLE_COMPRESSED_IRON_BLOCK_ITEM = ITEMS.register("double_compressed_iron_block", () -> new BlockItem(DOUBLE_COMPRESSED_IRON_BLOCK.get(), new Item.Properties().group(ItemGroupResourcefulBees.RESOURCEFUL_BEES)){
		@OnlyIn(Dist.CLIENT)
		@Override
		public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
			tooltip.add(new StringTextComponent(TextFormatting.GOLD + I18n.format("item.ctiers.double_compressed_iron_block")));
			super.addInformation(stack, worldIn, tooltip, flagIn);
		}
	});
	public static final RegistryObject<Item> TRIPLE_COMPRESSED_IRON_BLOCK_ITEM = ITEMS.register("triple_compressed_iron_block", () -> new BlockItem(TRIPLE_COMPRESSED_IRON_BLOCK.get(), new Item.Properties().group(ItemGroupResourcefulBees.RESOURCEFUL_BEES)){
		@OnlyIn(Dist.CLIENT)
		@Override
		public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
			tooltip.add(new StringTextComponent(TextFormatting.GOLD + I18n.format("item.ctiers.triple_compressed_iron_block")));
			super.addInformation(stack, worldIn, tooltip, flagIn);
		}
	});
	public static final RegistryObject<Item> QUADRUPLE_COMPRESSED_IRON_BLOCK_ITEM = ITEMS.register("quadruple_compressed_iron_block", () -> new BlockItem(QUADRUPLE_COMPRESSED_IRON_BLOCK.get(), new Item.Properties().group(ItemGroupResourcefulBees.RESOURCEFUL_BEES)){
		@OnlyIn(Dist.CLIENT)
		@Override
		public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
			tooltip.add(new StringTextComponent(TextFormatting.GOLD + I18n.format("item.ctiers.quadruple_compressed_iron_block")));
			super.addInformation(stack, worldIn, tooltip, flagIn);
		}
	});
	public static final RegistryObject<Item> QUINTUPLE_COMPRESSED_IRON_BLOCK_ITEM = ITEMS.register("quintuple_compressed_iron_block", () -> new BlockItem(QUINTUPLE_COMPRESSED_IRON_BLOCK.get(), new Item.Properties().group(ItemGroupResourcefulBees.RESOURCEFUL_BEES)){
		@OnlyIn(Dist.CLIENT)
		@Override
		public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
			tooltip.add(new StringTextComponent(TextFormatting.GOLD + I18n.format("item.ctiers.quintuple_compressed_iron_block")));
			super.addInformation(stack, worldIn, tooltip, flagIn);
		}
	});
	public static final RegistryObject<Item> SEXTUPLE_COMPRESSED_IRON_BLOCK_ITEM = ITEMS.register("sextuple_compressed_iron_block", () -> new BlockItem(SEXTUPLE_COMPRESSED_IRON_BLOCK.get(), new Item.Properties().group(ItemGroupResourcefulBees.RESOURCEFUL_BEES)){
		@OnlyIn(Dist.CLIENT)
		@Override
		public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
			tooltip.add(new StringTextComponent(TextFormatting.GOLD + I18n.format("item.ctiers.sextuple_compressed_iron_block")));
			super.addInformation(stack, worldIn, tooltip, flagIn);
		}
	});
	
	//Compressed Gold Block
	public static final RegistryObject<Block> COMPRESSED_GOLD_BLOCK = BLOCKS.register("compressed_gold_block", () -> new Block(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(3.0F, 6.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> DOUBLE_COMPRESSED_GOLD_BLOCK = BLOCKS.register("double_compressed_gold_block", () -> new Block(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(6.0F, 12.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> TRIPLE_COMPRESSED_GOLD_BLOCK = BLOCKS.register("triple_compressed_gold_block", () -> new Block(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(12.0F, 24.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> QUADRUPLE_COMPRESSED_GOLD_BLOCK = BLOCKS.register("quadruple_compressed_gold_block", () -> new Block(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(24.0F, 48.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> QUINTUPLE_COMPRESSED_GOLD_BLOCK = BLOCKS.register("quintuple_compressed_gold_block", () -> new Block(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(48.0F, 96.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> SEXTUPLE_COMPRESSED_GOLD_BLOCK = BLOCKS.register("sextuple_compressed_gold_block", () -> new Block(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(96.0F, 192.0F).sound(SoundType.METAL)));
	
	public static final RegistryObject<Item> COMPRESSED_GOLD_BLOCK_ITEM = ITEMS.register("compressed_gold_block", () -> new BlockItem(COMPRESSED_GOLD_BLOCK.get(), new Item.Properties().group(ItemGroupResourcefulBees.RESOURCEFUL_BEES)){
		@OnlyIn(Dist.CLIENT)
		@Override
		public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
			tooltip.add(new StringTextComponent(TextFormatting.GOLD + I18n.format("item.ctiers.compressed_gold_block")));
			super.addInformation(stack, worldIn, tooltip, flagIn);
		}
	});
	public static final RegistryObject<Item> DOUBLE_COMPRESSED_GOLD_BLOCK_ITEM = ITEMS.register("double_compressed_gold_block", () -> new BlockItem(DOUBLE_COMPRESSED_GOLD_BLOCK.get(), new Item.Properties().group(ItemGroupResourcefulBees.RESOURCEFUL_BEES)){
		@OnlyIn(Dist.CLIENT)
		@Override
		public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
			tooltip.add(new StringTextComponent(TextFormatting.GOLD + I18n.format("item.ctiers.double_compressed_gold_block")));
			super.addInformation(stack, worldIn, tooltip, flagIn);
		}
	});
	public static final RegistryObject<Item> TRIPLE_COMPRESSED_GOLD_BLOCK_ITEM = ITEMS.register("triple_compressed_gold_block", () -> new BlockItem(TRIPLE_COMPRESSED_GOLD_BLOCK.get(), new Item.Properties().group(ItemGroupResourcefulBees.RESOURCEFUL_BEES)){
		@OnlyIn(Dist.CLIENT)
		@Override
		public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
			tooltip.add(new StringTextComponent(TextFormatting.GOLD + I18n.format("item.ctiers.triple_compressed_gold_block")));
			super.addInformation(stack, worldIn, tooltip, flagIn);
		}
	});
	public static final RegistryObject<Item> QUADRUPLE_COMPRESSED_GOLD_BLOCK_ITEM = ITEMS.register("quadruple_compressed_gold_block", () -> new BlockItem(QUADRUPLE_COMPRESSED_GOLD_BLOCK.get(), new Item.Properties().group(ItemGroupResourcefulBees.RESOURCEFUL_BEES)){
		@OnlyIn(Dist.CLIENT)
		@Override
		public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
			tooltip.add(new StringTextComponent(TextFormatting.GOLD + I18n.format("item.ctiers.quadruple_compressed_gold_block")));
			super.addInformation(stack, worldIn, tooltip, flagIn);
		}
	});
	public static final RegistryObject<Item> QUINTUPLE_COMPRESSED_GOLD_BLOCK_ITEM = ITEMS.register("quintuple_compressed_gold_block", () -> new BlockItem(QUINTUPLE_COMPRESSED_GOLD_BLOCK.get(), new Item.Properties().group(ItemGroupResourcefulBees.RESOURCEFUL_BEES)){
		@OnlyIn(Dist.CLIENT)
		@Override
		public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
			tooltip.add(new StringTextComponent(TextFormatting.GOLD + I18n.format("item.ctiers.quintuple_compressed_gold_block")));
			super.addInformation(stack, worldIn, tooltip, flagIn);
		}
	});
	public static final RegistryObject<Item> SEXTUPLE_COMPRESSED_GOLD_BLOCK_ITEM = ITEMS.register("sextuple_compressed_gold_block", () -> new BlockItem(SEXTUPLE_COMPRESSED_GOLD_BLOCK.get(), new Item.Properties().group(ItemGroupResourcefulBees.RESOURCEFUL_BEES)){
		@OnlyIn(Dist.CLIENT)
		@Override
		public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
			tooltip.add(new StringTextComponent(TextFormatting.GOLD + I18n.format("item.ctiers.sextuple_compressed_gold_block")));
			super.addInformation(stack, worldIn, tooltip, flagIn);
		}
	});
	
	//Compressed Diamond Block
	public static final RegistryObject<Block> COMPRESSED_DIAMOND_BLOCK = BLOCKS.register("compressed_diamond_block", () -> new Block(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> DOUBLE_COMPRESSED_DIAMOND_BLOCK = BLOCKS.register("double_compressed_diamond_block", () -> new Block(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(10.0F, 12.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> TRIPLE_COMPRESSED_DIAMOND_BLOCK = BLOCKS.register("triple_compressed_diamond_block", () -> new Block(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(20.0F, 24.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> QUADRUPLE_COMPRESSED_DIAMOND_BLOCK = BLOCKS.register("quadruple_compressed_diamond_block", () -> new Block(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(40.0F, 48.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> QUINTUPLE_COMPRESSED_DIAMOND_BLOCK = BLOCKS.register("quintuple_compressed_diamond_block", () -> new Block(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(80.0F, 96.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> SEXTUPLE_COMPRESSED_DIAMOND_BLOCK = BLOCKS.register("sextuple_compressed_diamond_block", () -> new Block(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(160.0F, 192.0F).sound(SoundType.METAL)));
	
	public static final RegistryObject<Item> COMPRESSED_DIAMOND_BLOCK_ITEM = ITEMS.register("compressed_diamond_block", () -> new BlockItem(COMPRESSED_DIAMOND_BLOCK.get(), new Item.Properties().group(ItemGroupResourcefulBees.RESOURCEFUL_BEES)){
		@OnlyIn(Dist.CLIENT)
		@Override
		public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
			tooltip.add(new StringTextComponent(TextFormatting.GOLD + I18n.format("item.ctiers.compressed_diamond_block")));
			super.addInformation(stack, worldIn, tooltip, flagIn);
		}
	});
	public static final RegistryObject<Item> DOUBLE_COMPRESSED_DIAMOND_BLOCK_ITEM = ITEMS.register("double_compressed_diamond_block", () -> new BlockItem(DOUBLE_COMPRESSED_DIAMOND_BLOCK.get(), new Item.Properties().group(ItemGroupResourcefulBees.RESOURCEFUL_BEES)){
		@OnlyIn(Dist.CLIENT)
		@Override
		public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
			tooltip.add(new StringTextComponent(TextFormatting.GOLD + I18n.format("item.ctiers.double_compressed_diamond_block")));
			super.addInformation(stack, worldIn, tooltip, flagIn);
		}
	});
	public static final RegistryObject<Item> TRIPLE_COMPRESSED_DIAMOND_BLOCK_ITEM = ITEMS.register("triple_compressed_diamond_block", () -> new BlockItem(TRIPLE_COMPRESSED_DIAMOND_BLOCK.get(), new Item.Properties().group(ItemGroupResourcefulBees.RESOURCEFUL_BEES)){
		@OnlyIn(Dist.CLIENT)
		@Override
		public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
			tooltip.add(new StringTextComponent(TextFormatting.GOLD + I18n.format("item.ctiers.triple_compressed_diamond_block")));
			super.addInformation(stack, worldIn, tooltip, flagIn);
		}
	});
	public static final RegistryObject<Item> QUADRUPLE_COMPRESSED_DIAMOND_BLOCK_ITEM = ITEMS.register("quadruple_compressed_diamond_block", () -> new BlockItem(QUADRUPLE_COMPRESSED_DIAMOND_BLOCK.get(), new Item.Properties().group(ItemGroupResourcefulBees.RESOURCEFUL_BEES)){
		@OnlyIn(Dist.CLIENT)
		@Override
		public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
			tooltip.add(new StringTextComponent(TextFormatting.GOLD + I18n.format("item.ctiers.quadruple_compressed_diamond_block")));
			super.addInformation(stack, worldIn, tooltip, flagIn);
		}
	});
	public static final RegistryObject<Item> QUINTUPLE_COMPRESSED_DIAMOND_BLOCK_ITEM = ITEMS.register("quintuple_compressed_diamond_block", () -> new BlockItem(QUINTUPLE_COMPRESSED_DIAMOND_BLOCK.get(), new Item.Properties().group(ItemGroupResourcefulBees.RESOURCEFUL_BEES)){
		@OnlyIn(Dist.CLIENT)
		@Override
		public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
			tooltip.add(new StringTextComponent(TextFormatting.GOLD + I18n.format("item.ctiers.quintuple_compressed_diamond_block")));
			super.addInformation(stack, worldIn, tooltip, flagIn);
		}
	});
	public static final RegistryObject<Item> SEXTUPLE_COMPRESSED_DIAMOND_BLOCK_ITEM = ITEMS.register("sextuple_compressed_diamond_block", () -> new BlockItem(SEXTUPLE_COMPRESSED_DIAMOND_BLOCK.get(), new Item.Properties().group(ItemGroupResourcefulBees.RESOURCEFUL_BEES)){
		@OnlyIn(Dist.CLIENT)
		@Override
		public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
			tooltip.add(new StringTextComponent(TextFormatting.GOLD + I18n.format("item.ctiers.sextuple_compressed_diamond_block")));
			super.addInformation(stack, worldIn, tooltip, flagIn);
		}
	});
	
	//Compressed Emerald Block
	public static final RegistryObject<Block> COMPRESSED_EMERALD_BLOCK = BLOCKS.register("compressed_emerald_block", () -> new Block(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> DOUBLE_COMPRESSED_EMERALD_BLOCK = BLOCKS.register("double_compressed_emerald_block", () -> new Block(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(10.0F, 12.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> TRIPLE_COMPRESSED_EMERALD_BLOCK = BLOCKS.register("triple_compressed_emerald_block", () -> new Block(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(20.0F, 24.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> QUADRUPLE_COMPRESSED_EMERALD_BLOCK = BLOCKS.register("quadruple_compressed_emerald_block", () -> new Block(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(40.0F, 48.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> QUINTUPLE_COMPRESSED_EMERALD_BLOCK = BLOCKS.register("quintuple_compressed_emerald_block", () -> new Block(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(80.0F, 96.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> SEXTUPLE_COMPRESSED_EMERALD_BLOCK = BLOCKS.register("sextuple_compressed_emerald_block", () -> new Block(Block.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(160.0F, 192.0F).sound(SoundType.METAL)));
	
	public static final RegistryObject<Item> COMPRESSED_EMERALD_BLOCK_ITEM = ITEMS.register("compressed_emerald_block", () -> new BlockItem(COMPRESSED_EMERALD_BLOCK.get(), new Item.Properties().group(ItemGroupResourcefulBees.RESOURCEFUL_BEES)){
		@OnlyIn(Dist.CLIENT)
		@Override
		public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
			tooltip.add(new StringTextComponent(TextFormatting.GOLD + I18n.format("item.ctiers.compressed_emerald_block")));
			super.addInformation(stack, worldIn, tooltip, flagIn);
		}
	});
	public static final RegistryObject<Item> DOUBLE_COMPRESSED_EMERALD_BLOCK_ITEM = ITEMS.register("double_compressed_emerald_block", () -> new BlockItem(DOUBLE_COMPRESSED_EMERALD_BLOCK.get(), new Item.Properties().group(ItemGroupResourcefulBees.RESOURCEFUL_BEES)){
		@OnlyIn(Dist.CLIENT)
		@Override
		public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
			tooltip.add(new StringTextComponent(TextFormatting.GOLD + I18n.format("item.ctiers.double_compressed_emerald_block")));
			super.addInformation(stack, worldIn, tooltip, flagIn);
		}
	});
	public static final RegistryObject<Item> TRIPLE_COMPRESSED_EMERALD_BLOCK_ITEM = ITEMS.register("triple_compressed_emerald_block", () -> new BlockItem(TRIPLE_COMPRESSED_EMERALD_BLOCK.get(), new Item.Properties().group(ItemGroupResourcefulBees.RESOURCEFUL_BEES)){
		@OnlyIn(Dist.CLIENT)
		@Override
		public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
			tooltip.add(new StringTextComponent(TextFormatting.GOLD + I18n.format("item.ctiers.triple_compressed_emerald_block")));
			super.addInformation(stack, worldIn, tooltip, flagIn);
		}
	});
	public static final RegistryObject<Item> QUADRUPLE_COMPRESSED_EMERALD_BLOCK_ITEM = ITEMS.register("quadruple_compressed_emerald_block", () -> new BlockItem(QUADRUPLE_COMPRESSED_EMERALD_BLOCK.get(), new Item.Properties().group(ItemGroupResourcefulBees.RESOURCEFUL_BEES)){
		@OnlyIn(Dist.CLIENT)
		@Override
		public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
			tooltip.add(new StringTextComponent(TextFormatting.GOLD + I18n.format("item.ctiers.quadruple_compressed_emerald_block")));
			super.addInformation(stack, worldIn, tooltip, flagIn);
		}
	});
	public static final RegistryObject<Item> QUINTUPLE_COMPRESSED_EMERALD_BLOCK_ITEM = ITEMS.register("quintuple_compressed_emerald_block", () -> new BlockItem(QUINTUPLE_COMPRESSED_EMERALD_BLOCK.get(), new Item.Properties().group(ItemGroupResourcefulBees.RESOURCEFUL_BEES)){
		@OnlyIn(Dist.CLIENT)
		@Override
		public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
			tooltip.add(new StringTextComponent(TextFormatting.GOLD + I18n.format("item.ctiers.quintuple_compressed_emerald_block")));
			super.addInformation(stack, worldIn, tooltip, flagIn);
		}
	});
	public static final RegistryObject<Item> SEXTUPLE_COMPRESSED_EMERALD_BLOCK_ITEM = ITEMS.register("sextuple_compressed_emerald_block", () -> new BlockItem(SEXTUPLE_COMPRESSED_EMERALD_BLOCK.get(), new Item.Properties().group(ItemGroupResourcefulBees.RESOURCEFUL_BEES)){
		@OnlyIn(Dist.CLIENT)
		@Override
		public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
			tooltip.add(new StringTextComponent(TextFormatting.GOLD + I18n.format("item.ctiers.sextuple_compressed_emerald_block")));
			super.addInformation(stack, worldIn, tooltip, flagIn);
		}
	});
}
