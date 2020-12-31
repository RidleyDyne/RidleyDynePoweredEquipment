package ridleydyne.ridleydynepoweredequipment.item;

import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import ridleydyne.ridleydynepoweredequipment.RidleyDynePoweredEquipment;
import ridleydyne.ridleydynepoweredequipment.init.ModItems;
import ridleydyne.ridleydynepoweredequipment.itemtier.ModItemTier;

public class RidliumPoweredHoe extends HoeItem {
    public RidliumPoweredHoe() {
        // tier, attack damage, attack speed, builder ??
        super(ModItemTier.BASE, -1, -2.4F, ModItems.defaultItemProperties(1));
    }
    
}
