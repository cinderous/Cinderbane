package net.cinderous.cinderbane.util;

import net.cinderous.cinderbane.Cinderbane;
import net.cinderous.cinderbane.block.*;
import net.cinderous.cinderbane.entity.Cinderling;
import net.cinderous.cinderbane.item.CinderbanePortalItem;
import net.cinderous.cinderbane.item.CinderbanePortalItemOld;
import net.cinderous.cinderbane.tileentity.CinderspookTreeBuilderTileEntity;
import net.cinderous.cinderbane.world.CinderbaneAbyss;




import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = Cinderbane.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CinderbaneRegistry
{

    public static void init() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        BIOMES.register(FMLJavaModLoadingContext.get().getModEventBus());

        TILE_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }


    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Cinderbane.MODID);
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Cinderbane.MODID);
    private static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, Cinderbane.MODID);
    private static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Cinderbane.MODID);
    private static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Cinderbane.MODID);
    private static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, Cinderbane.MODID);
    //




    //Blocks
    public static final RegistryObject<Block> CINDIRT_GRASS_BLOCK = BLOCKS.register("cindirt_grass_block", () -> new CindirtGrassBlock(Block.Properties.create(Material.EARTH)));
    public static final RegistryObject<Block> CINDIRT = BLOCKS.register("cindirt", () -> new Block(Block.Properties.create(Material.EARTH)));
    public static final RegistryObject<Block> CINDERIUM_BLOCK = BLOCKS.register("cinderium_block", () -> new Block(Block.Properties.create(Material.IRON)));
    public static final RegistryObject<Block> CINDERSPOOK_TREE_BUILDER = BLOCKS.register("cinderspook_tree_builder", () -> new CinderspookTreeBuilder(Block.Properties.create(Material.IRON)));
    public static final RegistryObject<Block> CINDERBANE_GRASS = BLOCKS.register("cinderbane_grass", () -> new CinderbaneGrass(Block.Properties.create(Material.TALL_PLANTS)));
    public static final RegistryObject<Block> CINDERBANE_TALL_GRASS = BLOCKS.register("cinderbane_tall_grass", () -> new CinderbaneTallGrass(Block.Properties.create(Material.TALL_PLANTS)));
    public static final RegistryObject<Block> CINDERBANE_LOG = BLOCKS.register("cinderbane_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> CINDERBANE_LEAVES = BLOCKS.register("cinderbane_leaves", () -> new Block(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).sound(SoundType.PLANT).notSolid()));
    //public static final RegistryObject<Block> CINDERBANE_TREE_SAPLING = BLOCKS.register("cinderbane_tree_sapling", () -> new CinderbaneTreeSapling(new CinderfrostTree(), AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.PLANT)));
    public static final RegistryObject<Block> CINDERBANE_PORTAL_BLOCK = BLOCKS.register("cinderbane_portal_block", () -> new CinderbanePortalBlock(Block.Properties.create(Material.IRON)));
    //BlockItems
    public static final RegistryObject<Item> CINDIRT_GRASS_BLOCK_ITEM = ITEMS.register("cindirt_grass_block", () -> new BlockItem(CINDIRT_GRASS_BLOCK.get(), new Item.Properties().group(Cinderbane.CINDERBANE_TAB)));
    public static final RegistryObject<Item> CINDIRT_ITEM = ITEMS.register("cindirt", () -> new BlockItem(CINDIRT.get(), new Item.Properties().group(Cinderbane.CINDERBANE_TAB)));
    public static final RegistryObject<Item> CINDERIUM_BLOCK_ITEM = ITEMS.register("cinderium_block", () -> new BlockItem(CINDERIUM_BLOCK.get(), new Item.Properties().group(Cinderbane.CINDERBANE_TAB)));
    public static final RegistryObject<Item> CINDERBANE_DEV_BLOCK_ITEM = ITEMS.register("cinderbane_dev_block", () -> new BlockItem(CINDERSPOOK_TREE_BUILDER.get(), new Item.Properties().group(Cinderbane.CINDERBANE_TAB)));
    public static final RegistryObject<Item> CINDERBANE_GRASS_ITEM = ITEMS.register("cinderbane_grass", () -> new BlockItem(CINDERBANE_GRASS.get(), new Item.Properties().group(Cinderbane.CINDERBANE_TAB)));
    public static final RegistryObject<Item> CINDERBANE_TALL_GRASS_ITEM = ITEMS.register("cinderbane_tall_grass", () -> new BlockItem(CINDERBANE_TALL_GRASS.get(), new Item.Properties().group(Cinderbane.CINDERBANE_TAB)));
    public static final RegistryObject<Item> CINDERBANE_LOG_ITEM = ITEMS.register("cinderbane_log", () -> new BlockItem(CINDERBANE_LOG.get(), new Item.Properties().group(Cinderbane.CINDERBANE_TAB)));
    public static final RegistryObject<Item> CINDERBANE_LEAVES_ITEM = ITEMS.register("cinderbane_leaves", () -> new BlockItem(CINDERBANE_LEAVES.get(), new Item.Properties().group(Cinderbane.CINDERBANE_TAB)));
    //public static final RegistryObject<Item> CINDERBANE_TREE_SAPLING_ITEM = ITEMS.register("cinderbane_tree_sapling", () -> new BlockItem(CINDERBANE_TREE_SAPLING.get(), new Item.Properties().group(Cinderbane.CINDERBANE_TAB)));
    public static final RegistryObject<Item> CINDERBANE_PORTAL_BLOCK_ITEM = ITEMS.register("cinderbane_portal_block", () -> new BlockItem(CINDERBANE_PORTAL_BLOCK.get(), new Item.Properties().group(Cinderbane.CINDERBANE_TAB)));
    //Items
    public static final RegistryObject<Item> CINDERBANE_DEV = ITEMS.register("cinderbane_dev", () -> new Item(new Item.Properties().group(Cinderbane.CINDERBANE_TAB)));
    public static final RegistryObject<Item> CINDERBANE_PORTAL_ITEM = ITEMS.register("cinderbane_portal_item", () -> new CinderbanePortalItem(new Item.Properties().group(Cinderbane.CINDERBANE_TAB)));
;
    //Entities
    public static final RegistryObject<EntityType<Cinderling>> CINDERLING = ENTITIES
            .register("cinderling",
                    () -> EntityType.Builder.<Cinderling>create(Cinderling::new, EntityClassification.CREATURE)
                            .size(0.9f, 1.3f)
                            .build(new ResourceLocation(Cinderbane.MODID, "cinderling").toString()));
    //Entity Spawn and Attributes
    @SubscribeEvent
    public static void onRegisterEntityTypes(RegistryEvent.Register<EntityType<?>> event)
    {

        EntitySpawnPlacementRegistry.register(CinderbaneRegistry.CINDERLING.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canSpawnOn);
        GlobalEntityTypeAttributes.put(CinderbaneRegistry.CINDERLING.get(), Cinderling.registerAttributeMap().func_233813_a_());
    }



    //Tile Entities
    public static final RegistryObject<TileEntityType<CinderspookTreeBuilderTileEntity>> CINDERSPOOK_TREE_BUILDER_TILE_ENTITY = TILE_ENTITIES.register("cinderbane_dev_tile_entity", () -> TileEntityType.Builder
            .create(CinderspookTreeBuilderTileEntity::new, CinderbaneRegistry.CINDERSPOOK_TREE_BUILDER.get()).build(null));

    //Features
    //public static final CinderfrostTreeFeature CINDERFROST_TREE = new CinderfrostTreeFeature(CinderbaneBaseTreeFeatureConfig.field_236676_a_);

//    @SubscribeEvent
//    public static void bygRegisterFeatures(RegistryEvent.Register<Feature<?>> event) {
//        event.getRegistry().registerAll(
//            CINDERFROST_TREE.setRegistryName("cinderfrost_tree"));
//    };
    //public static final Feature<BaseTreeFeatureConfig> CINDERSPOOK_TREE = new CinderspookTree(BaseTreeFeatureConfig.field_236676_a_);
    //public static final Feature<BaseTreeFeatureConfig> CINDERFROST_TREE = FEATURES.register("cinderfrost_tree", () -> new TreeFeature(BaseTreeFeatureConfig.field_236676_a_));
//    @SubscribeEvent
//    public static void onRegisterFeatures(RegistryEvent.Register<Feature<?>> event) {
////        event.getRegistry().registerAll(
////                CINDERSPOOK_TREE = FEATURES.register("cinderspook_tree", () -> new CinderspookTree());
////        );
//
//    }
    //public static final AbstractTreeFeature<BaseTreeFeatureConfig> CINDERSPOOK_TREE = FEATURES.register("cinderspook_tree", () -> new TreeFeature(BaseTreeFeatureConfig.field_236676_a_));

    //Biomes
    public static final RegistryObject<Biome> CINDERBANE_ABYSS = BIOMES.register("cinderbane_abyss", () -> new CinderbaneAbyss());
    //Biome World Generation
    @SubscribeEvent
    public static void onRegisterBiomes(RegistryEvent.Register<Biome> event)
    {
        IForgeRegistry<Biome> registry = event.getRegistry();
        registerBiome(registry, CINDERBANE_ABYSS.get(), "cinderbane_abyss", true, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.COLD, BiomeDictionary.Type.CONIFEROUS, BiomeDictionary.Type.OVERWORLD);
    }

    private static void registerBiome(IForgeRegistry<Biome> registry, Biome biome, String name, boolean spawn, BiomeDictionary.Type... types) {
        //registry.register(biome.setRegistryName(Cinderbane.MODID, name));
        if (spawn) {
            BiomeManager.addSpawnBiome(biome);
        }
        BiomeDictionary.addTypes(biome, types);
    }

    //dimension joke




}
