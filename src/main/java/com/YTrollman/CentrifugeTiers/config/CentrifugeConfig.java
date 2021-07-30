package com.YTrollman.CentrifugeTiers.config;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.List;

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
    
    public static ForgeConfigSpec.IntValue CENTRIFUGE_TIER_3_MAX_TANK_CAPACITY;
    public static ForgeConfigSpec.IntValue CENTRIFUGE_TIER_4_MAX_TANK_CAPACITY;
    public static ForgeConfigSpec.IntValue CENTRIFUGE_TIER_5_MAX_TANK_CAPACITY;
    public static ForgeConfigSpec.IntValue CENTRIFUGE_TIER_CREATIVE_MAX_TANK_CAPACITY;
    
    public static ForgeConfigSpec.IntValue CENTRIFUGE_TIER_3_ITEM_MAX_STACK_SIZE;
    public static ForgeConfigSpec.IntValue CENTRIFUGE_TIER_4_ITEM_MAX_STACK_SIZE;
    public static ForgeConfigSpec.IntValue CENTRIFUGE_TIER_5_ITEM_MAX_STACK_SIZE;
    public static ForgeConfigSpec.IntValue CENTRIFUGE_TIER_CREATIVE_ITEM_MAX_STACK_SIZE;
    
    public static ForgeConfigSpec.BooleanValue CENTRIFUGE_TIER_CREATIVE_SIZE;
    
    public static void init(ForgeConfigSpec.Builder server) {

            server.comment("Centrifuge Tiers Options");
            
            CENTRIFUGE_TIER_3_MUTLIPLIER = server
            		.comment("\nCentrifuge Tier 3 output Multiplier")
                    .defineInRange("centrifugeTier3Mutliplier", 2, 1, Integer.MAX_VALUE);
            CENTRIFUGE_TIER_4_MUTLIPLIER = server
            		.comment("\nCentrifuge Tier 4 output Multiplier")
                    .defineInRange("centrifugeTier4Mutliplier", 4, 1, Integer.MAX_VALUE);
            CENTRIFUGE_TIER_5_MUTLIPLIER = server
            		.comment("\nCentrifuge Tier 5 output Multiplier")
                    .defineInRange("centrifugeTier5Mutliplier", 8, 1, Integer.MAX_VALUE);
            CENTRIFUGE_TIER_CREATIVE_MUTLIPLIER = server
            		.comment("\nCentrifuge Tier Creative output Multiplier")
                    .defineInRange("centrifugeTierCreativeMutliplier", 100, 1, Integer.MAX_VALUE);
            
            CENTRIFUGE_TIER_3_RECIPE_TIME = server
            		.comment("\nCentrifuge Tier 3 Recipe Time \nThe Value Multiplies the Recipe Time")
                    .defineInRange("centrifugeTier3RecipeTime", 0.333, 0.001, 100);
            CENTRIFUGE_TIER_4_RECIPE_TIME = server
            		.comment("\nCentrifuge Tier 4 Recipe Time \nThe Value Multiplies the Recipe Time")
                    .defineInRange("centrifugeTier4RecipeTime", 0.25, 0.001, 100);
            CENTRIFUGE_TIER_5_RECIPE_TIME = server
            		.comment("\nCentrifuge Tier 5 Recipe Time \nThe Value Multiplies the Recipe Time")
                    .defineInRange("centrifugeTier5RecipeTime", 0.125, 0.001, 100);
            CENTRIFUGE_TIER_CREATIVE_RECIPE_TIME = server
            		.comment("\nCentrifuge Tier Recipe Time \nThe Value is the Tick for the Recipe Time \nIf you set the Value to 0 the Game will Crash :)")
                    .defineInRange("centrifugeTierCreativeRecipeTime", -1, -1, 500);

            CENTRIFUGE_TIER_3_RF_PER_BLOCK = server
            		.comment("\nCentrifuge Tier 3 RF per Block Multiplier")
                    .defineInRange("centrifugeTier3RFPerBlockMutliplier", 2, 1, Integer.MAX_VALUE);
            CENTRIFUGE_TIER_4_RF_PER_BLOCK = server
            		.comment("\nCentrifuge Tier 4 RF per Block Multiplier")
                    .defineInRange("centrifugeTier4RFPerBlockMutliplier", 4, 1, Integer.MAX_VALUE);
            CENTRIFUGE_TIER_5_RF_PER_BLOCK = server
            		.comment("\nCentrifuge Tier 5 RF per Block Multiplier")
                    .defineInRange("centrifugeTier5RFPerBlockMutliplier", 8, 1, Integer.MAX_VALUE);
            
            CENTRIFUGE_TIER_3_RF_CAPACITY = server
            		.comment("\nCentrifuge Tier 3 RF Capacity Multiplier")
                    .defineInRange("centrifugeTier3CapacityMutliplier", 20, 1, Integer.MAX_VALUE);
            CENTRIFUGE_TIER_4_RF_CAPACITY = server
            		.comment("\nCentrifuge Tier 4 RF Capacity Multiplier")
                    .defineInRange("centrifugeTier4CapacityMutliplier", 40, 1, Integer.MAX_VALUE);
            CENTRIFUGE_TIER_5_RF_CAPACITY = server
            		.comment("\nCentrifuge Tier 5 RF Capacity Multiplier")
                    .defineInRange("centrifugeTier5CapacityMutliplier", 80, 1, Integer.MAX_VALUE);
            
            CENTRIFUGE_TIER_3_MAX_TANK_CAPACITY = server
            		.comment("\nCentrifuge Tier 3 Max Tank Capacity")
                    .defineInRange("centrifugeTier3MaxTankCapacity", 100000, 1, Integer.MAX_VALUE);
            CENTRIFUGE_TIER_4_MAX_TANK_CAPACITY = server
            		.comment("\nCentrifuge Tier 4 Max Tank Capacity")
                    .defineInRange("centrifugeTier4MaxTankCapacity", 200000, 1, Integer.MAX_VALUE);
            CENTRIFUGE_TIER_5_MAX_TANK_CAPACITY = server
            		.comment("\nCentrifuge Tier 5 Max Tank Capacity")
                    .defineInRange("centrifugeTier5MaxTankCapacity", 400000, 1, Integer.MAX_VALUE);
            CENTRIFUGE_TIER_CREATIVE_MAX_TANK_CAPACITY = server
            		.comment("\nCentrifuge Tier Creative Max Tank Capacity")
                    .defineInRange("centrifugeTierCreativeMaxTankCapacity", 5000000, 1, Integer.MAX_VALUE);
            
            CENTRIFUGE_TIER_3_ITEM_MAX_STACK_SIZE = server
            		.comment("\nCentrifuge Tier 3 Item Max Stack Size")
                    .defineInRange("centrifugeTier3ItemMaxStackSize", 256, 1, Integer.MAX_VALUE);
            CENTRIFUGE_TIER_4_ITEM_MAX_STACK_SIZE = server
            		.comment("\nCentrifuge Tier 4 Item Max Stack Size")
                    .defineInRange("centrifugeTier4ItemMaxStackSize", 512, 1, Integer.MAX_VALUE);
            CENTRIFUGE_TIER_5_ITEM_MAX_STACK_SIZE = server
            		.comment("\nCentrifuge Tier 5 Item Max Stack Size")
                    .defineInRange("centrifugeTier5ItemMaxStackSize", 1024, 1, Integer.MAX_VALUE);
            CENTRIFUGE_TIER_CREATIVE_ITEM_MAX_STACK_SIZE = server
            		.comment("\nCentrifuge Tier Creative Item Max Stack Size")
                    .defineInRange("centrifugeTierCreativeItemMaxStackSize", 16384, 1, Integer.MAX_VALUE);

            CENTRIFUGE_TIER_CREATIVE_SIZE = server
                    .comment("\nCentrifuge Tier Creative 3x3x3 Size \nShould it be 3x3x3?")
                    .define("centrifugeTierCreativeSize", true);
    }
}
