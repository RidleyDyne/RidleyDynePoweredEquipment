package ridleydyne.ridleydynepoweredequipment.init;

import net.minecraft.item.Item;
import ridleydyne.ridleydynepoweredequipment.item.*;

public enum ModItems {
    RIDLIUM_ENERGY_CRYSTAL(new RidliumEnergyCrystal()),
    RIDLIUM_POWERED_SHOVEL(new RidliumPoweredShovel(500));

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
   
}
