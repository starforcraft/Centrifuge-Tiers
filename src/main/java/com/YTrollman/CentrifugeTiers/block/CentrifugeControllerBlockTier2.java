package com.YTrollman.CentrifugeTiers.block;

import com.YTrollman.CentrifugeTiers.tileentity.CentrifugeControllerTileEntityTier2;
import com.resourcefulbees.resourcefulbees.utils.TooltipBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
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
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.IntStream;

public class CentrifugeControllerBlockTier2 extends Block {
    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
    public static final BooleanProperty PROPERTY_VALID = BooleanProperty.create("valid");

    public CentrifugeControllerBlockTier2(Properties properties) {
        super(properties);
        setDefaultState(getDefaultState().with(PROPERTY_VALID,false).with(FACING, Direction.NORTH));
    }

    @Nonnull
    @Override
    public ActionResultType onBlockActivated(@Nonnull BlockState state, World world, @Nonnull BlockPos pos, @Nonnull PlayerEntity player, @Nonnull Hand hand, @Nonnull BlockRayTraceResult blockRayTraceResult) {
        if (!world.isRemote) {
            INamedContainerProvider blockEntity = state.getContainer(world,pos);
            if (blockEntity != null) {
                if (blockEntity instanceof CentrifugeControllerTileEntityTier2){
                    CentrifugeControllerTileEntityTier2 multiblockCentrifuge =  (CentrifugeControllerTileEntityTier2)blockEntity;
                    multiblockCentrifuge.validStructure = multiblockCentrifuge.validateStructure(world, (ServerPlayerEntity) player);
                    if (multiblockCentrifuge.validStructure){
                        NetworkHooks.openGui((ServerPlayerEntity) player, blockEntity, pos);
                    }
                }
            }
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public boolean removedByPlayer(BlockState state, World world, BlockPos pos, PlayerEntity player, boolean willHarvest, FluidState fluid) {
        if (!world.isRemote){
            CentrifugeControllerTileEntityTier2 controller = (CentrifugeControllerTileEntityTier2)world.getTileEntity(pos);
            if (controller !=null){
                controller.setCasingsToNotLinked(controller.buildStructureBounds());
                return super.removedByPlayer(state,world,pos,player,willHarvest,fluid);
            }
        }
        return super.removedByPlayer(state,world,pos,player,willHarvest,fluid);
    }

    @Override
    public void onBlockExploded(BlockState state, World world, BlockPos pos, Explosion explosion) {
        if (!world.isRemote){
            CentrifugeControllerTileEntityTier2 controller = (CentrifugeControllerTileEntityTier2)world.getTileEntity(pos);
            if (controller !=null){
                controller.setCasingsToNotLinked(controller.buildStructureBounds());
                super.onBlockExploded(state,world,pos,explosion);
            }
        } else {
            super.onBlockExploded(state,world,pos,explosion);
        }
    }

    @Nullable
    @Override
    public INamedContainerProvider getContainer(@Nonnull BlockState state, World worldIn, @Nonnull BlockPos pos) {
        return (INamedContainerProvider)worldIn.getTileEntity(pos);
    }

    @SuppressWarnings("deprecation")
	@Override
    public void onReplaced(@Nonnull BlockState state1, World world, @Nonnull BlockPos pos, @Nonnull BlockState state, boolean isMoving) {
        TileEntity blockEntity = world.getTileEntity(pos);
        if (blockEntity instanceof CentrifugeControllerTileEntityTier2 && state.getBlock() != state1.getBlock()){
            CentrifugeControllerTileEntityTier2 centrifugeTileEntity = (CentrifugeControllerTileEntityTier2)blockEntity;
            ItemStackHandler h = centrifugeTileEntity.h;
            IntStream.range(0, h.getSlots()).mapToObj(h::getStackInSlot).filter(s -> !s.isEmpty()).forEach(stack -> InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), stack));
        }
        super.onReplaced(state1, world, pos, state, isMoving);
    }



    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new CentrifugeControllerTileEntityTier2();
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) { return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite()); }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) { builder.add(PROPERTY_VALID, FACING); }

    @Override
    public void addInformation(@Nonnull ItemStack stack, @Nullable IBlockReader worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
        tooltip.addAll(new TooltipBuilder()
                .addTip(I18n.format("block.resourcefulbees.centrifuge.tooltip.info"), TextFormatting.GOLD)
                .build());
        if (Screen.hasControlDown()){
            tooltip.addAll(new TooltipBuilder()
                    .addTip(I18n.format("block.resourcefulbees.centrifuge.tooltip.structure_size"), TextFormatting.AQUA)
                    .addTip(I18n.format("block.resourcefulbees.centrifuge.tooltip.requisites"), TextFormatting.AQUA)
                    .addTip(I18n.format("block.resourcefulbees.centrifuge.tooltip.capabilities"), TextFormatting.AQUA)
                    .addTip(I18n.format("block.ctiers.centrifuge_tier_2.tooltip.faster"), TextFormatting.AQUA)
                    .addTip(I18n.format("block.ctiers.centrifuge_tier_2.tooltip.multi"), TextFormatting.AQUA)
                    .addTip(I18n.format("block.ctiers.centrifuge_tier_2.tooltip.rfperblock"), TextFormatting.AQUA)
                    .addTip(I18n.format("block.ctiers.centrifuges.tooltip.warning"), TextFormatting.AQUA)
                    .build());
        }
        else
        {
            tooltip.add(new StringTextComponent(TextFormatting.AQUA + I18n.format("resourcefulbees.ctrl_info")));
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}



