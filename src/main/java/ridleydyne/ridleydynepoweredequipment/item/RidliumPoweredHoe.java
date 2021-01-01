package ridleydyne.ridleydynepoweredequipment.item;

import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTier;
import ridleydyne.ridleydynepoweredequipment.RidleyDynePoweredEquipment;
import ridleydyne.ridleydynepoweredequipment.init.ModItems;
import ridleydyne.ridleydynepoweredequipment.itemtier.ModItemTier;

public class RidliumPoweredHoe extends HoeItem {
    public RidliumPoweredHoe(ModItemTier tier) {
        // tier, attack damage, attack speed, builder ??
        super(tier, -3, 0.0F, ModItems.defaultItemProperties(1));
    }
    
}
