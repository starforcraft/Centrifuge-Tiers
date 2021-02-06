package com.YTrollman.CentrifugeTiers.registry;

import com.YTrollman.CentrifugeTiers.CentrifugeTiers;
import com.YTrollman.CentrifugeTiers.container.CentrifugeMultiblockContainerTier3;
import com.YTrollman.CentrifugeTiers.container.CentrifugeMultiblockContainerTier4;
import com.YTrollman.CentrifugeTiers.container.CentrifugeMultiblockContainerTier5;
import com.YTrollman.CentrifugeTiers.container.CentrifugeMultiblockContainerTierCreative;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModContainers {

    public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = DeferredRegister.create(ForgeRegistries.CONTAINERS, CentrifugeTiers.MOD_ID);

    public static final RegistryObject<ContainerType<CentrifugeMultiblockContainerTier3>> CENTRIFUGE_MULTIBLOCK_CONTAINER_TIER_3 = CONTAINER_TYPES.register("centrifuge_multiblock_tier_3", () -> IForgeContainerType
            .create((id, inv, c) -> new CentrifugeMultiblockContainerTier3(id, inv.player.world, c.readBlockPos(), inv)));
    
    public static final RegistryObject<ContainerType<CentrifugeMultiblockContainerTier4>> CENTRIFUGE_MULTIBLOCK_CONTAINER_TIER_4 = CONTAINER_TYPES.register("centrifuge_multiblock_tier_4", () -> IForgeContainerType
            .create((id, inv, c) -> new CentrifugeMultiblockContainerTier4(id, inv.player.world, c.readBlockPos(), inv)));
    
    public static final RegistryObject<ContainerType<CentrifugeMultiblockContainerTier5>> CENTRIFUGE_MULTIBLOCK_CONTAINER_TIER_5 = CONTAINER_TYPES.register("centrifuge_multiblock_tier_5", () -> IForgeContainerType
            .create((id, inv, c) -> new CentrifugeMultiblockContainerTier5(id, inv.player.world, c.readBlockPos(), inv)));
    
    public static final RegistryObject<ContainerType<CentrifugeMultiblockContainerTierCreative>> CENTRIFUGE_MULTIBLOCK_CONTAINER_TIER_CREATIVE = CONTAINER_TYPES.register("centrifuge_multiblock_tier_creative", () -> IForgeContainerType
            .create((id, inv, c) -> new CentrifugeMultiblockContainerTierCreative(id, inv.player.world, c.readBlockPos(), inv)));
}
