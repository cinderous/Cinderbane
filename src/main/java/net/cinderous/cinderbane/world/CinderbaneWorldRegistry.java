package net.cinderous.cinderbane.world;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.util.Locale;

public class CinderbaneWorldRegistry {

        public static final CinderbaneSurfaceBuilder RATLANTIS_SURFACE = new CinderbaneSurfaceBuilder(SurfaceBuilderConfig.field_237203_a_);

        //public static Biome RATLANTIS_BIOME;
//
//        public static final Structure<NoFeatureConfig> RAT_RUINS = new RatlantisRuinsStructure(NoFeatureConfig.field_236558_a_);
//
//        public static final Structure<NoFeatureConfig> FLYING_DUTCHRAT = new DutchratShipStructure(NoFeatureConfig.field_236558_a_);
//
//        public static final Structure<NoFeatureConfig> RUNWAY = new RunwayStructure(NoFeatureConfig.field_236558_a_);
//
//
//
//        public static <F extends Structure<?>> void putStructureOnAList(String nameForList, F structure) {
//
//            Structure.field_236365_a_.put(nameForList.toLowerCase(Locale.ROOT), structure);
//
//        }



        public static void init() {

        }

}
