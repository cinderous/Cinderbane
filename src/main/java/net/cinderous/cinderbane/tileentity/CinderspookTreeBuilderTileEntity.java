package net.cinderous.cinderbane.tileentity;

import net.cinderous.cinderbane.Cinderbane;
import net.cinderous.cinderbane.util.CinderbaneRegistry;
import net.cinderous.cinderbane.util.TickHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

public class CinderspookTreeBuilderTileEntity extends TileEntity implements ITickableTileEntity {
    private TickHandler handler;
    public BlockState log = CinderbaneRegistry.CINDERBANE_LOG.get().getDefaultState();
    public BlockState leaves = CinderbaneRegistry.CINDERBANE_LEAVES.get().getDefaultState();
    public BlockState air = Blocks.AIR.getDefaultState();

    public Random rand = new Random();
    int firstHeight = rand.nextInt(9) + 5;
    public Random rand2 = new Random();
    int firstLength = rand2.nextInt(4);
    int secondLength = rand2.nextInt(6);
    int firstWidthDirection = rand.nextInt(4);
    int secondWidthDirection = rand.nextInt(4);


    int height = rand.nextInt(15) + 2;
    int numberOfPods = 2;



    public CinderspookTreeBuilderTileEntity(TileEntityType<?> typeIn) {
        super(typeIn);
        Cinderbane.LOGGER.info("This should be the constructor");

        //
        handler = new TickHandler();
        handler.addMethod("initialize", (timesRun) -> initialize());
        //handler.addRepeatedMethod("oldloop", rand.nextInt(8) +1, (timesRun) -> doFirstLoop(timesRun));
        Direction horizontalDirection[] = {
//                Direction.UP,
//                Direction.DOWN,
                Direction.NORTH,
                Direction.WEST,
                Direction.SOUTH,
                Direction.EAST,
        };
        Direction verticalDirection[] = {
                Direction.UP,
                Direction.DOWN,
//                Direction.NORTH,
//                Direction.WEST,
//                Direction.SOUTH,
//                Direction.EAST,
        };

        handler.addRepeatedDelayedMethod("firstLoop",height, 5, (timesRun) -> doFirstLoop(verticalDirection[0]));
        handler.addMethod("everythingElse", (timesRun) -> doEverythingElse());
        handler.addRepeatedDelayedMethod("test",firstLength , 5, (timesRun) -> thisIsAwesome(horizontalDirection[firstWidthDirection]));
        handler.addMethod("doLeaves", (timesRun) -> doLeaves());
        handler.addMethod("finalizeTree", (timesRun) -> finalizeTree());
        handler.addRepeatedDelayedMethod("doMoreTree", rand.nextInt(9) + 5, 5, (timesRun) -> doMoreTree(verticalDirection[0]));
        handler.addRepeatedDelayedMethod("doMoreBranches", secondLength, 5, (timesRun) -> doMoreBranches(horizontalDirection[secondWidthDirection]));
    }

    public CinderspookTreeBuilderTileEntity() {
        this(CinderbaneRegistry.CINDERSPOOK_TREE_BUILDER_TILE_ENTITY.get());

    }

    @Override
    public void tick() {
        if (!world.isRemote) {
            handler.tick();

        }
    }

    //When the status is PLANTED the first tick afterwards runs the initialization code.
    public String initialize() {
//        EntityPredicate predicate = (new EntityPredicate()).setDistance(8.0D).allowInvulnerable().allowFriendlyFire();
//        LivingEntity closestPlayer = this.world.getClosestPlayer(predicate, this.pos.getX(), this.pos.getY(), this.pos.getZ());
//        if(closestPlayer != null) {
//            List<LivingEntity> list = this.world.getTargettableEntitiesWithinAABB(LivingEntity.class, predicate, closestPlayer, closestPlayer.getBoundingBox().grow(8.0D));
//            double d0 = Double.MAX_VALUE;
//            LivingEntity animalentity = null;
//
//            for(LivingEntity animalentity1 : list) {
//                if (closestPlayer.getDistanceSq(animalentity1) < d0) {
//                    animalentity = animalentity1;
//                    d0 = closestPlayer.getDistanceSq(animalentity1);
//                }
//            }
            EntityPredicate predicate = (new EntityPredicate()).setDistance(8.0D).allowInvulnerable().allowFriendlyFire();
            LivingEntity closestPlayer = this.world.getClosestPlayer(predicate, this.pos.getX(), this.pos.getY(), this.pos.getZ());
        if(closestPlayer != null) {
            double distance = closestPlayer.getDistanceSq(this.pos.getX(), this.pos.getY(), this.pos.getZ());
            if (distance <= 100) {
                Cinderbane.LOGGER.info("THIS SHOULD START");
                handler.addVariable("currentPos", this.getPos());
                handler.addVariable("startPos", this.getPos());
                handler.addVariable("stemPos1", this.getPos().offset(Direction.UP, height));
                //handler.addVariable("pods", numberOfPods);
                //handler.addVariable("stemPos2", this.getPos().offset(Direction.UP, firstHeight+ secondHeight));

                BlockPos currentPos = (BlockPos) handler.getVariable("currentPos").get();
//            world.setBlockState(currentPos.offset(Direction.WEST, 1), log);
//            world.setBlockState(currentPos.offset(Direction.NORTH, 1), log);
//            world.setBlockState(currentPos.offset(Direction.EAST, 1), log);
//            world.setBlockState(currentPos.offset(Direction.SOUTH, 1), log);
                return "firstLoop";
            }
        }

        return "initialize";
        }






    //When the status is FIRSTLOOP it runs this code
    public String doFirstLoop(Direction x) {
        //CrystalArchitect.LOGGER.info("THIS SHOULD BE FIRST LOOP");
        BlockPos currentPos = (BlockPos) handler.getVariable("currentPos").get();
        world.setBlockState(currentPos.offset(x), log);
        handler.setVariable("currentPos", currentPos.offset(x));
        //handler.addVariable("stemPos", currentPos.offset(x));
        return "everythingElse";
    }

    public String doEverythingElse() {
        Cinderbane.LOGGER.info("THIS SHOULD BE EVERYTHTN LOOP");
        BlockPos currentPos = (BlockPos) handler.getVariable("currentPos").get();
        BlockPos stemPos1 = (BlockPos) handler.getVariable("stemPos1").get();

        if(world.getBlockState(stemPos1.offset(Direction.NORTH)) != log) {
            world.setBlockState(stemPos1.offset(Direction.NORTH), leaves);
        }
        if(world.getBlockState(stemPos1.offset(Direction.SOUTH)) != log) {
            world.setBlockState(stemPos1.offset(Direction.SOUTH), leaves);
        }
        if(world.getBlockState(stemPos1.offset(Direction.EAST)) != log) {
            world.setBlockState(stemPos1.offset(Direction.EAST), leaves);
        }
        if(world.getBlockState(stemPos1.offset(Direction.WEST)) != log) {
            world.setBlockState(stemPos1.offset(Direction.WEST), leaves);
        }
        if(world.getBlockState(stemPos1.offset(Direction.UP)) != log) {
            world.setBlockState(stemPos1.offset(Direction.UP), leaves);
        }
        if(world.getBlockState(stemPos1.offset(Direction.DOWN)) != log) {
            world.setBlockState(stemPos1.offset(Direction.DOWN), leaves);
        }

        if(world.getBlockState(stemPos1.offset(Direction.NORTH,2)) != log) {
            world.setBlockState(stemPos1.offset(Direction.NORTH,2), leaves);
        }
        if(world.getBlockState(stemPos1.offset(Direction.SOUTH,2)) != log) {
            world.setBlockState(stemPos1.offset(Direction.SOUTH,2), leaves);
        }
        if(world.getBlockState(stemPos1.offset(Direction.EAST,2)) != log) {
            world.setBlockState(stemPos1.offset(Direction.EAST,2), leaves);
        }
        if(world.getBlockState(stemPos1.offset(Direction.WEST,2)) != log) {
            world.setBlockState(stemPos1.offset(Direction.WEST,2), leaves);
        }
        if(world.getBlockState(stemPos1.offset(Direction.UP,2)) != log) {
            world.setBlockState(stemPos1.offset(Direction.UP,2), leaves);
        }
        if(world.getBlockState(stemPos1.offset(Direction.DOWN,2)) != log) {
            world.setBlockState(stemPos1.offset(Direction.DOWN,2), leaves);
        }

        return "test";
    }

    //the first branch
    public String thisIsAwesome(Direction x) {
//        Cinderbane.LOGGER.info("THIS SHOULD BE AWESOME LOOP");
//        BlockPos currentPos = (BlockPos) handler.getVariable("currentPos").get();
        //int pods = (int) handler.getVariable("pods").get();

            BlockPos stemPos1 = (BlockPos) handler.getVariable("stemPos1").get();
            Cinderbane.LOGGER.info("STEM POS" + stemPos1);
            int segmentSize = height / 2 / numberOfPods;
            int segmentWholeSize = Math.round(segmentSize);
            Cinderbane.LOGGER.info("SEGMENT " + segmentWholeSize);;
            //int segmentYOffset =
            BlockPos segmentPos = new BlockPos(stemPos1.getX(),stemPos1.getY()-segmentWholeSize,stemPos1.getZ());
            Cinderbane.LOGGER.info("SEG POS" + segmentPos);
            world.setBlockState(segmentPos, leaves);
            if(world.getBlockState(segmentPos.offset(Direction.WEST)) == air) {
                world.setBlockState(segmentPos.offset(Direction.NORTH), leaves);
            }
            if(world.getBlockState(segmentPos.offset(Direction.WEST)) == air) {
                world.setBlockState(segmentPos.offset(Direction.SOUTH), leaves);
            }
            if(world.getBlockState(segmentPos.offset(Direction.WEST)) == air) {
                world.setBlockState(segmentPos.offset(Direction.EAST), leaves);
            }
            if(world.getBlockState(segmentPos.offset(Direction.WEST)) == air ) {
                world.setBlockState(segmentPos.offset(Direction.WEST), leaves);
            }

             handler.addVariable("segmentPos", segmentPos);
//        world.setBlockState(currentPos.offset(x), log);
//        handler.setVariable("currentPos", currentPos.offset(x));
        return "doLeaves";
    }

    public String doLeaves() {
        Cinderbane.LOGGER.info("THIS SHOULD BE LEAVES");
        int segmentSize = height / 2 / numberOfPods;
        int segmentWholeSize = Math.round(segmentSize);
        BlockPos segmentPosOriginal = (BlockPos) handler.getVariable("segmentPos").get();
        BlockPos segmentPos = new BlockPos(segmentPosOriginal.getX(),segmentPosOriginal.getY()-segmentWholeSize,segmentPosOriginal.getZ());
        Cinderbane.LOGGER.info("SEG POS" + segmentPos);
        world.setBlockState(segmentPos, leaves);
        if(world.getBlockState(segmentPos.offset(Direction.WEST)) == air) {
            world.setBlockState(segmentPos.offset(Direction.NORTH), leaves);
        }
        if(world.getBlockState(segmentPos.offset(Direction.WEST)) == air) {
            world.setBlockState(segmentPos.offset(Direction.SOUTH), leaves);
        }
        if(world.getBlockState(segmentPos.offset(Direction.WEST)) == air) {
            world.setBlockState(segmentPos.offset(Direction.EAST), leaves);
        }
        if(world.getBlockState(segmentPos.offset(Direction.WEST)) == air ) {
            world.setBlockState(segmentPos.offset(Direction.WEST), leaves);
        }
//        BlockPos currentPos = (BlockPos) handler.getVariable("currentPos").get();
//        if(world.getBlockState(currentPos.offset(Direction.NORTH)) != log) {
//            world.setBlockState(currentPos.offset(Direction.NORTH), leaves);
//        }
//        if(world.getBlockState(currentPos.offset(Direction.SOUTH)) != log) {
//            world.setBlockState(currentPos.offset(Direction.SOUTH), leaves);
//        }
//        if(world.getBlockState(currentPos.offset(Direction.EAST)) != log) {
//            world.setBlockState(currentPos.offset(Direction.EAST), leaves);
//        }
//        if(world.getBlockState(currentPos.offset(Direction.WEST)) != log) {
//            world.setBlockState(currentPos.offset(Direction.WEST), leaves);
//        }
//        if(world.getBlockState(currentPos.offset(Direction.UP)) != log) {
//            world.setBlockState(currentPos.offset(Direction.UP), leaves);
//        }
//        if(world.getBlockState(currentPos.offset(Direction.DOWN)) != log) {
//            world.setBlockState(currentPos.offset(Direction.DOWN), leaves);
//        }
//        //handler.setVariable("currentPos", currentPos.offset(x));
        return "doMoreTree";
    }

    public String finalizeTree() {
////        CrystalArchitect.LOGGER.info("THIS SHOULD FINALIZE AND REPLACE ITSELF");
////        BlockPos startPos = (BlockPos) handler.getVariable("startPos").get();
////        world.setBlockState(startPos, log);
//        BlockPos currentPos = (BlockPos) handler.getVariable("currentPos").get();
//        BlockPos stemPos1 = (BlockPos) handler.getVariable("stemPos1").get();
//        if(world.getBlockState(currentPos.offset(Direction.NORTH)) != log) {
//            world.setBlockState(currentPos.offset(Direction.NORTH), leaves);
//        }
//        if(world.getBlockState(currentPos.offset(Direction.SOUTH)) != log) {
//            world.setBlockState(currentPos.offset(Direction.SOUTH), leaves);
//        }
//        if(world.getBlockState(currentPos.offset(Direction.EAST)) != log) {
//            world.setBlockState(currentPos.offset(Direction.EAST), leaves);
//        }
//        if(world.getBlockState(currentPos.offset(Direction.WEST)) != log) {
//            world.setBlockState(currentPos.offset(Direction.WEST), leaves);
//        }
//        if(world.getBlockState(currentPos.offset(Direction.UP)) != log) {
//            world.setBlockState(currentPos.offset(Direction.UP), leaves);
//        }
//        if(world.getBlockState(currentPos.offset(Direction.DOWN)) != log) {
//            world.setBlockState(currentPos.offset(Direction.DOWN), leaves);
//        }
//
//        if(world.getBlockState(currentPos.offset(Direction.NORTH,2)) != log) {
//            world.setBlockState(currentPos.offset(Direction.NORTH,2), leaves);
//        }
//        if(world.getBlockState(currentPos.offset(Direction.SOUTH,2)) != log) {
//            world.setBlockState(currentPos.offset(Direction.SOUTH,2), leaves);
//        }
//        if(world.getBlockState(currentPos.offset(Direction.EAST,2)) != log) {
//            world.setBlockState(currentPos.offset(Direction.EAST,2), leaves);
//        }
//        if(world.getBlockState(currentPos.offset(Direction.WEST,2)) != log) {
//            world.setBlockState(currentPos.offset(Direction.WEST,2), leaves);
//        }
//        if(world.getBlockState(currentPos.offset(Direction.UP,2)) != log) {
//            world.setBlockState(currentPos.offset(Direction.UP,2), leaves);
//        }
//        if(world.getBlockState(currentPos.offset(Direction.DOWN,2)) != log) {
//            world.setBlockState(currentPos.offset(Direction.DOWN,2), leaves);
//        }
//        //topofstem
//        world.setBlockState(stemPos1.offset(Direction.NORTH), log);
//        world.setBlockState(stemPos1.offset(Direction.NORTH,2), log);
//        world.setBlockState(stemPos1.offset(Direction.NORTH,3), log);
//        stemPos1.add(0,3,0);
//        if(world.getBlockState(stemPos1.offset(Direction.NORTH)) != log) {
//            world.setBlockState(stemPos1.offset(Direction.NORTH), leaves);
//        }
//        if(world.getBlockState(stemPos1.offset(Direction.SOUTH)) != log) {
//            world.setBlockState(stemPos1.offset(Direction.SOUTH), leaves);
//        }
//        if(world.getBlockState(stemPos1.offset(Direction.EAST)) != log) {
//            world.setBlockState(stemPos1.offset(Direction.EAST), leaves);
//        }
//        if(world.getBlockState(stemPos1.offset(Direction.WEST)) != log) {
//            world.setBlockState(stemPos1.offset(Direction.WEST), leaves);
//        }
//        if(world.getBlockState(stemPos1.offset(Direction.UP)) != log) {
//            world.setBlockState(stemPos1.offset(Direction.UP), leaves);
//        }
//        if(world.getBlockState(stemPos1.offset(Direction.DOWN)) != log) {
//            world.setBlockState(stemPos1.offset(Direction.DOWN), leaves);
//        }
//
//        if(world.getBlockState(stemPos1.offset(Direction.NORTH,2)) != log) {
//            world.setBlockState(stemPos1.offset(Direction.NORTH,2), leaves);
//        }
//        if(world.getBlockState(stemPos1.offset(Direction.SOUTH,2)) != log) {
//            world.setBlockState(stemPos1.offset(Direction.SOUTH,2), leaves);
//        }
//        if(world.getBlockState(stemPos1.offset(Direction.EAST,2)) != log) {
//            world.setBlockState(stemPos1.offset(Direction.EAST,2), leaves);
//        }
//        if(world.getBlockState(stemPos1.offset(Direction.WEST,2)) != log) {
//            world.setBlockState(stemPos1.offset(Direction.WEST,2), leaves);
//        }
//        if(world.getBlockState(stemPos1.offset(Direction.UP,2)) != log) {
//            world.setBlockState(stemPos1.offset(Direction.UP,2), leaves);
//        }
//        if(world.getBlockState(stemPos1.offset(Direction.DOWN,2)) != log) {
//            world.setBlockState(stemPos1.offset(Direction.DOWN,2), leaves);
//        }
//

        return "finalizeTree";
    }

    public String doMoreTree(Direction x) {
//        Cinderbane.LOGGER.info("THIS SHOULD BE MORE TREE LOOP");
//        BlockPos stemPos1 = (BlockPos) handler.getVariable("stemPos1").get();
//        world.setBlockState(stemPos1.offset(x), log);
//        handler.setVariable("stemPos1", stemPos1.offset(x));

        return "doMoreBranches";
    }

    public String doMoreBranches(Direction x) {
//        Cinderbane.LOGGER.info("THIS SHOULD BE DO MORE BRANCHES LOOP");
//        BlockPos stemPos1 = (BlockPos) handler.getVariable("stemPos1").get();
//        //BlockPos currentPos = (BlockPos) handler.getVariable("currentPos").get();
//        world.setBlockState(stemPos1.offset(x), log);
//        handler.setVariable("currentPos", stemPos1.offset(x));

        return "finalizeTree";
    }


}

//    @Override
//    public void tick() {
//        if (!world.isRemote) {
//            handler.tick();
//        }
//    }
//
//    //When the status is PLANTED the first tick afterwards runs the initialization code.
//    public String initialize() {
//        CrystalArchitect.LOGGER.info("THIS SHOULD START");
//        handler.addVariable("currentPos", this.getPos());
//        BlockPos currentPos = (BlockPos) handler.getVariable("currentPos").get();
////            world.setBlockState(currentPos.offset(Direction.WEST, 1), log);
////            world.setBlockState(currentPos.offset(Direction.NORTH, 1), log);
////            world.setBlockState(currentPos.offset(Direction.EAST, 1), log);
////            world.setBlockState(currentPos.offset(Direction.SOUTH, 1), log);
//        return "firstLoop";
//    }
//
//    //When the status is FIRSTLOOP it runs this code
//    public String doFirstLoop(int x) {
//        CrystalArchitect.LOGGER.info("THIS SHOULD BE FIRST LOOP");
//        BlockPos currentPos = (BlockPos) handler.getVariable("currentPos").get();
//        world.setBlockState(currentPos.offset(Direction.UP, x), log);
//        handler.setVariable("currentPos", currentPos.add(0, 1, 0));
//        return "everythingElse";
//    }
//
//    public String doEverythingElse(int x) {
//        CrystalArchitect.LOGGER.info("THIS SHOULD BE FIRST LOOP");
//        CrystalArchitect.LOGGER.info("THIS SHOULD BE FIRST LOOP");
//        BlockPos currentPos = (BlockPos) handler.getVariable("currentPos").get();
//        world.setBlockState(currentPos.offset(Direction.NORTH, x), log);
//        handler.setVariable("currentPos", currentPos.add(0, 1, 0));
//        return "everythingElse";
//    }
//
//    public String thisIsAwesome(int x) {
//        CrystalArchitect.LOGGER.info("THIS SHOULD BE AWESOME");
//        BlockPos currentPos = (BlockPos) handler.getVariable("currentPos").get();
//        world.setBlockState(currentPos.offset(Direction.UP, x), log);
//        handler.setVariable("currentPos", currentPos.add(0, 1, 0));
//        return "everythingElse";
//    }
//
//
//}



