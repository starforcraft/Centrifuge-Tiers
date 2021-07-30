package com.YTrollman.CentrifugeTiers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.YTrollman.CentrifugeTiers.config.Config;
import com.YTrollman.CentrifugeTiers.init.ClientEventHandler;
import com.YTrollman.CentrifugeTiers.registry.RegistryHandler;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

@Mod("ctiers")
public class CentrifugeTiers
{
    public static final String MOD_ID = "ctiers";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    
    public CentrifugeTiers() {
        RegistryHandler.init();

        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.server_config);
        
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        Config.loadConfig(Config.server_config, FMLPaths.CONFIGDIR.get().resolve("ctiers-server.toml").toString());
        
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> ClientEventHandler::clientStuff);
        
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
    	
    }

    private void doClientStuff(final FMLClientSetupEvent event) 
    {
    	
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) 
    {
    	
    }
}
