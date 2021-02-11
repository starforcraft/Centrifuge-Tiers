package com.YTrollman.CentrifugeTiers.init;

import com.YTrollman.CentrifugeTiers.registry.ModContainers;
import com.resourcefulbees.resourcefulbees.client.gui.screen.CentrifugeMultiblockScreen;

import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class ClientEventHandler {

    public static void clientStuff() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientEventHandler::doClientStuff);
    }

    private static void doClientStuff(final FMLClientSetupEvent event) 
    {
        ScreenManager.registerFactory(ModContainers.CENTRIFUGE_MULTIBLOCK_CONTAINER_TIER_3.get(), CentrifugeMultiblockScreen::new);
        ScreenManager.registerFactory(ModContainers.CENTRIFUGE_MULTIBLOCK_CONTAINER_TIER_4.get(), CentrifugeMultiblockScreen::new);
        ScreenManager.registerFactory(ModContainers.CENTRIFUGE_MULTIBLOCK_CONTAINER_TIER_5.get(), CentrifugeMultiblockScreen::new);
        ScreenManager.registerFactory(ModContainers.CENTRIFUGE_MULTIBLOCK_CONTAINER_TIER_CREATIVE.get(), CentrifugeMultiblockScreen::new);
    }
}