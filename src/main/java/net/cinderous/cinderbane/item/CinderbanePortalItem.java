package net.cinderous.cinderbane.item;

import net.cinderous.cinderbane.Cinderbane;
import net.cinderous.cinderbane.util.CinderbaneRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
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

    public RegistryKey<World> getRatlantisDimension(){
        ResourceLocation resourcelocation = new ResourceLocation("cinderbane:cinderbane");
        RegistryKey<World> registrykey = RegistryKey.func_240903_a_(Registry.field_239699_ae_, resourcelocation);
        return registrykey;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (!worldIn.isRemote) {
            Cinderbane.LOGGER.info(getRatlantisDimension());
            BlockPos playerPos = new BlockPos (playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ());
            MinecraftServer server = worldIn.getServer();
            if ((!playerIn.isBeingRidden()) && (playerIn.getPassengers().isEmpty())) {
                boolean inOverworld = playerIn.world.func_234923_W_() != getRatlantisDimension();
                if ((playerIn instanceof ServerPlayerEntity)) {
                    ServerPlayerEntity thePlayer = (ServerPlayerEntity) playerIn;
                    if (thePlayer.timeUntilPortal > 0) {
                        thePlayer.timeUntilPortal = 10;
                    }
                    else if (inOverworld) {
                        thePlayer.timeUntilPortal = 10;
                        ServerWorld dimWorld = server.getWorld(getRatlantisDimension());
                        if(dimWorld != null){
                            //teleportEntity(thePlayer, dimWorld, playerPos, true);
                            cinderbaneTeleport(thePlayer, dimWorld, playerPos, true);
                        }
                    } else {
                        thePlayer.timeUntilPortal = 10;
                        ServerWorld dimWorld = server.getWorld(World.field_234918_g_);
                        if(dimWorld != null){
                            teleportEntity(thePlayer, dimWorld, playerPos, false);
                        }
                    }
                }
                if (!(playerIn instanceof PlayerEntity)) {
                    if (inOverworld) {
                        playerIn.timeUntilPortal = 10;
                        ServerWorld dimWorld = server.getWorld(getRatlantisDimension());
                        if(dimWorld != null){
                            teleportEntity(playerIn, dimWorld, playerPos, true);
                        }
                    } else {
                        ServerWorld dimWorld = server.getWorld(World.field_234918_g_);
                        playerIn.timeUntilPortal = 10;
                        if(dimWorld != null){
                            teleportEntity(playerIn, dimWorld, playerPos, false);
                        }
                    }
                }
            }
        }
         return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    private Entity cinderbaneTeleport(Entity entity, ServerWorld endpointWorld, BlockPos endpoint, boolean ratlantis) {
        BlockPos skyBox = new BlockPos(endpoint.getX(), 300, endpoint.getZ());
        ServerPlayerEntity player = (ServerPlayerEntity) entity;
        player.teleport(endpointWorld, skyBox.getX() + 0.5D, skyBox.getY() + 0.5D, skyBox.getZ() + 0.5D, entity.rotationYaw, entity.rotationPitch);
        placeInPortal(entity, endpointWorld);
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
    private Entity teleportEntity(Entity entity, ServerWorld endpointWorld, BlockPos endpoint, boolean ratlantis) {
        if(ratlantis){
            endpoint = new BlockPos(0, 112, 0);
            placeInPortal(entity, endpointWorld);

        }else{
            if (entity instanceof PlayerEntity && ((PlayerEntity) entity).getBedPosition().isPresent()) {
                BlockPos bedPos = ((PlayerEntity) entity).getBedPosition().get();
                endpoint = bedPos;
                entity.setLocationAndAngles(bedPos.getX() + 0.5D, bedPos.getY() + 1.5D, bedPos.getZ() + 0.5D, 0.0F, 0.0F);
            } else {
                BlockPos height = entity.world.getHeight(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, new BlockPos(entity.getPositionVec()));
                endpoint = height;
                entity.setLocationAndAngles(height.getX() + 0.5D, height.getY() + 0.5D, height.getZ() + 0.5D, entity.rotationYaw, 0.0F);
            }
        }
        if (entity instanceof ServerPlayerEntity) {
            ServerPlayerEntity player = (ServerPlayerEntity) entity;
            player.teleport(endpointWorld, endpoint.getX() + 0.5D, endpoint.getY() + 0.5D, endpoint.getZ() + 0.5D, entity.rotationYaw, entity.rotationPitch);
            return player;
        }

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
        BlockPos portalBottom = new BlockPos(1, 111, 1);
        for (BlockPos pos : BlockPos.getAllInBox(portalBottom.add(-2, 0, -2), portalBottom.add(2, 0, 2)).map(BlockPos::toImmutable).collect(Collectors.toList())) {
            serverWorld.setBlockState(pos, CinderbaneRegistry.CINDERIUM_BLOCK.get().getDefaultState());
            serverWorld.setBlockState(pos.up(4), CinderbaneRegistry.CINDERIUM_BLOCK.get().getDefaultState());
        }
        for (int i = 1; i < 4; i++) {
            serverWorld.setBlockState(portalBottom.add(2, 0, 2).up(i), CinderbaneRegistry.CINDERIUM_BLOCK.get().getDefaultState().with(RotatedPillarBlock.AXIS, Direction.Axis.Y));
            serverWorld.setBlockState(portalBottom.add(2, 0, -2).up(i), CinderbaneRegistry.CINDERIUM_BLOCK.get().getDefaultState().with(RotatedPillarBlock.AXIS, Direction.Axis.Y));
            serverWorld.setBlockState(portalBottom.add(-2, 0, 2).up(i), CinderbaneRegistry.CINDERIUM_BLOCK.get().getDefaultState().with(RotatedPillarBlock.AXIS, Direction.Axis.Y));
            serverWorld.setBlockState(portalBottom.add(-2, 0, -2).up(i), CinderbaneRegistry.CINDERIUM_BLOCK.get().getDefaultState().with(RotatedPillarBlock.AXIS, Direction.Axis.Y));
        }
        serverWorld.setBlockState(portalBottom, CinderbaneRegistry.CINDERIUM_BLOCK.get().getDefaultState());
        serverWorld.setBlockState(portalBottom.up(), CinderbaneRegistry.CINDERIUM_BLOCK.get().getDefaultState());
        serverWorld.setBlockState(portalBottom.up(2), CinderbaneRegistry.CINDERIUM_BLOCK.get().getDefaultState());
        serverWorld.setBlockState(portalBottom.up(3), CinderbaneRegistry.CINDERIUM_BLOCK.get().getDefaultState());
    }
}