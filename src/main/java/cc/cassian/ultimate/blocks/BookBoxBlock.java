package cc.cassian.ultimate.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.Random;

public class BookBoxBlock extends HorizontalFacingBlock {
    private static final char[] CHARACTERS = new char[]{' ', ',', '.', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

    public BookBoxBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH));

    }
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }


    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        Direction dir = state.get(FACING);
        int posY = pos.getY();
        int posX;
        byte switchFacing;


        switch (dir.ordinal()) {
            case 1:
                posX = 15 - pos.getX() & 15;
                switchFacing = 0;
                break;
            case 2:
                posX = pos.getX() & 15;
                switchFacing = 2;
                break;
            case 3:
                posX = 15 - pos.getZ() & 15;
                switchFacing = 1;
                break;
            case 4:
            default:
                posX = pos.getZ() & 15;
                switchFacing = 3;
        }

        if (posX > 0 && posX < 15) {
            ChunkPos cPos = new ChunkPos(pos);
            String bookTitle = cPos.x + "/" + cPos.z + "/" + switchFacing + "/" + posX + "/" + posY;
            Random randomX = new Random(cPos.x);
            Random randomZ = new Random(cPos.z);
            Random randomY = new Random((posX << 8) + ((long) posY << 4) + switchFacing);
            ItemStack stack = new ItemStack(Items.WRITTEN_BOOK);
            NbtCompound nbt = stack.getOrCreateNbt();
            NbtList pageContent = new NbtList();

            for(int i = 0; i < 16; ++i) {
                StringBuilder pageString = new StringBuilder();

                for(int c = 0; c < 128; ++c) {
                    int pageText = randomX.nextInt() + randomZ.nextInt() + -randomY.nextInt();
                    pageString.append(CHARACTERS[Math.floorMod(pageText, CHARACTERS.length)]);
                }

                pageContent.add(NbtString.of(Text.Serializer.toJson(Text.literal(pageString.toString()))));
            }

            nbt.put("pages", pageContent);
            nbt.putString("author", "§kUniverse itself");
            nbt.putString("title", bookTitle);
            dropStack(world, pos, dir, stack);

            return ActionResult.SUCCESS;
        } else {
            return ActionResult.FAIL;
        }
    }
//        return ActionResult.SUCCESS; }

}
