//package net.cinderous.cinderbane.world.feature;
//
//import com.mojang.serialization.Codec;
//import net.minecraft.block.trees.BigTree;
//import net.minecraft.block.trees.Tree;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.ISeedReader;
//import net.minecraft.world.biome.DefaultBiomeFeatures;
//import net.minecraft.world.gen.ChunkGenerator;
//import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
//import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
//import net.minecraft.world.gen.feature.ConfiguredFeature;
//import net.minecraft.world.gen.feature.Feature;
//import net.minecraft.world.gen.feature.TreeFeature;
//import net.minecraft.world.gen.feature.structure.StructureManager;
//import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
//import net.minecraftforge.common.IPlantable;
//
//import javax.annotation.Nullable;
//import java.rmi.registry.RegistryHandler;
//import java.util.Random;
//
//public class CinderfrostTree extends BigTree {
//    /**
//     * Get a {@link net.minecraft.world.gen.feature.ConfiguredFeature} of tree
//     */
//    @Nullable
//    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean p_225546_2_) {
//        return (new TreeFeature(BaseTreeFeatureConfig.field_236676_a_)).withConfiguration(CinderbaneFeatureConfigs.CINDERBANE_SAPLING_TREE_CONFIG);
//    }
//
//    /**
//     * Get a {@link net.minecraft.world.gen.feature.ConfiguredFeature} of the huge variant of this tree
//     */
//    @Nullable
//    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getHugeTreeFeature(Random p_225547_1_) {
//        return Feature.field_236291_c_.withConfiguration(CinderbaneFeatureConfigs.MEGA_CINDERBANE_SAPLING_TREE_CONFIG);
//    }
//}