package net.cinderous.cinderbane.client.entity.model.render;

import net.cinderous.cinderbane.Cinderbane;
import net.cinderous.cinderbane.client.entity.model.CinderlingModel;
import net.cinderous.cinderbane.entity.Cinderling;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class CinderlingRender  extends MobRenderer<Cinderling, CinderlingModel<Cinderling>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Cinderbane.MODID,
            "textures/entities/cinderling.png");

    public CinderlingRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new CinderlingModel<Cinderling>(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(Cinderling entity) {
        return TEXTURE;
    }
}
