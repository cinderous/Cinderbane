package net.cinderous.cinderbane.item;


import net.cinderous.cinderbane.Cinderbane;
import net.cinderous.cinderbane.util.CinderbaneRegistry;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.server.ServerWorld;

import java.util.stream.Collectors;

public class CinderbanePortalItem extends Item {

    public CinderbanePortalItem(Properties properties) {
        super(properties);
    }

    public RegistryKey<World> getCinderbaneDimension(){
        ResourceLocation resourcelocation = new ResourceLocation("cinderbane:cinderbane");
        RegistryKey<World> registrykey = RegistryKey.func_240903_a_(Registry.field_239699_ae_, resourcelocation);
        return registrykey;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity entity, Hand handIn) {
        if (!worldIn.isRemote) {
            MinecraftServer server = worldIn.getServer();
            BlockPos pos = new BlockPos (entity.getPosX(), entity.getPosY(), entity.getPosZ());
            if ((!entity.isBeingRidden()) && (entity.getPassengers().isEmpty())) {
                boolean inOverworld = entity.world.func_234923_W_() != getCinderbaneDimension();
                if ((entity instanceof ServerPlayerEntity)) {
                    ServerPlayerEntity thePlayer = (ServerPlayerEntity) entity;
                    if (thePlayer.timeUntilPortal > 0) {
                        thePlayer.timeUntilPortal = 10;
                    }
                    else if (inOverworld) {
                        thePlayer.timeUntilPortal = 10;
                        ServerWorld dimWorld = server.getWorld(getCinderbaneDimension());
                        if(dimWorld != null){
                            cinderbaneTeleport(thePlayer, dimWorld, pos, true);
                            //teleportEntity(thePlayer, dimWorld, pos, true);
                        }
                    } else {
                        thePlayer.timeUntilPortal = 10;
                        ServerWorld dimWorld = server.getWorld(World.field_234918_g_);
                        if(dimWorld != null){
                            cinderbaneTeleport(thePlayer, dimWorld, pos, true);
                        }
                    }
                }
                if (!(entity instanceof PlayerEntity)) {
                    if (inOverworld) {
                        entity.timeUntilPortal = 10;
                        ServerWorld dimWorld = server.getWorld(getCinderbaneDimension());
                        if(dimWorld != null){
                            cinderbaneTeleport(entity, dimWorld, pos, true);
                        }
                    } else {
                        ServerWorld dimWorld = server.getWorld(World.field_234918_g_);
                        entity.timeUntilPortal = 10;
                        if(dimWorld != null){
                            cinderbaneTeleport(entity, dimWorld, pos, true);
                        }
                    }
                }
            }
        }
        return super.onItemRightClick(worldIn, entity, handIn);
    }

    private Entity cinderbaneTeleport(Entity entity, ServerWorld endpointWorld, BlockPos endpoint, boolean ratlantis) {
        BlockPos skyBox = new BlockPos(endpoint.getX(), 300, endpoint.getZ());
        ServerPlayerEntity player = (ServerPlayerEntity) entity;
        player.teleport(endpointWorld, skyBox.getX() + 0.5D, skyBox.getY() + 0.5D, skyBox.getZ() + 0.5D, entity.rotationYaw, entity.rotationPitch);

        //placeInPortal(entity, endpointWorld);
        entity.setPositionAndRotation(0, 110, 0, 0, 0);
        entity.setMotion(0, 0, 0);
        entity.detach();
        entity.func_241206_a_(endpointWorld);
        Entity teleportedEntity = entity.getType().create(endpointWorld);
        if (teleportedEntity == null) {
            return entity;
        }
        teleportedEntity.copyDataFromOld(entity);
        teleportedEntity.setLocationAndAngles(endpoint.getX() + 0.5D, endpoint.getY() + 0.5D, endpoint.getZ() + 0.5D, entity.rotationYaw, entity.rotationPitch);
        teleportedEntity.setRotationYawHead(entity.rotationYaw);
        endpointWorld.addFromAnotherDimension(teleportedEntity);



        return teleportedEntity;
    }


    public void placeInPortal(Entity entity, ServerWorld serverWorld) {
        entity.setPositionAndRotation(0, 110, 0, 0, 0);
        entity.setMotion(0, 0, 0);
//        BlockPos portalBottom = new BlockPos(1, 111, 1);
//        for (BlockPos pos : BlockPos.getAllInBox(portalBottom.add(-2, 0, -2), portalBottom.add(2, 0, 2)).map(BlockPos::toImmutable).collect(Collectors.toList())) {
//            serverWorld.setBlockState(pos, CinderbaneRegistry.CINDERIUM_BLOCK.get().getDefaultState());
//            serverWorld.setBlockState(pos.up(4), CinderbaneRegistry.CINDERIUM_BLOCK.get().getDefaultState());
//        }
//        for (int i = 1; i < 4; i++) {
//            serverWorld.setBlockState(portalBottom.add(2, 0, 2).up(i), CinderbaneRegistry.CINDERIUM_BLOCK.get().getDefaultState().with(RotatedPillarBlock.AXIS, Direction.Axis.Y));
//            serverWorld.setBlockState(portalBottom.add(2, 0, -2).up(i), CinderbaneRegistry.CINDERIUM_BLOCK.get().getDefaultState().with(RotatedPillarBlock.AXIS, Direction.Axis.Y));
//            serverWorld.setBlockState(portalBottom.add(-2, 0, 2).up(i), CinderbaneRegistry.CINDERIUM_BLOCK.get().getDefaultState().with(RotatedPillarBlock.AXIS, Direction.Axis.Y));
//            serverWorld.setBlockState(portalBottom.add(-2, 0, -2).up(i), CinderbaneRegistry.CINDERIUM_BLOCK.get().getDefaultState().with(RotatedPillarBlock.AXIS, Direction.Axis.Y));
//        }
//        serverWorld.setBlockState(portalBottom, CinderbaneRegistry.CINDERIUM_BLOCK.get().getDefaultState());
//        serverWorld.setBlockState(portalBottom.up(), CinderbaneRegistry.CINDERIUM_BLOCK.get().getDefaultState());
//        serverWorld.setBlockState(portalBottom.up(2), CinderbaneRegistry.CINDERIUM_BLOCK.get().getDefaultState());
//        serverWorld.setBlockState(portalBottom.up(3), CinderbaneRegistry.CINDERIUM_BLOCK.get().getDefaultState());
    }
}