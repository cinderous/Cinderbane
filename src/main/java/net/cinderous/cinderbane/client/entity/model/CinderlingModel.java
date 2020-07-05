package net.cinderous.cinderbane.client.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.cinderous.cinderbane.entity.Cinderling;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class CinderlingModel <T extends Cinderling> extends EntityModel<T> {
    private final ModelRenderer Body;
    private final ModelRenderer Head;



    public CinderlingModel() {
        textureWidth = 16;
        textureHeight = 16;

        Body = new ModelRenderer(this);
        Body.setRotationPoint(0.0F, 29.0F, 3.0F);
        Body.setTextureOffset(0, 0).addBox(-3.0F, -11.0F, -4.0F, 5.0F, 6.0F, 5.0F, 0.0F, false);
        Body.setTextureOffset(0, 4).addBox(-3.0F, -8.0F, 1.0F, 5.0F, 3.0F, 1.0F, 0.0F, false);
        Body.setTextureOffset(0, 4).addBox(-2.0F, -7.0F, 1.0F, 3.0F, 2.0F, 2.0F, 0.0F, false);

        Head = new ModelRenderer(this);
        Head.setRotationPoint(0.0F, 3.0F, -5.0F);
        Body.addChild(Head);
        Head.setTextureOffset(2, 11).addBox(-2.0F, -12.0F, -2.0F, 3.0F, 4.0F, 2.0F, 0.0F, false);
        Head.setTextureOffset(1, 8).addBox(-2.0F, -10.0F, -4.0F, 3.0F, 2.0F, 2.0F, 0.0F, false);
        Head.setTextureOffset(1, 9).addBox(-2.0F, -11.0F, 0.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        Body.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }


}