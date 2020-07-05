package net.cinderous.cinderbane.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.trees.Tree;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Random;

public abstract class AbstractBigTree extends AbstractTree {
    public AbstractBigTree(Codec p_i231953_1_) {
        super(p_i231953_1_);
    }

    @Nullable
    @Override
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean p_225546_2_) {
        return null;
    }

    public boolean func_230339_a_(ServerWorld p_230339_1_, ChunkGenerator p_230339_2_, BlockPos p_230339_3_, BlockState p_230339_4_, Random p_230339_5_) {
        for(int i = 0; i >= -1; --i) {
            for(int j = 0; j >= -1; --j) {
                if (canBigTreeSpawnAt(p_230339_4_, p_230339_1_, p_230339_3_, i, j)) {
                    return this.func_235678_a_(p_230339_1_, p_230339_2_, p_230339_3_, p_230339_4_, p_230339_5_, i, j);
                }
            }
        }

        return super.func_230339_a_(p_230339_1_, p_230339_2_, p_230339_3_, p_230339_4_, p_230339_5_);
    }

    /**
     * Get a {@link net.minecraft.world.gen.feature.ConfiguredFeature} of the huge variant of this tree
     */
    @Nullable
    protected abstract ConfiguredFeature<BaseTreeFeatureConfig, ?> getHugeTreeFeature(Random p_225547_1_);

    public boolean func_235678_a_(ServerWorld p_235678_1_, ChunkGenerator p_235678_2_, BlockPos p_235678_3_, BlockState p_235678_4_, Random p_235678_5_, int p_235678_6_, int p_235678_7_) {
        ConfiguredFeature<BaseTreeFeatureConfig, ?> configuredfeature = this.getHugeTreeFeature(p_235678_5_);
        if (configuredfeature == null) {
            return false;
        } else {
            configuredfeature.config.forcePlacement();
            BlockState blockstate = Blocks.AIR.getDefaultState();
            p_235678_1_.setBlockState(p_235678_3_.add(p_235678_6_, 0, p_235678_7_), blockstate, 4);
            p_235678_1_.setBlockState(p_235678_3_.add(p_235678_6_ + 1, 0, p_235678_7_), blockstate, 4);
            p_235678_1_.setBlockState(p_235678_3_.add(p_235678_6_, 0, p_235678_7_ + 1), blockstate, 4);
            p_235678_1_.setBlockState(p_235678_3_.add(p_235678_6_ + 1, 0, p_235678_7_ + 1), blockstate, 4);
            if (configuredfeature.func_236265_a_(p_235678_1_, p_235678_1_.func_241112_a_(), p_235678_2_, p_235678_5_, p_235678_3_.add(p_235678_6_, 0, p_235678_7_))) {
                return true;
            } else {
                p_235678_1_.setBlockState(p_235678_3_.add(p_235678_6_, 0, p_235678_7_), p_235678_4_, 4);
                p_235678_1_.setBlockState(p_235678_3_.add(p_235678_6_ + 1, 0, p_235678_7_), p_235678_4_, 4);
                p_235678_1_.setBlockState(p_235678_3_.add(p_235678_6_, 0, p_235678_7_ + 1), p_235678_4_, 4);
                p_235678_1_.setBlockState(p_235678_3_.add(p_235678_6_ + 1, 0, p_235678_7_ + 1), p_235678_4_, 4);
                return false;
            }
        }
    }

    public static boolean canBigTreeSpawnAt(BlockState blockUnder, IBlockReader worldIn, BlockPos pos, int xOffset, int zOffset) {
        Block block = blockUnder.getBlock();
        return block == worldIn.getBlockState(pos.add(xOffset, 0, zOffset)).getBlock() && block == worldIn.getBlockState(pos.add(xOffset + 1, 0, zOffset)).getBlock() && block == worldIn.getBlockState(pos.add(xOffset, 0, zOffset + 1)).getBlock() && block == worldIn.getBlockState(pos.add(xOffset + 1, 0, zOffset + 1)).getBlock();
    }

    @Override
    public boolean func_230362_a_(ISeedReader p_230362_1_, StructureManager p_230362_2_, ChunkGenerator p_230362_3_, Random p_230362_4_, BlockPos p_230362_5_, IFeatureConfig p_230362_6_) {
        return false;
    }
}