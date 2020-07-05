//package net.cinderous.cinderbane.world.feature;
//
//import com.mojang.serialization.Codec;
//import net.minecraft.block.trees.BigTree;
//import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
//import net.minecraft.world.gen.feature.ConfiguredFeature;
//import net.minecraft.world.gen.feature.Feature;
//import net.minecraft.world.gen.feature.TreeFeature;
//
//import javax.annotation.Nullable;
//import java.util.Random;
//
//public class CinderspookTree extends AbstractBigTree {
//    public CinderspookTree(Codec p_i231953_1_) {
//        super(p_i231953_1_);
//    }
//
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
