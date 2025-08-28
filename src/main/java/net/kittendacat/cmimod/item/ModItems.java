package net.kittendacat.cmimod.item;

import net.kittendacat.cmimod.CMIMod;
import net.kittendacat.cmimod.item.custom.FishyBucketOfMuchTNTItem;
import net.kittendacat.cmimod.item.custom.FishyBucketOfTNTItem;
import net.kittendacat.cmimod.item.custom.VeryFishyBucketOfMuchTNTItem;
import net.kittendacat.cmimod.item.custom.VeryFishyBucketOfTNTItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    //public static final Item BUCKET_OF_TNT = registerItem("bucket_of_tnt", new Item(new Item.Settings()));

    public static final Item FISHY_BUCKET_OF_TNT = registerItem("fishy_bucket_of_tnt", new FishyBucketOfTNTItem(new Item.Settings()
            .maxCount(1)));

    public static final Item VERY_FISHY_BUCKET_OF_TNT = registerItem("very_fishy_bucket_of_tnt", new VeryFishyBucketOfTNTItem(new Item.Settings()
            .maxCount(1)));

    public static final Item FISHY_BUCKET_OF_MUCH_TNT = registerItem("fishy_bucket_of_much_tnt", new FishyBucketOfMuchTNTItem(new Item.Settings()
            .maxCount(1)));

    public static final Item VERY_FISHY_BUCKET_OF_MUCH_TNT = registerItem("very_fishy_bucket_of_much_tnt", new VeryFishyBucketOfMuchTNTItem(new Item.Settings()
            .maxCount(1)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(CMIMod.MOD_ID, name), item);
    }

    public static void registerModItems() {

        CMIMod.LOGGER.info("Registering Mod Items For " + CMIMod.MOD_ID);

    }
}
