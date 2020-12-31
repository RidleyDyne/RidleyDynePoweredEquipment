package ridleydyne.ridleydynepoweredequipment.item;

import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import ridleydyne.ridleydynepoweredequipment.RidleyDynePoweredEquipment;
import ridleydyne.ridleydynepoweredequipment.itemtier.ModItemTier;

public class RidliumPoweredAxe extends AxeItem {
    public RidliumPoweredAxe() {
        // tier, attack damage, attack speed, builder ??
        super(ModItemTier.BASE, 2, -2.4F, new Item.Properties()
        .maxStackSize(1)        
        .group(RidleyDynePoweredEquipment.ITEM_GROUP)
        );
    }
    
}
