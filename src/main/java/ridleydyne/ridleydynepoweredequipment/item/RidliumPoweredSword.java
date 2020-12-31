package ridleydyne.ridleydynepoweredequipment.item;

import net.minecraft.item.SwordItem;
import net.minecraft.item.Item;
import ridleydyne.ridleydynepoweredequipment.RidleyDynePoweredEquipment;
import ridleydyne.ridleydynepoweredequipment.itemtier.ModItemTier;
import ridleydyne.ridleydynepoweredequipment.init.ModItems;

public class RidliumPoweredSword extends SwordItem {
    public RidliumPoweredSword() {
        // tier, attack damage, attack speed, builder ??
        super(ModItemTier.BASE, 4, -2.4F, ModItems.defaultItemProperties(1));
    }
    
}
