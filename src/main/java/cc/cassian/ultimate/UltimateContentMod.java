package cc.cassian.ultimate;

import cc.cassian.ultimate.blocks.AntBlock;
import cc.cassian.ultimate.blocks.BookBoxBlock;
import cc.cassian.ultimate.blocks.ModFire;
import cc.cassian.ultimate.blocks.ModStairs;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class UltimateContentMod implements ModInitializer {

    public static final Block BOOK_BOX  = new BookBoxBlock(FabricBlockSettings.create().strength(1.5f).sounds(BlockSoundGroup.WOOD).mapColor(MapColor.BROWN));
    public static final Block NETHERITE_STAIRS = new ModStairs(Blocks.NETHERITE_BLOCK.getDefaultState(),FabricBlockSettings.copyOf(Blocks.NETHERITE_BLOCK).strength(3.0f));
    public static final Block NETHERITE_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.NETHERITE_BLOCK));
    public static final Block CURSOR = new Block(FabricBlockSettings.create().strength(1.8f).mapColor(MapColor.GREEN).sounds(BlockSoundGroup.STONE));
    public static final Block ANT = new AntBlock(FabricBlockSettings.create().strength(-1f, 3600000.0f).mapColor(MapColor.WHITE).sounds(BlockSoundGroup.METAL).dropsNothing());
    public static final Item FOOTPRINT = new Item(new FabricItemSettings());
    public static final Item FINE_ITEM = new Item(new FabricItemSettings());
    public static final Block FIRE = new ModFire(FabricBlockSettings.create().strength(0.1f).mapColor(MapColor.RED).sounds(BlockSoundGroup.STONE).dropsNothing().noCollision().luminance(15));



    @Override
    public void onInitialize() {
        String[] ultimateBlockIDs = {
                "book_box", "netherite_stairs", "netherite_slab", "cursor", "ant", "fire"
        };
        Block[] ultimateBlocks = {
                BOOK_BOX, NETHERITE_STAIRS, NETHERITE_SLAB, CURSOR, ANT, FIRE
        };
        for (int i = 0; i < ultimateBlockIDs.length; i++) {
            Registry.register(Registries.BLOCK, new Identifier("ultimate", ultimateBlockIDs[i]), ultimateBlocks[i]);
            Registry.register(Registries.ITEM, new Identifier("ultimate", ultimateBlockIDs[i]), new BlockItem(ultimateBlocks[i], new FabricItemSettings()));
        }

        Registry.register(Registries.ITEM, new Identifier("ultimate", "footprint"), FOOTPRINT);
        Registry.register(Registries.ITEM, new Identifier("ultimate", "fine_item"), FINE_ITEM);



        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(content -> {
            content.addAfter(Items.NETHERITE_BLOCK, NETHERITE_SLAB);
            content.addAfter(Items.NETHERITE_BLOCK, NETHERITE_STAIRS);

        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(content -> {
            content.addAfter(Items.LODESTONE, ANT);


        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COLORED_BLOCKS).register(content -> {
            content.addAfter(Items.PINK_TERRACOTTA, CURSOR);

        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(content -> {
            content.addAfter(Items.DISC_FRAGMENT_5, FOOTPRINT, FINE_ITEM);


        });
    }
}
