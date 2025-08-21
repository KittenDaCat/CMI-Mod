package net.kittendacat.cmimod;

import net.fabricmc.api.ModInitializer;

import net.kittendacat.cmimod.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CMIMod implements ModInitializer {

	public static final String MOD_ID = "cmimod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
        ModItems.registerModItems();
	}
}
