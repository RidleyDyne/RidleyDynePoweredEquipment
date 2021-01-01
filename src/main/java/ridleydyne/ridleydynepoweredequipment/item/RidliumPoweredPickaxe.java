package ridleydyne.ridleydynepoweredequipment.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemTier;
import net.minecraft.item.PickaxeItem;
import ridleydyne.ridleydynepoweredequipment.RidleyDynePoweredEquipment;
import ridleydyne.ridleydynepoweredequipment.init.ModItems;
import ridleydyne.ridleydynepoweredequipment.itemtier.ModItemTier;

public class RidliumPoweredPickaxe extends PickaxeItem {
    public RidliumPoweredPickaxe(ModItemTier tier) {
        // tier, attack damage, attack speed, builder ??
        super(tier, 1, -2.8F, ModItems.defaultItemProperties(1));
    }
    
    
}
