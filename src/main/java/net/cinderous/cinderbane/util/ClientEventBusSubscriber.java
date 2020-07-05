package net.cinderous.cinderbane.util;

import net.cinderous.cinderbane.Cinderbane;
import net.cinderous.cinderbane.client.entity.model.render.CinderlingRender;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Cinderbane.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {

    @SubscribeEvent
    public static void registerRenders(ModelRegistryEvent e) {
        RenderingRegistry.registerEntityRenderingHandler(CinderbaneRegistry.CINDERLING.get(), CinderlingRender::new);

    }
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        RenderTypeLookup.setRenderLayer(CinderbaneRegistry.CINDERBANE_GRASS.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(CinderbaneRegistry.CINDERBANE_TALL_GRASS.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(CinderbaneRegistry.CINDERBANE_LEAVES.get(), RenderType.getCutout());
    }
}
