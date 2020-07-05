package net.cinderous.cinderbane.world.feature;

import com.google.common.collect.ImmutableList;
import net.cinderous.cinderbane.util.CinderbaneRegistry;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.TwoLayerFeature;
import net.minecraft.world.gen.foliageplacer.AcaciaFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.JungleFoliagePlacer;
import net.minecraft.world.gen.treedecorator.LeaveVineTreeDecorator;
import net.minecraft.world.gen.treedecorator.TrunkVineTreeDecorator;
import net.minecraft.world.gen.trunkplacer.ForkyTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.MegaJungleTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;

public class CinderbaneFeatureConfigs {

    //public static final CinderbaneBaseTreeFeatureConfig CINDERBANE_SAPLING_TREE_CONFIG = (new CinderbaneBaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(CinderbaneRegistry.CINDERBANE_LOG.get().getDefaultState()), new SimpleBlockStateProvider(CinderbaneRegistry.CINDERBANE_LEAVES.get().getDefaultState()), new BlobFoliagePlacer(2, 0, 0, 0, 3), new StraightTrunkPlacer(4, 8, 0), new TwoLayerFeature(1, 0, 1))).func_236700_a_().build();
    public static final BaseTreeFeatureConfig MEGA_CINDERBANE_SAPLING_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(CinderbaneRegistry.CINDERBANE_LOG.get().getDefaultState()), new SimpleBlockStateProvider(CinderbaneRegistry.CINDERBANE_LEAVES.get().getDefaultState()), new JungleFoliagePlacer(2, 0, 0, 0, 2), new MegaJungleTrunkPlacer(10, 2, 19), new TwoLayerFeature(1, 1, 2))).func_236703_a_(ImmutableList.of(TrunkVineTreeDecorator.field_236879_b_, LeaveVineTreeDecorator.field_236871_b_)).build();
    //public static final CinderbaneBaseTreeFeatureConfig CINDERSPOOK_TREE_CONFIG = (new CinderbaneBaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(CinderbaneRegistry.CINDERBANE_LOG.get().getDefaultState()), new SimpleBlockStateProvider(CinderbaneRegistry.CINDERBANE_LEAVES.get().getDefaultState()), new AcaciaFoliagePlacer(2, 0, 0, 0), new ForkyTrunkPlacer(5, 2, 2), new TwoLayerFeature(1, 0, 2))).func_236700_a_().build();
}
