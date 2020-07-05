package net.cinderous.cinderbane.block;

import net.cinderous.cinderbane.util.CinderbaneRegistry;
import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class CinderbaneGrass extends BushBlock implements IGrowable, net.minecraftforge.common.IForgeShearable {
    protected static final VoxelShape SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);

    public CinderbaneGrass(AbstractBlock.Properties properties) {
        super(properties.doesNotBlockMovement());
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    /**
     * Whether this IGrowable can grow
     */
    public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }

    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return true;
    }

    public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
        DoublePlantBlock doubleplantblock = (DoublePlantBlock)(this == Blocks.FERN ? Blocks.LARGE_FERN : Blocks.TALL_GRASS);
        if (doubleplantblock.getDefaultState().isValidPosition(worldIn, pos) && worldIn.isAirBlock(pos.up())) {
            doubleplantblock.placeAt(worldIn, pos, 2);
        }

    }

    /**
     * Get the OffsetType for this Block. Determines if the model is rendered slightly offset.
     */
    public AbstractBlock.OffsetType getOffsetType() {
        return AbstractBlock.OffsetType.XYZ;
    }


    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        BlockPos blockpos = pos.down();
        if (state.getBlock() == this) //Forge: This function is called during world gen and placement, before this block is set, so if we are not 'here' then assume it's the pre-check.
            return this.isValidGround(worldIn.getBlockState(blockpos), worldIn, blockpos);
        return this.isValidGround(worldIn.getBlockState(blockpos), worldIn, blockpos);
    }

    @Override
    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        Block block = state.getBlock();
        return (block == CinderbaneRegistry.CINDIRT.get() || block == CinderbaneRegistry.CINDIRT_GRASS_BLOCK.get());

    }

}
