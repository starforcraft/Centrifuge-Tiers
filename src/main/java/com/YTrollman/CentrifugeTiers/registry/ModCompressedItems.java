package com.YTrollman.CentrifugeTiers.registry;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.YTrollman.CentrifugeTiers.CentrifugeTiers;
import com.resourcefulbees.resourcefulbees.registry.ItemGroupResourcefulBees;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModCompressedItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, CentrifugeTiers.MOD_ID);
	//Compressed Iron Block
	public static final RegistryObject<Item> COMPRESSED_IRON_BLOCK_ITEM = ITEMS.register("compressed_iron_block", () -> new BlockItem(ModCompressedBlocks.COMPRESSED_IRON_BLOCK.get(), new Item.Properties().tab(ItemGroupResourcefulBees.RESOURCEFUL_BEES)){
		@OnlyIn(Dist.CLIENT)
		@Override
		public void appendHoverText(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
			tooltip.add(new StringTextComponent(TextFormatting.GOLD + I18n.get("item.ctiers.compressed_iron_block")));
			super.appendHoverText(stack, worldIn, tooltip, flagIn);
		}
	});
	public static final RegistryObject<Item> DOUBLE_COMPRESSED_IRON_BLOCK_ITEM = ITEMS.register("double_compressed_iron_block", () -> new BlockItem(ModCompressedBlocks.DOUBLE_COMPRESSED_IRON_BLOCK.get(), new Item.Properties().tab(ItemGroupResourcefulBees.RESOURCEFUL_BEES)){
		@OnlyIn(Dist.CLIENT)
		@Override
		public void appendHoverText(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
			tooltip.add(new StringTextComponent(TextFormatting.GOLD + I18n.get("item.ctiers.double_compressed_iron_block")));
			super.appendHoverText(stack, worldIn, tooltip, flagIn);
		}
	});
	public static final RegistryObject<Item> TRIPLE_COMPRESSED_IRON_BLOCK_ITEM = ITEMS.register("triple_compressed_iron_block", () -> new BlockItem(ModCompressedBlocks.TRIPLE_COMPRESSED_IRON_BLOCK.get(), new Item.Properties().tab(ItemGroupResourcefulBees.RESOURCEFUL_BEES)){
		@OnlyIn(Dist.CLIENT)
		@Override
		public void appendHoverText(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
			tooltip.add(new StringTextComponent(TextFormatting.GOLD + I18n.get("item.ctiers.triple_compressed_iron_block")));
			super.appendHoverText(stack, worldIn, tooltip, flagIn);
		}
	});
	public static final RegistryObject<Item> QUADRUPLE_COMPRESSED_IRON_BLOCK_ITEM = ITEMS.register("quadruple_compressed_iron_block", () -> new BlockItem(ModCompressedBlocks.QUADRUPLE_COMPRESSED_IRON_BLOCK.get(), new Item.Properties().tab(ItemGroupResourcefulBees.RESOURCEFUL_BEES)){
		@OnlyIn(Dist.CLIENT)
		@Override
		public void appendHoverText(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
			tooltip.add(new StringTextComponent(TextFormatting.GOLD + I18n.get("item.ctiers.quadruple_compressed_iron_block")));
			super.appendHoverText(stack, worldIn, tooltip, flagIn);
		}
	});
	public static final RegistryObject<Item> QUINTUPLE_COMPRESSED_IRON_BLOCK_ITEM = ITEMS.register("quintuple_compressed_iron_block", () -> new BlockItem(ModCompressedBlocks.QUINTUPLE_COMPRESSED_IRON_BLOCK.get(), new Item.Properties().tab(ItemGroupResourcefulBees.RESOURCEFUL_BEES)){
		@OnlyIn(Dist.CLIENT)
		@Override
		public void appendHoverText(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
			tooltip.add(new StringTextComponent(TextFormatting.GOLD + I18n.get("item.ctiers.quintuple_compressed_iron_block")));
			super.appendHoverText(stack, worldIn, tooltip, flagIn);
		}
	});
	public static final RegistryObject<Item> SEXTUPLE_COMPRESSED_IRON_BLOCK_ITEM = ITEMS.register("sextuple_compressed_iron_block", () -> new BlockItem(ModCompressedBlocks.SEXTUPLE_COMPRESSED_IRON_BLOCK.get(), new Item.Properties().tab(ItemGroupResourcefulBees.RESOURCEFUL_BEES)){
		@OnlyIn(Dist.CLIENT)
		@Override
		public void appendHoverText(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
			tooltip.add(new StringTextComponent(TextFormatting.GOLD + I18n.get("item.ctiers.sextuple_compressed_iron_block")));
			super.appendHoverText(stack, worldIn, tooltip, flagIn);
		}
	});

	//Compressed Gold Block
	public static final RegistryObject<Item> COMPRESSED_GOLD_BLOCK_ITEM = ITEMS.register("compressed_gold_block", () -> new BlockItem(ModCompressedBlocks.COMPRESSED_GOLD_BLOCK.get(), new Item.Properties().tab(ItemGroupResourcefulBees.RESOURCEFUL_BEES)){
		@OnlyIn(Dist.CLIENT)
		@Override
		public void appendHoverText(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
			tooltip.add(new StringTextComponent(TextFormatting.GOLD + I18n.get("item.ctiers.compressed_gold_block")));
			super.appendHoverText(stack, worldIn, tooltip, flagIn);
		}
	});
	public static final RegistryObject<Item> DOUBLE_COMPRESSED_GOLD_BLOCK_ITEM = ITEMS.register("double_compressed_gold_block", () -> new BlockItem(ModCompressedBlocks.DOUBLE_COMPRESSED_GOLD_BLOCK.get(), new Item.Properties().tab(ItemGroupResourcefulBees.RESOURCEFUL_BEES)){
		@OnlyIn(Dist.CLIENT)
		@Override
		public void appendHoverText(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
			tooltip.add(new StringTextComponent(TextFormatting.GOLD + I18n.get("item.ctiers.double_compressed_gold_block")));
			super.appendHoverText(stack, worldIn, tooltip, flagIn);
		}
	});
	public static final RegistryObject<Item> TRIPLE_COMPRESSED_GOLD_BLOCK_ITEM = ITEMS.register("triple_compressed_gold_block", () -> new BlockItem(ModCompressedBlocks.TRIPLE_COMPRESSED_GOLD_BLOCK.get(), new Item.Properties().tab(ItemGroupResourcefulBees.RESOURCEFUL_BEES)){
		@OnlyIn(Dist.CLIENT)
		@Override
		public void appendHoverText(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
			tooltip.add(new StringTextComponent(TextFormatting.GOLD + I18n.get("item.ctiers.triple_compressed_gold_block")));
			super.appendHoverText(stack, worldIn, tooltip, flagIn);
		}
	});
	public static final RegistryObject<Item> QUADRUPLE_COMPRESSED_GOLD_BLOCK_ITEM = ITEMS.register("quadruple_compressed_gold_block", () -> new BlockItem(ModCompressedBlocks.QUADRUPLE_COMPRESSED_GOLD_BLOCK.get(), new Item.Properties().tab(ItemGroupResourcefulBees.RESOURCEFUL_BEES)){
		@OnlyIn(Dist.CLIENT)
		@Override
		public void appendHoverText(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
			tooltip.add(new StringTextComponent(TextFormatting.GOLD + I18n.get("item.ctiers.quadruple_compressed_gold_block")));
			super.appendHoverText(stack, worldIn, tooltip, flagIn);
		}
	});
	public static final RegistryObject<Item> QUINTUPLE_COMPRESSED_GOLD_BLOCK_ITEM = ITEMS.register("quintuple_compressed_gold_block", () -> new BlockItem(ModCompressedBlocks.QUINTUPLE_COMPRESSED_GOLD_BLOCK.get(), new Item.Properties().tab(ItemGroupResourcefulBees.RESOURCEFUL_BEES)){
		@OnlyIn(Dist.CLIENT)
		@Override
		public void appendHoverText(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
			tooltip.add(new StringTextComponent(TextFormatting.GOLD + I18n.get("item.ctiers.quintuple_compressed_gold_block")));
			super.appendHoverText(stack, worldIn, tooltip, flagIn);
		}
	});
	public static final RegistryObject<Item> SEXTUPLE_COMPRESSED_GOLD_BLOCK_ITEM = ITEMS.register("sextuple_compressed_gold_block", () -> new BlockItem(ModCompressedBlocks.SEXTUPLE_COMPRESSED_GOLD_BLOCK.get(), new Item.Properties().tab(ItemGroupResourcefulBees.RESOURCEFUL_BEES)){
		@OnlyIn(Dist.CLIENT)
		@Override
		public void appendHoverText(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
			tooltip.add(new StringTextComponent(TextFormatting.GOLD + I18n.get("item.ctiers.sextuple_compressed_gold_block")));
			super.appendHoverText(stack, worldIn, tooltip, flagIn);
		}
	});

	//Compressed Diamond Block
	public static final RegistryObject<Item> COMPRESSED_DIAMOND_BLOCK_ITEM = ITEMS.register("compressed_diamond_block", () -> new BlockItem(ModCompressedBlocks.COMPRESSED_DIAMOND_BLOCK.get(), new Item.Properties().tab(ItemGroupResourcefulBees.RESOURCEFUL_BEES)){
		@OnlyIn(Dist.CLIENT)
		@Override
		public void appendHoverText(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
			tooltip.add(new StringTextComponent(TextFormatting.GOLD + I18n.get("item.ctiers.compressed_diamond_block")));
			super.appendHoverText(stack, worldIn, tooltip, flagIn);
		}
	});
	public static final RegistryObject<Item> DOUBLE_COMPRESSED_DIAMOND_BLOCK_ITEM = ITEMS.register("double_compressed_diamond_block", () -> new BlockItem(ModCompressedBlocks.DOUBLE_COMPRESSED_DIAMOND_BLOCK.get(), new Item.Properties().tab(ItemGroupResourcefulBees.RESOURCEFUL_BEES)){
		@OnlyIn(Dist.CLIENT)
		@Override
		public void appendHoverText(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
			tooltip.add(new StringTextComponent(TextFormatting.GOLD + I18n.get("item.ctiers.double_compressed_diamond_block")));
			super.appendHoverText(stack, worldIn, tooltip, flagIn);
		}
	});
	public static final RegistryObject<Item> TRIPLE_COMPRESSED_DIAMOND_BLOCK_ITEM = ITEMS.register("triple_compressed_diamond_block", () -> new BlockItem(ModCompressedBlocks.TRIPLE_COMPRESSED_DIAMOND_BLOCK.get(), new Item.Properties().tab(ItemGroupResourcefulBees.RESOURCEFUL_BEES)){
		@OnlyIn(Dist.CLIENT)
		@Override
		public void appendHoverText(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
			tooltip.add(new StringTextComponent(TextFormatting.GOLD + I18n.get("item.ctiers.triple_compressed_diamond_block")));
			super.appendHoverText(stack, worldIn, tooltip, flagIn);
		}
	});
	public static final RegistryObject<Item> QUADRUPLE_COMPRESSED_DIAMOND_BLOCK_ITEM = ITEMS.register("quadruple_compressed_diamond_block", () -> new BlockItem(ModCompressedBlocks.QUADRUPLE_COMPRESSED_DIAMOND_BLOCK.get(), new Item.Properties().tab(ItemGroupResourcefulBees.RESOURCEFUL_BEES)){
		@OnlyIn(Dist.CLIENT)
		@Override
		public void appendHoverText(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
			tooltip.add(new StringTextComponent(TextFormatting.GOLD + I18n.get("item.ctiers.quadruple_compressed_diamond_block")));
			super.appendHoverText(stack, worldIn, tooltip, flagIn);
		}
	});
	public static final RegistryObject<Item> QUINTUPLE_COMPRESSED_DIAMOND_BLOCK_ITEM = ITEMS.register("quintuple_compressed_diamond_block", () -> new BlockItem(ModCompressedBlocks.QUINTUPLE_COMPRESSED_DIAMOND_BLOCK.get(), new Item.Properties().tab(ItemGroupResourcefulBees.RESOURCEFUL_BEES)){
		@OnlyIn(Dist.CLIENT)
		@Override
		public void appendHoverText(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
			tooltip.add(new StringTextComponent(TextFormatting.GOLD + I18n.get("item.ctiers.quintuple_compressed_diamond_block")));
			super.appendHoverText(stack, worldIn, tooltip, flagIn);
		}
	});
	public static final RegistryObject<Item> SEXTUPLE_COMPRESSED_DIAMOND_BLOCK_ITEM = ITEMS.register("sextuple_compressed_diamond_block", () -> new BlockItem(ModCompressedBlocks.SEXTUPLE_COMPRESSED_DIAMOND_BLOCK.get(), new Item.Properties().tab(ItemGroupResourcefulBees.RESOURCEFUL_BEES)){
		@OnlyIn(Dist.CLIENT)
		@Override
		public void appendHoverText(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
			tooltip.add(new StringTextComponent(TextFormatting.GOLD + I18n.get("item.ctiers.sextuple_compressed_diamond_block")));
			super.appendHoverText(stack, worldIn, tooltip, flagIn);
		}
	});

	//Compressed Emerald Block
	public static final RegistryObject<Item> COMPRESSED_EMERALD_BLOCK_ITEM = ITEMS.register("compressed_emerald_block", () -> new BlockItem(ModCompressedBlocks.COMPRESSED_EMERALD_BLOCK.get(), new Item.Properties().tab(ItemGroupResourcefulBees.RESOURCEFUL_BEES)){
		@OnlyIn(Dist.CLIENT)
		@Override
		public void appendHoverText(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
			tooltip.add(new StringTextComponent(TextFormatting.GOLD + I18n.get("item.ctiers.compressed_emerald_block")));
			super.appendHoverText(stack, worldIn, tooltip, flagIn);
		}
	});
	public static final RegistryObject<Item> DOUBLE_COMPRESSED_EMERALD_BLOCK_ITEM = ITEMS.register("double_compressed_emerald_block", () -> new BlockItem(ModCompressedBlocks.DOUBLE_COMPRESSED_EMERALD_BLOCK.get(), new Item.Properties().tab(ItemGroupResourcefulBees.RESOURCEFUL_BEES)){
		@OnlyIn(Dist.CLIENT)
		@Override
		public void appendHoverText(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
			tooltip.add(new StringTextComponent(TextFormatting.GOLD + I18n.get("item.ctiers.double_compressed_emerald_block")));
			super.appendHoverText(stack, worldIn, tooltip, flagIn);
		}
	});
	public static final RegistryObject<Item> TRIPLE_COMPRESSED_EMERALD_BLOCK_ITEM = ITEMS.register("triple_compressed_emerald_block", () -> new BlockItem(ModCompressedBlocks.TRIPLE_COMPRESSED_EMERALD_BLOCK.get(), new Item.Properties().tab(ItemGroupResourcefulBees.RESOURCEFUL_BEES)){
		@OnlyIn(Dist.CLIENT)
		@Override
		public void appendHoverText(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
			tooltip.add(new StringTextComponent(TextFormatting.GOLD + I18n.get("item.ctiers.triple_compressed_emerald_block")));
			super.appendHoverText(stack, worldIn, tooltip, flagIn);
		}
	});
	public static final RegistryObject<Item> QUADRUPLE_COMPRESSED_EMERALD_BLOCK_ITEM = ITEMS.register("quadruple_compressed_emerald_block", () -> new BlockItem(ModCompressedBlocks.QUADRUPLE_COMPRESSED_EMERALD_BLOCK.get(), new Item.Properties().tab(ItemGroupResourcefulBees.RESOURCEFUL_BEES)){
		@OnlyIn(Dist.CLIENT)
		@Override
		public void appendHoverText(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
			tooltip.add(new StringTextComponent(TextFormatting.GOLD + I18n.get("item.ctiers.quadruple_compressed_emerald_block")));
			super.appendHoverText(stack, worldIn, tooltip, flagIn);
		}
	});
	public static final RegistryObject<Item> QUINTUPLE_COMPRESSED_EMERALD_BLOCK_ITEM = ITEMS.register("quintuple_compressed_emerald_block", () -> new BlockItem(ModCompressedBlocks.QUINTUPLE_COMPRESSED_EMERALD_BLOCK.get(), new Item.Properties().tab(ItemGroupResourcefulBees.RESOURCEFUL_BEES)){
		@OnlyIn(Dist.CLIENT)
		@Override
		public void appendHoverText(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
			tooltip.add(new StringTextComponent(TextFormatting.GOLD + I18n.get("item.ctiers.quintuple_compressed_emerald_block")));
			super.appendHoverText(stack, worldIn, tooltip, flagIn);
		}
	});
	public static final RegistryObject<Item> SEXTUPLE_COMPRESSED_EMERALD_BLOCK_ITEM = ITEMS.register("sextuple_compressed_emerald_block", () -> new BlockItem(ModCompressedBlocks.SEXTUPLE_COMPRESSED_EMERALD_BLOCK.get(), new Item.Properties().tab(ItemGroupResourcefulBees.RESOURCEFUL_BEES)){
		@OnlyIn(Dist.CLIENT)
		@Override
		public void appendHoverText(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
			tooltip.add(new StringTextComponent(TextFormatting.GOLD + I18n.get("item.ctiers.sextuple_compressed_emerald_block")));
			super.appendHoverText(stack, worldIn, tooltip, flagIn);
		}
	});
}