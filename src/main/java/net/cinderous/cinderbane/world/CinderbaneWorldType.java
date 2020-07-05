package net.cinderous.cinderbane.world;

import net.cinderous.cinderbane.util.CinderbaneRegistry;
import net.minecraft.client.gui.screen.BiomeGeneratorTypeScreens;
import net.minecraft.world.biome.provider.SingleBiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.DimensionSettings;
import net.minecraft.world.gen.NoiseChunkGenerator;

public class CinderbaneWorldType {
    private static final BiomeGeneratorTypeScreens CINDERBANE = new BiomeGeneratorTypeScreens("cinderbane") {
        protected ChunkGenerator func_230484_a_(long seed) {
            return new NoiseChunkGenerator(new SingleBiomeProvider(CinderbaneRegistry.CINDERBANE_ABYSS.get()), seed, DimensionSettings.Preset.field_236127_g_.func_236137_b_());
        }
    };

    public static void addGenerator() {
        BiomeGeneratorTypeScreens.field_239068_c_.add(CINDERBANE);
    }
}