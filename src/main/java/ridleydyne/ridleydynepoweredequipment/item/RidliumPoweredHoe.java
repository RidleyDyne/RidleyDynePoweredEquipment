package ridleydyne.ridleydynepoweredequipment.item;

import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import ridleydyne.ridleydynepoweredequipment.RidleyDynePoweredEquipment;
import ridleydyne.ridleydynepoweredequipment.itemtier.ModItemTier;

public class RidliumPoweredHoe extends HoeItem {
    public RidliumPoweredHoe() {
        // tier, attack damage, attack speed, builder ??
        super(ModItemTier.BASE, -1, -2.4F, new Item.Properties()
        .maxStackSize(1)        
        .group(RidleyDynePoweredEquipment.ITEM_GROUP)
        );
    }
    
}
