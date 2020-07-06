package net.cinderous.cinderbane;

import net.cinderous.cinderbane.events.ClientEvents;
import net.cinderous.cinderbane.util.CinderbaneRegistry;
import net.cinderous.cinderbane.world.CinderbaneWorldType;
import net.minecraft.entity.EntityClassification;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("cinderbane")
public class Cinderbane
{
        // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();

    public static final String NAME = "Cinderbane";

    public static final String MODID = "cinderbane";

    public Cinderbane() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        CinderbaneRegistry.init();
        MinecraftForge.EVENT_BUS.register(new ClientEvents());
        MinecraftForge.EVENT_BUS.register(this);
    }

    public static ResourceLocation locate(String name)
    {
        return new ResourceLocation(Cinderbane.MODID, name);
    }

    public static String find(String name)
    {
        return Cinderbane.MODID + ":" + name;
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code

        for (Biome biome : ForgeRegistries.BIOMES.getValues())
        {
            if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.OVERWORLD) && !BiomeDictionary.hasType(biome, BiomeDictionary.Type.NETHER) && !BiomeDictionary.hasType(biome, BiomeDictionary.Type.END) && !BiomeDictionary.hasType(biome, BiomeDictionary.Type.VOID) && !BiomeDictionary.hasType(biome, BiomeDictionary.Type.RIVER) && !BiomeDictionary.hasType(biome, BiomeDictionary.Type.OCEAN))
            {
                biome.getSpawns(EntityClassification.CREATURE).add(new Biome.SpawnListEntry(CinderbaneRegistry.CINDERLING.get(), 40, 2, 6));
            }

        }


        LOGGER.info("HELLO FROM PREINIT");

    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);
        CinderbaneWorldType.addGenerator();
    }

    public static final ItemGroup CINDERBANE_TAB = new ItemGroup("cinderbanetab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(CinderbaneRegistry.CINDERIUM_BLOCK.get());
        }

    };

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("examplemod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }



    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }


}
