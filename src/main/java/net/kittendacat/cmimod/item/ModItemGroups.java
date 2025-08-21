package net.kittendacat.cmimod.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.kittendacat.cmimod.CMIMod;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup CMI_MOD_ITEMS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(CMIMod.MOD_ID, "cmi_mod_items"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.BUCKET_OF_TNT))
                    .displayName(Text.translatable("itemGroup.cmimod.cmi_mod_items"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.BUCKET_OF_TNT);
                    }).build());

    public static void registerItemGroups() {

        CMIMod.LOGGER.info("Registering Item Groups For " + CMIMod.MOD_ID);

    }
}
