package ridleydyne.ridleydynepoweredequipment.init;

import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import ridleydyne.ridleydynepoweredequipment.RidleyDynePoweredEquipment;
import ridleydyne.ridleydynepoweredequipment.item.*;
import ridleydyne.ridleydynepoweredequipment.itemtier.*;

public enum ModItems {
    RIDLIUM_ENERGY_CRYSTAL(new RidliumEnergyCrystal()),
    RIDLIUM_POWERED_SHOVEL(new RidliumPoweredShovel(ModItemTier.TIER1)), 
    RIDLIUM_POWERED_HOE(new RidliumPoweredHoe(ModItemTier.TIER1)),
    RIDLIUM_POWERED_AXE(new RidliumPoweredAxe(ModItemTier.TIER1)),
    RIDLIUM_POWERED_PICKAXE(new RidliumPoweredPickaxe(ModItemTier.TIER1)),
    RIDLIUM_POWERED_SWORD(new RidliumPoweredSword(ModItemTier.TIER1));

    private final Item item;

    ModItems(Item item) {
        this.item = item;
        this.item.setRegistryName(this.getName());
    }

    public String getName() {
        return String.valueOf(this).toLowerCase();
    }

    public Item getItem() {
        return this.item;
    }
   
    public static Properties defaultItemProperties(int maxStackSize) {
        return new Item.Properties()
            .maxStackSize(1)        
            .group(RidleyDynePoweredEquipment.ITEM_GROUP);
    }
}
