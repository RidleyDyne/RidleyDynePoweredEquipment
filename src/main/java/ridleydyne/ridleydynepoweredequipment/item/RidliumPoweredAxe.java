package ridleydyne.ridleydynepoweredequipment.item;

import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import ridleydyne.ridleydynepoweredequipment.RidleyDynePoweredEquipment;
import ridleydyne.ridleydynepoweredequipment.init.ModItems;
import ridleydyne.ridleydynepoweredequipment.itemtier.ModItemTier;

public class RidliumPoweredAxe extends AxeItem {
    public RidliumPoweredAxe() {
        // tier, attack damage, attack speed, builder ??
        super(ModItemTier.TIER1, 2, -2.4F, ModItems.defaultItemProperties(1));
    }
    
}
