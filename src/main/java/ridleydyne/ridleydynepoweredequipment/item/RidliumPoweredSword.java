package ridleydyne.ridleydynepoweredequipment.item;

import net.minecraft.item.SwordItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTier;
import ridleydyne.ridleydynepoweredequipment.RidleyDynePoweredEquipment;
import ridleydyne.ridleydynepoweredequipment.itemtier.ModItemTier;
import ridleydyne.ridleydynepoweredequipment.init.ModItems;

public class RidliumPoweredSword extends SwordItem {
    public RidliumPoweredSword(ModItemTier tier) {
        // tier, attack damage, attack speed, builder ??
        super(tier, 3, -2.4F, ModItems.defaultItemProperties(1));
    }
    
}
