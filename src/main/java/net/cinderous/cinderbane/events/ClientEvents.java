package net.cinderous.cinderbane.events;

import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.fluid.FluidState;
import net.minecraft.tags.FluidTags;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@OnlyIn(Dist.CLIENT)
public class ClientEvents {


    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void onFogColors(EntityViewRenderEvent.FogColors event) {
        ClientWorld world = Minecraft.getInstance().world;
        if(world.func_234923_W_().func_240901_a_().getPath().equals("cinderbane")){

            float p_230494_2_ = Minecraft.getInstance().world.getCelestialAngle(Minecraft.getInstance().getRenderPartialTicks());
            float red = (p_230494_2_ * 0.2F);
            float green = (p_230494_2_ * 0F);
            float blue = (p_230494_2_ * 0F);
            FluidState fluidstate = event.getInfo().getFluidState();
            if (!fluidstate.isTagged(FluidTags.WATER)) {
                event.setRed(red);
                event.setGreen(green);
                event.setBlue(blue);
            }
        }

    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void onSkyColor(EntityViewRenderEvent.FogColors event) {
        ClientWorld world = Minecraft.getInstance().world;
        if(world.func_234923_W_().func_240901_a_().getPath().equals("cinderbane")){

            float p_230494_2_ = Minecraft.getInstance().world.getCelestialAngle(Minecraft.getInstance().getRenderPartialTicks());
            float red = (p_230494_2_ * 0.4F);
            float green = (p_230494_2_ * 0.4F);
            float blue = (p_230494_2_ * 0.4F);
            FluidState fluidstate = event.getInfo().getFluidState();
            if (!fluidstate.isTagged(FluidTags.WATER)) {
                event.setRed(red);
                event.setGreen(green);
                event.setBlue(blue);
            }
        }

    }

}
