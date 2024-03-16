package cc.cassian.ultimate;

import cc.cassian.ultimate.blocks.ModStairs;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.SlabBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.Identifier;

public class UltimateContentMod implements ModInitializer {

    public static final Block BOOK_BOX  = new Block(FabricBlockSettings.copy(Blocks.BOOK_BOX).build());
    public static final Block NETHERITE_STAIRS = new ModStairs(Blocks.NETHERITE_BLOCK.getDefaultState(),FabricBlockSettings.copy(Blocks.NETHERITE_BLOCK).build());
    public static final Block NETHERITE_SLAB = new SlabBlock(FabricBlockSettings.copy(Blocks.NETHERITE_BLOCK).build());
    public static final Block CURSOR = new Block(FabricBlockSettings.copy(Blocks.CURSOR).build());
    public static final Block ANT = new Block(FabricBlockSettings.copy(Blocks.ANT).build());




    @Override
    public void onInitialize() {
        String[] ultimateBlockIDs = {
                "book_box", "netherite_stairs", "netherite_slab", "cursor", "ant"
        };
        Block[] ultimateBlocks = {
                BOOK_BOX, NETHERITE_STAIRS, NETHERITE_SLAB, CURSOR, ANT
        };
        for (int i = 0; i < ultimateBlockIDs.length; i++) {
            Registry.register(Registry.BLOCK, new Identifier("pyrite", ultimateBlockIDs[i]), ultimateBlocks[i]);
            Registry.register(Registry.ITEM, new Identifier("ultimate", ultimateBlockIDs[i]), new BlockItem(ultimateBlocks[i], new Item.Settings().group(ItemGroup.MISC)));
        }


    }
}
