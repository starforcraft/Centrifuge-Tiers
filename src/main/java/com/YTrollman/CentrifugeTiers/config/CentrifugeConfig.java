package com.YTrollman.CentrifugeTiers.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class CentrifugeConfig {

    public static ForgeConfigSpec.IntValue CENTRIFUGE_TIER_3_MUTLIPLIER;
    public static ForgeConfigSpec.IntValue CENTRIFUGE_TIER_4_MUTLIPLIER;
    public static ForgeConfigSpec.IntValue CENTRIFUGE_TIER_5_MUTLIPLIER;
    public static ForgeConfigSpec.IntValue CENTRIFUGE_TIER_CREATIVE_MUTLIPLIER;
    
    public static ForgeConfigSpec.DoubleValue CENTRIFUGE_TIER_3_RECIPE_TIME;
    public static ForgeConfigSpec.DoubleValue CENTRIFUGE_TIER_4_RECIPE_TIME;
    public static ForgeConfigSpec.DoubleValue CENTRIFUGE_TIER_5_RECIPE_TIME;
    public static ForgeConfigSpec.IntValue CENTRIFUGE_TIER_CREATIVE_RECIPE_TIME;
    
    public static ForgeConfigSpec.IntValue CENTRIFUGE_TIER_3_RF_PER_BLOCK;
    public static ForgeConfigSpec.IntValue CENTRIFUGE_TIER_4_RF_PER_BLOCK;
    public static ForgeConfigSpec.IntValue CENTRIFUGE_TIER_5_RF_PER_BLOCK;
    
    public static ForgeConfigSpec.IntValue CENTRIFUGE_TIER_3_RF_CAPACITY;
    public static ForgeConfigSpec.IntValue CENTRIFUGE_TIER_4_RF_CAPACITY;
    public static ForgeConfigSpec.IntValue CENTRIFUGE_TIER_5_RF_CAPACITY;
    
    public static ForgeConfigSpec.BooleanValue CENTRIFUGE_TIER_CREATIVE_SIZE;

    public static void init(ForgeConfigSpec.Builder client) {

            client.comment("Centrifuge Tiers Options");
            
            CENTRIFUGE_TIER_3_MUTLIPLIER = client
            		.comment("\nCentrifuge Tier 3 output Multiplier")
                    .defineInRange("centrifugeTier3Mutliplier", 2, 1, 150);
            CENTRIFUGE_TIER_4_MUTLIPLIER = client
            		.comment("\nCentrifuge Tier 4 output Multiplier")
                    .defineInRange("centrifugeTier4Mutliplier", 4, 1, 150);
            CENTRIFUGE_TIER_5_MUTLIPLIER = client
            		.comment("\nCentrifuge Tier 5 output Multiplier")
                    .defineInRange("centrifugeTier5Mutliplier", 8, 1, 150);
            CENTRIFUGE_TIER_CREATIVE_MUTLIPLIER = client
            		.comment("\nCentrifuge Tier output Multiplier")
                    .defineInRange("centrifugeTierCreativeMutliplier", 100, 1, 150);
            
            CENTRIFUGE_TIER_3_RECIPE_TIME = client
            		.comment("\nCentrifuge Tier 3 Recipe Time \nThe Value Multiplies the Recipe Time")
                    .defineInRange("centrifugeTier3RecipeTime", 0.333, 0.001, 100);
            CENTRIFUGE_TIER_4_RECIPE_TIME = client
            		.comment("\nCentrifuge Tier 4 Recipe Time \nThe Value Multiplies the Recipe Time")
                    .defineInRange("centrifugeTier4RecipeTime", 0.25, 0.001, 100);
            CENTRIFUGE_TIER_5_RECIPE_TIME = client
            		.comment("\nCentrifuge Tier 5 Recipe Time \nThe Value Multiplies the Recipe Time")
                    .defineInRange("centrifugeTier5RecipeTime", 0.125, 0.001, 100);
            CENTRIFUGE_TIER_CREATIVE_RECIPE_TIME = client
            		.comment("\nCentrifuge Tier Recipe Time \nThe Value is the Tick for the Recipe Time")
                    .defineInRange("centrifugeTierCreativeRecipeTime", 1, 1, 500);

            CENTRIFUGE_TIER_3_RF_PER_BLOCK = client
            		.comment("\nCentrifuge Tier 3 RF per Block Multiplier")
                    .defineInRange("centrifugeTier3RFPerBlockMutliplier", 2, 1, 1000);
            CENTRIFUGE_TIER_4_RF_PER_BLOCK = client
            		.comment("\nCentrifuge Tier 4 RF per Block Multiplier")
                    .defineInRange("centrifugeTier4RFPerBlockMutliplier", 4, 1, 1000);
            CENTRIFUGE_TIER_5_RF_PER_BLOCK = client
            		.comment("\nCentrifuge Tier 5 RF per Block Multiplier")
                    .defineInRange("centrifugeTier5RFPerBlockMutliplier", 8, 1, 1000);
            
            CENTRIFUGE_TIER_3_RF_CAPACITY = client
            		.comment("\nCentrifuge Tier 3 RF Capacity Multiplier")
                    .defineInRange("centrifugeTier3CapacityMutliplier", 20, 1, 1500);
            CENTRIFUGE_TIER_4_RF_CAPACITY = client
            		.comment("\nCentrifuge Tier 4 RF Capacity Multiplier")
                    .defineInRange("centrifugeTier4CapacityMutliplier", 40, 1, 1500);
            CENTRIFUGE_TIER_5_RF_CAPACITY = client
            		.comment("\nCentrifuge Tier 5 RF Capacity Multiplier")
                    .defineInRange("centrifugeTier5CapacityMutliplier", 80, 1, 1500);
            
            CENTRIFUGE_TIER_CREATIVE_SIZE = client
            		.comment("\nCentrifuge Tier Creative 3x3x3 Size \nShould it be 3x3x3?")
                    .define("centrifugeTierCreativeSize", true);
    }
}
