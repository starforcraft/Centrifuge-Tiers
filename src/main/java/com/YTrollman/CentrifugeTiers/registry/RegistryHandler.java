package com.YTrollman.CentrifugeTiers.registry;

import com.YTrollman.CentrifugeTiers.config.CentrifugeConfig;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class RegistryHandler {

    public static void init() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItems.ITEMS.register(bus);
        ModBlocks.BLOCKS.register(bus);
        ModContainers.CONTAINER_TYPES.register(bus);
        ModTileEntityTypes.TILE_ENTITY_TYPES.register(bus);
        if (CentrifugeConfig.COMPRESSED_BLOCKS.get() == true)
        {
        	ModCompressedBlocks.BLOCKS.register(bus);
        	ModCompressedItems.ITEMS.register(bus);
        }
	}
}
