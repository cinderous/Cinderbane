package net.cinderous.cinderbane.block;

import net.cinderous.cinderbane.util.CinderbaneRegistry;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

import javax.annotation.Nullable;

public class CinderbaneTallGrass  extends DoublePlantBlock {
    public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;

    public CinderbaneTallGrass(Block.Properties properties) {
        super(Block.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT));
        this.setDefaultState(this.stateContainer.getBaseState().with(HALF, DoubleBlockHalf.LOWER));
    }
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        DoubleBlockHalf doubleblockhalf = stateIn.get(HALF);
        if (facing.getAxis() != Direction.Axis.Y || doubleblockhalf == DoubleBlockHalf.LOWER != (facing == Direction.UP) || facingState.getBlock() == this && facingState.get(HALF) != doubleblockhalf) {
            return doubleblockhalf == DoubleBlockHalf.LOWER && facing == Direction.DOWN && !stateIn.isValidPosition(worldIn, currentPos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
        } else {
            return Blocks.AIR.getDefaultState();
        }
    }

    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockPos blockpos = context.getPos();
        return blockpos.getY() < context.getWorld().getHeight() - 1 && context.getWorld().getBlockState(blockpos.up()).isReplaceable(context) ? super.getStateForPlacement(context) : null;
    }

    /**
     * Called by ItemBlocks after a block is set in the world, to allow post-place logic
     */
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        worldIn.setBlockState(pos.up(), this.getDefaultState().with(HALF, DoubleBlockHalf.UPPER), 3);
    }
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        if (state.get(HALF) != DoubleBlockHalf.UPPER) {
            return canSustainPlant(state, worldIn, pos, Direction.UP, null);
        } else {
            BlockState blockstate = worldIn.getBlockState(pos.down());
            if (state.getBlock() != this) return super.isValidPosition(state, worldIn, pos); //Forge: This function is called during world gen and placement, before this block is set, so if we are not 'here' then assume it's the pre-check.
            return blockstate.getBlock() == this && blockstate.get(HALF) == DoubleBlockHalf.LOWER;
        }

    }

    public void placeAt(IWorld worldIn, BlockPos pos, int flags) {
        worldIn.setBlockState(pos, this.getDefaultState().with(HALF, DoubleBlockHalf.LOWER), flags);
        worldIn.setBlockState(pos.up(), this.getDefaultState().with(HALF, DoubleBlockHalf.UPPER), flags);
    }

    @Override
    public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, IPlantable plantable) {
//        BlockState plant = plantable.getPlant(world, pos.offset(facing));
        BlockState blockstate = world.getBlockState(pos.down());
        if (state.getBlock() == this)
            if (blockstate == CinderbaneRegistry.CINDIRT.get().getDefaultState() || blockstate == CinderbaneRegistry.CINDIRT_GRASS_BLOCK.get().getDefaultState()) {
                return true;
            }
        return false;
    }
}