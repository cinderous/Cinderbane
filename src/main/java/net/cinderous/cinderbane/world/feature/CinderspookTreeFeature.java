package net.cinderous.cinderbane.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.trees.Tree;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nullable;
import java.util.Random;

public class CinderspookTreeFeature extends Feature<BaseTreeFeatureConfig> {


    public CinderspookTreeFeature(Codec<BaseTreeFeatureConfig> p_i231953_1_) {
        super(p_i231953_1_);
    }

    @Override
    public boolean func_230362_a_(ISeedReader p_230362_1_, StructureManager p_230362_2_, ChunkGenerator p_230362_3_, Random p_230362_4_, BlockPos p_230362_5_, BaseTreeFeatureConfig p_230362_6_) {
        return true;
    }
}
