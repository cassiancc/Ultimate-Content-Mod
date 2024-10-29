package cc.cassian.ultimate;

import cc.cassian.ultimate.blocks.ModStairs;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;

public final class UltimateContentMod {

    public static final String MOD_ID = "ultimate";
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(MOD_ID, RegistryKeys.BLOCK);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MOD_ID, RegistryKeys.ITEM);

    public static final RegistrySupplier<Block> NETHERITE_STAIRS = BLOCKS.register("netherite_stairs", () ->
            new ModStairs(Blocks.NETHERITE_BLOCK.getDefaultState(), AbstractBlock.Settings.copy(Blocks.NETHERITE_BLOCK)));
    public static final RegistrySupplier<Block> NETHERITE_SLAB = BLOCKS.register("netherite_slab", () ->
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.NETHERITE_BLOCK)));
    public static final RegistrySupplier<Block> CURSOR = BLOCKS.register("cursor", () ->
            new Block(AbstractBlock.Settings.create().strength(1.8f).mapColor(MapColor.GREEN).sounds(BlockSoundGroup.STONE)));
    public static final RegistrySupplier<Block> ANT = BLOCKS.register("ant", () ->
            new Block(AbstractBlock.Settings.create().strength(-1f, 3600000.0f).mapColor(MapColor.WHITE).sounds(BlockSoundGroup.METAL).dropsNothing()));

    public static final RegistrySupplier<Item> FOOTPRINT = ITEMS.register("footprint", () -> new Item(new Item.Settings()));
    public static final RegistrySupplier<Item> FINE_ITEM = ITEMS.register("fine_item", () -> new Item(new Item.Settings()));

    public static void registerSimpleBlockItem(RegistrySupplier<Block> block, RegistryKey<ItemGroup> group, Item item) {
        var blockItem = registerSimpleBlockItem(block, new Item.Settings());
        addAfter(blockItem, group, item);
    }

    public static RegistrySupplier<Item> registerSimpleBlockItem(RegistrySupplier<Block> block, Item.Settings settings) {
        return ITEMS.register(block.getId(), () -> new BlockItem(block.get(), settings));
    }

    public static void addAfter(RegistrySupplier<Item> blockItem, RegistryKey<ItemGroup> group, Item item) {
        ItemGroupEvents.modifyEntriesEvent(group).register(content -> content.addAfter(item, blockItem.get()));

    }

    public static void init() {
        BLOCKS.register();
        ITEMS.register();
        registerSimpleBlockItem(NETHERITE_STAIRS, ItemGroups.BUILDING_BLOCKS, Items.NETHERITE_BLOCK);
        registerSimpleBlockItem(NETHERITE_SLAB, ItemGroups.BUILDING_BLOCKS, Items.NETHERITE_BLOCK);
        registerSimpleBlockItem(ANT, ItemGroups.FUNCTIONAL, Items.LODESTONE);
        registerSimpleBlockItem(CURSOR, ItemGroups.COLORED_BLOCKS, Items.PINK_TERRACOTTA);
        registerSimpleBlockItem(CURSOR, ItemGroups.INGREDIENTS, Items.PINK_TERRACOTTA);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(content -> content.addAfter(Items.DISC_FRAGMENT_5, FOOTPRINT.get(), FINE_ITEM.get()));
    }
}
