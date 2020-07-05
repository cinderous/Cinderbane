package net.cinderous.cinderbane.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;

public abstract class AbstractTreeFeature <T extends IFeatureConfig> extends Feature<T> {

    public AbstractTreeFeature(Codec<T> p_i231953_1_) {
        super(p_i231953_1_);
    }
}
