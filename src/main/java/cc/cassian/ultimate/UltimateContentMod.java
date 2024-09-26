package cc.cassian.ultimate;

import cc.cassian.ultimate.blocks.AntBlock;
import cc.cassian.ultimate.blocks.ModStairs;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
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

    public static final Block NETHERITE_STAIRS = new ModStairs(Blocks.NETHERITE_BLOCK.getDefaultState(),AbstractBlock.Settings.copy(Blocks.NETHERITE_BLOCK).strength(3.0f));
    public static final Block NETHERITE_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.NETHERITE_BLOCK));
    public static final Block CURSOR = new Block(AbstractBlock.Settings.create().strength(1.8f).mapColor(MapColor.GREEN).sounds(BlockSoundGroup.STONE));
    public static final Block ANT = new AntBlock(AbstractBlock.Settings.create().strength(-1f, 3600000.0f).mapColor(MapColor.WHITE).sounds(BlockSoundGroup.METAL).dropsNothing());
    public static final Item FOOTPRINT = new Item(new Item.Settings());
    public static final Item FINE_ITEM = new Item(new Item.Settings());



    @Override
    public void onInitialize() {
        String[] ultimateBlockIDs = {
                "netherite_stairs", "netherite_slab", "cursor", "ant"
        };
        Block[] ultimateBlocks = {
                NETHERITE_STAIRS, NETHERITE_SLAB, CURSOR, ANT
        };
        for (int i = 0; i < ultimateBlockIDs.length; i++) {
            Registry.register(Registries.BLOCK, Identifier.of("ultimate", ultimateBlockIDs[i]), ultimateBlocks[i]);
            Registry.register(Registries.ITEM, Identifier.of("ultimate", ultimateBlockIDs[i]), new BlockItem(ultimateBlocks[i], new Item.Settings()));
        }

        Registry.register(Registries.ITEM, Identifier.of("ultimate", "footprint"), FOOTPRINT);
        Registry.register(Registries.ITEM, Identifier.of("ultimate", "fine_item"), FINE_ITEM);



        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(content -> {
            content.addAfter(Items.NETHERITE_BLOCK, NETHERITE_SLAB);
            content.addAfter(Items.NETHERITE_BLOCK, NETHERITE_STAIRS);

        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(content -> content.addAfter(Items.LODESTONE, ANT));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COLORED_BLOCKS).register(content -> content.addAfter(Items.PINK_TERRACOTTA, CURSOR));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(content -> content.addAfter(Items.DISC_FRAGMENT_5, FOOTPRINT, FINE_ITEM));
    }
}
