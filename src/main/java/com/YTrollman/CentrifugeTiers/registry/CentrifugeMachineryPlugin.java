package com.YTrollman.CentrifugeTiers.registry;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

import com.YTrollman.CentrifugeTiers.CentrifugeTiers;
import com.resourcefulbees.resourcefulbees.compat.jei.CentrifugeRecipeCategory;

@JeiPlugin
public class CentrifugeMachineryPlugin implements IModPlugin {
    @Nonnull
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(CentrifugeTiers.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.CENTRIFUGE_CONTROLLER_TIER_3.get()), CentrifugeRecipeCategory.ID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.CENTRIFUGE_CONTROLLER_TIER_4.get()), CentrifugeRecipeCategory.ID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.CENTRIFUGE_CONTROLLER_TIER_5.get()), CentrifugeRecipeCategory.ID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.CENTRIFUGE_CONTROLLER_TIER_CREATIVE.get()), CentrifugeRecipeCategory.ID);
    }
}