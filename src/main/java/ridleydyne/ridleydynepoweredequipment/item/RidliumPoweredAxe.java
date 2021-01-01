package ridleydyne.ridleydynepoweredequipment.item;

import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTier;
import ridleydyne.ridleydynepoweredequipment.RidleyDynePoweredEquipment;
import ridleydyne.ridleydynepoweredequipment.init.ModItems;
import ridleydyne.ridleydynepoweredequipment.itemtier.ModItemTier;

public class RidliumPoweredAxe extends AxeItem {
    public RidliumPoweredAxe(ModItemTier tier) {
        // tier, attack damage, attack speed, builder ??
        super(tier, 5, -3.0F, ModItems.defaultItemProperties(1));
    }
    
}
