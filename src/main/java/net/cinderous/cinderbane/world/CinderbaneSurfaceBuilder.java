package net.cinderous.cinderbane.world;

import com.mojang.serialization.Codec;
import net.cinderous.cinderbane.util.CinderbaneRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.util.Random;

public class  CinderbaneSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig> {

    public static final SurfaceBuilderConfig ABYSSCONFIG = new SurfaceBuilderConfig(CinderbaneRegistry.CINDIRT_GRASS_BLOCK.get().getDefaultState(), CinderbaneRegistry.CINDIRT.get().getDefaultState(), Blocks.OBSIDIAN.getDefaultState());

    public CinderbaneSurfaceBuilder(Codec<SurfaceBuilderConfig> p_i232136_1_) {
        super(p_i232136_1_);
    }



@Override
public void buildSurface(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise,
                         BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config) {

        double d0 = Biome.INFO_NOISE.noiseAt((double)x * 0.25D, (double)z * 0.25D, false);
        if (d0 > 0.0D) {
        int i = x & 15;
        int j = z & 15;
        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

        for(int k = startHeight; k >= 0; --k) {
        blockpos$mutable.setPos(i, k, j);
        if (!chunkIn.getBlockState(blockpos$mutable).isAir()) {
        if (k == 62 && chunkIn.getBlockState(blockpos$mutable).getBlock() != defaultFluid.getBlock()) {
        chunkIn.setBlockState(blockpos$mutable, defaultFluid, false);
        }
        break;
        }
        }
        }

        SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, config);
//        Random rd = new Random();
//        int i = rd.nextInt(3);
//        if (i == 0) {
//            SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock,
//                    defaultFluid, seaLevel, seed,
//                    new SurfaceBuilderConfig(BlockInit.EXAMPLE_BLOCK.get().getDefaultState(),
//                            RegistryHandler.DEF_BLOCK.get().getDefaultState(), Blocks.ACACIA_PLANKS.getDefaultState()));
//        } else {
//            SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock,
//                    defaultFluid, seaLevel, seed,
//                    new SurfaceBuilderConfig(
//                            i == 1 ? Blocks.GRASS_BLOCK.getDefaultState()
//                                    : BlockInit.EXAMPLE_BLOCK.get().getDefaultState(),
//                            BlockInit.DEF_BLOCK.get().getDefaultState(), Blocks.ACACIA_PLANKS.getDefaultState()));
//        }
        }
        }