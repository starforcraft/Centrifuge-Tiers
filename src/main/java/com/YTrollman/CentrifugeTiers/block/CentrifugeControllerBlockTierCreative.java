package com.YTrollman.CentrifugeTiers.block;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.YTrollman.CentrifugeTiers.config.CentrifugeConfig;
import com.YTrollman.CentrifugeTiers.registry.ModTileEntityTypes;
import com.YTrollman.CentrifugeTiers.tileentity.CentrifugeControllerTileEntityTierCreative;
import com.resourcefulbees.resourcefulbees.tileentity.CentrifugeTileEntity;
import com.resourcefulbees.resourcefulbees.tileentity.multiblocks.centrifuge.CentrifugeControllerTileEntity;
import com.resourcefulbees.resourcefulbees.utils.TooltipBuilder;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fml.network.NetworkHooks;

public class CentrifugeControllerBlockTierCreative extends Block {
	private int number = CentrifugeConfig.CENTRIFUGE_TIER_CREATIVE_RECIPE_TIME.get();
	private int number2 = CentrifugeConfig.CENTRIFUGE_TIER_CREATIVE_MUTLIPLIER.get();
	private int number3 = CentrifugeConfig.CENTRIFUGE_TIER_CREATIVE_ITEM_MAX_STACK_SIZE.get();
	private int number4 = CentrifugeConfig.CENTRIFUGE_TIER_CREATIVE_MAX_TANK_CAPACITY.get();
	
    public static final DirectionProperty FACING = HorizontalBlock.FACING;
    public static final BooleanProperty PROPERTY_VALID = BooleanProperty.create("valid");
    
    public CentrifugeControllerBlockTierCreative(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(PROPERTY_VALID,false).setValue(FACING, Direction.NORTH));
    }

    protected CentrifugeControllerTileEntityTierCreative getControllerEntity(World world, BlockPos pos) {
        TileEntity tileEntity = world.getBlockEntity(pos);
        if (tileEntity instanceof CentrifugeControllerTileEntity) {
            return (CentrifugeControllerTileEntityTierCreative) tileEntity;
        }
        return null;
    }
    
    @Nonnull
    @Override
    public ActionResultType use(@Nonnull BlockState state, World world, @Nonnull BlockPos pos, @Nonnull PlayerEntity player, @Nonnull Hand hand, @Nonnull BlockRayTraceResult rayTraceResult) {
        if (!world.isClientSide) {
            ItemStack heldItem = player.getItemInHand(hand);
            boolean usingBucket = heldItem.getItem() instanceof BucketItem;
            CentrifugeControllerTileEntity controller = getControllerEntity(world, pos);

            if (controller != null && controller.isValidStructure()) {
                if (usingBucket) {
                    controller.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
                            .ifPresent(iFluidHandler -> FluidUtil.interactWithFluidHandler(player, hand, world, pos, null));
                } else if (!player.isShiftKeyDown()) {
                    NetworkHooks.openGui((ServerPlayerEntity) player, controller, pos);
                }
            }
            else
            {
                return ActionResultType.PASS;
            }
        }

        return ActionResultType.SUCCESS;
    }
    
    @Override
    public void neighborChanged(@Nonnull BlockState state, World world, @Nonnull BlockPos pos, @Nonnull Block changedBlock, @Nonnull BlockPos changedBlockPos, boolean bool) {
        TileEntity tileEntity = world.getBlockEntity(pos);
        if (tileEntity instanceof CentrifugeTileEntity) {
            CentrifugeTileEntity centrifugeTileEntity = (CentrifugeTileEntity) tileEntity;
            centrifugeTileEntity.setIsPoweredByRedstone(world.hasNeighborSignal(pos));
        }
    }
    
    @Nullable
    @Override
    public INamedContainerProvider getMenuProvider(@Nonnull BlockState state, @Nonnull World worldIn, @Nonnull BlockPos pos) {
        return getControllerEntity(worldIn, pos);
    }
    
    @Override
    public boolean hasTileEntity(BlockState state) { return true; }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) { return new CentrifugeControllerTileEntityTierCreative(ModTileEntityTypes.CENTRIFUGE_CONTROLLER_ENTITY_TIER_CREATIVE.get()); }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) { return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()); }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) { builder.add(PROPERTY_VALID, FACING); }
    
    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(@Nonnull ItemStack stack, @Nullable IBlockReader worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
        tooltip.addAll(new TooltipBuilder()
                .addTip(I18n.get("block.resourcefulbees.centrifuge.tooltip.info"), TextFormatting.GOLD)
                .build());
        if (Screen.hasControlDown()){
    		if (CentrifugeConfig.CENTRIFUGE_TIER_CREATIVE_SIZE.get() == true)
    		{
                tooltip.addAll(new TooltipBuilder()
                        .addTip(I18n.get("block.ctiers.centrifuge_tier_creative.tooltip.structure_size"), TextFormatting.AQUA)
                        .addTip(I18n.get("block.resourcefulbees.centrifuge.tooltip.requisites"), TextFormatting.AQUA)
                        .addTip(I18n.get("block.resourcefulbees.centrifuge.tooltip.capabilities"), TextFormatting.AQUA)
                        .build());	
    		}
    		else if (CentrifugeConfig.CENTRIFUGE_TIER_CREATIVE_SIZE.get() == false) {
                tooltip.addAll(new TooltipBuilder()
                        .addTip(I18n.get("block.resourcefulbees.centrifuge.tooltip.structure_size"), TextFormatting.AQUA)
                        .addTip(I18n.get("block.resourcefulbees.centrifuge.tooltip.requisites"), TextFormatting.AQUA)
                        .addTip(I18n.get("block.resourcefulbees.centrifuge.tooltip.capabilities"), TextFormatting.AQUA)
                        .build());	
    		}
        }
        else if (Screen.hasShiftDown()){
            tooltip.addAll(new TooltipBuilder()
                    .addTip(I18n.get("block.ctiers.centrifuge_tier_creative.tooltip.faster") + number + I18n.get("block.ctiers.centrifuge_tier_creative.tooltip.faster2"), TextFormatting.YELLOW)
                    .addTip(I18n.get("block.ctiers.centrifuge_tier_creative.tooltip.multi") + number2, TextFormatting.YELLOW)
                    .addTip(I18n.get("block.ctiers.centrifuge_tier_creative.tooltip.ItemMaxStackSize") + number3, TextFormatting.YELLOW)
                    .addTip(I18n.get("block.ctiers.centrifuge_tier_creative.tooltip.MaxTankCapacity") + number4, TextFormatting.YELLOW)
                    .build());
        }
        else {
            tooltip.add(new StringTextComponent(TextFormatting.YELLOW + I18n.get("ctiers.left_shift_info")));
            tooltip.add(new StringTextComponent(TextFormatting.AQUA + I18n.get("resourcefulbees.ctrl_info")));
        }
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
    }
} 



