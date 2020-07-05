package net.cinderous.cinderbane.world;

import net.cinderous.cinderbane.util.CinderbaneRegistry;
import net.cinderous.cinderbane.world.feature.CinderbaneFeatureConfigs;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockplacer.DoublePlantBlockPlacer;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;

public class CinderbaneAbyss extends Biome {

    //public static final BlockClusterFeatureConfig LARGE_FERN_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(RegistryHandler.CINDERFERN.get().getDefaultState()), new DoublePlantBlockPlacer())).tries(32).func_227317_b_().build();
    public static final BlockClusterFeatureConfig GRASS_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(CinderbaneRegistry.CINDERBANE_GRASS.get().getDefaultState()), new SimpleBlockPlacer())).tries(32).build();
    public static final BlockClusterFeatureConfig TALL_GRASS_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(CinderbaneRegistry.CINDERBANE_TALL_GRASS.get().getDefaultState()), new DoublePlantBlockPlacer())).tries(32).func_227317_b_().build();
    public static final BlockClusterFeatureConfig CINDERBANE_TREE_BUILDER_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(CinderbaneRegistry.CINDERSPOOK_TREE_BUILDER.get().getDefaultState()), new SimpleBlockPlacer())).tries(5).build();
    public CinderbaneAbyss() {
        super(new Biome.Builder().surfaceBuilder(CinderbaneSurfaceBuilder.DEFAULT, CinderbaneSurfaceBuilder.ABYSSCONFIG).precipitation(RainType.SNOW).category(Biome.Category.FOREST).depth(0.1F).scale(0.2F).temperature(0.0F).downfall(10F).func_235097_a_((new BiomeAmbience.Builder()).func_235246_b_(4159204).func_235248_c_(329011).func_235239_a_(12638463).func_235243_a_(MoodSoundAmbience.field_235027_b_).func_235238_a_()).parent((String)null));

//        this.addStructure(Feature.VILLAGE.withConfiguration(new VillageConfig("village/snowy/town_centers", 6)));
//        this.addStructure(Feature.IGLOO.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
//        this.addStructure(Feature.MINESHAFT.withConfiguration(new MineshaftConfig(0.004D, MineshaftStructure.Type.NORMAL)));
//        this.addStructure(Feature.STRONGHOLD.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
//        this.addStructure(Feature.PILLAGER_OUTPOST.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
        DefaultBiomeFeatures.addCarvers(this);
//        DefaultBiomeFeatures.addStructures(this);
        //DefaultBiomeFeatures.addMonsterRooms(this);
        //DefaultBiomeFeatures.addTaigaRocks(this);
        //DefaultBiomeFeatures.addTaigaLargeFerns(this);
        //DefaultBiomeFeatures.addStoneVariants(this);
        //DefaultBiomeFeatures.addOres(this);
        //DefaultBiomeFeatures.addSedimentDisks(this);
 //       BYGTreeFeatures.addGiantBlueTaigaTrees(this);
       // DefaultBiomeFeatures.addDefaultFlowers(this);
        //DefaultBiomeFeatures.addTaigaGrassDeadBushesMushrooms(this);
        //DefaultBiomeFeatures.addMushrooms(this);
        //DefaultBiomeFeatures.addReedsAndPumpkins(this);
        //DefaultBiomeFeatures.addSprings(this);
        DefaultBiomeFeatures.addLakes(this);
        DefaultBiomeFeatures.addFreezeTopLayer(this);
//        BYGFeatures.addBlueberries(this);
//        BYGFeatures.addGrass(this);

//        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
//                CinderbaneRegistry.CINDERSPOOK_TREE.withConfiguration(CinderbaneFeatureConfigs.CINDERSPOOK_TREE_CONFIG).withPlacement(
//                        Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(7, 0.1f, 1))));
//        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
//                CinderbaneRegistry.CINDERFROST_TREE.withConfiguration(CinderbaneFeatureConfigs.CINDERSPOOK_TREE_CONFIG).withPlacement(
//                        Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(7, 0.1f, 1))));

        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(CINDERBANE_TREE_BUILDER_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(2))));


        //this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(LARGE_FERN_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_32.configure(new FrequencyConfig(5))));

        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(GRASS_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(8))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(TALL_GRASS_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_32.configure(new FrequencyConfig(4))));
        //this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(CinderbaneRegistry.CINDERLING.get(), 40, 2, 6));

        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.SHEEP, 12, 4, 4));
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.PIG, 10, 4, 4));
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.CHICKEN, 10, 4, 4));
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.COW, 8, 4, 4));
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.WOLF, 8, 4, 4));
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.RABBIT, 4, 2, 3));
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.FOX, 8, 2, 4));
        this.addSpawn(EntityClassification.AMBIENT, new SpawnListEntry(EntityType.BAT, 10, 8, 8));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.SPIDER, 100, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.ZOMBIE, 95, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.SKELETON, 100, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.CREEPER, 100, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.SLIME, 100, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.ENDERMAN, 10, 1, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.WITCH, 5, 1, 1));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.STRAY, 80, 4, 4));

    }

    @Override
    public Biome getRiver() {
        return Biomes.FROZEN_RIVER;
    }
}