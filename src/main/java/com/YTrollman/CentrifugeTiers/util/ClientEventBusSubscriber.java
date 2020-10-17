package com.YTrollman.CentrifugeTiers.util;

import com.YTrollman.CentrifugeTiers.CentrifugeTiers;
import com.YTrollman.CentrifugeTiers.gui.screen.CentrifugeMultiblockScreenTier2;
import com.YTrollman.CentrifugeTiers.gui.screen.CentrifugeMultiblockScreenTier3;
import com.YTrollman.CentrifugeTiers.gui.screen.CentrifugeMultiblockScreenTier4;
import com.YTrollman.CentrifugeTiers.gui.screen.CentrifugeMultiblockScreenTier5;
import com.YTrollman.CentrifugeTiers.gui.screen.CentrifugeMultiblockScreenTierCreative;
import com.YTrollman.CentrifugeTiers.registry.RegistryHandler;

import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = CentrifugeTiers.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {
	
	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
        ScreenManager.registerFactory(RegistryHandler.CENTRIFUGE_MULTIBLOCK_CONTAINER_TIER_2.get(), CentrifugeMultiblockScreenTier2::new);
        ScreenManager.registerFactory(RegistryHandler.CENTRIFUGE_MULTIBLOCK_CONTAINER_TIER_3.get(), CentrifugeMultiblockScreenTier3::new);
        ScreenManager.registerFactory(RegistryHandler.CENTRIFUGE_MULTIBLOCK_CONTAINER_TIER_4.get(), CentrifugeMultiblockScreenTier4::new);
        ScreenManager.registerFactory(RegistryHandler.CENTRIFUGE_MULTIBLOCK_CONTAINER_TIER_5.get(), CentrifugeMultiblockScreenTier5::new);
        ScreenManager.registerFactory(RegistryHandler.CENTRIFUGE_MULTIBLOCK_CONTAINER_TIER_CREATIVE.get(), CentrifugeMultiblockScreenTierCreative::new);
	}
}
