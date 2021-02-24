package com.YTrollman.CentrifugeTiers.registry;

import com.YTrollman.CentrifugeTiers.CentrifugeTiers;
import com.resourcefulbees.resourcefulbees.registry.ItemGroupResourcefulBees;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, CentrifugeTiers.MOD_ID);

    public static final RegistryObject<Item> CENTRIFUGE_CONTROLLER_TIER_3_ITEM = ITEMS.register("centrifuge_controller_tier_3", () -> new BlockItem(ModBlocks.CENTRIFUGE_CONTROLLER_TIER_3.get(), new Item.Properties().group(ItemGroupResourcefulBees.RESOURCEFUL_BEES)));
    public static final RegistryObject<Item> CENTRIFUGE_CASING_TIER_3_ITEM = ITEMS.register("centrifuge_casing_tier_3", () -> new BlockItem(ModBlocks.CENTRIFUGE_CASING_TIER_3.get(), new Item.Properties().group(ItemGroupResourcefulBees.RESOURCEFUL_BEES)));

    public static final RegistryObject<Item> CENTRIFUGE_CONTROLLER_TIER_4_ITEM = ITEMS.register("centrifuge_controller_tier_4", () -> new BlockItem(ModBlocks.CENTRIFUGE_CONTROLLER_TIER_4.get(), new Item.Properties().group(ItemGroupResourcefulBees.RESOURCEFUL_BEES)));
    public static final RegistryObject<Item> CENTRIFUGE_CASING_TIER_4_ITEM = ITEMS.register("centrifuge_casing_tier_4", () -> new BlockItem(ModBlocks.CENTRIFUGE_CASING_TIER_4.get(), new Item.Properties().group(ItemGroupResourcefulBees.RESOURCEFUL_BEES)));

    public static final RegistryObject<Item> CENTRIFUGE_CONTROLLER_TIER_5_ITEM = ITEMS.register("centrifuge_controller_tier_5", () -> new BlockItem(ModBlocks.CENTRIFUGE_CONTROLLER_TIER_5.get(), new Item.Properties().group(ItemGroupResourcefulBees.RESOURCEFUL_BEES)));
    public static final RegistryObject<Item> CENTRIFUGE_CASING_TIER_5_ITEM = ITEMS.register("centrifuge_casing_tier_5", () -> new BlockItem(ModBlocks.CENTRIFUGE_CASING_TIER_5.get(), new Item.Properties().group(ItemGroupResourcefulBees.RESOURCEFUL_BEES)));

    public static final RegistryObject<Item> CENTRIFUGE_CONTROLLER_TIER_CREATIVE_ITEM = ITEMS.register("centrifuge_controller_tier_creative", () -> new BlockItem(ModBlocks.CENTRIFUGE_CONTROLLER_TIER_CREATIVE.get(), new Item.Properties().group(ItemGroupResourcefulBees.RESOURCEFUL_BEES)));
    public static final RegistryObject<Item> CENTRIFUGE_CASING_TIER_CREATIVE_ITEM = ITEMS.register("centrifuge_casing_tier_creative", () -> new BlockItem(ModBlocks.CENTRIFUGE_CASING_TIER_CREATIVE.get(), new Item.Properties().group(ItemGroupResourcefulBees.RESOURCEFUL_BEES)));
}