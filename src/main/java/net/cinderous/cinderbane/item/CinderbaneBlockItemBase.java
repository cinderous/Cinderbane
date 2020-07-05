package net.cinderous.cinderbane.item;

import net.cinderous.cinderbane.Cinderbane;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class CinderbaneBlockItemBase extends BlockItem {
    public CinderbaneBlockItemBase(Block blockIn, Properties builder) {
        super(blockIn, new Item.Properties().group(Cinderbane.CINDERBANE_TAB));
    }


}
