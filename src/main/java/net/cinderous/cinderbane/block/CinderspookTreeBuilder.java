package net.cinderous.cinderbane.block;

import net.cinderous.cinderbane.util.CinderbaneRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;

import javax.annotation.Nullable;

public class CinderspookTreeBuilder extends Block {
    public CinderspookTreeBuilder(Properties properties) {
        super(properties);

    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return CinderbaneRegistry.CINDERSPOOK_TREE_BUILDER_TILE_ENTITY.get().create();
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        if(worldIn.getBlockState(pos.offset(Direction.DOWN)) == CinderbaneRegistry.CINDIRT.get().getDefaultState() || worldIn.getBlockState(pos.offset(Direction.DOWN)) == CinderbaneRegistry.CINDIRT_GRASS_BLOCK.get().getDefaultState()) {
            return true;
        }
       return false;
    }


}

