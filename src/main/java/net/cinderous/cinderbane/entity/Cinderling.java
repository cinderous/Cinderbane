package net.cinderous.cinderbane.entity;

import net.cinderous.cinderbane.util.CinderbaneRegistry;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class Cinderling extends AnimalEntity {

    public Cinderling(EntityType<? extends AnimalEntity> type, World worldIn) {
        super(type, worldIn);

    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 2.0D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25D, Ingredient.fromItems(Items.WHEAT), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
    }

    @Nullable
    @Override
    public AgeableEntity createChild(AgeableEntity ageable) {
        return CinderbaneRegistry.CINDERLING.get().create(this.world);
    }

    public static AttributeModifierMap.MutableAttribute registerAttributeMap()
    {
        return MonsterEntity.func_234295_eP_().func_233815_a_(Attributes.field_233818_a_, 16.0D).func_233815_a_(Attributes.field_233819_b_, 35.0D).func_233815_a_(Attributes.field_233821_d_, 0.24F).func_233815_a_(Attributes.field_233823_f_, 3.0D).func_233815_a_(Attributes.field_233826_i_, 2.0D);
    }

        public static boolean canAnimalSpawn(EntityType<? extends AnimalEntity> animal, IWorld worldIn, SpawnReason reason, BlockPos pos, Random random) {
        return worldIn.getBlockState(pos.down()).isIn(CinderbaneRegistry.CINDIRT_GRASS_BLOCK.get()) && worldIn.getLightSubtracted(pos, 0) > 8;
    }



}


