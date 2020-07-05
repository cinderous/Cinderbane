package net.cinderous.cinderbane.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class Cindirt extends Block {
    public Cindirt() {
        super(Block.Properties.create(Material.EARTH)
                .hardnessAndResistance(0.1f,0.1f)
                .sound(SoundType.GROUND)
                .doesNotBlockMovement()
                .harvestTool(ToolType.SHOVEL)

        );
    }
}
